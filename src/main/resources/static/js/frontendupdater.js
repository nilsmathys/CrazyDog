$(function updateFrontend() {
    $.ajax({
        type: 'GET',
        url: 'getchanges',
        success: function(data) {
            if(data[0] != null && data[1] != null) {
                console.log("Calling /getchanges for updates.");
                $('#' + data[0].field).attr('src', '/img/pieces/' + data[0].piece);
                $('#' + data[1].field).attr('src', '/img/pieces/' + data[1].piece);
            }
            else {
                console.log("Dom was not manipulated, because there is nothing to update.");
            }
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(updateFrontend, 1000);
        }
    });
});