import java.util.HashMap;
import java.util.Map;

class AuthenticationManager {
  int timeToLive;
  Map<String, Integer> tokenIdToExpirationTime = new HashMap<>();

  public AuthenticationManager(int timeToLive) {
    this.timeToLive = timeToLive;
  }

  public void generate(String tokenId, int currentTime) {
    tokenIdToExpirationTime.put(tokenId, currentTime + timeToLive);
  }

  public void renew(String tokenId, int currentTime) {
    if (tokenIdToExpirationTime.getOrDefault(tokenId, -1) <= currentTime) {
      tokenIdToExpirationTime.remove(tokenId);
    }

    if (tokenIdToExpirationTime.containsKey(tokenId)) {
      generate(tokenId, currentTime);
    }
  }

  public int countUnexpiredTokens(int currentTime) {
    return (int) tokenIdToExpirationTime.values().stream().filter(t -> t > currentTime).count();
  }
}

// Your AuthenticationManager object will be instantiated and called as such:
// AuthenticationManager obj = new AuthenticationManager(timeToLive);
// obj.generate(tokenId,currentTime);
// obj.renew(tokenId,currentTime);
// int param_3 = obj.countUnexpiredTokens(currentTime);
