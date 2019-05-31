package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Pipeline;
import io.github.jhipster.application.repository.PipelineRepository;
import io.github.jhipster.application.service.dto.PipelineDTO;
import io.github.jhipster.application.service.mapper.PipelineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Pipeline}.
 */
@Service
@Transactional
public class PipelineService {

    private final Logger log = LoggerFactory.getLogger(PipelineService.class);

    private final PipelineRepository pipelineRepository;

    private final PipelineMapper pipelineMapper;

    public PipelineService(PipelineRepository pipelineRepository, PipelineMapper pipelineMapper) {
        this.pipelineRepository = pipelineRepository;
        this.pipelineMapper = pipelineMapper;
    }

    /**
     * Save a pipeline.
     *
     * @param pipelineDTO the entity to save.
     * @return the persisted entity.
     */
    public PipelineDTO save(PipelineDTO pipelineDTO) {
        log.debug("Request to save Pipeline : {}", pipelineDTO);
        Pipeline pipeline = pipelineMapper.toEntity(pipelineDTO);
        pipeline = pipelineRepository.save(pipeline);
        return pipelineMapper.toDto(pipeline);
    }

    /**
     * Get all the pipelines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pipelines");
        return pipelineRepository.findAll(pageable)
            .map(pipelineMapper::toDto);
    }


    /**
     * Get one pipeline by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipelineDTO> findOne(Long id) {
        log.debug("Request to get Pipeline : {}", id);
        return pipelineRepository.findById(id)
            .map(pipelineMapper::toDto);
    }

    /**
     * Delete the pipeline by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pipeline : {}", id);
        pipelineRepository.deleteById(id);
    }
}
