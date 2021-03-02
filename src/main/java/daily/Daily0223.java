package daily;

/**
 * @author luliangliang9534@100.me
 *
 * 今天，书店老板有一家店打算试营业customers.length分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 *
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 *
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续X分钟不生气，但却只能使用一次。
 *
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 *
 * 示例：
 *
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 *
 * 提示：
 *
 * 1 <= X <=customers.length ==grumpy.length <= 20000
 * 0 <=customers[i] <= 1000
 * 0 <=grumpy[i] <= 1
 */
public class Daily0223 {

    /**
     * sum = grumpy[i] == 0 的下标，customers[i]中对应下标的和
     *
     * 思路：第一眼看到想到的是滑动窗口，X是连续的
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum = 0;
        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 0) {
                sum = sum + customers[i];
            }
        }
        //获取第一次left = 0 ，right = X-1时，秘技的收益
        int beginIncrease = 0;
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) {
                beginIncrease = beginIncrease + customers[i];
            }
        }
        int endIncrease = beginIncrease;
        //窗口右移，获取新的收益，与上一次相比，取较大值，一直右移到尾部，获取最大的收益
        for (int i = X; i < grumpy.length; i++) {
            beginIncrease = beginIncrease + grumpy[i] * customers[i] - grumpy[i - X] * customers[i - X];
            endIncrease = Math.max(beginIncrease, endIncrease);
        }

        return sum + endIncrease;
    }

    public static void main(String[] args) {
        int[] customers = {3};
        int[] grumpy = {1};
        int i = maxSatisfied(customers, grumpy, 1);
        System.out.println(i);
    }

}
