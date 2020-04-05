import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
    Map<State, Score> cache;

    public String stoneGameIII(int[] stoneValue) {
        cache = new HashMap<>();
        Score score = search(stoneValue, 0, true);

        if (score.alice > score.bob) {
            return "Alice";
        } else if (score.alice < score.bob) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

    Score search(int[] stoneValue, int index, boolean aliceOrBobTurn) {
        if (index == stoneValue.length) {
            return new Score(0, 0);
        }

        State state = new State(index, aliceOrBobTurn);
        if (cache.containsKey(state)) {
            return cache.get(state);
        }

        int aliceScore = Integer.MIN_VALUE;
        int bobScore = Integer.MIN_VALUE;
        int current = 0;
        for (int i = 0; i < 3 && index + i < stoneValue.length; ++i) {
            current += stoneValue[index + i];
            Score subScore = search(stoneValue, index + i + 1, !aliceOrBobTurn);

            if (aliceOrBobTurn) {
                if (current + subScore.alice > aliceScore) {
                    aliceScore = current + subScore.alice;
                    bobScore = subScore.bob;
                }
            } else {
                if (current + subScore.bob > bobScore) {
                    bobScore = current + subScore.bob;
                    aliceScore = subScore.alice;
                }
            }
        }

        Score score = new Score(aliceScore, bobScore);
        cache.put(state, score);

        return score;
    }
}

class State {
    int index;
    boolean aliceOrBobTurn;

    State(int index, boolean aliceOrBobTurn) {
        this.index = index;
        this.aliceOrBobTurn = aliceOrBobTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, aliceOrBobTurn);
    }

    @Override
    public boolean equals(Object obj) {
        State other = (State) obj;

        return index == other.index && aliceOrBobTurn == other.aliceOrBobTurn;
    }
}

class Score {
    int alice;
    int bob;

    Score(int alice, int bob) {
        this.alice = alice;
        this.bob = bob;
    }
}