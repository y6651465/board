<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://www.gstatic.com/firebasejs/4.2.0/firebase.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>




<a href='#' id="dnperm">Reuqest Permission</a><br><br>
Username: <input type="text" id="username"/><br>
Message: <input type="text" id="message"/><br>
<input type="button" id="btnGetMessage" value="Get Message" /><br>
<ul id="comment"></ul>

<script src="https://www.gstatic.com/firebasejs/4.1.1/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/4.1.1/firebase-database.js"></script>
<script src="https://www.gstatic.com/firebasejs/4.1.1/firebase-messaging.js"></script>
<script src="https://www.gstatic.com/firebasejs/4.2.0/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCCz1QBgdGHOMtDsNBuVdP0vV4AEzMWSwQ",
    authDomain: "fmc-test-1efbc.firebaseapp.com",
    databaseURL: "https://fmc-test-1efbc.firebaseio.com",
    projectId: "fmc-test-1efbc",
    storageBucket: "fmc-test-1efbc.appspot.com",
    messagingSenderId: "117555202324"
  };
  firebase.initializeApp(config);
  var dnperm = $('#dnperm');
  
  $('#dnperm').click(function(e){
    e.preventDefault();
 if(!window.Notification){
    alert('NOT supported');
 }else{
    Notification.requestPermission().then(function(p){
       if(p == 'denied'){
          alert('you denied to show');
       }else if(p == 'granted'){
          alert('you allowed to show');
       }
    });
 }
 });
 
 function writeUserData(name, message) {
    database.push().set({
       username: name,
       message: message
    });
 }
 
 function renderUI(obj){
    var html='';
    
    var keys = Object.keys(obj);
    for(var i=0;i<keys.length;i++){
       html += "<li><b><i>" + obj[keys[i]].username+"</i></b> says: "+obj[keys[i]].message+"</li>";
    }
    $('#comment').html(html);
 }
 
 $('#btnGetMessage').click(function(){
    writeUserData($('#username').val(),$('#message').val());
    $('#username').val('');
    $('#message').val('');
 });
 var database = firebase.database().ref().child("users");
 
 database.on('value', function(snapshot){
    renderUI(snapshot.val());
 });
 
 database.on('child_added', function(data) {
    if(Notification.permission != 'default'){
       var notify;
       notify = new Notification('New Message from' + data.val().username,{
          body: data.val().message,
          icon: "resources/images/ok.jpg",
          tag: data.getKey
          
       });
   
       notify.onclick = function() {
          alert(this.tag);
       }
    }else{
       alert('please allow');
    }
 });


  
  
</script>
</body>
</html>