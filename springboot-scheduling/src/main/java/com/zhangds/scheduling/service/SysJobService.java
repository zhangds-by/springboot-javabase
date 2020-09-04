package com.zhangds.scheduling.service;

import com.zhangds.scheduling.entities.SysJob;
import com.zhangds.scheduling.repository.SysJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysJobService {

    @Autowired
    private SysJobRepository repository;

    /**
     * 保存
     * @param sysJob
     * @return
     */
    @Transactional
    public SysJob saveJob(SysJob sysJob) {
        return repository.save(sysJob);
    }

    /**
     * 更新
     * @param sysJob
     * @return
     */
    @Transactional
    public SysJob updateJob(SysJob sysJob){
        return repository.save(sysJob);
    }

    public SysJob findById(Integer jobId){
        return repository.getOne(jobId);
    }

    public void deleteById(Integer jobId){
        this.findById(jobId);
    }

    /**
     * 条件查询 + 排序
     * @param searchMap
     * @return
     */
    public List<SysJob> findSearch(Map searchMap) {
        Specification<SysJob> specification = createSpecification(searchMap);
//        springboot2.2.1（含）以上的版本Sort已经不能再实例化
//        Sort sort = new Sort(Sort.Direction.ASC, "jobId");
        Sort sort = Sort.by(Sort.Direction.ASC, "jobId");
        return repository.findAll(specification, sort);
    }

    /**
     * 条件 + 分页 + 排序
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<SysJob> findSearch(Map searchMap, int page, int size) {
        Specification<SysJob> specification = createSpecification(searchMap);
        Sort sort = Sort.by(Sort.Direction.ASC, "age");
        PageRequest pageRequest = PageRequest.of(page - 1, size,sort);
        return repository.findAll(specification, pageRequest);
    }

    /**
     * 创建查询条件
     * @param searchMap
     * @return
     */
    private Specification<SysJob> createSpecification(Map searchMap) {
        return new Specification<SysJob>() {
            @Override
            public Predicate toPredicate(Root<SysJob> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> preList = new ArrayList<Predicate>();
                if (searchMap.get("beanName") != null && !(searchMap.get("beanName")).equals("")) {
                    preList.add(criteriaBuilder.like(root.get("beanName").as(String.class), "%" + searchMap.get("beanName") + "%"));
                }
                Predicate[] preArray = new Predicate[preList.size()];
                return criteriaBuilder.and(preList.toArray(preArray));
            }
        };
    }


}
