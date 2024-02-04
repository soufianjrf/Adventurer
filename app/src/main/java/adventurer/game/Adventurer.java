package adventurer.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventurer.exceptions.InvalidPositionException;
import adventurer.geometry.Point;

public class Adventurer {

    // The position of the adventurer
    public Point coordinates;

    // The forest where he will be
    public Forest forest;

    public Adventurer(Point initialPosition, Forest forest) {
        this.forest = forest;

        int x = initialPosition.getX(), y = initialPosition.getY();

        if (y < 0 || y >= forest.map.length) {
            throw new InvalidPositionException("Coordinate y out of forest range");
        } else if (x < 0 || x >= forest.map[y].length) {
            throw new InvalidPositionException("Coordinate x out of forest range");
        } else if (forest.map[y][x] == '#') {
            throw new InvalidPositionException("The initial position in the forest is occupied by a tree");
        }
        this.coordinates = initialPosition;
    }

    public Adventurer(int x, int y, Forest forest) {
        this(new Point(x, y), forest);
    }

    // returns true if there is no obstacle, false otherwise
    public boolean canAdvance(char direction) {
        int x = this.coordinates.x, y = this.coordinates.y;
        boolean result = true;

        // Depending on the direction, we check the availability of the next position
        switch (direction) {
            case 'O':
                if (x == 0 || forest.map[y][x - 1] == '#') {
                    result = false;
                }
                break;
            case 'E':
                if (x == forest.map[y].length - 1 || forest.map[y][x + 1] == '#') {
                    result = false;
                }
                break;
            case 'S':
                if (y == forest.map.length - 1 || forest.map[y + 1][x] == '#') {
                    result = false;
                }
                break;
            case 'N':
                if (y == 0 || forest.map[y - 1][x] == '#') {
                    result = false;
                }
                break;
        }
        return result;
    }

    // moves the adventurer into his new position, avoiding obstacles
    public void advance(String directions) {

        Map<Character, List<Integer>> coordinateUpdates = new HashMap<>();
        coordinateUpdates.put('N', Arrays.asList(0, -1));
        coordinateUpdates.put('S', Arrays.asList(0, 1));
        coordinateUpdates.put('E', Arrays.asList(1, 0));
        coordinateUpdates.put('O', Arrays.asList(-1, 0));

        for (char direction : directions.toCharArray()) {
            // Code for debugging
            // System.out.println(this.coordinates);
            // System.out.println(direction);
            // System.out.println("can advance : " + this.canAdvance(direction));
            if (this.canAdvance(direction)) {
                int newX = coordinates.getX() + coordinateUpdates.get(direction).get(0);
                int newY = coordinates.getY() + coordinateUpdates.get(direction).get(1);

                coordinates.setX(newX);
                coordinates.setY(newY);
            }
        }
    }

    @Override
    // Prints the map with our adventurer standing there, marking a red X
    public String toString() {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m\u001B[1m";
        String GREEN = "\u001B[32m";
        String stringValue = "X(" + coordinates.getX() + "," + coordinates.getY() + ")\n";
        for (int i = 0; i < forest.map.length; i++) {
            for (int j = 0; j < forest.map[i].length; j++) {
                if (i == coordinates.getY() && j == coordinates.getX()) {
                    stringValue += RED + "X" + RESET + ",";
                } else if (forest.map[i][j] == '#') {
                    stringValue += GREEN + "#" + RESET + ",";
                } else {
                    stringValue += forest.map[i][j] + ",";
                }
            }
            stringValue += '\n';
        }
        return stringValue;
    }
}
