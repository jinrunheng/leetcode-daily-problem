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