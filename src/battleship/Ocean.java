package battleship;

import java.util.Random;

public class Ocean {
    private Ship[][] ships = new Ship[10][10];
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;

    /**
     * constructor
     */
    public Ocean() {
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                ships[i][j] = new EmptySea();
    }

    /**
     * method to place randomly a particular type of ship several time
     * @param type
     */
    private void placeShip(int type){
        Random rnd = new Random();
        int row;
        int column;
        boolean horizontal;
        Ship ship;

        for (int i = 0; i < type; i++){
            do {
                if (type == 1)
                    ship = new Battleship();
                else if (type == 2)
                    ship = new Cruiser();
                else if (type == 3)
                    ship = new Destroyer();
                else
                    ship = new Submarine();
                row = rnd.nextInt(10);
                column = rnd.nextInt(10);
                horizontal = rnd.nextBoolean();
                if (ship.okToPlaceShipAt(row, column, horizontal, this)){
                    ship.placeShipAt(row, column, horizontal, this);
                    break;
                }
            } while (true);
        }
    }

    /**
     * method that calls the method to place ships and gives the amount and type
     */
    public void placeAllShipsRandomly() {
        placeShip(1);
        placeShip(2);
        placeShip(3);
        placeShip(4);
    }

    /**
     * @param row
     * @param column
     * @return if a cell is occupied
     */
    public boolean isOccupied(int row, int column){
        if (ships[row][column] instanceof EmptySea)
            return false;
        else
            return true;
    }

    /**
     * increases shots fired and total hits with total ships sunk if necessary
     * @param row
     * @param column
     * @return if a cell contains a ship and it is not sunk
     */
    public boolean shootAt(int row, int column){
        shotsFired++;
        if (!ships[row][column].isSunk() && ships[row][column].shootAt(row, column)){
            if (ships[row][column].isSunk()) {
                hitCount++;
                shipsSunk++;
            }else
                hitCount++;
            return true;

        }
        else
            return false;
    }

    /**
     * @return total shots fired
     */
    public int getShotsFired(){
        return shotsFired;
    }

    /**
     * @return hits count
     */
    public int getHitCount(){
        return hitCount;
    }

    /**
     * @return total ships sunk
     */
    public int getShipsSunk(){
        return shipsSunk;
    }

    /**
     * @return if the game is over
     */
    public boolean isGameOver(){
        if (shipsSunk == 10)
            return true;
        else
            return false;
    }

    /**
     * @return array of ships
     */
    public Ship[][] getShipArray(){
        return ships;
    }

    /**
     * method for printing ocean with all marks
     */
    public void print(){
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++){

            System.out.print(i + " ");

            for (int j = 0; j < 10; j++){

                if (ships[i][j] instanceof EmptySea || ships[i][j].isHit(i, j)){
                    System.out.print(ships[i][j] + " ");
                }else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}