package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import racingcar.validation.CarInputValidation;
import racingcar.validation.MoveCountsValidation;


public class InputView {
    CarInputValidation carInputValidation = new CarInputValidation();
    MoveCountsValidation moveCountsValidation = new MoveCountsValidation();

    public List<String> getCarNamesInput() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carInput = Console.readLine();
        carInputValidation.validateCarInput(carInput);
        String[] carsList = carInput.split(",");
        return Arrays.asList(carsList);
    }

    public int getMoveCountsInput() {
        System.out.println("시도할 회수는 몇회인가요?");
        String MoveCountsInput = Console.readLine();
        moveCountsValidation.validateMoveCountsInput(MoveCountsInput);
        return Integer.parseInt(MoveCountsInput);
    }
}
