package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.FreeSpan;
import io.github.jhipster.application.repository.FreeSpanRepository;
import io.github.jhipster.application.service.dto.FreeSpanDTO;
import io.github.jhipster.application.service.mapper.FreeSpanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FreeSpan}.
 */
@Service
@Transactional
public class FreeSpanService {

    private final Logger log = LoggerFactory.getLogger(FreeSpanService.class);

    private final FreeSpanRepository freeSpanRepository;

    private final FreeSpanMapper freeSpanMapper;

    public FreeSpanService(FreeSpanRepository freeSpanRepository, FreeSpanMapper freeSpanMapper) {
        this.freeSpanRepository = freeSpanRepository;
        this.freeSpanMapper = freeSpanMapper;
    }

    /**
     * Save a freeSpan.
     *
     * @param freeSpanDTO the entity to save.
     * @return the persisted entity.
     */
    public FreeSpanDTO save(FreeSpanDTO freeSpanDTO) {
        log.debug("Request to save FreeSpan : {}", freeSpanDTO);
        FreeSpan freeSpan = freeSpanMapper.toEntity(freeSpanDTO);
        freeSpan = freeSpanRepository.save(freeSpan);
        return freeSpanMapper.toDto(freeSpan);
    }

    /**
     * Get all the freeSpans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FreeSpans");
        return freeSpanRepository.findAll(pageable)
            .map(freeSpanMapper::toDto);
    }


    /**
     * Get one freeSpan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FreeSpanDTO> findOne(Long id) {
        log.debug("Request to get FreeSpan : {}", id);
        return freeSpanRepository.findById(id)
            .map(freeSpanMapper::toDto);
    }

    /**
     * Delete the freeSpan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FreeSpan : {}", id);
        freeSpanRepository.deleteById(id);
    }
}
