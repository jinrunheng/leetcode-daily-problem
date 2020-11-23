class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0){
            return 0;
        }
        // 按照每个气球的右侧边界排序
        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]);
        int res = 1;
        int shotPosition = points[0][1];
        for(int i = 0; i < points.length; i++){
            if (shotPosition <= points[i][1] && shotPosition >= points[i][0]) {
                continue;
            }
            shotPosition = points[i][1];
            res++;
        }
        return res;
    }
}