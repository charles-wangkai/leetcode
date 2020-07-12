import java.util.Arrays;

class Solution {
    public double getMinDistSum(int[][] positions) {
        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;
        for (int[] position : positions) {
            minX = Math.min(minX, position[0]);
            maxX = Math.max(maxX, position[0]);
            minY = Math.min(minY, position[1]);
            maxY = Math.max(maxY, position[1]);
        }

        double result = Double.MAX_VALUE;
        double bestX = 0;
        double bestY = 0;
        double delta = Math.max(1e-9, Math.max(maxX - minX, maxY - minY));
        do {
            delta /= 10;

            for (double x = minX; x <= maxX; x += delta) {
                for (double y = minY; y <= maxY; y += delta) {
                    double distance = computeDistance(positions, x, y);

                    if (distance < result) {
                        result = distance;
                        bestX = x;
                        bestY = y;
                    }
                }
            }

            minX = bestX - delta * 2;
            maxX = bestX + delta * 2;
            minY = bestY - delta * 2;
            maxY = bestY + delta * 2;
        } while (delta >= 1e-5);

        return result;
    }

    double computeDistance(int[][] positions, double x, double y) {
        return Arrays.stream(positions).mapToDouble(
                position -> Math.sqrt((x - position[0]) * (x - position[0]) + (y - position[1]) * (y - position[1])))
                .sum();
    }
}