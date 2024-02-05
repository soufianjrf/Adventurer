package adventurer.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventurer.exceptions.InvalidPositionException;
import adventurer.geometry.Point;

public class Adventurer {

    // The position of the adventurer
    private Point coordinates;

    // The forest where he will be
    private Forest forest;

    public Adventurer(Point initialPosition, Forest forest) {
        this.forest = forest;

        int x = initialPosition.getX(), y = initialPosition.getY();

        // Throws InvalidPositionException if the Adventurer couldn't be placed within
        // the map (invalid coordinates, or tree in position)
        if (y < 0 || y >= forest.getMap().length) {
            throw new InvalidPositionException("Coordinate y out of forest range");
        } else if (x < 0 || x >= forest.getMap()[y].length) {
            throw new InvalidPositionException("Coordinate x out of forest range");
        } else if (forest.getMap()[y][x] == '#') {
            throw new InvalidPositionException("The initial position in the forest is occupied by a tree");
        }
        coordinates = initialPosition;
    }

    public Adventurer(int x, int y, Forest forest) {
        this(new Point(x, y), forest);
    }

    // returns true if there is no obstacle, false otherwise
    public boolean canAdvance(char direction) {
        int x = coordinates.getX(), y = coordinates.getY();
        boolean result = true;

        // Depending on the direction, we check the availability of the next position
        switch (direction) {
            case 'O':
                // the direction is Ouest(West) "<-", the x-1 needs to be defined and empty
                if (x == 0 || forest.getMap()[y][x - 1] == '#') {
                    result = false;
                }
                break;
            case 'E':
                // the direction is East "->", the x+1 needs to be defined and empty
                if (x == forest.getMap()[y].length - 1 || forest.getMap()[y][x + 1] == '#') {
                    result = false;
                }
                break;
            case 'S':
                // the direction is South "down", the y+1 needs to be defined and empty
                if (y == forest.getMap().length - 1 || forest.getMap()[y + 1][x] == '#') {
                    result = false;
                }
                break;
            case 'N':
                // the direction is Ouest(West) "up", the y-1 needs to be defined and empty
                if (y == 0 || forest.getMap()[y - 1][x] == '#') {
                    result = false;
                }
                break;
        }
        return result;
    }

    // moves the adventurer into his new position, avoiding obstacles, following the
    // String of directions .. ex: "SSOOSSSSE"
    public void advance(String directions) {

        // this map will contain the coordinates updates depending on the direction
        Map<Character, List<Integer>> coordinateUpdates = new HashMap<>();
        coordinateUpdates.put('N', Arrays.asList(0, -1));
        coordinateUpdates.put('S', Arrays.asList(0, 1));
        coordinateUpdates.put('E', Arrays.asList(1, 0));
        coordinateUpdates.put('O', Arrays.asList(-1, 0));

        for (char direction : directions.toCharArray()) {
            // checking if the next direction has no obstacles
            if (canAdvance(direction)) {

                int newX = coordinates.getX() + coordinateUpdates.get(direction).get(0);
                int newY = coordinates.getY() + coordinateUpdates.get(direction).get(1);

                // updating the coordinates i.e moving to the new spot
                coordinates.setX(newX);
                coordinates.setY(newY);
            }
            // if the next direction has obstacles, then the adventurer doesn't move, and
            // checks the following direction instead
        }
    }

    @Override
    // Prints the map with our adventurer standing there, marking a red X
    public String toString() {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m\u001B[1m";
        String GREEN = "\u001B[32m";
        String stringValue = "X(" + coordinates.getX() + "," + coordinates.getY() + ")\n";
        for (int i = 0; i < forest.getMap().length; i++) {
            for (int j = 0; j < forest.getMap()[i].length; j++) {
                if (i == coordinates.getY() && j == coordinates.getX()) {
                    stringValue += RED + "X" + RESET + ",";
                } else if (forest.getMap()[i][j] == '#') {
                    stringValue += GREEN + "#" + RESET + ",";
                } else {
                    stringValue += forest.getMap()[i][j] + ",";
                }
            }
            stringValue += '\n';
        }
        return stringValue;
    }

    public Point getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Forest getForest() {
        return this.forest;
    }

    public void setForest(Forest forest) {
        this.forest = forest;
    }

}
