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
        console.log(tableData);
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
}
