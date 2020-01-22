package battleship;

public class EmptySea extends Ship {
    /**
     * constructor, sets length param
     */
    public EmptySea(){
        setLength(1);
    }
    private boolean _isHit = false;
    @Override
    public boolean shootAt(int row, int column) {
        _isHit = true;
        return false;
    }

    @Override
    public boolean isSunk() {
        _isHit = true;
        return false;
    }

    /**
     * @return "-" or "." if the area is shot or not shot yet
     */
    @Override
    public String toString() {
        if (_isHit)
            return "-";
        else
            return ".";
    }

    /**
     * @return the length of the "Empty sea" = 1
     */
    @Override
    public int getLength() {
        return 1;
    }
}
