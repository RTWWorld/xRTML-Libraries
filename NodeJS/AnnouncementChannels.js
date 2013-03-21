// In this example we show how use the AnnoucementChannels.

// First we need to require the xRTML module.
// Install it via Node Package Manager and then require it
var xRTML = require("xRTML");

var connection = xRTML.ConnectionManager.create({
    // ORTC server url.
    url: "http://ortc-developers.realtime.co/server/2.1",
    // The id of the connection.
    id: "DashBoard-Connection",
    // The application key.
    appKey: "myAppKey",
    // The authentication token.
    authToken: "myAuthToken",
    // The array of channels to subscribe. Each of them have a "message" event listener to process the messages that arrive.
    channels:[{
        // The name of the channel to subscribe.
        name: "ortcClientConnected",
        // The function to process the arrived messages.
        onMessage: function(e) {
            // Since this is a handler to a announcement channel, we will only receive metadata in this channel.
            // We will parse it to extract the information we need.
            console.log("Client Connected.");
            console.log(e.message);
        }
    }, { 
        // The name of the channel to subscribe.
        name: "ortcClientDisconnected",
        // The function to process the arrived messages.
        onMessage: function(e) {
            // Since this is a handler to a announcement channel, we will only receive metadata in this channel.
            // We will parse it to extract the information we need.
            console.log("Client Disconnected.");
            console.log(e.message);
        }
    }, {
        // The name of the channel to subscribe.
        name: "ortcClientSubscribed",
        // The function to process the arrived messages.
        onMessage: function(e) {
        	// Since this is a handler to a announcement channel, we will only receive metadata in this channel.
            // We will parse it to extract the information we need.
            console.log("Channel Subscribed.");
            console.log(e.message);
        }
    }, { 
        // The name of the channel to subscribe.
        name: "ortcClientUnsubscribed",
        // The handler to process the arrived messages.
        onMessage: function(e) {
			// Since this is a handler to a announcement channel, we will only receive metadata in this channel.
            // We will parse it to extract the information we need.
            console.log("Channel Unsubscribed.");
        	console.log(e.message);
        }
    }]
});

// Now every time a user connects, disconnects, subscribes or unsubscribes a channel you will get a console log.
// With this simple example we covered the Annoucement Channels.