package adventurer.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventurer.Point;

public class Adventurer {

    public Point coordinates;

    public Adventurer(Point initialPosition) {
        this.coordinates = new Point(initialPosition);
    }

    public Adventurer(int x, int y) {
        this.coordinates = new Point(x, y);
    }

    public boolean canAdvance(Forest forest, char direction) {
        int x = this.coordinates.x, y = this.coordinates.y;
        boolean result = true;
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

    public void advance(Forest map, String directions) {
        Map<Character, List<Integer>> coordinateUpdates = new HashMap<>();
        coordinateUpdates.put('N', Arrays.asList(0, -1));
        coordinateUpdates.put('S', Arrays.asList(0, 1));
        coordinateUpdates.put('E', Arrays.asList(1, 0));
        coordinateUpdates.put('O', Arrays.asList(-1, 0));

        for (char direction : directions.toCharArray()) {
            System.out.println(this.coordinates);
            System.out.println(direction);
            System.out.println("can advance : " + this.canAdvance(map, direction));
            if (this.canAdvance(map, direction)) {
                int newX = this.coordinates.getX() + coordinateUpdates.get(direction).get(0);
                int newY = this.coordinates.getY() + coordinateUpdates.get(direction).get(1);

                this.coordinates.setX(newX);
                this.coordinates.setY(newY);
                // switch (direction) {
                // case 'N':
                // this.coordinates.setY(this.coordinates.getY() - 1);
                // break;
                // case 'S':
                // this.coordinates.setY(this.coordinates.getY() + 1);
                // break;
                // case 'E':
                // this.coordinates.setX(this.coordinates.getX() + 1);
                // break;
                // case 'O':
                // this.coordinates.setX(this.coordinates.getX() - 1);
                // break;
                // }
            }
        }
    }

}
