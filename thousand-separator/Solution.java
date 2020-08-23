class Solution {
    public String thousandSeparator(int n) {
        String s = String.valueOf(n);
        String result = "";
        for (int i = 0; i < s.length(); ++i) {
            if (i != 0 && i % 3 == 0) {
                result = '.' + result;
            }

            result = s.charAt(s.length() - 1 - i) + result;
        }

        return result;
    }
}