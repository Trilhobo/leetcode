package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author luliangliang9534@100.me
 *
 * 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1：
 *
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 *
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 *
 *
 * 提示：
 *
 * nums.length在1到 50,000 区间范围内。
 * nums[i]是一个在 0 到 49,999 范围内的整数
 */
public class Daily0220 {

    /**
     * 1、先找数组的度
     * 2、再找最短子数组
     */
    public static int findShortestSubArray(int[] nums) {
        //1、先找数组的度，使用hash，key为元素，value是出现的次数，最后次数最大的就是数组的度
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Object[] array = map.values().toArray();
        Arrays.sort(array);
        //2、求度
        int length = array.length;
        Integer max = (Integer) array[length - 1];
        List<Integer> integers = new ArrayList<>();
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(max)) {
                integers.add(entry.getKey());
            }
        }
        //3、滑动窗口,滑动窗口的边界条件就是nums[]度和度对应的元素
        int numsLength = nums.length;
        int degree = 50000;
        for (int i = 0; i < integers.size(); i++) {
            int left = 0, right = 0, res = 50000, count = 0;
            Integer target = integers.get(i);
            while (right < numsLength) {
                if (nums[right] == target) {
                    count++;
                }
                if (count == max) {
                    while (true) {
                        if (nums[left] != target) {
                            left++;
                        } else {
                            break;
                        }
                    }
                    res = Math.min(res, right - left + 1);
                }
                right++;
            }
            degree = Math.min(degree, res);
        }
        return degree;
    }

    public static int findShortestSubArray2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int degree = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            degree = Math.max(degree, map.get(num));
        }
        //此时已经知道degree就是度
        //滑动窗口
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int left = 0, right = 0, res = 50000;
        while (right < nums.length) {
            //将每个数字出现的次数都存在map中
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
            //如果某个元素出现的次数等于度的时候
            while (countMap.get(nums[right]) == degree) {
                countMap.put(nums[left], countMap.get(nums[left]) - 1);
                res = Math.min(res, right - left + 1);
                left++;
            }
            right++;
        }
        return res;
    }


    public static int findShortestSubArray3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int degree = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            degree = Math.max(degree, map.get(num));
        }
        //此时已经知道degree就是度
        //滑动窗口
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int right = 0, res = 50000;
        while (right < nums.length) {
            //将每个数字出现的次数都存在map中
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
            //如果某个元素出现的次数等于度的时候
            if (countMap.get(nums[right]) == degree) {
                int left = 0;
                while (left < nums.length && nums[left] != nums[right]) {
                    left++;
                }
                res = Math.min(res, right - left + 1);
            }
            right++;
        }
        return res;
    }

    /**
     * hash表
     *
     * Integer, int[]
     *
     * Integer : 元素
     * int[0] : 次数
     * int[1]: 第一次出现下标
     * int[2]: 最后一次出现下标
     */
    public static int findShortestSubArray4(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                map.get(num)[0]++;
                map.get(num)[2] = i;
            } else {
                map.put(num, new int[]{1, i, i});
            }
        }
        //查找int[]中出现次数最多的，并且int[2]-int[1]最小的
        int max = 0, min = 0;
        for (Entry<Integer, int[]> entry : map.entrySet()) {
            int[] value = entry.getValue();
            if (value[0] > max) {
                max = value[0];
                min = value[2] - value[1] + 1;
            } else if (value[0] == max && min > value[2] - value[1] + 1) {
                min = value[2] - value[1] + 1;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int[] A = {1, 2, 2, 3, 1, 4, 2};
        int shortestSubArray = findShortestSubArray4(A);
        System.out.println(shortestSubArray);
    }

}
