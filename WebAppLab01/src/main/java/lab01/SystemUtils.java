package lab01;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SystemUtils {
	static String jndiString; 
	static public Date toSqlDate(String dateString) {
		java.sql.Date date = null;
		java.util.Date utilDate = null; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			utilDate = sdf.parse(dateString);
			date = new Date(utilDate.getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return date;
	}
	public static String getJndiString() {
		return jndiString;
	}
	public static void setJndiString(String jndiString) {
		SystemUtils.jndiString = jndiString;
	}
	
}
