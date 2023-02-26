public class SudokuSolver {
    private static final int ROW_COLUMN = 9;

    public static void main(String[] args) {
        int[][] grid = {
                {6,7,0,5,4,2,0,9,1},
                {5,0,0,0,8,0,0,0,7},
                {2,9,0,7,1,3,0,5,4},
                {0,0,0,4,0,6,0,0,0},
                {0,0,1,0,0,0,5,0,0},
                {0,0,0,1,0,5,0,0,0},
                {7,8,0,2,6,4,0,3,5},
                {1,0,0,0,7,0,0,0,2},
                {4,2,0,3,5,1,0,8,9}
        };

        solver(grid);
        printGrid(grid);
    }


    private static void printGrid(int[][] grid){
        for (int i = 0; i < ROW_COLUMN; i++) {
            for (int j = 0; j < ROW_COLUMN; j++) {
                if(j % 3 == 2){
                    System.out.print(grid[i][j] + "|" + "\t");
                }else{
                    System.out.print(grid[i][j] + "\t");
                }
            }
            System.out.println();
            if(i % 3 == 2){
                System.out.println("__________________________________");
            }

        }
    }
    private static boolean isNumAlreadyInRow(int[][] grid, int num, int row){
        for (int i = 0; i < ROW_COLUMN; i++) {
            if(grid[row][i] == num){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumAlreadyInColumn(int[][] grid, int num, int column){
        for (int i = 0; i < ROW_COLUMN; i++) {
            if(grid[i][column] == num){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumAlreadyInSquare(int[][] grid, int num, int row, int column){
        int squareRow = row - (row % 3);
        int squareColumn = column - (column % 3);
        for (int i = squareRow; i < squareRow + 3; i++) {
            for (int j = squareColumn; j < squareColumn + 3; j++) {
                if(grid[i][j] == num){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkAll(int[][] grid, int num, int row, int column){
        return !isNumAlreadyInRow(grid, num, row) &&
                !isNumAlreadyInColumn(grid, num, column) &&
                !isNumAlreadyInSquare(grid, num, row, column);
    }

    private static boolean solver(int[][] grid){
        for (int row = 0; row < ROW_COLUMN; row++) {
            for (int column = 0; column < ROW_COLUMN; column++) {
                if(grid[row][column] == 0){
                    for (int num = 1; num <= ROW_COLUMN; num++) {
                        if(checkAll(grid, num, row, column)){
                            grid[row][column] = num;

                            if(solver(grid)){
                                return true;
                            }else{
                                grid[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}