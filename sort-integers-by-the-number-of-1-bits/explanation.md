##  根据数字二进制下 1 的数目排序

[题目链接](https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/)

##### 题解 

bitwise operation

本题重点考察的内容是如何获取一个数字二进制形式下的1的个数

我的解题流程：

1. 将原数组排序，其原因是题目要求如果存在多个数字二进制中 **1** 的数目相同，则必须将它们按照数值大小升序排列。

2. 遍历数组，获取每个数字二进制中1的个数 `count`，并将这个数字本身,我们定义为`original`和`count`封装到自己定义的类`MyNum`中，并依次放入`list`集合
3. 将list按照`MyNum.count`升序排序，这样排好序的`list`集合中的每个元素`MyNum.original`就是我们想要的结果

##### 如何获取一个数字二进制形式下1的个数

方法一: `Integer.toBinaryString()`

```java
	private static int bitCount(int num) {
        int res = 0;
        String s = Integer.toBinaryString(num);
        for (char c : s.toCharArray()) {
            if (c == '1') {
                res++;
            }
        }
        return res;
    }
```

方法二：常规遍历，循环次数为num二进制的长度

```java
	private static int bitCount(int num){
        int count = 0; // 计数器
        while (num > 0) {
            if((num & 1) == 1)  count++;  // 当前位是1，count++
            num >>= 1 ; // n向右移位
        }
        return count;
    }
```

方法三：只循环num的二进制中1的个数次

```java
	private static int bitCount(int num) {
        int count = 0;
        while (num != 0) {
            num &= (num - 1); // 清除最低位的1
            count++;
        }
        return count;
    }
```



##### 代码

```java
class Solution {
    
    class MyNum {
        int original;
        int count;

        MyNum(int original, int count) {
            this.original = original;
            this.count = count;
        }
    }

    public int[] sortByBits(int[] arr) {
        Arrays.sort(arr);
        int[] res = new int[arr.length];
        List<MyNum> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new MyNum(arr[i], get1CountOfBinary(arr[i])));
        }
        // Collections.sort 是稳定排序算法
        Collections.sort(list, new Comparator<MyNum>() {
            @Override
            public int compare(MyNum o1, MyNum o2) {
                return o1.count - o2.count;
            }
        });

        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i).original;
        }
        return res;
    }

    private static int get1CountOfBinary(int num) {
        int count = 0;
        while (num != 0) {
            num &= (num - 1); // 清除最低位的1
            count++;
        }
        return count;
    }
}
```

