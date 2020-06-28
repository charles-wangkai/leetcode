import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] prereqs = new int[n];
        for (int[] dependency : dependencies) {
            prereqs[dependency[1] - 1] |= 1 << (dependency[0] - 1);
        }

        Set<Integer> states = new HashSet<>();
        states.add(0);

        for (int semester = 1;; ++semester) {
            Set<Integer> nextStates = new HashSet<>();

            for (int state : states) {
                boolean[] availables = new boolean[n];
                int availableCount = 0;
                for (int i = 0; i < availables.length; ++i) {
                    if ((state & (1 << i)) == 0 && (prereqs[i] & ~state) == 0) {
                        availables[i] = true;
                        ++availableCount;
                    }
                }

                int takenNum = Math.min(availableCount, k);
                int skipNum = availableCount - takenNum;

                search(nextStates, state, availables, 0, takenNum, skipNum);
            }

            states = nextStates;
            if (states.contains((1 << n) - 1)) {
                return semester;
            }
        }
    }

    void search(Set<Integer> nextStates, int state, boolean[] availables, int index, int takeNum, int skipNum) {
        if (index == availables.length) {
            nextStates.add(state);

            return;
        }

        if (!availables[index]) {
            search(nextStates, state, availables, index + 1, takeNum, skipNum);
        }

        if (skipNum != 0) {
            search(nextStates, state, availables, index + 1, takeNum, skipNum - 1);
        }
        if (takeNum != 0) {
            search(nextStates, state | (1 << index), availables, index + 1, takeNum - 1, skipNum);
        }
    }
}