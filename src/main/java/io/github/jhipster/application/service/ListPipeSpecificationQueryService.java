package io.github.jhipster.application.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.ListPipeSpecification;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListPipeSpecificationRepository;
import io.github.jhipster.application.service.dto.ListPipeSpecificationCriteria;
import io.github.jhipster.application.service.dto.ListPipeSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListPipeSpecificationMapper;

/**
 * Service for executing complex queries for {@link ListPipeSpecification} entities in the database.
 * The main input is a {@link ListPipeSpecificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListPipeSpecificationDTO} or a {@link Page} of {@link ListPipeSpecificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListPipeSpecificationQueryService extends QueryService<ListPipeSpecification> {

    private final Logger log = LoggerFactory.getLogger(ListPipeSpecificationQueryService.class);

    private final ListPipeSpecificationRepository listPipeSpecificationRepository;

    private final ListPipeSpecificationMapper listPipeSpecificationMapper;

    public ListPipeSpecificationQueryService(ListPipeSpecificationRepository listPipeSpecificationRepository, ListPipeSpecificationMapper listPipeSpecificationMapper) {
        this.listPipeSpecificationRepository = listPipeSpecificationRepository;
        this.listPipeSpecificationMapper = listPipeSpecificationMapper;
    }

    /**
     * Return a {@link List} of {@link ListPipeSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListPipeSpecificationDTO> findByCriteria(ListPipeSpecificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListPipeSpecification> specification = createSpecification(criteria);
        return listPipeSpecificationMapper.toDto(listPipeSpecificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListPipeSpecificationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipeSpecificationDTO> findByCriteria(ListPipeSpecificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListPipeSpecification> specification = createSpecification(criteria);
        return listPipeSpecificationRepository.findAll(specification, page)
            .map(listPipeSpecificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListPipeSpecificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListPipeSpecification> specification = createSpecification(criteria);
        return listPipeSpecificationRepository.count(specification);
    }

    /**
     * Function to convert ListPipeSpecificationCriteria to a {@link Specification}.
     */
    private Specification<ListPipeSpecification> createSpecification(ListPipeSpecificationCriteria criteria) {
        Specification<ListPipeSpecification> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListPipeSpecification_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListPipeSpecification_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListPipeSpecification_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListPipeSpecification_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListPipeSpecification_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListPipeSpecification_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListPipeSpecification_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListPipeSpecification_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListPipeSpecification_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListPipeSpecification_.editor));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListPipeSpecification_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
        }
        return specification;
    }
}
