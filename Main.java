package com.tdstyrone;

public class Main {



    public Main(){ }

    public boolean solve(int[][] board){
    /*
    Solves a sudoku board using backtracking
    :param board: 2-Dimensional list of integers
    :return: solution
    */

        int[] find = findEmpty(board);
        if (find == null)
            return true;

        for (int i = 1; i < 10; i++){
            int row = find[0];
            int col = find[1];
            if (isBoardValid(board, i, find)){
                board[row][col] = i;

                if (solve(board))
                        return true;

                board[row][col] = 0;
            }
        }
        return false;
    }


    public boolean isBoardValid(int[][] board, int num, int[] positionTuple){
    /*
    Returns if the attempted move is valid
    :param board: 2-Dimensional list of integers
    :param positionTuple: (row, col)
    :param num: int
    :return: bool
    */

        for (int i = 0; i < board[0].length; i++){
            if (board[positionTuple[0]][i] == num && positionTuple[1] != i)
                return false;
        }

        for (int i = 0; i < board.length; i++){
            if(board[i][positionTuple[1]] == num && positionTuple[0] != i)
                return  false;
        }

        int box_x = positionTuple[1] / 3;
        int box_y = positionTuple[0] / 3;

        for (int i = box_y * 3; i < box_y*3 + 3; i++){
            for (int j = box_x * 3; j < box_x*3 + 3; j++){
                if(board[i][j] == num && positionTuple[0] != i && positionTuple[1] != j)
                    return false;
            }
        }
        return true;
    }

    public void displayBoard(int[][] board){
    /*
    prints the board
    :param bo: 2-Dimensional list of integers
    :return: None
    */
        for (int i = 0; i < board.length; i++){
            if (i % 3 == 0 && i != 0)
                System.out.println("------------------------");

            for (int j = 0; j < board[i].length; j++){
                if(j % 3 == 0 && j !=0 )
                    System.out.print(" | ");

                if (j == 8)
                    System.out.println(board[i][j]);
                else
                    System.out.print(board[i][j] + " ");
            }
        }

    }

    public int[] findEmpty(int[][]board){
    /*
    finds an empty space in the board
    :param board: partially complete board
    :return: positionTuple
    */

        int[] positionTuple = new int[2];

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if ( board[i][j] == 0) {
                    positionTuple[0] = i;
                    positionTuple[1] = j;
                    return (positionTuple);
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        int[][] SODOKU_BOARD = {
                {7, 8, 0, 4, 0, 0, 1, 2, 0},
                {6, 0, 0, 0, 7, 5, 0, 0, 9},
                {0, 0, 0, 6, 0, 1, 0, 7, 8},
                {0, 0, 7, 0, 4, 0, 2, 6, 0},
                {0, 0, 1, 0, 5, 0, 9, 3, 0},
                {9, 0, 4, 0, 6, 0, 0, 0, 5},
                {0, 7, 0, 3, 0, 0, 0, 1, 2},
                {1, 2, 0, 0, 0, 7, 4, 0, 0},
                {0, 4, 9, 2, 0, 6, 0, 0, 7}
        };

        Main board1 = new Main();
        board1.displayBoard(SODOKU_BOARD );
        board1.solve(SODOKU_BOARD );
        System.out.println("                        ");
        System.out.println("-----SOLVED SOLUTION----");
        System.out.println("                        ");
        board1.displayBoard(SODOKU_BOARD );
    }
}
