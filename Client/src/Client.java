import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Socket socket=null;
        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;

        try {

            socket=new Socket("localhost",3000);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);


            System.out.print("Enter Message: ");
            Scanner scanner=new Scanner(System.in);

            while (true){

                String msg=scanner.nextLine();
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("server "+bufferedReader.readLine());

                if (msg.equalsIgnoreCase("EXIT")){
                    break;
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (socket!=null){
                    socket.close();
                }
                if (inputStreamReader!=null){
                    inputStreamReader.close();
                }
                if (outputStreamWriter!=null){
                    outputStreamWriter.close();
                }
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
                if (bufferedWriter!=null){
                    bufferedWriter.close();
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
