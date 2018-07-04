import java.util.Stack;

public class Solution {
	static final String CDATA_ENDING = "]]>";

	public boolean isValid(String code) {
		if (code.length() == 0) {
			return false;
		}

		if (code.charAt(0) != '<') {
			return false;
		}

		int endIndex = code.indexOf('>');
		if (endIndex < 0) {
			return false;
		}

		String tagName = code.substring(1, endIndex);
		if (!isValidTagName(tagName)) {
			return false;
		}

		String tail = String.format("</%s>", tagName);
		if (!code.endsWith(tail)) {
			return false;
		}

		return isValidTagContent(code.substring(endIndex + 1, code.length() - tail.length()));
	}

	boolean isValidTagName(String tagName) {
		return tagName.chars().allMatch(Character::isUpperCase) && tagName.length() >= 1 && tagName.length() <= 9;
	}

	boolean isValidTagContent(String tagContent) {
		Stack<String> tags = new Stack<String>();

		int index = 0;
		while (index < tagContent.length()) {
			if (tagContent.startsWith("<![CDATA[", index)) {
				int endIndex = tagContent.indexOf(CDATA_ENDING, index);
				if (endIndex < 0) {
					return false;
				}

				index = endIndex + CDATA_ENDING.length();
			} else if (tagContent.charAt(index) == '<') {
				int endIndex = tagContent.indexOf('>', index);
				if (endIndex < 0) {
					return false;
				}

				boolean openOrCloseTag = (tagContent.charAt(index + 1) != '/');
				String tagName = tagContent.substring(openOrCloseTag ? (index + 1) : (index + 2), endIndex);
				if (openOrCloseTag) {
					if (!isValidTagName(tagName)) {
						return false;
					}
					tags.push(tagName);
				} else {
					if (tags.empty() || !tags.peek().equals(tagName)) {
						return false;
					}
					tags.pop();
				}

				index = endIndex + 1;
			} else {
				index++;
			}
		}

		return tags.empty();
	}
}
