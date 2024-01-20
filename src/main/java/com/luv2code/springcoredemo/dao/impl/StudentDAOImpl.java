package com.luv2code.springcoredemo.dao.impl;

import com.luv2code.springcoredemo.dao.StudentDAO;
import com.luv2code.springcoredemo.entity.Student;
import com.luv2code.springcoredemo.rest.exception.StudentBadRequestException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
    public List<Student> findById(Integer id) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where id=:id", Student.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> fromStudent = entityManager.createQuery("from Student",Student.class);
        return fromStudent.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where lastName=:theData", Student.class);
        query.setParameter("theData", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
     entityManager.merge(student);
    }

    @Override
    @Transactional
    public void removeStudent(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
    @Override
    @Transactional
    public int deleteAll() {
        return entityManager.createQuery("delete from Student").executeUpdate();
    }

    @Override
    @Transactional
    public int deleteByLastname(String lastname) {
        Query query = entityManager.createQuery("delete from Student where lastName=:lastname");
        query.setParameter("lastname", lastname);
        return query.executeUpdate();
    }

    @Override
    public List<Integer> getAllId() {
        return entityManager.createQuery("select id from Student",Integer.class).getResultList();
    }

    @Override
    @Transactional
    public void createStudent(Student student) {
       try {
           entityManager.persist(student);
           entityManager.flush();
           System.out.println("fdşsl");
       }catch (PersistenceException e){
           throw new StudentBadRequestException();
       }
    }

    @Override
    public List<Student> findByName(String patates) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where firstName = :patates", Student.class);
        query.setParameter("patates",patates);
        return query.getResultList();
    }

    @Override
    public List<Student> findByIdAndLastName(String firstName, Integer id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.id = :id and s.firstName = :firstName", Student.class);
        query.setParameter("id", id);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

}
