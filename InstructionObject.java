public class InstructionObject {

	public InstructionType type;
	public String subjectName;
	public String objectName;
	public int value;

	public InstructionObject(InstructionType type, String subject, String object, int val){
		this.type = type;
		subjectName = subject;
		objectName = object;
		if(this.type == InstructionType.WRITE)
			value = val;
		else value = 0;	
	}

	public InstructionObject(InstructionType type){
		this.type = type;
		subjectName = null;
		objectName = null;
		value = 0;
	}
}