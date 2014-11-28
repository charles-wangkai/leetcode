import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
	public List<String> restoreIpAddresses(String s) {
		List<String> ipAddresses = new ArrayList<String>();
		search(ipAddresses, s, new int[5], 1);
		return ipAddresses;
	}

	void search(List<String> ipAddresses, String s, int[] positions, int index) {
		if (index == positions.length) {
			if (positions[positions.length - 1] == s.length()) {
				ipAddresses.add(convertToIp(s, positions));
			}
			return;
		}
		for (int i = positions[index - 1] + 1; i <= Math.min(s.length(),
				positions[index - 1] + 3); i++) {
			String partStr = s.substring(positions[index - 1], i);
			int part = Integer.parseInt(partStr);
			if (partStr.equals("0")
					|| (!partStr.startsWith("0") && part <= 255)) {
				positions[index] = i;
				search(ipAddresses, s, positions, index + 1);
			}
		}
	}

	String convertToIp(String s, int[] positions) {
		StringBuilder ip = new StringBuilder();
		for (int i = 1; i < positions.length; i++) {
			if (ip.length() != 0) {
				ip.append(".");
			}
			ip.append(s.substring(positions[i - 1], positions[i]));
		}
		return ip.toString();
	}
}
