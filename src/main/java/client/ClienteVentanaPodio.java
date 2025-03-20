/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;

/**
 *
 * @author link
 */
public class ClienteVentanaPodio extends javax.swing.JFrame { //no entra
    private static ClienteVentanaPodio instancia;

    /**
     * Creates new form VentanaPodio
     */
    public ClienteVentanaPodio(byte[] posiciones, String[] nombres) {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        for(int i=0;i<4;i++){
            switch (posiciones[i]){
                case 1:
                    this.labelNombreCamelloPos1.setText(nombres[i]);
                    break;
                case 2:
                    this.labelNombreCamelloPos2.setText(nombres[i]);
                    break;
                case 3:
                    this.labelNombreCamelloPos3.setText(nombres[i]);
                    break;
                case 4:
                    this.labelNombreCamelloPos4.setText(nombres[i]);
                    break;
            }
        }
    }

    //Singleton
    public static ClienteVentanaPodio getInstancia(byte[] posiciones, String[] nombres){
        if(instancia==null){
            instancia=new ClienteVentanaPodio(posiciones, nombres);
        }
        return instancia;
    }
    
    private ClienteVentanaPodio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPos4 = new javax.swing.JPanel();
        labelPos4 = new javax.swing.JLabel();
        panelPos2 = new javax.swing.JPanel();
        labelPos2 = new javax.swing.JLabel();
        panelPos1 = new javax.swing.JPanel();
        labelPos1 = new javax.swing.JLabel();
        panelPos3 = new javax.swing.JPanel();
        labelPos3 = new javax.swing.JLabel();
        labelNombreCamelloPos1 = new javax.swing.JLabel();
        labelNombreCamelloPos2 = new javax.swing.JLabel();
        labelNombreCamelloPos4 = new javax.swing.JLabel();
        labelNombreCamelloPos3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPos4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPos4.setBackground(Color.WHITE);

        labelPos4.setText("Posición 4");

        javax.swing.GroupLayout panelPos4Layout = new javax.swing.GroupLayout(panelPos4);
        panelPos4.setLayout(panelPos4Layout);
        panelPos4Layout.setHorizontalGroup(
            panelPos4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelPos4)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelPos4Layout.setVerticalGroup(
            panelPos4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPos4)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        panelPos2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPos2.setBackground(Color.LIGHT_GRAY);

        labelPos2.setText("Posición 2");

        javax.swing.GroupLayout panelPos2Layout = new javax.swing.GroupLayout(panelPos2);
        panelPos2.setLayout(panelPos2Layout);
        panelPos2Layout.setHorizontalGroup(
            panelPos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelPos2)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelPos2Layout.setVerticalGroup(
            panelPos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPos2)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        panelPos1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPos1.setBackground(Color.YELLOW);
        labelPos1.setText("Posición 1");

        javax.swing.GroupLayout panelPos1Layout = new javax.swing.GroupLayout(panelPos1);
        panelPos1.setLayout(panelPos1Layout);
        panelPos1Layout.setHorizontalGroup(
            panelPos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelPos1)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelPos1Layout.setVerticalGroup(
            panelPos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPos1)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        panelPos3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPos3.setBackground(Color.ORANGE);
        labelPos3.setText("Posición 3");

        javax.swing.GroupLayout panelPos3Layout = new javax.swing.GroupLayout(panelPos3);
        panelPos3.setLayout(panelPos3Layout);
        panelPos3Layout.setHorizontalGroup(
            panelPos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelPos3)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelPos3Layout.setVerticalGroup(
            panelPos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPos3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPos3)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        labelNombreCamelloPos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelNombreCamelloPos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelNombreCamelloPos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelNombreCamelloPos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPos4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNombreCamelloPos4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNombreCamelloPos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombreCamelloPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelPos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelNombreCamelloPos3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelPos3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(labelNombreCamelloPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelNombreCamelloPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelPos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelPos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelNombreCamelloPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelPos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelNombreCamelloPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelPos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteVentanaPodio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteVentanaPodio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteVentanaPodio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteVentanaPodio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteVentanaPodio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelNombreCamelloPos1;
    private javax.swing.JLabel labelNombreCamelloPos2;
    private javax.swing.JLabel labelNombreCamelloPos3;
    private javax.swing.JLabel labelNombreCamelloPos4;
    private javax.swing.JLabel labelPos1;
    private javax.swing.JLabel labelPos2;
    private javax.swing.JLabel labelPos3;
    private javax.swing.JLabel labelPos4;
    private javax.swing.JPanel panelPos1;
    private javax.swing.JPanel panelPos2;
    private javax.swing.JPanel panelPos3;
    private javax.swing.JPanel panelPos4;
    // End of variables declaration//GEN-END:variables
}
