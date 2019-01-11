// 页面面板的控制部分
// 页面上已经创建的用户对话面板
var tmp = [];
// 面板是否被打开
let flag = false;
// 存放消息
var send_no_msg = {};

var tabCounter = 0; //统计打开了几个面板
var $tab = $('#doc-tab-demo-1');
var $nav = $tab.find('.am-tabs-nav');
var $bd = $tab.find('.am-tabs-bd');

function addTab(distinguish) {// 追加聊天面板函数
    iSUserPanel(distinguish);
    if (flag == false) {
        var nav = '<li><span class="am-icon-close"></span>' +
            '<a href="javascript: void(0)"> ' + distinguish + '的聊天面板</a></li>';
        // 复制一份模板
        var box_1 = $("#main").html();
        // 设置发送按钮，让发送按钮可以传入用户识别
        var send_button = "<button id=\"send\" style=\"margin-left: 420px;width:1002px;\" type=\"button\" class=\"am-btn am-btn-primary am-btn-block\" onclick=\"sendMsg('" + distinguish + "')\">发送</button></div>";
        // alert(box_1);
        var content = '<div class="am-tab-panel" value=' + distinguish + '>' + box_1 + send_button + '</div>';
        $nav.append(nav);
        $bd.append(content);
        tabCounter++;
        $tab.tabs('refresh');
        // 把此用户增加进数组
        tmp[tmp.length] = distinguish;
        // 调用函数把消息发送
        panelOpen(distinguish);
        console.log(distinguish + "的聊天面板被打开");
    } else {
        console.log(distinguish + "用户的面板已被打开");
    }
}

// 移除标签页
$nav.on('click', '.am-icon-close', function () {
    var $item = $(this).closest('li');
    var index = $nav.children('li').index($item);

    $item.remove();
    // 获取用户识别
    let flag = $bd.find('.am-tab-panel').eq(index).attr('value');
    $bd.find('.am-tab-panel').eq(index).remove();

    $tab.tabs('open', index > 0 ? index - 1 : index + 1);
    $tab.tabs('refresh');
    //console.log(tmp);
    // 当面板关闭时，移除数组中的数据
    for (let i = 0; i < tmp.length; i++) {
        if (tmp[i] == flag) {
            tmp[i] = '-15';
            console.log(flag + "的聊天面板关闭");
        }
    }
});

function iSUserPanel(distinguish) {
    for (let index = 0; index < tmp.length; index++) {
        if (tmp[index] == distinguish) {
            flag = true;
        }
    }
}

function iSUserPanel_1(distinguish) {
    for (let index = 0; index < tmp.length; index++) {
        if (tmp[index] == distinguish) {
            return true;// 用户面板被打开，返回真
        }
    }
    return false;
}

// 当客服没有打开面板时
function panelNot(send_name, send_value) {
    if (send_no_msg[send_name] === undefined) {// 当其中没有这个值时
        send_no_msg[send_name] = send_value;
    } else {
        var tmp = send_no_msg[send_name] + "---" + send_value;
        send_no_msg[send_name] = tmp;
    }
    console.log(send_no_msg);
}

// 当用户面板被打开时
function panelOpen(send_name){
    var tmp_msg_str = send_no_msg[send_name];
    var tmp_msg_arr = tmp_msg_str.split("---");
    for (let i = 0; i < tmp_msg_arr.length; i++) {
        console.log(tmp_msg_arr[i]);
        // 转换为对象
        var obj = eval('(' + tmp_msg_arr[i] + ')');
        // 把消息发送到面板
        addMessage(obj);
    }
}