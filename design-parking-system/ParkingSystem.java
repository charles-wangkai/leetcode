class ParkingSystem {
  int[] rests;

  public ParkingSystem(int big, int medium, int small) {
    rests = new int[] {-1, big, medium, small};
  }

  public boolean addCar(int carType) {
    if (rests[carType] == 0) {
      return false;
    }

    --rests[carType];

    return true;
  }
}

// Your ParkingSystem object will be instantiated and called as such:
// ParkingSystem obj = new ParkingSystem(big, medium, small);
// boolean param_1 = obj.addCar(carType);
