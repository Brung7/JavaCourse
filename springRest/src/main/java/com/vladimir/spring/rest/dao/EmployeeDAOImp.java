package com.vladimir.spring.rest.dao;

import com.vladimir.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImp implements EmployeeDAO{

    private final SessionFactory sessionFactory;

    public EmployeeDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override

    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();
        List<Employee> allEmployees = session.createQuery("from Employee",
                Employee.class
        ).getResultList();
        return allEmployees;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Employee emp = session.merge(employee);
        return emp;
    }

    @Override

    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        MutationQuery query = session.createMutationQuery("delete from Employee " + "where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();


    }
}
