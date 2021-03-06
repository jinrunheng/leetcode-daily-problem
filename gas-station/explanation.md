## 超级易懂的贪心思路~加油站

[134. 加油站](https://leetcode-cn.com/problems/gas-station/)

#### 方法

如示例：

```
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
```

**审题：**

gas数组代表在环路上，每个加油站补充的汽油，如`gas[0]` 为1，它代表在第0号加油站补充了1升汽油

cost数组代表在环路上消耗的汽油，如`cost[0]`为3，它代表从第0号加油站到第1号加油站需要消耗3升汽油

**思考：**

什么时候，汽车无法绕环路行驶一周？

很显然，汽油的总消耗大于总的补充量时，无论如何，汽车都无法完成环绕一周的任务。

我们定义汽油的剩余量为`left[i] = gas[i] - cost[i]`；如果汽车可以完成绕环路行驶一周的任务，那么一定有，在某个加油站汽车出发，直到汽车返回到这个加油站，汽车油箱内的油量一定是时刻保证大于等于0的；也就是说汽油剩余量的累加和时刻都是大于等于0的。

我们将每个阶段剩余的油量表示出来，声明为数组`left`

```
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
left = [-2,-2,-2,3,3]
```

从第3号加油站出发（`gas[3]`），我们就能保证，汽车环绕一周，每个时刻油箱的油量都满足大于等于0

1. 第3号加油站出发时，到达第4号加油站时，汽车油箱油量为3升
2. 第4号加油站出发时，到达第0号加油站时，汽车油箱油量为6升(3 + 3)
3. 第0号加油站出发时，到达第1号加油站时，汽车油箱油量为4升(3 + 3 - 2)
4. 第1号加油站出发时，到达第2号加油站时，汽车油箱油量为2升(3 + 3 - 2 - 2)
5. 第2号加油站出发时，回到第3号加油站时，汽车油箱油量为0升(3 + 3 - 2 - 2 - 2)

从其他位置出发，则完成不了任务

**算法思路**

定义返回的索引为`res`;

在遍历`left`数组时，统计总的累加和`totalSum`，以及一个阶段的累加和`sum`;

如果`sum`出现小于零的情况,就说明汽车油箱出现了负值的情况，重置索引`res`和`sum`,令`res = i + 1`,`sum = 0`

最后判断`totolSum` 是否小于0；如果小于0，则说明无法完成任务，返回`-1`;否则则可以完成任务,返回`res`

#### 代码

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] left = new int[gas.length];
        for(int i = 0; i < left.length; i++){
            left[i] = gas[i] - cost[i];
        }
         
        int res = 0;
        int sum = 0;
        int totalSum = 0;
        for(int i = 0; i < left.length; i++){
            totalSum += left[i];
            sum += left[i];
            if(sum < 0){
                sum = 0;
                res = i + 1;
            }
        }
        return totalSum < 0 ? -1 : res;
    }
}
```







