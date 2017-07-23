	$(document).ready(function(){
    document.addEventListener("deviceready",onDeviceReady,false);
});
		 
	 
	 		function onDeviceReady() {
//	 			alert("Device ready index");
	 			 window.location='./camera.html';
//	 			 window.location='./check-in.html';
//	 			 window.location='./registration.html';
}

function exit_app()
	 		{
	 			navigator.app.exitApp();
	 		}
   