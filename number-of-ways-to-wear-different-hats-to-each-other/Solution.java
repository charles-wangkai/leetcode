import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    static final int MODULUS = 1_000_000_007;

    public int numberWays(List<List<Integer>> hats) {
        int peopleNum = hats.size();

        @SuppressWarnings("unchecked")
        Set<Integer>[] hatToPeople = new Set[41];
        for (int i = 0; i < hatToPeople.length; ++i) {
            hatToPeople[i] = new HashSet<>();
        }

        for (int i = 0; i < hats.size(); ++i) {
            for (int hat : hats.get(i)) {
                hatToPeople[hat].add(i);
            }
        }

        int[] wayNums = new int[1 << peopleNum];
        wayNums[0] = 1;

        for (Set<Integer> people : hatToPeople) {
            int[] nextWayNums = Arrays.copyOf(wayNums, wayNums.length);
            for (int person : people) {
                for (int i = 0; i < nextWayNums.length; ++i) {
                    if ((i & (1 << person)) != 0) {
                        nextWayNums[i] = addMod(nextWayNums[i], wayNums[i - (1 << person)]);
                    }
                }
            }

            wayNums = nextWayNums;
        }

        return wayNums[wayNums.length - 1];
    }

    static int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }
}