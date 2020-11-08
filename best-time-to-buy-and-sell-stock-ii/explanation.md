## 买卖股票的最佳时机II

[题目链接](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

### 贪心

贪心思路：**尽可能多次买卖**

如果第i天的股票价格要小于第i+1天的股票价格，我们就在第i天买入，在第i+1天卖出

示例1:

```
[1,2,3,4,5]
```

我们并不需要在完美的时机买入卖出；而是要尽可能多次交易：

第1天买入，在第2天卖出；第2天买入，在第3天卖出；第3天买入，在第4天卖出；第4天买入，在第5天卖出；这样我们收获的利润即是最大利润：4

它和我们在完美的时间买卖股票收益的利润是等效的，即：第1天买入，在第5天卖出。



示例2：

```
[7,1,5,3,6,4]
```

如果要想获得最大利润，我们需要在第2天买入，在第3天卖出；第4天买入，在第5天卖出；最大利润为：7



### 完整代码

Java:

```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for(int i = 0; i < prices.length - 1; i++){
            if(prices[i] < prices[i + 1]){
                maxProfit += prices[i + 1] - prices[i];
            }
        }
        return maxProfit;
    }
}
```

Go:

```go
func maxProfit(prices []int) int {
    maxProfit := 0
    for i := 0; i < len(prices) - 1; i++ {
        if prices[i] < prices[i + 1] {
            maxProfit += prices[i + 1] - prices[i]
        }
    } 
    return maxProfit
}
```





