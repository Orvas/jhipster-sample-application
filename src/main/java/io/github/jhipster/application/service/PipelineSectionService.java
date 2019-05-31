package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.repository.PipelineSectionRepository;
import io.github.jhipster.application.service.dto.PipelineSectionDTO;
import io.github.jhipster.application.service.mapper.PipelineSectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PipelineSection}.
 */
@Service
@Transactional
public class PipelineSectionService {

    private final Logger log = LoggerFactory.getLogger(PipelineSectionService.class);

    private final PipelineSectionRepository pipelineSectionRepository;

    private final PipelineSectionMapper pipelineSectionMapper;

    public PipelineSectionService(PipelineSectionRepository pipelineSectionRepository, PipelineSectionMapper pipelineSectionMapper) {
        this.pipelineSectionRepository = pipelineSectionRepository;
        this.pipelineSectionMapper = pipelineSectionMapper;
    }

    /**
     * Save a pipelineSection.
     *
     * @param pipelineSectionDTO the entity to save.
     * @return the persisted entity.
     */
    public PipelineSectionDTO save(PipelineSectionDTO pipelineSectionDTO) {
        log.debug("Request to save PipelineSection : {}", pipelineSectionDTO);
        PipelineSection pipelineSection = pipelineSectionMapper.toEntity(pipelineSectionDTO);
        pipelineSection = pipelineSectionRepository.save(pipelineSection);
        return pipelineSectionMapper.toDto(pipelineSection);
    }

    /**
     * Get all the pipelineSections.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineSectionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PipelineSections");
        return pipelineSectionRepository.findAll(pageable)
            .map(pipelineSectionMapper::toDto);
    }


    /**
     * Get one pipelineSection by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipelineSectionDTO> findOne(Long id) {
        log.debug("Request to get PipelineSection : {}", id);
        return pipelineSectionRepository.findById(id)
            .map(pipelineSectionMapper::toDto);
    }

    /**
     * Delete the pipelineSection by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PipelineSection : {}", id);
        pipelineSectionRepository.deleteById(id);
    }
}
