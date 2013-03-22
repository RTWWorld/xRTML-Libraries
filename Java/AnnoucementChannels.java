import xrtml.channel.Channel;
import xrtml.channel.events.OnMessageEvent;
import xrtml.channel.interfaces.IOnMessage;
import xrtml.connection.Connection;
import xrtml.connectionmanager.ConnectionManager;

public class AnnouncementChannels {

	public static void main(String[] args) {
		
		// In this example we will show how to use the announcement channels.

		// First we get the ConnectionManager singleton.
		ConnectionManager connectionManager = ConnectionManager.getInstance();

		// Then we create a connection.
		Connection myConnection = connectionManager.create("myConnection", "myAppKey", "myAuthToken", "http://ortc-developers.realtime.co/server/2.1");

		// After the connection creation we will add the announcement channels to it. Keep in mind that they are special read-only channels.		
		
		// This channel will receive a message every time a connection becomes established.
		Channel ortcClientConnected = new Channel("ortcClientConnected");
		ortcClientConnected.bind(new IOnMessage() {

			@Override
			public void run(OnMessageEvent evt) {
				System.out.println("Message Received at channel: [" + ((Channel) evt.getSource()).getName() + "]");
				System.out.println(evt.getMessage());

			}
		});
		myConnection.createChannel(ortcClientConnected);

		// This channel will receive a message every time a connection is lost.
		Channel ortcClientDisconnected = new Channel("ortcClientDisconnected");
		ortcClientDisconnected.bind(new IOnMessage() {

			@Override
			public void run(OnMessageEvent evt) {
				System.out.println("Message Received at channel: [" + ((Channel) evt.getSource()).getName() + "]");
				System.out.println(evt.getMessage());

			}
		});
		myConnection.createChannel(ortcClientDisconnected);

		// This channel will receive a message every time a connection subscribes a channel.
		Channel ortcClientSubscribed = new Channel("ortcClientSubscribed");
		ortcClientSubscribed.bind(new IOnMessage() {

			@Override
			public void run(OnMessageEvent evt) {
				System.out.println("Message Received at channel: [" + ((Channel) evt.getSource()).getName() + "]");
				System.out.println(evt.getMessage());

			}
		});
		myConnection.createChannel(ortcClientSubscribed);

		// This channel will receive a message every time a connection unsubscribes a channel.
		Channel ortcClientUnsubscribed = new Channel("ortcClientUnsubscribed");
		ortcClientUnsubscribed.bind(new IOnMessage() {

			@Override
			public void run(OnMessageEvent evt) {
				System.out.println("Message Received at channel: [" + ((Channel) evt.getSource()).getName() + "]");
				System.out.println(evt.getMessage());

			}
		});
		myConnection.createChannel(ortcClientUnsubscribed);

		
		// Now that we have added every channel to the connection we will establish the connection.
		myConnection.connect();
		
		// To see logs you will have to connect/disconnect or subscribe/unsubscribe other connections and channels examples.
	}

}