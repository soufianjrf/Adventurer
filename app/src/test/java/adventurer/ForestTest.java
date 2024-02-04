package adventurer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import adventurer.exceptions.InvalidForestFileException;
import adventurer.game.Forest;

public class ForestTest {
    @Test
    void shouldThrowExceptionWhenAnInvalidFileIsPassed() {
        assertThrows(InvalidForestFileException.class, () -> {
            new Forest("incorrect.path");
        });
    }

    @Test
    void shouldCreateForest() {
        assertDoesNotThrow(() -> {
            new Forest("carte.txt");
        });
        Forest forest = new Forest("carte.txt");

        assertEquals(forest.map.length, 20);
        assertEquals(forest.map[0].length, 20);
        ;
        assertEquals(forest.map[0][0], '#');
        assertEquals(forest.map[5][5], ' ');

    }

}
