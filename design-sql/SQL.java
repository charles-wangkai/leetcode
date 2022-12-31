import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SQL {
  Map<String, Integer> nameToNextId = new HashMap<>();
  Map<String, Map<Integer, List<String>>> nameToRows = new HashMap<>();

  public SQL(List<String> names, List<Integer> columns) {
    for (String name : names) {
      nameToNextId.put(name, 1);
      nameToRows.put(name, new HashMap<>());
    }
  }

  public void insertRow(String name, List<String> row) {
    int id = nameToNextId.get(name);
    nameToNextId.put(name, id + 1);

    nameToRows.get(name).put(id, row);
  }

  public void deleteRow(String name, int rowId) {
    nameToRows.get(name).remove(rowId);
  }

  public String selectCell(String name, int rowId, int columnId) {
    return nameToRows.get(name).get(rowId).get(columnId - 1);
  }
}

// Your SQL object will be instantiated and called as such:
// SQL obj = new SQL(names, columns);
// obj.insertRow(name,row);
// obj.deleteRow(name,rowId);
// String param_3 = obj.selectCell(name,rowId,columnId);
