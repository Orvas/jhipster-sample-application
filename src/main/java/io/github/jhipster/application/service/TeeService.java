package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Tee;
import io.github.jhipster.application.repository.TeeRepository;
import io.github.jhipster.application.service.dto.TeeDTO;
import io.github.jhipster.application.service.mapper.TeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Tee}.
 */
@Service
@Transactional
public class TeeService {

    private final Logger log = LoggerFactory.getLogger(TeeService.class);

    private final TeeRepository teeRepository;

    private final TeeMapper teeMapper;

    public TeeService(TeeRepository teeRepository, TeeMapper teeMapper) {
        this.teeRepository = teeRepository;
        this.teeMapper = teeMapper;
    }

    /**
     * Save a tee.
     *
     * @param teeDTO the entity to save.
     * @return the persisted entity.
     */
    public TeeDTO save(TeeDTO teeDTO) {
        log.debug("Request to save Tee : {}", teeDTO);
        Tee tee = teeMapper.toEntity(teeDTO);
        tee = teeRepository.save(tee);
        return teeMapper.toDto(tee);
    }

    /**
     * Get all the tees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tees");
        return teeRepository.findAll(pageable)
            .map(teeMapper::toDto);
    }



    /**
    *  Get all the tees where TeeHist is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<TeeDTO> findAllWhereTeeHistIsNull() {
        log.debug("Request to get all tees where TeeHist is null");
        return StreamSupport
            .stream(teeRepository.findAll().spliterator(), false)
            .filter(tee -> tee.getTeeHist() == null)
            .map(teeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TeeDTO> findOne(Long id) {
        log.debug("Request to get Tee : {}", id);
        return teeRepository.findById(id)
            .map(teeMapper::toDto);
    }

    /**
     * Delete the tee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tee : {}", id);
        teeRepository.deleteById(id);
    }
}
