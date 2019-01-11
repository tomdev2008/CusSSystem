// 请求servlet
setInterval(function () {
    ajax5s();
}, 5000);

function addMsgLog(msg){
    console.log(msg.log);
    var box = $("#logmsg");
    box.replaceWith(msg.log);
    $("#logBox div:eq(0)").scrollTop(999999);
}

function ajax5s(){
    var url = parent.localurl123+"/admin/getLog";
    $.ajax({
        type: "post",
        url: url,
        data: { "para": 1 },
        cache: false,
        async: false,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            addMsgLog(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("ajax请求失败！");
        }
    });
}

window.onload = function(){
    ajax5s();
}