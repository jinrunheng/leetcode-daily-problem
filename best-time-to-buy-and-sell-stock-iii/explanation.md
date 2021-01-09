## 123. 买卖股票的最佳时机 III

[123. 买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)

#### 解题思路：动态规划

动态规划三大步：

1. 定义状态转移方程
2. 给定方程初始值
3. 递推实现



因为本题要求最多可以进行两次交易，所以我们可以知道，在任意一天结束之后，我们会处于以下五个状态中的一种：

- 未进行过任何操作
- 只进行过一次买的操作（之前买过，这次没有执行任何操作 **或** 这次买）
- 进行过一次完整的交易，即买卖各一次（之前卖过一次，这次没有执行任何操作 **或**  这次卖）
- 第二次买（之前已经第二次买过这次没有执行任何操作 **或** 这次买第二次 ）
- 进行过两次完整的交易，即买卖各两次（之前已经卖了第二次，这次没有执行任何操作 **或**  这次卖第二次）

创建dp数组，`dp[i]`表示在第`i`天过后，获得利润情况

- 未进行过任何操作 : `dp[i][0] = 0`
- 只进行过一次买的操作：`dp[i][1] = max(dp[i - 1][1], -prices[i])`
- 进行过一次完整的交易：`dp[i][2] = max(dp[i - 1][2],dp[i - 1][1] + prices[i])`
- 第二次买：`dp[i][3] = max(dp[i - 1][3],dp[i - 1][2] - prices[i])`
- 进行过两次完整的交易：`dp[i][4] = max(dp[i - 1][4],dp[i - 1][3] + prices[i])`



方程初始值：

- 未进行过任何操作则代表手中的利润一直为`0`
- `dp[0][1] = -prices[0]`
- `dp[0][2] = 0`
- `dp[0][3] = -prices[0]`
- `dp[0][4] = 0`



递推实现详见代码

#### 代码

*Java*

```java
class Solution {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for(int i = 1; i < prices.length; i++){
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); 
            dp[i][2] = Math.max(dp[i - 1][2],dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3],dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4],dp[i - 1][3] + prices[i]);
        }

        return Math.max(dp[prices.length - 1][2],dp[prices.length - 1][4]);
    }
}
```

其实本代码可以有很多优化之处，我们可以只用几个变量来代替，这样就省去了开辟数组的消耗，优化如下：

*java*

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 只进行过一次买的操作
        int buy_once = -prices[0];
        // 进行过一次完整的交易
        int sell_once = 0;
        // 第二次买
        int buy_twice = -prices[0];
        // 进行过两次完整的交易
        int sell_twice = 0;

        for(int i = 1; i < prices.length; i++){
            buy_once = Math.max(buy_once, -prices[i]);
            sell_once = Math.max(sell_once,buy_once + prices[i]);
            buy_twice = Math.max(buy_twice,sell_once - prices[i]);
            sell_twice = Math.max(sell_twice,buy_twice + prices[i]);
        }
        return Math.max(sell_once,sell_twice);
    }
}
```

