## 455. 分发饼干

[455. 分发饼干](https://leetcode-cn.com/problems/assign-cookies/)

#### 解题思路：贪心算法

首先，我们已经知：

- 每个孩子最多只能获得一块饼干
- 如果`s[j] >= g[i]`,我们将饼干`j`分配给这个孩子`i`,那么这个孩子会得到满足



首先，对`g[]`与`s[]`两个数组从小到大进行排序

然后，用指针`i`和`j`分别指向`g[]`与`s[]`

我们的贪心策略为：如果当前`j`指向的饼干的大小能够满足`i`指向的孩子的胃口值，我们就把这块饼干分给这个孩子，然后指针`i`和`j`同时向后移动；

如果不能，我们就让指针`j`向后移动，继续进行判断是否满足`s[j] >= g[i]`

#### 代码

*Java*

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int i = 0;
        int j = 0;
        while(i < g.length && j < s.length){
            if(s[j] >= g[i]){
                res++;
                i++;
            }
            j++;
        }
        return res;
    }
}
```

