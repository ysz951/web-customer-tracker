package com.luv2code.springdemo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer", Customer.class);
        List<Customer> customers = theQuery.getResultList();
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        // TODO Auto-generated method stub
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save or update customer
        currentSession.saveOrUpdate(theCustomer);

    }

    @Override
    public Customer getCustomer(int theId) {
        // TODO Auto-generated method stub
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve from database using the primary key
        Customer theCustomer = currentSession.get(Customer.class, theId);
        return theCustomer;

    }

    @Override
    public void deleteCustomer(int theId) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =
                currentSession.createQuery("delete from Customer where id=:theCustomerId");
        theQuery.setParameter("theCustomerId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public List<Customer> getCustomersByName(String theSearchName) {
        // TODO Auto-generated method stub
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.trim().toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;

    }

}
