class ATM {
  static final int[] BANKNOTES = {20, 50, 100, 200, 500};

  long[] banknoteNums = new long[BANKNOTES.length];

  public void deposit(int[] banknotesCount) {
    for (int i = 0; i < banknoteNums.length; ++i) {
      banknoteNums[i] += banknotesCount[i];
    }
  }

  public int[] withdraw(int amount) {
    int[] result = new int[BANKNOTES.length];
    int rest = amount;
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] = (int) Math.min(banknoteNums[i], rest / BANKNOTES[i]);
      rest -= BANKNOTES[i] * result[i];
    }

    if (rest != 0) {
      return new int[] {-1};
    }

    for (int i = 0; i < banknoteNums.length; ++i) {
      banknoteNums[i] -= result[i];
    }

    return result;
  }
}

// Your ATM object will be instantiated and called as such:
// ATM obj = new ATM();
// obj.deposit(banknotesCount);
// int[] param_2 = obj.withdraw(amount);
