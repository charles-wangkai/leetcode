public class ReachingPoints {
	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		while (true) {
			if (tx == sx && ty == sy) {
				return true;
			}

			if (tx < sx || ty < sy) {
				return false;
			}

			if (tx <= ty) {
				if (tx == sx) {
					return (ty - sy) % tx == 0;
				} else {
					ty %= tx;
				}
			} else {
				if (ty == sy) {
					return (tx - sx) % ty == 0;
				} else {
					tx %= ty;
				}
			}
		}
	}
}
