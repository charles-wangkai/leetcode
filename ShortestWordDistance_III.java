public class ShortestWordDistance_III {
	public int shortestWordDistance(String[] words, String word1, String word2) {
		return word1.equals(word2) ? findDistance(words, word1)
				: findDistanceForDifferentWords(words, word1, word2);
	}

	int findDistanceForDifferentWords(String[] words, String word1, String word2) {
		int minDistance = Integer.MAX_VALUE;
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				if (index2 >= 0) {
					minDistance = Math.min(minDistance, i - index2);
				}
				index1 = i;
			} else if (words[i].equals(word2)) {
				if (index1 >= 0) {
					minDistance = Math.min(minDistance, i - index1);
				}
				index2 = i;
			}
		}
		return minDistance;
	}

	int findDistance(String[] words, String word) {
		int minDistance = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word)) {
				if (index >= 0) {
					minDistance = Math.min(minDistance, i - index);
				}
				index = i;
			}
		}
		return minDistance;
	}
}
