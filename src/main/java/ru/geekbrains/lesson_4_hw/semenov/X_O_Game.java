package ru.geekbrains.lesson_4_hw.semenov;

import java.util.Random;
import java.util.Scanner;

public class X_O_Game {
    public static Random rand = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static char[][] map;
    public static final int SIZE = 4;
    public static final int DOTS_TO_WIN = 4;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static void main(String[] args) {
        boolean replay = true;
        while (replay == true) {
            initMap();
            printMap();
            while (true) {
                humanTurn();
                printMap();
                if (checkWin(DOT_X)) {
                    System.out.println("Победил человек");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
                aiTurn();
                printMap();
                if (checkWin(DOT_O)) {
                    System.out.println("Победил компьютер");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
                System.out.println();

            }
            System.out.println("Игра закончена");
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            int replayNumber = sc.nextInt();
            if (replayNumber == 0) {
                sc.close();
                replay = false;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " "); // цифры по x
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // цифры по y
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x, y;
        System.out.println("Введите координаты в формате X Y");
        do {
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }


    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер выбрал точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        System.out.println("Координаты выходят за границы поля или ячейка уже заполнена. Повторите ввод");
        return false;
    }

    public static boolean checkDiag(char symb) {
        boolean d1 = true;
        boolean d2 = true;
        for (int i = 0; i < SIZE; i++) {
            d1 = d1 & (map[i][i] == symb);
            d2 = d2 & (map[i][3 - i] == symb);
        }
        if (d1) return true;
        if (d2) return true;
        return false;
    }

    public static boolean vertHorCheck(char symb) {
        boolean v;
        boolean h;
        for (int i = 0; i < SIZE; i++) {
            v = true;
            h = true;
            for (int j = 0; j < SIZE; j++) {
                v = v & (map[i][j] == symb);
                h = h & (map[j][i] == symb);
            }
            if (v) return true;
            if (h) return true;
        }
        return false;
    }

    public static boolean checkWin(char symb) {
        if (checkDiag(symb)) return true;
        if (vertHorCheck(symb)) return true;
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}
