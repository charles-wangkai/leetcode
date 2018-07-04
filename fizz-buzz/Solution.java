import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public List<String> fizzBuzz(int n) {
		return IntStream.rangeClosed(1, n).mapToObj(this::output).collect(Collectors.toList());
	}

	String output(int x) {
		return x % 15 == 0 ? "FizzBuzz" : (x % 3 == 0 ? "Fizz" : (x % 5 == 0 ? "Buzz" : String.valueOf(x)));
	}
}
