import  java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class MainScriptClient {
    
    public static void main(final String[] args) {

        Scanner PortScan = new Scanner(System.in); //Creates a new scanner
        System.out.println("Port"); //Asks question
        String PortString = PortScan.nextLine(); //Waits for input
        int Port=Integer.parseInt(PortString);

        Scanner IpScan = new Scanner(System.in); //Creates a new scanner
        System.out.println("ip"); //Asks question
        String Ip = IpScan.nextLine(); //Waits for input


        Scanner PacketScan = new Scanner(System.in); //Creates a new scanner
        System.out.println("Send Packet Name"); //Asks question
        String PacketName = PacketScan.nextLine(); //Waits for input
        try {
            //Sending Packets
            final Socket s=new Socket (Ip,Port);
            final DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF(Ip);
            dout.flush();
            //Sending Packets End



            //receiving Packets 
            final ServerSocket ss = new ServerSocket(Port + 1);
            final Socket sr = ss.accept();
            final DataInputStream dis = new DataInputStream(sr.getInputStream());
            TimeUnit.SECONDS.sleep(5);
            final String str = (String) dis.readUTF();
            String strn = str.replaceAll(" ", "");
            String Ipn = Ip.replaceAll(" ", "");
            //receiving Packets End
            if (strn.equals(Ipn)) {
                System.out.println("Packet Sent");
                dout.writeUTF(PacketName);
                dout.flush();
                dout.close();
            } else {
                s.close();
                sr.close();
                ss.close();
            }

            s.close();
            sr.close();
            ss.close();
            IpScan.close();
            
        }
        catch(Exception e) {

        System.out.println(e);
        
        }
        

    }

}

    
