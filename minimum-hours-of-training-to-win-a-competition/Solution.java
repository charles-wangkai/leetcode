class Solution {
  public int minNumberOfHours(
      int initialEnergy, int initialExperience, int[] energy, int[] experience) {
    int result = 0;
    int currentEnergy = initialEnergy;
    int currentExperience = initialExperience;
    for (int i = 0; i < energy.length; ++i) {
      int neededEnergy = Math.max(energy[i] + 1, currentEnergy);
      result += neededEnergy - currentEnergy;
      currentEnergy = neededEnergy - energy[i];

      int neededExperience = Math.max(experience[i] + 1, currentExperience);
      result += neededExperience - currentExperience;
      currentExperience = neededExperience + experience[i];
    }

    return result;
  }
}