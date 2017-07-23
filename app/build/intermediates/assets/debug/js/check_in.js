	var markers= [];
	var marker;
	var poly;
	var flightPlanCoordinates = [];
$(document).ready(function(){
	alert("Map document ready");
	
	
	 $("#submit").on('click', function () {
        alert("ALERT");
        test();
    });
    
    
	document.addEventListener("deviceready",onDeviceReady,false);  
    
	
});




    function onDeviceReady() 
    {
//    	alert("Map device ready");
    	keepscreenon.enable();
    	 var options =  { maximumAge: 3000, timeout: 8000, enableHighAccuracy: true };
    	  navigator.geolocation.getCurrentPosition(ShowPosition, ShowError, options);
    	document.addEventListener("backbutton", onBackKeyDown, false);
    	
     
     var element = document.getElementById('deviceProperties');
 	 var networkState = navigator.connection.type;
    if (networkState == Connection.NONE)
    {
    	window.location='./first_screen.html';
        return true;
    }
    else
    {
    	
	
  return false;
}	   
        navigator.geolocation.getCurrentPosition(function(position)
	{
    	// just to show how to access latitute and longitude
  
	},
	function(error)
	{
	//  error getting GPS coordinates

		navigator.app.exitApp();      
	}, 
	{ 
		enableHighAccuracy: true, maximumAge: 6000, timeout: 8000 });
        return false;
	}
	


		
	
		
		
		
		var map;
		function initialize() 
		{
  			var mapOptions = {
    		zoom: 16
  		};
  		map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);

  		// Try HTML5 geolocation
  		if(navigator.geolocation) 
  		{
//  			alert("Navigator If");
    		navigator.geolocation.getCurrentPosition(function(position) {
      		var pos = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
//      		alert("Initial Position:"+pos);
			marker = new google.maps.Marker({
				map: map,
	        	position: pos,
	        	icon: 'images/marker.png'
	        });
			$("#marker_icon").val(1);
//			markers.push(marker);
//      alert("Markers array insert");
			
		
		
		        
		        
latitude = position.coords.latitude;
longitude = position.coords.longitude;
//alert(latitude+'--'+longitude);
$('input#longitude').val(longitude);

      map.setCenter(pos);
      return pos;
    }, function() 
    {
      handleNoGeolocation(true);
    });
  } else 
  {
    // Browser doesn't support Geolocation
    handleNoGeolocation(false);
  }
}

		
function handleNoGeolocation(errorFlag) 
{
  if (errorFlag) 
  {
    var content = 'Error: The Geolocation service failed.';
  } 
  else 
  {
    var content = 'Error: Your browser doesn\'t support geolocation.';
  }

  var options = {
    map: map,
    position: new google.maps.LatLng(60, 105),
    content: content
  };

  var infowindow = new google.maps.InfoWindow(options);
  map.setCenter(options.position);
}

google.maps.event.addDomListener(window, 'load', initialize);


window.onload = function(){date()}, setInterval(function(){date()}, 6000);


	
function removeMarkers(){
//	alert("Remove markers function");
//	alert("Markers Length: "+markers.length);
    for(i=0; i<markers.length; i++){
        markers[i].setMap(null);
    }
}

	
function onBackKeyDown(e) {
	keepscreenon.disable();
	   navigator.app.exitApp();
	 
	} 

	/*
	 function onSuccess(position) {

//	      removeMarkers();
		
	  
	  		var latitude_coordinates = position.coords.latitude;
	  		latitude_coordinates = latitude_coordinates.toFixed(4);
	  		var longitude_coordinates = position.coords.longitude;
	  		longitude_coordinates = longitude_coordinates.toFixed(4);
      		var pos = new google.maps.LatLng(latitude_coordinates,longitude_coordinates);
//      		var pos = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
      		alert("Changed Position:"+pos);
			
			flightPlanCoordinates.push(pos);
//			alert("flightPlanCoordinates:"+flightPlanCoordinates);
				var flightPath  = new google.maps.Polyline({
					path: flightPlanCoordinates,
					geodesic: true,
					strokeColor: '#0000FF',
					strokeOpacity: 1.0,
					strokeWeight: 3
		        });
		       // poly.setMap(pos); 
			
			flightPath.setMap(map);
			
      		 if ($("#marker_icon").val() == 1)
             {

      			marker.setPosition(pos);
             }
      		 else{

			marker = new google.maps.Marker({
				map: map,
	        	position: pos,
	        	icon: 'images/marker.png'
        	});
      		 }
//			markers.push(marker);
//			alert("Marker array value: "+markers);
latitude = position.coords.latitude;
longitude = position.coords.longitude;
$('input#latitude').val(latitude);
$('input#longitude').val(longitude);

      map.setCenter(pos);
      return pos;
    
	    }
*/
	    // onError Callback receives a PositionError object
	    //
	    function onError(error) {
//	        alert("Watch position error");
	    }

	
window.setInterval( function () {
        setGeolocation();
    }, 
    15000 //check every 15 seconds
);	
		setGeolocation();
		
function setGeolocation() {
	
    var geolocation = window.navigator.geolocation.watchPosition( 
        function ( position ) {
//            		var latitude_coordinates = position.coords.latitude;
//	  		latitude_coordinates = latitude_coordinates.toFixed(5);
//	  		var longitude_coordinates = position.coords.longitude;
//	  		longitude_coordinates = longitude_coordinates.toFixed(9);
//      		var pos = new google.maps.LatLng(latitude_coordinates,longitude_coordinates);
      		var pos = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
//      		alert("Changed:"+pos);
      	
      		flightPlanCoordinates.push(pos);
			

			
//			alert("flightPlanCoordinates:"+flightPlanCoordinates);
				var flightPath  = new google.maps.Polyline({
					path: flightPlanCoordinates,
					geodesic: true,
					strokeColor: '#0000FF',
					strokeOpacity: 1.0,
					strokeWeight: 3
		        });
		       // poly.setMap(pos); 
			
			flightPath.setMap(map);
			
      		 if ($("#marker_icon").val() == 1)
             {

      			marker.setPosition(pos);
             }
      		 else{

			marker = new google.maps.Marker({
				map: map,
	        	position: pos,
	        	icon: 'images/marker.png'
        	});
      		 }
//			markers.push(marker);
//			alert("Marker array value: "+markers);
latitude = position.coords.latitude;
longitude = position.coords.longitude;
$('input#latitude').val(latitude);
$('input#longitude').val(longitude);

      map.setCenter(pos);
      return pos;
    
        },
        function () { /*error*/ }, {
            maximumAge: 250, 
            enableHighAccuracy: true
        } 
    );

    window.setTimeout( function () {
            window.navigator.geolocation.clearWatch( geolocation ) 
        }, 
        5000 //stop checking after 5 seconds
    );
};
	   



function test(){
alert("Test Function");
  var question1 = document.getElementsByName("question1");
  var check1 = 0;
  for(i=0;i<question1.length;i++){
    if(question1[i].checked){
      check1++;
      break;
    }
  }
 var question2 = document.getElementsByName("question2");
  var check2 = 0;
  for(i=0;i<question2.length;i++){
    if(question2[i].checked){
      check2++;
      break;
    }
  }
 var question3 = document.getElementsByName("question3");
  var check3 = 0;
  for(i=0;i<question3.length;i++){
    if(question3[i].checked){
      check3++;
      break;
    }
  } 
  
var question4 = document.getElementsByName("question4");
  var check4 = 0;
  for(i=0;i<question4.length;i++){
    if(question4[i].checked){
      check4++;
      break;
    }
  } 

var question5 = document.getElementsByName("question5");
  var check5 = 0;
  for(i=0;i<question5.length;i++){
    if(question5[i].checked){
      check5++;
      break;
    }
  }  
  
var question6 = document.getElementsByName("question6");
  var check6 = 0;
  for(i=0;i<question6.length;i++){
    if(question6[i].checked){
      check6++;
      break;
    }
  }  
  
 
  if(check1 && check2 && check3 && check4 && check5 && check6){
  }else{
    alert("you must select ratings for both company and category");
    return false;
  }
}













		 function ShowPosition(position) {
//			 alert("Location is enable");
 //var options =  { maximumAge: 6000, timeout: 8000, enableHighAccuracy: true };
	   // var watchID = navigator.geolocation.watchPosition(onSuccess, onError, options);
			return true;
			    
			}
		 function ShowError(error) {
			 alert("Location is Disabled.Please enable your location in your device settings.");
			 cordova.plugins.diagnostic.switchToLocationSettings();
			}
