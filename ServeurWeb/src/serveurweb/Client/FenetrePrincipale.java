/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurweb.Client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Dimitri
 */
public class FenetrePrincipale extends javax.swing.JFrame
{

    private Client client;
    private String site;
    private InetAddress serveur;

    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale()
    {
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
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jDialogErreur = new javax.swing.JDialog();
        adresseSite = new javax.swing.JTextField();
        atteindreSite = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        adresseIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nomSiteLocal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

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
        setPreferredSize(new java.awt.Dimension(500, 350));
        setResizable(false);

        adresseSite.setText("Koala.jpg");
        adresseSite.setToolTipText("");
        adresseSite.setName("adresse"); // NOI18N
        adresseSite.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                adresseSiteActionPerformed(evt);
            }
        });

        atteindreSite.setText("Atteindre le site");
        atteindreSite.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                atteindreSiteActionPerformed(evt);
            }
        });

        jLabel1.setText("Veuillez entrer le nom du fichier que vous souhaitez atteindre :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Atteindre un site internet");

        adresseIP.setText("127.0.0.1");

        jLabel3.setText("Adresse IP du serveur :");

        nomSiteLocal.setText("Koala");
        nomSiteLocal.setToolTipText("");
        nomSiteLocal.setName("adresse"); // NOI18N

        jLabel4.setText("Veuillez entrer le nom sous lequel vous voulez l'enregistrer (ne pas mettre l'extension) : ");
        jLabel4.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adresseIP, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(51, 51, 51)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 882, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(adresseSite, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomSiteLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atteindreSite))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adresseIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adresseSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nomSiteLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(atteindreSite)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atteindreSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atteindreSiteActionPerformed
        //  afficherResultat();
        String ip = adresseIP.getText();
        site = adresseSite.getText();
        String siteLocal = nomSiteLocal.getText();
        if (ip == null || ip.equals(""))//||    !ip.matches("\\D{1,3}\\.\\D{1,3}\\.\\D{1,3}\\.\\D{1,3} ")
        {
            JOptionPane.showMessageDialog(jDialogErreur,
                    "Veuillez saisir une adresse de serveur valide",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (site == null || site.equals("")) // || !site.matches("(?<=http://((\\S){1,20}:(\\S){1,20}@)?)[\\S&&[^:/]]+ ") )
        {
            JOptionPane.showMessageDialog(jDialogErreur,
                    "Veuillez saisir un nom de fichier distant",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (siteLocal == null || siteLocal.equals("")) // || !site.matches("(?<=http://((\\S){1,20}:(\\S){1,20}@)?)[\\S&&[^:/]]+ ") )
        {
            JOptionPane.showMessageDialog(jDialogErreur,
                    "Veuillez saisir un nom pour le fichier local",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        try
        {
            serveur = InetAddress.getByName(ip);

            client = new Client(serveur, 8080);
            int res = verifCo();
            if (res == 0)
            {
                res = client.run(site, siteLocal);
                switch (res)
                {
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
                    case 3:
                        JOptionPane.showMessageDialog(jDialogErreur,
                                "Ce fichier existe déjà ",
                                "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(jDialogErreur,
                                "Données reçues inccorectes ",
                                "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 404:
                        JOptionPane.showMessageDialog(jDialogErreur,
                               res+ " File not found ",
                                "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 400:
                        JOptionPane.showMessageDialog(jDialogErreur,
                                res+" Bad request ",
                                "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 408:
                        JOptionPane.showMessageDialog(jDialogErreur,
                                res+" Timeout ",
                                "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 500:
                        JOptionPane.showMessageDialog(jDialogErreur,
                                res+" Internal server error ",
                                "Erreur",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;

                }
            }
        } catch (UnknownHostException ex)
        {
            JOptionPane.showMessageDialog(jDialogErreur,
                    "L'adresse IP du serveur n'est pas valide ",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_atteindreSiteActionPerformed

    private void adresseSiteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_adresseSiteActionPerformed
    {//GEN-HEADEREND:event_adresseSiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresseSiteActionPerformed

    private int verifCo()
    {
        int res;
        res = client.initSocket();
        switch (res)
        {
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
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nomSiteLocal;
    // End of variables declaration//GEN-END:variables
}
