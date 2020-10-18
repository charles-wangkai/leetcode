class Solution {
  public int[] bestCoordinate(int[][] towers, int radius) {
    int bestX = -1;
    int bestY = -1;
    int bestNetworkQuality = -1;
    for (int x = 0; x <= 50; ++x) {
      for (int y = 0; y <= 50; ++y) {
        int networkQuality = 0;
        for (int[] tower : towers) {
          if ((tower[0] - x) * (tower[0] - x) + (tower[1] - y) * (tower[1] - y)
              <= radius * radius) {
            networkQuality +=
                Math.floor(
                    tower[2]
                        / (1
                            + Math.sqrt(
                                (tower[0] - x) * (tower[0] - x)
                                    + (tower[1] - y) * (tower[1] - y))));
          }
        }

        if (networkQuality > bestNetworkQuality) {
          bestNetworkQuality = networkQuality;
          bestX = x;
          bestY = y;
        }
      }
    }

    return new int[] {bestX, bestY};
  }
}
