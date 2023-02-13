package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.BookPeriod;
import java.util.Optional;

public interface BookRepositoryCustom {


    Optional<BookPeriod> findBookPeriodByProcessId(Long processId);

}
