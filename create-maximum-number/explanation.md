## 拼接最大数

[321. 拼接最大数](https://leetcode-cn.com/problems/create-maximum-number/)

#### 思路：单调栈

在做这道题之前，建议先做一下：[402. 移掉K位数字](https://leetcode-cn.com/problems/remove-k-digits/)

单调栈，顾名思义，存放的数据为有序的，所以也分单调递增栈和单调递减栈

- 单调递增栈：单调递增栈就是从栈底到栈顶数据是从小到大排列的
- 单调递减栈：单调递减栈就是从栈底到栈顶数据是从大到小排列的

我们先来看一下leetcode的402题，理解下单调栈

题目规定来给丁一个字符串，要求移除数中的k个数字，使得剩下的数字保持顺序的数字最小

因为我们需要删掉k位数字得到最小值，那么我们就让删除的数字尽量在高位

本题就需要维护一个单调递增栈

- 如果当前数字比栈顶更大，就还是一个递增状态，满足单调递增栈，则入栈
- 如果当前数字比栈顶更小，就要一直删除栈顶元素，直至，满足单调递增栈的状态
- 如果当前数字为0，并且栈为空，那么我们就不应该让0这个元素入栈

理清细节后，我们就可以写出代码：

代码：

```java
class Solution {
    public String removeKdigits(String num, int k) {
        // 单调栈:维护一个单调递增的栈
        if(k == num.length()){
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < num.length(); i++){
            while(!stack.isEmpty() && k > 0 && num.charAt(i) < stack.peek()){
                stack.pop();
                k--;
            }
            if(stack.isEmpty() && num.charAt(i) == '0'){
                continue;
            }
            stack.push(num.charAt(i));
        }
        while(k > 0){
            stack.pop();
            k--;
        }
        // num = "10"
        if(stack.isEmpty()){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
```



接下来让我们回到本题：

本题相当于要维护两个栈，而且因为题目要求的是最大数，所以本题维护的则是单调递增栈

虽然我们不知道每个数组中要取几个数字，不过我们知道，假设从第一个数组中取i个数字，那么第二个数组中则需要取k- i个数字；所以我们构造栈的思路和上一题实际上是一样的，代码如下：

```java
private static int[] findMaxSubsequence(int[] nums, int k) {
    if (k == 0) {
        return new int[]{};
    }
    if (nums.length == k) {
        return nums;
    }

    Stack<Integer> stack = new Stack<>();
    // 单调递减栈
    int left = nums.length - k;
    for (int i = 0; i < nums.length; i++) {
        while (!stack.isEmpty() && left > 0 && nums[i] > stack.peek()) {
            stack.pop();
            left--;
        }
        stack.push(nums[i]);
    }

    while (left > 0) {
        stack.pop();
        left--;
    }
    int[] res = new int[k];
    for (int i = k - 1; i >= 0; i--) {
        res[i] = stack.pop();
    }
    return res;
}
```

接下来的操作就是归并排序的merge操作，我们需要将两个数组组合成一个最大的数组，后面的步骤请详见我的代码。

#### 代码

Java：

```java
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int start = Math.max(0, k - n);
        int end = Math.min(k, m);
        int[] res = new int[k];
        for (int i = start; i <= end; i++) {
            int[] arr1 = findMaxSubsequence(nums1, i);
            int[] arr2 = findMaxSubsequence(nums2, k - i);
            int[] arr = merge(arr1, arr2);
            if (compare(arr, 0, res, 0) > 0) {
                res = arr;
            }
        }
        return res;
    }

    // 如果是负值说明 nums1 < nums2 正值说明 nums1 > nums2
    private static int compare(int[] nums1, int index1, int[] nums2, int index2) {
        while (index1 < nums1.length && index2 < nums2.length) {
            int diff = nums1[index1] - nums2[index2];
            if (diff != 0) {
                return diff;
            }
            index1++;
            index2++;
        }
        return (nums1.length - index1) - (nums2.length - index2);
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2;
        }
        if (nums2.length == 0) {
            return nums1;
        }
        int p1 = 0;
        int p2 = 0;
        int i = 0;
        int[] res = new int[nums1.length + nums2.length];
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                if (compare(nums1, p1, nums2, p2) > 0) {
                    res[i++] = nums1[p1++];
                } else {
                    res[i++] = nums2[p2++];
                }
            } else {
                res[i++] = nums1[p1] > nums2[p2] ? nums1[p1++] : nums2[p2++];
            }
        }
        while (p1 < nums1.length) {
            res[i++] = nums1[p1++];
        }
        while (p2 < nums2.length) {
            res[i++] = nums2[p2++];
        }
        return res;
    }

    private static int[] findMaxSubsequence(int[] nums, int k) {
        if (k == 0) {
            return new int[]{};
        }
        if (nums.length == k) {
            return nums;
        }

        Stack<Integer> stack = new Stack<>();
        // 单调递减栈
        int left = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && left > 0 && nums[i] > stack.peek()) {
                stack.pop();
                left--;
            }
            stack.push(nums[i]);
        }

        while (left > 0) {
            stack.pop();
            left--;
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
```



