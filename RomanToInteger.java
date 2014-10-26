import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class RomanToInteger {
	public int romanToInt(String s) {
		LinkedHashMap<String, Integer> roman2number = new LinkedHashMap<String, Integer>();
		roman2number.put("M", 1000);
		roman2number.put("CM", 900);
		roman2number.put("D", 500);
		roman2number.put("CD", 400);
		roman2number.put("C", 100);
		roman2number.put("XC", 90);
		roman2number.put("L", 50);
		roman2number.put("XL", 40);
		roman2number.put("X", 10);
		roman2number.put("IX", 9);
		roman2number.put("V", 5);
		roman2number.put("IV", 4);
		roman2number.put("I", 1);

		int result = 0;
		for (int index = 0; index < s.length();) {
			for (Entry<String, Integer> entry : roman2number.entrySet()) {
				String roman = entry.getKey();
				int number = entry.getValue();
				if (s.startsWith(roman, index)) {
					result += number;
					index += roman.length();
					break;
				}
			}
		}
		return result;
	}
}
