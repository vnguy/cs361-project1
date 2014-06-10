
public class Object {
	private String name;
	private int label;
	private int value;
	
	public Object(String ObjName, int ObjLabel, int ObjVal) {
		name = ObjName;
		label = ObjLabel;
		value = ObjVal;
	}
	public int getLabel () {
		return label;
	}
	public int getValue () {
		return value;
	}
	public void setValue(int n) {
		value = n;
	}
}