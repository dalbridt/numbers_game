package sub.guessnum;
import java.util.Random;
import java.util.Scanner;

class Randomiser {
    public static void main(String[] args) {
        int[] digits = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] referenceComb = getReferenceCombination(digits);
        int[] score = new int[2];
        int[] input = new int[4];
        while (!compareCombinations(input, referenceComb, score)) {
            // System.out.println( " \n----------------");
            // System.out.println("reference combination: ");
            //   for (int i : referenceComb) {
            //     System.out.print(i + " ");
            // }
            // System.out.println( " \n----------------");
            input = getSuggestedNumber();
            System.out.print("combination received :");
            for (int i : input) {
                System.out.print(i + " ");
            }
        }
    }

    public static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    public static int[] getReferenceCombination(int[] digits) {
        int[] combination = new int[4];
        shuffle(digits);
        for (int i = 0; i < 4; i++) {
            combination[i] = digits[i];
        }
        return combination;
    }

    public static int[] getSuggestedNumber() {
        // how to get int if inserted without space ?
        int[] current = new int[4];
        Scanner sc = new Scanner(System.in);
        while (!inputIsValid(current)) {
            for (int i = 0; i < 4; i++) {
                current[i] = sc.nextInt();
            }
        }
        return current;
    }

    public static boolean inputIsValid(int[] combination) {
        boolean flag = true;
        for (int i : combination) {
            if (i > 9 || i < 0) {
                flag = false;
                break;
            }
        }
        if (combination[0] == combination[1] || combination[0] == combination[2] || combination[0] == combination[3]
                || combination[1] == combination[2] || combination[1] == combination[3]
                || combination[2] == combination[3]) {
            flag = false;
        }
        if (!flag) {
            System.out.println("insert 4-digits combination. digits may not repeat");
        }
        return flag;
    }

    public static boolean compareCombinations(int[] current, int[] reference, int[] score) {
        boolean flag = true;
        score[0] = 0;
        score[1] = 0;
        for (int i = 0; i < 4; i++) {
            if (current[i] != reference[i]) {
                flag = false;
            }
            if (isPresent(current[i], reference)) {
                score[0]++;
            }
            if (current[i] == reference[i]) {
                score[1]++;
            }
        }
        System.out.println();
        System.out.println("digits guessed: " + score[0] + "\ndigits in their places: " + score[1]);
        return flag;
    }

    public static boolean isPresent(int x, int[] arr) {
        for (int i : arr) {
            if (i == x) {
                return true;
            }
        }
        return false;
    }
}