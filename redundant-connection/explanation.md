## 684. 冗余连接

[684. 冗余连接](https://leetcode-cn.com/problems/redundant-connection/)

#### 解题思路：并查集

关于并查集我就不在赘述了，大家可以看我过去的题解，里面有并查集的详细介绍与模板

- [结合官方题解，给没接触过并查集的小伙伴科普~~](https://leetcode-cn.com/problems/smallest-string-with-swaps/solution/jie-he-guan-fang-ti-jie-gei-mei-jie-hong-y2g0/)

题目分析：

我们要返回的边是导致环出现的边。

并且，如果有多个答案，我们要返回的是**二维数组中最后出现的边**。

思路：

并查集，最初将每个节点初始化为一个独立的集合；然后遍历`edges`数组，`edges[i]`保存的信息是节点与节点构成的联通分量信息，如果遍历到的两个节点的根节点不属于同一个集合，那么就意味着这两个节点之间不联通，说明当前的边不会导致环的出现，就将这两个节点合并；如果遍历到的两个节点的根节点属于同一个集合，那么就意味着这两个节点之间已经是联通的了，当前遍历到的边会导致环的出现，即返回。

#### 代码

*Java*

```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int node1 = edge[0];
            int node2 = edge[1];
            if(uf.find(node1) != uf.find(node2)){
                uf.union(node1,node2);
            }else {
                return edge;
            }
        }
        return new int[0];
    }

    class UnionFind {
        private Map<Integer,Integer> map;
        private Map<Integer,Integer> size;

        public UnionFind(int n){
            map = new HashMap<>();
            size = new HashMap<>();
            for(int i = 1; i <= n; i++){
                map.put(i,i);
                size.put(i,1);
            }
        }

        public int find(int i){
            int root = map.get(i);
            if(i != root){
                root = find(root);
            }
            map.put(i,root);
            return root;
        }

        public void union(int p,int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot != qRoot){
                int pSize = size.get(pRoot);
                int qSize = size.get(qRoot);
                if(pSize < qSize){
                    map.put(pRoot,qRoot);
                    size.put(qRoot,qSize + pSize);
                }else {
                    map.put(qRoot,pRoot);
                    size.put(pRoot,qSize + pSize);
                }
            }
        }
    }
}
```

