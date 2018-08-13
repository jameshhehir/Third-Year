package server;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Server extends JFrame {

	private static final long serialVersionUID = 123L;
	private static int PORT = 9001;
	private static ArrayList<String> nameList = new ArrayList<String>();
	private static ArrayList<PrintWriter> writerList = new ArrayList<PrintWriter>();

	private static String userdir = System.getProperty("user.dir");

	static int numWindows = 0;

	public static JTextArea messages = new JTextArea(8, 60);
	private JFrame frame;

	public static void main(String args[]) throws Exception {
		Server s = new Server();
		s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.frame.setVisible(true);
		s.frame.setSize(400, 600);
		s.runServer(PORT);
	}

	public Server() {
		super("Server Window");
		frame = this;
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));

		JPanel messagePane = new JPanel();
		messagePane.setLayout(new BoxLayout(messagePane, BoxLayout.LINE_AXIS));

		messagePane.add(Box.createRigidArea(new Dimension(10, 10)));
		messagePane.add(messages);
		messagePane.add(Box.createRigidArea(new Dimension(10, 10)));
		messages.setEditable(false);
		messages.setPreferredSize(new Dimension(400, 400));

		c.add(Box.createRigidArea(new Dimension(10, 10)));
		c.add(messagePane);
		c.add(Box.createRigidArea(new Dimension(400, 100)));

		messagePane.setBackground(Color.GRAY);
		c.setBackground(Color.GRAY);
		messages.append("Server Started. \n");
		setSize(400, 600);
	}

	public void runServer(int port) throws Exception {

		ServerSocket listener = new ServerSocket(port);

		try {
			new Handler(listener.accept()).start();
			numWindows++;
			System.out.println(numWindows);
		} finally {
			listener.close();
		}

	}

	public static String getUserdir() {
		return userdir;
	}

	public static void setUserdir(String userdir) {
		Server.userdir = userdir;
	}

	private static class Handler extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		public Handler(Socket s) {
			socket = s;
		}

		private InputStream is;
		byte[] imageArray;
		BufferedImage image;

		public void run() {
			is = null;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				is = socket.getInputStream();

				while (true) {

					out.println("SubmitName");
					name = in.readLine();

					if (name == null)
						return;

					synchronized (nameList) {
						if (!nameList.contains(name)) {

							nameList.add(name);
							break;
						} else {
						}
					}
				}

				out.println("FinishSubmitName");
				writerList.add(out);
				String address = in.readLine();
				if (address.startsWith("/"))
					messages.append(address + " has connected. \n");

				while (true) {

					String input = in.readLine();
					if (input == null) {
						return;
					} else {

						if (!input.startsWith("StoreImage")) {
							if (!input.startsWith("Upload")) {
								for (int i = 0; i < writerList.size(); i++) {
									PrintWriter writer = writerList.get(i);
									writer.println("Message " + name + ": " + input);
								}
							} else {

								messages.append("Received " + image.getHeight() + "x" + image.getWidth() + "\n");
								ImageIO.write(image, "jpg", new File("image" + System.currentTimeMillis() + ".jpg"));

								imageArray = null;
								image = null;
							}
						} else {

							imageArray = new byte[50000000];
							is.read(imageArray);
							image = ImageIO.read(new ByteArrayInputStream(imageArray));

						}
					}
				}
			} catch (IOException e) {
				System.out.println(e);

			} finally {
				if (name != null)
					nameList.remove(name);
				if (out != null)
					writerList.remove(out);
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}

	}

}