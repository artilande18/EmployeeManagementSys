package com.pr.sp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements  EmployeeService {
    
    @Autowired
    private EmpRepository empRepository;

    @Override
    public String createEmployee(Employee employee) {
        EmpEntity empEntity = new EmpEntity();
        BeanUtils.copyProperties(employee, empEntity);

        empRepository.save(empEntity);
        // employees.add(employee);
        return "Saved Succesfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmpEntity empEntity = empRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(empEntity, employee);

        return employee;
    }


    @Override
    public List<Employee> readEmployees() {
        List<EmpEntity> employeesList =  empRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for(EmpEntity empEntity : employeesList){

            Employee emp = new Employee();
            emp.setId(empEntity.getId());
            emp.setName(empEntity.getName());
            emp.setEmail(empEntity.getEmail());
            emp.setPhone(empEntity.getPhone());

            employees.add(emp);

        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmpEntity emp = empRepository.findById(id).get();
        empRepository.delete(emp);
        return true;
    }
    
    @Override
    public String updateEmployee(Long id, Employee employee){
        EmpEntity exestingEmployee = empRepository.findById(id).get();

        // exestingEmployee.setId(employee.getId());
        exestingEmployee.setName(employee.getName());
        exestingEmployee.setEmail(employee.getEmail());
        exestingEmployee.setPhone(employee.getPhone());
        
        empRepository.save(exestingEmployee);
        return "Update Succesfully";
    }

}
