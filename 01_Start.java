// To get input from the user in a Java program.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userScan = new Scanner(System.in); // Represents the standard input stream

        System.out.print("Your name: ");
        String userName = userScan.nextLine(); // Reads a string directly
        System.out.print("Your age: ");
        int userNum = userScan.nextInt(); // Reads an integer directly
        System.out.println("\nHello " + userName);

        int num1 = 50, num2 = 10, result; // Variable are the same type
        result = num1 + num2;
        System.out.println("\nResult: " + result);

        // Array
        int[] Nums = new int[5];
        Nums[0] = 2;
        Nums[1] = 4;
        Nums[2] = 6;
        Nums[3] = 7;
        Nums[4] = 9;

        // Using an enhanced for loop (for-each) to print all elements
        for (int number : Nums) {
            System.out.println(number);
        }

        float[] Nums2 = new float[] {2.0f, 6.45f, 45.3423f};

        // Two-demensional array
        char[][] syms = new char[2][2];

        byte[][] syms2 = new byte[][]{
                {5, 7},
                {7, 3},
                {2, 0}
        };

    }
}
