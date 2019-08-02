package com.klearn.hibernate.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.klearn.hibernate.config.AppConfig;
import com.klearn.hibernate.model.ContractEmp;
import com.klearn.hibernate.model.Employee;
import com.klearn.hibernate.model.PermEmp;
import com.klearn.hibernate.service.EmployeeService;

public class EmpRunner {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 
        EmployeeService service = (EmployeeService) context.getBean("employeeService");

        PermEmp permEmp = new PermEmp();
        permEmp.setName("Permesh");
        permEmp.setSalary("7lpa");
        
        ContractEmp contractEmp = new ContractEmp();
        contractEmp.setName("Kanthesh");
        contractEmp.setPayPerHour("Rs.200");
        
        Employee employee = new Employee();
        employee.setName("Justemp");
        
        service.saveEmployee(permEmp);
        service.saveEmployee(contractEmp);
        service.saveEmployee(employee);
	}

}
