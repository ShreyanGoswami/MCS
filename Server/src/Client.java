
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phanindra V.V.D
 */
public class Client implements Runnable{
    
    static private DataOutputStream output;
    static private DataInputStream input;//For secured communication of IDs
    //private final Socket socket;
    static protected Socket socket;
    private String ID;
    private final Key keyP, keyS;
    private String msg, name="";
    private SVR_DataTransfer DT;
    
    public Client(Socket obj, Key k1, Key k2)
    {
        socket = obj;
        keyP = k1;
        keyS = k2;
    }

    @Override
    public void run() {
        
        Thread t_eid, t_edt;
        Exchange_ID eid;
        Exchange_Data edt;
        try {
            setupStreams();            
            DT = new SVR_DataTransfer(output);
            setID();      
            
            
            if(ID.charAt(0)=='P')
            {
                eid = new Exchange_ID(DT, ID, name, keyS);
                edt = new Exchange_Data(socket,input, ID, keyS);
            }
            else
            {
                eid = new Exchange_ID(DT, ID, name, keyP);
                edt = new Exchange_Data(socket,input, ID, keyP);
            }
            
            t_eid = new Thread(eid);
            t_edt = new Thread(edt);
            
            t_eid.start();          
            t_edt.start();
            
            t_eid.join();
            t_edt.join();
            
        } 
        catch (IOException ex) {
            System.out.println("IO Exception : "+ex);
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found Exception : "+ex);
            ex.printStackTrace(System.out);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception : "+ex);
        }
        finally{
            closeCrap();
        }
    }

    public static void setupStreams() throws IOException {
        //Initialise Streams 
        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }    

    private void closeCrap() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void setID() throws IOException, ClassNotFoundException {       
       
            msg = (String)input.readUTF();
            name = (String)input.readUTF();
        
        switch (msg) {
            case "PROFESSOR":
                ID = keyP.setKey(1, DT, name);
                break;
            case "SCHOLAR":
                ID = keyS.setKey(0, DT, name);
                break;
        }
                
        output.writeUTF(ID);
        
    }

    //the set up streams can be used to link the IO streams to the socket
    //Somtimes we may wish to recieve an image from the Socket. But if we 
    //bind the 'input' stream we won't be able to recieve any other kind of data
    //For this we would like to access the input and output streams of sockets
    //bind it to various streams and after the work is done we would bind the 
    //input and output streams of the socket to 'input' and 'output'
    public void dereference(){
        input=null;
        output=null;
    }
    
    
}
