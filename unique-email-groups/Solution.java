import java.util.Arrays;

class Solution {
  public int uniqueEmailGroups(String[] emails) {
    return (int)
        Arrays.stream(emails)
            .map(
                email -> {
                  int atIndex = email.indexOf('@');

                  return new Element(
                      normalizeLocal(email.substring(0, atIndex)),
                      normalizeDomain(email.substring(atIndex + 1)));
                })
            .distinct()
            .count();
  }

  String normalizeLocal(String s) {
    int plusIndex = s.indexOf('+');
    if (plusIndex != -1) {
      s = s.substring(0, plusIndex);
    }

    return s.replace(".", "").toLowerCase();
  }

  String normalizeDomain(String s) {
    return s.toLowerCase();
  }
}

record Element(String normalizedLocal, String normalizedDomain) {}
