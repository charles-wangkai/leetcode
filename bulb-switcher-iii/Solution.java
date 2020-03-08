class Solution {
    public int numTimesAllBlue(int[] light) {
        int result = 0;
        int maxLight = 0;
        for (int i = 0; i < light.length; ++i) {
            maxLight = Math.max(maxLight, light[i]);
            if (maxLight == i + 1) {
                ++result;
            }
        }

        return result;
    }
}