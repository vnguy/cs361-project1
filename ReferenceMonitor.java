import java.util.*;

public class ReferenceMonitor {
	HashMap<String, SecurityLevel> objLabelMap;
	HashMap<String, SecurityLevel> subLabelMap;
	ObjectManager ObjManager;

	public ReferenceMonitor () {
		objLabelMap = new HashMap<String, SecurityLevel>();
		subLabelMap = new HashMap<String, SecurityLevel>();
		ObjManager = new ObjectManager();
	}
	
	public int readInstruction(InstructionObject instruct) {

		String objName = instruct.objectName;
		String subName = instruct.subjectName;
		
		SecurityLevel objLevel = objLabelMap.get(objName);
		SecurityLevel subLevel = subLabelMap.get(subName);
		if(instruct.instructType.equals(Type.BAD)) {
			//change this soon
		} else if(instruct.instructType.equals(Type.READ) && SimpleSecurity(subLevel,objLevel)) {
			return ObjManager.readObject(objName);
		} else if(instruct.instructType.equals(Type.WRITE) && starProperty(subLevel,objLevel)) {
			ObjManager.writeObject(objName, instruct.value);
		}
		return 0;
	
	}

	public boolean SimpleSecurity (SecurityLevel subLevel, SecurityLevel objLevel) {
		//boolean temp = SecurityLevel.dominates(subLevel,objLevel,InstructionType.READ);
		if(SecurityLevel.dominates(subLevel,objLevel,Type.READ)) {
				return true;
		}
		return false;
	}
	
	public boolean starProperty (SecurityLevel subLevel, SecurityLevel objLevel) {
		if(SecurityLevel.dominates(subLevel,objLevel,Type.WRITE)) {
			return true;
		}
		return false;
	}

	public void createNewObject(String name, SecurityLevel level) {
		ObjManager.addObject(name,level);
		objLabelMap.put(name,level);
	}
}
