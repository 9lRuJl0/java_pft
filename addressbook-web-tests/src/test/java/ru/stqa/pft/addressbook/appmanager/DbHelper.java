package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }
    // Получение списка групп из бд
    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list(); //where deprecated='0000-00-00'
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    // Получение списка контактов из бд
//    public Contacts contacts() {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<ContactData> result = session.createQuery("from ContactData where deprecated='0000-00-00'").list(); //where deprecated='0000-00-00'  //where deprecated ='0000-00-00 00:00:00'
//        session.getTransaction().commit();
//        session.close();
//        return new Contacts(result);


    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);


    }
}
