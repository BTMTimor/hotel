

function checkProperty(data, keys){
    let flag = true
    let lessKey;
    for (let i = 0; i < keys.length; i++){
        if (!data.hasOwnProperty(keys[i]) ){
            flag = false;
            lessKey = keys[i]
            break;
        }
    }

    assert.deepEqual(flag, true,"缺少字段"+lessKey);
}

let bodyKey=["msg","code","data","requestId"]
Property(body, bodyKey)