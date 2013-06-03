/**
 * Created with IntelliJ IDEA.
 * User: NBuchina
 * Date: 03.06.13
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */

//Вызывать после нажатия "назад", если приложение не установлено у пользователя. Не чаще, чем раз в день
function proposeToInstall(){
    VK.callMethod("showInstallBox");
}

//Вызывать, когда человек второй раз заходит в приложение (уже есть другая дата). Однократно.
function proposeToInstall(){
    VK.callMethod("showInviteBox");
}

//Вызывать, если человек жмёт клавишу рассказать друзьям
function postToWall(donationId){
    VK.Api.call('wall.post', {message: "Сегодня я сделал доброе дело! Попробуй и ты - это бесплатно!"});
}



