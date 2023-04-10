// If all squares are visited 
//     print the solution
// Else
//    a) Add one of the next moves to solution vector and recursively 
//    check if this move leads to a solution. (A Knight can make maximum 
//    eight moves. We choose one of the 8 moves in this step).
//    b) If the move chosen in the above step doesn't lead to a solution
//    then remove this move from the solution vector and try other 
//    alternative moves.
//    c) If none of the alternatives work then return false (Returning false 
//    will remove the previously added item in recursion and if false is 
//    returned by the initial call of recursion then "no solution exists" )
public class Solution {
    /**
     * @param N size of a chessboard
     */
    static int N = 8;

    /***
     * An utility function to not only check if x and y are valid indexes for N*N
     * chessboard
     * but also check if this square is never been used.
     * 
     * @param x   index of x axis
     * @param y   index of y axis
     * @param sol the chessboard matrix
     * @return boolean
     */
    static boolean isSafe(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    /**
     * Print the feasible solution.
     * 
     * @param sol the solution matrix
     */
    static void printSolution(int sol[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * this function will be used to solves the knight tour problems using
     * Backtracking.
     * This function mainly using solveKTUtil() to solve the problem. It returns
     * false if no complete tour is possible,
     * otherwise return true and prints the tour.
     * Please note that there may be more than one solutions, this function prints
     * one of the feasible solutions.
     * 
     * @return boolean
     */
    static boolean solveKT() {
        int sol[][] = new int[N][N];
        // initialize of solution matrix. unused squares will mark -1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sol[i][j] = -1;
            }
        }
        // first we need to save all the feasible move of a knight
        // Assume the knight is at the 0 0 coordinate
        // xMove and yMove define the next move of Knight according to x axis and y axis
        // respectively
        int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

        // assume the knight is at the first block (0,0)
        sol[0][0] = 0;
        // start from 0,0 and explore all tours using solveKTUtil()
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exists");
            return false;
        } else {
            printSolution(sol);
            return true;
        }
    }

    /**
     * recursive algorithm
     * by nature the knight have maximum 8 possible moves, this algorithm will try
     * to move from 1 to 8 possible move in order to find the solution
     * try all next move from the current coordinate x, y
     * 
     * @param x
     * @param y
     * @param movei
     * @param sol
     * @param xMove
     * @param yMove
     * @return boolean
     */
    private static boolean solveKTUtil(int x, int y, int movei, int[][] sol, int[] xMove, int[] yMove) {
        int k, next_x, next_y;
        // if we find retrieve all possible squares of the chessboard
        if (movei == N * N)
            return true;

        for (k = 0; k < 8; k++) {
            // make a move
            next_x = x + xMove[k];
            next_y = y + yMove[k];
            if (isSafe(next_x, next_y, sol)) {
                sol[next_x][next_y] = movei; // keep track the number of move
                if (solveKTUtil(next_x, next_y, movei + 1, sol, xMove, yMove)) {
                    return true;
                } else {
                    sol[next_x][next_y] = -1; // not a possible way, turn the square status to unvisited ==>
                                              // backtracking
                }
            }
        }
        return false;
    }

    // test the algorithm
    public static void main(String[] args) {
        solveKT();
    }

    // the result might be
    // 0 59 38 33 30 17 8 63
    // 37 34 31 60 9 62 29 16
    // 58 1 36 39 32 27 18 7
    // 35 48 41 26 61 10 15 28
    // 42 57 2 49 40 23 6 19
    // 47 50 45 54 25 20 11 14
    // 56 43 52 3 22 13 24 5
    // 51 46 55 44 53 4 21 12F
}
