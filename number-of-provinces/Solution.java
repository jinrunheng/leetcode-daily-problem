class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isConnected[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.size;
    }

    class UnionFind{
        public Map<Integer,Integer> map; // key: current node ; value: parent node
        public int size; // 维护集合的数量

        public UnionFind(int n){
            map = new HashMap<>();
            for(int i = 0; i < n; i++){
                map.put(i,i); // 初始化，每个节点自己指向自己
            }
            size = n;
        }

        // 找到节点的头节点    
        public int find(int i){
            int root = map.get(i);
            if(i != root){
                root = find(root);
            }
            map.put(i,root);
            return root;
        }
        public void union(int p,int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot != qRoot){
                map.put(pRoot,qRoot);
                size--; // 合并两个集合之后，集合的总数减一
            }
        }
    }
}