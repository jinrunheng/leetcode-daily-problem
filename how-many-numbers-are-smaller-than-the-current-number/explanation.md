## 排序+hash表;桶排序两种思路

#### 方法一：排序 + hash表

1. 复制原数组，并对复制的数组进行排序

   例如：

   `nums = [8,1,2,2,3]` 

   复制原数组，并排序：

   `tmp = [1,2,2,3,8]`

2. 倒序遍历数组，使用哈希表存储；key为tmp[i],value为i

   倒序遍历，并存储到map中：

   `{[8:4],[3:3],[2,1],[1,0]}`

3. 再次遍历原数组，`map.get(nums[i])` 即为返回数组中的每一个值

##### 完整代码：

```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] tmp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tmp);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = tmp.length - 1; i >= 0; i--) {
            map.put(tmp[i], i);
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }
}
```

时间复杂度为：O(nlogn)

#### 方法二：桶排序

题中给定条件：`0 <= nums[i] <= 100`

桶排思路如下：

1. 开辟101个桶，桶的索引值bucketIndex为nums[i];桶的记录值bucketValue为在nums[i]出现的次数
2. 将桶累加：`bucket[i] += bucket[i-1]`;累加后，bucket[i]则表示为在nums数组中，小于等于nums[i]的个数，`bucket[nums[i] - 1]`即可表示为在数组中比nums[i]小的所有数字的数目

##### 完整代码如下：

```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] bucket = new int[101];
        for(int i = 0; i < nums.length; i++){
            bucket[nums[i]]++;
        }
        for(int i = 1; i < bucket.length; i++){
            bucket[i] += bucket[i - 1];
        }
        int[] res = new int[nums.length];
        for(int i = 0; i < res.length; i++){
            res[i] =nums[i] == 0 ? 0 : bucket[nums[i] - 1];
        }
        return res;
    }
}
```

