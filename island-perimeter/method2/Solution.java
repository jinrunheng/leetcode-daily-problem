class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        int upIsLand = 0;
        int downIsLand = 0;
        int leftIsLand = 0;
        int rightIsLand = 0;

        int rowLength = grid.length;
        int colLength = grid[0].length;
        for(int i = 0; i < rowLength; i++){
            for(int j = 0; j < colLength; j++){
                if(grid[i][j] == 1){
                    upIsLand = j > 0 ? grid[i][j - 1] : 0;
                    downIsLand = j < colLength - 1 ? grid[i][j + 1] : 0;
                    leftIsLand = i > 0 ? grid[i - 1][j] : 0;
                    rightIsLand = i < rowLength - 1 ? grid[i + 1][j] : 0;

                    perimeter += 4 - (upIsLand + downIsLand + leftIsLand + rightIsLand);
                }
            }
        }
        return perimeter;
    }
}