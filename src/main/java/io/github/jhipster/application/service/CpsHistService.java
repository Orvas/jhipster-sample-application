package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.CpsHist;
import io.github.jhipster.application.repository.CpsHistRepository;
import io.github.jhipster.application.service.dto.CpsHistDTO;
import io.github.jhipster.application.service.mapper.CpsHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CpsHist}.
 */
@Service
@Transactional
public class CpsHistService {

    private final Logger log = LoggerFactory.getLogger(CpsHistService.class);

    private final CpsHistRepository cpsHistRepository;

    private final CpsHistMapper cpsHistMapper;

    public CpsHistService(CpsHistRepository cpsHistRepository, CpsHistMapper cpsHistMapper) {
        this.cpsHistRepository = cpsHistRepository;
        this.cpsHistMapper = cpsHistMapper;
    }

    /**
     * Save a cpsHist.
     *
     * @param cpsHistDTO the entity to save.
     * @return the persisted entity.
     */
    public CpsHistDTO save(CpsHistDTO cpsHistDTO) {
        log.debug("Request to save CpsHist : {}", cpsHistDTO);
        CpsHist cpsHist = cpsHistMapper.toEntity(cpsHistDTO);
        cpsHist = cpsHistRepository.save(cpsHist);
        return cpsHistMapper.toDto(cpsHist);
    }

    /**
     * Get all the cpsHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CpsHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CpsHists");
        return cpsHistRepository.findAll(pageable)
            .map(cpsHistMapper::toDto);
    }


    /**
     * Get one cpsHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CpsHistDTO> findOne(Long id) {
        log.debug("Request to get CpsHist : {}", id);
        return cpsHistRepository.findById(id)
            .map(cpsHistMapper::toDto);
    }

    /**
     * Delete the cpsHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CpsHist : {}", id);
        cpsHistRepository.deleteById(id);
    }
}
