import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubdomainVisitCount {
	public List<String> subdomainVisits(String[] cpdomains) {
		Map<String, Integer> subdomain2count = new HashMap<String, Integer>();
		for (String cpdomain : cpdomains) {
			String[] fields = cpdomain.split(" ");
			int count = Integer.parseInt(fields[0]);
			String domain = fields[1];

			int beginIndex = 0;
			while (true) {
				String subdomain = domain.substring(beginIndex);
				subdomain2count.put(subdomain, subdomain2count.getOrDefault(subdomain, 0) + count);

				beginIndex = domain.indexOf('.', beginIndex);
				if (beginIndex < 0) {
					break;
				}
				beginIndex++;
			}
		}

		return subdomain2count.entrySet().stream().map(entry -> entry.getValue() + " " + entry.getKey())
				.collect(Collectors.toList());
	}
}
