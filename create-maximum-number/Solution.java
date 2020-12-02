class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int start = Math.max(0, k - n);
        int end = Math.min(k, m);
        int[] res = new int[k];
        for (int i = start; i <= end; i++) {
            int[] arr1 = findMaxSubsequence(nums1, i);
            int[] arr2 = findMaxSubsequence(nums2, k - i);
            int[] arr = merge(arr1, arr2);
            if (compare(arr, 0, res, 0) > 0) {
                res = arr;
            }
        }
        return res;
    }

    // 如果是负值说明 nums1 < nums2 正值说明 nums1 > nums2
    private static int compare(int[] nums1, int index1, int[] nums2, int index2) {
        while (index1 < nums1.length && index2 < nums2.length) {
            int diff = nums1[index1] - nums2[index2];
            if (diff != 0) {
                return diff;
            }
            index1++;
            index2++;
        }
        return (nums1.length - index1) - (nums2.length - index2);
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2;
        }
        if (nums2.length == 0) {
            return nums1;
        }
        int p1 = 0;
        int p2 = 0;
        int i = 0;
        int[] res = new int[nums1.length + nums2.length];
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                if (compare(nums1, p1, nums2, p2) > 0) {
                    res[i++] = nums1[p1++];
                } else {
                    res[i++] = nums2[p2++];
                }
            } else {
                res[i++] = nums1[p1] > nums2[p2] ? nums1[p1++] : nums2[p2++];
            }
        }
        while (p1 < nums1.length) {
            res[i++] = nums1[p1++];
        }
        while (p2 < nums2.length) {
            res[i++] = nums2[p2++];
        }
        return res;
    }

    private static int[] findMaxSubsequence(int[] nums, int k) {
        if (k == 0) {
            return new int[]{};
        }
        if (nums.length == k) {
            return nums;
        }

        Stack<Integer> stack = new Stack<>();
        // 单调递减栈
        int left = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && left > 0 && nums[i] > stack.peek()) {
                stack.pop();
                left--;
            }
            stack.push(nums[i]);
        }

        while (left > 0) {
            stack.pop();
            left--;
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
