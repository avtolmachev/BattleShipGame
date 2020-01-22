package battleship;

public class Ship {

    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean [] hit = new boolean[4];

    /**
     * sets length param
     * @param len
     */
    public void setLength(int len){
        length = len;
    }

    /**
     * @return base length, for override
     */
    public int getLength(){
        return 0;
    }

    /**
     * @return bow row
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * @return bow column
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * @return if the ship is horizontal or not
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * sets bow row param
     * @param row
     */
    public void setBowRow(int row){
        bowRow = row;
    }

    /**
     * sets bow column param
     * @param column
     */
    public void setBowColumn(int column){
        bowColumn = column;
    }

    /**
     * sets horizontal param
     * @param horizontal
     */
    public void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    }

    /**
     * @return type of the ship, for override
     */
    public String getShipType(){
        return "";
    }

    /**
     * checks if it is possible to place ship at this location
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return if it is possible to place a ship
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
        if ((horizontal && column + getLength() > 10) || (!horizontal && row + getLength() > 10))
            return false;
        int r, c;
         for (int i = 0; i < getLength(); i++){
             if (!horizontal){
                 r = row + i;
                 c = column;
             }
             else{
                 c = column + i;
                 r = row;
             }
            for (int j = -1; j <= 1; j++){
                 for (int t = -1; t <= 1; t++){
                     try{
                         if (ocean.isOccupied(r + j, c + t))
                            return false;
                     }catch(IndexOutOfBoundsException ex){
                        continue;
                     }
                 }
            }
         }
         return true;
    }

    /**
     * places a ship at this location and initializes bow column, bow row and orientation
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            for (int i = column; i < column + length; i++) {
                ocean.getShipArray()[row][i] = this;
            }
        } else {
            for (int i = row; i < row + length; i++) {
                ocean.getShipArray()[i][column] = this;
            }
        }
        this.setBowColumn(column);
        this.setBowRow(row);
        this.setHorizontal(horizontal);
    }

    /**
     * updates the array of hits
     * @param row
     * @param column
     * @return if the ship is hit
     */
    public boolean shootAt(int row, int column){
        if (this instanceof EmptySea)
            return false;
        else{
            if (isHorizontal()){
                this.hit[column - getBowColumn()] = true;
            }
            else
                this.hit[row - getBowRow()] = true;
            return true;
        }
    }

    /**
     * @return if the ship is sunk
     */
    public  boolean isSunk(){
        for (int i = 0; i < this.getLength(); i++)
            if (hit[i] == false)
                return false;
        return true;
    }

    /**
     * @param row
     * @param column
     * @return if the part of a ship is hit
     */
    public boolean isHit(int row, int column){
        if (hit[row - this.getBowRow() + column - this.getBowColumn()]){
            return true;
        }else
            return false;
    }
}
