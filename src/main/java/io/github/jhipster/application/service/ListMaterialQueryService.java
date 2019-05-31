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

import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ListMaterialRepository;
import io.github.jhipster.application.service.dto.ListMaterialCriteria;
import io.github.jhipster.application.service.dto.ListMaterialDTO;
import io.github.jhipster.application.service.mapper.ListMaterialMapper;

/**
 * Service for executing complex queries for {@link ListMaterial} entities in the database.
 * The main input is a {@link ListMaterialCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListMaterialDTO} or a {@link Page} of {@link ListMaterialDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListMaterialQueryService extends QueryService<ListMaterial> {

    private final Logger log = LoggerFactory.getLogger(ListMaterialQueryService.class);

    private final ListMaterialRepository listMaterialRepository;

    private final ListMaterialMapper listMaterialMapper;

    public ListMaterialQueryService(ListMaterialRepository listMaterialRepository, ListMaterialMapper listMaterialMapper) {
        this.listMaterialRepository = listMaterialRepository;
        this.listMaterialMapper = listMaterialMapper;
    }

    /**
     * Return a {@link List} of {@link ListMaterialDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListMaterialDTO> findByCriteria(ListMaterialCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListMaterial> specification = createSpecification(criteria);
        return listMaterialMapper.toDto(listMaterialRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListMaterialDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListMaterialDTO> findByCriteria(ListMaterialCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListMaterial> specification = createSpecification(criteria);
        return listMaterialRepository.findAll(specification, page)
            .map(listMaterialMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListMaterialCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListMaterial> specification = createSpecification(criteria);
        return listMaterialRepository.count(specification);
    }

    /**
     * Function to convert ListMaterialCriteria to a {@link Specification}.
     */
    private Specification<ListMaterial> createSpecification(ListMaterialCriteria criteria) {
        Specification<ListMaterial> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListMaterial_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ListMaterial_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ListMaterial_.name));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), ListMaterial_.fullName));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), ListMaterial_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ListMaterial_.description));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ListMaterial_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), ListMaterial_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), ListMaterial_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), ListMaterial_.editor));
            }
            if (criteria.getAnodeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnodeHistId(),
                    root -> root.join(ListMaterial_.anodeHists, JoinType.LEFT).get(AnodeHist_.id)));
            }
            if (criteria.getBendHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBendHistId(),
                    root -> root.join(ListMaterial_.bendHists, JoinType.LEFT).get(BendHist_.id)));
            }
            if (criteria.getBuckleArrestorHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuckleArrestorHistId(),
                    root -> root.join(ListMaterial_.buckleArrestorHists, JoinType.LEFT).get(BuckleArrestorHist_.id)));
            }
            if (criteria.getPipeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipeHistId(),
                    root -> root.join(ListMaterial_.pipeHists, JoinType.LEFT).get(PipeHist_.id)));
            }
            if (criteria.getPipejointHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getPipejointHistId(),
                    root -> root.join(ListMaterial_.pipejointHists, JoinType.LEFT).get(PipejointHist_.id)));
            }
            if (criteria.getTeeHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeeHistId(),
                    root -> root.join(ListMaterial_.teeHists, JoinType.LEFT).get(TeeHist_.id)));
            }
            if (criteria.getValveHistId() != null) {
                specification = specification.and(buildSpecification(criteria.getValveHistId(),
                    root -> root.join(ListMaterial_.valveHists, JoinType.LEFT).get(ValveHist_.id)));
            }
        }
        return specification;
    }
}
