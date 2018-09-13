function insert() {
    //获取input标签中的值
    /*jquery封装好的方法
    document.getElementById()
     */
    //$("#").val() 如果当前元素是input 使用val来获取值
    //$("#").text() 如果不是表单类型的 使用text来获取元素中的文本<div></div>
    //$("#").html() 获取标签内部的html代码
    $.ajax({
        url: "/lease/insert.do",
        dataType: "json",
        //type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        data: {
            houseId: $("#houseId").val(),
            renterId: $("#renterId").val(),
            amount: $("#amount").val(),
            remark: $("#remark").val()
        },
        success:function (resp) {
            if(resp.isSuccess){
                alert("insert success")
                window.location.href="/view/lease/leaselist.html"
            }else{
                alert("insert failure "+ resp.error)
            }
        }
    })

}