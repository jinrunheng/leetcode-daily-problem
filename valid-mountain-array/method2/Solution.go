func validMountainArray(A []int) bool {
	p1 := 0
	p2 := len(A) - 1
	for p1 < len(A)-1 && A[p1] < A[p1+1] {
		p1++
	}
	for p2 > 0 && A[p2] < A[p2-1] {
		p2--
	}
	return p1 != len(A)-1 && p2 != 0 && p1 == p2
}