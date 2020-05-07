var chosenCard = 0;
var sessionId;
var sourceField;
var correctSourceField = 0;     // The field which will be sent to the Backend for making a move
var destinationField;
var highlightedSourceFields;
var highlightedDestinationFields;
var sourcefields;

var exchangeCards = false;

function getPossibleSourceFields(cardvalue, cardId) {
    $("input[name='selectedCardId']").val(cardId);
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
                    sourcefields = 0;               // Muss nicht unbedingt sein, aber sicherheitshalber zurücksetzen
                    correctSourceField = 0;         // Reset the variable
                    reset();
                    removeHighlight(highlightedSourceFields);
                    removeHighlight(highlightedDestinationFields);
                    // updateGameFields(); TODO: Check if we want to update the gamefields via this way or via getchanges API
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
        $('#' + data[index].cssId).css({"border-radius": "50%", "border": "6px solid #f700ff"});		// Solution of Remo
        // $('#' + data[index].cssId).addClass("highlight-field");										// Solution of Riccardo. TODO: Test better solution
    });
    highlightedDestinationFields = data;       // Store the data in a variable, so we can remove the highlighting later.
}

// Remove Highlight from fields
function removeHighlight(data) {
    $.each(data, function(index) {
        $('#' + data[index].cssId).css({"border-radius": "0", "border": "medium none"});			// Solution of Remo
        //$('#' + data[index].cssId).removeClass("highlight-field");									// Solution of Riccardo. TODO: Test better solution
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

function updateGameFields() {
    $.ajax({
        type: 'GET',
        url: 'getchangesAllGamefields',
        success: function (data) {
            if (data.length > 0) {
                var innerText = "";
                var i = 0;
                for (i = 0; i < data.length; i++) {
                    var src = "/img/pieces/empty.png";
                    var background = "#fcf8e8"
                    console.log(data.pieceOnField);
                    if (data[i].pieceOnField != null) {
                        src = "/img/pieces/" + data[i].pieceOnField.pictureName;
                        console.log(data[i].pieceOnField.pictureName);
                    }
                    innerText += "<img src='" + src + "' class='field' id='" + data[i].cssId + "' alt='" + data[i].cssId + "' onclick='makeMove(this.id)'/>";
                }
                document.getElementById('gameboard').innerHTML = innerText;
            } else {
                console.log("Dom was not manipulated, because there is nothing to update.");
            }

        },
        complete: function () {
            // Schedule the next request when the current one's complete
            //setTimeout(updateFrontend, 1000);
        }
    });
}

//Set the countdown for selecting a card to exchange
var timeleft = 30;
var countdownTimer = setInterval(function(){
    if (document.getElementById("countdown") != null) {
        if (timeleft <= 0) {
            clearInterval(countdownTimer);
            document.getElementById("countdown").innerHTML = "";
            document.getElementById("exchange-button").disabled = true;
            exchangeCards = true;
        } else {
            document.getElementById("countdown").innerHTML = "Wähle eine Karte in " + timeleft + " Sekunden";
        }
    }
    timeleft -= 1;
}, 1000);