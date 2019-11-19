import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

public class ServerChatUI extends JFrame {

    Socket socket;

    public ServerChatUI(Socket socket) {
        this.socket = socket;
        setFrame(createUI());
        runClient();
    }

    public JPanel createUI() {
        ServerChatUI.Controller controller = new ServerChatUI.Controller();
        JPanel main = new JPanel(new BorderLayout());

        JPanel message = new JPanel(new BorderLayout());
        TitledBorder messageTitle = new TitledBorder("MESSAGE");
        messageTitle.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.BLACK));
        messageTitle.setTitle("MESSAGE");
        message.setBorder(messageTitle);
        JTextField messageField = new JTextField();
        messageField.setText("Type Message");
        message.add(messageField, BorderLayout.CENTER);

        JButton send = new JButton("Send");
        send.addActionListener(controller);
        send.setActionCommand("Send");
        send.setEnabled(false);
        send.setOpaque(true);
        message.add(send, BorderLayout.EAST);

        main.add(message, BorderLayout.NORTH);

        /*
        The chat display components
         */
        JPanel chatDisplay = new JPanel(new BorderLayout());
        TitledBorder chatTitle = new TitledBorder("CHAT DISPLAY");
        chatTitle.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.BLUE));
        chatTitle.setTitle("CHAT DISPLAY");
        chatTitle.setTitleJustification(TitledBorder.CENTER);
        chatDisplay.setBorder(chatTitle);
        JTextArea chat = new JTextArea();
        chat.setColumns(45);
        chat.setRows(30);
        chat.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(chat);
        chatDisplay.add(scrollPane, BorderLayout.CENTER);

        main.add(chatDisplay, BorderLayout.CENTER);

        return main;
    }

    private final void setFrame(JPanel panel) {
        this.setContentPane(panel);
    }

    private class WindowController extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    private void runClient() {
        //TODO implement in part 2
    }

    private class Controller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
