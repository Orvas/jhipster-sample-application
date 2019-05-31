package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.FreeSpanSupportHist;
import io.github.jhipster.application.repository.FreeSpanSupportHistRepository;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistDTO;
import io.github.jhipster.application.service.mapper.FreeSpanSupportHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FreeSpanSupportHist}.
 */
@Service
@Transactional
public class FreeSpanSupportHistService {

    private final Logger log = LoggerFactory.getLogger(FreeSpanSupportHistService.class);

    private final FreeSpanSupportHistRepository freeSpanSupportHistRepository;

    private final FreeSpanSupportHistMapper freeSpanSupportHistMapper;

    public FreeSpanSupportHistService(FreeSpanSupportHistRepository freeSpanSupportHistRepository, FreeSpanSupportHistMapper freeSpanSupportHistMapper) {
        this.freeSpanSupportHistRepository = freeSpanSupportHistRepository;
        this.freeSpanSupportHistMapper = freeSpanSupportHistMapper;
    }

    /**
     * Save a freeSpanSupportHist.
     *
     * @param freeSpanSupportHistDTO the entity to save.
     * @return the persisted entity.
     */
    public FreeSpanSupportHistDTO save(FreeSpanSupportHistDTO freeSpanSupportHistDTO) {
        log.debug("Request to save FreeSpanSupportHist : {}", freeSpanSupportHistDTO);
        FreeSpanSupportHist freeSpanSupportHist = freeSpanSupportHistMapper.toEntity(freeSpanSupportHistDTO);
        freeSpanSupportHist = freeSpanSupportHistRepository.save(freeSpanSupportHist);
        return freeSpanSupportHistMapper.toDto(freeSpanSupportHist);
    }

    /**
     * Get all the freeSpanSupportHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FreeSpanSupportHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FreeSpanSupportHists");
        return freeSpanSupportHistRepository.findAll(pageable)
            .map(freeSpanSupportHistMapper::toDto);
    }


    /**
     * Get one freeSpanSupportHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FreeSpanSupportHistDTO> findOne(Long id) {
        log.debug("Request to get FreeSpanSupportHist : {}", id);
        return freeSpanSupportHistRepository.findById(id)
            .map(freeSpanSupportHistMapper::toDto);
    }

    /**
     * Delete the freeSpanSupportHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FreeSpanSupportHist : {}", id);
        freeSpanSupportHistRepository.deleteById(id);
    }
}
