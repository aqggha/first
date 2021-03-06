/**
 * $ (function(){})
 * 在页面加载的时候自动执行其中的代码
 */

$(function () {
    //获取当前页面的url
    var url=window.location.href;
    //获取当前页面的?后边的内容:请求参数
    var params=url.split("?")[1];
    //因为请求参数可能有多个,这里只需要id
    var kvs=params.split("&")
    var id;
    for (var i = 0; i < kvs.length; i++) {
        if(kvs[i].indexOf("id")==0){
            var id=kvs[i].split("=")[1]
            break;
        }
    }
    console.log(id)
    //根据id请求后台数据
    if(id==null){
        return
    }
    $.ajax({
        url:"/contract/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            // console.assert(resp)
            if(resp.isSuccess){
                var contract=JSON.parse(resp.content);
                console.log(contract)
                //将后台数据放入页面中

                $("#houseId").text(contract.houseId)
                $("#renterId").text(contract.renterId)
                $("#payTime").text(contract.payTime)
                $("#amount").text(contract.amount)
                $("#remark").text(contract.remark)
            }
        }


    })

})