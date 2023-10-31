package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.Car;

public class OutputView {
    public static final String CAR_FORMAT = "%s : %s%n";

    public void printCars(List<Car> participatingCars) {
        String carsOutput = participatingCars.stream()
                .map(car -> String.format(CAR_FORMAT, car.getName(), getCarMovement(car.getLocation())))
                .collect(Collectors.joining());

        System.out.println(carsOutput);
    }

    private String getCarMovement(int location) {
        return "-".repeat(Math.max(0, location));
    }
}
