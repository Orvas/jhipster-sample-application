package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.CpsRange;
import io.github.jhipster.application.repository.CpsRangeRepository;
import io.github.jhipster.application.service.dto.CpsRangeDTO;
import io.github.jhipster.application.service.mapper.CpsRangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CpsRange}.
 */
@Service
@Transactional
public class CpsRangeService {

    private final Logger log = LoggerFactory.getLogger(CpsRangeService.class);

    private final CpsRangeRepository cpsRangeRepository;

    private final CpsRangeMapper cpsRangeMapper;

    public CpsRangeService(CpsRangeRepository cpsRangeRepository, CpsRangeMapper cpsRangeMapper) {
        this.cpsRangeRepository = cpsRangeRepository;
        this.cpsRangeMapper = cpsRangeMapper;
    }

    /**
     * Save a cpsRange.
     *
     * @param cpsRangeDTO the entity to save.
     * @return the persisted entity.
     */
    public CpsRangeDTO save(CpsRangeDTO cpsRangeDTO) {
        log.debug("Request to save CpsRange : {}", cpsRangeDTO);
        CpsRange cpsRange = cpsRangeMapper.toEntity(cpsRangeDTO);
        cpsRange = cpsRangeRepository.save(cpsRange);
        return cpsRangeMapper.toDto(cpsRange);
    }

    /**
     * Get all the cpsRanges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CpsRangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CpsRanges");
        return cpsRangeRepository.findAll(pageable)
            .map(cpsRangeMapper::toDto);
    }


    /**
     * Get one cpsRange by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CpsRangeDTO> findOne(Long id) {
        log.debug("Request to get CpsRange : {}", id);
        return cpsRangeRepository.findById(id)
            .map(cpsRangeMapper::toDto);
    }

    /**
     * Delete the cpsRange by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CpsRange : {}", id);
        cpsRangeRepository.deleteById(id);
    }
}
