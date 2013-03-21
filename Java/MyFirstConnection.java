import xrtml.Config;
import xrtml.Console;
import xrtml.channel.Channel;
import xrtml.channel.events.OnMessageEvent;
import xrtml.channel.events.OnSubscribeEvent;
import xrtml.channel.interfaces.IOnMessage;
import xrtml.channel.interfaces.IOnSubscribe;
import xrtml.connection.Connection;
import xrtml.connection.events.OnConnectEvent;
import xrtml.connection.interfaces.IOnConnect;
import xrtml.connectionmanager.ConnectionManager;

public class MyFirstConnection {

	public static void main(String[] args) {

		// This example shows how to create a connection, bind event listeners to it and send messages.

		// To start we active the debug to see some useful logs.
		Config.setDebug(true);

		// Then we get the ConnectionManager singleton.
		ConnectionManager connectionManager = ConnectionManager.getInstance();

		// Now we create a connection.
		final Connection myConnection = connectionManager.create("myConnection", "myAppKey", "myAuthToken", "http://ortc-developers.realtime.co/server/2.1");

		// Here we define a listener for the connect event.
		myConnection.bind(new IOnConnect() {
			@Override
			public void run(OnConnectEvent evt) {

				// In the connect event we want to subscribe the channel "myChannel"
				((Connection) evt.getSource()).subscribe("myChannel");
			}
		});

		// Here we create a channel.
		final Channel myChannel = new Channel("myChannel");
		// We set the subscribe flag to false so it does not subscribe automatically.
		myChannel.setSubscribe(false);

		// Here we define a listener to the subscribe channel event and a listener to the message channel event
		myChannel.bind(new IOnSubscribe() {
			@Override
			public void run(OnSubscribeEvent evt) {
				myConnection.send("myChannel", "MyTestMessage");
			}
		});

		myChannel.bind(new IOnMessage() {
			@Override
			public void run(OnMessageEvent evt) {
				Console.info(evt.getSource().getClass(), evt.getMessage());
			}
		});

		// Then we associate the channel to the connection.
		myConnection.createChannel(myChannel);
		// And finally we connect.
		myConnection.connect();
	}
}