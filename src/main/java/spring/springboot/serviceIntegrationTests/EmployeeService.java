package spring.springboot.serviceIntegrationTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.springboot.entity.Employee;
import spring.springboot.exceptionHandling.NoEmployeeFoundException;
import spring.springboot.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> showAllEmployees () {

        return employeeRepository.findAll();
    }


    public Employee addEmployee(Employee employee) {
       return employeeRepository.save(employee);
    }

    public void deleteEmployee (int id) throws NoEmployeeFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new NoEmployeeFoundException("No such Employee found");
        }
        employeeRepository.deleteById(id);
    }


    public Employee findEmployee (int id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if(optional.isEmpty()){
            throw new NoEmployeeFoundException("No employee with this ID");
        }
        else
        return optional.get();
    }

    public Employee updateEmployee (Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee findOneByName (String name) {
        return employeeRepository.findByName(name);
    }

}
