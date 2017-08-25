/**
 * 
 */
importScripts("https://www.gstatic.com/firebasejs/4.2.0/firebase-app.js"); 
importScripts("https://www.gstatic.com/firebasejs/4.2.0/firebase-messaging.js");


var config = {
	    apiKey: "AIzaSyCCz1QBgdGHOMtDsNBuVdP0vV4AEzMWSwQ",
	    authDomain: "fmc-test-1efbc.firebaseapp.com",
	    databaseURL: "https://fmc-test-1efbc.firebaseio.com",
	    projectId: "fmc-test-1efbc",
	    storageBucket: "fmc-test-1efbc.appspot.com",
	    messagingSenderId: "117555202324"
	  };
	  firebase.initializeApp(config);
	  
const messaging = firevase.messaging();