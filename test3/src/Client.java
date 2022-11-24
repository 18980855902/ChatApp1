import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private DataInputStream in;
    private DataOutputStream out;
    private boolean connection;

    public Client(Socket socket){
        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            connection = true;
        }catch (IOException e){
            System.out.println("error client");
            e.printStackTrace();
        }
    }

    public void sendMessageToServer(String messageToSend){
        try {
            if (messageToSend != null && !"".equals(messageToSend)) {
                int size = messageToSend.length();
                out.writeInt(size);
                out.write(messageToSend.getBytes(), 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
            connection = false;
        }
    }

    public void receiveMessageFromServer(VBox messagePane){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(connection){
                    String messageFromClient = "";

                    try {
                        byte[] buffer = new byte[1024];

                        int size = in.readInt();
                        while(size > 0) {
                            int len = in.read(buffer, 0, Math.min(size, buffer.length));
                            messageFromClient += new String(buffer, 0, len);
                            size -= len;
                        }
                        ChatControl.displayReceiveMessage(messageFromClient);
                    } catch (IOException e) {
                        e.printStackTrace();
                        connection = false;
                    }
                }
            }
        }).start();
    }

    public void login(String userName){
        try{out.writeInt(userName.length());
            out.write(userName.getBytes(),0,userName.length());
        } catch(IOException e){
            System.err.println("login failure");
        }
    }
}
