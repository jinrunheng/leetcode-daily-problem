## 547. 省份数量

[547. 省份数量](https://leetcode-cn.com/problems/number-of-provinces/)

#### 解题思路：并查集

首先：什么是并查集？

并查集是一种树型的数据结构，在一些有N个元素的集合应用问题中我们通常是在开始时让每个元素构成一个单元素的集合(自己指向自己)，然后按一定顺序将属于同一组的元素所在的集合合并，其间要反复查找一个元素在哪个集合中。



并查集的主要功能有两个：

1. 判断元素A所在的集合是否和元素B所在的集合是同一个集合
2. 元素A所在的集合与元素B所在的集合合并





**并查集的初始化：**

并查集的初始化是将样本量为N的数据的每个元素构成一个单元素的集合

每一个元素作为一个节点，同时**自己指向自己**，成为 **标记节点** ,作为一个单元素构成的集合。





**寻找一个节点的根节点**

我们使用一个`Map`来存储节点的指向信息：

```java
Map<Node,Node> map = new HashMap<>();
```

`map`存储的`key`为这个节点自身，`map`存储的`value`为该节点的父节点

那么我们就可以写出寻找一个节点的根节点的代码了：

```java
public Node find(Node node){
    Node root = map.get(node);
    if(root != node){ // 因为根节点是自己指向自己的，所以找到根节点以后递归就会终止
        root = find(root);
    }
    return root;
}
```





**扁平化处理**

示例：

```
   1(root：自己指向自己)
  /|\
 2 3 6
       \
        4
```

假设有并查集如下，我们需要查寻`4`和`2`的根节点

元素`2`只需要走一步就可以找到根节点，而元素`4`需要走两步找到根节点。随着并查集的数据量越来越大，有可能就会导致树的高度越来越高，查询速率越来越慢。为了提升下次查询的效率，我们需要做**树的扁平化处理**。我们在查询后知道，节点`4`的根节点为`1`；所以，我们在查询到`4`的根节点之后，直接让`4`指向根节点`1`，然后再返回根节点，这种处理就是扁平化处理。

```
    1(root：自己指向自己)
  // \\
 23   64 
```

只需要添加一句代码即可：

```java
public Node find(Node node){
    Node root = map.get(node);
    if(root != node){ // 因为根节点是自己指向自己的，所以找到根节点以后递归就会终止
        root = find(root);
    }
    map.put(node,root); // 扁平化处理
    return root;
}
```





**判断A所在的集合是否和B所在的集合是同一集合**

在并查集中，如果两个节点A，B所指向的根节点(标记节点)为同一个节点，那么这两个节点所在的集合就是同一个集合





**合并集合(union)**

已知：为节点`a`和节点`b`在同一个集合，我们需要在并查集中合并两个节点所在的集合

本题中涉及到的合并集合操作并不难：

```java
public void union(Node a,Node b){
    Node root1 = find(a);
    Node root2 = find(b);
    if(root1 != root2){
        map.put(root1,root2); // map.put(root2,root1); 也可以
    }
}
```



#### 代码

*Java*

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isConnected[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.size;
    }

    class UnionFind{
        public Map<Integer,Integer> map; // key: current node ; value: parent node
        public int size; // 维护集合的数量

        public UnionFind(int n){
            map = new HashMap<>();
            for(int i = 0; i < n; i++){
                map.put(i,i); // 初始化，每个节点自己指向自己
            }
            size = n;
        }

        // 找到节点的头节点    
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
                map.put(pRoot,qRoot);
                size--; // 合并两个集合之后，集合的总数减一
            }
        }
    }
}
```

