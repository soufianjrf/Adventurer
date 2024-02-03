package adventurer.game;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Forest {

    // The map of the forest, where '#' is a tree
    public char[][] map;

    public Forest(String filePath) {

        Path path = Paths.get(filePath);

        try {

            List<String> lines = Files.readAllLines(path);

            this.map = new char[lines.size()][];

            int i = 0;
            for (String line : lines) {
                this.map[i] = line.toCharArray();
                i++;
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
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
};
