package cs1302.game;

import javafx.geometry.Pos;

import java.util.LinkedList;

/**
 * A class representing the Snake in a {@code SnakeGame}. Each {@code Snake} consists
 * of a LinkedList representing its {@code body}, the current {@code direction} which
 * the {@code Snake} is moving, and an integer {@code growth} amount representing
 * whether the tail of the {@code Snake} should be removed.
 */
public class Snake {

    /**
     * An enumeration describing the {@code Direction} of the {@code Snake}, which can
     * only be up, down, left, right, or none at all.
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

    /**
     * Constructs an {@code Snake} containing a {@code LinkedList} of each {@code body}
     * {@code Position}, the {@code Snake}'s current {@code Direction}, and the
     * {@code growth} amount.
     *
     * @param head the starting {@code Position} of the {@code Snake}
     */
    public Snake(Position head) {
        body = new LinkedList<>();
        body.add(head); // initially, the body only contains the head
        direction = Direction.NONE; // no initial Direction
        growth = 2; // allow Snake to grow initially
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
     * Returns true if the {@code Position} with the specified {@code x} and {@code y} values
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
     * opposite to that of its current {@code Snake}; for example, if the {@code Direction} of
     * the {@code Snake} is {@code LEFT}, the {@code Direction} cannot change to {@code RIGHT}.
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
     * Moves this {@code Snake} one {@code Position} in its current {@code Direction}, then
     * checks if the 'head' {@code Position} is equal to the {@code Position} of the
     * specified {@code food} {@code Position}.
     *
     * @param food the current {@code Position} of the food
     * @return true if the 'head' of this {@code Snake} is currently occupying the same
     *         {@code Position} as the food; false otherwise
     */
    public boolean move(Position food) {
        boolean ateFood = false;
        Position newHead = changeHead(); // find new head
        if (newHead == null) {
            return false; // do nothing if Direction is NONE
        } // if
        body.addFirst(newHead); // add the new head to the Snake body LinkedList
        if (newHead.equals(food)) {
            growth += 3; // allow the Snake to grow
            ateFood = true;
        } // if
        if (growth > 0) {
            growth--; // decrement body growth each time the Snake body grows
        } else {
            body.removeLast(); // remove the tail of the Snake body if Snake should not grow
        } // if
        return ateFood;
    } // move

    /**
     * Returns the new 'head' {@code Position} for this {@code Snake} given the current
     * {@code Direction} of this {@code Snake} and the current {@code Position} of the
     * 'head' of this {@code Snake}.
     *
     * @return the {@code Position} of the new 'head' for this {@code Snake}
     */
    private Position changeHead() {
        Position currHead = this.getHead();
        switch (direction) {
            case NONE:
                return null;
            case UP:
                return new Position(currHead.getX(), currHead.getY() - 1);
            case DOWN:
                return new Position(currHead.getX(), currHead.getY() + 1);
            case LEFT:
                return new Position(currHead.getX() - 1, currHead.getY());
            case RIGHT:
                return new Position(currHead.getX() + 1, currHead.getY());
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        } // switch
    } // changeHead

} // Snake