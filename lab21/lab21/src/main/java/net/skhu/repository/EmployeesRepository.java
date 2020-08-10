package net.skhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.skhu.domain.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Integer>  {


   @Query("SELECT e.id, CONCAT(e.lastName,',',e.firstName), e.jobTitle,e.businessPhone, e.emailAddress, e.city FROM Employees e")
    List<Employees>  findEmployees();

   @Query("SELECT e.id, CONCAT(e.lastName,',',e.firstName), e.jobTitle,e.businessPhone, e.emailAddress, e.city FROM Employees e WHERE e.city =?1 ")
   List<Employees> findByCity();
}

