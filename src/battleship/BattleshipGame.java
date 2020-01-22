package battleship;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class BattleshipGame {
    /**
     * Entry point
     * @param args
     */
    public static void main(String[] args) {
        String str = "";
        do{
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            ocean.print();
            String input;
            Scanner scanner = new Scanner(System.in);
            int row;
            int column;
            do{
                do{
                    System.out.println("Enter row");
                    try{
                        input =  scanner.nextLine();
                        row = Integer.parseInt(input);
                        if (row > 9 || row < 0)
                            throw new NumberFormatException();
                    }catch(NumberFormatException ex){
                        System.out.println("Incorrect row");
                        continue;
                    }
                    break;
                }while(true);
                do{
                    System.out.println("Enter column");
                    try{
                        input =  scanner.nextLine();
                        column = Integer.parseInt(input);
                        if (column > 9 || column < 0)
                            throw new NumberFormatException();
                    }catch( NumberFormatException ex){
                        System.out.println("Incorrect column");
                        continue;
                    }
                    break;
                }while(true);
                if (ocean.shootAt(row, column)){
                    if (ocean.getShipArray()[row][column].isSunk())
                        System.out.println(ocean.getShipArray()[row][column].getShipType() + " is sunk!");
                    else
                        System.out.println("Congratulations, you've hit a ship!");
                }
                else{
                    System.out.println("You've missed!");
                }

                ocean.print();
                System.out.println("Ships sunk: " + ocean.getShipsSunk());
                System.out.println("Total shots: " + ocean.getShotsFired());
                System.out.println("Total hits: " + ocean.getHitCount());
                if (ocean.isGameOver()){
                    System.out.println("You won!");
                    System.out.println("Do you want to play again?\nYES - y; NO - any other key");
                }
            }while(!ocean.isGameOver());
            str = scanner.next();
        }while(str.equals("y"));
    }
}
