
public class Subject {
	private String name;
	private int level;
	private int value;
	
	public Subject(String SubName, int SubLevel, int SubVal) {
		name = SubName;
		level = SubLevel;
		value = SubVal;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int n) {
		value = n;
	}

}
