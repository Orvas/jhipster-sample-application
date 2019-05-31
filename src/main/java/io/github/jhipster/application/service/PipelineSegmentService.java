package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PipelineSegment;
import io.github.jhipster.application.repository.PipelineSegmentRepository;
import io.github.jhipster.application.service.dto.PipelineSegmentDTO;
import io.github.jhipster.application.service.mapper.PipelineSegmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PipelineSegment}.
 */
@Service
@Transactional
public class PipelineSegmentService {

    private final Logger log = LoggerFactory.getLogger(PipelineSegmentService.class);

    private final PipelineSegmentRepository pipelineSegmentRepository;

    private final PipelineSegmentMapper pipelineSegmentMapper;

    public PipelineSegmentService(PipelineSegmentRepository pipelineSegmentRepository, PipelineSegmentMapper pipelineSegmentMapper) {
        this.pipelineSegmentRepository = pipelineSegmentRepository;
        this.pipelineSegmentMapper = pipelineSegmentMapper;
    }

    /**
     * Save a pipelineSegment.
     *
     * @param pipelineSegmentDTO the entity to save.
     * @return the persisted entity.
     */
    public PipelineSegmentDTO save(PipelineSegmentDTO pipelineSegmentDTO) {
        log.debug("Request to save PipelineSegment : {}", pipelineSegmentDTO);
        PipelineSegment pipelineSegment = pipelineSegmentMapper.toEntity(pipelineSegmentDTO);
        pipelineSegment = pipelineSegmentRepository.save(pipelineSegment);
        return pipelineSegmentMapper.toDto(pipelineSegment);
    }

    /**
     * Get all the pipelineSegments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineSegmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PipelineSegments");
        return pipelineSegmentRepository.findAll(pageable)
            .map(pipelineSegmentMapper::toDto);
    }


    /**
     * Get one pipelineSegment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipelineSegmentDTO> findOne(Long id) {
        log.debug("Request to get PipelineSegment : {}", id);
        return pipelineSegmentRepository.findById(id)
            .map(pipelineSegmentMapper::toDto);
    }

    /**
     * Delete the pipelineSegment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PipelineSegment : {}", id);
        pipelineSegmentRepository.deleteById(id);
    }
}
