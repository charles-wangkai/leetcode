import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

class FileSharing {
    PriorityQueue<Integer> availableIds = new PriorityQueue<>();
    int maxId = 0;
    Map<Integer, List<Integer>> userIdToChunkIds = new HashMap<>();
    Map<Integer, SortedSet<Integer>> chunkIdToUserIds = new HashMap<>();

    public FileSharing(int m) {
    }

    public int join(List<Integer> ownedChunks) {
        int userId;
        if (availableIds.isEmpty()) {
            ++maxId;
            userId = maxId;
        } else {
            userId = availableIds.poll();
        }

        userIdToChunkIds.put(userId, ownedChunks);

        for (int chunkId : ownedChunks) {
            if (!chunkIdToUserIds.containsKey(chunkId)) {
                chunkIdToUserIds.put(chunkId, new TreeSet<>());
            }

            chunkIdToUserIds.get(chunkId).add(userId);
        }

        return userId;
    }

    public void leave(int userID) {
        availableIds.offer(userID);

        for (int chunkId : userIdToChunkIds.remove(userID)) {
            chunkIdToUserIds.get(chunkId).remove(userID);

            if (chunkIdToUserIds.get(chunkId).isEmpty()) {
                chunkIdToUserIds.remove(chunkId);
            }
        }
    }

    public List<Integer> request(int userID, int chunkID) {
        if (!chunkIdToUserIds.containsKey(chunkID)) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>(chunkIdToUserIds.get(chunkID));

        if (!chunkIdToUserIds.get(chunkID).contains(userID)) {
            chunkIdToUserIds.get(chunkID).add(userID);
            userIdToChunkIds.get(userID).add(chunkID);
        }

        return result;
    }
}

// Your FileSharing object will be instantiated and called as such:
// FileSharing obj = new FileSharing(m);
// int param_1 = obj.join(ownedChunks);
// obj.leave(userID);
// List<Integer> param_3 = obj.request(userID,chunkID);