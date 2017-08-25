/**
 * 
 */
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
  
  
  
  const messaging = firebase.messaging();
  messaging.requestPermission().then(function () {
									  console.log("Have permission");
									  console.dir(messaging.getToken());
									  return messaging.getToken();
  }).catch(function (err) {
	  console.log("Error dccured.");
  })
  
  messaging.onMessage(function(payload){
	  console.log('onMessage: ', payload)
  });