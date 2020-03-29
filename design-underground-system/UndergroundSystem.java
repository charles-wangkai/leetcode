import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class UndergroundSystem {
    private Map<Pair, Long> pairToTotalTime = new HashMap<>();
    private Map<Pair, Integer> pairToCount = new HashMap<>();
    private Map<Integer, String> idToStartStation = new HashMap<>();
    private Map<Integer, Integer> idToStartTime = new HashMap<>();

    public void checkIn(int id, String stationName, int t) {
        idToStartStation.put(id, stationName);
        idToStartTime.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {
        Pair pair = new Pair(idToStartStation.remove(id), stationName);
        int time = t - idToStartTime.remove(id);

        pairToTotalTime.put(pair, pairToTotalTime.getOrDefault(pair, 0L) + time);
        pairToCount.put(pair, pairToCount.getOrDefault(pair, 0) + 1);
    }

    public double getAverageTime(String startStation, String endStation) {
        Pair pair = new Pair(startStation, endStation);

        return (double) pairToTotalTime.get(pair) / pairToCount.get(pair);
    }
}

class Pair {
    String startStation;
    String endStation;

    Pair(String startStation, String endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startStation, endStation);
    }

    @Override
    public boolean equals(Object obj) {
        Pair other = (Pair) obj;

        return startStation.equals(other.startStation) && endStation.equals(other.endStation);
    }
}

// Your UndergroundSystem object will be instantiated and called as such:
// UndergroundSystem obj = new UndergroundSystem();
// obj.checkIn(id,stationName,t);
// obj.checkOut(id,stationName,t);
// double param_3 = obj.getAverageTime(startStation,endStation);
