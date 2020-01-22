package battleship;

public class Cruiser extends Ship {
    /**
     * constructor, sets length param
     */
    public Cruiser(){
        setLength(3);
    }

    /**
     * @return type of ship
     */
    @Override
    public String getShipType() {
        return "cruiser";
    }

    /**
     * @return "s" or "x" if the ship is hit or sunk
     */
    @Override
    public String toString() {
        if (this.isSunk())
            return "x";
        else
            return "s";
    }

    /**
     * @return the length of the ship
     */
    @Override
    public int getLength() {
        return 3;
    }
}
