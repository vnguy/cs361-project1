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
	static Type instructType;
	ReferenceMonitor RM;
	static boolean updateSubCheck;
	InstructionObject result;
	int index;
	

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SecurityLevel low = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;
		String file = args[0];
		
		SecureSystem sys = new SecureSystem();		

		sys.createSubject("lyle", low);	
		sys.createSubject("hal", high);
	
		sys.RM.createNewObject("lobj", low);
		sys.RM.createNewObject("hobj", high);
		
		
		BufferedReader readFile = new BufferedReader(new FileReader(file));		
		String currentLine = readFile.readLine();	
		String delims = "[ ]+"; // split string by whitespaces 1 or more.
		String subName = "";
		String objName = "";

		while(currentLine != null ) {
			updateSubCheck = false;
			String[] seperateTokens = currentLine.split(delims);
			if(seperateTokens[0].toLowerCase().equals("read") && seperateTokens.length == 3) {
				subName = seperateTokens[1].toLowerCase();
				objName = seperateTokens[2].toLowerCase();

				if( !(checkSubjectName(subName)) || !(checkObjectName(objName))) {
					sys.result = new InstructionObject(instructType.BAD);
					System.out.println("Bad Instruction");
				} else {
					sys.result = new InstructionObject(instructType.READ,subName, objName, 0);
					System.out.println(subName + " reads " + objName);
					updateSubCheck = true;
				}
				
			} else if(seperateTokens[0].toLowerCase().equals("write") && seperateTokens.length == 4) {
				
				subName = seperateTokens[1].toLowerCase();
				objName = seperateTokens[2].toLowerCase();
				int objVal = Integer.parseInt(seperateTokens[3]);

				if(!(checkSubjectName(subName)) || !(checkObjectName(objName))) {
					sys.result = new InstructionObject(instructType.BAD);
					System.out.println("Bad Instruction");				
				} else {					
					sys.result = new InstructionObject(instructType.WRITE,subName, objName, objVal);
					System.out.println(subName + " writes " + objVal + " to " + objName);				
				}
	
			} else {
				//badinstruction
				sys.result = new InstructionObject(instructType.BAD);
				System.out.println("Bad Instruction");
			}
			int mostRecentSubVal = sys.RM.readInstruction(sys.result);
			if(updateSubCheck) {
				setReadValforSub(subName, mostRecentSubVal);
			}
			
			System.out.println("The current state is:");
			//sys.RM.ObjManager.printObjectVals();
		//	int subValR = sys.RM.ObjManager.reader(objName);
			//printSubVals();
			sys.displayResults();
			currentLine = readFile.readLine();
		}
		readFile.close();
	}
	
	public SecureSystem() {
		subjectList = new Subject[2];
		RM = new ReferenceMonitor();
		index = 0;
	}

	public static void printSubVals() {
		for(int i = 0; i < subjectList.length; i++){
			System.out.println("\t" + subjectList[i].getName() + " has recently read: "+ subjectList[i].getValue());
		}
	}

	public static void setReadValforSub(String subName, int val) {
		if(subName !=null) {
			for(int i = 0 ;i < subjectList.length; i++) {
				if(subjectList[i].getName().equals(subName)) {
					subjectList[i].setValue(val);
				}
			}
		}
	}
	
	public void createSubject(String subName, SecurityLevel level) {	
		if(index < subjectList.length) {
			subjectList[index] = new Subject(subName, level, 0);
			RM.subLabelMap.put(subName, level);		
			index++;
		}
	}

	
	public static boolean checkObjectName(String objName) {
		if(objName.equals("hobj") || objName.equals("lobj")) {
			return true;
		} 
		return false;	
	}

	public static boolean checkSubjectName(String subName) {
		if(subName.equals("lyle") || subName.equals("hal")) {
			return true;
		} 
		return false;
	}
	
	public void displayResults() {
		RM.ObjManager.printObjectVals();
		printSubVals();
		System.out.println();
	}
}
