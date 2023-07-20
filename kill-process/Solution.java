import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    Map<Integer, List<Integer>> ppid2pids = new HashMap<>();
    for (int i = 0; i < pid.size(); i++) {
      int onePpid = ppid.get(i);

      if (!ppid2pids.containsKey(onePpid)) {
        ppid2pids.put(onePpid, new ArrayList<>());
      }
      ppid2pids.get(onePpid).add(pid.get(i));
    }

    List<Integer> killed = new ArrayList<>();
    search(killed, ppid2pids, kill);
    return killed;
  }

  void search(List<Integer> killed, Map<Integer, List<Integer>> ppid2pids, int ppid) {
    killed.add(ppid);

    if (ppid2pids.containsKey(ppid)) {
      for (int pid : ppid2pids.get(ppid)) {
        search(killed, ppid2pids, pid);
      }
    }
  }
}
