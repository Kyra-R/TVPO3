package org.example;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Battleships {
    static final int FILED_LENGTH = 10;
    static char[][] playerField1 = new char[FILED_LENGTH][FILED_LENGTH];
    static char[][] playerField2 = new char[FILED_LENGTH][FILED_LENGTH];
    static Scanner scanner = new Scanner(System.in);

    protected static void fillPlayerField(char[][] playerField) {
        int count = 5;
        for (int i = 4; i >= 1; i--) {
            // растановка кораблей
            for (int k = i; k <= 5 - i; k++) {
                System.out.println("Setting " + i + "-long ship. Ships left: " + count);
                count--;

                // иницализируем переменную начальным значением
                int validationResult = 1;
                int position = 1;
                int y = 0;
                int x = 0;
                while (validationResult != 0) {
                    System.out.println("Input x coord: ");
                    x = scanner.nextInt();

                    System.out.println("Input y coord: ");
                    y = scanner.nextInt();

                    System.out.println("1 - horizontal; 2 - vertical ?");
                    position = scanner.nextInt();

                    validationResult = validateCoordForShip(playerField, x, y, position, i);
                }

                if (position == 1) {

                    for (int q = 0; q < i; q++) {
                        playerField[y][x + q] = '1';
                    }
                }

                if (position == 2) {

                    for (int m = 0; m < i; m++) {
                        playerField[y + m][x] = '1';
                    }
                }

                printField(playerField);
            }
        }
    }

    protected static void fillRandomField(char[][] playerField) {
        int count = 5;
        for (int i = 4; i >= 1; i--) {

            for (int k = i; k <= 5 - i; k++) {
                System.out.println("Setting " + i + "-long ship. Ships left: " + count);
                count--;

                int validationResult = 1;
                int position = 1;
                int y = 0;
                int x = 0;
                while (validationResult != 0) {
                    x = ThreadLocalRandom.current().nextInt(0, 10);

                    y = ThreadLocalRandom.current().nextInt(0, 10);

                    position = ThreadLocalRandom.current().nextInt(1, 3);

                    validationResult = validateCoordForShip(playerField, x, y, position, i);
                }


                if (position == 1) {

                    for (int q = 0; q < i; q++) {
                        playerField[y][x + q] = '1';
                    }
                }


                if (position == 2) {

                    for (int m = 0; m < i; m++) {
                        playerField[y + m][x] = '1';
                    }
                }
            }
        }
        System.out.println("Second player done.");
        //printField(playerField);
    }

    protected static int validateCoordForShip(char[][] field, int x, int y, int position, int shipType) {

        if(x >= 10 || y >= 10 || x < 0 || y < 0) return -1;


       for (int i = 0; i < shipType; i++) {

            if  ((x + i) > 9 && position == 1) {
                return -1;
            }
            if  ((y + i) > 9 && position == 2) {
                return -1;
            }


           if (x + i < 10) {
                if ('1' == field[y][x + i]) {

                    return -1;
                }
                if (y > 0 && '1' == field[y - 1][x + i]) {
                    return -1;
                }
                if (y < 9 && '1' == field[y + 1][x + i]) {
                    return -1;
                }
            }
            if(x + i + 1 < 10 && '1' == field[y][x + i + 1]) {
                return -1;
            }
            if(x + i - 1 >= 0 && '1' == field[y][x + i - 1]) {
                return -1;
            }



        }

        return 0;
    }



    private static void playGame(char[][] playerField1, char[][] playerField2) {

        char[][] playerBattleField1 = new char[FILED_LENGTH][FILED_LENGTH];
        char[][] playerBattleField2 = new char[FILED_LENGTH][FILED_LENGTH];


        String currentPlayerName = "Player";
        char[][] currentPlayerField = playerField2;
        char[][] currentPlayerBattleField = playerBattleField1;


        while (isPlayerAlive(playerField1) && isPlayerAlive(playerField2)) {

            System.out.println(" \n" + currentPlayerName + " shots:");
            printField(currentPlayerBattleField);
            int xShot;
            int yShot;

            if(currentPlayerName.equals("Player")) {
                System.out.println(currentPlayerName + ", please, input x coord of shot");
                xShot = scanner.nextInt();
                System.out.println(currentPlayerName + ", please, input y coord of shot");
                yShot = scanner.nextInt();
            } else {
                xShot = ThreadLocalRandom.current().nextInt(0, 10);
                yShot = ThreadLocalRandom.current().nextInt(0, 10);
            }


            int shotResult = handleShot(currentPlayerBattleField, currentPlayerField, xShot, yShot);

            if (shotResult == 0) {
                if(currentPlayerName == "Computer"){
                    currentPlayerName = "Player";
                } else {
                    currentPlayerName = "Computer";
                }
                currentPlayerField = playerField1;
                currentPlayerBattleField = playerBattleField2;
            }
        }
        System.out.println(currentPlayerName + " is winner!");
    }


    protected static int handleShot(char[][] battleField, char[][] field, int x, int y) {
        char t = '1';
        if (t == field[y][x]) {
            field[y][x] = '#';
            battleField[y][x] = '#';
            System.out.println("Good shot!");
            return 1;
        }
        battleField[y][x] = '*';
        System.out.println("Bad shot!");
        return 0;
    }

    static boolean printField(char[][] field) {
        for (char[] cells : field) {
            for (char cell : cells) {

                if (cell == 0) {
                    System.out.print(" |");
                } else {

                    System.out.print(cell + "|");
                }
            }
            System.out.println("");
            System.out.println("--------------------");
        }
        return true;
    }


    protected static boolean isPlayerAlive(char[][] field) {
        for (char[] cells : field) {
            for (char cell : cells) {
                if ('1' == cell) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        fillRandomField(playerField2);
        fillPlayerField(playerField1);
        playGame(playerField1,playerField2);

    }
}
