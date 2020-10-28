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