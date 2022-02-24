package com.mycodefu;

import java.util.HashSet;
import java.util.Random;

public class EntryPoint {
    public static final int ITERATIONS = 1_000_000;
    private static final Random random = new Random();

    private static int randomBirthday() {
        return random.nextInt(365);
    }

    public static void main(String[] args) {
        main_hashset(args);
        main_array(args);
    }

    public static void main_hashset(String[] args) {
        long startTimeMillis = System.currentTimeMillis();

        int[] matchCounts = new int[99];

        for (int iterations = 0; iterations < ITERATIONS; iterations++) {
            for (int peopleCount = 2; peopleCount <= 100; peopleCount++) {
                HashSet<Integer> birthdays = new HashSet<>(peopleCount);
                for (int person = 0; person < peopleCount; person++) {
                    birthdays.add(randomBirthday());
                }
                if (birthdays.size() != peopleCount) {
                    matchCounts[peopleCount - 2]++;
                }
            }
        }

        for (int i = 0; i < matchCounts.length; i++) {
            int peopleInRoom = i + 2;
            int matchCount = matchCounts[i];
            double matchPercentage = (double) matchCount / ITERATIONS * 100;
            System.out.printf("With %d people in the room there were %.3f%% with the same birthdays.\n", peopleInRoom, matchPercentage);
        }

        long finishTimeMillis = System.currentTimeMillis();
        System.out.printf("Took %d milliseconds for hashset.\n", finishTimeMillis - startTimeMillis);
    }


    public static void main_array(String[] args) {
        long startTimeMillis = System.currentTimeMillis();

        int[] matchCounts = new int[99];
        int[] birthdays = new int[100];

        for (int iterations = 0; iterations < ITERATIONS; iterations++) {
            for (int peopleCount = 2; peopleCount <= 100; peopleCount++) {
                for (int person = 0; person < peopleCount; person++) {
                    birthdays[person] = randomBirthday();
                }
                boolean matches = false;
                for (int birthday1 = 0; birthday1 < peopleCount; birthday1++) {
                    for (int birthday2 = 0; birthday2 < peopleCount; birthday2++) {
                        if (birthday1 != birthday2 && birthdays[birthday1] == birthdays[birthday2]) {
                            matches = true;
                            break;
                        }
                    }
                    if (matches) {
                        break;
                    }
                }
                if (matches) {
                    matchCounts[peopleCount - 2]++;
                }
            }
        }

        for (int i = 0; i < matchCounts.length; i++) {
            int peopleInRoom = i + 2;
            int matchCount = matchCounts[i];
            double matchPercentage = (double) matchCount / ITERATIONS * 100;
            System.out.printf("With %d people in the room there were %.3f%% with the same birthdays.\n", peopleInRoom, matchPercentage);
        }

        long finishTimeMillis = System.currentTimeMillis();
        System.out.printf("Took %d milliseconds for array.\n", finishTimeMillis - startTimeMillis);
    }
}
