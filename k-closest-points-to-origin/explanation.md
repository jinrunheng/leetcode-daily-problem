## 最接近原点的K个点

[题目链接](https://leetcode-cn.com/problems/k-closest-points-to-origin/)

#### 思路一：排序

直接调用排序API

代码如下：

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (o1, o2) -> o1[0] * o1[0] + o1[1] * o1[1] - (o2[0] * o2[0] + o2[1] * o2[1]));
        return Arrays.copyOfRange(points,0,K);
    }
}
```

#### 思路二：堆

本题属于TopK问题，TopK问题均可以使用堆这种数据结构解决，时间复杂度为O(nlogK)

- 创建一个大根堆,动态维护前K个最小的距离平方
- 先将前K个点的索引以及对应的距离的平方的数组放入大根堆中
- 从第K+1个点开始遍历，如果当前点的距离平方比堆顶的点的距离平方要小，就把堆顶的点弹出，再插入当前的点
- 遍历完成时，大根堆中存放的就是前K个距离最小的点

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < K; i++) {
            maxHeap.offer(new int[]{i, points[i][0] * points[i][0] + points[i][1] * points[i][1]});
        }
        for (int i = K; i < points.length; i++) {
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (distance < maxHeap.peek()[1]) {
                maxHeap.poll();
                maxHeap.offer(new int[]{i, distance});
            }
        }
        int res[][] = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[maxHeap.poll()[0]];
        }
        return res;
    }
}
```

#### 思路三：快排 partition

快排的**核心思想**就是partition，partition的作用是将数组中的某一个值(pivot)在一次partition之后，定位到整个数组都排好序的位置，并将比pivot小的数都放在左侧，比pivot大的数都放在右侧。partition方法返回的是pivot的索引` index`。

所以，在`index == k - 1`时，说明了前k个最小的值已经被划分出来了，虽然前k个最小值并不一定是排好序的。有以下几种情况：

1. 当`index == k-1` 说明前k个最小的值已经被切分好了，也是终止条件
2. 当`index < k-1` 说明我们需要继续对右侧进行切分
3. 当`index > k-1` 说明我们需要继续对左侧进行切分

代码如下：

Java:

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int l = 0;
        int r = points.length - 1;
        while (l < r) {
            int index = partition(points, l, r);
            if (index == K - 1) {
                break;
            } else if (index < K - 1) {
                // 需要对index的右边进行partition
                l = index + 1;
            } else {
                // 需要对index的左边进行partition
                r = index - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private static int partition(int[][] points, int start, int end) {
        int l = start - 1;
        int r = end;
        int cur = start;
        while (cur < r) {
            if (getDistance(points[cur]) < getDistance(points[end])) {
                swap(points, ++l, cur++);
            } else if (getDistance(points[cur]) > getDistance(points[end])) {
                swap(points, cur, --r);
            } else {
                cur++;
            }
        }
        swap(points, r, end);
        return r;
    }

    private static void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private static int getDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
```

Go:

```go
func kClosest(points [][]int, K int) [][]int {
    start := 0
    end := len(points) - 1
    for start < end {
        index := partition(points,start,end)
        if index == K - 1{
            break;
        } else if index < K - 1{
            start = index + 1 
        }else {
            end = index - 1
        }
    }
    return points[:K]
}

func partition(points[][] int,start int,end int) int{
    less := start - 1
    more := end
    cur := start
    for cur < more {
        if distance(points[cur]) < distance(points[end]){
            less++
            points[less],points[cur] = points[cur],points[less];
            cur++
        }else if distance(points[cur]) > distance(points[end]){
            more--
            points[cur],points[more] = points[more],points[cur]
        }else {
            cur++
        }
    }
    points[end],points[more] = points[more],points[end]
    return more
}

func distance(point[] int) int{
    return point[0] * point[0] + point[1] * point[1];
}

```

