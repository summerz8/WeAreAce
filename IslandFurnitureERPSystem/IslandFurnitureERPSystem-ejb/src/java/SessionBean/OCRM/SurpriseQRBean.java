/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.SurpriseQREntity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import eg.com.tm.barcode.processor.BarcodeEngine;
import eg.com.tm.barcode.processor.config.EncodeConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
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
            String resultPath = generateQRCodeImage(QR, qr.getId().toString());
            return resultPath;
        } else {
            return null;
        }
    }
//
    private String generateQRCodeImage(String QR, String fileName) {
        String path = "/Users/dan/Desktop/Project/QRCode" + fileName + ".png";
        File qrCodeFile = new File(path);

        EncodeConfig encodeConfig
                = new EncodeConfig.Builder().createDirectories(Boolean.TRUE)
                .isQRCodeFormat(Boolean.TRUE)
                .withErrorCorrLevel(ErrorCorrectionLevel.M).build();

        String content = QR;

        BarcodeEngine.encode(qrCodeFile, content, BarcodeFormat.QR_CODE, 200, 200, encodeConfig);

        encodeConfig
                = new EncodeConfig.Builder().createDirectories(Boolean.TRUE).
                withCharactersMode(Mode.ALPHANUMERIC).build();
        return path;
    }
    
    public Boolean checkSameQR(String QR) {

        try {
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
        } catch (NullPointerException ex) {
            ex.printStackTrace();
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

}
