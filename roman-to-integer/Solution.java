class Solution {
  static final Element[] ELEMENTS = {
    new Element("M", 1000),
    new Element("CM", 900),
    new Element("D", 500),
    new Element("CD", 400),
    new Element("C", 100),
    new Element("XC", 90),
    new Element("L", 50),
    new Element("XL", 40),
    new Element("X", 10),
    new Element("IX", 9),
    new Element("V", 5),
    new Element("IV", 4),
    new Element("I", 1)
  };

  public int romanToInt(String s) {
    int result = 0;
    int index = 0;
    while (index != s.length()) {
      for (Element element : ELEMENTS) {
        if (s.startsWith(element.roman(), index)) {
          result += element.value();
          index += element.roman().length();

          break;
        }
      }
    }

    return result;
  }
}

record Element(String roman, int value) {}
