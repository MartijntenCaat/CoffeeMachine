package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private Scanner scanner;

    private int currentMlOfWater;
    private int currentMlOfMilk;
    private int currentGramOfBeans;
    private int currentNumberOfCups;
    private int currentChange;
    private boolean isOnline = true;

    private CoffeeMachine() {
        this.scanner = new Scanner(System.in);
        this.currentMlOfWater = 400;
        this.currentMlOfMilk = 540;
        this.currentGramOfBeans = 120;
        this.currentNumberOfCups = 9;
        this.currentChange = 550;
    }

    private int getCurrentMlOfWater() {
        return currentMlOfWater;
    }

    private int getCurrentMlOfMilk() {
        return currentMlOfMilk;
    }

    private int getCurrentGramOfBeans() {
        return currentGramOfBeans;
    }

    private int getCurrentNumberOfCups() {
        return currentNumberOfCups;
    }

    private int getCurrentChange() {
        return currentChange;
    }

    private boolean IsOnline() {
        return isOnline;
    }

    private void addWater(int water) {
        currentMlOfWater += water;
    }

    private void addMilk(int milk) {
        currentMlOfMilk += milk;
    }

    private void addBeans(int beans) {
        currentGramOfBeans += beans;
    }

    private void addCups(int cups) {
        currentNumberOfCups += cups;
    }

    private void subtractChange(int change) {
        currentChange -= change;
    }

    private void setOffline() {
        isOnline = false;
    }

    private String nextAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        return scanner.nextLine();
    }

    private void makeCoffee(int water, int milk, int beans, int cups, int change) {
        if (getCurrentMlOfWater() - water < 0) {
            System.out.println("Sorry, not enough water!");
        } else if (getCurrentMlOfMilk() - milk < 0) {
            System.out.println("Sorry, not enough milk!");
        } else if (getCurrentGramOfBeans() - beans < 0) {
            System.out.println("Sorry, not enough beans!");
        } else if (getCurrentNumberOfCups() - cups < 0) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            currentMlOfWater -= water;
            currentMlOfMilk -= milk;
            currentGramOfBeans -= beans;
            currentNumberOfCups -= cups;
            currentChange += change;
        }
    }

    private void fillCoffeeMachine() {
        System.out.println("Write how many ml of water do you want to add:");
        addWater(scanner.nextInt());

        System.out.println("Write how many ml of milk do you want to add:");
        addMilk(scanner.nextInt());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        addBeans(scanner.nextInt());

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        addCups(scanner.nextInt());
    }

    private void buyCoffee() {

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String buyAction = scanner.nextLine();

        switch (buyAction) {
            case "1": // For the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
                makeCoffee(250, 0, 16, 1, 4);
                break;
            case "2": // For the latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
                makeCoffee(350, 75, 20, 1, 7);
                break;
            case "3": // And for the cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.
                makeCoffee(200, 100, 12, 1, 6);
                break;
            default:
                break;
        }
    }

    private void takeChangeFromMachine() {
        System.out.println("I gave you $" + getCurrentChange());
        subtractChange(getCurrentChange());
    }

    private void printRemainingStock() {
        System.out.println("The coffee machine has:");
        System.out.println(getCurrentMlOfWater() + " of water");
        System.out.println(getCurrentMlOfMilk() + " of milk");
        System.out.println(getCurrentGramOfBeans() + " of coffee beans");
        System.out.println(getCurrentNumberOfCups() + " of disposable cups");
        System.out.println("$" + getCurrentChange() + " of money");
    }

    private void shutdownCoffeeMachine() {
        setOffline();
        scanner.close();
    }

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (coffeeMachine.IsOnline()) {

            switch (coffeeMachine.nextAction()) {
                case "buy":
                    coffeeMachine.buyCoffee();
                    break;

                case "fill":
                    coffeeMachine.fillCoffeeMachine();
                    break;

                case "take":
                    coffeeMachine.takeChangeFromMachine();
                    break;

                case "remaining":
                    coffeeMachine.printRemainingStock();
                    break;

                case "exit":
                    coffeeMachine.shutdownCoffeeMachine();
                    break;

                default:
                    break;

            }
        }

    }
}
