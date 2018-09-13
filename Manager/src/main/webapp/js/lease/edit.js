$(function () {
    //获取路径上的id
    var url=window.location.href;
    var params=url.split("?")[1];
    var kvs=params.split("&")
    var id;
    for (var i = 0; i < kvs.length; i++) {
        if (kvs[i].indexOf("id")==0){
            var id= kvs[i].split("=")[1]
            break;
        }
    }
    console.log(id)
    //根据id请求后台数据
    if(id==null){
        return
    }
    $.ajax({
        // type:"post",
        // contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        url:"/lease/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess) {
                var lease = JSON.parse(resp.content);
                console.log(lease)
                //将后台数据放入页面中

                $("#houseId").val(lease.houseId)
                $("#id").val(lease.id)
                $("#renterId").val(lease.renterId)
                $("#amount").val(lease.amount)
                $("#payTime").val(lease.payTime)
                $("#remark").val(lease.remark)
            }
        }
    })
})

function save() {
    $.ajax({
        url:"/lease/update.do",
        dataType:"json",
        //type:"post",
        scriptCharset:"utf-8",
        data:{
            id: $("#id").val(),
            houseId: $("#houseId").val(),
            renterId: $("#renterId").val(),
            amount: $("#amount").val(),
            remark: $("#remark").val()
        },
        success:function (resp) {
            if (resp.isSuccess){
                window.location.reload()
            } else{
                alert("更新失败："+resp.error)
            }

        }

    })

}