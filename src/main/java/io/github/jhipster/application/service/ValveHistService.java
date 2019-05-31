package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ValveHistRepository;
import io.github.jhipster.application.service.dto.ValveHistDTO;
import io.github.jhipster.application.service.mapper.ValveHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ValveHist}.
 */
@Service
@Transactional
public class ValveHistService {

    private final Logger log = LoggerFactory.getLogger(ValveHistService.class);

    private final ValveHistRepository valveHistRepository;

    private final ValveHistMapper valveHistMapper;

    public ValveHistService(ValveHistRepository valveHistRepository, ValveHistMapper valveHistMapper) {
        this.valveHistRepository = valveHistRepository;
        this.valveHistMapper = valveHistMapper;
    }

    /**
     * Save a valveHist.
     *
     * @param valveHistDTO the entity to save.
     * @return the persisted entity.
     */
    public ValveHistDTO save(ValveHistDTO valveHistDTO) {
        log.debug("Request to save ValveHist : {}", valveHistDTO);
        ValveHist valveHist = valveHistMapper.toEntity(valveHistDTO);
        valveHist = valveHistRepository.save(valveHist);
        return valveHistMapper.toDto(valveHist);
    }

    /**
     * Get all the valveHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ValveHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ValveHists");
        return valveHistRepository.findAll(pageable)
            .map(valveHistMapper::toDto);
    }


    /**
     * Get one valveHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValveHistDTO> findOne(Long id) {
        log.debug("Request to get ValveHist : {}", id);
        return valveHistRepository.findById(id)
            .map(valveHistMapper::toDto);
    }

    /**
     * Delete the valveHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ValveHist : {}", id);
        valveHistRepository.deleteById(id);
    }
}
