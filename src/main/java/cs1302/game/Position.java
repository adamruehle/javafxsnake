package cs1302.game;

/**
 * This class represents each {@code Position} on the {@code Board}.
 * Every {@code Position} has an {@code x} and {@code y} and value
 * describing its location on the {@code Board}.
 */
public class Position {

    private final int x;
    private final int y;

    /**
     * Constructs a {@code Position} with an associate x and y value.
     *
     * @param x the specified x value
     * @param y the specified y value
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    } // Position

    /**
     * Returns the x value of this {@code Position}.
     *
     * @return the x value of this {@code Position}
     */
    public int getX() {
        return x;
    } // getX

    /**
     * Returns the y value of this {@code Position}.
     *
     * @return the y value of this {@code Position}
     */
    public int getY() {
        return y;
    } // getY

    /**
     * Returns true if the {@code x} and {@code y} values of
     * the {@code other} {@code Position} equals that of this
     * {@code Position}.
     *
     * @param other the spcified {@code Position} to be compared
     * @return true if this x and y values equal the x and y values
     *         of the {@code other} {@code Position}; false otherwise
     */
    public boolean equals(Position other) {
        return x == other.x && y == other.y;
    } // equals

} // Board Cell
