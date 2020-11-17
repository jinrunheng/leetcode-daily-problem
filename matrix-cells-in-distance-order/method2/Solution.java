class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 桶排序
        int maxDist = Math.max(r0,R - 1 - r0) + Math.max(c0,C - 1 - c0);
        // 初始化桶
        List<List<int[]>> bucket = new ArrayList<>();
        for(int i = 0; i <= maxDist; i++){
            bucket.add(new ArrayList<>());
        }
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                bucket.get(getDist(i,j,r0,c0)).add(new int[]{i,j});
            }
        }
        int[][] res = new int[R * C][0];
        int index = 0;
        for(int i = 0 ; i <= maxDist; i++){
            for(int[] b : bucket.get(i)){
                res[index++] = b;
            }
        }
        return res;
    }

    private static int getDist(int r1,int c1,int r2,int c2){
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}