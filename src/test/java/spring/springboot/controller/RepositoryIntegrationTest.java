package spring.springboot.controller;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import spring.springboot.entity.Employee;
import spring.springboot.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryIntegrationTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void showEmployees() {

        List<Employee> employeeList = repository.findAll();
        assertThat(employeeList).size().isGreaterThan(0);
    }

    @Test
    void findEmployeeByIdExistingTest() {

        int id = 2;
        Optional<Employee> employee = repository.findById(id);
        assertThat(employee.orElse(null)).isNotNull();
    }

    @Test
    @Rollback(value = false)
    void createNewEmployeeTest() {

        Employee employee = new Employee("Bill","Farrel","Sales",1500);
        Employee newOne = repository.save(employee);
        assertNotNull(newOne);
    }

    @Test
    void findEmployeeByNameExistsTest () {

        String name = "Bill";
        Employee employee = repository.findByName(name);
        assertThat(employee.getName()).isEqualTo(name);
    }

    @Test
    void findEmployeeByNameNotExistTest () {

        String name = "Test";
        Employee employee = repository.findByName(name);
        assertNull(employee);
    }

    @Test
    @Rollback(value = false)
    void updateExistingEmployeeTest() {

        int salary = 2500;
        Employee existingEmployee = repository.findByName("Bill");
        existingEmployee.setSalary(salary);
        Employee updatedEmployee = repository.save(existingEmployee);
        assertThat(updatedEmployee.getSalary()).isEqualTo(salary);

    }

    @Test
    void deleteExistingEmployeeTest() {

        int id = 4;
        boolean isPresentBeforeDelete = repository.findById(id).isPresent();
        repository.deleteById(id);
        boolean notPresentAfterDelete = repository.findById(id).isPresent();

        assertTrue(isPresentBeforeDelete);
        assertFalse(notPresentAfterDelete);

    }
}