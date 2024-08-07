import java.util.ArrayList;
import java.util.List;

class Solution {
  static final String[] TRUNK_WORDS = {"", "Thousand", "Million", "Billion"};
  static final String[] TEN_WORDS = {
    null, null, "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
  };
  static final String[] TEEN_WORDS = {
    "Ten",
    "Eleven",
    "Twelve",
    "Thirteen",
    "Fourteen",
    "Fifteen",
    "Sixteen",
    "Seventeen",
    "Eighteen",
    "Nineteen"
  };
  static final String[] DIGIT_WORDS = {
    "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
  };

  public String numberToWords(int num) {
    if (num == 0) {
      return DIGIT_WORDS[0];
    }

    List<String> words = new ArrayList<>();
    int trunkWordIndex = 0;
    while (num != 0) {
      int chunk = num % 1000;
      List<String> translated = translateChunk(chunk);
      if (!translated.isEmpty()) {
        translated.add(TRUNK_WORDS[trunkWordIndex]);
        words.addAll(0, translated);
      }

      num /= 1000;
      ++trunkWordIndex;
    }
    return String.join(" ", words.stream().filter(word -> !word.isEmpty()).toList());
  }

  List<String> translateChunk(int chunk) {
    List<String> result = new ArrayList<>();
    int hundred = chunk / 100;
    if (hundred != 0) {
      result.add(DIGIT_WORDS[hundred]);
      result.add("Hundred");
    }
    int ten = chunk % 100 / 10;
    int one = chunk % 10;
    if (ten == 1) {
      result.add(TEEN_WORDS[one]);
    } else {
      if (ten != 0) {
        result.add(TEN_WORDS[ten]);
      }
      if (one != 0) {
        result.add(DIGIT_WORDS[one]);
      }
    }

    return result;
  }
}
