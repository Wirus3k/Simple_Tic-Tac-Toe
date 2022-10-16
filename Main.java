package tictactoe;
import java.util.Scanner;
import java.lang.Math;
import java.util.InputMismatchException;

public class Main {
    public static void display(String[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }
    public static String[][] startBoard() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "_";
            }
        }
        return board;
    }

    public static boolean winHorizontal1(String[][] board) {
        for (int i = 0; i < 3; i++){
            if (board[i][0].equals("X") && board[i][1].equals("X") && board[i][2].equals("X")) {
                return true;
            }
        }
        return false;
    }

    public static boolean winHorizontal2(String[][] board) {
        for (int i = 0; i < 3; i++){
            if (board[i][0].equals("O") && board[i][1].equals("O") && board[i][2].equals("O")) {
                return true;
            }
        }
        return false;
    }

    public static boolean winVertical1(String[][] board) {
        for (int i = 0; i < 3; i++){
            if (board[0][i].equals("X") && board[1][i].equals("X") && board[2][i].equals("X")) {
                return true;
            }
        }
        return false;
    }

    public static boolean winVertical2(String[][] board) {
        for (int i = 0; i < 3; i++){
            if (board[0][i].equals("O") && board[1][i].equals("O") && board[2][i].equals("O")) {
                return true;
            }
        }
        return false;
    }

    public static boolean winDiagonal1(String[][] board) {
            if ((board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X")) || (board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X"))) {
                return true;
        }
        return false;
    }

    public static boolean winDiagonal2(String[][] board) {
        if ((board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O")) || (board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O"))) {
            return true;
        }
        return false;
    }

    public static boolean emptyCell(String[][] board){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j].equals("_")){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isItPossible(String[][] board){
        int x = 0;
        int o = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j].equals("X")) {
                    x++;
                } else if (board[i][j].equals("O")) {
                    o++;
                }
            }
        }
        if (Math.abs(x - o) < 2){
            return true;
        } else {
            return false;
        }
    }

    public static boolean count(String[][] board){
        int x = 0;
        int o = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j].equals("X")) {
                    x++;
                } else if (board[i][j].equals("O")) {
                    o++;
                }
            }
        }
        if(x + o == 9){
            return true;
        } else {
            return false;
        }
    }

    public static String endOfGame(String[][] board){
        if (!isItPossible(board)) {
            return "Impossible";
        } else if (winDiagonal1(board) || winVertical1(board) || winHorizontal1(board)) {
            return "X wins";
        } else if (winDiagonal2(board) || winVertical2(board) || winHorizontal2(board)) {
            return "O wins";
        } else if (emptyCell(board) && !count(board)) {
            return "Game not finshed";
        } else if (count(board)) {
            return "Draw";
        } else {
            return "unknown";
        }
    }

    public static void move(String[][] board, String turn){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try{
                System.out.print("Enter the coordinates: ");
                String[] numbers = scanner.nextLine().split(" ");
                int i = Integer.parseInt(numbers[0]);
                int j = Integer.parseInt(numbers[1]);

                if (i < 1 || i > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                } else if (j < 1 || j > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                i--;
                j--;

                if (!board[i][j].equals("_")){
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                } else {
                    board[i][j] = turn;
                    display(board);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
        }
    }


    public static void main(String[] args) {

        String[][] board = startBoard();
        String turn = "X";
        display(board);
        for(;;){
            if(endOfGame(board).equals("Impossible") || endOfGame(board).equals("X wins") || endOfGame(board).equals("O wins") || endOfGame(board).equals("Draw")){
                System.out.println(endOfGame(board));
                break;
            }
            move(board, turn);
            if (turn.equals("X")) {
                turn = "O";
            } else {
                turn = "X";
            }
        }
    }
}
