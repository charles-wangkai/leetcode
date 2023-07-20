import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
  static final Map<Character, Integer> ch2row;

  static {
    ch2row = new HashMap<>();

    ch2row.put('q', 1);
    ch2row.put('w', 1);
    ch2row.put('e', 1);
    ch2row.put('r', 1);
    ch2row.put('t', 1);
    ch2row.put('y', 1);
    ch2row.put('u', 1);
    ch2row.put('i', 1);
    ch2row.put('o', 1);
    ch2row.put('p', 1);

    ch2row.put('a', 2);
    ch2row.put('s', 2);
    ch2row.put('d', 2);
    ch2row.put('f', 2);
    ch2row.put('g', 2);
    ch2row.put('h', 2);
    ch2row.put('j', 2);
    ch2row.put('k', 2);
    ch2row.put('l', 2);

    ch2row.put('z', 3);
    ch2row.put('x', 3);
    ch2row.put('c', 3);
    ch2row.put('v', 3);
    ch2row.put('b', 3);
    ch2row.put('n', 3);
    ch2row.put('m', 3);
  }

  public String[] findWords(String[] words) {
    return Arrays.stream(words)
        .filter(
            word ->
                word.toLowerCase()
                        .chars()
                        .map(ch -> ch2row.get((char) ch))
                        .collect(HashSet<Integer>::new, Set::add, Set::addAll)
                        .size()
                    == 1)
        .toArray(String[]::new);
  }
}
