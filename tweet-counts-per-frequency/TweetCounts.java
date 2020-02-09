import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TweetCounts {
    private Map<String, SortedMap<Integer, Integer>> nameToTimeTable = new HashMap<>();

    public void recordTweet(String tweetName, int time) {
        if (!nameToTimeTable.containsKey(tweetName)) {
            nameToTimeTable.put(tweetName, new TreeMap<>());
        }

        SortedMap<Integer, Integer> timeTable = nameToTimeTable.get(tweetName);
        timeTable.put(time, timeTable.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int interval = getInterval(freq);
        int[] result = new int[getIndex(interval, startTime, endTime) + 1];

        if (nameToTimeTable.containsKey(tweetName)) {
            for (Entry<Integer, Integer> entry : nameToTimeTable.get(tweetName).subMap(startTime, endTime + 1)
                    .entrySet()) {
                result[getIndex(interval, startTime, entry.getKey())] += entry.getValue();
            }
        }

        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    private int getInterval(String freq) {
        if (freq.equals("minute")) {
            return 60;
        } else if (freq.equals("hour")) {
            return 60 * 60;
        } else {
            return 24 * 60 * 60;
        }
    }

    private int getIndex(int interval, int startTime, int time) {
        return (time - startTime) / interval;
    }
}

// Your TweetCounts object will be instantiated and called as such:
// TweetCounts obj = new TweetCounts();
// obj.recordTweet(tweetName,time);
// List<Integer> param_2 =
// obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);