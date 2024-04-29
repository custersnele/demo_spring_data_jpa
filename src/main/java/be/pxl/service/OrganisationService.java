package be.pxl.service;

import be.pxl.domain.Employee;
import be.pxl.domain.Organisation;
import be.pxl.exception.NotFoundException;
import be.pxl.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganisationService {

    private final OrganizationRepository organizationRepository;
    private final EmployeeService employeeService;


    public OrganisationService(OrganizationRepository organizationRepository, EmployeeService employeeService) {
        this.organizationRepository = organizationRepository;
        this.employeeService = employeeService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void joinOrganisation(String organisationName, String employeeName) {
        Organisation organisation = organizationRepository.findByName(organisationName).orElseThrow(() -> new NotFoundException("Not found"));
        Employee employee = employeeService.insertEmployee(employeeName);
        employee.setOrganisation(organisation);
        throw new RuntimeException("Intentional error");
    }
}
