## 用最少数量的箭引爆气球

[452. 用最少数量的箭引爆气球](https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/)

#### 思路：贪心

每个气球都是有宽度的，数组中用`points[i][0]`和`points[i][1]` 表示第`i + 1`气球的左边界和右边界。

1. 首先我们需要将所有气球按照右边界的大小进行排序。
2. 然后第一支箭尽可能地往当前能射到的第一个气球的右边靠，这样第一支箭引爆气球的数量就是最多的
3. 同理，第二支箭也要尽可能地往当前能射到的第一个气球的右边靠(射出第一支箭以后，引爆了第一支箭能射到的所有气球以后)，这样就保证了第二支箭引爆的气球的数量就是最多的
4. ... ...

#### 代码

```JAVA
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0){
            return 0;
        }
        // 按照每个气球的右侧边界排序
        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]);
        int res = 1;
        int shotPosition = points[0][1];
        for(int i = 0; i < points.length; i++){
            if (shotPosition <= points[i][1] && shotPosition >= points[i][0]) {
                continue;
            }
            shotPosition = points[i][1];
            res++;
        }
        return res;
    }
}
```

