package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import racingcar.utils.Constants;
import racingcar.validation.ParticipatingCarsValidation;

public class Race {
    private List<Car> participatingCars;
    private int moveCounts;
    public ParticipatingCarsValidation participatingCarsValidation;

    public Race() {
        participatingCars = new ArrayList<>();
        participatingCarsValidation = new ParticipatingCarsValidation();
    }

    public void registerCar(List<String> participatingCars) {
        participatingCarsValidation.validateParticipatingCars(participatingCars);
        registerEachCar(participatingCars);
    }

    private void registerEachCar(List<String> carNames) {
        for (String carName : carNames) {
            this.participatingCars.add(new Car(carName));
        }
    }

    public void registerMoveCounts(int moveCounts) {
        this.moveCounts = moveCounts;
    }

    private int injectFuel() {
        int fuel;
        do {
            fuel = Randoms.pickNumberInRange(Constants.MINIMUM_GENERATE_FUEL_LEVEL,
                    Constants.MAXIMUM_GENERATE_FUEL_LEVEL);
        } while (!isFuelInRange(fuel));
        return fuel;
    }

    private boolean isFuelInRange(int fuel) {
        return fuel >= Constants.MINIMUM_GENERATE_FUEL_LEVEL && fuel <= Constants.MAXIMUM_GENERATE_FUEL_LEVEL;
    }

    public void startCarRacing() {
        validateRaceNotOver();
        conductRace();
        decreaseMoveCounts();
    }

    private void validateRaceNotOver() {
        if (isRaceOver()) {
            throw new IllegalArgumentException("경기가 종료되었습니다.");
        }
    }

    public boolean isRaceOver() {
        return moveCounts <= Constants.MINIMUM_MOVE_COUNTS;
    }

    private void conductRace() {
        for (Car car : participatingCars) {
            car.tryMove(injectFuel());
        }
    }

    private void decreaseMoveCounts() {
        moveCounts--;
    }

    public List<Car> getParticipatingCars() {
        return participatingCars;
    }

    public List<String> calculateWinners() {
        int furthestLocation = findFurthestLocation();
        return getWinnersNames(furthestLocation);
    }

    private int findFurthestLocation() {
        int furthestLocation = 0;
        for (Car car : participatingCars) {
            furthestLocation = updateFurthestLocation(car, furthestLocation);
        }
        return furthestLocation;
    }

    private int updateFurthestLocation(Car car, int currentMaxLocation) {
        return Math.max(car.getLocation(), currentMaxLocation);
    }

    private List<String> getWinnersNames(int furthestLocation) {
        List<String> winnersNames = new ArrayList<>();
        addWinners(furthestLocation, winnersNames);
        return winnersNames;
    }

    private void addWinners(int furthestLocation, List<String> winnersNames) {
        for (Car car : participatingCars) {
            addWinner(car, furthestLocation, winnersNames);
        }
    }

    private void addWinner(Car car, int furthestLocation, List<String> winnersNames) {
        if (isWinner(car, furthestLocation)) {
            winnersNames.add(car.getName());
        }
    }

    private boolean isWinner(Car car, int furthestLocation) {
        return car.getLocation() == furthestLocation;
    }
}