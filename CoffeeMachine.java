package machine;
import java.util.Scanner;

public class CoffeeMachine {

    public static final int WATER_FOR_ESPRESSO = 250;
    public static final int BEANS_FOR_ESPRESSO = 16;
    public static final int COST_FOR_ESPRESSO = 4;
    public static final int WATER_FOR_LATTE = 350;
    public static final int MILK_FOR_LATTE = 75;
    public static final int BEANS_FOR_LATTE = 20;
    public static final int COST_FOR_LATTE = 7;
    public static final int WATER_FOR_CAPUCCINO = 200;
    public static final int MILK_FOR_CAPUCCINO = 100;
    public static final int BEANS_FOR_CAPUCCINO = 12;
    public static final int COST_FOR_CAPUCCINO = 6;

    static State state = State.CHOOSE_ACTION;
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    public static void recieveString(String usersAnswer){
        switch (state){
            case CHOOSE_ACTION:
                if(usersAnswer.equals("buy")){
                    System.out.println();
                    state = State.CHOOSE_COFFEE;
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                }else if(usersAnswer.equals("fill")){
                    System.out.println();
                    state = State.FILLING_WATER;
                    System.out.println("Write how many ml of water you want to add: ");
                }else if(usersAnswer.equals("take")){
                    System.out.println();
                    System.out.println("I gave you $" + money);
                    money = 0;
                    System.out.println();
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                }else if(usersAnswer.equals("remaining")){
                    System.out.println();
                    System.out.println("The coffee machine has:\n" +
                            water + " ml of water\n" +
                            milk + " ml of milk\n" +
                            beans + " g of coffee beans\n" +
                            cups + " disposable cups\n" +
                            "$" + money + " of money");
                    System.out.println();
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                }else if(usersAnswer.equals("exit")){
                    state = State.EXIT;
                }
                break;
            case CHOOSE_COFFEE:
                switch (usersAnswer){
                    case "1":
                        if (water < WATER_FOR_ESPRESSO || beans<BEANS_FOR_ESPRESSO || cups<1){
                            System.out.println("Not enough resources");
                        }else{
                            water -= WATER_FOR_ESPRESSO;
                            beans -= BEANS_FOR_ESPRESSO;
                            cups --;
                            money += COST_FOR_ESPRESSO;
                            System.out.println("I have enough resources, making you a coffee!");
                        }
                        break;
                    case "2":
                        if (water < WATER_FOR_LATTE || milk < MILK_FOR_LATTE || beans<BEANS_FOR_LATTE || cups<1){
                            System.out.println("Not enough resources");
                        }else{
                            water -= WATER_FOR_LATTE;
                            milk -= MILK_FOR_LATTE;
                            beans -= BEANS_FOR_LATTE;
                            cups --;
                            money += COST_FOR_LATTE;
                            System.out.println("I have enough resources, making you a coffee!");
                        }
                        break;
                    case "3":
                        if (water < WATER_FOR_CAPUCCINO || milk < MILK_FOR_CAPUCCINO || beans<BEANS_FOR_CAPUCCINO || cups<1){
                            System.out.println("Not enough resources");
                        }else{
                            water -= WATER_FOR_CAPUCCINO;
                            milk -= MILK_FOR_CAPUCCINO;
                            beans -= BEANS_FOR_CAPUCCINO;
                            money += COST_FOR_CAPUCCINO;
                            cups --;
                            System.out.println("I have enough resources, making you a coffee!");
                        }
                        break;
                    case "back":
                        break;

                }
                System.out.println();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                state = State.CHOOSE_ACTION;
                break;
            case FILLING_WATER:
                water += Integer.valueOf(usersAnswer);
                System.out.println("Write how many ml of milk you want to add: ");
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                milk += Integer.valueOf(usersAnswer);
                System.out.println("Write how many grams of coffee beans you want to add: ");
                state = State.FILLING_BEANS;
                break;
            case FILLING_BEANS:
                beans += Integer.valueOf(usersAnswer);
                System.out.println("Write how many disposable cups you want to add: ");
                state = State.FILLING_CUPS;
                break;

            case FILLING_CUPS:
                cups += Integer.valueOf(usersAnswer);
                System.out.println();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                state = State.CHOOSE_ACTION;
                break;
        }
    }
    public static void main(String[] args) {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        Scanner scanner = new Scanner(System.in);
        while (state != State.EXIT) {
            String answer = scanner.next();
            recieveString(answer);
        }
        scanner.close();
    }
    public enum State{
        CHOOSE_ACTION, CHOOSE_COFFEE, FILLING_WATER, FILLING_MILK, FILLING_BEANS, FILLING_CUPS, EXIT
    }
}
