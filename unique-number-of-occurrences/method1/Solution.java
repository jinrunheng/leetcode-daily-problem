class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        // 最简单的思路 使用hashmap + set去重
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