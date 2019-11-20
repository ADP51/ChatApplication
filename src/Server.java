/*
File Name:  Client.java
Author:     Andrew Palmer , 040719945
Course:     CST8221 - JAP 302
Assignment: 2 part 1
Date:       2019-11-19
Professor:  Daniel Cormier
Purpose:    To run the ServerChat application
 */
import javax.swing.*;
        import java.awt.*;
        import java.net.Socket;

/**
 * The Server class is responsible for creating a ServerChatUI object and setting some of the parameters for it.
 * @author Andrew Palmer
 * @version 1.0
 * @see ServerChatUI
 * @since 1.8.192
 */
public class Server {

    /**
     * The main function for the server side of this application. Calls launchClient.
     * @param args command line args
     */
    public static void main(String[] args) {
        launchClient(null, "Andrews server");
    }

    /**
     * Responsible for creating a new Runnable instance that creates a new ServerChatUI object and displays it on the screen.
     * Sets basic attributes of the GUI.
     * @param socket the socket object passed from client
     * @param title the title of the GUI Frame
     */
    public static void launchClient(Socket socket, String title) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ServerChatUI chatUI = new ServerChatUI(null);
                chatUI.setTitle(title);
                chatUI.setSize(new Dimension(588, 500));
                chatUI.setResizable(false);
                chatUI.setVisible(true);
            }
        });
    }
}
