import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        SortedSet<String> tables = new TreeSet<>(
                (t1, t2) -> Integer.compare(Integer.parseInt(t1), Integer.parseInt(t2)));
        SortedSet<String> foods = new TreeSet<>();
        Map<TableFood, Integer> tableFoodToCount = new HashMap<>();
        for (List<String> order : orders) {
            String table = order.get(1);
            String food = order.get(2);

            tables.add(table);
            foods.add(food);
            TableFood tableFood = new TableFood(table, food);
            tableFoodToCount.put(tableFood, tableFoodToCount.getOrDefault(tableFood, 0) + 1);
        }

        List<List<String>> result = new ArrayList<>();
        result.add(Stream.concat(Stream.of("Table"), foods.stream()).collect(Collectors.toList()));
        for (String table : tables) {
            List<String> row = new ArrayList<>();
            row.add(table);
            for (String food : foods) {
                row.add(String.valueOf(tableFoodToCount.getOrDefault(new TableFood(table, food), 0)));
            }

            result.add(row);
        }

        return result;
    }
}

class TableFood {
    String table;
    String food;

    TableFood(String table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, food);
    }

    @Override
    public boolean equals(Object obj) {
        TableFood other = (TableFood) obj;

        return table.equals(other.table) && food.equals(other.food);
    }
}