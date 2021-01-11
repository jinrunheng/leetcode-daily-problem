## 1202. 交换字符串中的元素

[1202. 交换字符串中的元素](https://leetcode-cn.com/problems/smallest-string-with-swaps/)

#### 解题思路：并查集

我们先看一个输入输出的示例：

输入：`s = "abdc"` ，`pairs = [[0,3],[0,2]]`

输出：`"abcd"`



我们先不想着该如何交换才能得到输出的结果。

思考：

`[0,3]`表示索引`0`位置的数字可以和`3`位置的数字进行交换

`[0,2]`表示索引`0`位置的数字可以和`2`位置的数字进行交换

那么自然就可以得出：索引`2`位置的数字也可以和索引`3`位置的数字进行交换



于是，我们可以得到一个结论：

正是官方题解所说的：**"交换关系具有传递性"**

也就是说，`0,2,3`这三个位置是 **联通** 的，字符串这三个位置的字符可以任意调换顺序；`1`位置的字符`b`不能动

所以，结果输出为`abcd`。



再来看一个题目给到的用例：

输入：`s = "dcab"`，`pairs = [[0,3],[1,2],[0,2]`

分析：位置`0,1,2,3` 都是联通的；这也就意味着，`s`字符串任意位置的字符都可以互换

所以，我们不用考虑如何交换，直接就可以得到结论，输出结果为：`abcd`。



综上所述,我们需要确定的是`pairs`中，哪些索引的位置是**联通**的？

使用并查集则非常适合解决哪些元素共属于同一个集合这样的问题。

接下来我来科普一下，什么是并查集？

##### 并查集

什么是并查集？

并查集是一种树型的数据结构，在一些有N个元素的集合应用问题中我们通常是在开始时让每个元素构成一个单元素的集合(自己指向自己)，然后按一定顺序将属于同一组的元素所在的集合合并，其间要反复查找一个元素在哪个集合中。



并查集的主要功能有两个：

1. 判断元素A所在的集合是否和元素B所在的集合是同一个集合
2. 元素A所在的集合与元素B所在的集合合并



###### 并查集的初始化：

并查集的初始化是将样本量为N的数据的每个元素构成一个单元素的集合

每一个元素作为一个节点，同时**自己指向自己**，成为 **标记节点** ,作为一个单元素构成的集合。

我们维护两个哈希表:

```java
Map<Integer,Integer> map;
Map<Integer,Integer> size;
```

第一个`map`的`key`存储当前节点的值，`value`存储该节点指向的父节点的值

第二个`size`的`key`存储当前节点的值，`value`存储该节点所在的集合共有多少元素

并查集的初始化代码如下：

```java
public Map<Integer,Integer> map;
public Map<Integer,Integer> size;

public UnionFind(int n){
    map = new HashMap<>();
    size = new HashMap<>();
    for(int i = 0; i < n; i++){
        // 最开始每个元素自己指向自己，自己成为根节点作为一个单元素构成的集合
        map.put(i,i);
        // 每个集合只有一个元素
        size.put(i,1);
    }
}
```

###### 寻找一个节点的根节点(find)：

因为`map`存储的`key`为这个节点自身，`map`存储的`value`为该节点的父节点

我们很容易就可以写出寻找一个节点的根节点的代码：

```java
public Node find(Node node){
    Node root = map.get(node);
    if(root != node){ // 因为根节点是自己指向自己的，所以找到根节点以后递归就会终止
        root = find(root);
    }
    return root;
}
```

不过这样写代码会影响查询的效率

示例：

```
   1(root：自己指向自己)
  /|\
 2 3 6
       \
        4
```

假设有并查集如下，我们需要查寻`4`和`2`的根节点

元素`2`只需要走一步就可以找到根节点，而元素`4`需要走两步找到根节点。

随着并查集的数据量越来越大，有可能就会导致树的高度越来越高，查询速率越来越慢。为了提升下次查询的效率，我们需要做**树的扁平化处理**。我们在查询后知道，节点`4`的根节点为`1`；所以，我们在查询到`4`的根节点之后，直接让`4`指向根节点`1`，然后再返回根节点，这种处理就是扁平化处理。

```
    1(root：自己指向自己)
  // \\
 23   64 
```

处理方式很简单，只需要添加一句代码即可：

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

###### 合并集合(union)

已知：节点`p`和节点`q`在同一个集合，我们需要在并查集中合并两个节点所在的集合

我们需要知道

节点`a`所在集合的根节点`aRoot`与节点`b`所在的集合的根节点`bRoot`

以及，节点`a`所在的集合的元素数量`aSize`与节点`b`所在的集合的元素数量`bSize`

如果，`aSize < bSize` 那么我们就让节点`a`所在的集合向节点`b`所在的集合合并

如果，`bSize < aSize`那么我们就让节点`b`所在的集合向节点`a`所在的集合合并

代码如下：

```java
public void union(int p,int q){
    int pRoot = find(p);
    int qRoot = find(q);
    if(pRoot != qRoot){
        int pSize = size.get(pRoot);
        int qSize = size.get(qRoot);

        if(pSize < qSize){
            map.put(pRoot,qRoot);
            size.put(qRoot,pSize + qSize);
        }else {
            map.put(qRoot,pRoot);
            size.put(pRoot,qSize + pSize);
        }
    }
}
```



并查集已经介绍完毕了，接下来我们回归到本题，看下本题的解题思路

1. 首先，初始化并查集，并查集将字符串`s`中共属于同一集合的索引值合并

   ```java
   UnionFind uf = new UnionFind(s.length());
   for(List<Integer> pair : pairs){
       uf.union(pair.get(0),pair.get(1));
   }
   ```

2. 遍历输入字符串`s`，建立映射关系

   `Map<Integer,PriorityQueue<Character>> map = new HashMap<>();`

   哈希表中的`key`为一个集合的根节点值

   哈希表中的`value`为一个最小堆，遍历字符串`s`的过程中，如果当前遍历到的字符属于某个集合，我们就将这个值加入到优先级队列中，优先级队列会将字符按照字典序从小到大进行排序；如果不属于任何集合，则自己创建一个优先级队列并存储到`map`中

3. 重构字符串

详见代码

#### 代码

*Java*

```java
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if(pairs.size() == 0){
            return s;
        }
        UnionFind uf = new UnionFind(s.length());
        for(List<Integer> pair : pairs){
            uf.union(pair.get(0),pair.get(1));
        }

        Map<Integer,PriorityQueue<Character>> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            int root = uf.find(i);
            if(map.containsKey(root)){
                map.get(root).offer(s.charAt(i));
            }else {
                PriorityQueue<Character> minHeap = new PriorityQueue<>();
                minHeap.offer(s.charAt(i));
                map.put(root,minHeap);
            }
        } 

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            int root = uf.find(i);
            sb.append(map.get(root).poll());
        }
        return sb.toString();  
    }

    class UnionFind{
        public Map<Integer,Integer> map;
        public Map<Integer,Integer> size;
        public UnionFind(int n){
            map = new HashMap<>();
            size = new HashMap<>();
            for(int i = 0; i < n; i++){
                map.put(i,i);
                size.put(i,1);
            }
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
                int pSize = size.get(pRoot);
                int qSize = size.get(qRoot);

                if(pSize < qSize){
                    map.put(pRoot,qRoot);
                    size.put(qRoot,pSize + qSize);
                }else {
                    map.put(qRoot,pRoot);
                    size.put(pRoot,qSize + pSize);
                }
            }
        }
    }
}
```

