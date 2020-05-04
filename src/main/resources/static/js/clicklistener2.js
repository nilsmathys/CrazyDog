var chosenCard = 0;
var sessionId;
var sourceField;
var correctSourceField = 0;     // The field which will be sent to the Backend for making a move
var destinationField;
var highlightedSourceFields;
var highlightedDestinationFields;
var sourcefields;


function getPossibleSourceFields(cardvalue) {
    chosenCard = cardvalue;
    sessionId = $('#sessionId').html();
    getSourceFields();
}

function getPossibleDestinationFieldsOrMakeMove(field) {
        if(chosenCard != 0 && correctSourceField != 0 && isNotOneOfTheSourceFields(field)) {
            destinationField = field;
            makeMove();
        }
        else if(chosenCard != 0) {
            sourceField = field;
            getPossibleDestinationFields();
        }
}

// This is used for calculating all the possible destinations
function getSourceFields() {
    $(document).ready(function() {
        $.ajax({
            url : 'getsourcefields',
            type:'POST',
            data : JSON.stringify({chosenCard: chosenCard, sessionId:sessionId}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                sourcefields = data;        // Save the data of source fields into variable, because we need it later.
                correctSourceField = 0;     // Reset the variable
                removeHighlight(highlightedSourceFields);
                removeHighlight(highlightedDestinationFields);
                showSourceFields(data);
            },
            error: function(data) {
                console.log("failure");
                console.log(data);
            },
        });
    });
}

// This is used for getting possible destination fields
function getPossibleDestinationFields() {
    $(document).ready(function() {
        $.ajax({
            url : 'getdestinationfields',
            type:'POST',
            data : JSON.stringify({chosenCard: chosenCard, sessionId:sessionId, sourceField: sourceField}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                if (!$.trim(data)){             // if the data is empty, user clicked on a field with no piece
                    correctSourceField = 0;     // Reset the variable
                    console.log("There is no piece on this field");
                }
                else {
                    correctSourceField = sourceField;
                    removeHighlight(highlightedDestinationFields);
                    showDestinationFields(data);
                }
            },
            error: function(data) {
                console.log("failure");
            },
        });
    });
}

// This is used for actually making a move
function makeMove() {
    $(document).ready(function() {
        $.ajax({
            url : 'makemove',
            type:'POST',
            data : JSON.stringify({chosenCard: chosenCard, sessionId: sessionId, correctSourceField: correctSourceField, destinationField: destinationField}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                showSuccessMessage(data);
                if(data.message == "Erfolgreicher Zug") {
                    sourcefields = 0;               // Muss nicht unbedingt sein, aber sicherheitshalber reseten
                    correctSourceField = 0;         // Reset the variable
                    reset();
                    removeHighlight(highlightedSourceFields);
                    removeHighlight(highlightedDestinationFields);
                }
                else {
                    // evt. setze auch alles zurück....
                    // oder möglich auch rot und grünes highlight zu behalten
                }
            },
            error: function(data) {
                console.log("failure");
            },
        });
    });
}

// Highlight the source fields
function showSourceFields(data) {
    $.each(data, function(index) {
        $('#' + data[index].cssId).css({"border-radius": "50%", "border": "6px solid #f2ff00"});
    });
    highlightedSourceFields = data;       // Store the data in a variable, so we can remove the highlighting later.
}

// Highlight the source fields
function showDestinationFields(data) {
    $.each(data, function(index) {
        $('#' + data[index].cssId).css({"border-radius": "50%", "border": "6px solid #f700ff"});
    });
    highlightedDestinationFields = data;       // Store the data in a variable, so we can remove the highlighting later.
}

// Remove Highlight from fields
function removeHighlight(data) {
    $.each(data, function(index) {
        $('#' + data[index].cssId).css({"border-radius": "0", "border": "medium none"});
    });
}

// returns true if the Field is not one of the source Fields
function isNotOneOfTheSourceFields(field) {
    var isNotOneOfTheSourceFields = true;
    var arrayLength = sourcefields.length;
    for (var i = 0; i < arrayLength; i++) {
        if(sourcefields[i].cssId === field) {
            isNotOneOfTheSourceFields = false;
        }
    }
    return isNotOneOfTheSourceFields;
}

function reset() {
    chosenCard = 0;
}

function showSuccessMessage(data) {
    $("#gamemessage").text(data.message);
    $("#gamemessage").show().delay(5000).fadeOut();     // Show the message for 5 seconds
}