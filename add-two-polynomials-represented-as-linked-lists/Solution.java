// Definition for polynomial singly-linked list.
class PolyNode {
  int coefficient;
  int power;
  PolyNode next = null;

  PolyNode() {}

  PolyNode(int x, int y) {
    this.coefficient = x;
    this.power = y;
  }

  PolyNode(int x, int y, PolyNode next) {
    this.coefficient = x;
    this.power = y;
    this.next = next;
  }
}

class Solution {
  public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
    PolyNode tempHead = new PolyNode();
    PolyNode tail = tempHead;

    PolyNode node1 = poly1;
    PolyNode node2 = poly2;
    while (node1 != null || node2 != null) {
      if (node2 == null || (node1 != null && node1.power > node2.power)) {
        tail.next = new PolyNode(node1.coefficient, node1.power);
        tail = tail.next;

        node1 = node1.next;
      } else if (node1 == null || node2.power > node1.power) {
        tail.next = new PolyNode(node2.coefficient, node2.power);
        tail = tail.next;

        node2 = node2.next;
      } else {
        if (node1.coefficient + node2.coefficient != 0) {
          tail.next = new PolyNode(node1.coefficient + node2.coefficient, node1.power);
          tail = tail.next;
        }

        node1 = node1.next;
        node2 = node2.next;
      }
    }

    return tempHead.next;
  }
}
