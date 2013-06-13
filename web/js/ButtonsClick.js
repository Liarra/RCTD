var context = "/RCTD";
var viewer_id;

document.getElementById("myIframe").style.height=1;
document.getElementById("myIframe").style.width=10;
document.getElementById("myIframe").style.position="absolute";
document.getElementById("myIframe").style.left=-50;

function clickDonateButton(donateID) {
    parent.clearSpace();
    parent.replaceIframeContent("/thankyou?id=" + donateID + "&viewer_id=" + viewer_id)  ;
    return false;
}

function clickBackButton() {
    clearSpace();
    uncheckAllMenuButtons();
    replaceIframeContent("/main" + "?viewer_id=" + viewer_id);
    checkStartMenuButton();
}

function clickHelp() {
    clearSpace();
    uncheckAllMenuButtons();
    document.getElementById("helpMenuBtn").className = "btn_checked";
    replaceIframeContent("/help");
}

function clickMenu(TypeID, buttonID) {
    clearSpace();
    uncheckAllMenuButtons();
    document.getElementById(buttonID).className = "btn_checked";
    replaceIframeContent("/main?id=" + TypeID + "&viewer_id=" + viewer_id);
}

function clickWelcome() {
    var a1 = document.getElementById("dialog-overlay");
    a1.parentNode.removeChild(a1);
    var a2 = document.getElementById("dialog-box");
    a2.parentNode.removeChild(a2);
}

function uncheckAllMenuButtons() {
    var menuContainer = document.getElementById("menucell");
    var children = menuContainer.childNodes;

    for (var i in children) {
        children[i].className = "btn_unchecked";
    }
    document.getElementById("helpMenuBtn").className = "btn_unchecked";
}

function checkStartMenuButton() {
    var AllMenu = document.getElementById("menuAll");
    AllMenu.className = "btn_checked";
}

function clearSpace() {
//    if(IE)return;
    document.getElementById("spinner").style.display = "block";
    document.getElementById("myIframe").style.height=1;
    document.getElementById("myIframe").style.width=10;
    document.getElementById("myIframe").style.position="absolute";
    document.getElementById("myIframe").style.left=-50;
}

function replaceIframeContent(servletpath) {
    document.getElementById("myIframe").src = context + servletpath;
 }

function initScript() {
    var prmstr = window.location.search.substr(1);
    var prmarr = prmstr.split("&");
    var params = {};

    for (var i = 0; i < prmarr.length; i++) {
        var tmparr = prmarr[i].split("=");
        params[tmparr[0]] = tmparr[1];
    }

    setViewerId(params["viewer_id"]);
}

function setViewerId(vid) {
    viewer_id = vid;
}

function processingComplete() {
    document.getElementById("spinner").style.display = "none";
    document.getElementById("myIframe").style.display = "compact";

    document.getElementById("myIframe").style.height=440;
    document.getElementById("myIframe").style.width=800;
    document.getElementById("myIframe").style.position="relative";
    document.getElementById("myIframe").style.left=0;
}

initScript();