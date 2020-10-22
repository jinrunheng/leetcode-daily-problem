## 双指针 + 贪心

本题链接：[https://leetcode-cn.com/problems/partition-labels/](https://leetcode-cn.com/problems/partition-labels/)

#### 递归

递归是我第一次提交的代码

有字符串“ababcbacadefegdehijhklij”，它的划分结果为："ababcbaca", "defegde", "hijhklij"

其结果返回为：[9,7,8]

如果在字符串的末尾又新增了一个字母，那么就有以下推论：

1. 新增的字母在每个划分片段内都没有出现过，那么就返回[9,7,8,1]
2. 新增的字母在第三个片段出现过 ，那么就返回[9,7,8 + 1]
3. 新增的字母在第二个片段出现过，那么就返回[9, 7 + 8 + 1]
4. 新增的字母在第一个片段出现过，那么就返回[9 + 7 + 8 + 1]

不过递归思路提交的代码运行正确但是速度并不理想，代码如下：

```java
class Solution {
    // recursion
    public List<Integer> partitionLabels(String S) {
        char[] chars = S.toCharArray();
        return partitionLabels(chars);
    }

    public List<Integer> partitionLabels(char[] chars) {
        if(chars.length == 1){
            List<Integer> res = new ArrayList<>();
            res.add(1);
            return res;
        }
        char[] subChars = new char[chars.length - 1];
        char target = chars[chars.length - 1];
        for (int i = 0; i < subChars.length; i++) {
            subChars[i] = chars[i];
        }
        List<Integer> subCharsList = partitionLabels(subChars);
        int i = 0;
        for(; i < subChars.length; i++){
            if(target == subChars[i]){
                break;
            }
        }
        if(i == subChars.length){
            List<Integer> res = new ArrayList<>();
            res.addAll(subCharsList);
            res.add(1);
            return res;
        }
        List<Integer> tmp = new ArrayList<>();
        for(int j = 0; j < subCharsList.size(); j++){
            if(j == 0){
                tmp.add(subCharsList.get(0));
            }else{
                tmp.add(tmp.get(j - 1) + subCharsList.get(j));
            }
        }
        int j = 0;
        for(; j < tmp.size(); j++){
            if(i + 1 <= tmp.get(j)){
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int k = 0;k < subCharsList.size(); k++){
            if(k == j){
                if(k == 0){
                    res.add(tmp.get(tmp.size() - 1) + 1);
                    return res;
                }else{
                    res.add(tmp.get(tmp.size() - 1) - tmp.get(k - 1) + 1);
                    break;
                }
            }else{
                res.add(subCharsList.get(k));
            }
        }
        return res;
    }
}
```



#### 双指针 + 贪心

1. 得到每一个字母最后一次出现的下标位置，可以使用长度为26的数组或是哈希表来记录

   ```java
   int[] last_idx = new int[26];
   for(int i = 0; i < S.length(); i++){
       last_idx[S.charAt(i) - 'a'] = i;
   }
   ```

2. 双指针，start，end分别记录每个片段的首尾位置

   - 遍历字符串，遍历的同时维护当前片段的下标索引start，end；初始时，start = 0,end = 0。
   - 对于每个访问到的字母 c, 得到当前字母的最后一次出现的下标位置 endc,通过贪心断言：当前片段的结束下标一定不会小于endc,因此我们每次都更新end为`end = max(end,endc)`
   - 当我们访问到下标 end 时，当前片段访问结束，当前片段的下标范围是 [start,end]，长度为 end−start+1，将当前片段的长度添加到返回值，然后令 start=end+1，继续寻找下一个片段。

   - 重复上述过程，直至遍历完字符串

3. 代码如下

   ```java
   class Solution {
       public List<Integer> partitionLabels(String S) {
           // 贪心算法
           int[] last_idx = new int[26];
           for(int i = 0; i < S.length(); i++){
               last_idx[S.charAt(i) - 'a'] = i;
           }
   
           // 双指针start,end分别记录每个片段的首尾位置
           int start = 0;
           int end = 0;
           List<Integer> res = new ArrayList<>();
           for(int i = 0; i < S.length(); i++){
               end = Math.max(last_idx[S.charAt(i) - 'a'],end);
               if(i == end){
                   res.add(end - start + 1);
                   start = end + 1;
               }
           }
           return res;
       }
   }
   ```

   



