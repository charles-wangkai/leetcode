import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public String largestMultipleOfThree(int[] digits) {
        @SuppressWarnings("unchecked")
        List<Integer>[] mods = new List[3];
        for (int i = 0; i < mods.length; ++i) {
            mods[i] = new ArrayList<>();
        }

        for (int digit : digits) {
            mods[digit % 3].add(digit);
        }

        Arrays.stream(mods).forEach(m -> Collections.sort(m, Collections.reverseOrder()));

        int totalMod = Arrays.stream(digits).sum() % 3;
        if (totalMod != 0) {
            if (!mods[totalMod].isEmpty()) {
                mods[totalMod].remove(mods[totalMod].size() - 1);
            } else {
                mods[3 - totalMod].remove(mods[3 - totalMod].size() - 1);
                mods[3 - totalMod].remove(mods[3 - totalMod].size() - 1);
            }
        }

        if (mods[1].isEmpty() && mods[2].isEmpty() && !mods[0].isEmpty() && mods[0].get(0) == 0) {
            return "0";
        }

        return Arrays.stream(mods).flatMap(List::stream).sorted(Collections.reverseOrder())
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}