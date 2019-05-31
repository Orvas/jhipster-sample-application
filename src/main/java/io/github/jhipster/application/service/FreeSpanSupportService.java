package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.FreeSpanSupport;
import io.github.jhipster.application.repository.FreeSpanSupportRepository;
import io.github.jhipster.application.service.dto.FreeSpanSupportDTO;
import io.github.jhipster.application.service.mapper.FreeSpanSupportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FreeSpanSupport}.
 */
@Service
@Transactional
public class FreeSpanSupportService {

    private final Logger log = LoggerFactory.getLogger(FreeSpanSupportService.class);

    private final FreeSpanSupportRepository freeSpanSupportRepository;

    private final FreeSpanSupportMapper freeSpanSupportMapper;

    public FreeSpanSupportService(FreeSpanSupportRepository freeSpanSupportRepository, FreeSpanSupportMapper freeSpanSupportMapper) {
        this.freeSpanSupportRepository = freeSpanSupportRepository;
        this.freeSpanSupportMapper = freeSpanSupportMapper;
    }

    /**
     * Save a freeSpanSupport.
     *
     * @param freeSpanSupportDTO the entity to save.
     * @return the persisted entity.
     */
    public FreeSpanSupportDTO save(FreeSpanSupportDTO freeSpanSupportDTO) {
        log.debug("Request to save FreeSpanSupport : {}", freeSpanSupportDTO);
        FreeSpanSupport freeSpanSupport = freeSpanSupportMapper.toEntity(freeSpanSupportDTO);
        freeSpanSupport = freeSpanSupportRepository.save(freeSpanSupport);
        return freeSpanSupportMapper.toDto(freeSpanSupport);
    }

    /**
     * Get all the freeSpanSupports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanSupportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FreeSpanSupports");
        return freeSpanSupportRepository.findAll(pageable)
            .map(freeSpanSupportMapper::toDto);
    }


    /**
     * Get one freeSpanSupport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FreeSpanSupportDTO> findOne(Long id) {
        log.debug("Request to get FreeSpanSupport : {}", id);
        return freeSpanSupportRepository.findById(id)
            .map(freeSpanSupportMapper::toDto);
    }

    /**
     * Delete the freeSpanSupport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FreeSpanSupport : {}", id);
        freeSpanSupportRepository.deleteById(id);
    }
}
