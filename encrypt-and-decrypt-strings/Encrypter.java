import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Encrypter {
  private char[] keys;
  private String[] values;
  private Map<String, Integer> encryptedToCount = new HashMap<>();

  public Encrypter(char[] keys, String[] values, String[] dictionary) {
    this.keys = keys;
    this.values = values;

    for (String word : dictionary) {
      String encrypted = encrypt(word);
      encryptedToCount.put(encrypted, encryptedToCount.getOrDefault(encrypted, 0) + 1);
    }
  }

  public String encrypt(String word1) {
    return word1
        .chars()
        .mapToObj(
            c ->
                values[
                    IntStream.range(0, keys.length).filter(i -> c == keys[i]).findAny().getAsInt()])
        .collect(Collectors.joining());
  }

  public int decrypt(String word2) {
    return encryptedToCount.getOrDefault(word2, 0);
  }
}

// Your Encrypter object will be instantiated and called as such:
// Encrypter obj = new Encrypter(keys, values, dictionary);
// String param_1 = obj.encrypt(word1);
// int param_2 = obj.decrypt(word2);
