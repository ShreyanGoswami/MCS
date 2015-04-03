
import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author Phanindra V.V.D and Shreyan
 */
public class S_DataTransfer implements Runnable{
    
    //Only for Receive
    
    S_ScrollPane_GUI spGUI;
    String msg;
    int index;
    S_Chat_GUI chtGUI;
    private String data;
    //to prevent the files from getting overidden
    private int scount=0,fcount=0;
    private String fileName="F:/SE Project/Downloaded/Monitor.png";
    private String dstID;
    private String ID="";
    S_DataTransfer(S_ScrollPane_GUI spGUI)
    {
        this.spGUI = spGUI;
    }
    
    @Override
    public void run() {
        receive();
    }
    
    synchronized private void receive()
    {
        String  name;
        while(true)
        {
            //recieving data
            try {
                data = spGUI.sdtGUI.input.readUTF();
                System.out.println(data);
                if(data.charAt(0)=='P')
                {
                    name=spGUI.sdtGUI.input.readUTF();
                    spGUI.update(data,name);
                    continue;
                }
                
                //else if recieve files
                else if(data.charAt(0)=='F')
                {
                    recieveFile();
                    ID = spGUI.sdtGUI.input.readUTF();
                    
                    index = ID.charAt(1) - '0';
                    chtGUI = spGUI.chtGUI[index];
                    chtGUI.updateTextArea("New file recieved");
                    recieveFile();
                    continue;
                }
                
                //else it is a screenshot
                else if(data.charAt(0)=='A')
                {
                    recieveScreenshot();
                    continue;
                }
                else if(data.charAt(0)=='M'){
                    msg = data.substring(1, data.length());
                    ID = spGUI.sdtGUI.input.readUTF();
                    index = ID.charAt(1) - '0';
                    msg = spGUI.hostNames[index] + " - " + msg;
                    chtGUI = spGUI.chtGUI[index];
                    chtGUI.updateTextArea(msg);
                    continue;
                }
                else if(data.charAt(0)=='X'){
                    System.out.println("Request Recieved");
                    takeScreenshot();
                    System.out.println("Screenshot taken");
                    ID=spGUI.sdtGUI.input.readUTF();
                    System.out.println("ID recieved");
                    sendScreenshot();
                    System.out.println("Screenshot sent");
                }
                
                
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
            catch(InterruptedException e){
                e.printStackTrace(System.out);
            }
            
            //Get the specific index of the user
//            System.out.println(ID);
//            index = ID.charAt(1) - '0';
//
//            msg = spGUI.hostNames[index] + " - " + msg;
//
//            //Get corresponding chat panel
//            chtGUI = spGUI.chtGUI[index];
//            chtGUI.updateTextArea(msg);
        }
    }
    
    private void recieveFile(){
        String file=new String();
        file=data.substring(1);
        char[]rbuff=new char[file.length()];
        rbuff=file.toCharArray();
        
        //writing the contents
        try{
            //destination for the recieved file
            String dest="F:/SE Project/Downloaded/Files/RecievedFile"+ fcount++ +".txt";
            //creates the output file
            FileOutputStream fos = new FileOutputStream(dest);
            Writer writer = new OutputStreamWriter(fos);
            writer.write(rbuff);
            writer.close();
        }catch(IOException e){
            System.out.println("Unable to write to file");
            e.printStackTrace(System.out);
        }
    }
    
    private void recieveScreenshot(){
        try{
            // int width=1366,height=768; /* set the width and height here */
            BufferedImage inputImage;
            
            inputImage=ImageIO.read(Student_GUI.input);
            
            //obtain size of image
            
            int width=inputImage.getWidth();
            int height=inputImage.getHeight();
            BufferedReader br=new BufferedReader(new InputStreamReader(Student_GUI.input));
            int n;
            while((n=br.read())!=(char)'A'){
                System.out.println(n);
            }
            BufferedImage outputImage=new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g=outputImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.clearRect(0, 0, width, height);
            g.drawImage(inputImage, 0, 0, width, height, null);
            
            g.dispose();
            //save the image
            String path1 = "F:/SE Project/Downloaded/Pictures/";
            String path2="Capture"+ System.currentTimeMillis() +".png";
            String path=path1+path2;
            File dest=new File(path);
            ImageIO.write(outputImage,"png",dest);
            System.out.println("Write Successful");
            
            ID = spGUI.sdtGUI.input.readUTF();
            System.out.println("ID recieved");
            index = ID.charAt(1) - '0';
            chtGUI = spGUI.chtGUI[index];
            chtGUI.updateTextArea("New image recieved: "+path2);
            JLabel picLabel=new JLabel(new ImageIcon(inputImage));
            JOptionPane.showMessageDialog(null, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
        }catch(IOException e){
            System.out.println("Failed to recieve image");
            e.printStackTrace(System.out);
        }
    }
    
    private void takeScreenshot(){
        
        try {
            
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            // Thread.sleep(2000);
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
            
            ImageIO.write(capture, "png", new File(fileName));
            System.out.println("Screenshot taken successfully");
        } catch (IOException ex) {
            
            System.out.println(ex);
            
        } catch (AWTException ex) {
            
            System.out.println(ex);
            
        } catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    private void sendScreenshot() throws InterruptedException{
        try{
            dstID= "P" + String.valueOf(spGUI.getIndex());
            spGUI.sdtGUI.output.writeUTF("SCREENSHOT");
            System.out.println(dstID);
            spGUI.sdtGUI.output.writeUTF(ID);
            BufferedImage img = ImageIO.read(new File(fileName));
            System.out.println("Image read");
            ImageIO.write(img, "png", spGUI.sdtGUI.output);
            img.flush();
            spGUI.sdtGUI.output.writeUTF("Ping");
            
            // spGUI.sdtGUI.output.write(buff);
            System.out.println("Picture sent");
            Thread.sleep(1000);
            //removing the screenshot from the student's PC
            File f=new File(fileName);
            f.delete();
        }catch(IOException e){
            System.out.println("Unable to read image");
            e.printStackTrace(System.out);
        }
    }
}
