package com.bachhoaxanh;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("Dang khoi tao ket noi toi Neon.tech PostgreSQL...");
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            // "BachHoaXanhPU" la ten persistence-unit khai bao trong persistence.xml
            emf = Persistence.createEntityManagerFactory("BachHoaXanhPU");
            em = emf.createEntityManager();

            // Chay mot cau query don gian kiem tra ket noi truc tiep
            Object result = em.createNativeQuery("SELECT version()").getSingleResult();
            
            System.out.println("==================================================");
            System.out.println("KET NOI THANH CONG TOI NEON POSTGRESQL!");
            System.out.println("Thong tin database: " + result);
            System.out.println("==================================================");

        } catch (Exception e) {
            System.err.println("==================================================");
            System.err.println("KET NOI THAT BAI!");
            System.err.println("==================================================");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}