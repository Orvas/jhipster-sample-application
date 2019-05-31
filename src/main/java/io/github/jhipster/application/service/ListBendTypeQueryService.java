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

import io.github.jhipster.application.domain.ListBendType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListBendTypeRepository;
import io.github.jhipster.application.service.dto.ListBendTypeCriteria;
import io.github.jhipster.application.service.dto.ListBendTypeDTO;
import io.github.jhipster.application.service.mapper.ListBendTypeMapper;

/**
 * Service for executing complex queries for {@link ListBendType} entities in the database.
 * The main input is a {@link ListBendTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListBendTypeDTO} or a {@link Page} of {@link ListBendTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListBendTypeQueryService extends QueryService<ListBendType> {

    private final Logger log = LoggerFactory.getLogger(ListBendTypeQueryService.class);

    private final ListBendTypeRepository listBendTypeRepository;

    private final ListBendTypeMapper listBendTypeMapper;

    public ListBendTypeQueryService(ListBendTypeRepository listBendTypeRepository, ListBendTypeMapper listBendTypeMapper) {
        this.listBendTypeRepository = listBendTypeRepository;
        this.listBendTypeMapper = listBendTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListBendTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListBendTypeDTO> findByCriteria(ListBendTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListBendType> specification = createSpecification(criteria);
        return listBendTypeMapper.toDto(listBendTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListBendTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBendTypeDTO> findByCriteria(ListBendTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListBendType> specification = createSpecification(criteria);
        return listBendTypeRepository.findAll(specification, page)
            .map(listBendTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListBendTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListBendType> specification = createSpecification(criteria);
        return listBendTypeRepository.count(specification);
    }

    /**
     * Function to convert ListBendTypeCriteria to a {@link Specification}.
     */
    private Specification<ListBendType> createSpecification(ListBendTypeCriteria criteria) {
        Specification<ListBendType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListBendType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListBendType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListBendType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListBendType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListBendType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListBendType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListBendType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListBendType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListBendType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListBendType_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListBendType_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
        }
        return specification;
    }
}
