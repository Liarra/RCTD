var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

// open hidden layer
function mopen(id)
{
    // cancel close timer
    mcancelclosetime();
    var newmenuitem=document.getElementById(id);
    // close old layer
    if(ddmenuitem && (ddmenuitem!=newmenuitem)) ddmenuitem.style.visibility = 'hidden';

    // get new layer and show it
    ddmenuitem =newmenuitem ;
    ddmenuitem.style.visibility = 'visible';
//    moveDown(id);

}
// close showed layer
function mclose()
{
    if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
    closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
    if(closetimer)
    {
        window.clearTimeout(closetimer);
        closetimer = null;
    }
}

    function moveDown (contentid) {
        var content=document.getElementById(contentid) ;
        var top = parseInt(content.style.marginTop); // get the top margin
        // we'll be using this to
        // push the div down

        if (!top) {
            top = 0; // if the margin is undefined, default it to zero
        }

        top += 20; // add 20 pixels to the current margin

        content.style.marginTop = top + 'px'; // push div down

        if (top < 200) {
            // If it's not yet 200 pixels then call this function
            // again in another 100 milliseconds (100 ms gives us
            // roughly 10 fps which should be good enough):
            setTimeout(moveDown,50);
        }
}

// close layer when click-out
document.onclick = mclose;