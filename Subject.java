
public class Subject {
	private String name;
	private SecurityLevel level;
	private int value;
	
	public Subject(String SubName, SecurityLevel SubLevel, int SubVal) {
		name = SubName;
		level = SubLevel;
		value = SubVal;
	}
	
	public SecurityLevel getLevel() {
		return level;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int n) {
		value = n;
	}
	
	public String getName() {
		return name;
	}
}
