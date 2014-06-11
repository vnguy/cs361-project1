/*SecureSystem
Sets up Objects - passes them to ReferenceMonitor class to be stored
Sets up Subjects - passes them to ReferenceMonitor class to be stored
Reads in a file line by line - passes line to InstructionObject class, gets InstructionObject back
Pass InstructionObject to ReferenceMonitor
Prints out new state*/
import java.io.*;
public class SecureSystem {
	public static InstructionType Type;
	public static String LYLE = "lyle";
	public static String HAL = "hal";
	public static String HOBJ = "hobj";
	public static String LOBJ = "lobj";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String file = args[0];
		ReferenceManager RM = new ReferenceManager();
		Subject Lyle = new Subject(LYLE, 0,0);
		Subject Hal = new Subject(HAL, 1, 0);
		Object LObj = new Object(LOBJ, 0, 0);
		Object HObj = new Object(LOBJ, 1, 0);
		RM.addSub(Lyle);
		RM.addSub(Hal);
		RM.addObj(LObj);
		RM.addObj(HObj);
		
		BufferedReader readFile = new BufferedReader(new FileReader(file));		
		String currentLine = readFile.readLine();	
		String delims = "[ ]+"; // split string by whitespaces 1 or more.
		while(currentLine != null ) {
			String[] seperateTokens = currentLine.split(delims);
			if(seperateTokens[0].toLowerCase().equals("read") && seperateTokens.length == 3) {
				String subName = seperateTokens[1].toLowerCase();
				String objName = seperateTokens[2].toLowerCase();
				//System.out.println("" + checkSubjectName(subName) + " " + checkObjectName(objName));
				if( !(checkSubjectName(subName)) || !(checkObjectName(objName))) {
					InstructionObject result = new InstructionObject(Type.BAD,subName, objName, 0);
				} else {
				//	System.out.println(" - " + seperateTokens[0]+ 
				//		",  - " +subName +",  -" + objName);
				InstructionObject result = new InstructionObject(Type.READ,subName, objName, 0);
				}
				
			} else if(seperateTokens[0].toLowerCase().equals("write") && seperateTokens.length == 4) {
				
				String subName = seperateTokens[1].toLowerCase();
				String objName = seperateTokens[2].toLowerCase();
				int objVal = Integer.parseInt(seperateTokens[3]);// still need to check if String can parse too a int.
				//System.out.println("" + checkSubjectName(subName) + " " +checkObjectName(objName));
				if(!(checkSubjectName(subName)) || !(checkObjectName(objName))) {
					InstructionObject result = new InstructionObject(Type.BAD,subName, objName, objVal);
					//System.out.println("BAD");				
				} else {					
					InstructionObject result = new InstructionObject(Type.WRITE,subName, objName, objVal);
				
					//System.out.println(" - " + seperateTokens[0]+ 
					//	",  - " +subName +",  -" + objName + "  - " + objVal);
				}
	
			} else {
				//badinstruction
				InstructionObject result = new InstructionObject(Type.BAD,subName, objName, 0);
				//System.out.println("BAD");

			}
			currentLine = readFile.readLine();
		}
		readFile.close();

	}
	
	public static boolean checkObjectName(String objName) {
		//System.out.println("TEMP " + objName);
		if(objName.equals("hobj") || objName.equals("lobj")) {
			return true;
		} 
		return false;
		
	}
	public static boolean checkSubjectName(String subName) {
		//System.out.println("TEMP " + subName);
		if(subName.equals("lyle") || subName.equals("hal")) {
			return true;
		} 
		return false;
	}
}
