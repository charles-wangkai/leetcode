import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
	public String reverseWords(String s) {
		return String.join(" ", Arrays.stream(s.split(" ")).map(word -> new StringBuilder(word).reverse().toString())
				.collect(Collectors.toList()));
	}
}
