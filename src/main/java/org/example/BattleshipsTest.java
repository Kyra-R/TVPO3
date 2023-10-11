package org.example;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class BattleshipsTest {
    static final int FILED_LENGTH = 10;

    @Test
    public void fillPlayerField() {
        char[][] playerFieldTest = new char[FILED_LENGTH][FILED_LENGTH];
        Battleships.fillRandomField(playerFieldTest);
        Assert.assertTrue(Battleships.isPlayerAlive(playerFieldTest));
    }

    @Test
    public void printField() {
        char[][] playerFieldTest = new char[FILED_LENGTH][FILED_LENGTH];
        Battleships.fillRandomField(playerFieldTest);
        Assert.assertTrue(Battleships.printField(playerFieldTest));
    }

    @Test
    public void isPlayerAlive() {
        char[][] playerFieldTest = new char[FILED_LENGTH][FILED_LENGTH];
        Battleships.fillRandomField(playerFieldTest);
        Assert.assertTrue(Battleships.isPlayerAlive(playerFieldTest));
    }

    @Test
    public void isPlayerDead() {
        char[][] playerFieldTest = new char[FILED_LENGTH][FILED_LENGTH];
        Assert.assertFalse(Battleships.isPlayerAlive(playerFieldTest));
    }

    @Test
    public void validateCoordForShip() {
        char[][] playerFieldTest = new char[FILED_LENGTH][FILED_LENGTH];
        Integer count = 0;
        Battleships.fillRandomField(playerFieldTest);
        for (int i = 0; i < 10; i++) {
            Integer x = ThreadLocalRandom.current().nextInt(0, 10);

            Integer y = ThreadLocalRandom.current().nextInt(0, 10);

            Integer position = ThreadLocalRandom.current().nextInt(1, 3);

            Integer shipType = ThreadLocalRandom.current().nextInt(1, 3);

            count+=Battleships.validateCoordForShip(playerFieldTest, x, y, position, shipType);

        }
        Assert.assertTrue(count<=0);
    }

    @Test
    public void handleShot() {
        char[][] playerFieldTest = new char[FILED_LENGTH][FILED_LENGTH];
        Integer count = 0;
        char[][] battleFieldTest = new char[FILED_LENGTH][FILED_LENGTH];
        Battleships.fillRandomField(playerFieldTest);
        Battleships.printField(playerFieldTest);
        for (int i = 0; i < 20; i++) {
            Integer x = ThreadLocalRandom.current().nextInt(0, 10);

            Integer y = ThreadLocalRandom.current().nextInt(0, 10);

            count+=Battleships.handleShot(battleFieldTest, playerFieldTest, x, y);

        }
        Assert.assertTrue(count>=0);
    }
}
