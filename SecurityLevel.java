
public class SecurityLevel {
	static int LOW = 0;
	static int HIGH = 1;
	public static boolean dominate(int objLevel, int subLevel) {
		if(subLevel == HIGH || subLevel == LOW && objLevel == LOW) {
			return true;
		} else {
			return false;
		}
	}

}
