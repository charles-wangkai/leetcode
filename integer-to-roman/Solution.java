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
    for (Element element : ELEMENTS) {
      while (num >= element.value()) {
        result.append(element.roman());
        num -= element.value();
      }
    }

    return result.toString();
  }
}

record Element(int value, String roman) {}
