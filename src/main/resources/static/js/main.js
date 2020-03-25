$(document).ready(function() {
    $("#field7").click(function() {
        $.ajax({
            url : 'test',
            success : function(data) {
                $("field7").html(data);
            }
        })
    });
});