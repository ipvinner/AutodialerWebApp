(function ($, undefined) {

    "use strict"

    //  no-js
    if($("body").length && $("body").hasClass('no-js')) {
        $("body").removeClass('no-js');
    }

    function equalHeight() {
        var max = Math.max($(".main").height(), $(".aside").height(), $(window).height());
        $(".aside").height(max);
        $(".main").height(max);
    }

    equalHeight();
    $("#popup").dialog({
        autoOpen: false,
        "width": 340,
        resizable: false,
        show: {
            effect : "fade",
            duration: 500
        },
        hide: {
            effect: false,
            duration: 500
        }
    });

    $("#popupOpen").on("click", function(e) {
        e.preventDefault();
        $("#popup").dialog("open");
    });

    $( ".datepicker" ).datepicker({
        showOn: "button",
        buttonImage: "images/design/calendar-icon.png",
        buttonImageOnly: true
    });

    $(".dropdown").on("click", ".dropdown__header", function() {
        
        var parent = $(this).parent(),
            parentTr = $(this).parents("tr"),
            addHeight = parentTr.height();

        parent.toggleClass('dropdown_open');
        for(var i = 0; i < parent.find("table tr").length; ++i) {
            if(parent.hasClass('dropdown_open')) {
                $("<tr><td style='height: "+ addHeight + "px'>&nbsp;</td>").insertAfter(parentTr);
            } else {
                parentTr.next().remove();
            }
        }

    });

})(jQuery);