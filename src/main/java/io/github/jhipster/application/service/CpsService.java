package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Cps;
import io.github.jhipster.application.repository.CpsRepository;
import io.github.jhipster.application.service.dto.CpsDTO;
import io.github.jhipster.application.service.mapper.CpsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cps}.
 */
@Service
@Transactional
public class CpsService {

    private final Logger log = LoggerFactory.getLogger(CpsService.class);

    private final CpsRepository cpsRepository;

    private final CpsMapper cpsMapper;

    public CpsService(CpsRepository cpsRepository, CpsMapper cpsMapper) {
        this.cpsRepository = cpsRepository;
        this.cpsMapper = cpsMapper;
    }

    /**
     * Save a cps.
     *
     * @param cpsDTO the entity to save.
     * @return the persisted entity.
     */
    public CpsDTO save(CpsDTO cpsDTO) {
        log.debug("Request to save Cps : {}", cpsDTO);
        Cps cps = cpsMapper.toEntity(cpsDTO);
        cps = cpsRepository.save(cps);
        return cpsMapper.toDto(cps);
    }

    /**
     * Get all the cps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CpsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cps");
        return cpsRepository.findAll(pageable)
            .map(cpsMapper::toDto);
    }


    /**
     * Get one cps by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CpsDTO> findOne(Long id) {
        log.debug("Request to get Cps : {}", id);
        return cpsRepository.findById(id)
            .map(cpsMapper::toDto);
    }

    /**
     * Delete the cps by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cps : {}", id);
        cpsRepository.deleteById(id);
    }
}
