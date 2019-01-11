// 追加
// 存放列表的数组
let listuserarr = []; 
// 判断当前列表中是否存在该用户
function iSPageUserName(username){
    let flag = false;
    for(var i=0;i<listuserarr.length;i++){
        if(listuserarr[i]===username){
            flag=true;
            console.log("用户已被添加");
        }
    }
    if(flag===false){
        listuserarr[listuserarr.length]=username;
        // 增加一个用户按钮
        addButton(username);
    }
}

// 新增按钮
function addButton(username){
    let tab = $(".am-offcanvas-bar");// 获取div
    tab.find('#buttonmb').find('button').attr('onclick','addTab("'+username+'")'); //设置用户标识
    tab.find('#buttonmb').find('button').html(username);// 设置元素内容
    let box = tab.find('#buttonmb').html();//复制一份模板
    tab.append(box);// 追加
}