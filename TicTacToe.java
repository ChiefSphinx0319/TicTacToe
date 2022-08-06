import java.util.Scanner;

public class TicTacToe {
    final static Scanner scanner = new Scanner(System.in);
    final static int ARRAY_SIZE = 9;
    static String[][] matrix = new String[3][3];
    static String player = "X";
    boolean isGameLaunch = false;

    public static void main(String[] args) {
        printThis();
        checkUserInput(matrix);
    }
    static void printThis() {

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                matrix[i][j] = " ";
            }
        }
        //print out
        System.out.println("---------");
        for(int i=0; i<matrix.length; i++) {
            System.out.print("| ");
            // inner loop for column
            for(int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("|");
            System.out.println(); // new line
        }
        System.out.println("---------");
    }
    static void checkUserInput(String[][] matrix){
        while (true) {
            String input1 = scanner.next();
            String input2 = scanner.next();

            try {
                int input1Int = Integer.parseInt(input1);
                int input2Int = Integer.parseInt(input2);

                if (input1Int < 1 || input1Int > 3 || input2Int < 1 || input2Int > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                input1Int--;
                input2Int--;
                if (matrix[input1Int][input2Int].equals("X") || matrix[input1Int][input2Int].equals("O")) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                } else {
                    int[] number = {input1Int, input2Int};
                    display2DArray(number);
                    break;
                }
            } catch (NumberFormatException ne) {
                System.out.println("You should enter numbers!");
            }
        }
    }
    static void display2DArray(int[] userInput) {

        switch (player) {
            case "X":
                matrix[userInput[0]][userInput[1]] = "X";
                break;
            case "O":
                matrix[userInput[0]][userInput[1]] = "O";
                break;
            default:
                System.out.println("There's something wrong!");
                break;
        }
        System.out.println("---------");
        for(int i=0; i<matrix.length; i++) {
            System.out.print("| ");
            // inner loop for column
            for(int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
                if (player.equals("X")) player = "O";
                else player = "X";
            }
            System.out.print("|");
            System.out.println(); // new line
        }
        System.out.println("---------");
        switch (checkWinner(matrix)) {
            case "X wins":
                System.out.println("X wins");
                break;
            case "O wins":
                System.out.println("O wins");
                break;
            case "Draw":
                System.out.println("Draw");
                break;
            default:
                checkUserInput(matrix);
                break;
        }
    }
    static String checkWinner(String[][] matrix)
    {
        int xxxCounter = 0;
        int oooCounter = 0;
        String result = "";

        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = matrix[0][0] + matrix[0][1] + matrix[0][2];
                    break;
                case 1:
                    line = matrix[1][0] + matrix[1][1] + matrix[1][2];
                    break;
                case 2:
                    line = matrix[2][0] + matrix[2][1] + matrix[2][2];
                    break;
                case 3:
                    line = matrix[0][0] + matrix[1][0] + matrix[2][0];
                    break;
                case 4:
                    line = matrix[0][1] + matrix[1][1] + matrix[2][1];
                    break;
                case 5:
                    line = matrix[0][2] + matrix[1][2] + matrix[2][2];
                    break;
                case 6:
                    line = matrix[0][0] + matrix[1][1] + matrix[2][2];
                    break;
                case 7:
                    line = matrix[0][2] + matrix[1][1] + matrix[2][0];
                    break;
            }
            if (line.equals("XXX")) {
                xxxCounter++;
            }
            else if (line.equals("OOO")) {
                oooCounter++;
            }
        }
        int x = 0;
        int o = 0;
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if (matrix[i][j].equals("X")) {
                    x++;
                } else if (matrix[i][j].equals("O")){
                    o++;
                }
            }
        }
        int dif = x-o;
        if (Math.abs(dif) >= 2) {
            return  "Impossible";
        }
        if (xxxCounter > 0 || oooCounter > 0) {
            if (oooCounter >= 1 && xxxCounter >= 1) {
                result = "Impossible";
            }
            else if (xxxCounter == 1) {
                result = "X wins";
            } else if (oooCounter == 1) {
                result = "O wins";
            }
        } else {
            if (x + o == 9) {
                result = "Draw";
            } else {
                result = "Game not finished";
            }
        }
        return result;
    }
}
