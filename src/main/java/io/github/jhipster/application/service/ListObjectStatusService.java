package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.ListObjectStatusRepository;
import io.github.jhipster.application.service.dto.ListObjectStatusDTO;
import io.github.jhipster.application.service.mapper.ListObjectStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListObjectStatus}.
 */
@Service
@Transactional
public class ListObjectStatusService {

    private final Logger log = LoggerFactory.getLogger(ListObjectStatusService.class);

    private final ListObjectStatusRepository listObjectStatusRepository;

    private final ListObjectStatusMapper listObjectStatusMapper;

    public ListObjectStatusService(ListObjectStatusRepository listObjectStatusRepository, ListObjectStatusMapper listObjectStatusMapper) {
        this.listObjectStatusRepository = listObjectStatusRepository;
        this.listObjectStatusMapper = listObjectStatusMapper;
    }

    /**
     * Save a listObjectStatus.
     *
     * @param listObjectStatusDTO the entity to save.
     * @return the persisted entity.
     */
    public ListObjectStatusDTO save(ListObjectStatusDTO listObjectStatusDTO) {
        log.debug("Request to save ListObjectStatus : {}", listObjectStatusDTO);
        ListObjectStatus listObjectStatus = listObjectStatusMapper.toEntity(listObjectStatusDTO);
        listObjectStatus = listObjectStatusRepository.save(listObjectStatus);
        return listObjectStatusMapper.toDto(listObjectStatus);
    }

    /**
     * Get all the listObjectStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListObjectStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListObjectStatuses");
        return listObjectStatusRepository.findAll(pageable)
            .map(listObjectStatusMapper::toDto);
    }


    /**
     * Get one listObjectStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListObjectStatusDTO> findOne(Long id) {
        log.debug("Request to get ListObjectStatus : {}", id);
        return listObjectStatusRepository.findById(id)
            .map(listObjectStatusMapper::toDto);
    }

    /**
     * Delete the listObjectStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListObjectStatus : {}", id);
        listObjectStatusRepository.deleteById(id);
    }
}
