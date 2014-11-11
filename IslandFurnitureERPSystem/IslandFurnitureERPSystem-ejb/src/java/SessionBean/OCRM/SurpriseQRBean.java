/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.SurpriseQREntity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateless
public class SurpriseQRBean implements SurpriseQRBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    public SurpriseQRBean() {
    }

    @Override
    public String createQR(Double percentage, Double rewardPoints, Calendar expireDate, String name) {

        String QR;
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        QR = sb.toString();
        System.out.println("createQR(): " + QR);
        if (checkSameQR(QR)) {
            SurpriseQREntity qr = new SurpriseQREntity(name, QR, percentage, rewardPoints, expireDate);
            em.persist(qr);
            em.flush();

            return QR;
        } else {
            return null;
        }
    }

    public Boolean checkSameQR(String QR) {
        Query q = em.createQuery("SELECT m FROM SurpriseQREntity m WHERE m.randomString=:QR");
        q.setParameter("QR", QR);

        try {
            SurpriseQREntity sqe = (SurpriseQREntity) q.getSingleResult();
            if (sqe != null) {
                return false;
            } else {
                return true;
            }
        } catch (NoResultException e) {
            return true;
        }
    }
//    //return false if qr code is already created
//    
//    @Override
//    public Boolean checkQR(Calendar date) {
//        List<SurpriseQREntity> qrList = getAllQR();
//        if (!qrList.isEmpty()) {
//            for (SurpriseQREntity qr : qrList) {
//                Calendar expireDate = qr.getExpireDate();
//
//                if (expireDate.get(Calendar.YEAR) == date.get(Calendar.YEAR)
//                        && expireDate.get(Calendar.MONTH) == date.get(Calendar.MONTH)
//                        && expireDate.get(Calendar.DATE) == date.get(Calendar.DATE)) {
//                    return false;
//                }
//
//            }
//            return true;
//        } else {
//            return true;
//        }
//    }

    @Override
    public List<SurpriseQREntity> getAllQR() {
        Query q = em.createQuery("SELECT q FROM SurpriseQREntity q");
        List<SurpriseQREntity> QRList = new ArrayList();
        for (Object o : q.getResultList()) {
            SurpriseQREntity qr = (SurpriseQREntity) o;
            QRList.add(qr);
        }
        return QRList;
    }

    public void generateImage(String QRstr) {
        String myCodeText = QRstr;
        String filePath = "/Users/arpit/Documents/eclipsewp/CrunchifyQR.png";
        int size = 125;
        String fileType = "png";
        File myFile = new File(filePath);
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
 
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
 
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
    } 
}
