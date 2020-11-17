import java.util.ArrayList;
import java.util.List;

class OrderedStream {
  String[] values;
  int ptr = 1;

  public OrderedStream(int n) {
    values = new String[n + 1];
  }

  public List<String> insert(int id, String value) {
    values[id] = value;

    List<String> result = new ArrayList<>();
    while (ptr != values.length && values[ptr] != null) {
      result.add(values[ptr]);
      ++ptr;
    }

    return result;
  }
}

 // Your OrderedStream object will be instantiated and called as such:
 // OrderedStream obj = new OrderedStream(n);
 // List<String> param_1 = obj.insert(id,value);
