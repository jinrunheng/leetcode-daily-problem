## 两个数组的交集

[题目链接](https://leetcode-cn.com/problems/intersection-of-two-arrays/)

#### 方法一：哈希表

哈希表：

- 遍历第一个数组，将数组中的值放入map(或者是Set)中
- 新建一个set集合，遍历第二个数组，如果`map.containsKey(nums2[i])` 那么就`set.add(nums2[i])`
- 使用stream，将集合转换为返回数组`set.stream().mapToInt(Integer::valueOf).toArray();` 

##### 完整代码如下：

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        // hashmap
        Map<Integer,Boolean> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            map.put(nums1[i],true);
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums2.length; i++){
            if(map.containsKey(nums2[i])){
                set.add(nums2[i]);
            }
        }
        return set.stream().mapToInt(Integer::valueOf).toArray();
    }
}
```

#### 方法二：排序 + 双指针

- 首先对两个数组进行排序
- 用两个变量分别记录两个已排序数组的头部，并且使用一个变量i来维护返回的数组
- 比较两个指针指向的数字大小，`if(nums1[p1] == nums2[p2])` 并且满足`i == 0 || intersection[i - 1] != nums1[p1]`  我们就将这个数组加入到返回数组intersection中，然后让两个指针加1
- 如果两个数字不相等，就将指向较小数字的指针右移一位

##### 完整代码如下：

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 排序 + 双指针思路
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        int i = 0;

        int[] intersection = new int[nums1.length + nums2.length];
        while(p1 < nums1.length && p2 < nums2.length){
            if(nums1[p1] == nums2[p2]){
                if(i == 0 || intersection[i - 1] != nums1[p1]){
                    intersection[i++] = nums1[p1];
                }
                p1++;
                p2++;
            }else if(nums1[p1] < nums2[p2]){
                p1++;
            }else{
                p2++;
            }
        }
        return Arrays.copyOfRange(intersection,0,i);
    }
}
```

最近在学习Go，也贴下Go的代码：

```go
func intersection(nums1 []int, nums2 []int) []int {
    sort.Ints(nums1)
	sort.Ints(nums2)
	var res []int
	p1, p2 := 0, 0
	for p1 < len(nums1) && p2 < len(nums2) {
		x, y := nums1[p1], nums2[p2]
		if x == y {
			if res == nil || x != res[len(res)-1] {
				res = append(res, x)
			}
			p1++
			p2++
		} else if x < y {
			p1++
		} else {
			p2++
		}
	}
	return res
}
```



