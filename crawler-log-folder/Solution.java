class Solution {
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if (log.equals("../")) {
                depth = Math.max(0, depth - 1);
            } else if (!log.equals("./")) {
                ++depth;
            }
        }

        return depth;
    }
}