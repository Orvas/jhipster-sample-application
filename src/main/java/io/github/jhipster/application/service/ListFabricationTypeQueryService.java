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

import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListFabricationTypeRepository;
import io.github.jhipster.application.service.dto.ListFabricationTypeCriteria;
import io.github.jhipster.application.service.dto.ListFabricationTypeDTO;
import io.github.jhipster.application.service.mapper.ListFabricationTypeMapper;

/**
 * Service for executing complex queries for {@link ListFabricationType} entities in the database.
 * The main input is a {@link ListFabricationTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListFabricationTypeDTO} or a {@link Page} of {@link ListFabricationTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListFabricationTypeQueryService extends QueryService<ListFabricationType> {

    private final Logger log = LoggerFactory.getLogger(ListFabricationTypeQueryService.class);

    private final ListFabricationTypeRepository listFabricationTypeRepository;

    private final ListFabricationTypeMapper listFabricationTypeMapper;

    public ListFabricationTypeQueryService(ListFabricationTypeRepository listFabricationTypeRepository, ListFabricationTypeMapper listFabricationTypeMapper) {
        this.listFabricationTypeRepository = listFabricationTypeRepository;
        this.listFabricationTypeMapper = listFabricationTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ListFabricationTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListFabricationTypeDTO> findByCriteria(ListFabricationTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListFabricationType> specification = createSpecification(criteria);
        return listFabricationTypeMapper.toDto(listFabricationTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListFabricationTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListFabricationTypeDTO> findByCriteria(ListFabricationTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListFabricationType> specification = createSpecification(criteria);
        return listFabricationTypeRepository.findAll(specification, page)
            .map(listFabricationTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListFabricationTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListFabricationType> specification = createSpecification(criteria);
        return listFabricationTypeRepository.count(specification);
    }

    /**
     * Function to convert ListFabricationTypeCriteria to a {@link Specification}.
     */
    private Specification<ListFabricationType> createSpecification(ListFabricationTypeCriteria criteria) {
        Specification<ListFabricationType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListFabricationType_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListFabricationType_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListFabricationType_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListFabricationType_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListFabricationType_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListFabricationType_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListFabricationType_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListFabricationType_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListFabricationType_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListFabricationType_.editor));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListFabricationType_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListFabricationType_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListFabricationType_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListFabricationType_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListFabricationType_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
