public class Solution {
	public String strWithout3a3b(int A, int B) {
		return (A >= B) ? findString('a', A, 'b', B) : findString('b', B, 'a', A);
	}

	String findString(char letter1, int count1, char letter2, int count2) {
		int doubleCount1 = count1 - count2 - 1;
		StringBuilder result = new StringBuilder();
		int group1 = 0;
		while (true) {
			if (count1 == 0) {
				break;
			}

			result.append(letter1);
			count1--;

			if (group1 < doubleCount1) {
				result.append(letter1);
				count1--;
			}
			group1++;

			if (count2 == 0) {
				break;
			}

			result.append(letter2);
			count2--;
		}
		return result.toString();
	}
}
