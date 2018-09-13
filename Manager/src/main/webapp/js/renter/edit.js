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
        url:"/renter/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess) {
                var renter = JSON.parse(resp.content);
                console.log(renter)
                //将后台数据放入页面中
                $("#id").val(renter.id)
                $("#name").val(renter.name)
                $("#phoneNumber").val(renter.phoneNumber)
                $("#sex").val(renter.sex)
                $("#nativePlace").val(renter.nativePlace)
                $("#idNumber").val(renter.idNumber)
            }
        }
    })
})

function save() {
    $.ajax({
        url:"/renter/update.do",
        dataType:"json",
        //type:"post",
        scriptCharset:"utf-8",
        data:{
            id: $("#id").val(),
            name: $("#name").val(),
            phoneNumber: $("#phoneNumber").val(),
            sex: $("#sex").val(),
            nativePlace: $("#nativePlace").val(),
            idNumber: $("#idNumber").val()
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