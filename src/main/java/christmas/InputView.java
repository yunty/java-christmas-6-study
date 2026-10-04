package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;
import christmas.exception.InputException;

public class InputView {
    public int readDate() {
        String input = Console.readLine();
        if (!input.matches("[0-9]+")) {
            throw new InputException(ErrorMessage.INVALID_DATE);
        }
        return parseDate(input);
    }

    private int parseDate(String input) {
        try {
            int day = Integer.parseInt(input);
            if (day < 1 || day > 31) {
                throw new InputException(ErrorMessage.INVALID_DATE);
            }
            return day;
        } catch (NumberFormatException e) {
            throw new InputException(ErrorMessage.INVALID_DATE);
        }
    }

    public String readOrders() {
        return Console.readLine();
    }
}
