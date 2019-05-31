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

import io.github.jhipster.application.domain.ListPipelineLocation;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListPipelineLocationRepository;
import io.github.jhipster.application.service.dto.ListPipelineLocationCriteria;
import io.github.jhipster.application.service.dto.ListPipelineLocationDTO;
import io.github.jhipster.application.service.mapper.ListPipelineLocationMapper;

/**
 * Service for executing complex queries for {@link ListPipelineLocation} entities in the database.
 * The main input is a {@link ListPipelineLocationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListPipelineLocationDTO} or a {@link Page} of {@link ListPipelineLocationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListPipelineLocationQueryService extends QueryService<ListPipelineLocation> {

    private final Logger log = LoggerFactory.getLogger(ListPipelineLocationQueryService.class);

    private final ListPipelineLocationRepository listPipelineLocationRepository;

    private final ListPipelineLocationMapper listPipelineLocationMapper;

    public ListPipelineLocationQueryService(ListPipelineLocationRepository listPipelineLocationRepository, ListPipelineLocationMapper listPipelineLocationMapper) {
        this.listPipelineLocationRepository = listPipelineLocationRepository;
        this.listPipelineLocationMapper = listPipelineLocationMapper;
    }

    /**
     * Return a {@link List} of {@link ListPipelineLocationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListPipelineLocationDTO> findByCriteria(ListPipelineLocationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListPipelineLocation> specification = createSpecification(criteria);
        return listPipelineLocationMapper.toDto(listPipelineLocationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListPipelineLocationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipelineLocationDTO> findByCriteria(ListPipelineLocationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListPipelineLocation> specification = createSpecification(criteria);
        return listPipelineLocationRepository.findAll(specification, page)
            .map(listPipelineLocationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListPipelineLocationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListPipelineLocation> specification = createSpecification(criteria);
        return listPipelineLocationRepository.count(specification);
    }

    /**
     * Function to convert ListPipelineLocationCriteria to a {@link Specification}.
     */
    private Specification<ListPipelineLocation> createSpecification(ListPipelineLocationCriteria criteria) {
        Specification<ListPipelineLocation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListPipelineLocation_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListPipelineLocation_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListPipelineLocation_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListPipelineLocation_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListPipelineLocation_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListPipelineLocation_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListPipelineLocation_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListPipelineLocation_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListPipelineLocation_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListPipelineLocation_.editor));
            }
            if (criteria.getPipelineHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipelineHistId(),
                    root -> root.join(ListPipelineLocation_.pipelineHists, JoinType.LEFT).get(PipelineHist_.id)));
            }
        }
        return specification;
    }
}
