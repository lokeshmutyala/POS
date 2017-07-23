
$(document).ready(function () {
    $('#accordion').on('hidden.bs.collapse', function (e) {
    	$(".indicator").addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
    	})

    	$('#accordion .accordion-toggle').click(function (e){
    	  var chevState = $(e.target).siblings("i.indicator").toggleClass('glyphicon-chevron-down glyphicon-chevron-down');
    	  $("i.indicator").not(chevState).removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
    	});
    $("#submit").on('click', function () {
        
        submit_function();
    });
   
    document.addEventListener("deviceready", onDeviceReady_123, false);
});


function onDeviceReady_123()
{
    document.addEventListener("backbutton", onBackKeyDown, false);
var options =  { maximumAge: 3000, timeout: 8000, enableHighAccuracy: true };
    	  navigator.geolocation.getCurrentPosition(ShowPosition, ShowError, options);

}


function submit_function()
{


var store_name = $('.store_name').val();
var owner_name = $('.owner_name').val();
var phone_number = $('.phone_number').val();
var address = $('.address').val();
var pincode = $('.pincode').val();
var gst_number = $('.gst_number').val();
var pan_number = $('.pan_number').val();
if(store_name == "" || owner_name == "" || phone_number == "" || address == "" || pincode == "" || gst_number == "" || pan_number == "")
{
    alert("Please fill all details");
}
else
{
//	form = document.forms[0] //assuming only form.
//form.submit();
alert('hi');
document.getElementById("myform").submit();
	//window.location='./check-in.html';
                                 
}


}


 function ShowPosition(position) {
$('#latitude_value').html(position.coords.latitude);
$('#longitude_value').html(position.coords.longitude);
$('#accuracy_value').html(position.coords.accuracy);
			}
		 function ShowError(error) {
			 alert("Location is Disabled.Please enable your location in your device settings.");
			 cordova.plugins.diagnostic.switchToLocationSettings();
			}

function getURLParameters(paramName)
{
    var sURL = window.document.URL.toString();
    if (sURL.indexOf("?") > 0)
    {
        var arrParams = sURL.split("?");
        var arrURLParams = arrParams[1].split("&");
        var arrParamNames = new Array(arrURLParams.length);
        var arrParamValues = new Array(arrURLParams.length);

        var i = 0;
        for (i = 0; i < arrURLParams.length; i++)
        {
            var sParam = arrURLParams[i].split("=");
            arrParamNames[i] = sParam[0];
            if (sParam[1] != "")
                arrParamValues[i] = unescape(sParam[1]);
            else
                arrParamValues[i] = "No Value";
        }

        for (i = 0; i < arrURLParams.length; i++)

        {
            if (arrParamNames[i] == paramName)
            {
                //alert("Parameter:" + arrParamValues[i]);
                return arrParamValues[i];
            }
        }
        return "No Parameters Found";
    }
}


//$("#reg").click(function() {
//  alert( "Handler for .click() called." );
//  window.location='./check-in.html';
//});

function onBackKeyDown() {

    navigator.app.exitApp();

}




