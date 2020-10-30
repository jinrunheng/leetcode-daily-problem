class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    perimeter += composePerimeter(grid,i,j);
                }
            }
        }
        return perimeter;
    }

    private static int composePerimeter(int[][] grid,int x,int y){
        // up : x - 1,y
        // down : x + 1,y
        // left : x,y - 1
        // right : x,y + 1
        int length = 0;
        length += composeLength(grid,x - 1,y);
        length += composeLength(grid,x + 1,y);
        length += composeLength(grid,x,y - 1);
        length += composeLength(grid,x,y + 1);
        return length;
    }

    private static int composeLength(int[][] grid,int x,int y){
        if(isLegal(grid,x,y)){
            return grid[x][y] == 0 ? 1 : 0;
        }
        return 1;
    }

    private static boolean isLegal(int[][] grid,int x,int y){
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}