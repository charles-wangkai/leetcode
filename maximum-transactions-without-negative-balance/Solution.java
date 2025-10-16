import java.util.PriorityQueue;

class Solution {
  public int maxTransactions(int[] transactions) {
    int result = 0;
    long balance = 0;
    int count = 0;
    PriorityQueue<Integer> negatives = new PriorityQueue<>();
    for (int transaction : transactions) {
      if (transaction < 0) {
        negatives.add(transaction);
      }

      balance += transaction;
      ++count;

      while (balance < 0) {
        balance -= negatives.poll();
        --count;
      }

      result = Math.max(result, count);
    }

    return result;
  }
}