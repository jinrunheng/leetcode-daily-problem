## 一：爬楼梯

[70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)

#### 解题思路：动态规划

思路分析：我们每次可以爬1或2级台阶。

也就是说，如果我们要爬到第`i`级台阶，我们可以：爬到第`i - 1`级台阶之后再爬1级台阶；也可以爬到第`i - 2`级台阶之后再爬2级台阶。

所以，我们就可以写出递归的代码：

```java
class Solution {
    public int climbStairs(int n) {
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
```

但是这样，就会造成大量的重复计算，导致提交超时；所以，我们可以使用动态规划，用额外的空间消耗来记录每次递归造成重复计算的步骤。

递归是一种自上而下的思想，而动态规划则是一种自下而上逐步递推的思想。

动态规划步骤：

1. 定义状态转移方程
2. 给定方程初始值
3. 递推实现

首先,定义`dp`数组与状态转移方程：

定义`dp`数组：`dp[i]`表示爬到第`i`级台阶共有多少种方法

经上述分析，我们可以知道：爬到第`i`级台阶的总方法数为：爬到第`i-1`级台阶的方法数与爬到第`i-2`级台阶的方法数之和。

所以可得：状态转移方程

```
dp[i] = dp[i - 1] + dp[i - 2]  
```

初始值为：

```
dp[0] = 1;
dp[1] = 1;
```

最终返回结果为：

```
dp[n - 1] + dp[n - 2]
```

#### 代码

*Java*

```java
class Solution {
    public int climbStairs(int n) {
        if(n <= 1){
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1] + dp[n - 2];
    }
}
```



## 二：使用最小花费爬楼梯

[746. 使用最小花费爬楼梯](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)

#### 解题思路：动态规划

本题其实与爬楼梯的思想是一样的，依旧使用动态规划进行求解

动态规划步骤：

1. 定义状态转移方程
2. 给定方程初始值
3. 递推实现

定义`dp`数组，`dp[i]`表示到达第`i`级台阶 总共耗费的体力花费值

我们可以得到状态转移方程为：

```
dp[i] = min(dp[i - 1],dp[i - 2]) + cost[i]
```

方程初始值：

```
dp[0] = cost[0] // 到达第0个台阶花费为cost[0]
dp[1] = cost[1] // 到达第1个台阶花费为cost[1]
```

最终返回结果：

因为我们要到达阶梯顶部，所以最后我们只需要到达倒数第1级台阶然后迈一步，或者到达倒数第2级台阶然后迈2步

所以返回：

```
min(dp[cost.length - 1],dp[cost.length - 2])
```

#### 代码

*Java*

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i = 2; i < cost.length; i++){
            dp[i] = Math.min(dp[i - 1],dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 1],dp[cost.length - 2]);
    }
}
```

