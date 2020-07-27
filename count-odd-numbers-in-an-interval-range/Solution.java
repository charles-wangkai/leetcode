class Solution {
    public int countOdds(int low, int high) {
        return ((high - (1 - high % 2)) - (low + (1 - low % 2))) / 2 + 1;
    }
}