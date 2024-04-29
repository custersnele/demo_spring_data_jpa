package be.pxl.service;

import be.pxl.domain.Employee;
import be.pxl.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Employee insertEmployee(String name) {
        Employee employee = new Employee();
        employee.setName(name);
        employee = employeeRepository.save(employee);
        return employee;
    }
}
