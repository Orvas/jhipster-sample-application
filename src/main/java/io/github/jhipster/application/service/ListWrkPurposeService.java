package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListWrkPurpose;
import io.github.jhipster.application.repository.ListWrkPurposeRepository;
import io.github.jhipster.application.service.dto.ListWrkPurposeDTO;
import io.github.jhipster.application.service.mapper.ListWrkPurposeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListWrkPurpose}.
 */
@Service
@Transactional
public class ListWrkPurposeService {

    private final Logger log = LoggerFactory.getLogger(ListWrkPurposeService.class);

    private final ListWrkPurposeRepository listWrkPurposeRepository;

    private final ListWrkPurposeMapper listWrkPurposeMapper;

    public ListWrkPurposeService(ListWrkPurposeRepository listWrkPurposeRepository, ListWrkPurposeMapper listWrkPurposeMapper) {
        this.listWrkPurposeRepository = listWrkPurposeRepository;
        this.listWrkPurposeMapper = listWrkPurposeMapper;
    }

    /**
     * Save a listWrkPurpose.
     *
     * @param listWrkPurposeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListWrkPurposeDTO save(ListWrkPurposeDTO listWrkPurposeDTO) {
        log.debug("Request to save ListWrkPurpose : {}", listWrkPurposeDTO);
        ListWrkPurpose listWrkPurpose = listWrkPurposeMapper.toEntity(listWrkPurposeDTO);
        listWrkPurpose = listWrkPurposeRepository.save(listWrkPurpose);
        return listWrkPurposeMapper.toDto(listWrkPurpose);
    }

    /**
     * Get all the listWrkPurposes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkPurposeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListWrkPurposes");
        return listWrkPurposeRepository.findAll(pageable)
            .map(listWrkPurposeMapper::toDto);
    }


    /**
     * Get one listWrkPurpose by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListWrkPurposeDTO> findOne(Long id) {
        log.debug("Request to get ListWrkPurpose : {}", id);
        return listWrkPurposeRepository.findById(id)
            .map(listWrkPurposeMapper::toDto);
    }

    /**
     * Delete the listWrkPurpose by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListWrkPurpose : {}", id);
        listWrkPurposeRepository.deleteById(id);
    }
}
