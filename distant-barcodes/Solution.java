import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
  public int[] rearrangeBarcodes(int[] barcodes) {
    Map<Integer, Integer> barcodeToCount = new HashMap<>();
    for (int barcode : barcodes) {
      barcodeToCount.put(barcode, barcodeToCount.getOrDefault(barcode, 0) + 1);
    }

    List<Entry<Integer, Integer>> sortedEntries =
        barcodeToCount.entrySet().stream()
            .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
            .toList();

    int[] result = new int[barcodes.length];
    int index = -2;
    for (Entry<Integer, Integer> entry : sortedEntries) {
      int barcode = entry.getKey();
      int count = entry.getValue();

      for (int i = 0; i < count; i++) {
        index += 2;
        if (index % 2 == 0 && index >= result.length) {
          index = 1;
        }

        result[index] = barcode;
      }
    }
    return result;
  }
}
