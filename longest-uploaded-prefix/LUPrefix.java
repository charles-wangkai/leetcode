class LUPrefix {
  boolean[] uploaded;
  int prefix = 0;

  public LUPrefix(int n) {
    uploaded = new boolean[n + 1];
  }

  public void upload(int video) {
    uploaded[video] = true;
  }

  public int longest() {
    while (prefix + 1 != uploaded.length && uploaded[prefix + 1]) {
      ++prefix;
    }

    return prefix;
  }
}

// Your LUPrefix object will be instantiated and called as such:
// LUPrefix obj = new LUPrefix(n);
// obj.upload(video);
// int param_2 = obj.longest();
