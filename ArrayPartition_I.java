import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayPartition_I {
	public int arrayPairSum(int[] nums) {
		Arrays.sort(nums);
		return IntStream.range(0, nums.length).filter(i -> i % 2 == 0).map(i -> nums[i]).sum();
	}
}
