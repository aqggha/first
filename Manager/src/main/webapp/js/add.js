function insert() {
    //获取input标签中的值
    /*jquery封装好的方法
    document.getElementById()
     */
    //$("#").val() 如果当前元素是input 使用val来获取值
    //$("#").text() 如果不是表单类型的 使用text来获取元素中的文本<div></div>
    //$("#").html() 获取标签内部的html代码


    $.ajax({
        url: "/house/insert.do",
        dataType: "json",
        type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        data: {
            area: $("#area").val(),
            community: $("#community").val(),
            price: $("#price").val(),
            facility: $("#facility").val(),
            space: $("#space").val(),
            address: $("#address").val(),
            remark: $("#remark").val(),
            isDoubleAir: $("#isDoubleAir").val(),
            status: $("#status").val(),
            unitNum: $("#unitNum").val(),
            floor: $("#floor").val(),
            roomNum: $("#roomNum").val(),
            direction: $("#direction").val(),
            limit: $("#limit").val(),
            fitment:$("#fitment").val()
        },
        success:function (resp) {
            if(resp.isSuccess){
                alert("insert success")
                window.location.href="/admin.html"
            }else{
                alert("insert failure"+resp.error)
            }
        }
    })

}
/*
$("#submit"){

}*/
