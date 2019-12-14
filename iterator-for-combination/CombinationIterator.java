import java.util.Arrays;
import java.util.stream.IntStream;

public class CombinationIterator {
	String characters;
	int[] indices;

	public CombinationIterator(String characters, int combinationLength) {
		this.characters = characters;
		indices = IntStream.range(0, combinationLength).toArray();
	}

	public String next() {
		String result = Arrays.stream(indices).mapToObj(characters::charAt)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();

		if (indices[0] == characters.length() - indices.length) {
			indices = null;
		} else {
			int pos = indices.length - 1;
			while (indices[pos] == characters.length() - (indices.length - pos)) {
				pos--;
			}

			indices[pos]++;
			for (int i = pos + 1; i < indices.length; i++) {
				indices[i] = indices[i - 1] + 1;
			}
		}

		return result;
	}

	public boolean hasNext() {
		return indices != null;
	}
}

// Your CombinationIterator object will be instantiated and called as such:
// CombinationIterator obj = new CombinationIterator(characters,
// combinationLength);
// String param_1 = obj.next();
// boolean param_2 = obj.hasNext();
