class Solution {
    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        int n = 4 * len * len;

        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < len; i++){
            char[] chars = grid[i].toCharArray();
            for(int j = 0; j < len; j++){
                int index = 4 * (i * len + j);
                char c = chars[j];
                if(c == '/'){
                    uf.union(index,index + 3);
                    uf.union(index + 1,index + 2);
                }else if (c == '\\'){
                    uf.union(index,index + 1);
                    uf.union(index + 2,index + 3);
                }else {
                    uf.union(index,index + 1);
                    uf.union(index + 1,index + 2);
                    uf.union(index + 2,index + 3);
                }

                // 单元格之间合并
                // 向右合并
                if(j + 1 < len){
                    uf.union(index + 1, 4 * (i * len + j + 1) + 3);
                }
                // 向下合并
                if(i + 1 < len){
                    uf.union(index + 2, 4 * ((i + 1) * len + j));
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind {
        private Map<Integer,Integer> parent;
        private Map<Integer,Integer> size;
        // 记录联通分量的个数
        private int count;

        public int getCount(){
            return count;
        }

        public UnionFind(int n){
            count = n;
            parent = new HashMap<>();
            size = new HashMap<>();

            for(int i = 0; i < n; i++){
                parent.put(i,i);
                size.put(i,1);
            }
        }

        public int find(int i){
            int root = parent.get(i);
            if(i != root){
                root = find(root);
            }
            parent.put(i,root);
            return root;
        }

        public void union(int p,int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot != qRoot){
                int pSize = size.get(p);
                int qSize = size.get(q);
                if(pSize < qSize){
                    parent.put(pRoot,qRoot);
                    size.put(qRoot,qSize + pSize);
                }else {
                    parent.put(qRoot,pRoot);
                    size.put(pRoot,qSize + pSize);
                }

                count--;
            }
        }
    }
}