package jb_coffee_machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;
    private boolean switchOn = true;

    public static void main(String[] args) {
        new CoffeeMachine().run();
    }

    public CoffeeMachine() {
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        disposableCups = 9;
        money = 550;
    }

    public void run() {
        while (switchOn) {
            switch (action()) {
                case BUY:
                    buy();
                    break;
                case FILL:
                    fill();
                    break;
                case TAKE:
                    take();
                    break;
                case REMAINING:
                    residue();
                    break;
                case EXIT:
                    exit();
                    break;
                case UNKNOWN:
                    unknownAction();
                    break;
            }
        }
    }

    private Action action() {
        System.out.print("Write action (buy, fill, take, remaining, exit):\n> ");
        final String customChoice = customInput().toUpperCase();
        return isValidCustomActionChoice(customChoice) ? Action.valueOf(customChoice) : Action.UNKNOWN;
    }

    private boolean isValidCustomActionChoice(String choice) {
        boolean isValidChoice = false;
        for (Action aciton : Action.values()) {
            if (choice.equals(aciton.name())) {
                isValidChoice = true;
                break;
            }
        }
        return isValidChoice;
    }

    private void buy() {
        System.out.println();
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n> ");
        String customCoffeeChoice = customInput();
        if (customCoffeeChoice.equals("back")) {
            System.out.println();
            return;
        }
        switch (Integer.parseInt(customCoffeeChoice)) {
            case 1:
                buyEspresso();
                break;
            case 2:
                buyLatte();
                break;
            case 3:
                buyCappuccino();
                break;
            default:
                buyUnknownCoffee();
                break;
        }
    }

    private void buyEspresso() {
        if (makeCoffee(Coffee.ESPRESSO)) {
            money += Coffee.ESPRESSO.cost;
        }
    }

    private void buyLatte() {
        if (makeCoffee(Coffee.LATTE)) {
            money += Coffee.LATTE.cost;
        }
    }

    private void buyCappuccino() {
        if (makeCoffee(Coffee.CAPPUCCINO)) {
            money += Coffee.CAPPUCCINO.cost;
        }
    }

    private void buyUnknownCoffee() {
        System.out.println();
        System.out.println("I can't make coffee");
        System.out.println();
    }

    private boolean makeCoffee(Coffee coffee) {
        boolean done = false;
        if (haveEnoughIngredients(coffee)) {
            water -= coffee.water;
            milk -= coffee.milk;
            coffeeBeans -= coffee.coffeeBeans;
            disposableCups -= coffee.disposableCups;
            System.out.println("I have enough resources, making you a coffee!");
            done = true;
        } else {
            System.out.println("Sorry, not enough water!");
        }
        System.out.println();
        return done;
    }

    private boolean haveEnoughIngredients(Coffee coffee) {
        return water >= coffee.water && milk >= coffee.milk && coffeeBeans >= coffee.coffeeBeans && disposableCups >= coffee.disposableCups;
    }

    private void fill() {
        System.out.println();
        System.out.print("Write how many ml of water do you want to add:\n> ");
        water += Integer.parseInt(customInput());
        System.out.print("Write how many ml of milk do you want to add:\n> ");
        milk += Integer.parseInt(customInput());
        System.out.print("Write how many grams of coffee beans do you want to add:\n> ");
        coffeeBeans += Integer.parseInt(customInput());
        System.out.print("Write how many disposable cups of coffee do you want to add:\n> ");
        disposableCups += Integer.parseInt(customInput());
        System.out.println();
    }

    private void take() {
        System.out.println();
        System.out.println("I gave you $" + money);
        System.out.println();
        money = 0;
    }

    private void residue() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(disposableCups + " of disposable cups");
        System.out.println("$" + money + " of money");
        System.out.println();
    }

    private void unknownAction() {
        System.out.println();
    }

    private String customInput() {
        return new Scanner(System.in).nextLine().trim();
    }

    private void exit() {
        switchOn = false;
    }
}

//enum Action {
//    BUY, FILL, TAKE, REMAINING, EXIT, UNKNOWN
//}

//enum Coffee {
//    ESPRESSO(250, 0, 16, 1, 4),
//    LATTE(350, 75, 20, 1, 7),
//    CAPPUCCINO(200, 100, 12, 1, 6);
//
//    final int water;
//    final int milk;
//    final int coffeeBeans;
//    final int disposableCups;
//    final int cost;
//
//    Coffee(int water, int milk, int coffeeBeans, int disposableCups, int cost) {
//        this.water = water;
//        this.milk = milk;
//        this.coffeeBeans = coffeeBeans;
//        this.disposableCups = disposableCups;
//        this.cost = cost;
//    }
//}
