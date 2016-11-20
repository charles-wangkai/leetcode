import java.util.HashMap;
import java.util.Map;

public class CanIWin {
	Map<Integer, Boolean> code2win = new HashMap<Integer, Boolean>();

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if (desiredTotal == 0) {
			return true;
		}

		if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
			return false;
		}

		return isWin(maxChoosableInteger, desiredTotal, 0, 0);
	}

	boolean isWin(int maxChoosableInteger, int desiredTotal, int code, int sum) {
		if (sum >= desiredTotal) {
			return false;
		}

		if (code2win.containsKey(code)) {
			return code2win.get(code);
		}

		boolean win = false;
		for (int i = 1; i <= maxChoosableInteger; i++) {
			int mask = 1 << i;
			if ((code & mask) == 0) {
				code ^= mask;
				boolean nextWin = isWin(maxChoosableInteger, desiredTotal, code, sum + i);
				code ^= mask;

				if (!nextWin) {
					win = true;
					break;
				}
			}
		}
		code2win.put(code, win);
		return win;
	}
}
