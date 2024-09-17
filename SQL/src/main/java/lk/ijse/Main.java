package lk.ijse;

import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import lk.ijse.entity.Batch;
import lk.ijse.entity.Student;
import org.hibernate.Transaction;
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

        Batch batch = new Batch(2001, "B001", "Software Engineering", null);

        batch.setStudents(students);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(batch);

        session.save(student1);
        session.save(student2);
        session.save(student3);

        //SQL Query - INSERT
        Query query = session.createNativeQuery("INSERT INTO student VALUES(?,?,?,?)");
        query.setParameter(1, 1004);
        query.setParameter(2, "Kamal Perera");
        query.setParameter(3, 26);
        query.setParameter(4, "Kurunegala");

        //SQL Query - DELETE
        Query query1 = session.createNativeQuery("DELETE FROM student WHERE sid=?");
        query1.setParameter(1, 1002);

        //SQL Query - UPDATE
        Query query2 = session.createNativeQuery("UPDATE student SET sname=? WHERE sid=?");
        query2.setParameter(1, "Janith Perera");
        query2.setParameter(2, 1001);

        //SQL Query - search by column name
        Query query3 = session.createNativeQuery("SELECT * FROM student WHERE age=?");
        query3.setParameter(1, 24);

        //SQL Query - join query
        Query query4 = session.createNativeQuery("SELECT * FROM student s INNER JOIN batch b ON s.bid=b.bid");

        transaction.commit();
        session.close();
    }
}