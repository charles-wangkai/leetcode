import java.util.stream.IntStream;

class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        return (int) IntStream.range(0, startTime.length)
                .filter(i -> queryTime >= startTime[i] && queryTime <= endTime[i]).count();
    }
}