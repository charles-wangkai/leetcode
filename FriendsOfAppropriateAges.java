public class FriendsOfAppropriateAges {
	public int numFriendRequests(int[] ages) {
		int[] counts = new int[121];
		for (int age : ages) {
			counts[age]++;
		}

		int requestNum = 0;
		for (int ageA = 1; ageA < counts.length; ageA++) {
			for (int ageB = 1; ageB < counts.length; ageB++) {
				if (isValid(ageA, ageB)) {
					requestNum += counts[ageA] * (counts[ageB] - ((ageA == ageB) ? 1 : 0));
				}
			}
		}
		return requestNum;
	}

	boolean isValid(int ageA, int ageB) {
		return !(2 * ageB <= ageA + 14 || ageB > ageA || (ageB > 100 && ageA < 100));
	}
}
