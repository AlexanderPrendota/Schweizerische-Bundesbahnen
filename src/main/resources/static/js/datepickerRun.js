/**
 * Created by aleksandrprendota on 26.03.17.
 */

function goDatePicker(){
    $( function() {
        $("#datepicker").datepicker({
            dateFormat: 'yy-mm-dd'
        });
        //$("#datepicker").datepicker();
    });
}

function goesDatePicker(){
    $( function() {
        $(".datepicker").datepicker({
            dateFormat: 'yy-mm-dd'

        });
    });
}

function goTimePicker() {
    $( function() {
        $(".timepicker").timepicker({
            timeFormat: 'HH:mm'
        });
    });

}
