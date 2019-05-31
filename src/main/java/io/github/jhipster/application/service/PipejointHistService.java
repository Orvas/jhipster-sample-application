package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.repository.PipejointHistRepository;
import io.github.jhipster.application.service.dto.PipejointHistDTO;
import io.github.jhipster.application.service.mapper.PipejointHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PipejointHist}.
 */
@Service
@Transactional
public class PipejointHistService {

    private final Logger log = LoggerFactory.getLogger(PipejointHistService.class);

    private final PipejointHistRepository pipejointHistRepository;

    private final PipejointHistMapper pipejointHistMapper;

    public PipejointHistService(PipejointHistRepository pipejointHistRepository, PipejointHistMapper pipejointHistMapper) {
        this.pipejointHistRepository = pipejointHistRepository;
        this.pipejointHistMapper = pipejointHistMapper;
    }

    /**
     * Save a pipejointHist.
     *
     * @param pipejointHistDTO the entity to save.
     * @return the persisted entity.
     */
    public PipejointHistDTO save(PipejointHistDTO pipejointHistDTO) {
        log.debug("Request to save PipejointHist : {}", pipejointHistDTO);
        PipejointHist pipejointHist = pipejointHistMapper.toEntity(pipejointHistDTO);
        pipejointHist = pipejointHistRepository.save(pipejointHist);
        return pipejointHistMapper.toDto(pipejointHist);
    }

    /**
     * Get all the pipejointHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipejointHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PipejointHists");
        return pipejointHistRepository.findAll(pageable)
            .map(pipejointHistMapper::toDto);
    }


    /**
     * Get one pipejointHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipejointHistDTO> findOne(Long id) {
        log.debug("Request to get PipejointHist : {}", id);
        return pipejointHistRepository.findById(id)
            .map(pipejointHistMapper::toDto);
    }

    /**
     * Delete the pipejointHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PipejointHist : {}", id);
        pipejointHistRepository.deleteById(id);
    }
}
