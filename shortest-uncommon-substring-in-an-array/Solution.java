import java.util.Arrays;

class Solution {
  public String[] shortestSubstrings(String[] arr) {
    return Arrays.stream(arr)
        .map(
            s -> {
              String result = "";
              for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
                for (int endIndex = beginIndex; endIndex < s.length(); ++endIndex) {
                  String substr = s.substring(beginIndex, endIndex + 1);
                  if (Arrays.stream(arr).filter(str -> str.contains(substr)).count() == 1
                      && (result.isEmpty()
                          || substr.length() < result.length()
                          || (substr.length() == result.length()
                              && substr.compareTo(result) < 0))) {
                    result = substr;
                  }
                }
              }

              return result;
            })
        .toArray(String[]::new);
  }
}