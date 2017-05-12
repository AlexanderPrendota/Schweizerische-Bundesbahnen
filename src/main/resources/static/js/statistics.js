/**
 * Created by aleksandrprendota on 12.05.17.
 */
function showStatistics() {
    $("#dialog").empty();
    $("#logo").empty();
    $("#chartdiv").remove();
    $("#fon").remove();
    $("#source").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Statistics</h1>' +
        '</div>' +
        '</div></div>'
    );
    statisticBoughtByStation();
}

function statisticBoughtByStation() {
    $.ajax({
        type: "GET",
        url : '/statistics/bought',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#source").append(
                '<div class="container">' +
                '<div class="panel-heading">' +
                '<div class="panel-title text-center">' +
                '<h3 class="title">Bought tickets by cities</h3>' +
                '</div>' +
                '</div></div>'
            );
            $('#source').append('<div class="center" id="chartdiv"></div>');
            $('#chartdiv').css('background-color','#D3D3D3');
            $('#chartdiv').css('wight','300px');
            $('#chartdiv').css('height','300px');
            var chart = AmCharts.makeChart( "chartdiv", {
                "type": "pie",
                "theme": "light",
                "dataProvider": response,
                "valueField": "Bought",
                "titleField": "City",
                "startEffect": "elastic",
                "startDuration": 2,
                "labelRadius": 15,
                "innerRadius": "50%",
                "depth3D": 10,
                "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                "angle": 20
            } );


        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });
}