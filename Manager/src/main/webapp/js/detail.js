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
        url:"/house/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if(resp.isSuccess){
                var house=JSON.parse(resp.content);
                console.log(house)
                //将后台数据放入页面中
                $("#area").text(house.area)
                $("#unitNum").text(house.unitNum)
                $("#floor").text(house.floor)
                $("#remark").text(house.remark)
                $("#roomNum").text(house.roomNum)
                $("#price").text(house.price)
                $("#space").text(house.space)
                $("#limit").text(house.limit)
                $("#isDoubleAir").text(house.isDoubleAir)
                $("#createTime").text(house.createTime)
                $("#lastModifiedTime").text(house.lastModifiedTime)
                $("#facility").text(house.facility)
                $("#community").text(house.community)
                $("#direction").text(house.direction)
                $("#address").text(house.address)

            }
        }


    })

})