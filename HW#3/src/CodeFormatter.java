import java.io.*;
import java.lang.String;
import java.util.Scanner;

public class CodeFormatter 
{
	
	public static void main(String args[]) throws Exception
	{
		Scanner scan = new Scanner(System.in);//Scanner to receive user input
		System.out.println("Please enter the name of the text file you wish to format.");
		String name = scan.nextLine();//Sets name equal to user input
		
		/**
		 * Creates file input stream from given file.
		 * Precondition: name is valid name of file in the directory
		 */
		try
		{
			FileInputStream fis = new FileInputStream(name); 
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			StringStack parenStack = new StringStack();//Stack to keep track of parenthesis
			StringStack braceStack = new StringStack();//Stack to keep track of braces
			StringStack indents = new StringStack();//Stack to keep track of indentation
			String formattedText = "";//The final string to return (formatted)
			String data;//The string that holds a line of a text file
			boolean type= false;
			while((data = stdin.readLine()) != null)
			{
				int i = 0;//Creates a counter that points at characters in the String
				while(i<data.length())
				{
					/**
				 	* If line is empty, then read next line
				 	*/
					if(data.equals(""))
						stdin.readLine();
				
					/**
				 	* Otherwise, manipulate the string
				 	*/
					else
						{
						/**
					 	* Checks to see if the line only consists of spaces.
					 	*/
						String s = data + "d";
						if(s.trim().equals("d"))
						{
							data="";
						}
					
					
						data = data.trim();//Trims spaces at the beginning of a line.
					
						/**
					 	* Checks for key characters, if data.charAt(i) is not a key character, add to formattedText and increment i
					 	*/
						if(i<data.length() && data.charAt(i)!='p' && data.charAt(i)!='f' && data.charAt(i)!='i' && data.charAt(i)!='w' && data.charAt(i)!=';' && data.charAt(i)!='{' && data.charAt(i)!='}')
						{
							formattedText += data.charAt(i);
							i++;
						}
					
						/**
					 	* If key character was found, check to see if it is indeed a key character that belongs to a block.
					 	*/
						else if(i == data.indexOf("public") || i == data.indexOf("private") || i == data.indexOf("if") || i == data.indexOf("while") || i == data.indexOf("for") || i == data.indexOf("{"))
						{
							/**
						 	* Used to check if block is a method later
						 	*/
							if(i == data.indexOf("public") || i == data.indexOf("private"))
								type = true;
							else if(i == data.indexOf("if") || i == data.indexOf("while") || i == data.indexOf("for"))
								type = false;
						
							if(i == data.indexOf("if") || i == data.indexOf("while") || i == data.indexOf("for"))
							{
								while(i<data.length() && data.charAt(i) != ')')
								{
									if(data.charAt(i) != '(')
									{
										formattedText += data.charAt(i);
										i++;
									}
									else if(data.charAt(i) == '(')
									{
										parenStack.push("(");
										formattedText += data.charAt(i);
										i++;
										while(!parenStack.isEmpty())
										{
											if(data.charAt(i) == '(')
											{
												parenStack.push("(");
												
										
											}
											else if(data.charAt(i) == ')')
											{
												parenStack.pop();
												
											}
											if(i<data.length())
											{	formattedText += data.charAt(i);
												i++;
											}
										}
									}
								}
								data = data.trim();
								if(data.charAt(0)!='{')
								{
									
									if(i<data.length())
									{
										braceStack.push("{");
										formattedText += "\n";
										indents = braceStack.clone();
										while(!indents.isEmpty())//Indents once for every brace found in braceStack
										{
											formattedText += "	";
											indents.pop();
										}
										while(i<data.length() && data.trim().charAt(0)!=';')
										{
											formattedText += data.charAt(i);
											i++;
										}
										braceStack.pop();
										formattedText += "\n";
									}
								}
							}
							
							/**
						 	* While the character is not an important character, add to formattedText and increment
						 	*/
							while(i<data.length() && data.charAt(i) != ';' && data.charAt(i) != '(' && data.charAt(i) != '{' && data.charAt(i) != '}')
							{
								formattedText += data.charAt(i);
								i++;
							}
						
							/**
						 	* If character at i is '(', then print everything until the appropriate closing parenthesis is found.
						 	*/
							if(i<data.length() && data.charAt(i) == '(')
							{
								parenStack.push("(");
								formattedText += data.charAt(i);
								i++;
								while(!parenStack.isEmpty())
								{
									if(data.charAt(i) == '(')
									{
										parenStack.push("(");
								
									}
									else if(data.charAt(i) == ')')
									{
										parenStack.pop();
									}
									formattedText += data.charAt(i);
									i++;
								}
							}
						
							/**
						 	* If character at i is '{', then push into braceStack, go to a new line, trim the remaining string, and indent before adding that string.
						 	*/
							else if(i<data.length() && data.charAt(i) == '{')
							{
								braceStack.push("{");
								formattedText += data.charAt(i);
								formattedText += "\n";
								i++;
								data = data.trim();
								indents = braceStack.clone();
								while(!indents.isEmpty())//Indents once for every brace found in braceStack
								{
									formattedText += "	";
									indents.pop();
								}
								data = data.substring(i, data.length());
								i=0;
							}
						
							/**
						 	* If character at i is '}', then remove last indentation, pop a brace from braceStack, and go to new line. Trim remaining string.
						 	*/
							else if(i<data.length() && data.charAt(i) == '}')
							{
								formattedText = formattedText.substring(0, formattedText.length()-1);
								braceStack.pop();
								if(i<data.length())
								{
									formattedText += data.charAt(i);
									formattedText += "\n";
									i++;
									data = data.trim();
									indents = braceStack.clone();
									while(!indents.isEmpty())
									{
										formattedText += "	";
										indents.pop();
									}
									data = data.substring(i, data.length());
									i=0;
								}
							}
						}
					
						/**
					 	* If character at i is a key character that did not belong to previous group of key characters, manipulate text.
					 	*/
						else if(i<data.length() && (data.charAt(i) == ';' || data.charAt(i) == '{'))
						{
							/**
						 	* If character is opening brace, enter new line, trim, and push into braceStack.
						 	*/
							if(data.charAt(i) == '{')
								braceStack.push("{");
							formattedText += data.charAt(i);
							formattedText += "\n";
							i++;
							data = data.trim();
							indents = braceStack.clone();
							while(!indents.isEmpty())
							{
								formattedText += "	";
								indents.pop();
							}
							data = data.substring(i, data.length());
							i=0;
						}
						
						/**
					 	* If character is closing brace, delete last indent, trim, and go to new line.
					 	*/
						else if((i<data.length() && (data.charAt(i) == '}')))
						{
							formattedText = formattedText.substring(0, formattedText.length()-1);
							braceStack.pop();
							formattedText += data.charAt(i);
							formattedText += "\n";
							i++;
							data = data.trim();
							if(type) //If true, means that it is a block of private or public, meaning it is a class or method, extra line
								formattedText += "\n";
							indents = braceStack.clone();
							while(!indents.isEmpty())
							{
							formattedText += "	";
							indents.pop();
							}
							data = data.substring(i, data.length());
							i=0;
						}
					
						/**
					 	* If no conditions met, append character and increment.
					 	*/
						else
						{
							if(i<data.length())
								formattedText += data.charAt(i);
							i++;
						}
					}
				}
			}
			System.out.println(formattedText); //Prints final formatted code.
			fis.close();
			scan.close();
		}
		catch(EmptyStackException e)
		{
			System.out.println("There are more closing braces then opening braces in the text file.");
		}
		/*catch(Exception e)
		{
			System.out.println("File not found in given directory.");
		}*/
	}
}
