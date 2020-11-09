class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < K; i++) {
            maxHeap.offer(new int[]{i, points[i][0] * points[i][0] + points[i][1] * points[i][1]});
        }
        for (int i = K; i < points.length; i++) {
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (distance < maxHeap.peek()[1]) {
                maxHeap.poll();
                maxHeap.offer(new int[]{i, distance});
            }
        }
        int res[][] = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[maxHeap.poll()[0]];
        }
        return res;
    }
}