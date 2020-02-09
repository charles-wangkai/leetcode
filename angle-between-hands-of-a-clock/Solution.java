public class Solution {
    public double angleClock(int hour, int minutes) {
        double angle = Math.abs(hour * 30 - minutes * 5.5);

        return Math.min(angle, 360 - angle);
    }
}