/**
 * Created by aleksandrprendota on 30.03.17.
 */

function toPurchase() {
    $("tr.tab").click(function() {
        var idRide = $(this).attr('id');
        //alert($(this).attr('id'));

        var tableData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        if (tableData.length < 1){
            return '';
        }
        var sendData = {
            id: idRide,
            train_number : tableData[0],
            station_departure : tableData[1],
            time_departure : tableData[2],
            station_arrival : tableData[3],
            time_arrival : tableData[4]
        };
        localStorage.setItem('trip', JSON.stringify(sendData));
        window.location = "/purchase";

    });

    $("tr.blank_row").click(function() {
        var idStr = $(this).attr('id');
        var allways = localStorage.getItem('smartways');
        var trip = JSON.parse(allways);
        var tableData = [];
        for (var i = 0; i < trip.length; i++){
            for (var key in trip[i]){
                if(trip[i].hasOwnProperty(key)){
                    if (key == idStr){
                        tableData = trip[i][key];
                    }
                }
            }
        }
        if (tableData.length < 1){
            return '';
        } else if (tableData.length == 1){
            var sendData = {
                id: tableData[0].id.toString(),
                train_number : tableData[0].train.id,
                station_departure : tableData[0].stationDeparture.stationName,
                time_departure : new Date(tableData[0].timeDeparture).toLocaleString(),
                station_arrival : tableData[0].stationArrival.stationName,
                time_arrival : new Date(tableData[0].timeArrival).toLocaleString()
             };
            localStorage.setItem('trip', JSON.stringify(sendData));
            window.location = "/purchase";
        } else {
            localStorage.setItem('trip', JSON.stringify(tableData));
            window.location = "/multipurchase";
        }
    });
}
