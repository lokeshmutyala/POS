
$(document).ready(function () {
	
	document.addEventListener("deviceready", onDeviceReady_1, false);
	$('.alert_bootstrap').hide();
	document.getElementById("pin_id1").value = "";
	document.getElementById("pin_id2").value = "";
    
  
});

function onDeviceReady_1()
{

	
$("#pin_id2").on('keydown', function(e){
 
    if(e.which == 9) {
      
     
        changepin();
    }
});


 document.addEventListener("backbutton", onBackKeyDown, false);
$('#reset_pin_submit').attr('disabled',true);


    var networkState = navigator.connection.type;
    if (networkState == Connection.NONE)
    {

        window.location = './first_screen.html';
        return true;

    }
    


        $("#pin_id1").keyup(function () {
        	
            valuepin1();
       });
       
        
        $("#pin_id2").keyup(function () {
             //valuepin1();
			  valuepin2();
          
        });
	
    

}







 var device_uuid=getURLParameters("device_uuid");
	   
		$('#device_uuid').val(device_uuid);

 
function changepin() {


  





                $.ajaxSetup({
                    xhrFields: {
                        withCredentials: true
                    }
                });


                var loading = $('#loading').html();
                $('#reset_pin_submit').html(loading);
                $('#reset_pin_submit').prop('disabled', true);
                

                var pin = $("#pin_id1").val();
//                alert("pin"+pin);
                var device_uuid=device.uuid;
                //alert("device_uuid"+device_uuid);
                //var device_uuid = $('#device_uuid').val();
//                alert("device_uuid"+device_uuid);

	var url='http://183.82.97.23:8182/reset_pin/'+device.uuid+'/'+pin+'?_format=json';			

			$.ajax({
                    url: url,
                    type: "get",
                    dataType: "json",
                    timeout: 20000,
                    crossDomain: true,
                    error: function (jqXHR, textStatus, errorThrown) {
                        // alert("inside error");
                        //alert(jqXHR.responseText);
                    	var loading = $('#loading').html();
                    	var change_pin_label = $('#change_pin').html();
                        $('#reset_pin_submit').html(loading);
                        $('#reset_pin_submit').prop('disabled', false);
                        $('#reset_pin_submit').prop('disabled', false);
                        $('#reset_pin_submit').html(change_pin_label);


                        var please_try_after_sometime = $('#please_try_after_sometime').html();
                    	$('.alert_bootstrap').show();
                    	$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+please_try_after_sometime+'</div>');

                    

                    },
                    success: function (data) {



                         

                        if (data.response == '0')
                        {
//                            alert("PIN changed Successfully");
                            var phone = data.name;
//                             alert("phone"+phone);
                            var user_id = data.user_id;
//                             alert("user_id"+user_id);
                         var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});
//                         alert("After initialising db");
                		 db.query('mobile_app/sort_by_devices').then(function (res) {
//                			 alert("res");
//                			 alert("Json Stringify:"+JSON.stringify(res));
                		 	var uid = res.rows[0].id;
//                		 	alert("uid: "+uid);
                		 	
//                		 	alert("db query pin is: "+pin);
                		 	var md5_encrypted =  md5(pin);
//                		 	alert("After encryption: "+md5_encrypted);
                		 	db.get(uid).then(function (doc) {

                		 		

//                		 		alert('submitted_same pin'+JSON.stringify(doc)); 
    			    			
    			    			
//    			    				alert("Extracting device uuid");
    			    				var device_uuid = device.uuid;
//    			    				alert("Device Uuid: "+device_uuid);
    			    				var new_pin='';
    			    				   
    			    				new_pin='{"pin": "'+md5_encrypted+'"}';
    			    				new_pin = JSON.parse(new_pin);
    			    					
    			    				
    			    				doc.mobile_details.devices[device_uuid]=new_pin;
//    			    				alert("After Modifying::: "+doc.mobile_details.devices[device_uuid]);
    			    			
                		 		
                		 		
                		 		
                	return db.put(doc);
                	}).then(function () {
                    	db.sync('http://admin:Eapp4U123@183.82.97.23:5984/medical_dictionary',  {
                    	doc_ids: [uid,'_design/mobile_app','_design/patient_medicines','_design/notification']
                    	}).on('complete', function (info) {
                    		
                    		
                    		var pin_changed_successfully = $('#pin_changed_successfully').html();
                    		$('.alert_bootstrap').show();
                    		$(".alert_bootstrap").html('<div class="alert alert-success"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+pin_changed_successfully+'</div>');
                    		window.location = './index.html';
                           

                    		
                    		
                    	}).on('error', function (err) {
                    		
                    		var pin_changed_successfully = $('#pin_changed_successfully').html();
                    		$('.alert_bootstrap').show();
                    		$(".alert_bootstrap").html('<div class="alert alert-success"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+pin_changed_successfully+'</div>');
                    		window.location = './index.html';


                    	}).on('paused', function (err) {
                    	});
                	});
                		 })


                        }
                        else
                        {

                    		var pin_not_generated = $('#pin_not_generated').html();
                    		$('.alert_bootstrap').show();
                    		$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+pin_not_generated+'</div>');
                    		

                        }

                    }
                });
       
 
 
 
}




function onBackKeyDown() {

    window.location = './login_existing.html';

}


function valuepin1(){
   

 var value = document.getElementById('pin_id1').value;
 if (value.length > 3) {
   var changepin = /^(?!(.)\1{3})(?!0123|1234|2345|3456|4567|5678|6789|7890|0987|9876|8765|7654|6543|5432|4321|3210)\d{4}$/; 
            var newpin_sequence1=changepin.test(value);

if(newpin_sequence1==false)
			{
				
	var log_in_pin_condition = $('#log_in_pin_condition').html();
	$('.alert_bootstrap').show();
	$(".alert_bootstrap").html('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+log_in_pin_condition+'</div>');
	
				return false;
				
		  }
 else{
	 
 $('#reset_pin_submit').attr('disabled',true);
  } 
 }
 else{
 
 $('#reset_pin_submit').attr('disabled',true);
 }
	   
	   
}


function valuepin2(){
   

 var value = document.getElementById('pin_id2').value;
  var value1 = document.getElementById('pin_id1').value;
 
 if (value.length > 3) {

 	if(value==value1){
 	        var changepin = /^(?!(.)\1{3})(?!0123|1234|2345|3456|4567|5678|6789|7890|0987|9876|8765|7654|6543|5432|4321|3210)\d{4}$/; 
            var newpin_sequence1=changepin.test(value);


   if(newpin_sequence1==false)
			{
	   var log_in_pin_condition = $('#log_in_pin_condition').html();
		$('.alert_bootstrap').show();
		$(".alert_bootstrap").html('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+log_in_pin_condition+'</div>');
		
				return false;
		  }
		  
	else{
	   $('#reset_pin_submit').attr('disabled',false);
	   $("#reset_pin_submit").addClass("new_pin_submit1");
      }
 
  }
  else if(document.getElementById("pin_id1").value == "")
  {
  
	    var first_field_cannot_be_empty = $('#first_field_cannot_be_empty').html();
		$('.alert_bootstrap').show();
		$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+first_field_cannot_be_empty+'</div>');
		
	  
	  
				return false;
  }
  
  else{
 
	  var pin_does_not_match = $('#pin_does_not_match').html();
		$('.alert_bootstrap').show();
		$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+pin_does_not_match+'</div>');
		
	  
	  
				return false;
  } 
  
 }

else{
       $('#reset_pin_submit').attr('disabled',true);
 }
	   
	   
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




function onBackKeyDown() {

window.location = './login_existing.html';
 
}
 


  



