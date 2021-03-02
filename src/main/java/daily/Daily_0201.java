package daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author luliangliang
 *
 * 888. 公平的糖果棒交换
 *
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1]是 Bob 必须交换的糖果棒的大小。
 *
 *
 * 输入：A = [1,2,5], B = [2,4]
 * 输出：[5,4]
 *
 * 1 <= A.length <= 10000
 * 1 <= B.length <= 10000
 * 1 <= A[i] <= 100000
 * 1 <= B[i] <= 100000
 * 保证爱丽丝与鲍勃的糖果总量不同。
 * 答案肯定存在。
 */
public class Daily_0201 {

    /**
     * 交换的糖果大小差，是两人糖果总和差的一半
     */
    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        Arrays.sort(B);
        int diff = (sumA - sumB) / 2;
        for (int temp : A) {
            int findIndex;
            if ((findIndex = Arrays.binarySearch(B, temp - diff)) >= 0) {
                return new int[]{temp, B[findIndex]};
            }
        }
        return null;
    }

    /**
     * 官方解答
     */
    public static int[] fairCandySwapOfficial(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int diff = (sumA - sumB) / 2;
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            set.add(a);
        }
        int[] result = new int[2];
        for (int b : B) {
            int find;
            if (set.contains((find = b + diff))) {
                result[0] = find;
                result[1] = b;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = fairCandySwapOfficial(new int[]{35, 17, 4, 24, 10}, new int[]{63, 21});
        System.out.println(Arrays.toString(ints));
    }
}
