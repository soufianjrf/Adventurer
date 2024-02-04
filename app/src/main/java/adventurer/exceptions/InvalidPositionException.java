package adventurer.exceptions;

//Our custom exception that is thrown when the adventurer's initial position is invalid
public class InvalidPositionException extends IllegalArgumentException {
    public InvalidPositionException(String message) {
        super(message);
    }
}