/**
 * Created by aleksandrprendota on 12.05.17.
 */
function showStatistics() {
    $("#statistics").attr("class","active");
    $("#supportbutton").removeAttr("class");
    $("#adminbutton").removeAttr("class");

    $("#dialog").empty();
    $("#logo").empty();
    $("#chartdiv").remove();
    $("#chartdiv1").remove();
    $("#chartdiv2").remove();
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
    statisticBoughtByStationDeparture();
    statisticBoughtByStationArrival();
    moneyStatistics();
}

function statisticBoughtByStationDeparture() {
    $.ajax({
        type: "GET",
        url : '/statistics/bought/departure',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#source").append(
                '<div class="container">' +
                '<div class="panel-heading">' +
                '<div class="panel-title text-center">' +
                '<h3 class="title">Bought tickets by station departure</h3>' +
                '</div>' +
                '</div></div>'
            );
            $('#source').append('<div class="center" id="chartdiv"></div>');
            $('#chartdiv').css('wight','300px');
            $('#chartdiv').css('height','300px');
            var chart = AmCharts.makeChart( "chartdiv", {
                "type": "pie",
                "theme": "chalk",
                "dataProvider": response,
                "valueField": "Bought",
                "titleField": "City",
                "startEffect": "elastic",
                "startDuration": 5,
                "labelRadius": 15,
                "innerRadius": "50%",
                "depth3D": 10,
                "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                "angle": 35
            } );

        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });
}

function statisticBoughtByStationArrival() {
    $.ajax({
        type: "GET",
        url : '/statistics/bought/arrival',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#source").append(
                '<div class="container">' +
                '<div class="panel-heading">' +
                '<div class="panel-title text-center">' +
                '<h3 class="title">Bought tickets by station arrival</h3>' +
                '</div>' +
                '</div></div>'
            );
            $('#source').append('<div class="center" id="chartdiv2"></div>');
            $('#chartdiv2').css('wight','300px');
            $('#chartdiv2').css('height','300px');
            var chart = AmCharts.makeChart( "chartdiv2", {
                "type": "pie",
                "theme": "chalk",
                "dataProvider": response,
                "valueField": "Bought",
                "titleField": "City",
                "startEffect": "elastic",
                "startDuration": 5,
                "labelRadius": 15,
                "innerRadius": "50%",
                "depth3D": 10,
                "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                "angle": 35
            } );

        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });
}

function moneyStatistics() {
    $.ajax({
        type: "GET",
        url : '/statistics/cash',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#source").append(
                '<div class="container">' +
                '<div class="panel-heading">' +
                '<div class="panel-title text-center">' +
                '<h3 class="title">Money Statistics</h3>' +
                '</div>' +
                '</div></div>'
            );
            $('#source').append('<div class="center" id="chartdiv1"></div>');
            var chart = AmCharts.makeChart("chartdiv1", {
                "type": "serial",
                "theme": "chalk",
                "marginRight": 40,
                "marginLeft": 40,
                "autoMarginOffset": 20,
                "mouseWheelZoomEnabled":true,
                "dataDateFormat": "YYYY-MM-DD",
                "valueAxes": [{
                    "id": "v1",
                    "axisAlpha": 0,
                    "position": "left",
                    "ignoreAxisWidth":true
                }],
                "balloon": {
                    "borderThickness": 1,
                    "shadowAlpha": 0
                },
                "graphs": [{
                    "id": "g1",
                    "balloon":{
                        "drop":true,
                        "adjustBorderColor":false,
                        "color":"#ffffff"
                    },
                    "bullet": "round",
                    "bulletBorderAlpha": 1,
                    "bulletColor": "#FFFFFF",
                    "bulletSize": 5,
                    "hideBulletsCount": 50,
                    "lineThickness": 2,
                    "title": "red line",
                    "useLineColorForBulletBorder": true,
                    "valueField": "value",
                    "balloonText": "<span style='font-size:18px;'>[[value]]</span>"
                }],
                "chartScrollbar": {
                    "graph": "g1",
                    "oppositeAxis":false,
                    "offset":30,
                    "scrollbarHeight": 80,
                    "backgroundAlpha": 0,
                    "selectedBackgroundAlpha": 0.1,
                    "selectedBackgroundColor": "#888888",
                    "graphFillAlpha": 0,
                    "graphLineAlpha": 0.5,
                    "selectedGraphFillAlpha": 0,
                    "selectedGraphLineAlpha": 1,
                    "autoGridCount":true,
                    "color":"#AAAAAA"
                },
                "chartCursor": {
                    "pan": true,
                    "valueLineEnabled": true,
                    "valueLineBalloonEnabled": true,
                    "cursorAlpha":1,
                    "cursorColor":"#258cbb",
                    "limitToGraph":"g1",
                    "valueLineAlpha":0.2,
                    "valueZoomable":true
                },
                "valueScrollbar":{
                    "oppositeAxis":false,
                    "offset":50,
                    "scrollbarHeight":10
                },
                "categoryField": "date",
                "categoryAxis": {
                    "parseDates": true,
                    "dashLength": 1,
                    "minorGridEnabled": true
                },
                "dataProvider": response
            });

            chart.addListener("rendered", zoomChart);

            zoomChart();

            function zoomChart() {
                chart.zoomToIndexes(chart.dataProvider.length - 40, chart.dataProvider.length - 1);
            }

        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });
}