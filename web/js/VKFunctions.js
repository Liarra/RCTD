/**
 * Created with IntelliJ IDEA.
 * User: NBuchina
 * Date: 03.06.13
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
var access_token;
var proposedToday=false;

//Вызывать после нажатия "назад", если приложение не установлено у пользователя. Не чаще, чем раз в день
function proposeToInstall(){
    if(!proposedToday){
    VK.callMethod("showInstallBox");
        proposedToday=true;
    }
}

//Вызывать, когда человек второй раз заходит в приложение (уже есть другая дата). Однократно.
function proposeFriendsToInstall(){
    VK.callMethod("showInviteBox");
}

//Вызывать, если человек жмёт клавишу рассказать друзьям
function postToWall(att_id){
    VK.init();
    VK.api('wall.post', {
        message: "Сегодня я сделал доброе дело! Попробуй и ты - это бесплатно!"
//        ,attachments:"photo18462180_305497230"},
        ,attachments:att_id},
        function(data) {
        if (data.response) {
            // data.response is object
        }
    });
}

function initScriptToken() {
    var prmstr = window.location.search.substr(1);
    var prmarr = prmstr.split("&");
    var params = {};

    for (var i = 0; i < prmarr.length; i++) {
        var tmparr = prmarr[i].split("=");
        params[tmparr[0]] = tmparr[1];
    }

    access_token=params["access_token"];
}

initScriptToken();




