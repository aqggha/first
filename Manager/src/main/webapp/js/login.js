function login() {
    //第一步 获取表单数据
    var name=$("#username").val()
    var password=$("#password").val()
    var tip=$("#tip")
    //第二部 校验
    if(name.length==0){
        tip.text("名字不能为空")
        return
    }
    if(password.length<6){
        tip.text("密码长度不能小于6位")
        return
    }
    //发送ajax请求
    $.ajax({
        url:"/login/login.do",
        method:"post",
        data:{
            name:name,
            password:md5(password)
        },
        dataType:"json",
        success:function(resp){
            console.log(resp)
            if(resp['isSuccess']){
                window.location.href="admin.html"
            }else{
                tip.text(resp['error'])
            }
            /*window.location.href="admin.html"*/
        },
        error:function (resp){
            console.log(resp)
            tip.text(resp['error'])
        }

    })
}
function md5(password) {
    return hex_md5(password);
}