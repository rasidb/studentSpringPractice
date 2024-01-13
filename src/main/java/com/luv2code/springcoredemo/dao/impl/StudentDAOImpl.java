package com.luv2code.springcoredemo.dao.impl;

import com.luv2code.springcoredemo.dao.StudentDAO;
import com.luv2code.springcoredemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@code StudentDAOImpl}, {@link StudentDAO} arabirimini uygulayan bir sınıftır.
 * {@link Student} varlığı ile ilgili veri erişim işlemlerini yönetir.
 * Bu sınıf, Spring tarafından yönetilen bir veritabanı işlemleri deposu olduğunu belirtmek
 * amacıyla {@link Repository} ile işaretlenmiştir.
 */
@Repository
public class StudentDAOImpl implements StudentDAO {
    //define field for entity manager
    private EntityManager entityManager;


    //inject entity manager using constructor injection

    /**
     * {@link Autowired} anatasyonu  bağımlılıkları otomatik olarak enjekte etmek için kullanılır.
     * Bu örnekte, EntityManager tipindeki bağımlılığı constructor enjeksiyonu ile alır.
     * EntityManager, JPA (Java Persistence API) ile entegrasyon sağlar ve veritabanı işlemlerini yönetir.
     */
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //implement save method

    /**
     * {@link Transactional} anatasyonu bu method çağrıldığında bir transaksiyon başlatılır, metot tamamlanırsa commit edilir, bir hata olursa ise geri alınır.
     * Bu, işlemlerin atomik olmasını sağlar ve veritabanı bütünlüğünü korur.
     *
     * @param theStudent
     */
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    @Transactional
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public List<Student> findAll() {
        Query fromStudent = entityManager.createQuery("from Student");
        List<Student> resultList = fromStudent.getResultList();
        return resultList;
    }
}
