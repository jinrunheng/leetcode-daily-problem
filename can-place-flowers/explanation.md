## 605. 种花问题

[605. 种花问题](https://leetcode-cn.com/problems/can-place-flowers/)

#### 解题思路：贪心

题目要求能否在不打破种植规则的情况下种`n`朵花

我们只需要判断，当前的花坛中最多可以种植多少朵花，然后判断最多种植的数量是否大于等于`n`即可

种植最多花的策略：

- 如果花坛的第一个位置和第二个位置为`0`,那么优先在`flowerbed[0]`处种植一朵花，这样就是最优秀的种植策略
- 其他位置如果满足`flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0 `,那么就可以在`i`位置种植一朵花
- 最后，我们要检查花坛最后面，如果最后一个位置和倒数第二个位置为`0`,那么在`flowerbed[len - 1]`也就是最后一个位置还可以种植一朵花

实现思路：

- 维护一个变量`max`表示花坛最多可以种植花的数量

- 遍历`flowerbed`数组，如果满足种花规则，那么就原地修改数组使得`flowerbed[i] = 1`,并且更新`max`值：`max++`
- 最后返回`max >= n`

#### 代码

*Java*

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int max = 0;
        if(n == 0){
            return true;
        }
        if(flowerbed.length == 1){
            return flowerbed[0] == 0 && n == 1;
        }
        
        for(int i = 0; i < flowerbed.length; i++){
            if(i == 0){
                if(flowerbed[0] == 0 && flowerbed[1] == 0){
                    flowerbed[0] = 1;
                    max++;
                }
            }else if(i == flowerbed.length - 1){
                if(flowerbed[i] == 0 && flowerbed[i - 1] == 0){
                    flowerbed[i] = 1;
                    max++;
                }
            }else {
                if(flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0){
                    flowerbed[i] = 1;
                    max++;
                }
            }

            if(max >= n){
                return true;
            }
        }
        return max >= n;
    }
}
```

