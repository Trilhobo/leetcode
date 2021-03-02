package daily;

/**
 * @author luliangliang9534@100.me
 *
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 * 示例 1：
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *
 * 示例 2：
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 * 提示：
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1
 */
public class Daily0219 {

    /**
     * 解题思路：滑动窗口
     * 题意转换：找出数组A中包含k个0的最长子串
     */
    public static int longestOnes(int[] A, int K) {
        int left = 0, right = 0, countZero = 0, maxSize = 0;
        int length = A.length;
        while (right < length) {
            //如果右指针==0,那么0的个数+1
            if (A[right] == 0) {
                countZero++;
            }
            //如果0的个数大于k，那么左指针被迫右移
            while (countZero > K) {
                //如果左指针==0，那么0的个数-1，再循环对比K看是否还需要右移
                if (A[left++] == 0) {
                    countZero--;
                }
            }
            //满足条件那么和现有的最长子串长度进行对比取较大值
            maxSize = Math.max(maxSize, right - left + 1);
            //右指针继续右移开始新的一轮
            right++;
        }
        return maxSize;
    }

    /**
     * 官方解答
     */
    public int longestOnesOffice(int[] A, int K) {
        int n = A.length;
        int[] P = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            P[i] = P[i - 1] + (1 - A[i - 1]);
        }

        int ans = 0;
        for (int right = 0; right < n; ++right) {
            int left = binarySearch(P, P[right + 1] - K);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] A = {1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1};
        int i = longestOnes(A, 3);
        System.out.println(i);
    }

}
