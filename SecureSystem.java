/*SecureSystem
Sets up Objects - passes them to ReferenceMonitor class to be stored
Sets up Subjects - passes them to ReferenceMonitor class to be stored
Reads in a file line by line - passes line to InstructionObject class, gets InstructionObject back
Pass InstructionObject to ReferenceMonitor
Prints out new state*/
import java.io.*;
public class SecureSystem {
	//InstructionType Type;

	static Subject[] subjectList;
	static InstructionType Type;
	ReferenceManager RM;
	InstructionObject result;
	

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SecurityLevel low = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;
		String file = args[0];
		
		SecureSystem sys = new SecureSystem();
		
		sys.createSubject("lyle", low, 0);
		sys.createSubject("hal", high, 1);
		
		sys.RM.createNewObject("lobj", low);
		sys.RM.createNewObject("hobj", high);
		
		BufferedReader readFile = new BufferedReader(new FileReader(file));		
		String currentLine = readFile.readLine();	
		String delims = "[ ]+"; // split string by whitespaces 1 or more.
		String subName = "";
		String objName = "";
		while(currentLine != null ) {
			String[] seperateTokens = currentLine.split(delims);
			if(seperateTokens[0].toLowerCase().equals("read") && seperateTokens.length == 3) {
				subName = seperateTokens[1].toLowerCase();
				objName = seperateTokens[2].toLowerCase();

				//System.out.println("" + checkSubjectName(subName) + " " + checkObjectName(objName));
				if( !(checkSubjectName(subName)) || !(checkObjectName(objName))) {
					sys.result = new InstructionObject(Type.BAD);
				} else {
				//	System.out.println(" - " + seperateTokens[0]+ 
				//		",  - " +subName +",  -" + objName);
					sys.result = new InstructionObject(Type.READ,subName, objName, 0);
				}
				
			} else if(seperateTokens[0].toLowerCase().equals("write") && seperateTokens.length == 4) {
				
				subName = seperateTokens[1].toLowerCase();
				objName = seperateTokens[2].toLowerCase();
				int objVal = Integer.parseInt(seperateTokens[3]);// still need to check if String can parse too a int.
				//System.out.println("" + checkSubjectName(subName) + " " +checkObjectName(objName));
				if(!(checkSubjectName(subName)) || !(checkObjectName(objName))) {
					sys.result = new InstructionObject(Type.BAD);
					//System.out.println("BAD");				
				} else {					
					sys.result = new InstructionObject(Type.WRITE,subName, objName, objVal);
				
					//System.out.println(" - " + seperateTokens[0]+ 
					//	",  - " +subName +",  -" + objName + "  - " + objVal);
				}
	
			} else {
				//badinstruction
				sys.result = new InstructionObject(Type.BAD);
			
				//System.out.println("BAD");

			}
			int val = sys.RM.readInstruction(sys.result);
			setReadValforSub(subName, val);
			sys.RM.ObjManager.printObjectVals();
			
			currentLine = readFile.readLine();
		}
		readFile.close();

	}
	
	public SecureSystem() {
		subjectList = new Subject[2];
		RM = new ReferenceManager();
	}
	public void printSubVals() {
		for(int i = 0; i < subjectList.length; i++){
			System.out.println("\t" + subjectList[i].getName() + " has recently read: "+ subjectList[i].getValue());
		}
	}
	public static void setReadValforSub(String subName, int val) {
		Subject current;
		if(subName !=null) {
			for(int i = 0 ;i < subjectList.length; i++) {
				current = subjectList[i];
				if(current.getName() == subName) {
					current.setValue(val);
				}
			}
		}
	}
	
	public void createSubject(String subName, SecurityLevel level,int i) {
		subjectList[i] = new Subject(subName, level, 0);
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
	
	public void displayResults() {
		RM.ObjManager.printObjectVals();
	}
}
