
public class ObjectManager {
	
	Object[] objList;
	int index;
	public ObjectManager() {
		objList = new Object[2];
		index = 0;
	}
	public void addObject(String objName, SecurityLevel level) {
		Object obj = new Object(objName, level, 0);
		if(index < objList.length) {
			objList[index] = obj;
			index++;
		}
	}
	public int readObject(String objName) {
		for(int i =0; i < objList.length; i++) {
			String currentObjName = objList[i].getName().toLowerCase();
			System.out.println("objList Name: " + currentObjName + " readName: " +objName.toLowerCase() );
			if(objName.toLowerCase().equals(currentObjName)) {
				return objList[i].getValue();
			}		
		}	
		return 0;	
	}
	
	public void writeObject(String objName,int val) {
		for(int i =0; i < objList.length; i++) {
			String currentObjName = objList[i].getName().toLowerCase();	
			if(objName.toLowerCase().equals(currentObjName)) {
				objList[i].setValue(val);
			}
		}
	}
	
	public void printObjectVals() {
		for(int i = 0; i < objList.length; i++) {
			System.out.println("\t"+ objList[i].getName() + " has value: " + objList[i].getValue());
			//System.out.println("SQA");
		}
		System.out.println();
	}
}
