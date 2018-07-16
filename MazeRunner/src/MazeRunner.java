import java.util.*;

public class MazeRunner {
    public static void main(String[] args) {
        Maze myMap = new Maze();
        int count = 0;
        intro(myMap);
        while (!myMap.didIWin() && count < 100){
            printMap(myMap);
            String position = userMover(myMap);
            boolean valid = checkMove(myMap, position);
            if (valid){
                move(myMap, position);
            }
            count++;
            movesMessages(count);
        }
        if (count >= 100){
            System.out.println("Sorry, but you didn't escape in time- you lose!");
        } else System.out.println("Congratulations, you made it out alive!");
        System.out.println("You did it in " + count + " moves.");
    }

    public static void intro(Maze myMap) {
        System.out.println("Welcome to Maze Runner!");
    }

    public static String userMover(Maze myMap){
        Scanner input = new Scanner(System.in);
        String[] options = {"R", "L", "U", "D"};
        System.out.print("Where would you like to move? (R, L, U, D) ");
        String position = input.next().toUpperCase();
        while (!Arrays.asList(options).contains(position)){
            System.out.println("Incorrect position, please choose again (R, L, U, D): ");
            position = input.next().toUpperCase();
        }
        return position;
    }

    public static boolean checkMove(Maze myMap, String position) {
        boolean validMove;
        if (position.equals("R")) {
            validMove = myMap.canIMoveRight();
        } else if (position.equals("L")) {
            validMove = myMap.canIMoveLeft();
        } else if (position.equals("U")) {
            validMove = myMap.canIMoveUp();
        } else validMove = myMap.canIMoveDown();
        if (!validMove && myMap.isThereAPit(position)){
            navigatePit(myMap, position);
        } else if (!validMove){
            System.out.println("Sorry, you've hit a wall.");
        }
        return validMove;
    }

    public static void navigatePit(Maze myMap, String direction){
        Scanner input = new Scanner(System.in);
        System.out.println("Watch out! There's a pit ahead, jump it? ");
        String response = input.next().toLowerCase();
        if (response.startsWith("y")){
            myMap.jumpOverPit(direction);
        }
    }

    public static void move(Maze myMap, String direction){
        if (direction.equals("R")){
            myMap.moveRight();
        } else if (direction.equals("L")){
            myMap.moveLeft();
        } else if (direction.equals("U")){
            myMap.moveUp();
        } else myMap.moveDown();
    }

    public static void printMap(Maze myMap){
        System.out.println("Here is your current position:");
        myMap.printMap();
        System.out.println("----------------------------------------");
    }

    public static void movesMessages(int count){
        if (count == 50){
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes.");
        } else if (count == 75){
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if (count == 90){
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        } else if (count == 100){
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
        }
    }
}