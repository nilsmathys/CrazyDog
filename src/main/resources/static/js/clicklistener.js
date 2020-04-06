var sourcefield = 0;
var sourcepiece = 0;

var destfield = 0;
var destpiece = 0;

function main(field, piecefullpath) {
    piece = piecefullpath.replace(/^.*[\\\/]/, '');     // This extracts the image name out of the image src

    if(sourcefield != 0) {
        destfield = field;
        destpiece = piece;
        send();
    }
    else {
        sourcefield = field;
        sourcepiece = piece;
    }
}

function send() {
    $(document).ready(function() {
        $.ajax({
            url : 'listenclicks',
            type:'POST',
            data : JSON.stringify({sourcefield: sourcefield, sourcepiece:sourcepiece, destfield: destfield, destpiece:destpiece}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                console.log('Source field' + data[0].field);
                console.log('Source piece' + data[0].piece);
                console.log('Destination field' + data[1].field);
                console.log('Destination piece' + data[1].piece);
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
}

function changeFrontend(data) {
    $('#'+data[0].field).attr('src', '/img/pieces/' + data[0].piece);
    $('#'+data[1].field).attr('src', '/img/pieces/' + data[1].piece);
}