class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] wins = new boolean[n + 1];
        for (int i = 1; i < wins.length; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                if (!wins[i - j * j]) {
                    wins[i] = true;

                    break;
                }
            }
        }

        return wins[n];
    }
}