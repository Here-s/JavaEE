//加上一个逻辑，通过 GET/login 这个接口来获取当前的登陆状态
function getUserInfo(pageName) {
    $.ajax({
        type: 'get',
        url: 'login',
        success: function (body) {
            //判断此处的 body 是不是一个游戏的 user 对象
            if (body.userId && body.userId > 0) {
                //登陆成功，不做处理
                console.log("当前用户登陆成功！用户名：" + body.username);
                //根据当前用户登录的情况，修改当前的用户名
                if (pageName == 'blog.html') {
                    changeUserName(body.username);
                }
            } else {
                //登陆失败，让 前端页面 跳转到 blog_login.html
                alert("当前您尚未登录，请登录后再访问博客列表")
                location.assign('blog_login.html')
            }
        },
        error: function () {
            alert("当前您尚未登录，请登录后再访问博客列表")
            location.assign('blog_login.html')
        }
    });
}

getUserInfo();

function changeUserName(username) {
    let h3 = document.querySelector('.card>h3');
    h3.innerHTML = username;
}