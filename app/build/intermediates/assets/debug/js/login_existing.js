    
$(document).ready(function(){
	//alert("Doc Ready11111")
//	 window.location='./notification.html';
//	 window.location='./newpin.html';
//	 window.location='ajax/content/event_schedules.html';
	$('.alert_bootstrap').hide();
				 logout();
				 document.getElementById("PIN").value = "";
				 var sound_file;
				 var four_digit_security_pin = $('#four_digit_security_pin').html();
		//		   alert("four_digit_security_pin"+four_digit_security_pin);
    document.addEventListener("deviceready",onDeviceReady,false);       
});
 
    function onDeviceReady() 
    {
     window.location='./check-in.html';
    	cordova.plugins.notification.local.on("click", function (notification, state) {
    	      window.location='./notification.html?id='+notification.id;
    	}, this)
	 var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});
		 db.query('notification/notification_sound').then(function (res) {
//			 alert("String:"+JSON.stringify(res));
		
		 	var uid = res.rows[0].id;
			
	db.get(uid).then(function (doc) {
		
 	return db.put(doc);
	}).then(function () {
    	db.sync('http://admin:Eapp4U123@183.82.97.23:5984/medical_dictionary',  {
    	doc_ids: [uid,'_design/notification']
    	}).on('complete', function (info) {
    		
    	}).on('error', function (err) {

    	}).on('paused', function (err) {
    	});
	});
		 })
   sound_file = results.rows.item(0).sound;
   
  $("#sound_file").val(sound_file);
   
		
      document.addEventListener("backbutton", onBackKeyDown, false);
		
		var networkState = navigator.connection.type;
    if (networkState == Connection.NONE)
    {
    
        
              
    }
    
    /*
    var footerHeight = 0,
           footerTop = 0,
           $footer = $("#footer");
           
       positionFooter();
       
       function positionFooter() {
      
                footerHeight = $footer.height();
                footerTop = ($(window).scrollTop()+$(window).height()-footerHeight)+"px";
       
               if ( ($(document.body).height()+footerHeight) < $(window).height()) {
                   $footer.css({
                        position: "absolute"
                   }).animate({
                        top: footerTop
                   })
               } else {
                   $footer.css({
                        position: "static"
                   })
               }
               
       }

       $(window)
               .scroll(positionFooter)
               .resize(positionFooter)
               


*/
		
		
		
		
		
		var element = document.getElementById('deviceProperties');
		var device_uuid = device.uuid;
       	var device_name  = device.name;
       	var device_model  = device.model;
       	var device_platform =  device.platform;                        
       	var device_version =  device.version;  
       
       	document.getElementById('device_uuid').value=device_uuid;
		document.getElementById('device_model').value=device_model;
		document.getElementById('device_platform').value=device_platform;
		document.getElementById('device_version').value=device_version;
       	

   
			}


     $(document).ready(function() {
     
     
     
     
     jQuery('input').keypress(function(event){
     
       
       var enterOkClass =  jQuery(this).attr('class');
	   //alert(event.which);
	   
	   
        if (event.which == 13) {
	   login();
            event.preventDefault();
            return false;   
        }
    });
    
    
    $('#PIN').on('keydown', function(e){
   
    if(e.which === 9) {
       
        login();
    }
});
    
     
     $("#PIN").keyup(function(){
   
   /*   var networkState = navigator.connection.type;
      if(networkState == Connection.NONE)
       {
       
      window.location='./first_screen.html';
           	return true; 
      
       
       }*/
     var value = $('#PIN').val();
     if(value.length>3){
     var loginpin = /^(?!(.)\1{3})(?!0123|1234|2345|3456|4567|5678|6789|7890|0987|9876|8765|7654|6543|5432|4321|3210)\d{4}$/; 
	  loginpin1=document.getElementById("PIN").value;		
			
			var pin_sequence=loginpin.test(loginpin1);
			
			var country=$('#phone').val().substring(0,2);
            var phonelen=$("#phone").val().length;
            var ok_label = $('#ok').html();
            var information_label = $('#information').html();
            var alert_label = $('#alert').html();
           
   
            
            if(document.getElementById("PIN").value == "")
			{
		       //alert("Please enter OTP");
            	var four_digit_security_pin = $('#four_digit_security_pin').html();
            	
            	$('.alert_bootstrap').show();
            	$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+four_digit_security_pin+'</div>');
		
				return false;
			}
			
			else if(pin_sequence==false)
			{
				 var log_in_label = $('#log_in_label').html();
					
				$('#login_submit').prop('disabled', false);
				$('#login_submit').html(log_in_label);
				var invalid_log_in_pin = $('#invalid_log_in_pin').html();
				$('.alert_bootstrap').show();
				$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+invalid_log_in_pin+'</div>');
				return false;
			
			}
			
      $("#login_submit").removeAttr('disabled');
      $("#login_submit").addClass("log_submit");
       }
       
       
       
       
       
       else{
       $('#login_submit').attr('disabled',true);
       $("#login_submit").removeClass("log_submit");
       }
   });
   
  
 });
    

	
		$(document).ready(function(){
	    logout();
		
		var device_uuid=getURLParameters("device_uuid");
		var phone=getURLParameters("phone");
		$('#device_uuid').val(device_uuid);
     	
     $("#user").val(user);
    
     $("#phone").val(phone);
   
   $('#forgot_pin').click(function(event) {
        document.addEventListener("backbutton", onBackKeyDown, false);
	
	var networkState = navigator.connection.type;
if (networkState == Connection.NONE)
{

	 window.location='./first_screen.html';
     return true;
          
}

else{
device_uuid= $('#device_uuid').val();  
//location.href="newpin.html?device_uuid="+device_uuid;
   location.href="forgotpin.html?device_uuid="+device_uuid;
}
   });
   
   
    $("#login_submit").click(function(event) {
       
	   login();
	  });
      
      
      
      

   
      

      
      
      
       });
	  
   
function onBackKeyDown(e) {

   navigator.app.exitApp();
 
} 
   
function about_us(){
	
	$('.alert_bootstrap').show();
	$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>Build Version 3.1.0</div>');
}

function contact_us(){

	$('.alert_bootstrap').show();
	$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>Medical Dictionary.inc, Phone number: 9999999999, Email: contactus@medicaldictionary.inc.</div>');

}
function locate_us(){

	
	$('.alert_bootstrap').show();
	$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>eApp Innovation Infrasystems Pvt Ltd.</div>');

}




function services(){

	
	$('.alert_bootstrap').show();
	$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>Various Services are available in this application.</div>');

}


function calendar(){
 
}


   function login(){
	 
		var loading = $('#loading').html();
	   $('#login_submit').html(loading);
	 var encrypted_pin=getURLParameters("encrypted_pin");
         var networkState = navigator.connection.type;
  

var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});



		db.info().then(function (info) {

if(info.doc_count > 0)
{
    
    var db = new PouchDB('medical_dictionary_login_session', {adapter: 'websql', iosDatabaseLocation: 'default'});
db.info().then(function (info) {
//alert("Db info1");
//alert(info.doc_count);
if(info.doc_count < 1)
{
//	alert("New db doesnt exists");
        
        if(navigator.onLine)
        {
//            alert("Internet is enabled");
            
            
            var session_uuid = genString(32);
//              alert("Session UUID: "+session_uuid);
		  var db = new PouchDB('medical_dictionary_login_session', {adapter: 'websql', iosDatabaseLocation: 'default'});
//		  alert("After creating new pouch db");
//                  alert(device.uuid);
//                  alert(session_uuid);
                                db.put({
                                    _id: '123456789',
                                    mobile_session_details: {device_uuid: device.uuid,
                                        session_id: session_uuid}
                                    });
                                    
                                    
            	   $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });

		   
			var loading = $('#loading').html();
            $('#login_submit').html(loading);
           	$('#login_submit').prop('disabled', true);
           	$('#login_submit').prop('disabled', true);	
	
        $.ajax({
              url: 'http://183.82.97.23:8182/save_session_id/'+device.uuid+'/'+session_uuid+'?_format=json',
              type: "GET",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
  error: function (jqXHR, textStatus, errorThrown) {
	  var log_in_label = $('#log_in_label').html();
		var loading = $('#loading').html();
				$('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
              	var problem_connecting_with_server = $('#problem_connecting_with_server').html();
              	$('.alert_bootstrap').show();
            	$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+problem_connecting_with_server+'</div>');
              },
                success: function (data) {

//						alert("Web service success"+data.response);

                        if (data.response == '1')
                        {
//							alert("Response 1");
							var uid=data.uid;
                        	var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});


                        	db.sync('http://admin:Eapp4U123@183.82.97.23:5984/medical_dictionary',  {
                        	doc_ids: [uid,'_design/mobile_app','_design/notification','_design/patient_medicines','_design/labs_master_list','labs_master_list']
                        	}).on('complete', function (info) {
                                    
//                                          	window.location='./ajax/index.html';
                        		 window.location='./check-in.html';
                      return false;
                        		
                        	}).on('error', function (err) {
//                        	  alert("handle error"+JSON.stringify(err));

//                          	window.location='./ajax/index.html';
                        		 window.location='./check-in.html';
                        	}).on('paused', function (err) {


                        	});
                        	
                        	
                        	
    					 
			  
			
						}
						else
						{
//						alert("Wen service response is not 1");
							var log_in_label = $('#log_in_label').html();
							var loading = $('#loading').html();
						 $('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
					var invalid_log_in_pin = $('#invalid_log_in_pin').html();
					
		          
					$('.alert_bootstrap').show();
					$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+invalid_log_in_pin+'</div>');
	
						}


				}

	});
        }
        else
        {
//            alert("Internet is disabled");
            var session_uuid = genString(32);
//              alert("Session UUID: "+session_uuid);
		  var db = new PouchDB('medical_dictionary_login_session', {adapter: 'websql', iosDatabaseLocation: 'default'});
//		  alert("After creating new pouch db");
//                  alert(device.uuid);
//                  alert(session_uuid);
                                db.put({
                                    _id: '123456789',
                                    mobile_session_details: {device_uuid: device.uuid,
                                        session_id: session_uuid}
                                    });
        }
        
        
        
}
else
{
//	alert("New db exists");
}
});
			 var phone=getURLParameters("phone");
			 var encrypted_pin=getURLParameters("encrypted_pin");
			 var pin_value = $("#PIN").val();
			 var md5_encrypted =  md5(pin_value);
			 if(encrypted_pin==md5_encrypted)
			 {
//	alert("Encrypted pin");
    if (navigator.onLine)
    {
//        alert("Online");
    var db = new PouchDB('medical_dictionary_login_session', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});
				  var id = '123456789';
                                db.get(id).then(function (doc) {
                                    
                                    for (session_id in doc.mobile_session_details)
                                    {
                                        var session_id_get = doc.mobile_session_details.session_id;
                                       
                                    }
//                                    alert("session_id_get"+session_id_get);
                                    
                                    
                                    $.ajax({
              url: 'http://183.82.97.23:8182/save_session_id/'+device.uuid+'/'+session_id_get+'?_format=json',
              type: "GET",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
  error: function (jqXHR, textStatus, errorThrown) {
	  var log_in_label = $('#log_in_label').html();
		var loading = $('#loading').html();
				$('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
              	var problem_connecting_with_server = $('#problem_connecting_with_server').html();
              	$('.alert_bootstrap').show();
            	$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+problem_connecting_with_server+'</div>');
              },
                success: function (data) {

//						alert("Web service success");

                        if (data.response == '1')
                        {
//							alert("Response 1");
//							alert("Internet exists");
//		alert("Encrypted pin internet");
			  var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});
            db.query('mobile_app/sort_by_devices').then(function (res) {
            	
                var id = res.rows[0].id;
                
                db.get(id).then(function (doc) {

     if (!doc.user_profile_data.wizard_step)
	 {
//		      window.location='./ajax/index.html#page/user_profile_wizard'; 
    	 window.location='./check-in.html';
	 }
     else if (doc.user_profile_data.wizard_step!=3)
	 {

//		      window.location='./ajax/index.html#page/user_profile_wizard'; 
    	 window.location='./check-in.html';
	 }
	 else
	 {
		 
//		       window.location='./ajax/index.html';
		 window.location='./check-in.html';
	 }	 


                });
				                // index was built!
            }).catch(function (err) {

//                			  alert("login some error");
            });
                
 
 return false;
				
		  
		
                        	
                        	
                        	
    					 
			  
			
						}
						else
						{
//						alert("Wen service response is not 1");
							var log_in_label = $('#log_in_label').html();
							var loading = $('#loading').html();
						 $('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
					var invalid_log_in_pin = $('#invalid_log_in_pin').html();
					
		          
					$('.alert_bootstrap').show();
					$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+invalid_log_in_pin+'</div>');
	
						}


				}

	});
                                });
									
									
         
		
				
              
    }
	else
	{
//				alert("Encrypted pin");
			  var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});
            db.query('mobile_app/sort_by_devices').then(function (res) {
            	
                var id = res.rows[0].id;
                
                db.get(id).then(function (doc) {

     if (!doc.user_profile_data.wizard_step)
	 {
//		      window.location='./ajax/index.html#page/user_profile_wizard'; 
    	 window.location='./check-in.html';
	 }
     else if (doc.user_profile_data.wizard_step!=3)
	 {

//		      window.location='./ajax/index.html#page/user_profile_wizard'; 
    	 window.location='./check-in.html';
	 }
	 else
	 {
		 
//		       window.location='./ajax/index.html';
		 window.location='./check-in.html';
	 }	 


                });
				                // index was built!
            }).catch(function (err) {

//                			  alert("login some error");
            });
                
 
 return false;
				
		  
		
		
				
		
		
			 }
			 }
		 else{
			 $('#login_submit').prop('disabled', false);
			 var log_in_label = $('#log_in_label').html();
				
				$('#login_submit').html(log_in_label);
				var invalid_log_in_pin = $('#invalid_log_in_pin').html();
		
				$('.alert_bootstrap').show();
				$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+invalid_log_in_pin+'</div>');
		 }
			 
			 
			 
         	//window.location='./first_screen.html';
           	//return true;   
         }

          
   
    
         else if(document.getElementById("PIN").value == "")
			{
        	 
        	 var four_digit_security_pin = $('#four_digit_security_pin').html();
        	 $('.alert_bootstrap').show();
        		$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+four_digit_security_pin+'</div>');
		
				return false;
			}
			
			
			
			else
			{
      
//                        alert("No local database");
					
//					event.preventDefault();
	      var session_uuid = genString(32);
//              alert("Session UUID: "+session_uuid);
		  var db = new PouchDB('medical_dictionary_login_session', {adapter: 'websql', iosDatabaseLocation: 'default'});
//		  alert("After creating new pouch db");
//                  alert(device.uuid);
//                  alert(session_uuid);
                                db.put({
                                    _id: '123456789',
                                    mobile_session_details: {device_uuid: device.uuid,
                                        session_id: session_uuid}
                                    });
//                                    alert("Device uuid is: "+device.uuid);
		  
		   $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });

		   
			var loading = $('#loading').html();
            $('#login_submit').html(loading);
           	$('#login_submit').prop('disabled', true);
           	$('#login_submit').prop('disabled', true);	
	
        $.ajax({
              url: 'http://183.82.97.23:8182/save_session_id/'+device.uuid+'/'+session_uuid+'?_format=json',
              type: "GET",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
  error: function (jqXHR, textStatus, errorThrown) {
	  var log_in_label = $('#log_in_label').html();
		var loading = $('#loading').html();
				$('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
              	var problem_connecting_with_server = $('#problem_connecting_with_server').html();
              	$('.alert_bootstrap').show();
            	$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+problem_connecting_with_server+'</div>');
              },
                success: function (data) {

//						alert("Web service success");

                        if (data.response == '1')
                        {
//							alert("Response 1");
							//var uid=data.uid;
                        	var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});


                        	db.sync('http://admin:Eapp4U123@183.82.97.23:5984/medical_dictionary',  {
                        	doc_ids: [uid,'_design/mobile_app','_design/notification','_design/patient_medicines','_design/labs_master_list','labs_master_list']
                        	}).on('complete', function (info) {
                                    
//                                          	window.location='./ajax/index.html';
                        		 window.location='./check-in.html';
                      return false;
                        		
                        	}).on('error', function (err) {
//                        	  alert("handle error"+JSON.stringify(err));

//                          	window.location='./ajax/index.html';
                        		 window.location='./check-in.html';
                        	}).on('paused', function (err) {


                        	});
                        	
                        	
                        	
    					 
			  
			
						}
						else
						{
//						alert("Wen service response is not 1");
							var log_in_label = $('#log_in_label').html();
							var loading = $('#loading').html();
						 $('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
					var invalid_log_in_pin = $('#invalid_log_in_pin').html();
					
		          
					$('.alert_bootstrap').show();
					$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+invalid_log_in_pin+'</div>');
	
						}


				}

	});
        
//      			alert("After Web service");
                    var user=$("#user").val();
					var phone=$("#phone").val();
					
					var PIN=$("#PIN").val();
					var device_uuid=device.uuid;  	
					var pin_device_uuid=PIN+'--'+device_uuid
					var phone = phone.replace(" ", "");
					
				/*	alert("user"+user);	
					alert("phone"+phone);
					alert("PIN"+PIN);
					alert("check length PIN"+PIN.length);	
					alert("check length Phone"+phone.length);
					alert("check length User"+user.length);			*/
					
	
	   $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });
    
    
    
    
    
   PIN=pin_device_uuid;

  // alert(phone);
 //  alert(PIN);
   
	$.ajax({
              url: 'http://183.82.97.23:8182/user_auth/'+encodeURIComponent(phone)+'/'+encodeURIComponent(PIN)+'?_format=json',
              type: "GET",
              dataType: "json",
              timeout:20000,
              crossDomain: true,
  error: function (jqXHR, textStatus, errorThrown) {
	  var log_in_label = $('#log_in_label').html();
		var loading = $('#loading').html();
				$('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
              	var problem_connecting_with_server = $('#problem_connecting_with_server').html();
              	$('.alert_bootstrap').show();
            	$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+problem_connecting_with_server+'</div>');
              },
                success: function (data) {



                        if (data.response == '1')
                        {

                        	

var uid=data.uid;
                        	var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});


                        	db.sync('http://admin:Eapp4U123@183.82.97.23:5984/medical_dictionary',  {
                        	doc_ids: [uid,'_design/mobile_app','_design/notification','_design/patient_medicines','_design/labs_master_list','labs_master_list']
                        	}).on('complete', function (info) {
                                    
//                                          	window.location='./ajax/index.html';
                        		 window.location='./check-in.html';
                      return false;
                        		var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});
                        		db.query('notification/today_schedules').then(function (res) {
                        		
                        		  for(var i = 0; i<res.rows.length; i++)
                    			  {
                    				
                    			  var id = res.rows[i].value.id;
                    			  var title = res.rows[i].value.med_name;
                    			  var time = res.rows[i].value.start;
                    			  var comp_name = res.rows[i].value.comp_name; 
                    			  var med_quantity = res.rows[i].value.med_quantity;
                    			  var med_strength = res.rows[i].value.med_strength;
                    			  var now_time = new Date();
                    			  var schedule_time = new Date(time);
                    			


                    			  if(med_quantity=="1/4" || med_quantity=="1/2" || med_quantity=="1"){

                    			  switch (comp_name) {
                    			  case ("Syrup"):
                    			     var category = 'Spoon';
                    			      break;
                    			  case ("Tablet"):
                    			     var category = 'Tablet';
                    			      break;
                    			  case ("Capsule"):
                    			     var category = 'Capsule';
                    			      break;
                    			  case ("Injection"):
                    			    var category = 'Injection';
                    			      break;
                    			  case ("Powder"):
                    			     var category = 'gm';
                    			      break;
                    			  case ("Cream"):
                    			     var category = 'gm';
                    			      break;
                    			  case ("Inhaler"):
                    			     var category = 'Puff';
                    			      break;
                    			  case ("Suspension"):
                    			     var category = 'Dose';
                    			      break;
                    			  case ("Drop"):
                    			      var category = 'Drop';
                    			      break;
                    			  case ("Expectorant"):
                    			     var category = 'Dose';
                    			      break;
                    			  case ("Gel"):
                    			     var category = 'ml';
                    			      break;
                    			  case ("Infusion"):
                    			     var category = 'Dose';
                    			      break;
                    			  case ("Kit"):
                    			      var category = 'Kit';
                    			      break;
                    			  case ("Lotion"):
                    			    var category = 'ml';
                    			      break;
                    			  case ("Lozenges"):
                    			    var category = 'Lozenge';
                    			      break;
                    			  case ("Mouthwash"):
                    			     var category = 'ml';
                    			      break;
                    			  case ("Ointment"):
                    			     var category = ' % ';
                    			      break;
                    			  case ("Pessary"):
                    			   var category = 'Dose';
                    			      break;
                    			  case ("Sachet"):
                    			    var category = 'Sachet';
                    			      break;
                    			  case ("Shampoo"):
                    			    var category = 'Shampoo';
                    			      break;
                    			  case ("Soap"):
                    			   var category = 'Soap';
                    			      break;
                    			  case ("Solution"):
                    			    var category = 'Dose';
                    			      break;
                    			  case ("Toothpaste"):
                    			    var category = 'gm';
                    			      break;

                    			  }
                    			  }
                    			  else
                    			  {
                    			  switch (comp_name) {
                    			  case ("Syrup"):
                    			     var category = 'Spoons';
                    			      break;
                    			  case ("Tablet"):
                    			     var category = 'Tablets';
                    			      break;
                    			  case ("Capsule"):
                    			     var category = 'Capsules';
                    			      break;
                    			  case ("Injection"):
                    			    var category = 'Injections';
                    			      break;
                    			  case ("Powder"):
                    			     var category = 'gms';
                    			      break;
                    			  case ("Cream"):
                    			     var category = 'gms';
                    			      break;
                    			  case ("Inhaler"):
                    			     var category = 'Puffs';
                    			      break;
                    			  case ("Suspension"):
                    			     var category = 'Doses';
                    			      break;
                    			  case ("Drop"):
                    			      var category = 'Drops';
                    			      break;
                    			  case ("Expectorant"):
                    			     var category = 'Doses';
                    			      break;
                    			  case ("Gel"):
                    			     var category = 'ml';
                    			      break;
                    			  case ("Infusion"):
                    			     var category = 'Doses';
                    			      break;
                    			  case ("Kit"):
                    			      var category = 'Kits';
                    			      break;
                    			  case ("Lotion"):
                    			    var category = 'ml';
                    			      break;
                    			  case ("Lozenges"):
                    			    var category = 'Lozenges';
                    			      break;
                    			  case ("Mouthwash"):
                    			     var category = 'ml';
                    			      break;
                    			  case ("Ointment"):
                    			     var category = ' % ';
                    			      break;
                    			  case ("Pessary"):
                    			   var category = 'Doses';
                    			      break;
                    			  case ("Sachet"):
                    			    var category = 'Sachets';
                    			      break;
                    			  case ("Shampoo"):
                    			    var category = 'Shampoos';
                    			      break;
                    			  case ("Soap"):
                    			   var category = 'Soaps';
                    			      break;
                    			  case ("Solution"):
                    			    var category = 'Doses';
                    			      break;
                    			  case ("Toothpaste"):
                    			    var category = 'gm';
                    			      break;

                    			  }	
                    			  }
                    			  
                    			  
                    			  var message = 'Take '+med_quantity+' '+category;
                    		
                    			  
                    			  
                    		
                     					var data_quantity = res.rows[i].value.med_quantity;
                    					
                    				  if(schedule_time > now_time )
                    				  {
                    				  schedule(id, title, message, schedule_time,data_quantity,comp_name);
                    				   }
                    				 
                    				 
                    			  }
                        		
                        		
                        		
                        		  cordova.plugins.notification.local.hasPermission(function(granted){
                    	          	  if(granted == true)
                    	          	  {
                    	          		
                    	          	    schedule(id, title, message, schedule_time,data_quantity,comp_name);
                    	          	  }
                    	          	  else
                    	          	  {
                    	          		
                    	          	    cordova.plugins.notification.local.registerPermission(function(granted) {
                    	          	        if(granted == true)
                    	          	        {
                    	          	        	
                    	          	          schedule(id, title, message, schedule_time,data_quantity,comp_name);
                    	          	        }
                    	          	        else
                    	          	        {
                    	          	          navigator.notification.alert("Reminder cannot be added because app doesn't have permission");
                    	          	        }
                    	          	    });
                    	          	  }
                    	          	})
                    				  
                    			
//                            	window.location='./ajax/index.html';
                    	          	 window.location='./check-in.html';
                        	})
                        	}).on('error', function (err) {
//                        	  alert("handle error"+JSON.stringify(err));

//                          	window.location='./ajax/index.html';
                        		 window.location='./check-in.html';
                        	}).on('paused', function (err) {


                        	});
                        	
                        	
                        	
    					 
			  
			
						}
						else
						{
						
							var log_in_label = $('#log_in_label').html();
							var loading = $('#loading').html();
						 $('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					
					var invalid_log_in_pin = $('#invalid_log_in_pin').html();
					
		          
					$('.alert_bootstrap').show();
					$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+invalid_log_in_pin+'</div>');
	
						}


				}

	});
	
		return false;    
			
	
	
            $.ajax({
              url:" http://183.82.97.23:8182/m_service/user/login",
		    data:"username="+encodeURIComponent(phone)+"&password="+encodeURIComponent(pin_device_uuid),
              type: "POST",
	  		
      		 
              dataType: "json",
              //timeout:20000,
              crossDomain: true,
              
			  error: function (jqXHR, textStatus, errorThrown) {
			  
		//	 alert("inside error");
             
              //alert(jqXHR.statusText);
              
				  var log_in_label = $('#log_in_label').html();
					var loading = $('#loading').html();
               $('#login_submit').html(loading);
				  $('#login_submit').prop('disabled', false);
				  $('#login_submit').prop('disabled', false);
					$('#login_submit').html(log_in_label);
					var problem_connecting_with_server = $('#problem_connecting_with_server').html();
					$('.alert_bootstrap').show();
					$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+problem_connecting_with_server+'</div>');
               
              },
              success: function (data) {
            
               /*alert(JSON.stringify(data, null, 4));
               //alert("check");
             
			
			    var cookie_state = "Cookies Enabled: " + navigator.cookieEnabled;
			    alert("cookie_state"+cookie_state);
			    var x = document.cookie;
			    alert("cookie data"+x);*/
			 
			   // window.location = 'http://183.82.97.23:8182/user/user_mobile_access/'+phone+'/'+PIN;
			    
			    
			  }
			  });
			
            
	  }
	   
		});   
	   
	   
	   }
    



function logout()
{
	
	
	 $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });
	
		   
	$.ajax({
		 url:"http://183.82.97.23:8182/user/logout",		 
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

	
	
function schedule(id, title, message, schedule_time,data_quantity,comp_name)
{

var sound_clip = $("#sound_file").val();


if(sound_clip=="sound1")
{
	var custom_sound = "file://notification_sounds/time_medicine.mp3";
}
else if(sound_clip=="sound2")
{
	var custom_sound = "file://notification_sounds/super_ringtone.mp3";
}
else if(sound_clip=="sound3")
{
	var custom_sound = "file://notification_sounds/notification_sound.mp3";
}
else if(sound_clip=="sound4")
{
	var custom_sound = "file://notification_sounds/awesome_ringtone.mp3";
}
	else if(sound_clip=="sound5")
{
	var custom_sound = "file://notification_sounds/google.mp3";
}
else if(sound_clip=="sound6")
{
var custom_sound = "file://notification_sounds/medicine_notification.mp3";
}
else if(sound_clip=="sound7")
{
var custom_sound = "file://notification_sounds/pills.mp3";
}
else
{
	var custom_sound = "file://notification_sounds/time_medicine.mp3";
}



	var quantity = data_quantity+' '+comp_name+'(s)';


  	cordova.plugins.notification.local.schedule({
	    id: id,
	    icon: "res://notification_icon.png",
	    smallIcon: "res://notification_icon.png",
	    ongoing: false,
	    sound:custom_sound,
	    title: title,
	    message: message,
	    at: schedule_time,
	    data:quantity
	   
	});
	
}
	
	function getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }

        /**
         * Gen random digits string in specific length
         * @param {Int} length of string
         *
         * @return {String}
         *
         */
        function genString(length) {
            var times = length;
            var key = '';
            while (times > 0) {
                times--;
                key += getRandomInt(0, 9);
            }
            return key;
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