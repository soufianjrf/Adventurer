package adventurer.game;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import adventurer.exceptions.InvalidForestFileException;

public class Forest {

    // The map of the forest, where '#' is a tree
    private char[][] map;

    public Forest(String filePath) {

        // Reads a map from an external file
        Path path = Paths.get(filePath);

        try {
            List<String> lines = Files.readAllLines(path);
            map = new char[lines.size()][];
            int i = 0;
            for (String line : lines) {
                map[i] = line.toCharArray();
                i++;
            }
        } catch (Exception e) {
            throw new InvalidForestFileException(e.getMessage());
        }
    }

    @Override
    // Showing the map on the console
    public String toString() {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String stringValue = "";
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') {
                    stringValue += GREEN + "#" + RESET + ",";
                } else {
                    stringValue += map[i][j] + ",";
                }
            }
            stringValue += '\n';
        }
        return stringValue;
    }

    public char[][] getMap() {
        return this.map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

};
