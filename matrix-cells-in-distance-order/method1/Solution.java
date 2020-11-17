class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        
        Map<int[],Integer> map = new HashMap<>();
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                map.put(new int[]{i,j},(Math.abs(i - r0) + Math.abs(j - c0)));
            }
        }
        
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .toArray(new int[R * C][]);

    }
}