class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1){
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for(int[] connection : connections){
            uf.union(connection[0],connection[1]);
        }
        return uf.setCount - 1;

    }

    class UnionFind{
        Map<Integer,Integer> parent;
        Map<Integer,Integer> size;

        // 联通分量的个数
        int setCount;

        public UnionFind(int n){
            setCount = n;
            parent = new HashMap<>();
            size = new HashMap<>();
            for(int i = 0; i < n; i++){
                parent.put(i,i);
                size.put(i,1);
            }
        }

        public int findRoot(int i){
            int root = parent.get(i);
            if(root != i){
                root = findRoot(root);
            }
            // 扁平化处理
            parent.put(i,root);
            return root;
        }

        public boolean isSameSet(int p,int q){
            return findRoot(p) == findRoot(q);
        }

        public void union(int p,int q){
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            if(pRoot != qRoot){
                int pSize = size.get(p);
                int qSize = size.get(q);
                if(pSize < qSize){
                    parent.put(pRoot,qRoot);
                    size.put(qRoot,pSize + qSize);
                }else {
                    parent.put(qRoot,pRoot);
                    size.put(pRoot,pSize + qSize);
                }
                setCount--;
            }
        }
    }
}

