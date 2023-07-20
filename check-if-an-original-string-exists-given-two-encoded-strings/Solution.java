import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
  Map<State, Boolean> cache = new HashMap<>();

  public boolean possiblyEquals(String s1, String s2) {
    return check(s1, 0, 0, s2, 0, 0);
  }

  boolean check(String s1, int index1, int prefix1, String s2, int index2, int prefix2) {
    if (prefix1 > prefix2) {
      return check(s2, index2, prefix2, s1, index1, prefix1);
    }
    if (prefix1 != 0) {
      int commonPrefix = Math.min(prefix1, prefix2);

      return check(s1, index1, prefix1 - commonPrefix, s2, index2, prefix2 - commonPrefix);
    }
    if (index1 == s1.length()) {
      return prefix2 == 0 && index2 == s2.length();
    }

    State state = new State(s1, index1, prefix1, s2, index2, prefix2);
    if (!cache.containsKey(state)) {
      boolean result;
      if (Character.isLetter(s1.charAt(index1))) {
        if (prefix2 != 0) {
          result = check(s1, index1 + 1, 0, s2, index2, prefix2 - 1);
        } else if (index2 == s2.length()) {
          result = false;
        } else if (Character.isLetter(s2.charAt(index2))) {
          result =
              (s1.charAt(index1) == s2.charAt(index2))
                  && check(s1, index1 + 1, 0, s2, index2 + 1, 0);
        } else {
          int nextIndex2 = index2;
          while (nextIndex2 != s2.length() && Character.isDigit(s2.charAt(nextIndex2))) {
            ++nextIndex2;
          }
          int[] lengths2 = buildLengths(s2, index2, nextIndex2);

          result = false;
          for (int length2 : lengths2) {
            if (check(s1, index1 + 1, 0, s2, nextIndex2, length2 - 1)) {
              result = true;

              break;
            }
          }
        }
      } else {
        int nextIndex1 = index1;
        while (nextIndex1 != s1.length() && Character.isDigit(s1.charAt(nextIndex1))) {
          ++nextIndex1;
        }
        int[] lengths1 = buildLengths(s1, index1, nextIndex1);

        if (prefix2 != 0) {
          result = false;
          for (int length1 : lengths1) {
            if (check(s1, nextIndex1, length1, s2, index2, prefix2)) {
              result = true;

              break;
            }
          }
        } else if (index2 == s2.length()) {
          result = false;
        } else if (Character.isLetter(s2.charAt(index2))) {
          result = false;
          for (int length1 : lengths1) {
            if (check(s1, nextIndex1, length1 - 1, s2, index2 + 1, 0)) {
              result = true;

              break;
            }
          }
        } else {
          int nextIndex2 = index2;
          while (nextIndex2 != s2.length() && Character.isDigit(s2.charAt(nextIndex2))) {
            ++nextIndex2;
          }
          int[] lengths2 = buildLengths(s2, index2, nextIndex2);

          result = false;
          for (int length1 : lengths1) {
            for (int length2 : lengths2) {
              if (check(s1, nextIndex1, length1, s2, nextIndex2, length2)) {
                result = true;

                break;
              }
            }
            if (result) {
              break;
            }
          }
        }
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }

  int[] buildLengths(String s, int beginIndex, int endIndex) {
    if (endIndex - beginIndex == 1) {
      return new int[] {s.charAt(beginIndex) - '0'};
    } else if (endIndex - beginIndex == 2) {
      return new int[] {
        (s.charAt(beginIndex) - '0') + (s.charAt(beginIndex + 1) - '0'),
        (s.charAt(beginIndex) - '0') * 10 + (s.charAt(beginIndex + 1) - '0')
      };
    }

    return new int[] {
      (s.charAt(beginIndex) - '0')
          + (s.charAt(beginIndex + 1) - '0')
          + (s.charAt(beginIndex + 2) - '0'),
      (s.charAt(beginIndex) - '0') * 10
          + (s.charAt(beginIndex + 1) - '0')
          + (s.charAt(beginIndex + 2) - '0'),
      (s.charAt(beginIndex) - '0')
          + (s.charAt(beginIndex + 1) - '0') * 10
          + (s.charAt(beginIndex + 2) - '0'),
      (s.charAt(beginIndex) - '0') * 100
          + (s.charAt(beginIndex + 1) - '0') * 10
          + (s.charAt(beginIndex + 2) - '0')
    };
  }
}

class State {
  String s1;
  int index1;
  int prefix1;
  String s2;
  int index2;
  int prefix2;

  State(String s1, int index1, int prefix1, String s2, int index2, int prefix2) {
    this.s1 = s1;
    this.index1 = index1;
    this.prefix1 = prefix1;
    this.s2 = s2;
    this.index2 = index2;
    this.prefix2 = prefix2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(s1, index1, prefix1, s2, index2, prefix2);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return s1.equals(other.s1)
        && index1 == other.index1
        && prefix1 == other.prefix1
        && s2.equals(other.s2)
        && index2 == other.index2
        && prefix2 == other.prefix2;
  }
}
