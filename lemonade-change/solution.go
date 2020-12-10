func lemonadeChange(bills []int) bool {
    five_amount,ten_amount := 0,0
    for i := 0; i < len(bills); i++ {
        if bills[i] == 5 {
            five_amount += 1
        }

        if bills[i] == 10 {
            if five_amount < 1 {
                return false
            }else {
                five_amount -= 1
                ten_amount += 1
            }
        }

        if bills[i] == 20 {
            if five_amount < 1 {
                return false
            }else {
                if ten_amount < 1 {
                    if five_amount < 3 {
                        return false
                    }else {
                        five_amount -= 3
                    }
                }else {
                    ten_amount -= 1
                    five_amount -= 1
                }
            }
        }
    }
    return true
}
