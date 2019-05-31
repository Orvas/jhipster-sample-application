package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListWrkcmmsStatus;
import io.github.jhipster.application.repository.ListWrkcmmsStatusRepository;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusDTO;
import io.github.jhipster.application.service.mapper.ListWrkcmmsStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListWrkcmmsStatus}.
 */
@Service
@Transactional
public class ListWrkcmmsStatusService {

    private final Logger log = LoggerFactory.getLogger(ListWrkcmmsStatusService.class);

    private final ListWrkcmmsStatusRepository listWrkcmmsStatusRepository;

    private final ListWrkcmmsStatusMapper listWrkcmmsStatusMapper;

    public ListWrkcmmsStatusService(ListWrkcmmsStatusRepository listWrkcmmsStatusRepository, ListWrkcmmsStatusMapper listWrkcmmsStatusMapper) {
        this.listWrkcmmsStatusRepository = listWrkcmmsStatusRepository;
        this.listWrkcmmsStatusMapper = listWrkcmmsStatusMapper;
    }

    /**
     * Save a listWrkcmmsStatus.
     *
     * @param listWrkcmmsStatusDTO the entity to save.
     * @return the persisted entity.
     */
    public ListWrkcmmsStatusDTO save(ListWrkcmmsStatusDTO listWrkcmmsStatusDTO) {
        log.debug("Request to save ListWrkcmmsStatus : {}", listWrkcmmsStatusDTO);
        ListWrkcmmsStatus listWrkcmmsStatus = listWrkcmmsStatusMapper.toEntity(listWrkcmmsStatusDTO);
        listWrkcmmsStatus = listWrkcmmsStatusRepository.save(listWrkcmmsStatus);
        return listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);
    }

    /**
     * Get all the listWrkcmmsStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkcmmsStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListWrkcmmsStatuses");
        return listWrkcmmsStatusRepository.findAll(pageable)
            .map(listWrkcmmsStatusMapper::toDto);
    }


    /**
     * Get one listWrkcmmsStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListWrkcmmsStatusDTO> findOne(Long id) {
        log.debug("Request to get ListWrkcmmsStatus : {}", id);
        return listWrkcmmsStatusRepository.findById(id)
            .map(listWrkcmmsStatusMapper::toDto);
    }

    /**
     * Delete the listWrkcmmsStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListWrkcmmsStatus : {}", id);
        listWrkcmmsStatusRepository.deleteById(id);
    }
}
