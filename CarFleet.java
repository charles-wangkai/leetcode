import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarFleet {
	public int carFleet(int target, int[] position, int[] speed) {
		List<Car> cars = IntStream.range(0, position.length).mapToObj(i -> new Car(position[i], speed[i]))
				.sorted((car1, car2) -> Integer.compare(car2.position, car1.position)).collect(Collectors.toList());

		int headDistance = 0;
		int headSpeed = 1;

		int result = 0;
		for (Car car : cars) {
			int distance = target - car.position;
			if ((long) distance * headSpeed > (long) headDistance * car.speed) {
				result++;

				headDistance = distance;
				headSpeed = car.speed;
			}
		}
		return result;
	}
}

class Car {
	int position;
	int speed;

	Car(int position, int speed) {
		this.position = position;
		this.speed = speed;
	}
}