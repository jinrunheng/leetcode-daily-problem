func insert(intervals [][]int, newInterval []int) (ans [][]int) {
	i := 0
	len := len(intervals)
	for i < len && intervals[i][1] < newInterval[0] {
		ans = append(ans, intervals[i])
		i++
	}
	start := newInterval[0]
	end := newInterval[1]

	for i < len && intervals[i][0] <= newInterval[1] {
		start = getMin(start, intervals[i][0])
		end = getMax(end, intervals[i][1])
		i++
	}

	ans = append(ans, []int{start, end})
	for i < len {
		ans = append(ans, intervals[i])
		i++
	}

	return ans
}

func getMin(a int, b int) int {
	if a < b {
		return a
	}
	return b
}

func getMax(a int, b int) int {
	if a > b {
		return a
	}
	return b
}