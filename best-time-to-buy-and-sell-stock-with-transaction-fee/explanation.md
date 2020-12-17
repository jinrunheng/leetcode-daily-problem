## 买卖股票的最佳时机含手续费

[714. 买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

#### 解题思路：动态规划

动态规划三步走：

1. 定义状态转移方程
2. 给定方程初始值
3. 递推实现

声明`dp`数组：`dp[i][2]`

其中：

`dp[i][0]`  代表第`i`天 没有持有股票时的最大利润；分为两种情况：

1. 第`i - 1`天也没有持有股票
2. 第`i - 1`天持有了股票，第`i`天卖出

所以，我们就可以得到：`dp[i][0] = max(dp[i - 1][0],dp[i - 1][1] + prices[i] - fee)`

`dp[i][1]` 代表 第`i`天 当持有股票时的最大利润；分为两种情况：

1. 第`i - 1`天也持有了股票
2. 第`i - 1`天没有持有股票，在第`i`天买入

所以,我们可以得到：`dp[i][1] = max(dp[i - 1][1],dp[i - 1][0] - prices[i])`

最终返回的结果应该为:`dp[prices.length - 1][0]`

#### 代码

*Java*

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        // 声明dp数组：dp[i][2]
        // dp[i][0] 代表 第i天 没有持有股票时的最大利润；分为两种情况：
        // 1.第i - 1天也没有持有股票
        // 2.第i - 1天持有了股票，第i天卖出
        // 所以dp[i][0] = max(dp[i - 1][0],dp[i - 1][1] + prices[i] - fee)

        // dp[i][1] 代表 第i天 当持有股票时的最大利润；分为两种情况：
        // 1. 第i - 1天也持有了股票
        // 2. 第i - 1天没有持有股票，在第i天买入
        // 所以dp[i][1] = max(dp[i - 1][1],dp[i - 1][0] - prices[i])

        // 最终结果应该返回: dp[prices.length - 1][0]
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0];
        for(int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0],dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
```

