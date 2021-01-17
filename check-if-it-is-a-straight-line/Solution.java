class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        // 两点确定一条直线
        // 通过前两个点确定斜率
        if(coordinates[1][0] == coordinates[0][0]){
            for(int i = 3; i < coordinates.length; i++){
                if(coordinates[i][0] != coordinates[1][0]){
                    return false;
                }
            }
            return true;
        }
        double k = (double) (coordinates[1][1] - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][0]);
        for(int i = 2; i < coordinates.length; i++){
            if((coordinates[i][0] == coordinates[i - 1][0]) || (double)(coordinates[i][1] - coordinates[i - 1][1]) / (coordinates[i][0] - coordinates[i - 1][0]) != k){
                return false;
            }
        }
        return true;
    }
}