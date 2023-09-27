package com.seleniumexpress.employeeapp.openfeignclients;

import com.seleniumexpress.employeeapp.dtos.AddressDTO;
import com.seleniumexpress.employeeapp.dtos.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api/")
public interface AddressClient {

    @GetMapping("/address/{id}")
    ResponseEntity<AddressDTO> getAddressByEmployeeId(@PathVariable("id") int id);

    @GetMapping("/addresses")
    ResponseEntity<List<AddressDTO>> getAddresses();

    @PostMapping("/addresses")
    ResponseEntity<AddressDTO> saveAddress(AddressDTO addressDTO);
}
