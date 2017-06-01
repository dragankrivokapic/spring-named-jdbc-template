package com.dragan.springdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.dragan.springdemo.dao.OrganizationDao;
import com.dragan.springdemo.domain.Organization;

@Component
public class NamedJdbcTemplateApp {

    @Autowired
    private OrganizationDao dao;

    @Autowired
    private DaoUtils daoUtils;

    public void actionMethod() {

	// createing seed data
	daoUtils.createSeedData(dao);
	// list organization
	List<Organization> orgs = dao.getAllOrganizations();
	daoUtils.printOrganizations(orgs, daoUtils.readOperation);

	// create a new organization record
	Organization org = new Organization("General Electric", 1978, "25659", 2565, "Imagination at work");
	boolean isCreated = dao.create(org);
	daoUtils.printSuccessFailure(daoUtils.createOperation, isCreated);
	daoUtils.printOrganizations(dao.getAllOrganizations(), daoUtils.readOperation);

	// get a single organization
	Organization org2 = dao.getOrganization(2);
	daoUtils.printOrganization(org2, " getOrganization");

	// update a slogan for an organization
	Organization org3 = dao.getOrganization(2);
	org3.setSlogan("We build **** awesome **** machines.");
	boolean isUpdate = dao.update(org3);
	daoUtils.printSuccessFailure(daoUtils.updateOperation, isUpdate);
	daoUtils.printOrganization(dao.getOrganization(2), daoUtils.updateOperation);

	// delete an organization
	//boolean isDeleted = dao.delete(dao.getOrganization(3));
	//daoUtils.printSuccessFailure(daoUtils.deleteOperation, isDeleted);
	//daoUtils.printOrganizations(dao.getAllOrganizations(), daoUtils.deleteOperation);

	// Cleanup
	//dao.cleanup();
	daoUtils.printOrganitationCount(dao.getAllOrganizations(), daoUtils.cleanUpOperation);

    }

    public static void main(String[] args) {

	// create application context
	ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-cp.xml");
	NamedJdbcTemplateApp mainApp = ctx.getBean(NamedJdbcTemplateApp.class);
	mainApp.actionMethod();

	// close app context
	((ClassPathXmlApplicationContext) ctx).close();
	// create the bean
	// OrganizationDao dao = (OrganizationDao) ctx.getBean("orgDao");

    }

}
