import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IntegerToRoman {
	public String intToRoman(int num) {
		Map<Integer, String> number2roman = new LinkedHashMap<Integer, String>();
		number2roman.put(1000, "M");
		number2roman.put(900, "CM");
		number2roman.put(500, "D");
		number2roman.put(400, "CD");
		number2roman.put(100, "C");
		number2roman.put(90, "XC");
		number2roman.put(50, "L");
		number2roman.put(40, "XL");
		number2roman.put(10, "X");
		number2roman.put(9, "IX");
		number2roman.put(5, "V");
		number2roman.put(4, "IV");
		number2roman.put(1, "I");

		String result = "";
		while (num != 0) {
			for (Entry<Integer, String> entry : number2roman.entrySet()) {
				int number = entry.getKey();
				String roman = entry.getValue();
				if (number <= num) {
					result += roman;
					num -= number;
					break;
				}
			}
		}
		return result;
	}
}
