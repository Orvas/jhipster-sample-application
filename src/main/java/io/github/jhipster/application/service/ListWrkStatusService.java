package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListWrkStatus;
import io.github.jhipster.application.repository.ListWrkStatusRepository;
import io.github.jhipster.application.service.dto.ListWrkStatusDTO;
import io.github.jhipster.application.service.mapper.ListWrkStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListWrkStatus}.
 */
@Service
@Transactional
public class ListWrkStatusService {

    private final Logger log = LoggerFactory.getLogger(ListWrkStatusService.class);

    private final ListWrkStatusRepository listWrkStatusRepository;

    private final ListWrkStatusMapper listWrkStatusMapper;

    public ListWrkStatusService(ListWrkStatusRepository listWrkStatusRepository, ListWrkStatusMapper listWrkStatusMapper) {
        this.listWrkStatusRepository = listWrkStatusRepository;
        this.listWrkStatusMapper = listWrkStatusMapper;
    }

    /**
     * Save a listWrkStatus.
     *
     * @param listWrkStatusDTO the entity to save.
     * @return the persisted entity.
     */
    public ListWrkStatusDTO save(ListWrkStatusDTO listWrkStatusDTO) {
        log.debug("Request to save ListWrkStatus : {}", listWrkStatusDTO);
        ListWrkStatus listWrkStatus = listWrkStatusMapper.toEntity(listWrkStatusDTO);
        listWrkStatus = listWrkStatusRepository.save(listWrkStatus);
        return listWrkStatusMapper.toDto(listWrkStatus);
    }

    /**
     * Get all the listWrkStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListWrkStatuses");
        return listWrkStatusRepository.findAll(pageable)
            .map(listWrkStatusMapper::toDto);
    }


    /**
     * Get one listWrkStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListWrkStatusDTO> findOne(Long id) {
        log.debug("Request to get ListWrkStatus : {}", id);
        return listWrkStatusRepository.findById(id)
            .map(listWrkStatusMapper::toDto);
    }

    /**
     * Delete the listWrkStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListWrkStatus : {}", id);
        listWrkStatusRepository.deleteById(id);
    }
}
