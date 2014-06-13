public class InstructionObject {

	public Type instructType;
	public String subjectName;
	public String objectName;
	public int value;

	public InstructionObject(Type type, String subject, String object, int val){
		this.instructType = type;
		subjectName = subject;
		objectName = object;
		if(instructType == Type.WRITE)
			value = val;
		else value = 0;	
	}

	public InstructionObject(Type type){
		instructType = type;
		subjectName = null;
		objectName = null;
		value = 0;
	}
}
