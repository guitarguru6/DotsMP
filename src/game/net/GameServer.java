package game.net;

import game.game.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@SuppressWarnings("unused")
public class GameServer extends Thread {
	private DatagramSocket socket;
	private Component component;

	public GameServer(Component component) {
		this.component = component;
		try {
			this.socket = new DatagramSocket(13331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			System.out.println("CLIENT [" + packet.getAddress().getHostAddress() + " : " + packet.getPort() + "]> " + message);
			if (message.trim().equalsIgnoreCase("ping")) {
				try {
					sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port) throws UnknownHostException {
		DatagramPacket packet = null;
		packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
