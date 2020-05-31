import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
    public double getProbability(int[] balls) {
        Map<State, Double> stateToProb = new HashMap<>();
        stateToProb.put(new State(0, 0), 1.0);

        for (int ball : balls) {
            Map<State, Double> nextStateToProb = new HashMap<>();
            for (State state : stateToProb.keySet()) {
                for (int left = 0; left <= ball; ++left) {
                    int right = ball - left;

                    int totalDiff = left - right;
                    int distinctDiff = (left == 0 ? 0 : 1) - (right == 0 ? 0 : 1);

                    State nextState = new State(state.totalDelta + totalDiff, state.distinctDelta + distinctDiff);
                    double p = (double) C(ball, left) / (1 << ball);

                    nextStateToProb.put(nextState,
                            nextStateToProb.getOrDefault(nextState, 0.0) + stateToProb.get(state) * p);
                }
            }

            stateToProb = nextStateToProb;
        }

        return stateToProb.getOrDefault(new State(0, 0), 0.0) / stateToProb.keySet().stream()
                .filter(state -> state.totalDelta == 0).mapToDouble(stateToProb::get).sum();
    }

    int C(int n, int r) {
        int result = 1;
        for (int i = 1; i <= r; ++i) {
            result = result * (n - i + 1) / i;
        }

        return result;
    }
}

class State {
    int totalDelta;
    int distinctDelta;

    State(int totalDelta, int distinctDelta) {
        this.totalDelta = totalDelta;
        this.distinctDelta = distinctDelta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalDelta, distinctDelta);
    }

    @Override
    public boolean equals(Object obj) {
        State other = (State) obj;

        return totalDelta == other.totalDelta && distinctDelta == other.distinctDelta;
    }
}