class TextEditor {
  Node current = new Node(null);

  public void addText(String text) {
    Node begin = new Node(text.charAt(0));
    Node end = begin;
    for (int i = 1; i < text.length(); ++i) {
      Node node = new Node(text.charAt(i));
      end.next = node;
      node.prev = end;
      end = node;
    }

    begin.prev = current.prev;
    if (current.prev != null) {
      current.prev.next = begin;
    }

    current.prev = end;
    end.next = current;

    current = end.next;
  }

  public int deleteText(int k) {
    int result = 0;
    for (int i = 0; i < k; ++i) {
      if (current.prev != null) {
        ++result;

        current.prev = current.prev.prev;
        if (current.prev != null) {
          current.prev.next = current;
        }
      }
    }

    return result;
  }

  public String cursorLeft(int k) {
    for (int i = 0; i < k; ++i) {
      if (current.prev != null) {
        current = current.prev;
      }
    }

    return getLeft();
  }

  public String cursorRight(int k) {
    for (int i = 0; i < k; ++i) {
      if (current.next != null) {
        current = current.next;
      }
    }

    return getLeft();
  }

  private String getLeft() {
    StringBuilder sb = new StringBuilder();
    Node node = current;
    for (int i = 0; i < 10; ++i) {
      if (node.prev != null) {
        node = node.prev;
        sb.append(node.letter);
      }
    }

    return sb.reverse().toString();
  }
}

class Node {
  Character letter;
  Node prev;
  Node next;

  Node(Character letter) {
    this.letter = letter;
  }
}

// Your TextEditor object will be instantiated and called as such:
// TextEditor obj = new TextEditor();
// obj.addText(text);
// int param_2 = obj.deleteText(k);
// String param_3 = obj.cursorLeft(k);
// String param_4 = obj.cursorRight(k);
