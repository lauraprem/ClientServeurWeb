/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurweb.Client;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Dimitri
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    private Client client;
    private String site;
    private InetAddress serveur;

    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale() {
        initComponents();
//        try {
//            client = new Client(null, 0);
//            
//        } catch (SocketException ex) {
//           System.err.println(ex.getMessage());
//        }
        site = new String();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDialogErreur = new javax.swing.JDialog();
        adresseSite = new javax.swing.JTextField();
        atteindreSite = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        adresseIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialogErreurLayout = new javax.swing.GroupLayout(jDialogErreur.getContentPane());
        jDialogErreur.getContentPane().setLayout(jDialogErreurLayout);
        jDialogErreurLayout.setHorizontalGroup(
            jDialogErreurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialogErreurLayout.setVerticalGroup(
            jDialogErreurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Application web");

        adresseSite.setToolTipText("");
        adresseSite.setName("adresse"); // NOI18N

        atteindreSite.setText("Atteindre le site");
        atteindreSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atteindreSiteActionPerformed(evt);
            }
        });

        jLabel1.setText("Veuillez entrer le site que vous souhaitez atteindre :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Atteindre un site internet");

        jLabel3.setText("Adresse IP du serveur :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(adresseSite, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(atteindreSite))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(adresseIP, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addComponent(adresseIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresseSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atteindreSite))
                .addGap(118, 118, 118))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atteindreSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atteindreSiteActionPerformed

        String ip = adresseIP.getText();
        site = adresseSite.getText();
        if (ip == null || ip.equals(""))//||    !ip.matches("\\D{1,3}\\.\\D{1,3}\\.\\D{1,3}\\.\\D{1,3} ")
        {
            JOptionPane.showMessageDialog(jDialogErreur,
                    "Veuillez saisir une adresse de serveur valide",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (site == null || site.equals("")) // || !site.matches("(?<=http://((\\S){1,20}:(\\S){1,20}@)?)[\\S&&[^:/]]+ ") )
        {
            JOptionPane.showMessageDialog(jDialogErreur,
                    "Veuillez saisir une adresse de site",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            serveur = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            System.err.println(ex.getMessage());
        }
        client = new Client(serveur, 80);
        int res = verifCo();
        if (res == 0) {
            res = client.run();
            switch (res) {
                case 1:
                    JOptionPane.showMessageDialog(jDialogErreur,
                            "Le serveur n'a pas répondu ",
                            "Erreur",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(jDialogErreur,
                            "Erreur d'entrée sortie ",
                            "Erreur",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

            }
        }


    }//GEN-LAST:event_atteindreSiteActionPerformed

    private int verifCo() {
        int res;
        res = client.initSocket();
        switch (res) {
            case 1:
                JOptionPane.showMessageDialog(jDialogErreur,
                        "Impossible de joindre le serveur\n Veuillez vérifier que vous avez rentré une adresse valide.",
                        "Erreur",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(jDialogErreur,
                        "Erreur d'entrée sortie ",
                        "Erreur",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        return res;
    }

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
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetrePrincipale().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresseIP;
    private javax.swing.JTextField adresseSite;
    private javax.swing.JButton atteindreSite;
    private javax.swing.JDialog jDialogErreur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}