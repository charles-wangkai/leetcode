public class Solution {
	public boolean isLongPressedName(String name, String typed) {
		int typedIndex = 0;
		for (int i = 0; i < name.length(); i++) {
			if (i > 0 && name.charAt(i) != name.charAt(i - 1)) {
				while (typedIndex < typed.length() && typedIndex > 0
						&& typed.charAt(typedIndex) == typed.charAt(typedIndex - 1)) {
					typedIndex++;
				}
			}

			if (typedIndex == typed.length() || typed.charAt(typedIndex) != name.charAt(i)) {
				return false;
			}
			typedIndex++;
		}
		return true;
	}
}
