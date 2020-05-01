var chosenCard;
var sessionId;

function calculatePossibleMoves(cardvalue) {
    chosenCard = cardvalue;
    sessionId = $('#sessionId').html();
    send();
}

function send() {
    $(document).ready(function() {
        $.ajax({
            url : 'calculatemoves',
            type:'POST',
            data : JSON.stringify({chosenCard: chosenCard, sessionId:sessionId}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                //console.log('Source field: ' + data[0].field);
                //console.log('Source piece: ' + data[0].piece);
                //console.log('Destination field: ' + data[1].field);
                //console.log('Destination piece: ' + data[1].piece);
                //reset(); // Sets Value of Variables back to 0
                //changeFrontend(data);
            },
            error: function(data) {
                console.log("failure");
                console.log(data);
            },
        });
    });
}