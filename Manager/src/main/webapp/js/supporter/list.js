$(function () {
    loadData(1);
})
function deleteContract(id) {
    var flag=confirm("确定要删除吗")
    if(!flag){
        return;
    }
//请求后台删除
    $.ajax({
        url:"/supporter/delete.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                //重新加载
                window.location.reload();
            } else{
                alert("删除失败")
            }
        }
    })
    //刷新页面
}
function currentPage() {
    return parseInt($("#currentPage").text())
}
function prePage() {
    var prePage=currentPage()-1>0?currentPage()-1:1;
    loadData(prePage)
}
function nextPage() {
    loadData(currentPage()+1)
}
function loadData(page) {
    $.ajax({
        url:"/supporter/list.do?page="+page,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                //需要将json字符串转json对象
                var jsonObject=JSON.parse(resp.content);
                var table=$("#supporterData")
                var html="";
                html+="<tr>\n" +
                    "\t\t\t<th>序号</th>\n" +
                    "\t\t\t<th>合同号</th>\n" +
                    "\t\t\t<th>房屋信息</th>\n" +
                    "\t\t\t<th>租户信息</th>\n" +
                    "\t\t\t<th>房租总额</th>\n" +
                    "\t\t\t<th>付款方式</th>\n" +
                    "\t\t\t<th>押金</th>\n" +
                    "\t\t\t<th>付款期数</th>\n" +
                    "\t\t\t<th>签署人</th>\n" +
                    "\t\t\t<th>合同状态</th>\n" +
                    "\t\t\t<th>操作</th>\n" +
                    "\t\t</tr>"
                for (var i = 0; i < jsonObject.length; i++) {
                    var supporter=jsonObject[i];
                    console.log(supporter)
                    html+="<tr>"
                    html+="<td>"+supporter.id+"</td>"
                    html+="<td>"+supporter.supporterNumber+"</td>"
                    html+="<td>"+supporter.houseInfo+"</td>"
                    html+="<td>"+supporter.renterInfo+"</td>"
                    html+="<td>"+supporter.price+"</td>"
                    html+="<td>"+supporter.payMethod+"</td>"
                    html+="<td>"+supporter.deposit+"</td>"
                    html+="<td>"+supporter.payPeriods+"</td>"
                    html+="<td>"+supporter.people+"</td>"
                    html+="<td>"+supporter.status+"</td>"

                    var detailUrl="detail.html?id="+supporter.id
                    var editUrl="edit.html?id="+supporter.id
                    html+="<td>\n" +
                        "\t\t\t\t<a class=\"fa fa-info\" title=\"详情\" href="+detailUrl+"></a>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-pencil\" title=\"编辑\" href="+editUrl+"></a>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-remove\" title=\"删除\" href=\"#\" onclick=\"deleteContract("+supporter.id+")\"></a>\n" +
                        "\t\t\t</td>"
                    html+="</tr>"
                }
                $("#RenterListInfo").text("共有"+jsonObject.length+"条记录，当前第"+page+"页")
                $("#currentPage").text(page)
                table.html(html)
            }
        }
    })
}
