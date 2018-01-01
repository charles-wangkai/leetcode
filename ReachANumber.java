public class ReachANumber {
	public int reachNumber(int target) {
		target = Math.abs(target);
		int step = (int) Math.floor(Math.sqrt(target * 2));
		while (step * (step + 1) / 2 < target || step * (step + 1) / 2 % 2 != target % 2) {
			step++;
		}
		return step;
	}
}
