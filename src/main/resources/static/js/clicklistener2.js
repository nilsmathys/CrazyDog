var chosenCard = 0;
var sessionId;
var highlightedFields;
var destinationField;

function calculatePossibleMoves(cardvalue) {
    chosenCard = cardvalue;
    sessionId = $('#sessionId').html();
    sendCardAndId();
}

function makeMove(field) {
        if(chosenCard != 0) {
            destinationField = field;
            sendCardAndIdAndDestination();
        }
}

// This is used for calculating all the possible destinations
function sendCardAndId() {
    $(document).ready(function() {
        $.ajax({
            url : 'calculatemoves',
            type:'POST',
            data : JSON.stringify({chosenCard: chosenCard, sessionId:sessionId, destinationField: destinationField}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                removeHighlight(highlightedFields);
                changeFrontend(data);
            },
            error: function(data) {
                console.log("failure");
                console.log(data);
            },
        });
    });
}

// This is used for actually making the move to a calculated destination
function sendCardAndIdAndDestination() {
    $(document).ready(function() {
        $.ajax({
            url : 'makemove',
            type:'POST',
            data : JSON.stringify({chosenCard: chosenCard, sessionId:sessionId, destinationField: destinationField, destinationField: destinationField}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                showSuccessMessage(data);
                if(data.message == "Erfolgreicher Zug") {
                    removeHighlight(highlightedFields);
                    reset(); // Clear variable chosenCard
                }
            },
            error: function(data) {
                console.log("failure");
                console.log(data);
            },
        });
    });
}


// Highlight the destination fields
function changeFrontend(data) {
    $.each(data, function(index) {
        console.log(data[index].cssId);
        $('#' + data[index].cssId).css({"border-radius": "50%", "border": "3px solid red"});
    });
    highlightedFields = data;       // Store the data in a variable, so we can remove the highlighting later.
}

// Remove Highlight from old destination fields
function removeHighlight(data) {
    $.each(data, function(index) {
        console.log(data[index].cssId);
        $('#' + data[index].cssId).css({"border-radius": "0", "border": "medium none"});
    });
}

function reset() {
    chosenCard = 0;
}

function showSuccessMessage(data) {
    $("#gamemessage").text(data.message);
    $("#gamemessage").show().delay(5000).fadeOut();     // Show the message for 5 seconds
}