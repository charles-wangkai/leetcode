import java.util.Arrays;
import java.util.regex.Pattern;

class Solution {
  public int numUniqueEmails(String[] emails) {
    return (int) Arrays.stream(emails).map(this::simplify).distinct().count();
  }

  String simplify(String email) {
    int atIndex = email.indexOf('@');

    return String.format(
        "%s%s", processPeriod(processPlus(email.substring(0, atIndex))), email.substring(atIndex));
  }

  String processPlus(String localName) {
    int index = localName.indexOf('+');

    return (index == -1) ? localName : localName.substring(0, index);
  }

  String processPeriod(String localName) {
    return localName.replaceAll(Pattern.quote("."), "");
  }
}
