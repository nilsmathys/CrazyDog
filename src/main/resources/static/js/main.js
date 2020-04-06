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
            url : 'footest',
            type:'POST',
            data : JSON.stringify({sourcefield: sourcefield, sourcepiece:sourcepiece, destfield: destfield, destpiece:destpiece}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                console.log("success");
                console.log(data);
                console.log(data[0].field); // The source field id: E.g. : field 4
                console.log(data[0].piece); // The source piece id: E.g. : empty.png
                console.log(data[1].field); // The destination field id: E.g. : field 23
                console.log(data[1].piece); // The destination field id: E.g. : piece1blue.png
                reset(); // Sets Value of Variables back to 0
                $('#'+data[0].field).attr('src', '/img/pieces/' + data[0].piece);
                $('#'+data[1].field).attr('src', '/img/pieces/' + data[1].piece);

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