package com.seleniumexpress.addressapp.services;

import com.seleniumexpress.addressapp.dtos.AddressDTO;
import com.seleniumexpress.addressapp.entities.Address;
import com.seleniumexpress.addressapp.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    public AddressService(AddressRepository addressRepository,
                          ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    public AddressDTO getEmployeeAddressById(int employeeId) {
        Address savedAddress = addressRepository.findAddressByEmployeeId(employeeId);
        AddressDTO address = modelMapper.map(savedAddress, AddressDTO.class);
        return address;
    }

    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOS = List.of(modelMapper.map(addresses, AddressDTO[].class));

        return addressDTOS;
    }

    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address savedAddress = addressRepository.save(address);

        AddressDTO addressToBeSent = modelMapper.map(savedAddress, AddressDTO.class);
        return addressToBeSent;
    }
}
