package daily;

/**
 * @author luliangliang9534@100.me
 *
 * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 *
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1,col1) ，右下角为 (row2,col2) 。
 *
 *
 * 上图子矩阵左上角(row1, col1) = (2, 1)，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 *
 *
 *
 * 示例：
 *
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 *
 *
 * 提示：
 *
 * 你可以假设矩阵不可变。
 * 会多次调用sumRegion方法。
 * 你可以假设row1 ≤ row2 且col1 ≤ col2 。
 *
 *
 * 此版本是一维数组前缀和方法
 */
class NumMatrix {

    int[][] sums;

    public NumMatrix(int[][] matrix) {
        if(matrix.length > 0) {
            sums = new int[matrix.length][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    sums[i][j + 1] = sums[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += sums[i][col2+1] - sums[i][col1];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        int sumRegion = numMatrix.sumRegion(1, 1, 2, 2);
        System.out.println(sumRegion);
    }
}
