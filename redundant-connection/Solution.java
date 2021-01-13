class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int node1 = edge[0];
            int node2 = edge[1];
            if(uf.find(node1) != uf.find(node2)){
                uf.union(node1,node2);
            }else {
                return edge;
            }
        }
        return new int[0];
    }

    class UnionFind {
        private Map<Integer,Integer> map;
        private Map<Integer,Integer> size;

        public UnionFind(int n){
            map = new HashMap<>();
            size = new HashMap<>();
            for(int i = 1; i <= n; i++){
                map.put(i,i);
                size.put(i,1);
            }
        }

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
                int pSize = size.get(pRoot);
                int qSize = size.get(qRoot);
                if(pSize < qSize){
                    map.put(pRoot,qRoot);
                    size.put(qRoot,qSize + pSize);
                }else {
                    map.put(qRoot,pRoot);
                    size.put(pRoot,qSize + pSize);
                }
            }
        }
    }
}