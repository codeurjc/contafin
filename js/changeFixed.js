$(document).ready(function () {
    if ($(window).width() > 880) {
        $("#mainNav").addClass("fixed-top");
        $("#rContent").addClass("pl-5");
    }
    // Execute code each time window size changes
    $(window).resize(function () {
        if ($(window).width() <= 880) {
            $("#mainNav").removeClass("fixed-top");
            $("#rContent").removeClass("pl-5");
        } else {
            $("#mainNav").addClass("fixed-top");
            $("#rContent").addClass("pl-5");
        }
    });
});
