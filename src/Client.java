/*
File Name:  Client.java
Author:     Andrew Palmer , 040719945
Course:     CST8221 - JAP 302
Assignment: 2 part 1
Date:       2019-11-19
Professor:  Daniel Cormier
Purpose:    To run the ClientChatUI application
 */
import java.awt.*;

/**
 * The Client class is responsible for creating a ClientChatUI object and setting some of the parameters for it.
 * @author Andrew Palmer
 * @version 1.0
 * @see ClientChatUI
 * @since 1.8.192
 */
public class Client {

    /**
     * The main function for the Client side of the application
     * @param args command line args
     */
    public static void main(String[] args) {
        ClientChatUI frame = new ClientChatUI("Ranev's Friend Client ChatUI");
        frame.setSize(new Dimension(588, 500));
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
