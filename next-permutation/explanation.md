## 下一个排列

[下一个排列](https://leetcode-cn.com/problems/next-permutation/)

#### 本题思路

题目要求，将给定数字序列重新排列成字典序中下一个更大的排列。

示例1：

```
[4,5,2,6,3,1]
```

下一个排列为：

```
[4,5,3,1,2,6]
```



示例2：

```
[6,5,4,3,2,1]
```

如果没有下一个排列（当前序列从大到小排列）

那么即返回这个序列的反转

```
[1,2,3,4,5,6]
```

本题的思路为：数学推导

1. 首先，从后向前查找第一个顺序对`(i,i + 1)` 满足`a[i] < a[i + 1]`。查找到的索引定义为`firstIndex`, 此时，在`[i + 1,n)` 必然是一个下降序列。
2. 如果没有这样的顺序对，说明序列数组是从大到小排列的，我们需要反转整个序列数组，并返回。
3. 在`[i + 1,n)`这个下降序列内，从后向前遍历，找到第一个大于`a[firstIndex]` 的元素，其索引为`secondIndex`。
4. 交换元素`a[firstIndex]` 和 `a[secondIndex]`；然后，将`[i + 1,n)` 区间内进行翻转，这样我就得到了下一个排列的序列数组。



**完整示例：**

对于数组

```
[4,5,2,6,3,1]
```

求，这个数组的下一个排列

- 从右向左遍历找到`firstIndex = 2 `

- 找到`secondIndex = 4`  

- 交换`firstIndex`和`secondIndex` 的位置

  ```
  [4,5,3,6,2,1]
  ```

- 将`[firstIndex + 1,n)` 的序列翻转

  ```
  [4,5,3,1,2,6]
  ```

#### 代码

Java

```java
class Solution {
    public void nextPermutation(int[] nums) {
        //  [4,5,2,6,3,1]
        // 从后向前查找第一个顺序对 (i,i+1)，满足 a[i] < a[i+1]
        int firstIndex = nums.length - 2;
        for (; firstIndex >= 0; firstIndex--) {
            if (nums[firstIndex] < nums[firstIndex + 1]) {
                break;
            }
        }
        // 如果一直是降序排序的，那么就reverse整个数组
        if (firstIndex == -1) {
            // reverse nums
            reverse(nums,0,nums.length - 1);
            return;
        }
        int secondIndex = nums.length - 1;
        for (; secondIndex > firstIndex; secondIndex--) {
            if(nums[secondIndex] > nums[firstIndex]){
                break;
            }
        }
        swap(nums,firstIndex,secondIndex);
        reverse(nums,firstIndex + 1,nums.length - 1);
    }
    private void reverse(int[] nums,int l,int r){
        while(l < r){
            swap(nums,l++,r--);
        }
    }
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

Go

```go
func nextPermutation(nums []int)  {
    firstIndex := len(nums) - 2
    for ;firstIndex >= 0;firstIndex--{
        if nums[firstIndex] < nums[firstIndex + 1] {
            break
        }
    }
    if firstIndex == -1 {
        reverse(nums)
        return
    }
    secondIndex := len(nums) - 1
    for ;secondIndex >= firstIndex + 1; secondIndex-- {
        if nums[secondIndex] > nums[firstIndex]{
            break
        }
    }
    nums[firstIndex],nums[secondIndex] = nums[secondIndex],nums[firstIndex]
    reverse(nums[firstIndex + 1 :])
}

func reverse(a []int) {
    for i, n := 0, len(a); i < n/2; i++ {
        a[i], a[n-1-i] = a[n-1-i], a[i]
    }
}
```

