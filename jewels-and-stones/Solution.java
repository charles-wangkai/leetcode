public class Solution {
	public int numJewelsInStones(String J, String S) {
		return (int) S.chars().filter(stone -> J.indexOf(stone) >= 0).count();
	}
}
