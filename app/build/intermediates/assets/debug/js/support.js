	var pending_synchronize_count = '';
        
        
        $(document).ready(function(){
            $(".preloader-wrapper").hide();
            $(".sync_progress").hide();
             $("#sync_pending_count").html('0');
    document.addEventListener("deviceready",onDeviceReady,false);
});
		 
	 
	 		function onDeviceReady() {
                            document.addEventListener("backbutton", onBackKeyDown, false);
}

function send_emails()
           {


            var body = $(".address").val();
            if(body == "")
            {
               Materialize.toast('Please type body of email', 4000);
            }
            else
            {
            var link = "mailto:karthikrouthu93@gmail.com"
             + "?cc=routhukarthik93@gmail.com"
             + "&body=" + escape(body);
//alert("Link: "+link);
    window.location.href = link;
           //window.open('mailto:nearhereindia@gmail.com?subject='+subject+'&body='+body);
}
           }

function mail_us()
{
   alert("func");
    window.location='./send_mail.html';
    
}

function contact()
{
   
    Materialize.toast('We received your request. Will contact you shortly. Thankyou', 4000);
}



function onBackKeyDown()
	 		{
                            var exit_flag = $("#exit_flag").val();
//                            alert("Exit Flag: "+exit_flag);
                            if(exit_flag == "0")
                            {
	 			navigator.app.exitApp();
                            }
                            else
                            {
                                Materialize.toast('Please wait till Data Synchronization is completed', 4000);   
                            }
	 		}
   