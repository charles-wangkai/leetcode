import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Solution {
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs((int) ChronoUnit.DAYS.between(buildLocalDate(date1), buildLocalDate(date2)));
    }

    LocalDate buildLocalDate(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}