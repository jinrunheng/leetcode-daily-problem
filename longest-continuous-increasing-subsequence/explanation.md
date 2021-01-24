## 674. 最长连续递增序列

[674. 最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/)

#### 解题思路一：栈

我们需要维护一个单调递增的栈,并且维护一个返回值`res`的列表

初始化：

```java
Stack<Integer> stack = new Stack<>();
stack.push(nums[0]);
int res = 1;
```

从索引`1`的位置开始遍历`nums`数组，如果当前遍历到的数字要大于栈顶数字，那么就入栈，并且`res++`；否则就将栈清空，将`res`添加到列表中，重新维护一个单调递增的栈。最后我们只需要再遍历一次列表，找到最大的那个`res`返回即可。

#### 代码

*Java*

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        int res = 1;
        List<Integer> results = new ArrayList<>();
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > stack.peek()){
                stack.push(nums[i]);
                res++;
                if(i == nums.length - 1){
                    results.add(res);
                }
            }else {
                results.add(res);
                stack.clear();
                stack.push(nums[i]);
                res = 1;
            }
        }
        res = Integer.MIN_VALUE;
        for(int i = 0; i < results.size(); i++){
            if(results.get(i) > res){
                res = results.get(i);
            }
        }
        return res;
    }
}
```

#### 解题思路二：贪心

维护一个下标`start`;它代表连续递增序列的开始下标

维护一个变量`res`,表示返回结果

遍历数组

- 如果下标`i > 0`,并且`nums[i] <= nums[i - 1]`，说明当前元素小于或等于上一个元素，那么就重置`start = i`
- 否则就说明，此时下标范围`[start,i]`满足连续子序列是递增序列，此时的长度则为`i - start + 1`

*Java*

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int start = 0;
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] <= nums[i - 1]){
                start = i;
            }
            res = Math.max(res,i - start + 1);
        }
        return res;
    }
}
```

