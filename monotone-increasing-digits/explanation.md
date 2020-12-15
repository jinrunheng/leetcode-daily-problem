## 单调递增的数字

[738. 单调递增的数字](https://leetcode-cn.com/problems/monotone-increasing-digits/)

#### 题解：贪心

算法思路：

将数字N转换为字符数组`arr`

找到数字第一个`arr[pre] > arr[i]`的位置`pre`



**示例1：**

```
N = 1234
```

N这个数字每一位的本身就是单调递增的，所以返回N即可；



**示例2：**

```
N = 668841
```

对于这个示例，`pre`为`3`，即：`arr[3] > arr[4]`；但是，我们需要再向前遍历，返回所有和`arr[3]`相同的数字的首个位置,所以对于本示例，实际的返回值为2；



**示例3：**

```
N = 332
```

对于这个示例，`arr[1] > arr[2]`；我们需要向前遍历，找到所有和`arr[1]`相同的数字的首个位置，所以返回`0`

通过这个查找函数，找到了`pre` 位置，我们只需要将后置位所有的数字都置为9即可；

因为本题我是通过字符串进行处理的，所以对于最后返回的字符串，我们需要判断`str.charAt(0)` 位置的字符是否为`'0'` ；如果首字符为`'0'`，我们需要将首字符删除并将字符串转换为整数。

#### 代码

*Java*

```java
class Solution {
    public int monotoneIncreasingDigits(int N) {
        int p = findIncreasingPosition(N);
        if(p == -1){
            return N;
        }
        char[] chars = String.valueOf(N).toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean find = false;
        for(int i = 0; i < chars.length; i++){
            if(i == p){
                sb.append(chars[i] - '1');
                find = true;
                continue;
            }
            if(find){
                sb.append('9');
            }else {
                sb.append(chars[i]);
            }
        }
        if(sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        
        return Integer.parseInt(sb.toString());
    }

    private static int findIncreasingPosition(int N){
        char[] chars = String.valueOf(N).toCharArray();
        
        for(int i = 0; i < chars.length - 1; i++){
            
            if(chars[i] > chars[i + 1]){
                int j = i;
                while(j > 0 && chars[j] == chars[j - 1]){
                    j--;
                }
                return j;
            }
            
        }
        return -1;
    }
}
```

