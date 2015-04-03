

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phanindra V.V.D
 */
public class Key {
    
    String id[]=new String[100];
    String names[] = new String[100];
    int count;
    SVR_DataTransfer DT[];
    
    public Key()
    {
        count = 0;
        //inititalise 100 output streams for 100 clients
        DT = new SVR_DataTransfer[100];
    }
        
    synchronized String setKey(int i, SVR_DataTransfer obj, String name) {
        
        if(i==1)
        {
            id[count] = "P" + String.valueOf(count);
        }
        else
        {
            id[count] = "S" + String.valueOf(count);
        }      
        
        DT[count] = obj;
        this.names[count] = name;

        return id[count++];
    }
        
    SVR_DataTransfer getDTObj(String s)
    {
        char c;
        
        c = s.charAt(1);
        
        int n = c - '0';
        
        return DT[n];
    }
    
    SVR_DataTransfer[] getDTObjS()
    {        
        return DT;
    }
    
    String getName(String s)
    {
        char c;
        
        c = s.charAt(1);
        
        int n = c - '0';
        
        return names[n];
    }
    
    String[] getIDS()
    {
        return id;
    }
    
    String[] getNames()
    {
        return names;
    }
    
    int getNumber()
    {
        //Returns no.of connections
        return count;
    }
    
    
}
