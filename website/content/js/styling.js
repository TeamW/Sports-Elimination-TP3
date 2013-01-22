var $, window, document, visible;

window.onresize = function () {
    'use strict';
    var windowWidth, articleWidth = $("article").width();
    // Most common and preferred method of obtaining browser width
    if (window.innerWidth) {
        windowWidth = window.innerWidth;
    // Alternate one if above isn't defined in browser.
    } else if (document.compatMode === 'CSS1Compat' && document.documentElement && document.documentElement.offsetWidth) {
        windowWidth = document.documentElement.offsetWidth;
    // Alternate two if above isn't defined in browser.
    } else if (document.body && document.body.offsetWidth) {
        windowWidth = document.body.offsetWidth;
    // Cannot tell width so set width so it makes no stylistic changes.
    } else {
        windowWidth = 700;
    }
    // 700px width is required to display website normally, changes must be done for widths less than this.
    if (windowWidth < 700) {
        // Don't display .imageRight images (for example picture of me in the homepage).
        $(".imageRight").css("display", "none");
        // Ensure images are no wider than available width, change height to keep image in proportion.
        $("img").css("width", articleWidth);
        $("img").css("height", "auto");
        // Make footer text smaller
        $("footer").css("font-size", "0.8em");
        // More drastic changes are required for even smaller (mobile) displays
        if (windowWidth < 500) {
            // Navigation above article rather than to the left
            $("nav").css("float", "none");
            // Required change for header to display properly
            $("header").css("height", "2.75em");
            // Ensuring navigation waste no vertical space.
            $("nav, article, section").css("min-height", "0em");
        } else {
            // Restore settings changed above to defaults.
            $("nav").css("float", "left");
            $("header").css("height", "4em");
            $("nav, article, section").css("min-height", "20em");
        }
    } else {
        // Restore settings changed above to defaults.
        $(".imageRight").css("display", "inline");
        $("img").css("width", "auto");
        $("img").css("height", "auto");
        $("footer").css("font-size", "1em");
    }
};

window.onload = function () {
    'use strict';
    window.onresize();
    // Setup accordion affect. All blocks collapsed initially, heights are different for each section,
    // (appropriate for content) and opened block can be collapsed by clicking on it.
    $("#accordion").accordion({active: false, autoHeight: false, collapsible: true});
};

function showHideDiv(visible) {
    'use strict';
    if (visible === 1) {
        $("#hidden").css("display", "block");
    } else {
        $("#hidden").css("display", "none");
    }
}
