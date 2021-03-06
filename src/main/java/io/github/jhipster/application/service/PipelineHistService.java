package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PipelineHist;
import io.github.jhipster.application.repository.PipelineHistRepository;
import io.github.jhipster.application.service.dto.PipelineHistDTO;
import io.github.jhipster.application.service.mapper.PipelineHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PipelineHist}.
 */
@Service
@Transactional
public class PipelineHistService {

    private final Logger log = LoggerFactory.getLogger(PipelineHistService.class);

    private final PipelineHistRepository pipelineHistRepository;

    private final PipelineHistMapper pipelineHistMapper;

    public PipelineHistService(PipelineHistRepository pipelineHistRepository, PipelineHistMapper pipelineHistMapper) {
        this.pipelineHistRepository = pipelineHistRepository;
        this.pipelineHistMapper = pipelineHistMapper;
    }

    /**
     * Save a pipelineHist.
     *
     * @param pipelineHistDTO the entity to save.
     * @return the persisted entity.
     */
    public PipelineHistDTO save(PipelineHistDTO pipelineHistDTO) {
        log.debug("Request to save PipelineHist : {}", pipelineHistDTO);
        PipelineHist pipelineHist = pipelineHistMapper.toEntity(pipelineHistDTO);
        pipelineHist = pipelineHistRepository.save(pipelineHist);
        return pipelineHistMapper.toDto(pipelineHist);
    }

    /**
     * Get all the pipelineHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipelineHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PipelineHists");
        return pipelineHistRepository.findAll(pageable)
            .map(pipelineHistMapper::toDto);
    }


    /**
     * Get one pipelineHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipelineHistDTO> findOne(Long id) {
        log.debug("Request to get PipelineHist : {}", id);
        return pipelineHistRepository.findById(id)
            .map(pipelineHistMapper::toDto);
    }

    /**
     * Delete the pipelineHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PipelineHist : {}", id);
        pipelineHistRepository.deleteById(id);
    }
}
