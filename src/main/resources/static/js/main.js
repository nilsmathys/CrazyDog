$(document).ready(function() {
    $("#field7").click(function() {
        $.ajax({
            url : 'gametest',
            success : function(data) {
                $("field7").html(data);
            }
        })
    });
});