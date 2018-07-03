import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> readBinaryWatch(int num) {
		List<String> times = new ArrayList<String>();
		for (int hour = 0; hour <= 11; hour++) {
			int hourOneCount = countOne(hour);
			for (int minute = 0; minute <= 59; minute++) {
				if (hourOneCount + countOne(minute) == num) {
					times.add(String.format("%d:%02d", hour, minute));
				}
			}
		}
		return times;
	}

	int countOne(int n) {
		int result = 0;
		while (n != 0) {
			if (n % 2 != 0) {
				result++;
			}
			n /= 2;
		}
		return result;
	}
}
