package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.FreeSpanHist;
import io.github.jhipster.application.repository.FreeSpanHistRepository;
import io.github.jhipster.application.service.dto.FreeSpanHistDTO;
import io.github.jhipster.application.service.mapper.FreeSpanHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FreeSpanHist}.
 */
@Service
@Transactional
public class FreeSpanHistService {

    private final Logger log = LoggerFactory.getLogger(FreeSpanHistService.class);

    private final FreeSpanHistRepository freeSpanHistRepository;

    private final FreeSpanHistMapper freeSpanHistMapper;

    public FreeSpanHistService(FreeSpanHistRepository freeSpanHistRepository, FreeSpanHistMapper freeSpanHistMapper) {
        this.freeSpanHistRepository = freeSpanHistRepository;
        this.freeSpanHistMapper = freeSpanHistMapper;
    }

    /**
     * Save a freeSpanHist.
     *
     * @param freeSpanHistDTO the entity to save.
     * @return the persisted entity.
     */
    public FreeSpanHistDTO save(FreeSpanHistDTO freeSpanHistDTO) {
        log.debug("Request to save FreeSpanHist : {}", freeSpanHistDTO);
        FreeSpanHist freeSpanHist = freeSpanHistMapper.toEntity(freeSpanHistDTO);
        freeSpanHist = freeSpanHistRepository.save(freeSpanHist);
        return freeSpanHistMapper.toDto(freeSpanHist);
    }

    /**
     * Get all the freeSpanHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FreeSpanHists");
        return freeSpanHistRepository.findAll(pageable)
            .map(freeSpanHistMapper::toDto);
    }


    /**
     * Get one freeSpanHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FreeSpanHistDTO> findOne(Long id) {
        log.debug("Request to get FreeSpanHist : {}", id);
        return freeSpanHistRepository.findById(id)
            .map(freeSpanHistMapper::toDto);
    }

    /**
     * Delete the freeSpanHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FreeSpanHist : {}", id);
        freeSpanHistRepository.deleteById(id);
    }
}
