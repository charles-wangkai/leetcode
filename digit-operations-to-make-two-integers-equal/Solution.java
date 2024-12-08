import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(int n, int m) {
    Set<Integer> primes =
        IntStream.iterate(
                2, x -> String.valueOf(x).length() <= String.valueOf(n).length(), x -> x + 1)
            .filter(this::isPrime)
            .boxed()
            .collect(Collectors.toSet());
    if (primes.contains(n)) {
      return -1;
    }

    Set<Integer> visited = new HashSet<>();
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(n, n));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (!visited.contains(head.value())) {
        if (head.value() == m) {
          return head.distance();
        }

        visited.add(head.value());

        for (int placeValue = 1; placeValue <= head.value(); placeValue *= 10) {
          int digit = head.value() / placeValue % 10;
          if (digit != 9) {
            int nextValue = head.value() + placeValue;
            if (!primes.contains(nextValue) && !visited.contains(nextValue)) {
              pq.offer(new Element(nextValue, head.distance() + nextValue));
            }
          }
          if (digit != 0) {
            int nextValue = head.value() - placeValue;
            if (!primes.contains(nextValue) && !visited.contains(nextValue)) {
              pq.offer(new Element(nextValue, head.distance() + nextValue));
            }
          }
        }
      }
    }

    return -1;
  }

  boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}

record Element(int value, int distance) {}
