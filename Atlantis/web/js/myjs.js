function menuExpand(){
    if ($("#menuLeftExpand").css("width")=="300px"){
        $("#menuLeftExpand").css("width","90px");
        $("#btn-menu-left").css("display","none");
    } else {
        $("#menuLeftExpand").css("width","300px");
        $("#btn-menu-left").css("display","block");
    }
}