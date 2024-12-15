import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public double maxAmount(
      String initialCurrency,
      List<List<String>> pairs1,
      double[] rates1,
      List<List<String>> pairs2,
      double[] rates2) {
    Map<String, Double> currencyToRate1 = buildCurrencyToRate(initialCurrency, pairs1, rates1);
    Map<String, Double> currencyToRate2 = buildCurrencyToRate(initialCurrency, pairs2, rates2);

    return currencyToRate1.keySet().stream()
        .filter(currencyToRate2::containsKey)
        .mapToDouble(currency -> currencyToRate1.get(currency) / currencyToRate2.get(currency))
        .max()
        .getAsDouble();
  }

  Map<String, Double> buildCurrencyToRate(
      String initialCurrency, List<List<String>> pairs, double[] rates) {
    Map<String, Double> currencyToRate = new HashMap<>();
    currencyToRate.put(initialCurrency, 1.0);
    search(currencyToRate, pairs, rates, initialCurrency);

    return currencyToRate;
  }

  void search(
      Map<String, Double> currencyToRate,
      List<List<String>> pairs,
      double[] rates,
      String currency) {
    for (int i = 0; i < pairs.size(); ++i) {
      if (pairs.get(i).get(0).equals(currency)
          && !currencyToRate.containsKey(pairs.get(i).get(1))) {
        currencyToRate.put(pairs.get(i).get(1), currencyToRate.get(pairs.get(i).get(0)) * rates[i]);
        search(currencyToRate, pairs, rates, pairs.get(i).get(1));
      } else if (pairs.get(i).get(1).equals(currency)
          && !currencyToRate.containsKey(pairs.get(i).get(0))) {
        currencyToRate.put(pairs.get(i).get(0), currencyToRate.get(pairs.get(i).get(1)) / rates[i]);
        search(currencyToRate, pairs, rates, pairs.get(i).get(0));
      }
    }
  }
 }