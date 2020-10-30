## 岛屿的周长

#### 方法：找规律



我们可以发现如下规律：

对于一块陆地，

如果它的上，下，左，右均为陆地，那么它所贡献的周长为0；

如果它的四周只有某一块为陆地，那么它所贡献的周长为3；

如果它的四周有两块陆地，那么它所贡献的周长为2；

如果它的四周有三块陆地，那么它所贡献的周长为1。

所以，我们的思路为，遍历二维数组，找到陆地(`grid[x][y] == 1`)，然后累加每一块陆地所能贡献的周长

##### 完整代码如下：

```java
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
```



也可以稍稍调整一下思路，不过整体的思想同上面是一样的：

1. 遍历二维数组，判断当前遍历的空间是否是一块陆地
2. 如果是陆地，那么就计算当前陆地与四周相邻的陆地个数 
3. 当前陆地所能提供给整块岛屿的周长为：4 - 当前陆地四周的陆地个数

##### 完整代码如下：

```java
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
```



