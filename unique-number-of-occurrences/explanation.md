## 独一无二的出现次数

#### 方法一：哈希表+Set去重

1. 遍历数组；并使用哈希表存储，key为数组对应的元素，value为该元素在数组中出现的次数
2. 使用Set对map的value值进行去重
3. 比较`map.size() == set.size()` 相等则返回true，否则返回false

##### 完整代码如下：

```java
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        // map + set去重
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        
        Set<Integer> set = new HashSet<>();
        map.forEach((key, value) -> {
            set.add(value);
        });
        return map.size() == set.size();
    }
}
```

#### 方法二：使用桶代替哈希表

因为题目中有给定条件：`-1000 <= arr[i] <= 1000`	,所以我们可以想到用桶来代替哈希表

1. 初始化桶，并遍历数组；当前数组的值+1000对应的是桶的下标，遍历到相同值时，桶对应位置 的值+ 1
2. 遍历桶，并使用Set；如果遍历到桶的值为0则continue，如果`set.add(bucketNum) == false` 说明重复了，就返回false

##### 完整代码如下：

```java
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        // 使用桶代替哈希表
        int[] bucket = new int[2001];
        for(int i= 0; i < arr.length; i++){
            bucket[1000 + arr[i]]++;
        }
        Set<Integer> set = new HashSet<>();
        for(int num : bucket){
            if(num == 0){
                continue;
            }
            if(!set.add(num)){
                return false;
            }
        }
        return true;
    }
}
```

