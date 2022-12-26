import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class VideoSharingPlatform {
  PriorityQueue<Integer> availableIds = new PriorityQueue<>();
  Map<Integer, Video> idToVideo = new HashMap<>();

  VideoSharingPlatform() {
    availableIds.offer(0);
  }

  public int upload(String video) {
    int id = availableIds.poll();
    if (availableIds.isEmpty()) {
      availableIds.offer(id + 1);
    }

    idToVideo.put(id, new Video(video, 0, 0, 0));

    return id;
  }

  public void remove(int videoId) {
    if (idToVideo.containsKey(videoId)) {
      idToVideo.remove(videoId);
      availableIds.offer(videoId);
    }
  }

  public String watch(int videoId, int startMinute, int endMinute) {
    if (!idToVideo.containsKey(videoId)) {
      return "-1";
    }

    Video video = idToVideo.get(videoId);
    ++video.viewCount;

    return video.content.substring(
        startMinute, Math.min(endMinute, video.content.length() - 1) + 1);
  }

  public void like(int videoId) {
    if (idToVideo.containsKey(videoId)) {
      ++idToVideo.get(videoId).likeCount;
    }
  }

  public void dislike(int videoId) {
    if (idToVideo.containsKey(videoId)) {
      ++idToVideo.get(videoId).dislikeCount;
    }
  }

  public int[] getLikesAndDislikes(int videoId) {
    return idToVideo.containsKey(videoId)
        ? new int[] {idToVideo.get(videoId).likeCount, idToVideo.get(videoId).dislikeCount}
        : new int[] {-1};
  }

  public int getViews(int videoId) {
    return idToVideo.containsKey(videoId) ? idToVideo.get(videoId).viewCount : -1;
  }
}

class Video {
  String content;
  int viewCount;
  int likeCount;
  int dislikeCount;

  Video(String content, int viewCount, int likeCount, int dislikeCount) {
    this.content = content;
    this.viewCount = viewCount;
    this.likeCount = likeCount;
    this.dislikeCount = dislikeCount;
  }
}

// Your VideoSharingPlatform object will be instantiated and called as such:
// VideoSharingPlatform obj = new VideoSharingPlatform();
// int param_1 = obj.upload(video);
// obj.remove(videoId);
// String param_3 = obj.watch(videoId,startMinute,endMinute);
// obj.like(videoId);
// obj.dislike(videoId);
// int[] param_6 = obj.getLikesAndDislikes(videoId);
// int param_7 = obj.getViews(videoId);
