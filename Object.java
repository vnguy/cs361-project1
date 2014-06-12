//Object - has private fields NAME, LABEL, and VALUE, with methods to get and set VALUE
public class Object {
	private String name;
	private SecurityLevel level;
	private int value;
	
	public Object(String ObjName, SecurityLevel ObjLevel, int ObjVal) {
		name = ObjName;
		level = ObjLevel;
		value = ObjVal;
	}
	public SecurityLevel getLevel () {
		return level;
	}
	public String getName() {
		return name;
	}
	public int getValue () {
		return value;
	}
	public void setValue(int n) {
		value = n;
	}
}

