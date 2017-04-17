public class SplitAssembledStrings {
	public String splitLoopedString(String[] strs) {
		String result = null;
		for (int i = 0; i < strs.length; i++) {
			for (String str : new String[] { strs[i], reverse(strs[i]) }) {
				for (int j = 0; j <= str.length(); j++) {
					StringBuilder regular = new StringBuilder();
					regular.append(str.substring(j));

					if (result != null && !result.startsWith(regular.toString())
							&& regular.toString().compareTo(result) < 0) {
						continue;
					}

					for (int k = (i + 1) % strs.length; k != i; k = (k + 1) % strs.length) {
						String reversed = reverse(strs[k]);
						regular.append(strs[k].compareTo(reversed) > 0 ? strs[k] : reversed);
					}

					regular.append(str.substring(0, j));

					if (result == null || regular.toString().compareTo(result) > 0) {
						result = regular.toString();
					}
				}
			}
		}
		return result;
	}

	static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}
}
