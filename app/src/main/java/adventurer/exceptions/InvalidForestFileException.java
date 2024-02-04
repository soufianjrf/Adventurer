package adventurer.exceptions;

//Custom exception for invalid map files
public class InvalidForestFileException extends IllegalArgumentException {
    public InvalidForestFileException(String message) {
        super(message);
    }

}
