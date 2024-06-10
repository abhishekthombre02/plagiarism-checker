package LCS;
//import packages
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class findLCS {

    // Method to read files and compute LCS
    public static void readFiles() {
        System.out.println("readFiles Function");
        System.out.println("---------------------------------------------------------------------------------");
        // Declaring and initializing variables
        Scanner sc1, sc2;
        String[] arrOfFile1, arrOfFile2;
        String str1 = "", str2 = "";
        try {
            File file1 = new File("D:\\My Projects\\plagarism-check\\LCS\\files\\textFile3.txt");
            sc1 = new Scanner(file1);
            File file2 = new File("D:\\My Projects\\plagarism-check\\LCS\\files\\textFile6.txt");
            sc2 = new Scanner(file2);

            // Convert file into string
            while (sc1.hasNextLine()) {
                str1 = str1 + " " + sc1.nextLine();
            }

            while (sc2.hasNextLine()) {
                str2 = str2 + " " + sc2.nextLine();
            }

            // Remove all the special characters and extra spaces
            str1 = str1.replaceAll("[\\n\\r\\t]", "").trim();
            str2 = str2.replaceAll("[\\n\\r\\t]", "").trim();

            // Convert string into array of strings
            arrOfFile1 = str1.split("\\s+");
            arrOfFile2 = str2.split("\\s+");

            // Find longest common subsequence
            ArrayList<String> lcs = findingLCS(arrOfFile1, arrOfFile2, arrOfFile1.length, arrOfFile2.length);

            // No subsequence condition
            if (lcs.size() == 0) {
                System.out.println("No common subsequences found");
            } else {
                System.out.print("The Longest Common Subsequence is: ");
                for (String s : lcs) {
                    System.out.print(s + " ");
                }
                System.out.println("\nThe length of Longest Common Subsequence is: " + lcs.size());

                // Percentage of plagiarism calculation based on the shorter file
                int minLength = Math.min(arrOfFile1.length, arrOfFile2.length);
                double plagiarismPercentage = ((double) lcs.size() / minLength) * 100;
                System.out.println("The Percentage of Plagiarism is: " + plagiarismPercentage);
            }
            System.out.println("---------------------------------------------------------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to find the longest common subsequence
    public static ArrayList<String> findingLCS(String[] seq1, String[] seq2, int size1, int size2) {
        // Matrix of size size1+1 * size2+1
        int[][] lcsInt = new int[size1 + 1][size2 + 1];

        // First row and column is zero
        for (int i = 0; i <= size1; i++) {
            lcsInt[i][0] = 0;
        }

        for (int i = 0; i <= size2; i++) {
            lcsInt[0][i] = 0;
        }

        // Print initial matrix
        System.out.println("Initial Matrix / Table: ");
        for (int i = 0; i <= size1; i++) {
            for (int j = 0; j <= size2; j++) {
                System.out.print(lcsInt[i][j] + " ");
            }
            System.out.println();
        }

        // Populate the matrix using the LCS algorithm
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (seq1[i - 1].equals(seq2[j - 1])) {
                    lcsInt[i][j] = lcsInt[i - 1][j - 1] + 1;
                } else {
                    lcsInt[i][j] = Math.max(lcsInt[i][j - 1], lcsInt[i - 1][j]);
                }
            }
        }

        // Print matrix after calculation
        System.out.println("Matrix / Table after calculation: ");
        for (int i = 0; i <= size1; i++) {
            for (int j = 0; j <= size2; j++) {
                System.out.print(lcsInt[i][j] + " ");
            }
            System.out.println();
        }

        // Backtrack to find the subsequence
        int i = seq1.length, j = seq2.length;
        ArrayList<String> lcs = new ArrayList<>();
        while (i != 0 && j != 0) {
            if (seq1[i - 1].equals(seq2[j - 1])) {
                lcs.add(0, seq1[i - 1]);
                i--;
                j--;
            } else if (lcsInt[i][j - 1] > lcsInt[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }
        return lcs;
    }

    public static void main(String[] args) {
        readFiles();
    }
}
