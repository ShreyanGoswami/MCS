
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author Phanindra V.V.D and Shreyan
 */
public class Exchange_Data implements Runnable{
    
    DataInputStream input;
    String msg, srcID;
    Key key;
    SVR_DataTransfer DT;
    Socket s;
    
    public Exchange_Data(Socket s,DataInputStream input, String ID, Key key)
    {
        this.input = input;
        srcID = ID;
        this.key = key;
        this.s=s;
    }
    
    @Override
    public void run() {
        try {
            Transfer();
        } catch (IOException ex) {
            System.out.println("Connection Closed : "+ex);
            ex.printStackTrace(System.out);
        } catch (InterruptedException ex) {
            Logger.getLogger(Exchange_Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    synchronized private void Transfer() throws IOException, InterruptedException {
        
        String dstID, type;
        
        while(true)
        {
            System.out.println("Waiting for data");
            type = input.readUTF();
            System.out.println(type);
            
            if(type.equals("MESSAGE"))
            {
                System.out.println("Message Recieved");
                msg = input.readUTF();
                dstID = input.readUTF();
                int n=input.available();
                System.out.println(n);
                //Get corresponding DataTransfer object
                DT = key.getDTObj(dstID);
                
                DT.messageTransfer(msg);
                DT.messageTransfer(srcID);
            }
            
            else if(type.equals("MONITOR")){
                dstID=input.readUTF();
                System.out.println(dstID);
                DT=key.getDTObj(dstID);
                DT.messageTransfer("X");
                DT.messageTransfer(dstID);
            }
            
            else if(type.equals("FILE"))
            {
                //write the code to receive the file
                //and send the file contents to fileTransfer in DT
                System.out.println("File recieved");
                msg=input.readUTF();
                dstID=input.readUTF();
                DT = key.getDTObj(dstID);
                DT.messageTransfer(msg);
                DT.messageTransfer(srcID);
            }
            else if(type.equals("SCREENSHOT") || type.equals("IMAGE")){
                
                dstID = input.readUTF();
                
                DT = key.getDTObj(dstID);
                //Get corresponding DataTransfer object
                
                
                System.out.println("Sending information that data is a screenshot");
                DT.messageTransfer("A");
                
                BufferedImage rec=ImageIO.read(input);
                System.out.println("After reading");
                int n=input.available();
                System.out.println(n);
                
                
                DT.imageTransfer(rec);
//
                BufferedReader br=new BufferedReader(new InputStreamReader(input));
                
                while((n=br.read())!=0){
                    System.out.println((char)n);
                    System.out.println(".");
                }
                System.out.println("Out of loop");
                System.out.println(n);
                System.out.println(msg);
                
                Thread.sleep(1000);
                //send escape character
                DT.messageTransfer("A");
                //send source ID
                DT.messageTransfer(srcID);
            }
        }
    }
}
