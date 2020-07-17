package jb_coffee_machine;

import java.util.Scanner;

/**
 *  Coffee machine simulator.
 *  The machine works with typical products: coffee, milk, sugar, and plastic cups;
 *  if it runs out of something, it shows a notification.
 */
public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;
    private boolean switchOn = true;

    /**
     * @param water
     * @param milk
     * @param coffeeBeans
     * @param disposableCups
     * @param money
     */
    public CoffeeMachine(int water, int milk, int coffeeBeans, int disposableCups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.disposableCups = disposableCups;
        this.money = money;
    }

    /**
     * Run coffee machine
     */
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

    /**
     * Actions available to the customer
     * @return Action selected by customer
     */
    private Action action() {
        System.out.print("Write action (buy, fill, take, remaining, exit):\n> ");
        final String customChoice = customInput().toUpperCase();
        return isCustomActionChoiceValid(customChoice) ? Action.valueOf(customChoice) : Action.UNKNOWN;
    }

    /**
     * Validating a custom action choice
     * @param choice custom action choice
     * @return true, if a choice is valid, else false
     */
    private boolean isCustomActionChoiceValid(String choice) {
        boolean isValidChoice = false;
        for (Action aciton : Action.values()) {
            if (choice.equals(aciton.name())) {
                isValidChoice = true;
                break;
            }
        }
        return isValidChoice;
    }

    /**
     * Buying coffee
     */
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

    /**
     * Buying Espresso
     */
    private void buyEspresso() {
        if (makeCoffee(Coffee.ESPRESSO)) {
            money += Coffee.ESPRESSO.cost;
        }
    }

    /**
     * Buying Latte
     */
    private void buyLatte() {
        if (makeCoffee(Coffee.LATTE)) {
            money += Coffee.LATTE.cost;
        }
    }

    /**
     * Buying Cappuccino
     */
    private void buyCappuccino() {
        if (makeCoffee(Coffee.CAPPUCCINO)) {
            money += Coffee.CAPPUCCINO.cost;
        }
    }

    /**
     * Buying unknown coffee
     */
    private void buyUnknownCoffee() {
        System.out.println();
        System.out.println("I can't make coffee");
        System.out.println();
    }

    /**
     * Making coffee
     * @param coffee kind of coffee
     * @return true, if coffee has made, else false
     */
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

    /**
     * Checking if there are enough ingredients
     * @param coffee kind of coffee
     * @return true, if ingredients is enough, else false
     */
    private boolean haveEnoughIngredients(Coffee coffee) {
        return water >= coffee.water && milk >= coffee.milk && coffeeBeans >= coffee.coffeeBeans && disposableCups >= coffee.disposableCups;
    }

    /**
     * Filling coffee machine
     */
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

    /**
     * Taking money
     */
    private void take() {
        System.out.println();
        System.out.println("I gave you $" + money);
        System.out.println();
        money = 0;
    }

    /**
     * Displaying residue
     */
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

    /**
     * Unknown action
     */
    private void unknownAction() {
        System.out.println();
    }

    /**
     * Custon input
     * @return the string entered by the user
     */
    private String customInput() {
        return new Scanner(System.in).nextLine().trim();
    }

    /**
     * Switch off coffee machine
     */
    private void exit() {
        switchOn = false;
    }
}
