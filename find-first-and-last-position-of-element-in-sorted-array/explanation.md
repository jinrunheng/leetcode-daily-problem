## 在排序数组中查找元素的第一个和最后一个位置

[34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

#### 思路一：遍历数组寻找元素的第一和最后一个位置

遍历数组，找到开始位置和结束位置，时间复杂度为O(N)

思路比较简单，就不赘述了，详见代码：

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        int l = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                l = i;
                break;
            }
        }
        // 如果数组中没有等于target的数字
        if(l == -1){
            return new int[]{-1,-1};
        }
        int r =  l;
        for(int i =  l; i < nums.length; i++){
            if(nums[i] != target){
                r = i - 1;
                break;
            }else  {
                r = i;
            }
        }
        return new int[]{l,r}; 
    }
}
```

#### 思路二：二分查找

我们的思路为：

寻找的leftIndex为数组中第一个大于等于target的下标；寻找的rightIndex为数组中第一个大于target的下标，然后将下标减1。

代码如下：

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        int l = searchLeftIndex(nums,target);
        int r = searchRightIndex(nums,target) - 1;
        if(l <= r && r < nums.length && nums[l] == target && nums[r] == target){
            return new int[]{l,r};
        }
        return new int[]{-1,-1};
    }
    
    // 寻找数组中第一个大于等于target的下标
    private static int searchLeftIndex(int[] nums,int target){
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while(l <= r){
            int mid = l + ((r - l) >> 1);
            if(nums[mid] >= target){
                r = mid - 1;
                ans = mid;
            }else {
                l = mid + 1;
            }
        }
        return ans;
    }
    
    // 寻找数组中第一个大于target的下标
    private static int searchRightIndex(int[] nums,int target){
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while(l <= r){
            int mid = l + ((r  - l) >> 1);
            if(nums[mid] > target){
                r = mid - 1;
                ans  = mid;
            }else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
```





