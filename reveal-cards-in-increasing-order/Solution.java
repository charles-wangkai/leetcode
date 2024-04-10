import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  public int[] deckRevealedIncreasing(int[] deck) {
    Arrays.sort(deck);

    int[] result = new int[deck.length];
    Deque<Integer> indices = new ArrayDeque<>();
    for (int i = 0; i < result.length; ++i) {
      indices.offerLast(i);
    }

    for (int i = 0; i < deck.length; ++i) {
      int index = indices.pollFirst();
      result[index] = deck[i];

      if (i != deck.length - 1) {
        indices.offerLast(indices.pollFirst());
      }
    }

    return result;
  }
}
