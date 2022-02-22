// https://leetcode.com/problems/subtree-removal-game-with-fibonacci-tree/discuss/1468192/Hackenbush-game-and-Colon-principle
// https://blog.csdn.net/wu_tongtong/article/details/79311284

class Solution {
  public boolean findGameWinner(int n) {
    int[] sg = new int[n + 1];
    for (int i = 1; i < sg.length; ++i) {
      sg[i] = (i <= 2) ? (i - 1) : ((sg[i - 2] + 1) ^ (sg[i - 1] + 1));
    }

    return sg[n] != 0;
  }
}