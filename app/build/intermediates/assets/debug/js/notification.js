
$(document).ready(function () {

logout();
var current_time = Date();
$("#quantity").hide();
$("#next_schedule").hide();
$("#medicine_note").hide();
$("#late_label").hide();
$("#comments").hide();


//document.getElementById("current_time").innerHTML = current_time;
  document.addEventListener("deviceready", onDeviceReady, false);
    
});


function onDeviceReady()
{

var id=getURLParameters("id");
var title=getURLParameters("title");
var quantity=getURLParameters("quantity");
var scheduled_time=getURLParameters("scheduled_time");
	
 document.addEventListener("backbutton", onBackKeyDown, false);
  

    var networkState = navigator.connection.type;
    if (networkState == Connection.NONE)
    {

        $("#quantity").show();
				$("#event_title").html(title);
				$("#med_quantity").html(quantity);
				
				
				var scheduled_time = data.start;
				var time_calculation = moment(scheduled_time).format("hh:mm A");
				$("#scheduled_time").html(time_calculation);
				
				
				var scheduled_time_format = html(scheduled_time);
				var now_time_format = moment(now_time);
				var ms = moment(now_time_format,"DD/MM/YYYY HH:mm").diff(moment(scheduled_time_format,"DD/MM/YYYY HH:mm"));
				var d = moment.duration(ms);
				var time_difference = Math.floor(d.asHours()) + moment.utc(ms).format(":mm A");

    }
    
		

else
{

		var	url ='http://183.82.97.23:9090/notification_details_get/'+id+'?_format=json';
			//alert(url);	
	
            $.ajax({
              url:url,
              type: "get",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
              
			  error: function (jqXHR, textStatus, errorThrown) {
			   
              },
              success: function (data) {
              var title = data.title;
              if(data.event_paramaters.med_name)
              {
				$("#quantity").show();
				$("#next_schedule").show();
				$("#event_title").html(data.event_paramaters.med_name);
				$("#med_quantity").html(data.event_paramaters.med_quantity);
				
				
				var scheduled_time = data.start;
				var time_calculation = moment(scheduled_time).format("hh:mm A");
				$("#scheduled_time").html(time_calculation);
				
				var scheduled_time = data.start;
				var now_time = new Date();
				var scheduled_time_format = moment(scheduled_time);
				var now_time_format = moment(now_time);
				var ms = moment(now_time_format,"DD/MM/YYYY HH:mm").diff(moment(scheduled_time_format,"DD/MM/YYYY HH:mm"));
				var d = moment.duration(ms);
				var time_difference = Math.floor(d.asHours()) + moment.utc(ms).format(":mm A");
				
			  var next_schedule_time = data.next_schedule;
             
             var next_schedule_date = moment(next_schedule_time).format("YYYY-MM-DD");
             var next_schedule_moment_date = moment(next_schedule_time).format("DD-MM-YYYY");
             
             var now_date = moment(now_time_format).format("YYYY-MM-DD");
             var next_schedule_moment_time = moment(next_schedule_time).format("hh:mm A");
             var next_schedule_date_time = next_schedule_moment_date+' '+next_schedule_moment_time;
            // alert("Date and Time:"+next_schedule_date_time);
            
             
              var time_cal = moment(next_schedule_time, "HH:mm").format("HH:mm");
              var recur_time = moment(next_schedule_time).format("hh:mm A");
              
             if(next_schedule_date == now_date)
             {
            
             	$("#next_schedule_time").html(recur_time);
             }
             else
             {
            
             	$("#next_schedule_time").html(next_schedule_date_time);
             }
            
            
			
			
			
			
			if(time_difference >1)
			{
				$("#late_label").show();
				$("#delayed_by").html(time_difference);
			}	
              
              }
              else
              {
              	$("#event_title").html(data.title);
              	$("#comments").show();
              	$("#next_schedule").show();
              	$("#comments").html(data.event_paramaters.event_comments);
              	var scheduled_time = data.start;
            	var time_calculation = moment(scheduled_time).format("hh:mm A");
				$("#scheduled_time").html(time_calculation);
              
            
            	var scheduled_time = data.start;
				var now_time = new Date();
				var scheduled_time_format = moment(scheduled_time);
				var now_time_format = moment(now_time);
				var ms = moment(now_time_format,"DD/MM/YYYY HH:mm").diff(moment(scheduled_time_format,"DD/MM/YYYY HH:mm"));
				var d = moment.duration(ms);
				var time_difference = Math.floor(d.asHours()) + moment.utc(ms).format(":mm A");
			
				
			if(time_difference >1)
			{
				$("#late_label").show();
				$("#delayed_by").html(time_difference);
			}	
			}
				  
				  
				  
	}
	});
 
}
 
 
 
 
 
 
 
 
 
 
 
 
    

}



function taken()
{


var id=getURLParameters("id");

			var	url ='http://183.82.97.23:9090/taken_notification_save/'+id+'?_format=json';
			//alert(url);	
	
            $.ajax({
              url:url,
              type: "get",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
              
			  error: function (jqXHR, textStatus, errorThrown) {
			   
              },
              success: function (data) {
            	 navigator.app.exitApp();
              }
              });
    

}


function skip()
{
	
	
var id=getURLParameters("id");
var	url ='http://183.82.97.23:9090/skip_notification_save/'+id+'?_format=json';
			//alert(url);	
	
            $.ajax({
              url:url,
              type: "get",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
              
			  error: function (jqXHR, textStatus, errorThrown) {
			   
              },
              success: function (data) {
              navigator.app.exitApp();
              }
              });
			  

}


function snooze()
{
	
	
var id = getURLParameters("id");
var title = getURLParameters("title");


var	url ='http://183.82.97.23:9090/snooze_notification_save/'+id+'?_format=json';
			//alert(url);	
	
            $.ajax({
              url:url,
              type: "get",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
              
			  error: function (jqXHR, textStatus, errorThrown) {
			   
              },
              success: function (data) {
             navigator.app.exitApp();
			 
			  cordova.plugins.notification.local.hasPermission(function(granted){
          	  if(granted == true)
          	  {
				  
          	  
			   
			  // var now = new Date().getTime(),
    		//	_5_sec_from_now = new Date(now + 5*1000);


		var snooze_time = new Date();
		snooze_time.setMinutes(snooze_time.getMinutes() + 10);

cordova.plugins.notification.local.schedule({
 	id: id,
    text: "Snooze Notification",
    at: snooze_time,
     title: title,
     icon: "res://notification_icon.png",
    sound:"res://pills.mp3"
});
			   
			   
			   
          	  }
          	  else
          	  {
          	    cordova.plugins.notification.local.registerPermission(function(granted) {
          	        if(granted == true)
          	        {
								
								
							var snooze_time = new Date();
		snooze_time.setMinutes(snooze_time.getMinutes() + 10);

cordova.plugins.notification.local.schedule({
    text: "Delayed Notification",
    at: snooze_time,
    icon: "res://notification_icon.png",
    sound:"res://medicine_notification.mp3"
});
         	        }
          	        else
          	        {
          	          navigator.notification.alert("Reminder cannot be added because app doesn't have permission");
          	        }
          	    });
          	  }
          	});
			  
			  
			  
			  
			 
		  
          if(!localStorage.getItem("rp_data"))
          {
            var rp_data = {data: []};
            localStorage.setItem("rp_data", JSON.stringify(rp_data));
          }
          info = JSON.parse(localStorage.getItem("rp_data"));


              }
			  
			  
			  
			  
			  
			  
			  
			  
              });
 




}


/*
			  
			 
				  var id = data[0].id;
				  //var id ="2326";
				  var title = data[0].title;
				  var message = 'Local Notification';
				  var schedule_time = data[0].start;
				//  alert("Time and Date  is:"+schedule_time);
				  var schedule_time = new Date(schedule_time);
				  
				   schedule(id, title, message, schedule_time);

		  
}	
    

	 function schedule(id, title, message, schedule_time)
          {
          
		  var twentyMinutesLater = new Date();
		  alert("Current Date:"+twentyMinutesLater);
var added_time = twentyMinutesLater.setMinutes(twentyMinutesLater.getMinutes() + 2);
alert("Time after adding: "+added_time);

          	cordova.plugins.notification.local.schedule({
          	    id: id,
          	    title: title,
          	    message: message,
          	    at: schedule_time
          	   
          	});
          	
          	
          	var array = [id, title, message, schedule_time];
          	info.data[info.data.length] = array;
          	localStorage.setItem("rp_data", JSON.stringify(info));
          	navigator.notification.alert("Reminder added successfully")
          }

*/




function onBackKeyDown() {

    window.location = './login_existing.html';

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
        for (i = 0; i<arrURLParams.length; i++)
        {
            var sParam =  arrURLParams[i].split("=");
            arrParamNames[i] = sParam[0];
            if (sParam[1] != "")
                arrParamValues[i] = unescape(sParam[1]);
            else
                arrParamValues[i] = "No Value";
        }

        for (i=0; i<arrURLParams.length; i++)
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




function logout()
{
	
	
	 $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });
	
	    
	$.ajax({
		 url:"http://183.82.97.23:9090/user/logout",		 
		 type:"POST",
		 dataType:"json",	
		 success:function(data)
		 {
		
		 	
		 	
		 },
		 error:function(jqXHR,textStatus,errorThrown)
		 {
		 	
	
       
        
		 
		 }
		});
		
	
	
	}





 
