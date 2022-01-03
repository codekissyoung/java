package Demo;

import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix.length <= 0)
            return new ArrayList<>();

        int rows_start = 0;
        int rows_end = matrix.length - 1;
        int cols_start = 0;
        int cols_end = matrix[0].length - 1;
        ArrayList<Integer> ret = new ArrayList<>();

        while (true) {
            // 从 cols_start 打印到 cols_end
            for (int i = cols_start; i <= cols_end; i++) {
                ret.add(matrix[rows_start][i]);
            }
            if (rows_start == rows_end) break;
            rows_start++; // 下移

            // 从 rows_start 打印到 rows_end
            for (int i = rows_start; i <= rows_end; i++) {
                ret.add(matrix[i][cols_end]);
            }
            if (cols_start == cols_end) break;
            cols_end--; // 左移

            // 从 cols_end 打印到 cols_start
            if (rows_start <= rows_end) {
                for (int i = cols_end; i >= cols_start; i--) {
                    ret.add(matrix[rows_end][i]);
                }
            }
            if (rows_start == rows_end) break;
            rows_end--; // 上移

            // 从 rows_end 打印到 rows_start
            if (cols_start <= cols_end) {
                for (int i = rows_end; i >= rows_start; i--) {
                    ret.add(matrix[i][cols_start]);
                }
            }
            if (cols_start == cols_end) break;
            cols_start++; // 右移
        }
        return ret;
    }
}