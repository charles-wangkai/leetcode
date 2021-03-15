import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Codec {
  static final String DOMAIN = "http://my_short_url.com/";

  List<String> longUrls = new ArrayList<String>();
  Map<String, Integer> longUrlToIndex = new HashMap<>();

  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {
    if (!longUrlToIndex.containsKey(longUrl)) {
      longUrlToIndex.put(longUrl, longUrls.size());
      longUrls.add(longUrl);
    }

    return toShortUrl(longUrlToIndex.get(longUrl));
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
    return longUrls.get(toIndex(shortUrl));
  }

  String toShortUrl(int index) {
    return DOMAIN + index;
  }

  int toIndex(String shortUrl) {
    return Integer.parseInt(shortUrl.substring(DOMAIN.length()));
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
