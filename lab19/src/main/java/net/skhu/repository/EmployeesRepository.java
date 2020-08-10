package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Integer>  {

  /*  @Query("SELECT e.id,e.mailAddress,e.jobTitle,e.businessPhone,e.city CONCAT(e.firstName,e.lastName) AS e.name FROM Employees e")
    List<Employees> findAllName();
*/
}

