import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String rankTeams(String[] votes) {
        Map<Character, Team> nameToTeam = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); ++i) {
                char name = vote.charAt(i);
                if (!nameToTeam.containsKey(name)) {
                    nameToTeam.put(name, new Team(name, new int[vote.length()]));
                }

                ++nameToTeam.get(name).counts[i];
            }
        }

        return nameToTeam.values().stream().sorted((team1, team2) -> {
            for (int i = 0; i < team1.counts.length; ++i) {
                if (team1.counts[i] != team2.counts[i]) {
                    return -Integer.compare(team1.counts[i], team2.counts[i]);
                }
            }

            return Character.compare(team1.name, team2.name);
        }).map(team -> team.name).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}

class Team {
    char name;
    int[] counts;

    Team(char name, int[] counts) {
        this.name = name;
        this.counts = counts;
    }
}