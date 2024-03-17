package lk.ijse.CustomerManager.model;

import lk.ijse.CustomerManager.Entity.Customer;
import lk.ijse.CustomerManager.config.SessionFactoryConfig;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerModel {
    public boolean saveCustomer(Customer customer){
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    public Customer searchCustomer(int customerTel) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, customerTel);
        if (customer == null) {
            return null;
        }
        transaction.commit();
        Hibernate.initialize(customer.getCustomerTel());
        session.close();
        return customer;
    }

    public boolean updateCustomer(String customerId, Customer customer) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer existingCustomer = session.get(Customer.class, customerId);

        existingCustomer.setCustomerTel(customer.getCustomerTel());
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setCustomerAddress(customer.getCustomerAddress());
        existingCustomer.setCustomerId(customer.getCustomerId());

        session.update(existingCustomer);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean deleteCustomer(String customerTel) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, customerTel);
        session.delete(customer);
        transaction.commit();
        session.close();
        return true;
    }
}
