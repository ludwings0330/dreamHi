package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Volunteer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long>, VolunteerRepositoryCustom {

    List<Volunteer> findByUserIdAndProcessId(Long userId, Long processId);

}