import java.io.IOException;

import xrtml.Config;
import xrtml.channel.Channel;
import xrtml.connection.Connection;
import xrtml.connection.events.OnSubscribeEvent;
import xrtml.connection.interfaces.IOnSubscribe;
import xrtml.connectionmanager.ConnectionManager;


public class PostMessage {

	
	public static void main(String[] args) {

		// In this example we will show how to send a message without the need of a connection.
		
		// To start we active the debug to see some useful logs.
		Config.setDebug(true);

		// Then we get the ConnectionManager singleton.
		final ConnectionManager connectionManager = ConnectionManager.getInstance();
		
		// We will use a connection just to receive the message that will be sent via POST request.
		Connection myConnection = connectionManager.create("myConnection", "myAppKey", "myAuthToken", "http://ortc-developers.realtime.co/server/2.1");
		myConnection.createChannel(new Channel("myChannel"));
		
		// When the channel becomes subscribed, we are ready to post the message.
		myConnection.bind(new IOnSubscribe() {
			
			@Override
			public void run(OnSubscribeEvent arg0) {
				try {
					// At this point we can send a message.
					connectionManager.postMessage("myChannel", "MyTestMessage", "http://ortc-developers.realtime.co/server/2.1", true, "myAuthToken", "myAppKey", "myPrivateToken");
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		});
		
		// Establish the connection.
		myConnection.connect();
	}

}