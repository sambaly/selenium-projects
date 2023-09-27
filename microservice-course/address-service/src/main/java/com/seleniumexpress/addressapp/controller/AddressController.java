package com.seleniumexpress.addressapp.controller;

import com.seleniumexpress.addressapp.dtos.AddressDTO;
import com.seleniumexpress.addressapp.services.AddressService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressDTO> getAddressByEmployeeId(@PathVariable("employeeId") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getEmployeeAddressById(id));
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses() {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddresses());
    }

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddress(addressDTO));
    }
}
