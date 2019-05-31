package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListMinpressUcase;
import io.github.jhipster.application.repository.ListMinpressUcaseRepository;
import io.github.jhipster.application.service.dto.ListMinpressUcaseDTO;
import io.github.jhipster.application.service.mapper.ListMinpressUcaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListMinpressUcase}.
 */
@Service
@Transactional
public class ListMinpressUcaseService {

    private final Logger log = LoggerFactory.getLogger(ListMinpressUcaseService.class);

    private final ListMinpressUcaseRepository listMinpressUcaseRepository;

    private final ListMinpressUcaseMapper listMinpressUcaseMapper;

    public ListMinpressUcaseService(ListMinpressUcaseRepository listMinpressUcaseRepository, ListMinpressUcaseMapper listMinpressUcaseMapper) {
        this.listMinpressUcaseRepository = listMinpressUcaseRepository;
        this.listMinpressUcaseMapper = listMinpressUcaseMapper;
    }

    /**
     * Save a listMinpressUcase.
     *
     * @param listMinpressUcaseDTO the entity to save.
     * @return the persisted entity.
     */
    public ListMinpressUcaseDTO save(ListMinpressUcaseDTO listMinpressUcaseDTO) {
        log.debug("Request to save ListMinpressUcase : {}", listMinpressUcaseDTO);
        ListMinpressUcase listMinpressUcase = listMinpressUcaseMapper.toEntity(listMinpressUcaseDTO);
        listMinpressUcase = listMinpressUcaseRepository.save(listMinpressUcase);
        return listMinpressUcaseMapper.toDto(listMinpressUcase);
    }

    /**
     * Get all the listMinpressUcases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListMinpressUcaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListMinpressUcases");
        return listMinpressUcaseRepository.findAll(pageable)
            .map(listMinpressUcaseMapper::toDto);
    }


    /**
     * Get one listMinpressUcase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListMinpressUcaseDTO> findOne(Long id) {
        log.debug("Request to get ListMinpressUcase : {}", id);
        return listMinpressUcaseRepository.findById(id)
            .map(listMinpressUcaseMapper::toDto);
    }

    /**
     * Delete the listMinpressUcase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListMinpressUcase : {}", id);
        listMinpressUcaseRepository.deleteById(id);
    }
}
