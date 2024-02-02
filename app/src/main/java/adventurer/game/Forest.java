package adventurer.game;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Forest {
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
    public String toString() {
        String stringValue = "";
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                stringValue += this.map[i][j];
            }
            stringValue += '\n';
        }
        return stringValue;
    }
};
