$(document).ready(function () {
    if ($(window).width() > 880) {
        $("#mainNav").addClass("fixed-top");
    }
    // Execute code each time window size changes
    $(window).resize(function () {
        if ($(window).width() <= 880) {
            $("#mainNav").removeClass("fixed-top");
        } else {
            $("#mainNav").addClass("fixed-top");
        }
    });
});
