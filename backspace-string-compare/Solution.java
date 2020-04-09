public class Solution {
	public boolean backspaceCompare(String S, String T) {
		int indexS = S.length() - 1;
		int deleteCountS = 0;
		int indexT = T.length() - 1;
		int deleteCountT = 0;
		while (true) {
			while (indexS >= 0 && (S.charAt(indexS) == '#' || deleteCountS != 0)) {
				if (S.charAt(indexS) == '#') {
					++deleteCountS;
				} else {
					--deleteCountS;
				}

				--indexS;
			}

			while (indexT >= 0 && (T.charAt(indexT) == '#' || deleteCountT != 0)) {
				if (T.charAt(indexT) == '#') {
					++deleteCountT;
				} else {
					--deleteCountT;
				}

				--indexT;
			}

			if (indexS == -1 && indexT == -1) {
				return true;
			}
			if (indexS == -1 || indexT == -1 || S.charAt(indexS) != T.charAt(indexT)) {
				return false;
			}

			--indexS;
			--indexT;
		}
	}
}
