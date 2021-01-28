## 724. 寻找数组的中心索引

[724. 寻找数组的中心索引](https://leetcode-cn.com/problems/find-pivot-index/)

#### 解题思路一：常规解法

常规做法：

- 遍历数组
- 计算当前遍历索引的左侧和以及右侧和
- 如果左侧和等于右侧和则返回当前索引，遍历完数组都没有相等的情况就返回`-1`

该算法的时间复杂度为:*O(N ^ 2)*

#### 代码

*Java*

```java
class Solution {
    public int pivotIndex(int[] nums) {
        if(nums.length < 3){
            return -1;
        }
        for(int i = 0; i < nums.length; i++){
            int leftSum = getArrSumOfRange(nums,0,i - 1);
            int rightSum = getArrSumOfRange(nums,i + 1,nums.length - 1);
            if(leftSum == rightSum){
                return i;
            }
        }
        return -1;
    }

    private int getArrSumOfRange(int[] nums,int l,int r){
        int sum = 0;
        for(int i = l; i <= r; i++){
            sum += nums[i];
        }
        return sum;
    }
}
```

#### 解题思路二：前缀和

参考官方题解：

记数组的全部元素之和为*total*，当遍历到第*i*个元素时，假设左侧元素之和为*sum*

那么右侧元素之和则为：*total - nums[i] - sum*

如果要满足题目要求，左侧和与右侧和相等时，满足：

```java
sum = total - nums[i] - sum
```

即：

```java
2 * sum + nums[i] = total
```

这样我们可以仅通过*O(N)*的时间复杂度得到结果

#### 代码

*Java*

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int total = 0;
        for(int i = 0; i < nums.length; i++){
            total += nums[i];
        }
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(2 * sum + nums[i] == total){
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
```

*Java8* 引入的*stream*可以简化数组求和的操作：

```java
int total = Arrays.stream(nums).sum();
```





