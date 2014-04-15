var context = "/RCTD";
var viewer_id;

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
    document.getElementById(buttonID).className = "menu_checked";
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
        children[i].className = "menu_unchecked";
    }
}

function checkStartMenuButton() {
    var AllMenu = document.getElementById("menuAll");
    AllMenu.className = "menu_checked";
}

function clearSpace() {
    document.getElementById("window").style.visibility="hidden";
    document.getElementById("spinner").style.visibility="visible";
}

function replaceIframeContent(servletpath) {
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("window").innerHTML = xmlhttp.responseText;
            processingComplete();
        }
    }
    xmlhttp.open("GET",context + servletpath,true);
    xmlhttp.send();
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
    document.getElementById("window").style.visibility = "visible";
   document.getElementById("spinner").style.visibility="hidden";
}

function hideFrameByDefault(){

}

initScript();
processingComplete();

