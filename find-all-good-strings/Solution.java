import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
    static final int MODULUS = 1_000_000_007;

    static Map<State, Integer> cache = null;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        cache = new HashMap<>();

        return search(n, s1, s2, evil, buildNexts(evil), 0, true, true, 0);
    }

    int search(int n, String s1, String s2, String evil, int[] nexts, int index, boolean prefix1, boolean prefix2,
            int evilPrefixLength) {
        if (evilPrefixLength == evil.length()) {
            return 0;
        }
        if (index == n) {
            return 1;
        }

        State state = new State(index, prefix1, prefix2, evilPrefixLength);
        if (cache.containsKey(state)) {
            return cache.get(state);
        }

        int result = 0;

        char begin = prefix1 ? s1.charAt(index) : 'a';
        char end = prefix2 ? s2.charAt(index) : 'z';
        for (char ch = begin; ch <= end; ++ch) {
            boolean nextPrefix1 = prefix1 && ch == s1.charAt(index);
            boolean nextPrefix2 = prefix2 && ch == s2.charAt(index);

            int nextEvilPrefixLength = evilPrefixLength;
            while (nextEvilPrefixLength > 0 && ch != evil.charAt(nextEvilPrefixLength)) {
                nextEvilPrefixLength = nexts[nextEvilPrefixLength - 1];
            }
            if (ch == evil.charAt(nextEvilPrefixLength)) {
                ++nextEvilPrefixLength;
            }

            result = addMod(result,
                    search(n, s1, s2, evil, nexts, index + 1, nextPrefix1, nextPrefix2, nextEvilPrefixLength));
        }

        cache.put(state, result);

        return result;
    }

    int[] buildNexts(String s) {
        int[] nexts = new int[s.length()];
        int k = 0;
        for (int i = 1; i < nexts.length; ++i) {
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = nexts[k - 1];
            }

            if (s.charAt(k) == s.charAt(i)) {
                ++k;
            }

            nexts[i] = k;
        }

        return nexts;
    }

    int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }
}

class State {
    int index;
    boolean prefix1;
    boolean prefix2;
    int evilPrefixLength;

    State(int index, boolean prefix1, boolean prefix2, int evilPrefixLength) {
        this.index = index;
        this.prefix1 = prefix1;
        this.prefix2 = prefix2;
        this.evilPrefixLength = evilPrefixLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, prefix1, prefix2, evilPrefixLength);
    }

    @Override
    public boolean equals(Object obj) {
        State other = (State) obj;

        return index == other.index && prefix1 == other.prefix1 && prefix2 == other.prefix2
                && evilPrefixLength == other.evilPrefixLength;
    }
}