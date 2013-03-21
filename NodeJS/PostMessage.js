// In this example we will show how to send a message without the need of a connection.

// First we need to require the xRTML module.
// Install it via Node Package Manager and then require it.
var xRTML = require("xRTML");

// We will use a connection just to receive the message that will be sent via POST request.
var myConnection = xRTML.ConnectionManager.create({
	// Here we specify the url of the ORTC server.
    url: "http://ortc-developers.realtime.co/server/2.1",
    // The id of the connection.
    id: "myConnection",
    // The application key 
    appKey: 'myAppKey',
    // The authentication token
    authToken: 'myAuthToken',
    // The list of channels assigned to the connection.
    channels: [{
    	name: 'myChannel', 
    	onMessage: function(e) {
    		// Do something with the message
    	}
	}],
	onSubscribe: function(e){
		// Now that we have a channel subscribed, we can POST the message to the server.
		xRTML.ConnectionManager.postMessage({
			// The url of the server to send the message.
			host:'http://ortc-developers.realtime.co/server/2.1',
			// Defines if the url is of a server cluster.
			isCluster: true,
			// The application key.
			appKey: "myAppKey",
			// The authentication token. Optional, but if not used a private key will be required.
			authToken: "myAuthToken",
			// The private key. Optional if a token is specified.
			privateKey: "myPrivateKey",
			// The channel to send.
			channel: "myChannel",
			// The message to send.
			message: "myMessage"
		});
	}
});