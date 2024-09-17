package lk.ijse;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Batch;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1001, "Amal Perera", 24, "Colombo", null);
        Student student2 = new Student(1002, "Sunil Perera", 24, "Kandy", null);
        Student student3 = new Student(1003, "Nimal Perera", 25, "Galle", null);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        Batch batch = new Batch();
        batch.setBid(2001);
        batch.setBname("B001");
        batch.setBdescription("Software Engineering");
        batch.setStudents(students);

        student1.setBatch(batch);
        student2.setBatch(batch);
        student3.setBatch(batch);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        //HQL Query - INSERT

        session.save(batch);

        session.save(student1);
        session.save(student2);
        session.save(student3);

        transaction.commit();
        session.close();

        Session session2 = FactoryConfiguration.getInstance().getSession();
        Transaction transaction2 = session2.beginTransaction();


        //HQL Query - INSERT(not recommended)
        Query query = session2.createQuery("INSERT INTO Student (sid, sname, age, address) SELECT :sid, :sname, :age, :address FROM Student");
        query.setParameter("sid", 1004);
        query.setParameter("sname", "Kamal Perera");
        query.setParameter("age", 26);
        query.setParameter("address", "Kurunegala");

        System.out.println("Inserted : "+query.executeUpdate());

        //HQL Query - DELETE
        Query query1 = session2.createQuery("DELETE FROM Student WHERE sid=:sid");
        query1.setParameter("sid", 1004);

        System.out.println("Deleted : "+query1.executeUpdate());

        //HQL Query - UPDATE


        // HQL Query - search by column name



        //HQL Query - join query


        transaction2.commit();
        session2.close();

    }
}