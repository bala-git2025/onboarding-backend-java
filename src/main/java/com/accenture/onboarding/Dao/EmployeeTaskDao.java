package com.accenture.onboarding.Dao;
import com.accenture.onboarding.model.EmployeeTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeTaskDao extends JpaRepository<EmployeeTaskEntity, Long> {
    List<EmployeeTaskEntity> findByEmployeeId(Long employeeId);
    Optional<EmployeeTaskEntity> findByIdAndEmployeeId(Long id, Long employeeId);
}