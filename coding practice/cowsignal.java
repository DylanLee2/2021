import java.util.*;
import java.io.*;

public class cowsignal {

    static Scanner in;
    static PrintWriter out;
    static String filename = "cowsignal";
    static int m,n,k;
    static String signal, ampSignal;
    
    public static void main(String[] args) throws IOException {

        //in = new Scanner(System.in);
        in = new Scanner(new File(filename+".in"));        
        out = new PrintWriter(new File(filename+".out")); 
        init();
        solve();
        
        in.close();
        out.close();
    }
    
    static void init() throws IOException {    
        m = in.nextInt();
        n = in.nextInt();
        k = in.nextInt();

        signal = "";
        ampSignal = "";

        for(int i = 0; i < m; i++)
            signal += in.nextLine();
    }
    
    static void solve() throws IOException {
        
        for(int i = 0; i < m*k; i++){
            for(int j = 0; j < n*k; j++){
                ampSignal += 
            }
        }

        out.println(ampSignal);
    }

}