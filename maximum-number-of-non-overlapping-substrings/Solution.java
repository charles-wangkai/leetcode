import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    static final int SIZE = 26;

    public List<String> maxNumOfSubstrings(String s) {
        int[] beginIndices = new int[SIZE];
        Arrays.fill(beginIndices, Integer.MAX_VALUE);
        int[] endIndices = new int[SIZE];
        Arrays.fill(endIndices, Integer.MIN_VALUE);

        for (int i = 0; i < s.length(); ++i) {
            int value = s.charAt(i) - 'a';
            beginIndices[value] = Math.min(beginIndices[value], i);
            endIndices[value] = Math.max(endIndices[value], i);
        }

        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < SIZE; ++i) {
            if (beginIndices[i] != Integer.MAX_VALUE) {
                Set<Integer> values = new HashSet<>();
                values.add(i);

                int minIndex = beginIndices[i];
                int maxIndex = endIndices[i];
                for (int j = minIndex; j <= maxIndex; ++j) {
                    int value = s.charAt(j) - 'a';
                    if (!values.contains(value)) {
                        values.add(value);

                        if (beginIndices[value] < minIndex) {
                            minIndex = beginIndices[value];
                            j = minIndex;
                        }
                        if (endIndices[value] > maxIndex) {
                            maxIndex = endIndices[value];
                        }
                    }
                }

                elements.add(new Element(s.substring(minIndex, maxIndex + 1), values));
            }
        }

        Collections.sort(elements, (e1, e2) -> Integer.compare(e1.substring.length(), e2.substring.length()));

        List<String> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (Element element : elements) {
            if (!element.values.stream().anyMatch(seen::contains)) {
                result.add(element.substring);
                seen.addAll(element.values);
            }
        }

        return result;
    }
}

class Element {
    String substring;
    Set<Integer> values;

    Element(String substring, Set<Integer> values) {
        this.substring = substring;
        this.values = values;
    }
}