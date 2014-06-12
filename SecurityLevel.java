
	public enum SecurityLevel {

		LOW, HIGH;

		// this method returns true if the subject dominates the object 
		// or if they have the same security level, false otherwise
/*		public static boolean dominates(SecurityLevel subjectLevel, SecurityLevel objectLevel, InstructionType type){
			if(type == InstructionType.READ){
				if(subjectLevel == HIGH)
					return true;
				else if(objectLevel == LOW)
					return true;
				return false;
			}else if(type == InstructionType.WRITE){
				if(subjectLevel == LOW)
					return true;
				else if(objectLevel == HIGH)
					return true;
				return false;
			}return false;
		}*/
		public static boolean dominates(SecurityLevel subjectLevel, SecurityLevel objectLevel, InstructionType type){
			if((type.equals(InstructionType.READ)) && ((subjectLevel == HIGH) || (objectLevel == LOW)))
					return true;
			else if((type.equals(InstructionType.WRITE)) && ((subjectLevel == LOW) || (objectLevel == HIGH))){
					return true;
			}return false;
		}
}