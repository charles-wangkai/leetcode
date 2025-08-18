import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  static final Operation[] OPERATIONS = {
    (x, y) -> new Rational(x.numer() * y.denom() + y.numer() * x.denom(), x.denom() * y.denom()),
    (x, y) -> new Rational(x.numer() * y.denom() - y.numer() * x.denom(), x.denom() * y.denom()),
    (x, y) -> new Rational(x.numer() * y.numer(), x.denom() * y.denom()),
    (x, y) -> (y.numer() == 0) ? null : new Rational(x.numer() * y.denom(), x.denom() * y.numer())
  };

  public boolean judgePoint24(int[] cards) {
    return search(Arrays.stream(cards).mapToObj(num -> new Rational(num, 1)).toList());
  }

  boolean search(List<Rational> values) {
    if (values.size() == 1) {
      return values.get(0).equals(new Rational(24, 1));
    }

    for (int i = 0; i < values.size(); ++i) {
      for (int j = 0; j < values.size(); ++j) {
        if (j != i) {
          for (Operation operation : OPERATIONS) {
            Rational combined = operation.operate(values.get(i), values.get(j));

            int i_ = i;
            int j_ = j;
            if (combined != null
                && search(
                    Stream.concat(
                            Stream.of(combined),
                            IntStream.range(0, values.size())
                                .filter(k -> k != i_ && k != j_)
                                .mapToObj(values::get))
                        .toList())) {
              return true;
            }
          }
        }
      }
    }

    return false;
  }
}

interface Operation {
  Rational operate(Rational x, Rational y);
}

record Rational(int numer, int denom) {
  Rational(int numer, int denom) {
    int g = gcd(numer, denom);

    this.numer = numer / g;
    this.denom = denom / g;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
