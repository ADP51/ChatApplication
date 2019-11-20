/*
File Name:  ClientChatUI.java
Author:     Andrew Palmer , 040719945
Course:     CST8221 - JAP 302
Assignment: 2 part 1
Date:       2019-11-19
Professor:  Daniel Cormier
Purpose:    To create the client chat GUI and implement the controller for all of the inputs
Class List: ClientChatUI, Controller, WindowController
 */
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

/**
 * The ClientChatUI class is responsible for creating the client chat GUI and implementing a controller for it.
 * @author Andrew Palmer
 * @version 1.0
 * @see JFrame
 * @since 1.8.192
 */
public class ClientChatUI extends JFrame {

    /**
     * The array of default port values to be used
     */
    private static final String[] PORTARRAY = {"", "8089", "65000", "65535"};

    /**
     * Class Constructor. Takes a title to set to the main frame then calls the runCLient method.
     * @param title The title of the GUI
     */
    public ClientChatUI(String title) {
        this.setTitle(title);
        runClient();
    }

    /**
     * Responsible for creating all of the content for the GUI. Also adds controller to all inputs.
     * @return JPanel with all content
     */
    public JPanel createClientUI() {
        Controller controller = new Controller();
        JPanel main = new JPanel(new BorderLayout());

        /***********************************************
         The connection panel set up
         ***********************************************/
        JPanel connection = new JPanel(new BorderLayout());
        TitledBorder connectionTitle = new TitledBorder("Connection"); //creating the titles border
        connectionTitle.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.red));
        connectionTitle.setTitle("CONNECTION");
        connection.setBorder(connectionTitle);

        //the host information
        JPanel hostPanel = new JPanel(new BorderLayout());
        hostPanel.setBorder(BorderFactory.createEmptyBorder(0,5,10,0)); //making labels line up
        JLabel host = new JLabel("Host:");
        host.setPreferredSize(new Dimension(35,30));

        JTextField hostInfo = new JTextField();
        hostInfo.setText("localhost");
        hostInfo.requestFocus(); // on start this has focus
        hostInfo.setPreferredSize(new Dimension(494,25));
        host.setLabelFor(hostInfo); // set the label host to the hostInfo textfield
        host.setDisplayedMnemonic(KeyEvent.VK_H); //add mnemonic to label for host textfield
        hostPanel.add(host, BorderLayout.WEST);
        hostPanel.add(hostInfo, BorderLayout.CENTER);
        connection.add(hostPanel, BorderLayout.NORTH);

        /*********************************************
        The port components to be added to the display
         *********************************************/
        JPanel portPanel = new JPanel(new BorderLayout());
        portPanel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0)); // to make the labels line up
        JLabel port = new JLabel("Port:");
        port.setPreferredSize(new Dimension(35,30)); // required size for the label
        portPanel.add(port, BorderLayout.WEST);

        JPanel portButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); //add combo box and connect button to this panel
        JComboBox portBox = new JComboBox(PORTARRAY); // gets the array of default port numbers and adds to combobox
        port.setDisplayedMnemonic(KeyEvent.VK_P); // set the mnemonic for port label
        port.setLabelFor(portBox); // set the label for portBox to port
        portBox.setEditable(true);
        portBox.setBackground(Color.WHITE); //required background color
        portBox.setOpaque(true); // to make it work on mac sort of
        portBox.setPreferredSize(new Dimension(125, 25));
        portBox.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        portBox.addActionListener(controller); //add listener might not be required
        portButtons.add(portBox);

        JButton connectButton = new JButton("Connect");
        connectButton.setBackground(Color.red);
        connectButton.setOpaque(true); // works on mac
        connectButton.setBorderPainted(false); // works on mac
        connectButton.setActionCommand("connect"); // set the action command for the controller
        connectButton.addActionListener(controller);
        connectButton.setPreferredSize(new Dimension(125,25));
        portButtons.add(connectButton);
        portPanel.add(portButtons, BorderLayout.CENTER);

        connection.add(portPanel, BorderLayout.SOUTH);

        //add the top of the main panel
        main.add(connection, BorderLayout.NORTH);

        //Group the message and Chat panels together for main layout
        JPanel groupingPanel = new JPanel(new BorderLayout());

        /**********************************************
        The message components
         **********************************************/
        JPanel message = new JPanel(new BorderLayout());
        //set up the titled border
        TitledBorder messageTitle;
        messageTitle = new TitledBorder("MESSAGE");
        messageTitle.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.BLACK));
        messageTitle.setTitle("MESSAGE");
        message.setBorder(messageTitle);
        //set up the message textfield
        JTextField messageField = new JTextField();
        messageField.setText("Type Message");
        message.add(messageField, BorderLayout.CENTER);

        //set up send button for messageField
        JButton send = new JButton("Send");
        send.addActionListener(controller);
        send.setActionCommand("Send");
        send.setEnabled(false);
        send.setOpaque(true);
        send.setPreferredSize(new Dimension(125,25));

        message.add(send, BorderLayout.EAST);
        groupingPanel.add(message, BorderLayout.NORTH);

        /********************************
        The chat display components
         ********************************/
        JPanel chatDisplay = new JPanel(new BorderLayout());
        //set up the titled border
        TitledBorder chatTitle = new TitledBorder("CHAT DISPLAY");
        chatTitle.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.BLUE));
        chatTitle.setTitle("CHAT DISPLAY");
        chatTitle.setTitleJustification(TitledBorder.CENTER);
        chatDisplay.setBorder(chatTitle);
        //set up text area for chat
        JTextArea chat = new JTextArea();
        chat.setColumns(45); // required
        chat.setRows(30);// required
        chat.setAutoscrolls(true);

        //set up the scrollpane and add the textarea to it to allow scrolling
        JScrollPane scrollPane = new JScrollPane(chat);
        chatDisplay.add(scrollPane, BorderLayout.CENTER);
        groupingPanel.add(chatDisplay, BorderLayout.CENTER);

        main.add(groupingPanel, BorderLayout.CENTER);

        return main;
    }

    /**
     * Responsible for setting the Frame content pane with what is generated in createClientUI() and
     * adding Window Listener with a custom controller
     */
    private void runClient() {
        this.setContentPane(createClientUI());
        this.addWindowListener(new WindowController());
    }

    /**
     * Custom controller for WindowListener.
     * @author Andrew Palmer
     * @version 1.0
     * @see WindowAdapter
     * @since 1.8.192
     */
    private class WindowController extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    /**
     * Custom controller for GUI actions.
     * @author Andrew Palmer
     * @version 1.0
     * @see ActionListener
     * @since 1.8.192
     */
    private class Controller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

