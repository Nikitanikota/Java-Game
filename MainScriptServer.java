import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
public class MainScriptServer {

    public static void main(final String[] args) {

        try {
            //receiving packets of drawgs
            final ServerSocket ss = new ServerSocket(1586);
            final Socket s = ss.accept();
            final DataInputStream dis = new DataInputStream(s.getInputStream());
            final String str = (String) dis.readUTF();
            //receiving packets of drawgs

            System.out.println(str);

            //sending huwgs
            final Socket sr=new Socket (str,1587);
            final DataOutputStream dout=new DataOutputStream(sr.getOutputStream());
            dout.writeUTF(str);
            dout.flush();
            //sending huwgs
            TimeUnit.SECONDS.sleep(7);
            final String Packet = (String) dis.readUTF();
            System.out.println("Client Says " + Packet);
            ss.close();
            sr.close();
            s.close();
        }

        catch (final Exception e) {
            System.out.println(e);
        }

    }

}