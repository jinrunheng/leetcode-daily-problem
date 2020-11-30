## 重构字符串

[767. 重构字符串](https://leetcode-cn.com/problems/reorganize-string/)

#### 思路：贪心 + 大根堆

算法流程：

1. 使用map，统计字符串中，每个字符出现的次数；map的key存储的为字符，value存储的是次数

2. 定义一个大根堆，堆中存放节点，关于节点的定义如下：

   ```java
   class Node {
       char c;
       int count;
       public Node(char c,int count){
           this.c = c;
           this.count = count;
       }
   }
   ```

   节点的c用表示字符，count则为一个字符在字符串S中出现的次数

3. 关于大根堆的比较原则

   ```java
   class myComparator implements Comparator<Node> {
       @Override
       public int compare(Node o1,Node o2) {
           if(o1.count == o2.count){
               return o1.c - o2.c;
           }else {
               return o2.count - o2.count;
           }
       }
   }
   ```

   本质上是让count作为比较的原则；不过我也加上了在：`if(o1.count == o2.count)` 这种情况下的比较原则，后面会说明这一点

4. 算法的核心：遍历map，map中已经统计了每个字符出现的次数，我们将每个字符以及对应的次数封装为一个节点，存入大根堆中，大根堆堆顶弹出的就是我们当前构建字符串的字符，弹出以后，节点的count要减一，这时候不要将该节点放入大根堆中；我们接着构建下一个字符，也是同样的原理，这里不做赘述，看代码便知：

   ```java
   String res = "";
   while(res.length() != S.length()){
       Node node = maxHeap.poll();
       if(res.length() > 0 && res.charAt(res.length() - 1) == node.c){
           return "";
       }
       res += node.c;
       node.count--;
       if(!maxHeap.isEmpty()){
           Node next = maxHeap.poll();
           res += next.c;
           next.count--;
           if(next.count != 0){
               maxHeap.add(next);
           }
        }
               
        if(node.count != 0){
           maxHeap.add(node);
        }
               
   }
   ```

5. 关于为什么要在比较器中加入这样的判断：`if(o1.count == o2.count)`

   如果字符串中只有两种字符a,b,每种字符出现的个数相同，我们这个算法流程就需要保证：每次构建好res的两个字符，将两个node返回后，堆顶的元素都是一致的：

   ```
      NODE(a,4)
      /
    NODE(b,4)  
    
    // 经过我们的算法流程后，a的数量减1，b的数量减1，
    // 将节点a和节点b放回到大根堆的时候,我们仍然要保证大根堆的顺序为a在堆顶
    
       NODE(a,3)
      /
    NODE(b,3)  
    
   ```

#### 代码

```java
class Solution {

    class Node {
        char c;
        int count;

        public Node(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    class myComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            if(o1.count == o2.count){
                return o1.c - o2.c;
            }else{
                return o2.count - o1.count;
            }
        }
    }

    public String reorganizeString(String S) {
        
        // 大根堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new myComparator());
        int[] map = new int[26];
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 0; i < map.length; i++) {
            if(map[i] != 0){
                maxHeap.add(new Node((char)(97 + i),map[i]));
            }
        }
        String res = "";
        while(res.length() != S.length()){
            Node node = maxHeap.poll();
            if(res.length() > 0 && res.charAt(res.length() - 1) == node.c){
                return "";
            }
            res += node.c;
            node.count--;
            if(!maxHeap.isEmpty()){
                Node next = maxHeap.poll();
                res += next.c;
                next.count--;
                if(next.count != 0){
                    maxHeap.add(next);
                }
            }
            
            if(node.count != 0){
                maxHeap.add(node);
            }
        }
        return res;
    }
}
```





