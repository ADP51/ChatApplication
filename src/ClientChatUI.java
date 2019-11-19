import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientChatUI extends JFrame {

    private static final String[] PORTARRAY = {"", "8089", "65000", "65535"};

    public ClientChatUI(String title) {
        this.setTitle(title);
        runClient();
    }

    public JPanel createClientUI() {
        Controller controller = new Controller();
        JPanel main = new JPanel(new BorderLayout());

        //connection panel set up
        JPanel connection = new JPanel(new BorderLayout());
        TitledBorder connectionTitle = new TitledBorder("Connection");
        connectionTitle.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.red));
        connectionTitle.setTitle("CONNECTION");
        connection.setBorder(connectionTitle);

        //the host information
        JPanel hostPanel = new JPanel(new BorderLayout());
        hostPanel.setBorder(BorderFactory.createEmptyBorder(0,5,5,4));
        JLabel host = new JLabel("Host:");
        host.setPreferredSize(new Dimension(35,30));

        JTextField hostInfo = new JTextField();
        hostInfo.setText("localhost");
        hostInfo.requestFocus();
        host.setLabelFor(hostInfo); // set the label host to the hostInfo textfield
        host.setDisplayedMnemonic('h');
        hostPanel.add(host, BorderLayout.WEST);
        hostPanel.add(hostInfo, BorderLayout.CENTER);
        connection.add(hostPanel, BorderLayout.NORTH);

        /*
        The port components to be added to the display
         */
        JPanel portPanel = new JPanel(new BorderLayout());
        portPanel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        JLabel port = new JLabel("Port:");
        port.setPreferredSize(new Dimension(35,30));
        portPanel.add(port, BorderLayout.WEST);

        JPanel portButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        JComboBox portBox = new JComboBox(PORTARRAY);
        port.setLabelFor(portBox); // set the label for portBox to port
        port.setDisplayedMnemonic('p');
        portBox.setEditable(true);
        portBox.setBackground(Color.WHITE);
        portBox.setOpaque(true);
        portBox.addActionListener(controller);
        portButtons.add(portBox);

        JButton connectButton = new JButton("Connect");
        connectButton.setBackground(Color.red);
        connectButton.setOpaque(true);
        connectButton.setBorderPainted(false);
        connectButton.addActionListener(controller);
        portButtons.add(connectButton);
        portPanel.add(portButtons, BorderLayout.CENTER);

        connection.add(portPanel, BorderLayout.SOUTH);

        //add the top of the main panel
        main.add(connection, BorderLayout.NORTH);

        //Group the message and Chat panels together for main layout
        JPanel groupingPanel = new JPanel(new BorderLayout());

        /*
        The message components
         */
        JPanel message = new JPanel(new BorderLayout());
        TitledBorder messageTitle;
        messageTitle = new TitledBorder("MESSAGE");
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
        groupingPanel.add(message, BorderLayout.NORTH);

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

        groupingPanel.add(chatDisplay, BorderLayout.CENTER);

        main.add(groupingPanel, BorderLayout.CENTER);

        return main;
    }

    private void runClient() {
        this.setContentPane(createClientUI());
        this.addWindowListener(new WindowController());
    }

    private class WindowController extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    private class Controller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

