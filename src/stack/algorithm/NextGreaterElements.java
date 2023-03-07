package stack.algorithm;

import java.util.Arrays;
import java.util.Stack;

/*
    【503 下一个更大元素 II】给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），
                          返回 nums 中每个元素的 下一个更大元素 。
                          数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，
                          这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
    【示例 1】
            输入: nums = [1,2,1]
            输出: [2,-1,2]
            解释: 第一个 1 的下一个更大的数是 2；
            数字 2 找不到下一个更大的数；
            第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
    【示例 2】
            输入: nums = [1,2,3,4,3]
            输出: [2,3,4,-1,4]
     =====================================================================================
    【解题思路】环形的解决方案可以参考环形队列
              i = i % nums.length;
 */
public class NextGreaterElements {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];

        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        stack.push(0);

        for (int ii = 1; ii < 2 * nums.length; ii++) {
            int i = ii % nums.length;
            if (nums[i] <= nums[stack.peek()])
                stack.push(i);
            else{
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                    result[stack.peek()] = nums[i];
                    stack.pop();
                }
                stack.push(i);
            }
        }

        return result;
    }
}
