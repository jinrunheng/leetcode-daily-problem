## 柠檬水找零

[860. 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/)

#### 解题思路：贪心

本题的贪心原则：

- 5美元不需要找零；手里拥有的5美元的数量加1
- 10美元只能用5美元找零；手里拥有的5美元的数量减1，拥有的10美元的数量加1
- 20美元优先使用1张10美元和1张5美元找零，如果没有10美元则使用3张五美元找零

#### 代码

*Go:*

```go
func lemonadeChange(bills []int) bool {
    five_amount,ten_amount := 0,0
    for i := 0; i < len(bills); i++ {
        if bills[i] == 5 {
            five_amount += 1
        }

        if bills[i] == 10 {
            if five_amount < 1 {
                return false
            }else {
                five_amount -= 1
                ten_amount += 1
            }
        }

        if bills[i] == 20 {
            if five_amount < 1 {
                return false
            }else {
                if ten_amount < 1 {
                    if five_amount < 3 {
                        return false
                    }else {
                        five_amount -= 3
                    }
                }else {
                    ten_amount -= 1
                    five_amount -= 1
                }
            }
        }
    }
    return true
}
```

*Java:*

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five_amount = 0;
        int ten_amount = 0;
        for(int i = 0; i < bills.length; i++){
            if(bills[i] == 5){
                five_amount++;
            }
            if(bills[i] == 10){
                if(five_amount < 1){
                    return false;
                }else{
                    five_amount--;
                    ten_amount++;
                }
            }
            if(bills[i] == 20){
                if(five_amount < 1){
                    return false;
                }else {
                    if(ten_amount < 1){
                        if(five_amount < 3){
                            return false;
                        }else {
                            five_amount -= 3;
                        }
                    }else {
                        ten_amount--;
                        five_amount--;
                    }
                }
            }
        }
        return true;
    }
}
```

