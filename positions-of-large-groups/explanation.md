## 830. 较大分组的位置

[830. 较大分组的位置](https://leetcode-cn.com/problems/positions-of-large-groups/)

#### 解题思路：双指针

本题思路非常简单，维护两个指针:`start`,`end`；两个指针初始值均为`0`

遍历字符串

- 如果`s.charAt(start) == s.charAt(end)`
  - `end`指针后移

- 如果`s.charAt(start) != s.charAt(end)`
  - 我们就先判断一下`end - start`的差值是否大于等于3，如果满足，我们就将其加入到结果集中，并重置`start`的位置，令`start = end`
  - 如果`end - start`的差值小于3，那么就让`start`指针后移
- 最后我们还需要判断下边界情况，如果`end`已经超出边界，这时候还要进行判断是否满足`end - start >= 3  `，如果满足要求，我们需要将最后的这组数据加入到结果集中

#### 代码

*Java*

```java
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int end = 0;
        while(start < s.length() && end < s.length()){
            if(s.charAt(start) == s.charAt(end)){
                end++;
            }else{
                if(end - start >= 3){
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end - 1);
                    res.add(list);
                    start = end;
                    continue;
                }
                start++;
            }
        }
        // 处理边界情况
        // 例如 s = "aaa"
        // end已经超出边界，这时候要进行判断 end - start 是否大于等于3；
        // 如果满足要求，我们需要再加上最后的这组数据
        if(end - start >= 3){
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end - 1);
            res.add(list);
        }
        return res;
    }
}
```

