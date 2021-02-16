package spring.springboot.serviceIntegrationTests;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import spring.springboot.entity.Employee;
import spring.springboot.exceptionHandling.NoEmployeeFoundException;
import spring.springboot.repository.EmployeeRepository;

import java.util.Optional;


@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository repository;

    private Employee existingEmployee;


    @TestConfiguration
    static class EmployeeServiceContextConfig {

        @Bean
        public EmployeeService getEmployeeService () {
            return new EmployeeService();
        }
    }


    @BeforeEach
    public void setUp() {
        existingEmployee = new Employee("John","Smith","HR",1800);

    }

    @Test
    void deleteNotExistingEmployeeTest () {
        int id = 100;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoEmployeeFoundException.class, () ->{
            employeeService.deleteEmployee(id);
        });
    }


    @Test
    void deleteExistingEmployeeTest() {

        int id = 2;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(existingEmployee));
        assertDoesNotThrow(()-> employeeService.deleteEmployee(id));
    }

    @Test
    void findByNameExistingEmployeeTest() {

        Mockito.when(repository.findByName(existingEmployee.getName())).thenReturn(existingEmployee);
        Employee found = employeeService.findOneByName(existingEmployee.getName());
        assertThat(found.getName())
                .isEqualTo(existingEmployee.getName());

    }

    @Test
    void findByIdNotExistingUser () {

        int id = 100;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoEmployeeFoundException.class, () -> employeeService.findEmployee(id)
                );
    }




}