## 四数相加 II

[454. 四数相加 II](https://leetcode-cn.com/problems/4sum-ii/)

#### 解题思路：Hash

思路参照题目[1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

两数之和

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            }else{
                map.put(nums[i],i);
            }
        }
        return null;
    }
}
```

本题的思路与两束之和是一致的，只是我们存储的key为两个数组计算出来的和

1. 使用map存储AB两数组之和，首先求出A和B的两数之和sumAB,以sumAB为key，sumAB出现的次数为value，存入到hashmap中
2. 再次遍历计算C和D中任意两数字之和的相反数sumCD,在hashmap中查找是否存在key为sumCD

算法的时间复杂度：O(N ^ 2)

#### 代码

```java
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                int sumAB = A[i] + B[j];
                if(map.containsKey(sumAB)){
                    map.put(sumAB,map.get(sumAB) + 1);
                }else{
                    map.put(sumAB,1);
                }
            }
        }

        for(int i = 0; i < C.length; i++){
            for(int j = 0; j < D.length; j++){
                int sumCD = -(C[i] + D[j]);
                if(map.containsKey(sumCD)){
                    res += map.get(sumCD);
                }
            }
        }
        return res;
    }
}
```

