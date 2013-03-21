// In this example we show how to create a connection, bind event listeners to the connection and send messages.

// First we need to require the xRTML module.
// Install it via Node Package Manager and then require it
var xRTML = require("xRTML");

// One of the modules that xRTML contains is the ConnectionManager. The Connection manager module contains the functions to create and manage connections.
// So we are using it to create a simple connection.
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
    channels: [{name: 'myChannel', subscribe: false }]
});

// Every connection hold the event mechanism, so we can bind/unbind event listeners to them.
// Here is a simple example that shows how to do it.
myConnection.bind({
	// type and the listener function
	connect: function(e) {
		// At this point the connection is established. Every connection must contain at least a channel subscribed to receive messages.
		// As you may have realized, when we  we set the subscribe value of the channel "myChannel" intentionally to false.
		// Now we are going to bind some event listeners and subscribe it.

		// We will bind to the event subscribe and to the event message.
		this.channels.myChannel.bind({			
			subscribe: function(e){

				console.log("Channel [" + this.name +"] is subscribed.");

				// Now that we have a subscribed channel, we are going to send a message to that channel.
				myConnection.send({
					channel: "myChannel",
					content: "MyTestMessage"
				});
			},
			message: function(e){
				// At this point, a message has arrived to the channel.
				console.log("A message has arrived to the channel [" + this.name + "]");
				console.log(e.message);
			}
		});

		this.subscribe({name: "myChannel"});
	}
});
// And thats it. We covered the basic connection steps.