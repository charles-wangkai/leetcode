import java.util.Arrays;

public class Heaters {
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(houses);
		Arrays.sort(heaters);

		int radius = 0;
		int leftHeaterIndex = -1;
		int leftHeater = Integer.MIN_VALUE;
		int rightHeater = heaters[0];
		for (int house : houses) {
			while (house > rightHeater) {
				leftHeaterIndex++;

				leftHeater = heaters[leftHeaterIndex];
				rightHeater = (leftHeaterIndex + 1 == heaters.length) ? Integer.MAX_VALUE
						: heaters[leftHeaterIndex + 1];
			}

			radius = (int) Math.max(radius, Math.min(house - (long) leftHeater, (long) rightHeater - house));
		}
		return radius;
	}
}
