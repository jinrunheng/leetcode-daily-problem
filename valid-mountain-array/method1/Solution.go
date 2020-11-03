func validMountainArray(A []int) bool {
	var isTop = false
	var pre = math.MinInt64
	for i := 0; i < len(A); i++ {
		if A[i] > pre {
			if isTop {
				return false
			}
			pre = A[i]
		} else if A[i] < pre {
			if i == 1 {
				return false
			}
			isTop = true
			pre = A[i]
		} else {
			return false
		}
	}
	return isTop
}