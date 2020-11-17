## 距离顺序排列矩阵单元格

[1030. 距离顺序排列矩阵单元格](https://leetcode-cn.com/problems/matrix-cells-in-distance-order/)

#### 方法一：map

看到本题，鄙人脑子里最先反应出来的方法就是遍历矩阵，然后将每一个点的坐标信息与这个点和`(r0,c0)`之间的距离整合在一起存储起来，这样自然而然就想到了使用`map`

思路如下：

1. 遍历矩阵，将每一个点与这个点到`(r0,c0)`的距离存储到`map`中，`key`存储坐标信息，`value`存储距离
2. 将`map`按照`value`进行排序，将排序的结果封装成一个`list`
3. 将`list`转换为返回的数组

代码如下：

```java
class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        
        Map<int[],Integer> map = new HashMap<>();
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                map.put(new int[]{i,j},(Math.abs(i - r0) + Math.abs(j - c0)));
            }
        }
        
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .toArray(new int[R * C][]);

    }
}
```

代码的运行时间，并不理想，只击败了7.38%的用户(很好奇谁被我击败了)

#### 方法二：桶排序

思路：

桶排序：将曼哈顿距离作为桶的下标

将矩阵中每个坐标与`(r0,c0)`的曼哈顿距离作为桶的下标，桶内存储点的坐标值，最后将桶从头至尾遍历并收集返回即可。



问题是如何确定桶的大小？

桶下标的最大值应该是在这个矩阵中曼哈顿距离的最大值

曼哈顿距离：`|r - r0| + |c - c0|`

当 `r = 0`或 `r = R - 1`的时候 `|r - r0|` 有最大值

当 `c = 0`或 `c = C - 1`的时候 `|c - c0|` 有最大值

所以我们可以确定：桶下标的最大值应为:`Math.max(r0,R - 1 - r0) + Math.max(c0,C - 1 - c0)`

然后，后面的东西就很简单了，请看代码吧，和官方题解的基本一致

```java
class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 桶排序
        int maxDist = Math.max(r0,R - 1 - r0) + Math.max(c0,C - 1 - c0);
        // 初始化桶
        List<List<int[]>> bucket = new ArrayList<>();
        
        for(int i = 0; i <= maxDist; i++){
            bucket.add(new ArrayList<>());
        }
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                bucket.get(getDist(i,j,r0,c0)).add(new int[]{i,j});
            }
        }
        int[][] res = new int[R * C][0];
        int index = 0;
        for(int i = 0 ; i <= maxDist; i++){
            for(int[] b : bucket.get(i)){
                res[index++] = b;
            }
        }
        return res;
    }

    private static int getDist(int r1,int c1,int r2,int c2){
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
```

