import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int getWinner(int[] arr, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int x : arr) {
            deque.offerLast(x);
        }

        int winCount = 0;
        while (true) {
            int first = deque.pollFirst();
            int second = deque.pollFirst();

            if (first > second) {
                deque.offerFirst(first);
                deque.offerLast(second);

                ++winCount;
            } else {
                deque.offerFirst(second);
                deque.offerLast(first);

                winCount = 1;
            }

            if (winCount == k || winCount == arr.length - 1) {
                return deque.peekFirst();
            }
        }
    }
}