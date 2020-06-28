import java.util.Arrays;

class Solution {
    public double average(int[] salary) {
        return (Arrays.stream(salary).sum() - Arrays.stream(salary).min().getAsInt()
                - Arrays.stream(salary).max().getAsInt()) / (salary.length - 2.0);
    }
}