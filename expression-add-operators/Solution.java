import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> addOperators(String num, int target) {
    return buildNumbersList(num).stream()
        .flatMap(numbers -> search(numbers, 0, target, true).stream())
        .toList();
  }

  List<List<Long>> buildNumbersList(String num) {
    List<List<Long>> numbersList = new ArrayList<>();
    split(numbersList, num, 0, new ArrayList<>());

    return numbersList;
  }

  void split(List<List<Long>> numbersList, String num, int startIndex, List<Long> numbers) {
    if (startIndex == num.length()) {
      numbersList.add(List.copyOf(numbers));

      return;
    }

    long number = 0;
    for (int i = startIndex; i < num.length(); ++i) {
      number = number * 10 + (num.charAt(i) - '0');

      numbers.add(number);
      split(numbersList, num, i + 1, numbers);
      numbers.remove(numbers.size() - 1);

      if (number == 0) {
        break;
      }
    }
  }

  List<String> search(List<Long> numbers, int startIndex, long target, boolean first) {
    List<String> result = new ArrayList<>();

    StringBuilder term = new StringBuilder();
    long product = 1;
    for (int i = startIndex; i < numbers.size(); ++i) {
      term.append((term.length() == 0) ? "" : "*").append(numbers.get(i));
      product *= numbers.get(i);

      if (i == numbers.size() - 1) {
        if (product == target) {
          result.add((first ? "" : "+") + term.toString());
        }
        if (!first && product == -target) {
          result.add("-" + term.toString());
        }
      } else {
        result.addAll(
            combine(
                (first ? "" : "+") + term.toString(),
                search(numbers, i + 1, target - product, false)));
        if (!first) {
          result.addAll(
              combine("-" + term.toString(), search(numbers, i + 1, target + product, false)));
        }
      }
    }

    return result;
  }

  List<String> combine(String leftPart, List<String> rightParts) {
    return rightParts.stream().map(rightPart -> leftPart + rightPart).toList();
  }
}
