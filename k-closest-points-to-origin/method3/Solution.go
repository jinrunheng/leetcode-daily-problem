func kClosest(points [][]int, K int) [][]int {
    start := 0
    end := len(points) - 1
    for start < end {
        index := partition(points,start,end)
        if index == K - 1{
            break;
        } else if index < K - 1{
            start = index + 1 
        }else {
            end = index - 1
        }
    }
    return points[:K]
}

func partition(points[][] int,start int,end int) int{
    less := start - 1
    more := end
    cur := start
    for cur < more {
        if distance(points[cur]) < distance(points[end]){
            less++
            points[less],points[cur] = points[cur],points[less];
            cur++
        }else if distance(points[cur]) > distance(points[end]){
            more--
            points[cur],points[more] = points[more],points[cur]
        }else {
            cur++
        }
    }
    points[end],points[more] = points[more],points[end]
    return more
}

func distance(point[] int) int{
    return point[0] * point[0] + point[1] * point[1];
}