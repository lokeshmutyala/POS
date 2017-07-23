$(document).ready(function(){


 document.addEventListener("deviceready", onDeviceReady, false);
});
	
	
	function onDeviceReady()
{
		
		
     var db = new PouchDB('medical_dictionary', {auto_compaction: true,adapter: 'websql', iosDatabaseLocation: 'default'});
 
     db.query('mobile_app/sort_by_devices').then(function (res) {
            var id = res.rows[0].id;
            db.get(id).then(function (doc) {
$("#language").val(doc.user_profile_data['userdetails'].language);            
            
            

var langCode = $("#language").val();
var langJS = null;


var translate = function (jsdata)
{	
	$("[tkey]").each (function (index)
	{
		var strTr = jsdata [$(this).attr ('tkey')];
		$(this).attr ('placeholder',strTr);
		$(this).attr ('value',strTr);
		$(this).attr ('option',strTr);
	    $(this).html (strTr);
	});
}

if(langCode){
	
	$.getJSON('ajax/json_docs/lang/'+langCode+'.json', translate);
        }
        else
        {
        	
	$.getJSON('ajax/json_docs/lang/English.json', translate);            
        }    
            
            
            
            
            });

            // index was built!
        }).catch(function (err) {
            			  
            			  var translate = function (jsdata)
            			  {	
            			  	$("[tkey]").each (function (index)
            			  	{
            			  		var strTr = jsdata [$(this).attr ('tkey')];
            			  		$(this).attr ('placeholder',strTr);
    				    		$(this).attr ('value',strTr);
    				    		$(this).attr ('option',strTr);
            			  	    $(this).html (strTr);
            			  	});
            			  }

            			
            			  	$.getJSON('ajax/json_docs/lang/English.json', translate);            
            			           
        });



        //alert(mode);
        return false;

}
    
    

