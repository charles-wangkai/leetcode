import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, String> emailToName = new HashMap<>();
    for (List<String> account : accounts) {
      for (int i = 1; i < account.size(); ++i) {
        emailToName.put(account.get(i), account.get(0));
      }
    }

    Map<String, String> emailToParent = new HashMap<>();
    for (List<String> account : accounts) {
      for (int i = 1; i + 1 < account.size(); ++i) {
        union(emailToParent, account.get(i), account.get(i + 1));
      }
    }

    Map<String, List<String>> rootToEmails = new HashMap<>();
    for (String email : emailToName.keySet()) {
      String root = findRoot(emailToParent, email);
      if (!rootToEmails.containsKey(root)) {
        rootToEmails.put(root, new ArrayList<>());
      }
      rootToEmails.get(root).add(email);
    }

    return rootToEmails.keySet().stream()
        .map(
            root ->
                Stream.concat(
                        Stream.of(emailToName.get(root)), rootToEmails.get(root).stream().sorted())
                    .collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  String findRoot(Map<String, String> emailToParent, String email) {
    String root = email;
    while (emailToParent.containsKey(root)) {
      root = emailToParent.get(root);
    }

    String p = email;
    while (!p.equals(root)) {
      String next = emailToParent.get(p);
      emailToParent.put(p, root);

      p = next;
    }

    return root;
  }

  void union(Map<String, String> emailToParent, String email1, String email2) {
    String root1 = findRoot(emailToParent, email1);
    String root2 = findRoot(emailToParent, email2);
    if (!root1.equals(root2)) {
      emailToParent.put(root2, root1);
    }
  }
}
