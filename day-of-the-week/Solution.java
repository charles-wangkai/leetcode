import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Solution {
	public String dayOfTheWeek(int day, int month, int year) {
		return LocalDate.of(year, month, day).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}
}
