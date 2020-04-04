class Solution {
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        for (int x = x_center - radius; x <= x_center + radius; ++x) {
            for (int y = y_center - radius; y <= y_center + radius; ++y) {
                if (isInCircle(radius, x_center, y_center, x, y) && isInRectangle(x1, y1, x2, y2, x, y)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean isInCircle(int radius, int x_center, int y_center, int x, int y) {
        return (x - x_center) * (x - x_center) + (y - y_center) * (y - y_center) <= radius * radius;
    }

    boolean isInRectangle(int x1, int y1, int x2, int y2, int x, int y) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }
}