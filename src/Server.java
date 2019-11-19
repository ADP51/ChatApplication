import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        launchClient(null, "Andrews server");
    }

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
