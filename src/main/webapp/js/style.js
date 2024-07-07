//获取元素
var a = document.querySelectorAll('.nav-bar a');
// 定义函数  给a加下边框
function tab(index){
    // 拿到数组的下标
    for(var i = 0; i < a.length; i++){
        //当index等于数组下标时 给a添加下边框
        if(index == i){
            a[i].className = 'active';
        }else{
            a[i].className = '';
        }
    }
   
}

var div = document.querySelectorAll('.new-course-box');
var li = document.querySelectorAll('.circle li');
//点击li切换div
function change(j){
    for(var i = 0; i < div.length; i++){
        if(i == j){
            div[i].style.display = 'block'
            li[i].style.backgroundColor = '#f8ce38';
        }else{
            div[i].style.display = 'none'
            li[i].style.backgroundColor = '';
        }
    }
}