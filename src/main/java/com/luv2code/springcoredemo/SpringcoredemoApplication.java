package com.luv2code.springcoredemo;

import com.github.javafaker.Faker;
import com.luv2code.springcoredemo.dao.StudentDAO;
import com.luv2code.springcoredemo.dao.impl.StudentDAOImpl;
import com.luv2code.springcoredemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot uygulamasının ana sınıfıdır.
 */
@SpringBootApplication
public class SpringcoredemoApplication {

    /**
     * Uygulamanın başlangıcını temsil eden main metodu.
     *
     * @param args Komut satırından alınan argümanlar.
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }

    /**
     * Spring Bean'ini tanımlayan metot.
     *
     * @param studentDAO Öğrenci veritabanı işlemlerini gerçekleştiren DAO arayüzü.
     * @return CommandLineRunner arayüzünü uygulayan bir nesne.
     */
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //createStudent(studentDAO);
            getAllStudents(studentDAO);
        };
    }

    private void getAllStudents(StudentDAO studentDAO) {
        System.out.println(studentDAO.findAll());
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        Student student;
        Faker faker = new Faker();
        String firstName;
        String lastName;
        String email;
        for (int i = 0; i < 100; i++) {
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            email = firstName + lastName + "@gmail.com";
            student = new Student(firstName, lastName, email);
            studentDAO.save(student);
        }
    }

    /**
     * Bir öğrenci oluşturan ve veritabanına kaydeden metot.
     *
     * @param studentDAO Öğrenci veritabanı işlemlerini gerçekleştiren DAO arayüzü.
     */
    private void createStudent(StudentDAO studentDAO) {
        // Öğrenci nesnesi oluştur
        Student student = new Student("chuck", "norris", "chuck58@hotmail.com");

        // Öğrenci nesnesini kaydet
        studentDAO.save(student);

        // Kaydedilen öğrencinin kimliğini ekrana yazdır
        System.out.println("Student id = " + student.getId());
    }

    private void readStudent(StudentDAO studentDAO){
        Student student =new Student("rasid","babamgul","woodenspoon@gmail.com");
        studentDAO.save(student);
        Student myStudent =studentDAO.findById(student.getId());
        System.out.println(myStudent);
    }

}
