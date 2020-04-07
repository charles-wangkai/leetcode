import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int countElements(int[] arr) {
        Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());

        return (int) Arrays.stream(arr).filter(x -> set.contains(x + 1)).count();
    }
}