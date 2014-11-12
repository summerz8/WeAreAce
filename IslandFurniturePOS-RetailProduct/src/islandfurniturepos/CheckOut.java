/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandfurniturepos;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import sessionbean.ocrm.MemberEntity;
import sessionbean.ocrm.StoreEntity;
import sessionbean.ocrm.TransactionEntity;
import sessionbean.ocrm.TransactionItemEntity;

/**
 *
 * @author hangsun
 */
public class CheckOut extends javax.swing.JFrame {

    private String POSid = null;
    private String partnerPoleDisplayCOMPort = "COM4";
    private OutputStream partnerPoleDisplayOutputStream;
    private SerialPort serialPort;
    private Long transactionId = null;
    private String storeStaffId = null;
    private Double totalPrice = null;
    private Double actualTotalPrice = null;
    private Double totalMemberPrice = null;
    private Long memberId = null;
    private Double currentPoints = null;  
    private List<TransactionItemEntity> transactionItemList = null;

    /**
     * Creates new form CheckOut
     */
    public CheckOut() {
        initComponents();
    }

    public CheckOut(String POSid, String storeStaffId, Long transactionId) {
        this();

        this.POSid = POSid;
        this.storeStaffId = storeStaffId;
        this.transactionId = transactionId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanelMain = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jButtonCash = new javax.swing.JButton();
        jLabelTotalPrice = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jLabelCurrent = new javax.swing.JLabel();
        jLabelRedemption = new javax.swing.JLabel();
        jFormattedTextFieldRedemption = new javax.swing.JFormattedTextField();
        jButtonRedemption = new javax.swing.JButton();
        jButtonCredit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItemList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanelTitle.setBackground(new java.awt.Color(255, 51, 51));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(899, 119));

        jLabelTitle.setFont(new java.awt.Font("New Peninim MT", 3, 48)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("                   Island Furniture POS");
        jLabelTitle.setToolTipText("");
        jLabelTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTotal.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelTotal.setText("Total Price :(S$)");

        jButtonCash.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCash.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonCash.setText("Cash");
        jButtonCash.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCashActionPerformed(evt);
            }
        });

        jLabelTotalPrice.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelTotalPrice.setText("Total Price");

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/singapore-logo0.1.jpg"))); // NOI18N

        jButtonCancel.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCancel.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonCancel.setText("Cancel");
        jButtonCancel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jLabelCurrent.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelCurrent.setText("Member Current Points:");

        jLabelRedemption.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelRedemption.setText("Redemption: ");

        jFormattedTextFieldRedemption.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextFieldRedemption.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jFormattedTextFieldRedemption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldRedemptionActionPerformed(evt);
            }
        });

        jButtonRedemption.setBackground(new java.awt.Color(255, 255, 255));
        jButtonRedemption.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonRedemption.setText("Redemption");
        jButtonRedemption.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonRedemption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedemptionActionPerformed(evt);
            }
        });

        jButtonCredit.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCredit.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonCredit.setText("Credit Card");
        jButtonCredit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jFormattedTextFieldRedemption, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonRedemption, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                    .addComponent(jButtonCredit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabelRedemption)))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelLogo))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabelCurrent)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabelTotalPrice))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabelTotal)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelCurrent)
                .addGap(18, 18, 18)
                .addComponent(jLabelRedemption)
                .addGap(4, 4, 4)
                .addComponent(jFormattedTextFieldRedemption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRedemption)
                .addGap(18, 18, 18)
                .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCash)
                .addGap(18, 18, 18)
                .addComponent(jButtonCredit)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancel)
                .addGap(70, 70, 70))
        );

        jTableItemList.setFont(new java.awt.Font("Times", 3, 12)); // NOI18N
        jTableItemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Item Name", "Unit Price", "Amount", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableItemList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //initPartnerPoleDisplay();
             

        totalPrice = caculateTotalPrice(transactionId);
        actualTotalPrice = totalPrice;
        TransactionEntity transaction = getTransactionById(transactionId);
        totalMemberPrice = transaction.getTotalMemberPrice();
        if (transaction.getMember() != null) {
            if (transaction.getLocation() == 1) {
                jLabelTotalPrice.setText(Double.toString(totalMemberPrice));
                actualTotalPrice = totalMemberPrice;
            } else {
                jLabelTotalPrice.setText(Double.toString(totalPrice));
            }
            memberId = transaction.getMember().getMemberId();
            currentPoints = transaction.getMember().getCurrentPoints();
            jLabelCurrent.setText("Member Current Points: " + currentPoints);

        } else {
            jLabelCurrent.setVisible(false);
            jLabelRedemption.setVisible(false);
            jFormattedTextFieldRedemption.setVisible(false);
            jButtonRedemption.setVisible(false);
            jLabelTotalPrice.setText(Double.toString(totalPrice));
        }
        loadTable();

        //poleDisplay(actualTotalPrice); 
    }//GEN-LAST:event_formWindowOpened

    private void jButtonCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCashActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Cash cash = new Cash(POSid, storeStaffId,transactionId,actualTotalPrice,this);
        cash.setVisible(true);
        cash.setExtendedState(JFrame.NORMAL);

    }//GEN-LAST:event_jButtonCashActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        deleteUnfinishedTransaction(transactionId);
        this.setVisible(false);
        this.dispose();
        MainMenu mainMenu = new MainMenu(POSid, storeStaffId);
        mainMenu.setVisible(true);
        mainMenu.setExtendedState(JFrame.NORMAL);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if (serialPort != null) {
            try {
                byte[] clear = {0x0C};
                partnerPoleDisplayOutputStream.write(clear);
                partnerPoleDisplayOutputStream.close();
                serialPort.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_formWindowClosed

    private void jFormattedTextFieldRedemptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldRedemptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldRedemptionActionPerformed

    private void jButtonRedemptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedemptionActionPerformed
        // TODO add your handling code here:
//        if (memberId == null) {
//            JOptionPane.showMessageDialog(this, "Redemption is only available for member!", "Error", JOptionPane.ERROR_MESSAGE);
//        } else 
        if (jFormattedTextFieldRedemption.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please insert redemption points!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (Double.parseDouble(jFormattedTextFieldRedemption.getText()) > currentPoints) {
                JOptionPane.showMessageDialog(this, "Not Enough Points!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Double redemptionPoints = Double.parseDouble(jFormattedTextFieldRedemption.getText());
                redemption(redemptionPoints, memberId);
                currentPoints -= redemptionPoints;
                jLabelCurrent.setText("Member Current Points: " + currentPoints);
                actualTotalPrice = caculateRedemption(redemptionPoints, transactionId);
                jLabelTotalPrice.setText(Double.toString(actualTotalPrice));
                JOptionPane.showMessageDialog(this, "Redemption success!", "Successful", JOptionPane.INFORMATION_MESSAGE);
                //poleDisplay(actualTotalPrice); 
                jFormattedTextFieldRedemption.setText("");
            }
        }
    }//GEN-LAST:event_jButtonRedemptionActionPerformed

    private void jButtonCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreditActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        CreditCard credit = new CreditCard(POSid, storeStaffId,transactionId,actualTotalPrice,this);
        credit.setVisible(true);
        credit.setExtendedState(JFrame.NORMAL);
    }//GEN-LAST:event_jButtonCreditActionPerformed

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
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCash;
    private javax.swing.JButton jButtonCredit;
    private javax.swing.JButton jButtonRedemption;
    private javax.swing.JFormattedTextField jFormattedTextFieldRedemption;
    private javax.swing.JLabel jLabelCurrent;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelRedemption;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotalPrice;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItemList;
    // End of variables declaration//GEN-END:variables

    private static Double caculateTotalPrice(java.lang.Long transactionId) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.caculateTotalPrice(transactionId);
    }

    private void loadTable() {
        try {
            transactionItemList = getTransactionItemList(transactionId);

            if (transactionItemList != null && transactionItemList.size() > 0) {
                Object[][] data = new Object[transactionItemList.size()][5];

                for (int i = 0; i < transactionItemList.size(); i++) {
                    TransactionItemEntity transactionItem = transactionItemList.get(i);
                    data[i][0] = transactionItem.getItemId();
                    data[i][1] = transactionItem.getItemName();
                    data[i][2] = transactionItem.getUnitPrice();
                    data[i][3] = transactionItem.getAmount();
                    data[i][4] = transactionItem.getTotalPrice();
                }

                Object[] columnNames = new Object[5];

                columnNames[0] = "Item ID";
                columnNames[1] = "Item Name";
                columnNames[2] = "Unit Price";
                columnNames[3] = "Amount";
                columnNames[4] = "Total Price";

                TableModel tableModel = new DefaultTableModel(data, columnNames);
                jTableItemList.setModel(tableModel);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unknown error has occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initPartnerPoleDisplay() {
        Enumeration commPortList = CommPortIdentifier.getPortIdentifiers();

        while (commPortList.hasMoreElements()) {
            CommPortIdentifier commPort = (CommPortIdentifier) commPortList.nextElement();

            if (commPort.getPortType() == CommPortIdentifier.PORT_SERIAL
                    && commPort.getName().equals(partnerPoleDisplayCOMPort)) {
                try {
                    serialPort = (SerialPort) commPort.open("POS", 5000);
                    partnerPoleDisplayOutputStream = serialPort.getOutputStream();
                } catch (PortInUseException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to initialize Partner Pole Display: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to initialize Partner Pole Display: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void poleDisplay(Double totalPrice) {
        byte[] clear = {0x0C};
        byte[] newLine = {0x0A};
        byte[] carriageReturn = {0x0D};
        byte[] message1 = new String("Total").getBytes();
        byte[] message2 = new String("S$" + String.valueOf(totalPrice)).getBytes();

        try {
            partnerPoleDisplayOutputStream.write(clear);
            partnerPoleDisplayOutputStream.write(message1);
            partnerPoleDisplayOutputStream.write(newLine);
            partnerPoleDisplayOutputStream.write(carriageReturn);
            partnerPoleDisplayOutputStream.write(message2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to write to Partner Pole Display: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static java.util.List<sessionbean.ocrm.TransactionItemEntity> getTransactionItemList(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getTransactionItemList(arg0);
    }

    private static void deleteUnfinishedTransaction(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.deleteUnfinishedTransaction(arg0);
    }

    private static StoreEntity getStoreByStaffId(java.lang.String storeStaffId) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getStoreByStaffId(storeStaffId);
    }

    private static TransactionEntity getTransactionById(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getTransactionById(arg0);
    }

    private static String getFullNameById(java.lang.String id) {
        util.login.IFManagerBeanService service = new util.login.IFManagerBeanService();
        util.login.IFManagerBean port = service.getIFManagerBeanPort();
        return port.getFullNameById(id);
    }

    private static java.util.List<sessionbean.ocrm.TransactionItemEntity> getTransactionItemList_1(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getTransactionItemList(arg0);
    }

    private static void redemption(java.lang.Double arg0, java.lang.Long arg1) {
        sessionbean.ocrm.MemberRegistrationModuleService service = new sessionbean.ocrm.MemberRegistrationModuleService();
        sessionbean.ocrm.MemberRegistrationModule port = service.getMemberRegistrationModulePort();
        port.redemption(arg0, arg1);
    }

    private static void addNewPointsForMember(java.lang.Double arg0, java.lang.Long arg1) {
        sessionbean.ocrm.MemberRegistrationModuleService service = new sessionbean.ocrm.MemberRegistrationModuleService();
        sessionbean.ocrm.MemberRegistrationModule port = service.getMemberRegistrationModulePort();
        port.addNewPointsForMember(arg0, arg1);
    }

    private static Double caculateRedemption(java.lang.Double arg0, java.lang.Long arg1) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.caculateRedemption(arg0, arg1);
    }

    private static void caculateChange(java.lang.Long transactionId, java.lang.Double moneyChange) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.caculateChange(transactionId, moneyChange);
    }

    private static MemberEntity getMemberById(java.lang.Long arg0) {
        sessionbean.ocrm.MemberRegistrationModuleService service = new sessionbean.ocrm.MemberRegistrationModuleService();
        sessionbean.ocrm.MemberRegistrationModule port = service.getMemberRegistrationModulePort();
        return port.getMemberById(arg0);
    }

    private static Calendar fromXMLGregorianCalendar(XMLGregorianCalendar xc)
            throws DatatypeConfigurationException {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(xc.toGregorianCalendar().getTimeInMillis());
        return c;
    }

}
