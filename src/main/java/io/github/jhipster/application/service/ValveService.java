package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Valve;
import io.github.jhipster.application.repository.ValveRepository;
import io.github.jhipster.application.service.dto.ValveDTO;
import io.github.jhipster.application.service.mapper.ValveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Valve}.
 */
@Service
@Transactional
public class ValveService {

    private final Logger log = LoggerFactory.getLogger(ValveService.class);

    private final ValveRepository valveRepository;

    private final ValveMapper valveMapper;

    public ValveService(ValveRepository valveRepository, ValveMapper valveMapper) {
        this.valveRepository = valveRepository;
        this.valveMapper = valveMapper;
    }

    /**
     * Save a valve.
     *
     * @param valveDTO the entity to save.
     * @return the persisted entity.
     */
    public ValveDTO save(ValveDTO valveDTO) {
        log.debug("Request to save Valve : {}", valveDTO);
        Valve valve = valveMapper.toEntity(valveDTO);
        valve = valveRepository.save(valve);
        return valveMapper.toDto(valve);
    }

    /**
     * Get all the valves.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ValveDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Valves");
        return valveRepository.findAll(pageable)
            .map(valveMapper::toDto);
    }


    /**
     * Get one valve by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValveDTO> findOne(Long id) {
        log.debug("Request to get Valve : {}", id);
        return valveRepository.findById(id)
            .map(valveMapper::toDto);
    }

    /**
     * Delete the valve by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Valve : {}", id);
        valveRepository.deleteById(id);
    }
}
