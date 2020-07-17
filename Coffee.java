package jb_coffee_machine;

enum Coffee {
    ESPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPPUCCINO(200, 100, 12, 1, 6);

    final int water;
    final int milk;
    final int coffeeBeans;
    final int disposableCups;
    final int cost;

    Coffee(int water, int milk, int coffeeBeans, int disposableCups, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.disposableCups = disposableCups;
        this.cost = cost;
    }
}
