class ParkingSystem {
  int[] remains;

  public ParkingSystem(int big, int medium, int small) {
    remains = new int[] {-1, big, medium, small};
  }

  public boolean addCar(int carType) {
    if (remains[carType] == 0) {
      return false;
    }

    --remains[carType];

    return true;
  }
}

// Your ParkingSystem object will be instantiated and called as such:
// ParkingSystem obj = new ParkingSystem(big, medium, small);
// boolean param_1 = obj.addCar(carType);
