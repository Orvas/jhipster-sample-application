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

import io.github.jhipster.application.domain.MetaList;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.MetaListRepository;
import io.github.jhipster.application.service.dto.MetaListCriteria;
import io.github.jhipster.application.service.dto.MetaListDTO;
import io.github.jhipster.application.service.mapper.MetaListMapper;

/**
 * Service for executing complex queries for {@link MetaList} entities in the database.
 * The main input is a {@link MetaListCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MetaListDTO} or a {@link Page} of {@link MetaListDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MetaListQueryService extends QueryService<MetaList> {

    private final Logger log = LoggerFactory.getLogger(MetaListQueryService.class);

    private final MetaListRepository metaListRepository;

    private final MetaListMapper metaListMapper;

    public MetaListQueryService(MetaListRepository metaListRepository, MetaListMapper metaListMapper) {
        this.metaListRepository = metaListRepository;
        this.metaListMapper = metaListMapper;
    }

    /**
     * Return a {@link List} of {@link MetaListDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MetaListDTO> findByCriteria(MetaListCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MetaList> specification = createSpecification(criteria);
        return metaListMapper.toDto(metaListRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MetaListDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MetaListDTO> findByCriteria(MetaListCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MetaList> specification = createSpecification(criteria);
        return metaListRepository.findAll(specification, page)
            .map(metaListMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MetaListCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MetaList> specification = createSpecification(criteria);
        return metaListRepository.count(specification);
    }

    /**
     * Function to convert MetaListCriteria to a {@link Specification}.
     */
    private Specification<MetaList> createSpecification(MetaListCriteria criteria) {
        Specification<MetaList> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MetaList_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), MetaList_.name));
            }
            if (criteria.getSchemaName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSchemaName(), MetaList_.schemaName));
            }
            if (criteria.getTableName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTableName(), MetaList_.tableName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), MetaList_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MetaList_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), MetaList_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), MetaList_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), MetaList_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), MetaList_.editor));
            }
        }
        return specification;
    }
}
