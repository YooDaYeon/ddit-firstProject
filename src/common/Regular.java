package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular {
	
	public boolean regId(String id) {
		Pattern p = Pattern
				.compile("[a-zA-Z]{4,}");

		Matcher m = p.matcher(id);

		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean regBirth(String bd) {
		Pattern p = Pattern.compile("\\d{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])");

		Matcher m = p.matcher(bd);

		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean regEmail(String email){
		
		Pattern p = Pattern.compile("(^[A-Za-z])([-_.A-Za-z0-9]*)@([A-Za-z\\d]{1,7})(.)([A-Za-z]{2,3}([.]kr?)");
		Matcher m = p.matcher(email);

		if (m.find()) {
			return true;
		} else {
			return false;
		}
	
	}
	


}
