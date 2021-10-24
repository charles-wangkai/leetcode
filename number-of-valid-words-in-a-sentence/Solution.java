import java.util.Arrays;

class Solution {
  public int countValidWords(String sentence) {
    return (int)
        Arrays.stream(sentence.split(" "))
            .filter(
                s -> {
                  if (s.isEmpty()
                      || s.chars().anyMatch(Character::isDigit)
                      || s.replaceAll("[^-]", "").length() >= 2
                      || s.replaceAll("[^!.,]", "").length() >= 2) {
                    return false;
                  }

                  int hyphenIndex = s.indexOf('-');
                  if (hyphenIndex != -1
                      && !(hyphenIndex != 0
                          && Character.isLetter(s.charAt(hyphenIndex - 1))
                          && hyphenIndex != s.length() - 1
                          && Character.isLetter(s.charAt(hyphenIndex + 1)))) {
                    return false;
                  }

                  s = s.replaceAll("[-]", "");

                  return s.substring(0, s.length() - 1).chars().allMatch(Character::isLetter);
                })
            .count();
  }
}
