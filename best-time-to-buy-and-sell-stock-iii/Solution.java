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