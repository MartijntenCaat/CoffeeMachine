package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private Scanner scanner;

    private int currentMlOfWater;
    private int currentMlOfMilk;
    private int currentGramOfBeans;
    private int currentNumberOfCups;
    private int currentChange;
    private boolean isOnline;

    public CoffeeMachine(boolean isOnline) {
        this.scanner = new Scanner(System.in);
        this.currentMlOfWater = 400;
        this.currentMlOfMilk = 540;
        this.currentGramOfBeans = 120;
        this.currentNumberOfCups = 9;
        this.currentChange = 550;
        this.isOnline = true;
    }

    public int getCurrentMlOfWater() {
        return currentMlOfWater;
    }

    public int getCurrentMlOfMilk() {
        return currentMlOfMilk;
    }

    public int getCurrentGramOfBeans() {
        return currentGramOfBeans;
    }

    public int getCurrentNumberOfCups() {
        return currentNumberOfCups;
    }

    public int getCurrentChange() {
        return currentChange;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setCurrentMlOfWater(int water) {
        currentMlOfWater += water;
    }

    public void setCurrentMlOfMilk(int milk) {
        currentMlOfMilk += milk;
    }

    public void setCurrentGramOfBeans(int beans) {
        currentGramOfBeans += beans;
    }

    public void setCurrentNumberOfCups(int cups) {
        currentNumberOfCups += cups;
    }

    public void setCurrentChange(int change) {
        currentChange -= change;
    }

    public void setIsOline(boolean state){
        isOnline = state;
    }

    public String nextAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        return scanner.nextLine();
    }

    public void brewCoffee(int water, int milk, int beans, int cups, int change) {
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

    public void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        setCurrentMlOfWater(scanner.nextInt());

        System.out.println("Write how many ml of milk do you want to add:");
        setCurrentMlOfMilk(scanner.nextInt());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        setCurrentGramOfBeans(scanner.nextInt());

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        setCurrentNumberOfCups(scanner.nextInt());
    }

    public void buy() {

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String buyAction = scanner.nextLine();

        switch (buyAction) {
            case "1": // For the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
                brewCoffee(250, 0, 16, 1, 4);
                break;
            case "2": // For the latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
                brewCoffee(350, 75, 20, 1, 7);
                break;
            case "3": // And for the cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.
                brewCoffee(200, 100, 12, 1, 6);
                break;
            case "back":
                break;
            default:
                break;
        }
    }

    public void take() {
        System.out.println("I gave you $" + getCurrentChange());
        setCurrentChange(getCurrentChange());
    }

    public void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println(getCurrentMlOfWater() + " of water");
        System.out.println(getCurrentMlOfMilk() + " of milk");
        System.out.println(getCurrentGramOfBeans() + " of coffee beans");
        System.out.println(getCurrentNumberOfCups() + " of disposable cups");
        System.out.println("$" + getCurrentChange() + " of money");
    }

    public void exit(){
        setIsOline(false);
        scanner.close();
    }

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine(true);

        while (coffeeMachine.getIsOnline()) {

            switch (coffeeMachine.nextAction()) {
                case "buy":
                    coffeeMachine.buy();
                    break;

                case "fill":
                    coffeeMachine.fill();
                    break;

                case "take":
                    coffeeMachine.take();
                    break;

                case "remaining":
                    coffeeMachine.remaining();
                    break;

                case "exit":
                    coffeeMachine.exit();
                    break;

                default:
                    break;

            }
        }

    }
}
