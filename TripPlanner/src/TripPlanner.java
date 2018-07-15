import java.util.Scanner;

public class TripPlanner {
    public static void main(String[] args){
        System.out.println("Welcome to Vacation Planner!");
        greeting();
        travel();
        time();
        area();
    }

    public static void greeting(){
        System.out.print("What is your name? ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.print("Nice to meet you " + name + ", where are you travelling to? ");
        String city = input.nextLine();
        System.out.println("Great! " + city + " sounds like a great trip.");
        System.out.println("**********\n");
    }

    public static void travel(){
        System.out.print("How many days are you going to spend travelling? ");
        Scanner input = new Scanner(System.in);
        Integer days = input.nextInt();
        System.out.print("How much money, in USD, are you planning to spend on your trip? ");
        Double money = input.nextDouble();
        Double roundedMoney = roundDecimals(money);
        System.out.print("What is the three letter currency symbol for your travel destination? ");
        String symbol = input.next();
        System.out.print("How many " + symbol + " are there in 1 USD? ");
        Double exchange = input.nextDouble();
        Double currencyMoney = roundDecimals(money/exchange);
        System.out.println("If you are traveling for " + days + " days, that is the same as " + days * 24 +
                " hours or " + days * 24 * 60 + " minutes.");
        System.out.println("If you are going to spend $" + roundedMoney + ", that means per day you can spend up to $" +
                currencyMoney + " USD.");
        Double budget = roundDecimals(money * exchange);
        Double budgetPerDay = roundDecimals(budget / days);
        System.out.println("Your total budget is " + budget + " " + symbol + ", which per day is " +
                budgetPerDay + " " + symbol + ".");
        System.out.println("**********\n");
    }

    public static void time(){
        System.out.print("What is the time difference, in hours, between your home and your destination? ");
        Scanner input = new Scanner(System.in);
        Integer time = input.nextInt();
        System.out.println("That means that when it is midnight at home, it will be " + (24 + time) % 24 + ":00 in your " +
                "travel destination, and when it is noon at home it will be " + (12 + time) % 24 + ":00.");
        System.out.println("**********\n");
    }

    public static void area(){
        System.out.print("What is the square area of your destination country in km2? ");
        Scanner input = new Scanner(System.in);
        Double area = input.nextDouble();
        Double inMiles = area * 0.386102;
        System.out.println("In miles2 that is " + roundDecimals(inMiles) + ".");
    }

    public static Double roundDecimals(Double num){
        return (int)(num * 100) / 100.0;
    }
}
