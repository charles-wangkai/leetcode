class Solution {
  public int accountBalanceAfterPurchase(int purchaseAmount) {
    int remainder = purchaseAmount % 10;

    return 100 - (purchaseAmount + ((remainder <= 4) ? -remainder : (10 - remainder)));
  }
}
