$(document).ready(function() {
    $("#dot1").click(function() {
        $.ajax({
            url : 'test',
            success : function(data) {
                $("dot1").html(data);
            }
        })
    });
});