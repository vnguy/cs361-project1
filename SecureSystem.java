/*SecureSystem
Sets up Objects - passes them to ReferenceMonitor class to be stored
Sets up Subjects - passes them to ReferenceMonitor class to be stored
Reads in a file line by line - passes line to InstructionObject class, gets InstructionObject back
Pass InstructionObject to ReferenceMonitor
Prints out new state*/
import java.io.*;
public class SecureSystem {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String file = args[0];
		ReferenceManager RM = new ReferenceManager();
		Subject Lyle = new Subject("Lyle", 0,0);
		Subject Hal = new Subject("Hal", 1, 0);
		Object LObj = new Object("LObj", 0, 0);
		Object HObj = new Object("HObj", 1, 0);
		RM.addSub(Lyle);
		RM.addSub(Hal);
		RM.addObj(LObj);
		RM.addObj(HObj);
		
		BufferedReader readFile = new BufferedReader(new FileReader(file));		
		String currentLine = readFile.readLine();	
		String delims = "[ ]+"; // split string by whitespaces 1 or more.
		while(currentLine != null ) {
			String[] seperateTokens = currentLine.split(delims);
			if(seperateTokens[0].equals("read") && seperateTokens.length == 3) {
				String subName = seperateTokens[1];
				String objName = seperateTokens[2];
				
				System.out.println("0 - " + seperateTokens[0]+ 
						", 1 - " +subName +", 2 -" + objName);
				
			} else if(seperateTokens[0].equals("write") && seperateTokens.length == 4) {
				String subName = seperateTokens[1];
				String objName = seperateTokens[2];

				int objVal = 0;
				objVal = Integer.parseInt(seperateTokens[3]);
				
				System.out.println("0 - " + seperateTokens[0]+ 
						", 1 - " +subName +", 2 -" + objName + "3 - " + objVal);
				/*try {
					objVal = Integer.parseInt(seperateTokens[3]);
					
				} catch (NumberFormatException e) {
					//badinstruction

				}*/

			} else {
				//badinstruction
				System.out.println("BAD");

			}
			currentLine = readFile.readLine();
		}
		readFile.close();


	}

}
