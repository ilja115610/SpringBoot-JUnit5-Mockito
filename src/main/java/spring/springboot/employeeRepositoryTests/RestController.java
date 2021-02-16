package spring.springboot.employeeRepositoryTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.springboot.entity.Employee;
import spring.springboot.serviceIntegrationTests.EmployeeService;

import java.util.List;


@org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
public class RestController {


    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employees")
    public List<Employee> showEmployees () {

        return employeeService.showAllEmployees();
    }


    @GetMapping("/employee/{id}")
    public Employee findEmployee( @PathVariable String id) {

        return employeeService.findEmployee(Integer.parseInt(id));
    }


    @PostMapping("/addEmployee")
    public Employee addNewEmployee (@RequestBody Employee employee) {

        return employeeService.addEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    public Employee updateEmployee (@RequestBody Employee employee) {

        employeeService.updateEmployee(employee);

        return employee;
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee (@PathVariable int id ) {

        employeeService.deleteEmployee(id);

        return "Employee with ID "+id+" deleted";
    }

}
