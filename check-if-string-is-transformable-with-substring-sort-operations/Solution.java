import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isTransformable(String s, String t) {
        @SuppressWarnings("unchecked")
        Queue<Integer>[] indexQueues = new Queue[10];
        for (int i = 0; i < indexQueues.length; ++i) {
            indexQueues[i] = new LinkedList<>();
        }

        for (int i = 0; i < s.length(); ++i) {
            indexQueues[s.charAt(i) - '0'].offer(i);
        }

        for (char ch : t.toCharArray()) {
            int digit = ch - '0';

            if (indexQueues[digit].isEmpty()) {
                return false;
            }

            int index = indexQueues[digit].poll();
            for (int i = 0; i < digit; ++i) {
                if (!indexQueues[i].isEmpty() && indexQueues[i].peek() < index) {
                    return false;
                }
            }
        }

        return true;
    }
}