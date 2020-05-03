import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> startCities = paths.stream().map(path -> path.get(0)).collect(Collectors.toSet());

        return paths.stream().map(path -> path.get(1)).filter(city -> !startCities.contains(city)).findAny().get();
    }
}