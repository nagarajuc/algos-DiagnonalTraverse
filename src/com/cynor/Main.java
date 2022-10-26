package com.cynor;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runner.notification.*;

public class Main {
    public int[] find_diagonal_order(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length; //counter for rows / cols
        int i = 0,
                row = 0,
                col = 0,
                total = n * m;
        int[] result = new int[total];
        boolean direction = true; //true for up, False for down

        //Empty edge case
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        while (total != 0) {
            result[i] = matrix[row][col];
            i++;
            total -= 1;

            if (direction) { //going up
                if (row == 0 || col == m - 1) {
                    direction = false;
                    if (col == m - 1) {
                        row += 1;
                    } else {
                        col += 1;
                    }
                } else {
                    row -= 1;
                    col += 1;
                }
            } else { //going down : the same as going up, just reversed
                if (col == 0 || row == n - 1) {
                    direction = true;
                    if (row == n - 1) {
                        col += 1;
                    } else {
                        row += 1;
                    }
                } else {
                    row += 1;
                    col -= 1;
                }
            }
        }
        return result;
    }

    @Test
    public void testBaseCase() {
        int[][] input = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[] expected = new int[]{1,2,4,7,5,3,6,8,9};
        int [] result = this.find_diagonal_order(input);
        assertEquals("find_diagonal_order([[1,2,3],[4,5,6],[7,8,9]])", Arrays.toString(expected), Arrays.toString(result));
    }

    public void testCase2() {
        int[][] input = new int[][]{{1}};
        int[] expected = new int[]{1};
        int [] result = this.find_diagonal_order(input);
        assertEquals("find_diagonal_order([[1]])", Arrays.toString(expected), Arrays.toString(result));
    }

    public void testCase3() {
        int[][] input = new int[][]{{1,2},{3,4}};
        int[] expected = new int[]{1,2,3,4};
        int [] result = this.find_diagonal_order(input);
        assertEquals("find_diagonal_order([[1,2],[3,4]])", Arrays.toString(expected), Arrays.toString(result));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Main.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
