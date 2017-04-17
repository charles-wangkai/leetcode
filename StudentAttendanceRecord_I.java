public class StudentAttendanceRecord_I {
	public boolean checkRecord(String s) {
		return !(s.chars().filter(c -> c == 'A').count() > 1 || s.contains("LLL"));
	}
}
