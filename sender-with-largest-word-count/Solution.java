import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public String largestWordCount(String[] messages, String[] senders) {
    Map<String, Integer> senderToWordCount = new HashMap<>();
    for (int i = 0; i < messages.length; ++i) {
      senderToWordCount.put(
          senders[i],
          senderToWordCount.getOrDefault(senders[i], 0) + messages[i].split(" ").length);
    }

    return senderToWordCount.keySet().stream()
        .max(
            Comparator.<String, Integer>comparing(senderToWordCount::get)
                .thenComparing(sender -> sender))
        .get();
  }
}
