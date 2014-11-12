/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandfurniturepos;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sessionbean.ocrm.TransactionItemEntity;

/**
 *
 * @author hangsun
 */
public class NewTransaction extends javax.swing.JFrame {

    private String POSid = null;
    private String partnerPoleDisplayCOMPort = "COM4";
    private OutputStream partnerPoleDisplayOutputStream;
    private SerialPort serialPort;
    private String storeStaffId = null;
    private int location;
    private Long memberId = null;
    private Long transactionId = null;
    private Long UUID = null;
    private int amount;
    private List<TransactionItemEntity> transactionItemList = new ArrayList();

    /**
     * Creates new form NewTransaction
     */
    public NewTransaction() {
        initComponents();
    }

    public NewTransaction(String POSid, String staffId, int location, Long memberId) {

        this();

        this.POSid = POSid;
        this.storeStaffId = staffId;
        this.location = location;
        this.memberId = memberId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItemList = new javax.swing.JTable();
        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanelInput = new javax.swing.JPanel();
        jLabelItemId = new javax.swing.JLabel();
        jTextFieldItemId = new javax.swing.JTextField();
        jLabelAmount = new javax.swing.JLabel();
        jFormattedTextFieldAmount = new javax.swing.JFormattedTextField();
        jButtonAddItem = new javax.swing.JButton();
        jPanelActionButton = new javax.swing.JPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonCheckOut = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        javax.swing.JButton jButtonDelete = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
        jTableItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableItemListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableItemList);

        jPanelTitle.setBackground(new java.awt.Color(255, 51, 51));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(705, 119));

        jLabelTitle.setFont(new java.awt.Font("New Peninim MT", 3, 48)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("                 Island Furniture POS");
        jLabelTitle.setToolTipText("");
        jLabelTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelInput.setBackground(new java.awt.Color(255, 255, 255));

        jLabelItemId.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jLabelItemId.setText("Item ID:");

        jTextFieldItemId.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jTextFieldItemId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldItemIdActionPerformed(evt);
            }
        });

        jLabelAmount.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jLabelAmount.setText("Amount: ");
        jLabelAmount.setToolTipText("");

        jFormattedTextFieldAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldAmount.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N

        jButtonAddItem.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAddItem.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonAddItem.setText("Add Item");
        jButtonAddItem.setToolTipText("");
        jButtonAddItem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInputLayout = new javax.swing.GroupLayout(jPanelInput);
        jPanelInput.setLayout(jPanelInputLayout);
        jPanelInputLayout.setHorizontalGroup(
            jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabelItemId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldItemId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelInputLayout.setVerticalGroup(
            jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelItemId)
                    .addComponent(jTextFieldItemId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelActionButton.setBackground(new java.awt.Color(255, 255, 255));

        jButtonCancel.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCancel.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonCancel.setText("Cancel");
        jButtonCancel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonCheckOut.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCheckOut.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonCheckOut.setText("Check Out");
        jButtonCheckOut.setToolTipText("");
        jButtonCheckOut.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckOutActionPerformed(evt);
            }
        });

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/singapore-logo0.1.jpg"))); // NOI18N

        jButtonDelete.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.setToolTipText("");
        jButtonDelete.setActionCommand("Delete");
        jButtonDelete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelActionButtonLayout = new javax.swing.GroupLayout(jPanelActionButton);
        jPanelActionButton.setLayout(jPanelActionButtonLayout);
        jPanelActionButtonLayout.setHorizontalGroup(
            jPanelActionButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelActionButtonLayout.createSequentialGroup()
                .addGap(0, 54, Short.MAX_VALUE)
                .addComponent(jLabelLogo))
            .addGroup(jPanelActionButtonLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelActionButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelActionButtonLayout.setVerticalGroup(
            jPanelActionButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionButtonLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addGap(36, 36, 36)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButtonCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelActionButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanelActionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddItemActionPerformed
        // TODO add your handling code here:

        if (jFormattedTextFieldAmount.getText().isEmpty() || jTextFieldItemId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please insert product id and amount!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkInput(jFormattedTextFieldAmount.getText())) {

                UUID = Long.parseLong(jTextFieldItemId.getText());
                amount = Integer.parseInt(jFormattedTextFieldAmount.getText());

                if (checkItem(UUID)) {
                    int itemType = checkItemType(UUID);
                    if (itemType != location) {
                        JOptionPane.showMessageDialog(this, "Item does not belong to current location!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        createTransactionItem(UUID, amount, transactionId);
                        List<TransactionItemEntity> transactionListTemp = getTransactionItemList(transactionId);
                        TransactionItemEntity temp = transactionListTemp.get(transactionListTemp.size() - 1);
                        //poleDisplay(temp.getItemName(), temp.getUnitPrice());
                        loadTable();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                jTextFieldItemId.setText("");
                jFormattedTextFieldAmount.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please input valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonAddItemActionPerformed

    private void jButtonCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckOutActionPerformed
        // TODO add your handling code here:

        if (transactionItemList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Transaction list is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            this.setVisible(false);
            this.dispose();
            jTextFieldItemId.setText("");
            CheckOut checkOut = new CheckOut(POSid, storeStaffId, transactionId);
            checkOut.setVisible(true);
            checkOut.setExtendedState(JFrame.NORMAL);
        }

    }//GEN-LAST:event_jButtonCheckOutActionPerformed

    private void jTextFieldItemIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldItemIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldItemIdActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         initPartnerPoleDisplay();

        transactionId = createTransaction(storeStaffId, memberId, location, POSid);

        jTextFieldItemId.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String kbValue = jTextFieldItemId.getText();

                if (kbValue != null && kbValue.trim().length() > 0) {
                    kbValue = kbValue.trim();
                    scanAndAddItem();
                }
            }
        });


    }//GEN-LAST:event_formWindowOpened

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        deleteUnfinishedTransaction(transactionId);
        this.setVisible(false);
        this.dispose();
        jTextFieldItemId.setText("");
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

    private void jTableItemListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableItemListMouseClicked

    }//GEN-LAST:event_jTableItemListMouseClicked

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTableItemList.getSelectionModel().getLeadSelectionIndex();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select one item!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            System.err.println("Row:" + selectedRow);
            transactionItemList = getTransactionItemList(transactionId);
            TransactionItemEntity selectedTransactionItem = transactionItemList.get(selectedRow);
            deleteTransactionItem(selectedTransactionItem.getTransactionItemId(), transactionId);
            loadTable();
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(NewTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewTransaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddItem;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCheckOut;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JFormattedTextField jFormattedTextFieldAmount;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabelAmount;
    private javax.swing.JLabel jLabelItemId;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelActionButton;
    private javax.swing.JPanel jPanelInput;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItemList;
    private javax.swing.JTextField jTextFieldItemId;
    // End of variables declaration//GEN-END:variables

    private static void createTransactionItem(java.lang.Long itemId, int amount, java.lang.Long transactionId) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.createTransactionItem(itemId, amount, transactionId);
    }

    private static Boolean checkItem(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.checkItem(arg0);
    }

    private static void deleteUnfinishedTransaction(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.deleteUnfinishedTransaction(arg0);
    }

    private Boolean checkInput(String amount) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher((CharSequence) amount);
        return matcher.matches();
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

                TableModel tableModel = new DefaultTableModel(data, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return true;
                    }

                    @Override
                    public void setValueAt(Object value, int row, int column) {
                        ChangeAmount(value, row);
                        System.err.println("row: " + row + "; column: " + column + "; value: " + value);
                        loadTable();
                    }
                };

                jTableItemList.setModel(tableModel);
                jTableItemList.getSelectionModel().addListSelectionListener(new RowListener());
            } else {
                Object[] columnNames = new Object[5];

                columnNames[0] = "Item ID";
                columnNames[1] = "Item Name";
                columnNames[2] = "Unit Price";
                columnNames[3] = "Amount";
                columnNames[4] = "Total Price";
                Object[][] data = new Object[1][6];

                TableModel tableModel = new DefaultTableModel(data, columnNames);
                jTableItemList.setModel(tableModel);
                jTableItemList.getSelectionModel().addListSelectionListener(new RowListener());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unknown error has occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class RowListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
        }
    }

    private void ChangeAmount(Object value, int row) {
        transactionItemList = getTransactionItemList(transactionId);
        TransactionItemEntity selectedTransactionItem = transactionItemList.get(row);
        int Amount = Integer.parseInt((String) value);
        changeAmount(selectedTransactionItem.getTransactionItemId(), Amount);
    }

    private void scanAndAddItem() {
        UUID = Long.parseLong(jTextFieldItemId.getText());

        if (checkItem(UUID)) {
            int itemType = checkItemType(UUID);
            if (itemType != location) {
                JOptionPane.showMessageDialog(this, "Item does not belong to current location!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                createTransactionItem(UUID, 1, transactionId);
                List<TransactionItemEntity> transactionListTemp = getTransactionItemList(transactionId);
                TransactionItemEntity temp = transactionListTemp.get(transactionListTemp.size() - 1);
                //poleDisplay(temp.getItemName(), temp.getUnitPrice());
                loadTable();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void poleDisplay(String itemName, Double itemPrice) {
        byte[] clear = {0x0C};
        byte[] newLine = {0x0A};
        byte[] carriageReturn = {0x0D};
        byte[] message1 = new String(itemName).getBytes();
        byte[] message2 = new String(String.valueOf(itemPrice)).getBytes();

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

    private static void changeAmount(java.lang.Long arg0, int arg1) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.changeAmount(arg0, arg1);
    }

    private static void deleteTransactionItem(java.lang.Long arg0, java.lang.Long arg1) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.deleteTransactionItem(arg0, arg1);
    }

    private static int checkItemType(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.checkItemType(arg0);
    }

    private static Long createTransaction(java.lang.String staffId, java.lang.Long memberId, int location, java.lang.String poSid) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.createTransaction(staffId, memberId, location, poSid);
    }

}
