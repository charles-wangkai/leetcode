import java.util.stream.IntStream;

class Solution {
  public int[] kthSmallestPrimeFraction(int[] arr, int k) {
    Fraction fraction =
        IntStream.range(0, arr.length)
            .boxed()
            .flatMap(
                i ->
                    IntStream.range(i + 1, arr.length)
                        .boxed()
                        .map(j -> new Fraction(arr[i], arr[j])))
            .sorted(
                (f1, f2) ->
                    Integer.compare(
                        f1.numerator() * f2.denominator(), f2.numerator() * f1.denominator()))
            .skip(k - 1)
            .findFirst()
            .get();

    return new int[] {fraction.numerator(), fraction.denominator()};
  }
}

record Fraction(int numerator, int denominator) {}
