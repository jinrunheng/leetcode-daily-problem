## Dota2 参议院

[649. Dota2 参议院](https://leetcode-cn.com/problems/dota2-senate/)

#### 解题思路：贪心 + 队列

本题的贪心原则为：

优先让下一个非自己一组的人失去权

举例：

```
RDDRDRD

第一轮

[0] -> [1] 失权  RXDRDRD
[2] -> [3] 失权  RXDXDRD
[4] -> [5] 失权  RXDXDXD
[5] -> [0] 失权  XXDXDXD

第二轮Dire直接宣布胜利
```

为了模拟整个投票过程，我们选择使用队列这样的数据结构。

原因有二：

1. 如果就字符串的字符数组模拟投票过程，这是一个循环的数据结构，处理起来非常不方便
2. 使用队列易于判断终止条件，当天辉方和夜魇方代表的队列一方为空时，表示已经判出了结果

#### 代码

*JavaScript*

```javascript
/**
 * @param {string} senate
 * @return {string}
 */
var predictPartyVictory = function(senate) {
    let radiant_queue = []
    let dire_queue = []
    
    for(let i = 0; i < senate.length; i++){
        if(senate[i] == 'R'){
            radiant_queue.push(i)
        }else {
            dire_queue.push(i)
        }
    }

    while(radiant_queue.length != 0 && dire_queue.length != 0){
        if(radiant_queue[0] < dire_queue[0]){
            dire_queue.shift()
            radiant_queue.push(radiant_queue.shift() + senate.length)
        }else {
            radiant_queue.shift()
            dire_queue.push(dire_queue.shift() + senate.length)
        }
    }

    return radiant_queue.length == 0 ? "Dire" : "Radiant"
};
```

*Java*

```java
class Solution {
    public String predictPartyVictory(String senate) {
        // RDDRDRD
        // [0] -> [1] 失权  RXDRDRD
        // [2] -> [3] 失权  RXDXDRD
        // [4] -> [5] 失权  RXDXDXD
        // [5] -> [0] 失权  XXDXDXD
        // 第二轮Dire直接宣布胜利

        // 贪心原则：
        // 优先让下一个非自己一组的人失权
        // 例如:DDRR [0] -> [2] 失权
        
        Queue<Integer> radiant_queue = new LinkedList<>();
        Queue<Integer> dire_queue = new LinkedList<>();

        for(int i = 0; i < senate.length(); i++){
            if(senate.charAt(i) == 'R'){
                radiant_queue.offer(i);
            }else {
                dire_queue.offer(i);
            }
        }

        while(!radiant_queue.isEmpty() && !dire_queue.isEmpty()){
            if(radiant_queue.peek() < dire_queue.peek()){
                dire_queue.poll();
                radiant_queue.offer(radiant_queue.poll() + senate.length());
            }else {
                radiant_queue.poll();
                dire_queue.offer(dire_queue.poll() + senate.length());
            }
        }

        return radiant_queue.isEmpty() ? "Dire" : "Radiant";
    }
}
```

