package Demo.Arr;

// 题目: 二维数组中的查找
// 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。
// 给定一个数，判断这个数是否在该二维数组中。
// 解题：根据 target 和当前元素的大小关系来快速地缩小查找区间，每次减少一行或者一列的元素。

public class demo02 {
    public static void main(String[] args) {
        var matrix = new int[][]{
                {1,  4,  7,  11, 15, 34},
                {2,  5,  8,  12, 19, 56},
                {3,  6,  9,  16, 22, 61},
                {10, 13, 14, 17, 24, 78},
                {18, 21, 23, 26, 30, 89}
        };
        var p = find(matrix,78);
        System.out.println(p);
    }

    static class Position{
        public int x;
        public int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static Position find(int[][] matrix , int target )
    {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new Position(-1,-1);

        int rows = matrix.length;
        int cols = matrix[0].length;
        // int[r][c]
        // 从左下角开始找
        int r = rows - 1;
        int c = 0;
        while (r >= 0 && c <= cols - 1)
        {
            System.out.printf("if %d == %d\n",matrix[r][c], target);
            if(matrix[r][c] == target) {
                return new Position(r, c);
            } else if(matrix[r][c] > target){
                r--; // 向上调整一行
            }else{
                c++; // 向右调整一行
            }
        }
        return new Position(-1,-1);
    }

}
