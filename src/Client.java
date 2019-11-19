import java.awt.*;

public class Client {

    public static void main(String[] args) {
        ClientChatUI frame = new ClientChatUI("Ranev's Friend Client ChatUI");
        frame.setSize(new Dimension(588, 500));
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
