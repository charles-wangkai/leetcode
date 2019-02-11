import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	public List<Integer> addToArrayForm(int[] A, int K) {
		return new BigInteger(String.join("", Arrays.stream(A).mapToObj(String::valueOf).toArray(String[]::new)))
				.add(BigInteger.valueOf(K)).toString().chars().mapToObj(ch -> ch - '0').collect(Collectors.toList());
	}
}
