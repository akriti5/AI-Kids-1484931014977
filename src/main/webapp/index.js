// index.js


// request message on server
//Calls SimpleServlet to get the "Hello World" message
function addItem(){
	var name='?name='+document.getElementById('name').value;
	
xhrGet("SimpleServlet"+name, function(responseText){
	// add to document
	alert(responseText);
	document.body.style.background = 'url(images/spider.jpg) '
	
	//document.body.style.background = 'url(images/'+responseText+'.jpg) '
	document.getElementById('div1').style.visibility = 'hidden';  
	document.getElementById('div1').style.display = 'none';      
	
	//var mytitle = document.getElementById('age');
	//mytitle.value = responseText;

}, function(err){
	console.log(err);
});

}


//utilities
function createXHR(){
	if(typeof XMLHttpRequest != 'undefined'){
		return new XMLHttpRequest();
	}else{
		try{
			return new ActiveXObject('Msxml2.XMLHTTP');
		}catch(e){
			try{
				return new ActiveXObject('Microsoft.XMLHTTP');
			}catch(e){}
		}
	}
	return null;
}
function xhrGet(url, callback, errback){
	
	var xhr = new createXHR();
	xhr.open("GET", url, true);
	xhr.onreadystatechange = function(){
		//alert(xhr.readyState)
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				callback(xhr.responseText);
			}else{
				errback('service not available');
			}
		}
	};
	xhr.timeout = 3000;
	xhr.ontimeout = errback;
	xhr.send();
}
function parseJson(str){
	return window.JSON ? JSON.parse(str) : eval('(' + str + ')');
}
function prettyJson(str){
	// If browser does not have JSON utilities, just print the raw string value.
	return window.JSON ? JSON.stringify(JSON.parse(str), null, '  ') : str;
}

