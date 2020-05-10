$(function updatePieces() {
    $.ajax({
        type: 'GET',
        url: 'getchangesPieces',
        success: function(data) {
            $.each(data, function(index) {
                $('#' + data[index].cssId).attr('src', '/img/pieces/' + ((data[index].pieceOnField == null) ? "empty.png" : data[index].pieceOnField.pictureName));
            });
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(updatePieces, 1000);
        }
    });
});

$(function updateInstructions() {
    $.ajax({
        type: 'GET',
        url: 'getchangesInstructions',
        success: function(data) {
            if(data.length > 0) {
                var innerText = "";
                var i=0;
                for(i=0;i<data.length;i++) {
                    var cssClass = "card-header instructions";
                    var background = "#fcf8e8"
                    if(i === 0) {
                        cssClass = "card-title card-header firstinstruction";
                    }
                    if((i % 2) == 0) {
                        background = "#f1f3f4";
                    }
                    innerText += "<div class='"+cssClass+"' style='background-color: "+background+";'>&#62; "+data[i]+"</div>";
                }
                document.getElementById('instructionList').innerHTML = innerText;
            }
            else {
                console.log("Dom was not manipulated, because there is nothing to update.");
            }
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(updateInstructions, 1000);
        }
    });
});

$(function updateCurrentPlayer() {
    $.ajax({
        type: 'GET',
        url: 'getchangesCurrentPlayer',
        success: function(data) {
            if(data >= 1 && data <= 4) {
                for(var i = 1;i <= 4; i++)
                {
                    $('#Player'+i).removeClass("currentplayer");
                }
                $('#Player'+data).addClass("currentplayer");
            }
            else {
                console.log("Dom was not manipulated, because there is nothing to update.");
            }
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(updateCurrentPlayer, 1000);
        }
    });
});

$(function updateCurrentDirection() {
    $.ajax({
        type: 'GET',
        url: 'getchangesCurrentDirection',
        success: function(data) {
            console.log("direction: "+data);
            if(data != "") {
                $('#direction').attr("src","/img/"+data);
            }
            else {
                console.log("Dom was not manipulated, because there is nothing to update.");
            }
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(updateCurrentDirection, 1000);
        }
    });
});

$(function updateRoundNr() {
    $.ajax({
        type: 'GET',
        url: 'getchangesRoundNr',
        success: function(data) {
            if(data > 0) {
                var innerText = "Round "+data;
                document.getElementById('roundNumber').innerHTML = innerText;
            }
            else {
                console.log("Dom was not manipulated, because there is nothing to update.");
            }
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(updateRoundNr, 1000);
        }
    });
});

$(function retrieveHand() {
    $.ajax({
        type: 'GET',
        url: 'getchangesCardsOnHand',
        success: function(fragment) {
            $("#handBlock").replaceWith(fragment);
        },
        complete: function() {
            setTimeout(retrieveHand, 10000);
        }
    });
});

jQuery(document).ready(function($){
    zoom();

    $( window ).resize(function() {
        zoom();
    });

    function zoom() {
        let htmlWidth = $('html').innerWidth();
        let bodyWidth = 900;

        let scale;
        let height;
        if (htmlWidth > bodyWidth) {
            scale = 0.5;
            height = 450;
        } else {
            scale = htmlWidth / bodyWidth / 2;
            height = 900 * scale;
        }
        $('.container-top').css('transform', 'scale(' + scale + ')');
        $('.playarea').css('height', height +'px' )
    }
});