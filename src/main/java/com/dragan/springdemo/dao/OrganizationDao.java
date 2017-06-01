package com.dragan.springdemo.dao;

import java.util.List;

import javax.sql.DataSource;

import com.dragan.springdemo.domain.Organization;

public interface OrganizationDao {
    // create connection
    public void setDataSource(DataSource ds);

    // create a record of organization
    public boolean create(Organization org);

    // retrieve a single org
    public Organization getOrganization(Integer id);

    // retrive all org
    public List<Organization> getAllOrganizations();

    // delete specific org
    public boolean delete(Organization org);

    // update an existing org
    public boolean update(Organization org);
    
    public void cleanup();	
}
