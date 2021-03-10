class Solution {
  static final Element[] ELEMENTS = {
    new Element(1000, "M"),
    new Element(900, "CM"),
    new Element(500, "D"),
    new Element(400, "CD"),
    new Element(100, "C"),
    new Element(90, "XC"),
    new Element(50, "L"),
    new Element(40, "XL"),
    new Element(10, "X"),
    new Element(9, "IX"),
    new Element(5, "V"),
    new Element(4, "IV"),
    new Element(1, "I")
  };

  public String intToRoman(int num) {
    StringBuilder result = new StringBuilder();
    while (num != 0) {
      for (Element element : ELEMENTS) {
        if (element.value <= num) {
          result.append(element.roman);
          num -= element.value;

          break;
        }
      }
    }

    return result.toString();
  }
}

class Element {
  int value;
  String roman;

  Element(int value, String roman) {
    this.value = value;
    this.roman = roman;
  }
}
