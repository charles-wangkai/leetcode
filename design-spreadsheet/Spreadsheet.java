import java.util.HashMap;
import java.util.Map;

class Spreadsheet {
  Map<String, Integer> cellToValue = new HashMap<>();

  public Spreadsheet(int rows) {}

  public void setCell(String cell, int value) {
    cellToValue.put(cell, value);
  }

  public void resetCell(String cell) {
    cellToValue.remove(cell);
  }

  public int getValue(String formula) {
    int plusIndex = formula.indexOf('+');

    return fetchValue(formula.substring(1, plusIndex))
        + fetchValue(formula.substring(plusIndex + 1));
  }

  private int fetchValue(String operand) {
    return (Character.isDigit(operand.charAt(0)))
        ? Integer.parseInt(operand)
        : cellToValue.getOrDefault(operand, 0);
  }
}

// Your Spreadsheet object will be instantiated and called as such:
// Spreadsheet obj = new Spreadsheet(rows);
// obj.setCell(cell,value);
// obj.resetCell(cell);
// int param_3 = obj.getValue(formula);
