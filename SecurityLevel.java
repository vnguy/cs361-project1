
	public enum SecurityLevel {

		LOW, HIGH;

		public static boolean dominates(SecurityLevel subjLevel, SecurityLevel objLevel, Type type){
			if(type == Type.READ){
				if(subjLevel == HIGH || objLevel == LOW) 
					return true;
			}else if(type == Type.WRITE){
				if(subjLevel == LOW || objLevel == HIGH)
					return true;
			}
			return false;
		}
}
