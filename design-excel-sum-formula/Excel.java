import java.util.ArrayList;
import java.util.List;

public class Excel {
  Cell[][] cells;

  public Excel(int H, char W) {
    cells = new Cell[H + 1][parseColName(W) + 1];

    for (int r = 0; r < cells.length; r++) {
      for (int c = 0; c < cells[0].length; c++) {
        cells[r][c] = new Fixed(0);
      }
    }
  }

  public void set(int r, char c, int v) {
    cells[r][parseColName(c)] = new Fixed(v);
  }

  public int get(int r, char c) {
    return cells[r][parseColName(c)].getValue();
  }

  public int sum(int r, char c, String[] strs) {
    List<Coordinate> coordinates = new ArrayList<>();
    for (String str : strs) {
      int index = str.indexOf(':');
      if (index < 0) {
        coordinates.add(parseCoordinate(str));
      } else {
        Coordinate topLeft = parseCoordinate(str.substring(0, index));
        Coordinate bottomRight = parseCoordinate(str.substring(index + 1));

        for (int i = topLeft.r; i <= bottomRight.r; i++) {
          for (int j = topLeft.c; j <= bottomRight.c; j++) {
            coordinates.add(new Coordinate(i, j));
          }
        }
      }
    }
    cells[r][parseColName(c)] = new Sum(cells, coordinates);

    return get(r, c);
  }

  int parseColName(char c) {
    return c - 'A' + 1;
  }

  Coordinate parseCoordinate(String s) {
    return new Coordinate(Integer.parseInt(s.substring(1)), parseColName(s.charAt(0)));
  }
}

interface Cell {
  int getValue();
}

class Fixed implements Cell {
  int value;

  Fixed(int value) {
    this.value = value;
  }

  @Override
  public int getValue() {
    return value;
  }
}

class Sum implements Cell {
  Cell[][] cells;
  List<Coordinate> coordinates;

  Sum(Cell[][] cells, List<Coordinate> coordinates) {
    this.cells = cells;
    this.coordinates = coordinates;
  }

  @Override
  public int getValue() {
    return coordinates.stream()
        .mapToInt(coordinate -> cells[coordinate.r][coordinate.c].getValue())
        .sum();
  }
}

class Coordinate {
  int r;
  int c;

  Coordinate(int r, int c) {
    this.r = r;
    this.c = c;
  }
}

// Your Excel object will be instantiated and called as such:
// Excel obj = new Excel(H, W);
// obj.set(r,c,v);
// int param_2 = obj.get(r,c);
// int param_3 = obj.sum(r,c,strs);
