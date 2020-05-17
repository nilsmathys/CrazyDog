function startGame() {
    $(document).ready(function() {
        $.ajax({
            url : 'startgame',
            type:'POST',
            success : function(data) {
            },
            error: function(data) {
                console.log("Calling startgame failed");
            },
        });
    });
}

function killServer() {
    $(document).ready(function() {
        $.ajax({
            url : 'killserver',
            type:'POST',
            success : function(data) {
            },
            error: function(data) {
                console.log("Calling killServer failed");
            },
        });
    });
}