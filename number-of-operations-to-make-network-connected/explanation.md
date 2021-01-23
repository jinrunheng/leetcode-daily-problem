## 1319. 连通网络的操作次数

[1319. 连通网络的操作次数](https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/)

#### 解题思路：并查集

首先，关于并查集的详细介绍可以看我往期的文章：

- leetcode题解：[结合官方题解，给没接触过并查集的小伙伴科普~~](https://leetcode-cn.com/problems/smallest-string-with-swaps/solution/jie-he-guan-fang-ti-jie-gei-mei-jie-hong-y2g0/)

- 简书文章：[并查集](https://www.jianshu.com/p/cb20518531f2)

- 更多关于并查集的题解：

  | 序号 | 题目                                                         | 题解                                                         |
  | ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | 1    | [547. 省份数量](https://leetcode-cn.com/problems/number-of-provinces/) | [题解](https://github.com/jinrunheng/leetcode-daily-problem/blob/main/number-of-provinces/explanation.md) |
  | 2    | [684. 冗余连接](https://leetcode-cn.com/problems/redundant-connection/) | [题解](https://github.com/jinrunheng/leetcode-daily-problem/blob/main/redundant-connection/explanation.md) |
  | 3    | [1202. 交换字符串中的元素](https://leetcode-cn.com/problems/smallest-string-with-swaps/) | [题解](https://github.com/jinrunheng/leetcode-daily-problem/blob/main/smallest-string-with-swaps/explanation.md) |
  | 4    | [947. 移除最多的同行或同列石头](https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/) | [代码](https://github.com/jinrunheng/leetcode-daily-problem/blob/main/most-stones-removed-with-same-row-or-column/Solution.java) |
  | 5    | [803. 打砖块](https://leetcode-cn.com/problems/bricks-falling-when-hit/) | [代码](https://github.com/jinrunheng/leetcode-daily-problem/blob/main/bricks-falling-when-hit/Solution.java) |

我们先来思考，什么时候无法使得所有的机器网络都连通？

假设有n台电脑，保证所有机器网络连通，我们至少需要**n-1**条网线

我们首先来看下官方给出的示意图：

示例一：

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmy3b7wmpuj30l3068jrf.jpg" alt="img" style="zoom:60%;" align="left"/>

本示例输出的结果为：`1`

我们观察到，左图有四台电脑，三条网线，并且共有两个联通分量

我们可以通过电脑数量和网线数量来判断，所有的机器是可以连通的

那么我们需要挪动几根网线呢？答案就是**联通分量的个数 - 1**

再来看下示例二：

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gmy3kd9rwvj30oe06a0sv.jpg" alt="img" style="zoom:60%;" align="left"/>

首先，六台机器，五条网线，可以保证所有机器都连通

左图共有三个联通分量，我们需要挪动的网线数量为：`2`，即：**联通分量的个数 - 1**

#### 代码

*Java*

```java
class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1){
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for(int[] connection : connections){
            uf.union(connection[0],connection[1]);
        }
        return uf.setCount - 1;

    }

    class UnionFind{
        Map<Integer,Integer> parent;
        Map<Integer,Integer> size;

        // 联通分量的个数
        int setCount;

        public UnionFind(int n){
            setCount = n;
            parent = new HashMap<>();
            size = new HashMap<>();
            for(int i = 0; i < n; i++){
                parent.put(i,i);
                size.put(i,1);
            }
        }

        public int findRoot(int i){
            int root = parent.get(i);
            if(root != i){
                root = findRoot(root);
            }
            // 扁平化处理
            parent.put(i,root);
            return root;
        }

        public boolean isSameSet(int p,int q){
            return findRoot(p) == findRoot(q);
        }

        public void union(int p,int q){
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            if(pRoot != qRoot){
                int pSize = size.get(p);
                int qSize = size.get(q);
                if(pSize < qSize){
                    parent.put(pRoot,qRoot);
                    size.put(qRoot,pSize + qSize);
                }else {
                    parent.put(qRoot,pRoot);
                    size.put(pRoot,pSize + qSize);
                }
                setCount--;
            }
        }
    }
}
```

