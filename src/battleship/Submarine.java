package battleship;

public class Submarine extends Ship {
    /**
     * constructor, sets length param
     */
    public Submarine(){
        setLength(1);
    }

    /**
     * @return type of ship
     */
    @Override
    public String getShipType() {
        return "submarine";
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
        return 1;
    }
}
