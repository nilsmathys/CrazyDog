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
                changeFrontend(data);
                //reset(); // Sets Value of Variables back to 0
            },
            error: function(data) {
                console.log("failure");
                console.log(data);
            },
        });
    });
}

function changeFrontend(data) {
    $.each(data, function(index) {
        console.log(data[index].cssId);
        //$('#' + data[index].cssId).css({"background-image": "url(/img/pieces/piece1green.png)"});
        $('#' + data[index].cssId).css({"border-radius": "50%", "border": "3px solid red"});
    });
}