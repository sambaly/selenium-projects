package com.seleniumexpress.employeeapp.services;

import com.seleniumexpress.employeeapp.dtos.AddressDTO;
import com.seleniumexpress.employeeapp.dtos.EmployeeDTO;
import com.seleniumexpress.employeeapp.entities.Employee;
import com.seleniumexpress.employeeapp.openfeignclients.AddressClient;
import com.seleniumexpress.employeeapp.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private AddressClient addressClient;

    private WebClient webClient;
    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;

    @Value("${addressservice.base.url}")
    private String addressBaseUrl;

    // private DiscoveryClient discoveryClient;
    private LoadBalancerClient loadBalancerClient;

    private RestTemplate restTemplate;

    public EmployeeService(AddressClient addressClient,
                           WebClient webClient,
                           EmployeeRepository employeeRepository,
                           ModelMapper modelMapper,
                           RestTemplate restTemplate,
                           // DiscoveryClient discoveryClient
                           LoadBalancerClient loadBalancerClient
    ) {
        this.addressClient = addressClient;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.webClient = webClient;
        this.restTemplate = restTemplate;
        // this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
    }

    public EmployeeDTO getEmployeeDetails(int id) {
        Employee savedEmployee = employeeRepository.findById(id).get();
        EmployeeDTO employee = modelMapper.map(savedEmployee, EmployeeDTO.class);
        /* AddressDTO addressDTO = webClient
                .get()
                .uri("/address/" + id)
                .retrieve()
                .bodyToMono(AddressDTO.class)
                .block(); // callingAddressServiceUsingRestTemplate(id); */
        AddressDTO addressDTO = this.addressClient.getAddressByEmployeeId(id).getBody();
        employee.setAddressDTO(addressDTO);
        return employee;
    }

    private AddressDTO callingAddressServiceUsingRestTemplate(int id) {
        // List<ServiceInstance> instances = discoveryClient.getInstances("address-service");
        // ServiceInstance instance = instances.get(0);

        /* ServiceInstance instance = loadBalancerClient.choose("address-service");
        String contextRoot = instance.getMetadata().get("configPath");

        String URI = instance.getUri().toString(); */

        return restTemplate.getForObject("http://ADDRESS-SERVICE/address-app/api/address/{id}", AddressDTO.class, id);
    }

    public List<EmployeeDTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = List.of(modelMapper.map(employees, EmployeeDTO[].class));

        List<AddressDTO> addressDTOS = addressClient.getAddresses().getBody();

        employeeDTOS.forEach(
                employee -> {
                    assert addressDTOS != null;
                    for (AddressDTO addressDTO: addressDTOS) {
                        if (addressDTO.getId() == employee.getId())
                            employee.setAddressDTO(addressDTO);
                    }
                }
        );

        return employeeDTOS;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        AddressDTO addressDTO = employeeDTO.getAddressDTO();
        Employee savedEmployee;
        EmployeeDTO savedEmployeeDTO = null;
        ResponseEntity<AddressDTO> savedAddress = this.addressClient.saveAddress(addressDTO);
        if (savedAddress.getStatusCode().is2xxSuccessful()) {
            Employee employee = modelMapper.map(employeeDTO, Employee.class);
            savedEmployee = employeeRepository.save(employee);
            savedEmployeeDTO = modelMapper.map(savedEmployee, EmployeeDTO.class);
            savedEmployeeDTO.setAddressDTO(savedAddress.getBody());
        }
        return savedEmployeeDTO;
    }
}
