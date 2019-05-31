package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.repository.AnodeHistRepository;
import io.github.jhipster.application.service.dto.AnodeHistDTO;
import io.github.jhipster.application.service.mapper.AnodeHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AnodeHist}.
 */
@Service
@Transactional
public class AnodeHistService {

    private final Logger log = LoggerFactory.getLogger(AnodeHistService.class);

    private final AnodeHistRepository anodeHistRepository;

    private final AnodeHistMapper anodeHistMapper;

    public AnodeHistService(AnodeHistRepository anodeHistRepository, AnodeHistMapper anodeHistMapper) {
        this.anodeHistRepository = anodeHistRepository;
        this.anodeHistMapper = anodeHistMapper;
    }

    /**
     * Save a anodeHist.
     *
     * @param anodeHistDTO the entity to save.
     * @return the persisted entity.
     */
    public AnodeHistDTO save(AnodeHistDTO anodeHistDTO) {
        log.debug("Request to save AnodeHist : {}", anodeHistDTO);
        AnodeHist anodeHist = anodeHistMapper.toEntity(anodeHistDTO);
        anodeHist = anodeHistRepository.save(anodeHist);
        return anodeHistMapper.toDto(anodeHist);
    }

    /**
     * Get all the anodeHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AnodeHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnodeHists");
        return anodeHistRepository.findAll(pageable)
            .map(anodeHistMapper::toDto);
    }


    /**
     * Get one anodeHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnodeHistDTO> findOne(Long id) {
        log.debug("Request to get AnodeHist : {}", id);
        return anodeHistRepository.findById(id)
            .map(anodeHistMapper::toDto);
    }

    /**
     * Delete the anodeHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnodeHist : {}", id);
        anodeHistRepository.deleteById(id);
    }
}
