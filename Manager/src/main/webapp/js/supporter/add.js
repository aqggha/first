function insert() {
    //获取input标签中的值
    /*jquery封装好的方法
    document.getElementById()
     */
    //$("#").val() 如果当前元素是input 使用val来获取值
    //$("#").text() 如果不是表单类型的 使用text来获取元素中的文本<div></div>
    //$("#").html() 获取标签内部的html代码
    $.ajax({
        url: "/supporter/insert.do",
        dataType: "json",
        //type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        data: {
            supporterNumber: $("#supporterNumber").val(),
            houseInfo: $("#houseInfo").val(),
            renterInfo: $("#renterInfo").val(),
            price: $("#price").val(),
            payMethod: $("#payMethod").val(),
            deposit: $("#deposit").val(),
            payPeriods: $("#peyPeriods").val(),
            people: $("#people").val(),
            status: $("#status").val(),
        },
        success:function (resp) {
            if(resp.isSuccess){
                alert("insert success")
                window.location.href="/admin.html"
            }else{
                alert("insert failure "+ resp.error)
            }
        }
    })

}