import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    static final int MODULUS = 1_000_000_007;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        boolean[][] visited = new boolean[locations.length][fuel + 1];
        int[][] wayNums = new int[locations.length][fuel + 1];
        wayNums[start][fuel] = 1;
        PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) -> -Integer.compare(e1.fuel, e2.fuel));
        pq.offer(new Element(start, fuel));

        while (!pq.isEmpty()) {
            Element head = pq.poll();
            if (!visited[head.index][head.fuel]) {
                visited[head.index][head.fuel] = true;

                for (int i = 0; i < locations.length; ++i) {
                    if (i != head.index && Math.abs(locations[i] - locations[head.index]) <= head.fuel) {
                        int nextFuel = head.fuel - Math.abs(locations[i] - locations[head.index]);

                        wayNums[i][nextFuel] = addMod(wayNums[i][nextFuel], wayNums[head.index][head.fuel]);
                        pq.offer(new Element(i, nextFuel));
                    }
                }
            }
        }

        return Arrays.stream(wayNums[finish]).reduce(this::addMod).getAsInt();
    }

    int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }
}

class Element {
    int index;
    int fuel;

    Element(int index, int fuel) {
        this.index = index;
        this.fuel = fuel;
    }
}