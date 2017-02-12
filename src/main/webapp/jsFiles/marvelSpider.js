


var canvas, ctx, flag = false,
prevX = 0,
currX = 0,
prevY = 0,
currY = 0,
dot_flag = false;

var canvasImg;

var x = "green",
y = 2;

function qzShow(){
	 document.getElementById("div1").style.display = 'block';
	 document.getElementById("can").style.display = 'block';
	 document.getElementById("div2").style.display = 'none';
	 document.getElementById('play').play();
}


function init() {
	carousel();
	canvas = document.getElementById('can');
	ctx = canvas.getContext("2d");
	w = canvas.width;
	h = canvas.height;
	
	canvas.addEventListener("mousemove", function (e) {
	    findxy('move', e)
	}, false);
	canvas.addEventListener("mousedown", function (e) {
	    findxy('down', e)
	}, false);
	canvas.addEventListener("mouseup", function (e) {
	    findxy('up', e)
	}, false);
	canvas.addEventListener("mouseout", function (e) {
	    findxy('out', e)
	}, false);
}

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    //alert('yes');
    setTimeout(carousel,500); // Change image every 2 seconds
}

function color(obj) {
switch (obj.id) {
    case "green":
        x = "green";
        break;
    case "blue":
        x = "blue";
        break;
    case "red":
        x = "red";
        break;
    case "yellow":
        x = "yellow";
        break;
    case "orange":
        x = "orange";
        break;
    case "black":
        x = "black";
        break;
    case "white":
        x = "white";
        break;
}
if (x == "white") y = 14;
else y = 2;

}

function draw() {
ctx.beginPath();
ctx.moveTo(prevX, prevY);
ctx.lineTo(currX, currY);
ctx.strokeStyle = x;
ctx.lineWidth = y;
ctx.stroke();
ctx.closePath();
}

function erase() {

    ctx.clearRect(0, 0, w, h);
    

}

function save() {
document.getElementById("canvasimg").style.border = "2px solid";
var dataURL = canvas.toDataURL();
document.getElementById("canvasimg").src = dataURL;
document.getElementById("canvasimg").style.display = "inline";
}

function func(){
	
	canvasImg="circle"+Math.floor((Math.random() * 10) + 1)+".png";
	document.getElementById("btn").download=canvasImg;
	alert("inside func"+document.getElementById("btn").download);
	var dt=c.toDataURL();
	this.href=dt;
	//servercall();
}

function findxy(res, e) {
if (res == 'down') {
    prevX = currX;
    prevY = currY;
    currX = e.clientX - canvas.offsetLeft;
    currY = e.clientY - canvas.offsetTop;

    flag = true;
    dot_flag = true;
    if (dot_flag) {
        ctx.beginPath();
        ctx.fillStyle = x;
        ctx.fillRect(currX, currY, 2, 2);
        ctx.closePath();
        dot_flag = false;
    }
}
if (res == 'up' || res == "out") {
    flag = false;
}
if (res == 'move') {
    if (flag) {
        prevX = currX;
        prevY = currY;
        currX = e.clientX - canvas.offsetLeft;
        currY = e.clientY - canvas.offsetTop;
        draw();
    }
}
}

function download(){
var dt=canvas.toDataURL();
this.href=dt;
}

document.getElementById("btn").addEventListener("click",download,false); 

///ajax request

function testingVR(){
	alert('inside servercall')
	var name='?name='+canvasImg;
	alert(name);
	xhrGet("VisualReg"+name, function(responseText){		
	alert('responseText');
	

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

