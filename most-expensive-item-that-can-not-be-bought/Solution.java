// https://artofproblemsolving.com/wiki/index.php/Chicken_McNugget_Theorem

class Solution {
  public int mostExpensiveItem(int primeOne, int primeTwo) {
    return primeOne * primeTwo - primeOne - primeTwo;
  }
}