var sourceid = 0;
var destinationid = 0;

function main(id) {
    if(sourceid != 0 && destinationid != 0) {
        send();
    }
    else {
        if(sourceid == 0) {
            sourceid = id;
        }
        else {
            destinationid = id;
        }
    }
    console.log(sourceid);
    console.log(destinationid);
}

function send() {
    $(document).ready(function() {
        $.ajax({
            url : 'footest',
            type:'POST',
            data : JSON.stringify({sourceid: sourceid, destinationid: destinationid}),
            contentType : 'application/json; charset=utf-8',
            dataType:'json',
            success : function(data) {
                console.log("success");
                console.log(data.pictureId);
                $("#field4").attr('src', '/img/pieces/' + data.pictureId + '.png'); // Change Field 4
                sourceid = 0;
                destinationid = 0;
            },
            error: function(data) {
                console.log("failure");
                console.log(data);
            },
        });
    });
}