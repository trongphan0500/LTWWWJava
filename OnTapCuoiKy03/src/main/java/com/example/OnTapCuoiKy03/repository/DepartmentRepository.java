package com.example.OnTapCuoiKy03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OnTapCuoiKy03.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
