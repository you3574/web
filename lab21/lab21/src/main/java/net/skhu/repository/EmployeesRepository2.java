package net.skhu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Employees;
import net.skhu.model.Pagination;

public interface EmployeesRepository2  extends JpaRepository<Employees, Integer> {

    public Page<Employees> findByCity(String city, Pageable pageable);

    public default List<Employees> findByCity(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPg() - 1, pagination.getSz(),
                                           new Sort(Sort.Direction.ASC, "studentNo"));
        Page<Employees> page = findByCity(pagination.getDi(), pageable);
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }


}

