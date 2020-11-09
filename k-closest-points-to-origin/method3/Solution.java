class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int l = 0;
        int r = points.length - 1;
        while (l < r) {
            int index = partition(points, l, r);
            if (index == K - 1) {
                break;
            } else if (index < K - 1) {
                // 需要对index的右边进行partition
                l = index + 1;
            } else {
                // 需要对index的左边进行partition
                r = index - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private static int partition(int[][] points, int start, int end) {
        int l = start - 1;
        int r = end;
        int cur = start;
        while (cur < r) {
            if (getDistance(points[cur]) < getDistance(points[end])) {
                swap(points, ++l, cur++);
            } else if (getDistance(points[cur]) > getDistance(points[end])) {
                swap(points, cur, --r);
            } else {
                cur++;
            }
        }
        swap(points, r, end);
        return r;
    }

    private static void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private static int getDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}