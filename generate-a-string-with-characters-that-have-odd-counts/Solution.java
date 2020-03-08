class Solution {
    public String generateTheString(int n) {
        StringBuilder result = new StringBuilder();
        if (n % 2 == 0) {
            result.append('a');
        }
        while (result.length() != n) {
            result.append('b');
        }

        return result.toString();
    }
}