class Solution {
    public int findTheLongestSubstring(String s) {
        int[][][][][] rightLengths = buildRightLengths(s);

        int totalA = count(s, 'a');
        int totalE = count(s, 'e');
        int totalI = count(s, 'i');
        int totalO = count(s, 'o');
        int totalU = count(s, 'u');

        int a = 0;
        int e = 0;
        int i = 0;
        int o = 0;
        int u = 0;
        int result = s.length() - rightLengths[totalA][totalE][totalI][totalO][totalU];
        for (int j = 0; j < s.length(); ++j) {
            char ch = s.charAt(j);
            if (ch == 'a') {
                a = 1 - a;
            } else if (ch == 'e') {
                e = 1 - e;
            } else if (ch == 'i') {
                i = 1 - i;
            } else if (ch == 'o') {
                o = 1 - o;
            } else if (ch == 'u') {
                u = 1 - u;
            }

            int rightA = totalA ^ a;
            int rightE = totalE ^ e;
            int rightI = totalI ^ i;
            int rightO = totalO ^ o;
            int rightU = totalU ^ u;

            result = Math.max(result, s.length() - (j + 1) - rightLengths[rightA][rightE][rightI][rightO][rightU]);
        }

        return result;
    }

    int count(String s, char target) {
        return (int) (s.chars().filter(ch -> ch == target).count() % 2);
    }

    int[][][][][] buildRightLengths(String s) {
        int[][][][][] rightLengths = new int[2][2][2][2][2];
        for (int a = 0; a < 2; ++a) {
            for (int e = 0; e < 2; ++e) {
                for (int i = 0; i < 2; ++i) {
                    for (int o = 0; o < 2; ++o) {
                        for (int u = 0; u < 2; ++u) {
                            rightLengths[a][e][i][o][u] = -1;
                        }
                    }
                }
            }
        }
        rightLengths[0][0][0][0][0] = 0;

        int a = 0;
        int e = 0;
        int i = 0;
        int o = 0;
        int u = 0;
        for (int j = s.length() - 1; j >= 0; --j) {
            char ch = s.charAt(j);
            if (ch == 'a') {
                a = 1 - a;
            } else if (ch == 'e') {
                e = 1 - e;
            } else if (ch == 'i') {
                i = 1 - i;
            } else if (ch == 'o') {
                o = 1 - o;
            } else if (ch == 'u') {
                u = 1 - u;
            }

            if (rightLengths[a][e][i][o][u] == -1) {
                rightLengths[a][e][i][o][u] = s.length() - j;
            }
        }

        return rightLengths;
    }
}