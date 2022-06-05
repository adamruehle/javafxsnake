package cs1302.game;

import java.util.LinkedList;

/**
 * A class representing the Snake in a {@code SnakeGame}.
 */
public class Snake {

    /**
     * An enumeration describing the {@code Direction} of the {@code Snake},
     * which can only be up, down, left, right, or none.
     */
    public enum Direction {
        NONE, // no direction
        UP,
        DOWN,
        LEFT,
        RIGHT
    } // Direction enumeration

    private final LinkedList<Position> body; // each Snake body Position
    private Direction direction; // the current direction of the snake
    private int growth; // the current number of Positions to be added at the tail
    private LinkedList<Direction> keyPresses;

    /**
     * Constructs an {@code Snake} containing a {@code LinkedList} with
     * each {@code body} {@code Position}, the current {@code Direction},
     * and the {@code growth} amount.
     *
     * @param head the starting {@code Position} of the {@code Snake}
     */
    public Snake(Position head) {
        this.body = new LinkedList<>();
        this.body.add(head);
        this.direction = Direction.NONE;
        this.keyPresses = new LinkedList<>();
        this.growth = 2; // allow Snake to grow initially
    } // Snake

    /**
     * Returns the head of this {@code Snake}.
     *
     * @return the head of this {@code Snake}
     */
    public Position getHead() {
        return this.body.get(0);
    } // getHead

    /**
     * Returns the {@code body} of this {@code Snake}.
     *
     * @return the {@code body} of this {@code Snake}
     */
    public LinkedList<Position> getBody() {
        return this.body;
    } // getBody

    /**
     * Returns true if the {@code Position} at the specified {@code x} and {@code y} values
     * is currently part of the {@code body} of this {@code Snake}.
     *
     * @param x the specified x value of the {@code Position}
     * @param y the specified y value of the {@code Position}
     * @return true if the x and y values match a body {@code Position}; false otherwise.
     */
    public boolean isBody(int x, int y) {
        for (Position pos : body) {
            if (pos.getX() == x && pos.getY() == y) {
                return true;
            } // if
        } // for
        return false;
    } // isBody

    /**
     * Immediately sets the {@code Direction} of this {@code Snake}, ensuring that the player
     * will not have to hold down the key for the {@code Direction} of this {@code Snake} to
     * change. Also, the player cannot change the {@code Direction} of the {@code Snake} to be
     * opposite to that of its current {@code Snake}.
     *
     * @param direction the specified {@code Direction}
     */
    public void setDirection(Direction direction) {
        if (direction.equals(Direction.DOWN) && this.direction.equals(Direction.UP)) {
            return;
        } else if (direction.equals(Direction.UP) && this.direction.equals(Direction.DOWN)) {
            return;
        } else if (direction.equals(Direction.LEFT) && this.direction.equals(Direction.RIGHT)) {
            return;
        } else if (direction.equals(Direction.RIGHT) && this.direction.equals(Direction.LEFT)) {
            return;
        } // if
        this.direction = direction;
    } // setDirection

    /**
     * Moves this {@code Snake} one {@code Position} in its current
     * {@code Direction}.
     *
     * @param food the current {@code Position} of the food
     * @return true if the head of this {@code Snake} is occupying
     *         the same {@code Position} as the food; false otherwise
     */
    public boolean move(Position food) {
        boolean ateFood = false;
        Position newHead = null;
        Position currHead = getHead();
        switch (direction) {
        case NONE:
            return false;
        case UP:
            newHead = new Position(currHead.getX(), currHead.getY() - 1);
            break;
        case DOWN:
            newHead = new Position(currHead.getX(), currHead.getY() + 1);
            break;
        case LEFT:
            newHead = new Position(currHead.getX() - 1, currHead.getY());
            break;
        case RIGHT:
            newHead = new Position(currHead.getX() + 1, currHead.getY());
            break;
        } // switch
        body.addFirst(newHead); // add the new head in front of the Snake
        if (newHead.equals(food)) {
            growth += 3; // allow the Snake to grow
            ateFood = true;
        } // if
        if (growth > 0) {
            growth--;
        } else {
            body.removeLast(); // remove the tail of the Snake
        } // if
        return ateFood;
    } // move

} // Snake