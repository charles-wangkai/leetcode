import java.util.regex.Pattern;

public class Solution {
	static final String IPV4_PART = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
	static final String IPV4_REGEX = String.format("%s(\\.%s){3}", IPV4_PART, IPV4_PART);

	static final String IPV6_PART = "([0-9a-fA-F]{1,4})";
	static final String IPV6_REGEX = String.format("%s(:%s){7}", IPV6_PART, IPV6_PART);

	public String validIPAddress(String IP) {
		if (Pattern.matches(IPV4_REGEX, IP)) {
			return "IPv4";
		} else if (Pattern.matches(IPV6_REGEX, IP)) {
			return "IPv6";
		} else {
			return "Neither";
		}
	}
}
