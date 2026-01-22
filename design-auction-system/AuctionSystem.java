import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class AuctionSystem {
  Map<UserAndItem, Integer> userAndItemToAmount = new HashMap<>();
  Map<Integer, SortedMap<Integer, SortedSet<Integer>>> itemIdToBidMap = new HashMap<>();

  public void addBid(int userId, int itemId, int bidAmount) {
    UserAndItem userAndItem = new UserAndItem(userId, itemId);

    if (userAndItemToAmount.containsKey(userAndItem)) {
      removeBid(userId, itemId);
    }

    userAndItemToAmount.put(userAndItem, bidAmount);

    itemIdToBidMap.putIfAbsent(itemId, new TreeMap<>());
    itemIdToBidMap.get(itemId).putIfAbsent(bidAmount, new TreeSet<>());
    itemIdToBidMap.get(itemId).get(bidAmount).add(userId);
  }

  public void updateBid(int userId, int itemId, int newAmount) {
    removeBid(userId, itemId);
    addBid(userId, itemId, newAmount);
  }

  public void removeBid(int userId, int itemId) {
    int amount = userAndItemToAmount.remove(new UserAndItem(userId, itemId));

    itemIdToBidMap.get(itemId).get(amount).remove(userId);
    if (itemIdToBidMap.get(itemId).get(amount).isEmpty()) {
      itemIdToBidMap.get(itemId).remove(amount);
    }
    if (itemIdToBidMap.get(itemId).isEmpty()) {
      itemIdToBidMap.remove(itemId);
    }
  }

  public int getHighestBidder(int itemId) {
    return itemIdToBidMap.containsKey(itemId)
        ? itemIdToBidMap.get(itemId).lastEntry().getValue().last()
        : -1;
  }
}

record UserAndItem(int userId, int itemId) {}

// Your AuctionSystem object will be instantiated and called as such:
// AuctionSystem obj = new AuctionSystem();
// obj.addBid(userId,itemId,bidAmount);
// obj.updateBid(userId,itemId,newAmount);
// obj.removeBid(userId,itemId);
// int param_4 = obj.getHighestBidder(itemId);
