class Solution {

    public int[] hitBricks(int[][] grid, int[][] hits) {
        // 返回结果
        int[] res = new int[hits.length];
        // copy grid数组
        int rows = grid.length;
        int cols = grid[0].length;
        int size = rows * cols;
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        // 将hits数组的砖块全部敲掉
        for (int[] hit : hits) {
            int x = hit[0];
            int y = hit[1];
            copy[x][y] = 0;
        }

        // 初始化并查集
        // 多初始化一个值，最后一个用来表示顶部
        UnionFind uf = new UnionFind(size + 1);
        for (int i = 0; i < cols; i++) {
            if (copy[0][i] == 1) {
                uf.union(i, size);
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 看看上面，如果copy[i][j]与copy[i - 1][j]均为1 则合并
                if (copy[i][j] == 1 && copy[i - 1][j] == 1) {
                    uf.union(getIndex(i, j, grid), getIndex(i - 1, j, grid));
                }

                // 看看左边，如果copy[i][j] 与 copy[i][j - 1]均为1 则合并，这里面需要考虑 j 越界的问题
                if (copy[i][j] == 1 && j > 0 && copy[i][j - 1] == 1) {
                    uf.union(getIndex(i, j, grid), getIndex(i, j - 1, grid));
                }
            }
        }

        // 倒序添加砖块
        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];

            // 如果原来就没有砖块，则不必添加
            if (grid[x][y] == 0) {
                continue;
            }

            // 在倒序添加这个砖块之前，有多少砖块和屋顶相连(同属一个联通分量)
            int before = uf.getSize(size);
            // 添加砖块
            // 如果补的砖块在第一行，那么就与屋顶相连
            if (x == 0) {
                uf.union(y, size);
            }

            // 和四周都要union
            int[] up = {x - 1, y};
            int[] down = {x + 1, y};
            int[] left = {x, y - 1};
            int[] right = {x, y + 1};
            int[][] directions = {up, down, left, right};
            for (int[] direction : directions) {
                if (isLegal(direction[0], direction[1], copy) && copy[direction[0]][direction[1]] == 1) {
                    uf.union(getIndex(x, y, copy), getIndex(direction[0], direction[1], copy));
                }
            }

            // 在倒序添加这个砖块之后，有多少砖块和屋顶相连(同属一个联通分量)
            int after = uf.getSize(size);
            res[i] = Math.max(0, after - before - 1);
            copy[x][y] = 1;
        }
        return res;
    }

    private boolean isLegal(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    private int getIndex(int i, int j, int[][] grid) {
        return grid[0].length * i + j;
    }

    class UnionFind {
        private Map<Integer, Integer> map;
        private Map<Integer, Integer> size;

        public UnionFind(int n) {
            map = new HashMap<>();
            size = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(i, i);
                size.put(i, 1);
            }
        }

        public int find(int i) {
            int root = map.get(i);
            if (root != i) {
                root = find(root);
            }
            // 路径压缩
            map.put(i, root);
            return root;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot != qRoot) {
                int pSize = size.get(pRoot);
                int qSize = size.get(qRoot);
                if (pSize < qSize) {
                    map.put(pRoot, qRoot);
                    size.put(qRoot, pSize + qSize);
                } else {
                    map.put(qRoot, pRoot);
                    size.put(pRoot, pSize + qSize);
                }
            }
        }

        public int getSize(int i) {
            int root = find(i);
            return size.get(root);
        }
    }
}