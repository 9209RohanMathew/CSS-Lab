package RSA;
import java.net.*;
import java.io.*;
// import java.math.*;

public class RSA_Server {
    static int power(int N, int P)
    {
        int pow = 1;
        for (int i = 1; i <= P; i++)
            pow *= N;
        return pow;
    }
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
    public static void main(String[] args) throws IOException{
        try{
            int port = 8088;
            
            double P = 5;
            double Q = 13;
            double CT,d=0,n = P*Q;
            double e = 2;
            double K = 0;
            double Tn = (P - 1) * (Q - 1);
            double ctt;
            double Bdash;
            while ( e < Tn){
                if (gcd(e, Tn) == 1){
                    break;
                }
                else{
                    e = e + 1;
                }
            }
            for(double k = 2; k<500; k++){
                if(((k+Tn)+1)%e == 0){
                    K = k;
                    break;
                    // System.out.println("k : "+k);
                }
                else{
                    k = k+1;
                }
            }
            d = ((K*Tn)+1)/e;
            ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			Socket server1 = serverSocket.accept();
			System.out.println("Just connected to " + server1.getRemoteSocketAddress());
            DataInputStream in1 = new DataInputStream(server1.getInputStream());
            // DataInputStream in2 = new DataInputStream(server1.getInputStream());
            // DataInputStream in3 = new DataInputStream(server1.getInputStream());
            System.out.println(in1);
            // in1=1394;
            CT = Integer.parseInt(in1.readUTF());
            
            System.out.println("Cipher Text : " + CT);
            // d = Integer.parseInt(in1.readUTF());
            // System.out.println("d : " + d);
            // n = Integer.parseInt(in1.readUTF()); 
            System.out.println("d : "+d);
            System.out.println("n :"+n);
            ctt=((int)CT);
            // double DT=((int)d);
            // double NT=((int)n);
            Bdash = Math.pow(ctt, d);
            Bdash = Bdash % n;
            
            System.out.println("Plain Text : " + Bdash);
            server1.close();
        }
        catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");
		}
        catch (IOException e) {
		}
    }
}
