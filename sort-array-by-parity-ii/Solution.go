func sortArrayByParityII(A []int) []int {
    res := make([]int, len(A))
    evenIndex := 0
    oddIndex := 1
    for i := 0; i < len(A); i++ {
        if A[i] % 2 == 0 {
            res[evenIndex] = A[i];
            evenIndex += 2;
        }else {
            res[oddIndex] = A[i]
            oddIndex += 2;
        }
    }
    return res
}