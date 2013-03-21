import ibt.ortc.api.ChannelPermissions;

import java.util.HashMap;

import xrtml.Config;
import xrtml.Console;
import xrtml.connectionmanager.ConnectionManager;

public class Authenticate {

	public static void main(String[] args) {

		// This example shows how to authenticate tokens used in the connections.

		// To start we active the debug to see some useful logs.
		Config.setDebug(true);

		// Then we get the ConnectionManager singleton.
		ConnectionManager connectionManager = ConnectionManager.getInstance();

		// Here we create a HashMap with the channel permissions.
		HashMap<String, ChannelPermissions> permissions = new HashMap<String, ChannelPermissions>();

		// Then we add read and write permissions to the channel "myChannel"
		permissions.put("myChannel", ChannelPermissions.Write);

		// Finally we call the saveAuthentication method to authenticate the token desired.
		if (connectionManager.saveAuthentication("http://ortc-developers.realtime.co/server/2.1", true, "myAuthToken", false, "myAppKey", 3600, "myPrivateKey",	permissions)) {
			Console.debug(connectionManager.getClass(), "Authentication Sucessfull");
		} else {
			Console.debug(connectionManager.getClass(), "Authentication Error");
		}

	}

}
