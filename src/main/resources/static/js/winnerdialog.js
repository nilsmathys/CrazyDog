$(function displayPopUp() {
    $.ajax({
        type: 'GET',
        url: 'getWinningTeam',
        success: function(data) {
            if(data != "") {
                var innerText = data.player1.username + " & " + data.player2.username + " haben gewonnen!";
                $('#winningDialog').attr("style","display:block");
                document.getElementById('winner').innerHTML = innerText;
            }
        },
        complete: function() {
            // Schedule the next request when the current one's complete
            setTimeout(displayPopUp, 5000);
        }
    });
});

$(function(){
    $('#close-dialog').click(function() {
        window.close();
        window.location.href = "/index";
    });
});