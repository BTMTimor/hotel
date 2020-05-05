//处理json数据
function getOneByForm() {
    let url = $("#url_input").val();
    let body = $("#body_input").val();
    let type = $("#type_select").val();
    let headers = $("#header_input").val();

    $.ajax({
        url: url,//请求地址
        // data: {id: 3},//提交的数据
        data: body.toString(),//提交的数据
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        type: type,//提交的方式
        dataType: "TEXT", //返回类型 TEXT：字符串 JSON XML
        headers: {headers},
        success: function (data) { // 校验返回内容，进行跳转
            //将获取到的数据输出到元素上
            $("#showArea").text(data);
            console.log(data);
        },
        error: function (xhr) {
            clearShowArea();
            // 失败输出HTTP状态码
            alert("调用失败！HTTP状态码：" + xhr.status);
        }
    })
}

function getOneByJson() {
    let url = $("#url_input").val();
    let body = $("#body_input").val();
    let type = $("#type_select").val();
    let headers = $("#header_input").val();
    $.ajax({
        url: url,//请求地址
        data: body,//提交的数据
        contentType: "application/json; charset=utf-8",
        headers: {headers},
        type: type,//提交的方式
        dataType: "TEXT", //返回类型 TEXT：字符串 JSON XML
        success: function (data) { // 校验返回内容，进行跳转
            //将获取到的数据输出到元素上
            $("#showArea").text(data);
            console.log(data);
        },
        error: function (xhr) {
            clearShowArea();
            // 失败输出HTTP状态码
            alert("调用失败！HTTP状态码：" + xhr.status);
        }
    })
}

//  清空结果
function clearShowArea() {
    $("#showArea").empty();
}

// 发送请求方法入口，判断数据类型分别调用对应方法
function send() {
    let bodyType = $('input:radio[name=bodyType]:checked').val();
    console.log("bodyType: " + bodyType)
    if (bodyType == "form") {
        getOneByForm();
    } else if (bodyType == "json") {
        getOneByJson();
    } else {
        alert("不支持该类型：" + bodyType)
    }
}

function jsonToFormData(json) {
    let object = JSON.parse(body);
    let rs = "";
    object.key(obj).forEach()
    {
        rs = {}
    }
}

// 跳转首页
function toIndex() {
    window.location.href = '/';
}