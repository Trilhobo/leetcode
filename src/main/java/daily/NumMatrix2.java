package daily;

/**
 * @author luliangliang9534@100.me
 */
public class NumMatrix2 {

    int[][] sums;

    public NumMatrix2(int[][] matrix) {
        if (matrix.length > 0) {
            sums = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2 + 1][col2 + 1] - sums[row1][col2+1] -sums[row2+1][col1] + sums[row1][col1];
    }
}
