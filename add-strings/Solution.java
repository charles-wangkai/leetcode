class Solution {
  public String addStrings(String num1, String num2) {
    StringBuilder reversed = new StringBuilder();
    int carry = 0;
    for (int index1 = num1.length() - 1, index2 = num2.length() - 1;
        index1 >= 0 || index2 >= 0;
        --index1, --index2) {
      int sum =
          ((index1 >= 0) ? (num1.charAt(index1) - '0') : 0)
              + ((index2 >= 0) ? (num2.charAt(index2) - '0') : 0)
              + carry;
      reversed.append(sum % 10);
      carry = sum / 10;
    }
    if (carry != 0) {
      reversed.append(carry);
    }

    return reversed.reverse().toString();
  }
}
