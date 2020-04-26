import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        return IntStream.range(0, nums.size()).boxed()
                .flatMap(r -> IntStream.range(0, nums.get(r).size())
                        .mapToObj(c -> new Element(r, c, nums.get(r).get(c))))
                .sorted((e1, e2) -> (e1.r + e1.c != e2.r + e2.c) ? Integer.compare(e1.r + e1.c, e2.r + e2.c)
                        : Integer.compare(e1.c, e2.c))
                .mapToInt(e -> e.value).toArray();
    }
}

class Element {
    int r;
    int c;
    int value;

    Element(int r, int c, int value) {
        this.r = r;
        this.c = c;
        this.value = value;
    }
}