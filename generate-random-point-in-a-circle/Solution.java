class Solution {
  private double radius;
  private double xCenter;
  private double yCenter;

  public Solution(double radius, double x_center, double y_center) {
    this.radius = radius;
    xCenter = x_center;
    yCenter = y_center;
  }

  public double[] randPoint() {
    while (true) {
      double x = xCenter + Math.random() * (radius * 2) - radius;
      double y = yCenter + Math.random() * (radius * 2) - radius;

      if (computeDistance(x, y, xCenter, yCenter) <= radius) {
        return new double[] {x, y};
      }
    }
  }

  private double computeDistance(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(radius, x_center, y_center);
// double[] param_1 = obj.randPoint();
