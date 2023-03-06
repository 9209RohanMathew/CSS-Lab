package RSA;
import java.io.*;
import java.net.*;
import java.math.*;

public class RSA_Client {
    public static double gcd(double a, double h)
  {
    /*
         * This function returns the gcd or greatest common
         * divisor
         */
    double temp;
    while (true) {
      temp = a % h;
      if (temp == 0)
        return h;
      a = h;
      h = temp;
    }
  }
    public static void main(String[] args){
        try{
            String serverName = "localhost";
			int port = 8088;
            double P = 5;
            double Q = 13;
            double n = P * Q;
            double Tn = (P - 1) * (Q - 1);
            // double k = 2;
            double e = 2;
            double PT = 10;
            while ( e < Tn){
                if (gcd(e, Tn) == 1){
                    break;
                }
                else{
                    e = e + 1;
                }
            }
            // double d = ((k * Tn) + 1) / e;
            // System.out.println("d : "+d);
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            // Sends the data to client
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out1 = new DataOutputStream(outToServer);
            // DataOutputStream out2 = new DataOutputStream(outToServer);
            // DataOutputStream out3 = new DataOutputStream(outToServer);
            System.out.println("P = "+P);
            System.out.println("Q = "+Q);
            System.out.println("e = "+e);
            System.out.println("n = "+n);
            double CT = ((Math.pow(PT, e)) % n);
            System.out.println("Cipher Value:" +CT);
            int ctt=((int)CT);
            String CT1 = Integer.toString(ctt);
            out1.writeUTF(CT1);
            // String d1 = Double.toString(d);
            // int d1t=((int)d);
            // String d1 = Integer.toString(d1t);
            // out1.writeUTF(d1);
            // String n1 = Double.toString(n);
            // int n1t=((int)n);
            // String n1 = Integer.toString(n1t);
			      // out1.writeUTF(n1);
            
            client.close();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }
}
