package client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Client extends JFrame {
	private static final long serialVersionUID = 123L;
	private final InetAddress serverAddress;
	private static int PORT = 9001;
	public BufferedReader in;
	public PrintWriter out;
	public OutputStream outputStream;

	private JFrame frame;
	private JTextField inputField;
	private JButton send;
	private JTextArea messages;
	private JLabel label;
	private Icon icon = new ImageIcon(
			new ImageIcon("image.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
	private JButton setting;
	private JRadioButton image;
	private JButton upload;
	private Color bg_color = Color.BLACK;

	private Icon iconUpload;

	public Client() throws Exception, IOException {
		super("Chat Window ");
		frame = this;
		Container c = getContentPane();
		serverAddress = InetAddress.getLocalHost();

		JPanel messagePanel = new JPanel();
		messages = new JTextArea(30, 30);
		messagePanel.add(messages);

		JScrollPane scrollableMessagePanel = new JScrollPane(messagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollableMessagePanel.getViewport().setPreferredSize(new Dimension(300, 500));
		scrollableMessagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		label = new JLabel(icon);
		send = new JButton("Send");
		inputField = new JTextField(20);
		JPanel inputsPanel1 = new JPanel();
		inputsPanel1.setLayout(new BoxLayout(inputsPanel1, BoxLayout.LINE_AXIS));
		inputsPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		inputsPanel1.add(label);
		inputsPanel1.add(Box.createRigidArea(new Dimension(10, 10)));
		inputsPanel1.add(inputField);
		inputsPanel1.add(Box.createRigidArea(new Dimension(10, 10)));
		inputsPanel1.add(send);

		setting = new JButton("Setting");
		image = new JRadioButton("Image");
		upload = new JButton("Upload");
		JPanel inputsPanel2 = new JPanel();
		inputsPanel2.setLayout(new BoxLayout(inputsPanel2, BoxLayout.LINE_AXIS));
		inputsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		inputsPanel2.add(setting);
		inputsPanel1.add(Box.createRigidArea(new Dimension(10, 10)));
		inputsPanel2.add(image);
		inputsPanel1.add(Box.createRigidArea(new Dimension(10, 10)));
		inputsPanel2.add(upload);

		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		c.add(scrollableMessagePanel);
		c.add(inputsPanel1);
		c.add(inputsPanel2);

		messages.setEditable(false);
		inputField.setEditable(false);

		inputField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				out.println(inputField.getText());
				inputField.setText("");
			}
		});

		send.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				out.println(inputField.getText());
				inputField.setText("");
			}
		});

		setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
				bg_color = color;
				scrollableMessagePanel.setBackground(bg_color);
				inputsPanel1.setBackground(bg_color);
				inputsPanel2.setBackground(bg_color);
				c.setBackground(bg_color);

				c.repaint();
			}
		});

		image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				try {
					File file = new File(filename);
					BufferedImage image = ImageIO.read(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(image, "jpg", baos);

					out.println("StoreImage");
					outputStream.write(baos.toByteArray());
					outputStream.flush();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
				}

			}

		});

		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Upload");
			}

		});

		Random r = new Random();
		Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		bg_color = color;

		scrollableMessagePanel.setBackground(bg_color);
		inputsPanel1.setBackground(bg_color);
		inputsPanel2.setBackground(bg_color);
		c.setBackground(bg_color);

		scrollableMessagePanel.setOpaque(false);
		inputsPanel1.setOpaque(false);
		inputsPanel2.setOpaque(false);
		setSize(400, 600);
		pack();
	}

	private String getUsername() {
		int selection = JOptionPane.showConfirmDialog(frame, "Enter as Anonymous?", "Login Page",
				JOptionPane.YES_NO_OPTION);

		switch (selection) {
		case JOptionPane.YES_OPTION:
			Random r = new Random();
			return "Anonymous" + r.nextInt(1024);
		default:
			return JOptionPane.showInputDialog(frame, "Enter name", "", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void run() throws IOException {
		@SuppressWarnings("resource")
		Socket socket = new Socket(serverAddress, PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		outputStream = socket.getOutputStream();

		while (true) {
			String line = in.readLine();
			if (!line.startsWith("SubmitName")) {
				if (!line.startsWith("FinishSubmitName")) {
					if (line.startsWith("Message"))
						messages.append(line.substring(8) + "\n");
				} else {
					inputField.setEditable(true);
					messages.append("Welcome to the Chat Room >.> \n");
					out.println("/" + serverAddress);

				}
			} else {
				out.println(getUsername());

				out.println("");

			}

		}

	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setVisible(true);
		client.run();
	}

	public Icon getIconUpload() {
		return iconUpload;
	}

	public void setIconUpload(Icon iconUpload) {
		this.iconUpload = iconUpload;
	}
}
