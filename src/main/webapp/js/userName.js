$(function (){
    //获取用户信息
    $.ajax({
        url:'/e_xitang/usermessage',
        type:'post',
        success:function (data){
            var result = eval("("+data+")");
            if(result.code==400) {
                window.location.replace("login.html");
            }
            else if(result.code==200){
                $('#user-name-label').text(result.message);
            }
        }
    })
})