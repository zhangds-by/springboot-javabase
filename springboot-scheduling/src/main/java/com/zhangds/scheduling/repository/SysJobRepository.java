package com.zhangds.scheduling.repository;

import com.zhangds.scheduling.entities.SysJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysJobRepository extends JpaRepository<SysJob, Integer>, JpaSpecificationExecutor {
    List<SysJob> getSysJobListByStatus(int status);
}
