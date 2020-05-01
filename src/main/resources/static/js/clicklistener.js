var sourcefield = 0;
var sourcepiece = 0;

var destfield = 0;
var destpiece = 0;

var chosenCardId = 0;
var chosenCardValue = 0;

var selectedAction = 0;

function main(field, piecefullpath) {
    piece = piecefullpath.replace(/^.*[\\\/]/, '');     // This extracts the image name out of the image src

    if (chosenCardValue == 2 || chosenCardValue == 5 || chosenCardValue == 6 || chosenCardValue == 8 || chosenCardValue == 9 || chosenCardValue == 10) {
        var fieldId = (parseInt(field.substring(5)) + parseInt(chosenCardValue)) % 64 !== 0?
            (parseInt(field.substring(5)) + parseInt(chosenCardValue)) % 64 : 64;
        console.log(fieldId);
        var fieldToGoTo = 'field' + fieldId;
        sourcefield = field;
        sourcepiece = piece;
        destfield = fieldToGoTo;
        destpiece = 'empty.png';
        send();
        removeCardFromHand();
    } else {
        if(sourcefield != 0) {
            destfield = field;
            destpiece = piece;
            send();
            removeCardFromHand();
        }
        else {
            sourcefield = field;
            sourcepiece = piece;
        }
    }
}

function send() {
    $(document).ready(function() {
        $.ajax({
            url : 'listenclicks',
            type:'POST',
            data : JSON.stringify({sourcefield: sourcefield, sourcepiece:sourcepiece, destfield: destfield, destpiece:destpiece, selectedAction:selectedAction}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                console.log('Source field: ' + data[0].field);
                console.log('Source piece: ' + data[0].piece);
                console.log('Destination field: ' + data[1].field);
                console.log('Destination piece: ' + data[1].piece);
                reset(); // Sets Value of Variables back to 0
                changeFrontend(data);
            },
            error: function(data) {
                console.log("failure");
                console.log(data);
            },
        });
    });
}

function reset() {
    sourcefield = 0;
    sourcepiece = 0;
    destfield = 0;
    destpiece = 0;
    chosenCardId = 0;
    chosenCardValue = 0;
}

function changeFrontend(data) {
    $('#'+data[0].field).attr('src', '/img/pieces/' + data[0].piece);
    $('#'+data[1].field).attr('src', '/img/pieces/' + data[1].piece);
}

function chooseCard(el) {
    let id = $(el).data('card_id');
    let value = $(el).data('card_value');
    let name = $(el).data('card_name');

    //3: 3 fahren oder Richtungsänderung
    //oneElven : 1 oder 11 fahren
    //questionmark: irgendeine karte auswählen->


    //7: mehrere figuren
    //pieeExchange: Figurenstandort tauschen
    switch(value) {
        case 3:
            $("#card3").modal();
            break;
        case 4:
            $("#card4").modal();
            playSpecialCard(0);
            break;
        case 7:
            playSpecialCard(0);
            break;
        case 0:

            if(name === "oneEleven") {
                $("#card11").modal();
            } else if(name === "questionmark") {
                $("#questionmark").modal();
            } else if(name === "pieceExchange") {
                playSpecialCard(0);
            }
            break;
        default:
            playNormalCard();
            break;

    }
    function playSpecialCard(selAction) {
        selectedAction = selAction;
    }

    function playNormalCard() {

    }



   // $(el).data('card_id', 1);
   //
   //  chosenCardId = id;
   //  if (value === 3) {}
   //  else if (value === 4) {}
   //  else if (value === 7) {}
   //  else if (value === 11) {
   //     chosenCardValue = value;
   //     //TODO: choose an action
   //  }
   //  else if (value === 12) {}
   //  else if (value === 13) {}
   //  else if (value === 14) {}
   //  else if (value === 15) {}
   //  else {
   //     chosenCardValue = value;
   //  }
}



function removeCardFromHand() {
    $('img[id='+ chosenCardId + ']').remove();
}

//Set the value of the hidden input field
$("img[data-card_id]").click(function(e){
    $("input[name='selectedCardId']").val($(this).data('card_id'));
});