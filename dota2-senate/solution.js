/**
 * @param {string} senate
 * @return {string}
 */
var predictPartyVictory = function(senate) {
    let radiant_queue = []
    let dire_queue = []
    
    for(let i = 0; i < senate.length; i++){
        if(senate[i] == 'R'){
            radiant_queue.push(i)
        }else {
            dire_queue.push(i)
        }
    }

    while(radiant_queue.length != 0 && dire_queue.length != 0){
        if(radiant_queue[0] < dire_queue[0]){
            dire_queue.shift()
            radiant_queue.push(radiant_queue.shift() + senate.length)
        }else {
            radiant_queue.shift()
            dire_queue.push(dire_queue.shift() + senate.length)
        }
    }

    return radiant_queue.length == 0 ? "Dire" : "Radiant"
};