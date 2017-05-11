package main.java;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Valentyn Kidruk
 */
public class Data {
    Data(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    private String inFile;
    private String outFile;

    private Scanner scanner;
    private PrintWriter writer;

    private Core core = new Core();

    /**
     * To read incoming data from file and processing
     * 
     * Using file format (example):
     * 
     * 2                    <- test cases count
     * 
     * 9                    <- walls count (case #1)
     * 2 5 1 2 3 4 7 7 6    <- walls values (case #1)
     * 0 8                  <- rain range indexes (case #1)
     * 
     * 10                   <- walls count (case #2)
     * 4 2 7 0 3 4 3 6 9 7  <- walls values (case #2)
     * 3 7                  <- rain range indexes (case #2)
     * 
     */
    public void process() throws Exception {
    	
    	//open input/output file
        try {
            scanner = new Scanner(new File(inFile));
            if (outFile != null && !outFile.isEmpty()) {
                writer = new PrintWriter(outFile, "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        }

        // read test cases count
        int testCount = 0;
        if (scanner.hasNextInt()) {
            testCount = scanner.nextInt();
        }

        for (int t=1; t<=testCount; t++) {
            // read data
            int wallCount = 0;
            if (scanner.hasNextInt()) {
                wallCount = scanner.nextInt();
            }

            int[] walls = new int[wallCount];
            for (int i = 0; i < wallCount; i++) {
                if (scanner.hasNextInt()) {
                    walls[i] = scanner.nextInt();
                }
            }

            int[] rainRange = new int[2];
            for (int i = 0; i < 2; i++) {
                if (scanner.hasNextInt()) {
                    rainRange[i] = scanner.nextInt();
                }
            }

            // solving
            int result = core.solve(walls, rainRange);

            // output result
            System.out.println("#" + t + ": " + result);

            // output debug
            outputDebug(t, result, walls, rainRange);
        }

    	//close input/output file
        if(writer != null) {
            writer.close();
        }
        if(scanner != null) {
            scanner.close();
        }
    }

    /**
     * To write result data into file
     * <p>
     * 
     * @param  int testNum     - case number
     * @param  int result      - case result value
     * @param  int[] walls     - set of walls, where array length is the quantity of walls
     *                           and the value at each index is the height of the wall
     * @param  int[] rainRange - range of indexes where it rains
     */
    private void outputDebug(int caseNum, int result, int[] walls, int[] rainRange) {

        int[] water = core.getWater();
        if (writer == null) {
            return;
        }

        int topIndex = core.getHighestWallIndex(walls, new int[] { 0, walls.length - 1 });
        int top = walls[topIndex] + 1;

        // prepare out data
        char[][] pic = new char[top][walls.length * 2];

        for (int i = 0; i < top; i++) {
            for (int j = 0; j < walls.length * 2; j++) {
                pic[i][j] = ' ';
            }
        }

        for (int i = rainRange[0]; i <= rainRange[1]; i++) {
            pic[0][i * 2] = '.';
            pic[0][i * 2 + 1] = '.';
        }

        for (int i = 0; i < walls.length; i++) {
            for (int j = 1; j <= walls[i]; j++) {
                pic[top - j][i * 2] = '#';
                pic[top - j][i * 2 + 1] = '#';
            }
            for (int j = 1; j <= water[i]; j++) {
                pic[top - walls[i] - j][i * 2] = '~';
                pic[top - walls[i] - j][i * 2 + 1] = '~';
            }
        }

        // output debug data
        writer.println("\n#" + caseNum + ": " + result);
        writer.print("walls: ");
        for (int i = 0; i < walls.length; i++) {
            writer.print(walls[i] + " ");
        }
        writer.println("\nrain: " + rainRange[0] + " - " + rainRange[1] + "\n");
        for (int i = 0; i < top; i++) {
            writer.println((i == 0 ? (" ") : ((top - i) % 10)) + " " + String.valueOf(pic[i]));
        }
        writer.print("  ");
        for (int i = 0; i < walls.length; i++) {
            writer.print(" " + i % 10);
        }
        writer.println("\n\n==========================================");
    }

    /**
     * To generate incoming data
     * Output generated into file
     */
    public void generate() throws Exception {
        try {
            if (outFile != null && !outFile.isEmpty()) {
                writer = new PrintWriter(outFile, "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        }

        int testCount = 1000;
        int wallCount = 100;
        int start, stop;

        Random rand = new Random();

        writer.println(testCount);
        for (int i = 0; i < testCount; i++) {
            writer.println(wallCount);
            for (int j = 0; j < wallCount; j++) {
                writer.print(rand.nextInt(wallCount) + " ");
            }
            start = rand.nextInt(wallCount);
            stop  = start + rand.nextInt(wallCount - start);
            writer.println("\n" + start + " " + stop);
        }

        if (writer != null) {
            writer.close();
        }
    }
}
