package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Volunteer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long>, VolunteerRepositoryCustom {

    List<Volunteer> findByUserIdAndProcessId(Long userId, Long processId);

    /**
     * 현재 채용 절차에 해당하는 지원자 수 조회
     */
    @Query("select count(v.id) from Volunteer v where v.process.id=:processId")
    Long countByCurrentProcessId(@Param("processId") Long processId);

}