// In this example we show how authenticate a token. The authenticated token can be used to create a connection.

// First we need to require the xRTML module.
// Install it via Node Package Manager and then require it.
var xRTML = require("xRTML");

// The authentication of tokens is only neeeded for production servers.
xRTML.ConnectionManager.saveAuthentication({
	// ORTC production server url.
    //url: "http://ortc-prd.realtime.co/server/2.1",
    url: "http://ortc-developers.realtime.co/server/2.1",
    // Defines if is a cluster url.
    isCluster: true,
    // The token to authenticate.
    authToken: "myAuthToken",
    // Defines if the token is only to be used once.
    // If true, after a connection uses the token, it will become useless.
    isPrivateKey: false,
    // The application key.
    appKey: "myAppKey",
    // The interval of time that the token is valid.
    timeToLive: 3600,
    // The private key.
    privateKey: "myPrivateToken",
    // The permissions given to the token.
    // With this permissions, the connection that takes this token will only be available to receive messages through the the channel "myChannel"
    permissions: { "myChannel": "r"},
    // The callback to run with the response of the server.
    callback: function(result) {
    	// 	The formats of the response are: 
    	//		{ error: 'Not authorized. Reason: Not valid', success: false }
    	//	or
    	//		{ error: null, success: true }	

    	// 	Here we can create a connection if the response is positive.
    }
});
// With this example we show the basic steps of how to authenticate tokens for connections.