func nextPermutation(nums []int)  {
    firstIndex := len(nums) - 2
    for ;firstIndex >= 0;firstIndex--{
        if nums[firstIndex] < nums[firstIndex + 1] {
            break
        }
    }
    if firstIndex == -1 {
        reverse(nums)
        return
    }
    secondIndex := len(nums) - 1
    for ;secondIndex >= firstIndex + 1; secondIndex-- {
        if nums[secondIndex] > nums[firstIndex]{
            break
        }
    }
    nums[firstIndex],nums[secondIndex] = nums[secondIndex],nums[firstIndex]
    reverse(nums[firstIndex + 1 :])
}

func reverse(a []int) {
    for i, n := 0, len(a); i < n/2; i++ {
        a[i], a[n-1-i] = a[n-1-i], a[i]
    }
}