package christmas;

import christmas.event.UserBenefit;
import christmas.exception.InputException;

public class Controller {
    private final InputView inputView = new InputView();
    private final OuputView outputView = new OuputView();

    public void run() {
        outputView.printStartComment();
        int day = readDate();
        User user = readUser(day);
        printResult(user, day);
    }

    private int readDate() {
        while (true) {
            outputView.printGetDayComment();
            try {
                return inputView.readDate();
            } catch (InputException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private User readUser(int day) {
        while (true) {
            outputView.printGetMenuComment();
            try {
                return User.of(inputView.readOrders(), day);
            } catch (InputException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void printResult(User user, int day) {
        UserBenefit benefit = user.getTotalBenefit();
        outputView.printShowEventComment(day);
        outputView.printUserInputMenu(user.getOrders());
        outputView.printTotalPrice(user.getTotalPrice());
        outputView.printFreeGiven(benefit.getFreeGivenOrder());
        outputView.printBenefits(benefit.getBenefits());
        outputView.printBenefitAmount(benefit.getBenefitAmount());
        outputView.printExpectedPayment(user.getExpectedPayment());
        outputView.printBadge(user.getBadge());
    }
}
