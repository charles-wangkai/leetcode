import java.util.Arrays;

class Solution {
    static final double EPSILON = 1e-9;

    public int numPoints(int[][] points, int r) {
        int result = 1;
        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                int dx = x1 - x2;
                int dy = y1 - y2;

                if (dx * dx + dy * dy <= 4 * r * r) {
                    double q = Math.sqrt(dx * dx + dy * dy);
                    double x3 = (x1 + x2) / 2.0;
                    double y3 = (y1 + y2) / 2.0;

                    result = Math.max(result,
                            countInnerPoints(points, r, x3 + Math.sqrt(r * r - q * q / 4) * (y1 - y2) / q,
                                    y3 + Math.sqrt(r * r - q * q / 4) * (x2 - x1) / q));
                    result = Math.max(result,
                            countInnerPoints(points, r, x3 - Math.sqrt(r * r - q * q / 4) * (y1 - y2) / q,
                                    y3 - Math.sqrt(r * r - q * q / 4) * (x2 - x1) / q));
                }
            }
        }

        return result;
    }

    static int countInnerPoints(int[][] points, int r, double cx, double cy) {
        return (int) Arrays.stream(points)
                .filter(p -> Math.sqrt((p[0] - cx) * (p[0] - cx) + (p[1] - cy) * (p[1] - cy)) <= r + EPSILON).count();
    }
}