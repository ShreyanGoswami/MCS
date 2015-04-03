
import java.awt.event.KeyEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phanindra V.V.D
 */
public class T_Connect_GUI extends javax.swing.JPanel {

    /**
     * Creates new form T_Connect_GUI
     */
    String name, ip;
    Teacher_GUI tgGUI;
    
    public T_Connect_GUI(Teacher_GUI obj) {
        initComponents();
        
        tgGUI = obj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        ipLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        status = new javax.swing.JLabel();

        connectPanel.setBackground(new java.awt.Color(0, 0, 51));

        nameLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(204, 204, 204));
        nameLabel.setText("Name :");

        ipLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        ipLabel.setForeground(new java.awt.Color(204, 204, 204));
        ipLabel.setText("IP Address :");

        jTextField1.setText("Teacher");

        jTextField2.setText("127.0.0.1");

        connectButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        connectButton.setForeground(new java.awt.Color(0, 0, 51));
        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });
        connectButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                connectButtonKeyPressed(evt);
            }
        });

        status.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        status.setForeground(new java.awt.Color(204, 204, 255));
        status.setText("Status");

        javax.swing.GroupLayout connectPanelLayout = new javax.swing.GroupLayout(connectPanel);
        connectPanel.setLayout(connectPanelLayout);
        connectPanelLayout.setHorizontalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(connectButton)
                    .addGroup(connectPanelLayout.createSequentialGroup()
                        .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(status, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(256, Short.MAX_VALUE))
        );
        connectPanelLayout.setVerticalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2))
                .addGap(48, 48, 48)
                .addComponent(connectButton)
                .addGap(67, 67, 67)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(connectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(connectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        // TODO add your handling code here:
        validate();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void connectButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_connectButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            validate();
        }

    }//GEN-LAST:event_connectButtonKeyPressed

    public void validate()
    {
        
        try{
        name = jTextField1.getText();
        ip = jTextField2.getText();
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        
        if(name.equals(""))
        {
            status.setText("Enter the name");
            return;
        }
       
        int n=0, len;
        char c;
        len = ip.length();
        
        //check for 3 dots in IP
        
        for(int i=0; i<len; i++)
        {
            c = ip.charAt(i);
            if(c=='.')
                n++;
        }
        
        
        if(n!=3 || len>15)
        {
            status.setText("Enter valid IP");
            return; //Failed
        }           
        
        //Validation Successful  
        tgGUI.name = name;        
        tgGUI.connect();
                
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton connectButton;
    protected javax.swing.JPanel connectPanel;
    protected javax.swing.JLabel ipLabel;
    protected javax.swing.JTextField jTextField1;
    protected javax.swing.JTextField jTextField2;
    protected javax.swing.JLabel nameLabel;
    protected javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
