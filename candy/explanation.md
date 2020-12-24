## 135. 分发糖果

[135. 分发糖果](https://leetcode-cn.com/problems/candy/)

#### 解题思路：贪心算法

该题目中有如下要求：

- 每个孩子至少分配到 1 个糖果。
- 相邻的孩子中，评分高的孩子必须获得更多的糖果。



我们维护一个数组`candies`代表每一个孩子所获得到的糖果的数量；

首先，我们知道每一个孩子无论得分多少，至少会获得一个糖果，所以，我们先分配给每个人一个糖果

```java
Arrays.fill(candies,1);
```

贪心策略：

本题目需要确定一边，然后再确定另一边，即：比较每一个孩子的左边或右边然后再比较右边或左边，如果两边同时考虑那么就会顾此失彼。

总的来说就是：

- 每个孩子先向左边看看：如果我比他分数高，那么我就要比他多1个糖果
- 然后每个孩子再向右边看看：如果我分数比他高，那么我就要比他多1个糖果

实现方法：

- 先正序遍历，如果后一位比前一位的分数高，那么就给后一位比前一位多一个糖果
- 然后再倒序遍历，如果前一位比后一位的分数高，并且糖果数量还没有后一位多，就给前一位比后一位多一个糖果

#### 代码

*Java*

```java
class Solution {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        int[] candies = new int[ratings.length];
        Arrays.fill(candies,1);
        // 先正序遍历，如果后一位比前一位的分数高，那么就给后一位比前一位多一个糖果
        for(int i = 0; i < ratings.length - 1; i++){
            if(ratings[i + 1] > ratings[i]){
                candies[i + 1] = candies[i] + 1;
            }
        }
		// 倒序遍历，如果前一位比后一位的分数高，并且糖果数量还没有后一位多，就给前一位比后一位多一个糖果
        for(int i = ratings.length - 1; i > 0; i--){
            if(ratings[i - 1] > ratings[i] && candies[i - 1] <= candies[i]){
                candies[i - 1] = candies[i] + 1;
            }
        }
        
        int res = 0;
        for(int c : candies){
            res += c;
        }
        return res;
    }
}
```

