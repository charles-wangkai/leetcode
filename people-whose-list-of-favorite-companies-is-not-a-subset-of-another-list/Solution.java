import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Set<String>> companySets = favoriteCompanies.stream()
                .map(companies -> companies.stream().collect(Collectors.toSet())).collect(Collectors.toList());

        return IntStream.range(0, companySets.size())
                .filter(i -> IntStream.range(0, companySets.size())
                        .allMatch(j -> j == i || !isSubset(companySets.get(i), companySets.get(j))))
                .boxed().collect(Collectors.toList());
    }

    boolean isSubset(Set<String> set1, Set<String> set2) {
        return set1.stream().allMatch(set2::contains);
    }
}