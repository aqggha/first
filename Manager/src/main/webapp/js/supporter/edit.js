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
        url:"/contract/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess) {
                var contract = JSON.parse(resp.content);
                console.log(contract)
                //将后台数据放入页面中

                $("#contractNumber").val(contract.contractNumber)
                $("#id").val(contract.id)
                $("#houseInfo").val(contract.houseInfo)
                $("#renterInfo").val(contract.renterInfo)
                $("#price").val(contract.price)
                $("#payMethod").val(contract.payMethod)
                $("#deposit").val(contract.deposit)
                $("#payPeriods").val(contract.payPeriods)
                $("#people").val(contract.people)
                $("#status").val(contract.status)
            }
        }
    })
})

function save() {
    $.ajax({
        url:"/contract/update.do",
        dataType:"json",
        //type:"post",
        scriptCharset:"utf-8",
        data:{
            id: $("#id").val(),
            contractNumber: $("#contractNumber").val(),
            renterId: $("#renterId").val(),
            houseInfo: $("#houseInfo").val(),
            renterInfo: $("#renterInfo").val(),
            price: $("#price").val(),
            payMethod: $("#payMethod").val(),
            deposit: $("#deposit").val(),
            payPeriods: $("#payPeriods").val(),
            people: $("#people").val(),
            status: $("#status").val()
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