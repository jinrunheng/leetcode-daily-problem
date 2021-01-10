## 228. 汇总区间

[228. 汇总区间](https://leetcode-cn.com/problems/summary-ranges/)

#### 解题思路：一次遍历数组

本题规定传入的数组为：

- 无重复元素
- 有序

思路：

规定返回结果集：`List<String> res = new ArrayList<>();`

从头遍历数组，每次我们都维护一个`StringBuilder`对象；

如果发现有连续区间的数字，我们就将开始连续区间的头数字与`"->"`字符串加入到`StringBuilder`对象中，直至连续区间结束，将连续区间的尾数字也加入到`StringBuilder`对象中，最后将`StringBuilder`对象加入到结果集

代码如下

#### 代码

*Java*

```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        
        List<String> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            StringBuilder sb = new StringBuilder();
            while(i < nums.length - 1 && nums[i] + 1 == nums[i + 1]){
                if(sb.length() == 0){
                    sb.append(nums[i]);
                    sb.append("->"); 
                }
                i++;              
            }
            sb.append(nums[i]);
            res.add(sb.toString());
        }
        return res;
    }
}
```

