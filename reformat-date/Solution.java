import java.util.Arrays;

class Solution {
    static final String[] MONTH_NAMES = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
            "Dec" };

    public String reformatDate(String date) {
        String[] parts = date.split(" ");

        int year = Integer.parseInt(parts[2]);
        int month = Arrays.asList(MONTH_NAMES).indexOf(parts[1]) + 1;
        int day = Integer.parseInt(parts[0].substring(0, parts[0].length() - 2));

        return String.format("%d-%02d-%02d", year, month, day);
    }
}