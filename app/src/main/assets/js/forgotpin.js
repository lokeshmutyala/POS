
$(document).ready(function(){
	$('.alert_bootstrap').hide();
	
	
    
    document.addEventListener("deviceready",onDeviceReady,false);       

    $("#phone").keydown(function(e) {
    	var number_field = $("#phone").val();
        if (number_field=="+91" ||number_field=="+1") {
        	if(e.which==8)
        		{
        		return false;
        		}
        }

    });

});
 

   
    function onDeviceReady() 
    {
  
  
  
      document.addEventListener("backbutton", onBackKeyDown, false);
      
      
			
      
      
      var networkState = navigator.connection.type;
    if (networkState == Connection.NONE)
    {
     
         window.location='./first_screen.html';
        return true;
              
    }
   
   
			}


		$(document).ready(function(){
	
		
	   var device_uuid=getURLParameters("device_uuid");
	   //alert("receivibg"+device_uuid);
		$('#device_uuid').val(device_uuid);
		
	
     $("#phone").intlTelInput({
        allowExtensions: true,
        autoFormat: true,
        autoHideDialCode: true,
        autoPlaceholder: true,
        defaultCountry: "auto",
         geoIpLookup: function(callback) {
          $.get('http://ipinfo.io', function() {}, "jsonp").always(function(resp) {
            var countryCode = (resp && resp.country) ? resp.country : "";
             callback(countryCode);
          });
         },
        nationalMode: false,
        numberType: "MOBILE",
        onlyCountries: ['in','us'],
        preferredCountries: ['in', 'us'],
        utilsScript: "lib/libphonenumber/build/utils.js"
      });
      	$("#phone").intlTelInput("selectCountry", "in");
      	
	$('#phone').on('keydown', function(e){
   
    if(e.which === 9) {
      send_forgot_otp();
       
    }
});


$('#verified_otp').on('keydown', function(e){
   
    if(e.which === 9) {
      forgot_verify_otp();
       
    }
});
	
   
         $("#otpbtn").click(function(event) {
			 send_forgot_otp();
		 });
		 function send_forgot_otp()
		 {
			 var loading = $('#loading').html();
			  $("#otpbtn>b").html(loading);
    var phone=$('#phone').val();
   
	 var phone=$('#phone').val();
	 var country=$('#phone').val().substring(0,2);
     var phonelen=$("#phone").val().length;
     
     var mob_ind = /^\+?([0-9]{2})\)?[1-9]{1}[0-9]{9}$/;
   var mob_us = /^\+?([0-9]{1})\)?[1-9]{1}[0-9]{9}$/;
   
   mob_num=document.getElementById("phone").value;
   var num_sequence1=mob_ind.test(mob_num);
   var num_sequence2=mob_us.test(mob_num);
   var mobile_validation=num_sequence1 || num_sequence2;
  
  
  
	if(document.getElementById("phone").value == "")
			{
		
		var send_otp = $('#send_otp').html();
		 $("#otpbtn>b").html(send_otp);
		 var please_enter_mobile_number = $('#please_enter_mobile_number').html();
		 $('.alert_bootstrap').show();
			$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+please_enter_mobile_number+'</div>');
				return false;
			}
			
			else if( phonelen < '13' && country=='+9' )
			{
				var send_otp = $('#send_otp').html();
		 $("#otpbtn>b").html(send_otp);
		 var please_enter_ten_digit_number = $('#please_enter_ten_digit_number').html();
		 $('.alert_bootstrap').show();
			$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+please_enter_ten_digit_number+'</div>');
		
	
				return false;
			}
			else if(phonelen < '12' && country=='+1' )
			{
				var send_otp = $('#send_otp').html();
		 $("#otpbtn>b").html(send_otp);
		
		 var please_enter_ten_digit_number = $('#please_enter_ten_digit_number').html();
		 $('.alert_bootstrap').show();
			$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+please_enter_ten_digit_number+'</div>');
		
				return false;
			}	
			
			else if(mobile_validation==false)
			{
				var send_otp = $('#send_otp').html();
			 $("#otpbtn>b").html(send_otp);
			
			 var invalid_mobile_number = $('#invalid_mobile_number').html();
			 $('.alert_bootstrap').show();
			$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+invalid_mobile_number+'</div>');
			
				return false;
			
			}
    
    
    
			else
			{
					event.preventDefault();
	      
		  
		 
		  
		   $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });

		    
		  
		   
   
   //alert("token has been generated");
					var phone=$("#phone").val();
					var device_uuid=$("#device_uuid").val();
					  	
//						alert("phone"+phone);
						var device_uuid = device.uuid;
//						alert("device_uuid"+device_uuid);
		
	   $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });

	var device_registered_in_portal=0;
	
	var url='http://183.82.97.23:8182/send_forgot_pin_otp/'+device_uuid+'/'+phone+'/'+device_registered_in_portal+'?_format=json';

//	alert(url);
	$.ajax({
            url:url,
            type: "get",
	  		dataType: "json",
            timeout:20000,
            crossDomain: true,
            error: function (jqXHR, textStatus, errorThrown) {
			  //alert("inside error");
            	var send_otp = $('#send_otp').html();
			   $("#otpbtn>b").html(send_otp);
              //alert(jqXHR.statusText);
			   var problem_connecting_with_server = $('#problem_connecting_with_server').html();
			   $('.alert_bootstrap').show();
				$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+problem_connecting_with_server+'</div>');
               
              },
              success: function (data) {
//				alert("Success Ajax");
//				alert(data.response);
            if(data.response=='0')
			   {
			   //alert("success");
            	var send_otp = $('#send_otp').html();
			   $("#otpbtn>b").html(send_otp);
			   var otp_sent_successfully = $('#otp_sent_successfully').html();
			   $('.alert_bootstrap').show();
				$(".alert_bootstrap").html('<div class="alert alert-success"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+otp_sent_successfully+'</div>');
               
			
             
			$('#otp').show();
		   $('#verified_otp').show();
			$('#pinbtn').show();
			
			   
			     }
			   else
			   {
			   // alert("Please Try Again");
				   var send_otp = $('#send_otp').html();
			   $("#otpbtn>b").html(send_otp);
			   var please_enter_registered_mobile_number = $('#please_enter_registered_mobile_number').html();
			   $('.alert_bootstrap').show();
				$(".alert_bootstrap").html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+please_enter_registered_mobile_number+'</div>');
               
			
			   }
			    
			  }
			  });
	        
		  
		  
		  
		  
		  
		  
		  

      }
		 }
      
      
     /*otp verification*/
     $("#pinbtn").click(function(event) {
		 forgot_verify_otp();
    });
      
      
  function forgot_verify_otp()
  {
	  var loading = $('#loading').html();
    $("#pinbtn>b").html(loading);
    var verified_otp=$('#verified_otp').val();
     //alert("verified_otp"+verified_otp);
    
   var device_uuid=$('#device_uuid').val();
    //alert("pin btn click device_uuid"+device_uuid);
    
    
	 //var phone=$('#phone').val();
	// var country=$('#phone').val().substring(0,2);
      var phonelen=$("#phone").val().length;
 // alert("phone"+phone);
  
  
	if(document.getElementById("verified_otp").value == "")
			{
		$("#pinbtn>b").html('Verify OTP');
		
		
		var please_enter_otp_and_click_on_verify_otp = $('#please_enter_otp_and_click_on_verify_otp').html();
		$('.alert_bootstrap').show();
		$(".alert_bootstrap").html('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+please_enter_otp_and_click_on_verify_otp+'</div>');
	
				return false;
			}
			
			
			
			else if(document.getElementById("verified_otp").value < 4)
			
			{
		$("#pinbtn>b").html('Verify OTP');
		
		var please_enter_four_digit_log_in_pin = $('#please_enter_four_digit_log_in_pin').html();
		$('.alert_bootstrap').show();
		$(".alert_bootstrap").html('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+please_enter_four_digit_log_in_pin+'</div>');
	
				return false;
			}
			else
			{
      
      
					
					event.preventDefault();
	      
		  
		 
		  
		   $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });

		    
		  
		
		
		
           	
           			
	
	   $.ajaxSetup({
        xhrFields: {
            withCredentials: true
        }
    });



		
				
		    
		
	
	     var verified_otp=$('#verified_otp').val();
    // alert("verified_otp finally"+verified_otp);
    
   var device_uuid=device.uuid;
    //alert("device uuid finally"+device_uuid);
    
  
var url='http://183.82.97.23:8182/forgot_pin_verify_otp/'+device_uuid+'/'+verified_otp+'?_format=json'; 
	
            $.ajax({
              url:url,
              type: "get",
	  		
      		 
              dataType: "json",
              timeout:20000,
              crossDomain: true,
              
			  error: function (jqXHR, textStatus, errorThrown) {
			  //alert("inside error");
              //alert(jqXHR.statusText);
             
			 $("#pinbtn>b").html('Verify OTP');
			 
			 var problem_connecting_with_server = $('#problem_connecting_with_server').html();
			   $('.alert_bootstrap').show();
				$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+problem_connecting_with_server+'</div>');
             
               
              },
              success: function (data) {
				//alert("Success Ajax");
            if(data.response=='0')
			   {
			  
			
			  // alert("otp verified successfully");  
			   
            	var otp_verified_successfully = $('#otp_verified_successfully').html();
 			   $('.alert_bootstrap').show();
 				$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+otp_verified_successfully+'</div>');
                
			    location.href="newpin.html?device_uuid="+device_uuid;
             
			
			
			   
			     }
			   else
			   {
				   $("#pinbtn>b").html('Verify OTP');
				   
					var otp_does_not_match = $('#otp_does_not_match').html();
		 			   $('.alert_bootstrap').show();
		 				$(".alert_bootstrap").html('<div class="alert alert-info"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+otp_does_not_match+'</div>');
		            
			   }
			    
			  }
			  });
			 

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
          url:" http://183.82.97.23:8182/services/session/token",
          type:"get",
          dataType:"text",
           crossDomain: true,
          error:function (jqXHR, textStatus, errorThrown) {
			
          },
          success: function (token) {   
 
   
	var token =token;
	var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
	$.ajax({
		 url:" http://183.82.97.23:8182/m_service/user/logout",		 
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
		});
	
	
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
      
      
 
      
       });
 
 function onBackKeyDown() {
	
	 

		
	 var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});

	 db.query('mobile_app/sort_by_devices').then(function (res) {
	 	
	 	

	 	var phone =res.rows[0].value.phone;
	 	var pin =res.rows[0].value.pin;
	  // alert(phone+'--'+pin);
	     window.location='./login_existing.html?phone='+phone+'&encrypted_pin='+pin;


	   // index was built!
	 }).catch(function (err) {
	 // alert("some error");
	 });

// window.location='./login_existing.html';
 
}
	  
   
   




