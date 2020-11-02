func intersection(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	var res []int
	p1, p2 := 0, 0
	for p1 < len(nums1) && p2 < len(nums2) {
		x, y := nums1[p1], nums2[p2]
		if x == y {
			if res == nil || x != res[len(res)-1] {
				res = append(res, x)
			}
			p1++
			p2++
		} else if x < y {
			p1++
		} else {
			p2++
		}
	}
	return res
}