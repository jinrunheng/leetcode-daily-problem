## 189. 旋转数组

[189. 旋转数组](https://leetcode-cn.com/problems/rotate-array/)

#### 解题思路一：使用辅助数组

可以使用额外的空间，开辟一个辅助数组`help`

计算出数组的一个偏移量，这里面需要注意的是，我们需要考虑到当`k`大于`nums.length`的情况，所以偏移量实际上为`nums.length - k % nums.length`

算法流程为：

首先，从偏移量开始遍历到数组最后，将这段数据写入到辅助数组中，然后从数组头部开始遍历到偏移量的位置，将这段数据接着写入到辅助数组中；最后，将辅助数组重新写回到原数组。

#### 代码

*Java*

```java
class Solution {
    public void rotate(int[] nums, int k) {
        // 方法一:使用辅助数组
        int[] help = new int[nums.length];
        int i = 0;
        for(int j = nums.length - k % nums.length; j < nums.length; j++){
            help[i++] = nums[j];
        }
        for(int j = 0; j < nums.length - k % nums.length; j++){
            help[i++] = nums[j];
        }

        // 将help写回到原数组
        for(int j = 0; j < nums.length; j++){
            nums[j] = help[j];
        }
    }
}
```



#### 解题思路二：翻转数组

示例：`nums = [1,2,3,4,5,6,7]`；`k = 3`

| 操作                                     | 结果          |
| ---------------------------------------- | ------------- |
| 原始数组                                 | 1 2 3 4 5 6 7 |
| 首先，翻转所有元素                       | 7 6 5 4 3 2 1 |
| 然后，翻转` [0, k % len - 1]` 区间的元素 | 5 6 7 4 3 2 1 |
| 最后，翻转`[k % len,len - 1]`区间的元素  | 5 6 7 1 2 3 4 |

#### 代码

*Java*

```java
class Solution {
    public void rotate(int[] nums, int k) {
        reverse(nums,0,nums.length - 1);
        reverse(nums,0,k % nums.length - 1);
        reverse(nums,k % nums.length,nums.length - 1);
    }

    private static void reverse(int[] nums,int start,int end){
        while(start < end){
            swap(nums,start++,end--);
        }
    }

    private static void swap(int[] nums,int i,int j){
        if(i == j){
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
```



