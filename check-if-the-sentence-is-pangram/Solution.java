class Solution {
  public boolean checkIfPangram(String sentence) {
    return sentence.chars().distinct().count() == 26;
  }
}
