package Demo.Arr;

public class demo03 {

    public static void main(String[] args) {
        var matrix1 = new int[][]{
                {1, 2, 3, 4, 5 },
                {6, 7, 8, 9, 10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25},
        };
        var matrix2 = new int[][]{
                {1, 2, 3, 4},
                {6, 7, 8, 9},
                {11,12,13,14},
                {16,17,18,19},
                {21,22,23,24},
        };
        var matrix3 = new int[][]{
                {1, 2, 3, 4, 5 },
                {6, 7, 8, 9, 10},
                {11,12,13,14,15},
                {16,17,18,19,20},
        };
        var matrix4 = new int[][]{
                {1, 2, 3, 4 },
                {6, 7, 8, 9},
                {11,12,13,14},
                {16,17,18,19},
        };
        printMatrix(matrix4);
    }

    public static void printMatrix(int[][] matrix)
    {
        // 下标
        int rows_start = 0;
        int rows_end   = matrix.length - 1;
        int cols_start = 0;
        int cols_end   = matrix[0].length - 1;

        while (cols_start <= cols_end && rows_start <= rows_end){
            // 从 cols_start 打印到 cols_end
            for(int i = cols_start; i <= cols_end; i++)
            {
                System.out.printf("%d\t",matrix[rows_start][i]);
            }
            rows_start++; // 下移
            System.out.println();

            // 从 rows_start 打印到 rows_end
            for(int j = rows_start; j <=rows_end; j++)
            {
                System.out.printf("%d\t",matrix[j][cols_end]);
            }
            cols_end--; // 左移
            System.out.println();

            // 从 cols_end 打印到 cols_start
            for(int i = cols_end; i >= cols_start; i--)
            {
                System.out.printf("%d\t",matrix[rows_end][i]);
            }
            rows_end--; // 上移
            System.out.println();

            // 从 rows_end 打印到 rows_start
            for(int j = rows_end; j >= rows_start; j--)
            {
                System.out.printf("%d\t",matrix[j][cols_start]);
            }
            cols_start++; // 右移
            System.out.println();
        }

    }

}

