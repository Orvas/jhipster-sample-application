package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListEffAxforceUcase;
import io.github.jhipster.application.repository.ListEffAxforceUcaseRepository;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseDTO;
import io.github.jhipster.application.service.mapper.ListEffAxforceUcaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListEffAxforceUcase}.
 */
@Service
@Transactional
public class ListEffAxforceUcaseService {

    private final Logger log = LoggerFactory.getLogger(ListEffAxforceUcaseService.class);

    private final ListEffAxforceUcaseRepository listEffAxforceUcaseRepository;

    private final ListEffAxforceUcaseMapper listEffAxforceUcaseMapper;

    public ListEffAxforceUcaseService(ListEffAxforceUcaseRepository listEffAxforceUcaseRepository, ListEffAxforceUcaseMapper listEffAxforceUcaseMapper) {
        this.listEffAxforceUcaseRepository = listEffAxforceUcaseRepository;
        this.listEffAxforceUcaseMapper = listEffAxforceUcaseMapper;
    }

    /**
     * Save a listEffAxforceUcase.
     *
     * @param listEffAxforceUcaseDTO the entity to save.
     * @return the persisted entity.
     */
    public ListEffAxforceUcaseDTO save(ListEffAxforceUcaseDTO listEffAxforceUcaseDTO) {
        log.debug("Request to save ListEffAxforceUcase : {}", listEffAxforceUcaseDTO);
        ListEffAxforceUcase listEffAxforceUcase = listEffAxforceUcaseMapper.toEntity(listEffAxforceUcaseDTO);
        listEffAxforceUcase = listEffAxforceUcaseRepository.save(listEffAxforceUcase);
        return listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);
    }

    /**
     * Get all the listEffAxforceUcases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListEffAxforceUcaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListEffAxforceUcases");
        return listEffAxforceUcaseRepository.findAll(pageable)
            .map(listEffAxforceUcaseMapper::toDto);
    }


    /**
     * Get one listEffAxforceUcase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListEffAxforceUcaseDTO> findOne(Long id) {
        log.debug("Request to get ListEffAxforceUcase : {}", id);
        return listEffAxforceUcaseRepository.findById(id)
            .map(listEffAxforceUcaseMapper::toDto);
    }

    /**
     * Delete the listEffAxforceUcase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListEffAxforceUcase : {}", id);
        listEffAxforceUcaseRepository.deleteById(id);
    }
}
