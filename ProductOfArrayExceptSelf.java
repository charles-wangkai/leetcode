public class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		int[] output = new int[nums.length];

		int rightProduct = 1;
		for (int i = output.length - 1; i >= 0; i--) {
			output[i] = rightProduct;
			rightProduct *= nums[i];
		}

		int leftProduct = 1;
		for (int i = 0; i < output.length; i++) {
			output[i] *= leftProduct;
			leftProduct *= nums[i];
		}

		return output;
	}
}
