// index.js

var responseVal;

function myFunction() {
	
    var popup = document.getElementById("myPopup");    
    popup.classList.toggle("show");
   document.getElementById("div3").style.display = 'block';
}


// request message on server
//Calls SimpleServlet to get the "Hello World" message
function addItem(){
	var name='?name='+document.getElementById('name').value;
	
	xhrGet("SimpleServlet"+name, function(responseText){
		
	responseVal=responseText;	
	document.body.style.background = 'url(images/'+responseText+'.jpg)'
	document.body.style.backgroundSize = "cover";
	document.getElementById('div1').style.visibility = 'hidden';  
	document.getElementById('div1').style.display = 'none'; 
	document.getElementById("div2").innerHTML="Help us "+document.getElementById('name').value+" !";
	if(responseText=="party")
	document.getElementById("myPopup").innerHTML="While making the dollhouse we forget homework. Solve it for us princess";
	 document.getElementById("div2").style.display = 'block';
	 

}, function(err){
	console.log(err);
});

}



function navigate(){
	if (responseVal=='marvel')
		window.location.href = 'ShapesQuiz.html';
	else 
		window.location.href = 'ShapeQuiz.html';
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

