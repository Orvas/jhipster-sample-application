package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.repository.ListInternalCoatingRepository;
import io.github.jhipster.application.service.dto.ListInternalCoatingDTO;
import io.github.jhipster.application.service.mapper.ListInternalCoatingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListInternalCoating}.
 */
@Service
@Transactional
public class ListInternalCoatingService {

    private final Logger log = LoggerFactory.getLogger(ListInternalCoatingService.class);

    private final ListInternalCoatingRepository listInternalCoatingRepository;

    private final ListInternalCoatingMapper listInternalCoatingMapper;

    public ListInternalCoatingService(ListInternalCoatingRepository listInternalCoatingRepository, ListInternalCoatingMapper listInternalCoatingMapper) {
        this.listInternalCoatingRepository = listInternalCoatingRepository;
        this.listInternalCoatingMapper = listInternalCoatingMapper;
    }

    /**
     * Save a listInternalCoating.
     *
     * @param listInternalCoatingDTO the entity to save.
     * @return the persisted entity.
     */
    public ListInternalCoatingDTO save(ListInternalCoatingDTO listInternalCoatingDTO) {
        log.debug("Request to save ListInternalCoating : {}", listInternalCoatingDTO);
        ListInternalCoating listInternalCoating = listInternalCoatingMapper.toEntity(listInternalCoatingDTO);
        listInternalCoating = listInternalCoatingRepository.save(listInternalCoating);
        return listInternalCoatingMapper.toDto(listInternalCoating);
    }

    /**
     * Get all the listInternalCoatings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListInternalCoatingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListInternalCoatings");
        return listInternalCoatingRepository.findAll(pageable)
            .map(listInternalCoatingMapper::toDto);
    }


    /**
     * Get one listInternalCoating by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListInternalCoatingDTO> findOne(Long id) {
        log.debug("Request to get ListInternalCoating : {}", id);
        return listInternalCoatingRepository.findById(id)
            .map(listInternalCoatingMapper::toDto);
    }

    /**
     * Delete the listInternalCoating by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListInternalCoating : {}", id);
        listInternalCoatingRepository.deleteById(id);
    }
}
