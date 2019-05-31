package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.repository.PipejointRepository;
import io.github.jhipster.application.service.dto.PipejointDTO;
import io.github.jhipster.application.service.mapper.PipejointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Pipejoint}.
 */
@Service
@Transactional
public class PipejointService {

    private final Logger log = LoggerFactory.getLogger(PipejointService.class);

    private final PipejointRepository pipejointRepository;

    private final PipejointMapper pipejointMapper;

    public PipejointService(PipejointRepository pipejointRepository, PipejointMapper pipejointMapper) {
        this.pipejointRepository = pipejointRepository;
        this.pipejointMapper = pipejointMapper;
    }

    /**
     * Save a pipejoint.
     *
     * @param pipejointDTO the entity to save.
     * @return the persisted entity.
     */
    public PipejointDTO save(PipejointDTO pipejointDTO) {
        log.debug("Request to save Pipejoint : {}", pipejointDTO);
        Pipejoint pipejoint = pipejointMapper.toEntity(pipejointDTO);
        pipejoint = pipejointRepository.save(pipejoint);
        return pipejointMapper.toDto(pipejoint);
    }

    /**
     * Get all the pipejoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipejointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pipejoints");
        return pipejointRepository.findAll(pageable)
            .map(pipejointMapper::toDto);
    }


    /**
     * Get one pipejoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipejointDTO> findOne(Long id) {
        log.debug("Request to get Pipejoint : {}", id);
        return pipejointRepository.findById(id)
            .map(pipejointMapper::toDto);
    }

    /**
     * Delete the pipejoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pipejoint : {}", id);
        pipejointRepository.deleteById(id);
    }
}
