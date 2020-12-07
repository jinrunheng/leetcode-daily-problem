## 翻转矩阵后的得分

[861. 翻转矩阵后的得分](https://leetcode-cn.com/problems/score-after-flipping-matrix/)

#### 思路一：贪心，实际翻转矩阵

对于一个矩阵，如果想要最高的得分，那么只需要满足：

1. 翻转行，保证每一行的矩阵最高位都是1
2. 对于每一行的其他的位，翻转原则位使得每一列中1的数量尽可能多

示例如下：

```
[0,0,1,1]
[1,0,1,0]
[1,1,0,0]
```

按照我们的思路首先翻转行，让每一行的首位均为1;对于本示例，我们只需要翻转第一行即可

```
[1,1,0,0]
[1,0,1,0]
[1,1,0,0]
```

对于每一行的其他位，我们只需要保证：每一列中1的数量尽可能多

所以，第三列，第四列需要翻转；翻转后的数组为：

```
[1,1,1,1]
[1,0,0,1]
[1,1,1,1]
```

最后的结果为：39

#### 代码

本身的思路非常简单，代码也不难：

```	java
class Solution {
    public int matrixScore(int[][] A) {
        for(int i = 0; i < A.length; i++){
            if(A[i][0] == 0){
                reverse(A[i]);
            }
        }

        for(int i = 1; i < A[0].length; i++){
            reverseCol(A,i);
        }
        int res = 0;
        for(int i = 0; i < A.length; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < A[0].length; j++){
                sb.append(A[i][j]);
            }
            res += Integer.parseInt(sb.toString(),2);
        }
        return res;
    }

    private static void reverseCol(int[][] A,int colIndex ){
        int res = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i][colIndex] == 0){
                res++;
            }
        }
        if(res > A.length / 2){
            for(int i = 0; i < A.length; i++){
                if(A[i][colIndex] == 0){
                    A[i][colIndex] = 1;
                }else {
                    A[i][colIndex] = 0;
                }
            }
        }
    }

    private static void reverse(int[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0){
                arr[i] = 1;
            }else {
                arr[i] = 0;
            }
        }
    }
}
```

#### 思路二：贪心，计算贡献不翻转矩阵

贪心思路和前面的思路一是一致的，不过我们可以不用实际模拟过程翻转矩阵，而是直接遍历一遍矩阵求出贡献即可

对于每一行的第一列数字，肯定是1，他们的“贡献”为：

```java
A.length * (1 << A[0].length - 1);
```

而对于其他的列，我们的处理方法是：循环每一列：从第二位开始遍历该列，如果值跟第一个相同，即是1，我们只需要判断对于每一行的该列，是1的个数多还是0的个数多即可；遍历完整个矩阵，我们也将最终的返回值计算完毕，时间复杂度优化至O(N)

#### 代码

```java
class Solution {
    public int matrixScore(int[][] A) {
        int res = A.length * (1 << A[0].length - 1); // 第一位均为1
        for(int i = 1; i < A[0].length; i++){
            int countOf1 = 0;
            for(int j = 0; j < A.length; j++){
                if(A[j][i] == A[j][0]){ // 1的个数
                    countOf1++;
                }
            }
            res += Math.max(countOf1,A.length - countOf1) * (1 << (A[0].length - 1 - i));
        }
        return res;
    }
}
```

