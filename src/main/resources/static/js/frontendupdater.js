$(function updateFrontend() {
    $.ajax({
        type: 'GET',
        url: 'getchanges',
        success: function(data) {
            if(data[0] != null && data[1] != null) {
                //console.log("Calling /getchanges for updates.");
                $('#' + data[0].field).attr('src', '/img/pieces/' + data[0].piece);
                $('#' + data[1].field).attr('src', '/img/pieces/' + data[1].piece);
            }
            else {
                console.log("Dom was not manipulated, because there is nothing to update.");
            }
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(updateFrontend, 1000);
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
                    var cssClass = "card-header";
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