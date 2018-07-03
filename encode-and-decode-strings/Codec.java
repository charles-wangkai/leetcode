import java.util.ArrayList;
import java.util.List;

public class Codec {
	private static final char FLAG_DATA = 'D';
	private static final char FLAG_SEPARATOR = 'S';

	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			for (int i = 0; i < str.length(); i++) {
				sb.append(FLAG_DATA);
				sb.append(str.charAt(i));
			}
			sb.append(FLAG_SEPARATOR);
			sb.append(FLAG_SEPARATOR);
		}
		return sb.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> strs = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i += 2) {
			if (s.charAt(i) == FLAG_DATA) {
				sb.append(s.charAt(i + 1));
			} else {
				strs.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		return strs;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));