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

import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.AnodeHistRepository;
import io.github.jhipster.application.service.dto.AnodeHistCriteria;
import io.github.jhipster.application.service.dto.AnodeHistDTO;
import io.github.jhipster.application.service.mapper.AnodeHistMapper;

/**
 * Service for executing complex queries for {@link AnodeHist} entities in the database.
 * The main input is a {@link AnodeHistCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnodeHistDTO} or a {@link Page} of {@link AnodeHistDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnodeHistQueryService extends QueryService<AnodeHist> {

    private final Logger log = LoggerFactory.getLogger(AnodeHistQueryService.class);

    private final AnodeHistRepository anodeHistRepository;

    private final AnodeHistMapper anodeHistMapper;

    public AnodeHistQueryService(AnodeHistRepository anodeHistRepository, AnodeHistMapper anodeHistMapper) {
        this.anodeHistRepository = anodeHistRepository;
        this.anodeHistMapper = anodeHistMapper;
    }

    /**
     * Return a {@link List} of {@link AnodeHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnodeHistDTO> findByCriteria(AnodeHistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AnodeHist> specification = createSpecification(criteria);
        return anodeHistMapper.toDto(anodeHistRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AnodeHistDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnodeHistDTO> findByCriteria(AnodeHistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AnodeHist> specification = createSpecification(criteria);
        return anodeHistRepository.findAll(specification, page)
            .map(anodeHistMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnodeHistCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AnodeHist> specification = createSpecification(criteria);
        return anodeHistRepository.count(specification);
    }

    /**
     * Function to convert AnodeHistCriteria to a {@link Specification}.
     */
    private Specification<AnodeHist> createSpecification(AnodeHistCriteria criteria) {
        Specification<AnodeHist> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AnodeHist_.id));
            }
            if (criteria.getDateFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFrom(), AnodeHist_.dateFrom));
            }
            if (criteria.getDateTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTo(), AnodeHist_.dateTo));
            }
            if (criteria.getIdWrk() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdWrk(), AnodeHist_.idWrk));
            }
            if (criteria.getDesignLife() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignLife(), AnodeHist_.designLife));
            }
            if (criteria.getDmcd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDmcd(), AnodeHist_.dmcd));
            }
            if (criteria.getl1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getl1(), AnodeHist_.l1));
            }
            if (criteria.getl2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getl2(), AnodeHist_.l2));
            }
            if (criteria.getLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLength(), AnodeHist_.length));
            }
            if (criteria.getElectrCapac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getElectrCapac(), AnodeHist_.electrCapac));
            }
            if (criteria.getDesignWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignWeight(), AnodeHist_.designWeight));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), AnodeHist_.weight));
            }
            if (criteria.getIsBurial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsBurial(), AnodeHist_.isBurial));
            }
            if (criteria.getChemicalComposition() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChemicalComposition(), AnodeHist_.chemicalComposition));
            }
            if (criteria.getDensity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDensity(), AnodeHist_.density));
            }
            if (criteria.getSpacing() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpacing(), AnodeHist_.spacing));
            }
            if (criteria.getCoatCutbackLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoatCutbackLength(), AnodeHist_.coatCutbackLength));
            }
            if (criteria.getCoatDmgArea() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoatDmgArea(), AnodeHist_.coatDmgArea));
            }
            if (criteria.geth2sSoil() != null) {
                specification = specification.and(buildRangeSpecification(criteria.geth2sSoil(), AnodeHist_.h2sSoil));
            }
            if (criteria.getRemain() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRemain(), AnodeHist_.remain));
            }
            if (criteria.getIntFldTemp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIntFldTemp(), AnodeHist_.intFldTemp));
            }
            if (criteria.getMinPrc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMinPrc(), AnodeHist_.minPrc));
            }
            if (criteria.getThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThickness(), AnodeHist_.thickness));
            }
            if (criteria.getCoord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoord(), AnodeHist_.coord));
            }
            if (criteria.getKpStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpStart(), AnodeHist_.kpStart));
            }
            if (criteria.getCoatThickness() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoatThickness(), AnodeHist_.coatThickness));
            }
            if (criteria.getKpEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKpEnd(), AnodeHist_.kpEnd));
            }
            if (criteria.getIsCurrentFlag() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsCurrentFlag(), AnodeHist_.isCurrentFlag));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), AnodeHist_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), AnodeHist_.comment));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), AnodeHist_.dateCreate));
            }
            if (criteria.getDateEdit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateEdit(), AnodeHist_.dateEdit));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreator(), AnodeHist_.creator));
            }
            if (criteria.getEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditor(), AnodeHist_.editor));
            }
            if (criteria.getAnodeId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnodeId(),
                    root -> root.join(AnodeHist_.anode, JoinType.LEFT).get(Anode_.id)));
            }
            if (criteria.getIdPipelineSectionId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdPipelineSectionId(),
                    root -> root.join(AnodeHist_.idPipelineSection, JoinType.LEFT).get(PipelineSection_.id)));
            }
            if (criteria.getIdBraceleteTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdBraceleteTypeId(),
                    root -> root.join(AnodeHist_.idBraceleteType, JoinType.LEFT).get(ListAnodeBraceleteType_.id)));
            }
            if (criteria.getIdMaterialId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdMaterialId(),
                    root -> root.join(AnodeHist_.idMaterial, JoinType.LEFT).get(ListMaterial_.id)));
            }
            if (criteria.getIdStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdStatusId(),
                    root -> root.join(AnodeHist_.idStatus, JoinType.LEFT).get(ListWrkStatus_.id)));
            }
        }
        return specification;
    }
}
