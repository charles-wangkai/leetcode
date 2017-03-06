import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncodeAndDecodeTinyURL {
	static final String DOMAIN = "http://my_short_url.com/";

	List<String> longUrls = new ArrayList<String>();
	Map<String, Integer> longUrl2index = new HashMap<String, Integer>();

	// Encodes a URL to a shortened URL.
	public String encode(String longUrl) {
		if (!longUrl2index.containsKey(longUrl)) {
			longUrls.add(longUrl);
			longUrl2index.put(longUrl, longUrls.size() - 1);
		}

		return toShortUrl(longUrl2index.get(longUrl));
	}

	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl) {
		return longUrls.get(toIndex(shortUrl));
	}

	String toShortUrl(int index) {
		return DOMAIN + index;
	}

	int toIndex(String shortUrl) {
		return Integer.parseInt(shortUrl.substring(shortUrl.lastIndexOf('/') + 1));
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));