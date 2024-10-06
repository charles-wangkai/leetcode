class Solution {
  public boolean areSentencesSimilar(String sentence1, String sentence2) {
    String[] words1 = sentence1.split(" ");
    String[] words2 = sentence2.split(" ");

    int leftLength = 0;
    while (leftLength != words1.length
        && leftLength != words2.length
        && words1[leftLength].equals(words2[leftLength])) {
      ++leftLength;
    }

    int rightLength = 0;
    while (rightLength != words1.length
        && rightLength != words2.length
        && words1[words1.length - 1 - rightLength].equals(
            words2[words2.length - 1 - rightLength])) {
      ++rightLength;
    }

    return leftLength + rightLength >= Math.min(words1.length, words2.length);
  }
}
