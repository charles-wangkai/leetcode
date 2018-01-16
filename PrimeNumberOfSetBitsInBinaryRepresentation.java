import java.util.stream.IntStream;

public class PrimeNumberOfSetBitsInBinaryRepresentation {
	public int countPrimeSetBits(int L, int R) {
		return (int) IntStream.rangeClosed(L, R).filter(this::isValid).count();
	}

	boolean isValid(int n) {
		return isPrime((int) Integer.toBinaryString(n).chars().filter(digit -> digit == '1').count());
	}

	boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
