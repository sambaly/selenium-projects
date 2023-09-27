package com.seleniumexpress.addressapp.repositories;

import com.seleniumexpress.addressapp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(nativeQuery = true, value = "SELECT ea.id, ea.lane1, ea.lane2, ea.state, ea.zip FROM address ea join employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
    public Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
