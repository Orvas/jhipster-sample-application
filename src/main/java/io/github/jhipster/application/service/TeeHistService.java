package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.repository.TeeHistRepository;
import io.github.jhipster.application.service.dto.TeeHistDTO;
import io.github.jhipster.application.service.mapper.TeeHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TeeHist}.
 */
@Service
@Transactional
public class TeeHistService {

    private final Logger log = LoggerFactory.getLogger(TeeHistService.class);

    private final TeeHistRepository teeHistRepository;

    private final TeeHistMapper teeHistMapper;

    public TeeHistService(TeeHistRepository teeHistRepository, TeeHistMapper teeHistMapper) {
        this.teeHistRepository = teeHistRepository;
        this.teeHistMapper = teeHistMapper;
    }

    /**
     * Save a teeHist.
     *
     * @param teeHistDTO the entity to save.
     * @return the persisted entity.
     */
    public TeeHistDTO save(TeeHistDTO teeHistDTO) {
        log.debug("Request to save TeeHist : {}", teeHistDTO);
        TeeHist teeHist = teeHistMapper.toEntity(teeHistDTO);
        teeHist = teeHistRepository.save(teeHist);
        return teeHistMapper.toDto(teeHist);
    }

    /**
     * Get all the teeHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TeeHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeeHists");
        return teeHistRepository.findAll(pageable)
            .map(teeHistMapper::toDto);
    }


    /**
     * Get one teeHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TeeHistDTO> findOne(Long id) {
        log.debug("Request to get TeeHist : {}", id);
        return teeHistRepository.findById(id)
            .map(teeHistMapper::toDto);
    }

    /**
     * Delete the teeHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TeeHist : {}", id);
        teeHistRepository.deleteById(id);
    }
}
