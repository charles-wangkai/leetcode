class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder result = new StringBuilder();
        char prev = 0;
        int count = -1;
        while (true) {
            boolean canA = !(prev == 'a' && count == 2);
            boolean canB = !(prev == 'b' && count == 2);
            boolean canC = !(prev == 'c' && count == 2);

            if (a != 0 && canA && (a >= b || !canB) && (a >= c || !canC)) {
                result.append('a');
                --a;

                if (prev == 'a') {
                    ++count;
                } else {
                    prev = 'a';
                    count = 1;
                }
            } else if (b != 0 && canB && (b >= a || !canA) && (b >= c || !canC)) {
                result.append('b');
                --b;

                if (prev == 'b') {
                    ++count;
                } else {
                    prev = 'b';
                    count = 1;
                }
            } else if (c != 0 && canC && (c >= a || !canA) && (c >= b || !canB)) {
                result.append('c');
                --c;

                if (prev == 'c') {
                    ++count;
                } else {
                    prev = 'c';
                    count = 1;
                }
            } else {
                return result.toString();
            }
        }
    }
}