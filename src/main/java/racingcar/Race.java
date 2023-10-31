package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
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
        for (String carName : participatingCars) {
            this.participatingCars.add(new Car(carName));
        }
    }

    public void registerMoveCounts(int moveCounts) {
        this.moveCounts = moveCounts;
    }

    private int generateFuel() {
        return Randoms.pickNumberInRange(0, 9);
    }
}