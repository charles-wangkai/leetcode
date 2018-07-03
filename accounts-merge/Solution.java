import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<String> noEmailNames = new ArrayList<String>();
		Map<String, String> email2name = new HashMap<String, String>();
		for (List<String> account : accounts) {
			Iterator<String> iter = account.iterator();
			String name = iter.next();

			if (!iter.hasNext()) {
				noEmailNames.add(name);
			}

			while (iter.hasNext()) {
				String email = iter.next();
				email2name.put(email, name);
			}
		}
		Set<String> emails = email2name.keySet();

		Map<String, String> email2parent = new HashMap<String, String>();
		for (String email : emails) {
			email2parent.put(email, null);
		}
		for (List<String> account : accounts) {
			Iterator<String> iter = account.iterator();
			iter.next();
			if (iter.hasNext()) {
				String firstEmail = iter.next();
				while (iter.hasNext()) {
					String email = iter.next();
					union(email2parent, firstEmail, email);
				}
			}
		}

		List<List<String>> mergedAccounts = new ArrayList<List<String>>();
		for (String noEmailName : noEmailNames) {
			mergedAccounts.add(Collections.singletonList(noEmailName));
		}
		for (String root : emails) {
			if (email2parent.get(root) == null) {
				List<String> account = new ArrayList<String>();
				account.add(email2name.get(root));
				account.addAll(emails.stream().filter(email -> find(email2parent, email).equals(root)).sorted()
						.collect(Collectors.toList()));
				mergedAccounts.add(account);
			}
		}
		return mergedAccounts;
	}

	String find(Map<String, String> email2parent, String email) {
		String root = email;
		while (email2parent.get(root) != null) {
			root = email2parent.get(root);
		}
		return root;
	}

	void union(Map<String, String> email2parent, String email1, String email2) {
		String root1 = find(email2parent, email1);
		String root2 = find(email2parent, email2);
		if (!root1.equals(root2)) {
			email2parent.put(root2, root1);
		}
	}
}
