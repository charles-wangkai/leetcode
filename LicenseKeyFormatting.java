public class LicenseKeyFormatting {
	public String licenseKeyFormatting(String S, int K) {
		String s = S.replace("-", "").toUpperCase();

		StringBuilder sb = new StringBuilder();

		sb.append(s.substring(0, s.length() % K));
		for (int beginIndex = s.length() % K; beginIndex < s.length(); beginIndex += K) {
			if (sb.length() != 0) {
				sb.append('-');
			}
			sb.append(s.substring(beginIndex, beginIndex + K));
		}

		return sb.toString();
	}
}
