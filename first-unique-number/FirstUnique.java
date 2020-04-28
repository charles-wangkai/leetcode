import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FirstUnique {
    private List<Integer> values = new ArrayList<>();
    private int index = 0;
    private Map<Integer, Integer> valueToCount = new HashMap<>();

    public FirstUnique(int[] nums) {
        Arrays.stream(nums).forEach(this::add);
    }

    public int showFirstUnique() {
        while (index < values.size() && valueToCount.get(values.get(index)) != 1) {
            ++index;
        }

        return (index == values.size()) ? -1 : values.get(index);
    }

    public void add(int value) {
        values.add(value);
        valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }
}

// Your FirstUnique object will be instantiated and called as such:
// FirstUnique obj = new FirstUnique(nums);
// int param_1 = obj.showFirstUnique();
// obj.add(value);