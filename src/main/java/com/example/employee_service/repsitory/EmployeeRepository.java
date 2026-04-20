package com.example.employee_service.repsitory;

import com.example.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     boolean existsByFirstName(String firstName);

     // to get list of employees whose DOB is today, to send notification/celebration!
     @Query("""
        SELECT e FROM Employee e
        WHERE MONTH(e.dateOfBirth) = :month
        AND DAY(e.dateOfBirth) = :day
    """)
     List<Employee> findTodayBirthdays(
             @Param("month") int month,
             @Param("day") int day
     );
}
