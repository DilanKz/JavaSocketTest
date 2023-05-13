import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        Socket socket=null;
        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;

        try {

            serverSocket =new ServerSocket(3000);

            while (true){

                try {

                    socket=serverSocket.accept();

                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                    bufferedReader = new BufferedReader(inputStreamReader);
                    bufferedWriter = new BufferedWriter(outputStreamWriter);

                    while (true){
                        String msgFromClient= bufferedReader.readLine();
                        System.out.println("Client "+msgFromClient);

                        bufferedWriter.write("Msg Received");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                        if (msgFromClient.equalsIgnoreCase("EXIT")){
                            break;
                        }
                    }

                    socket.close();
                    bufferedWriter.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    outputStreamWriter.close();

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
