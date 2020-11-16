class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // 首先按照h降序，k升序进行排列
        Arrays.sort(people,(o1,o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        List<int[]> list = new LinkedList<>();
        for(int[] p : people){
            list.add(p[1],p);
        }
        return list.toArray(new int[list.size()][2]);
    }
}
