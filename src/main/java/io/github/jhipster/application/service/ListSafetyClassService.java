package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListSafetyClass;
import io.github.jhipster.application.repository.ListSafetyClassRepository;
import io.github.jhipster.application.service.dto.ListSafetyClassDTO;
import io.github.jhipster.application.service.mapper.ListSafetyClassMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListSafetyClass}.
 */
@Service
@Transactional
public class ListSafetyClassService {

    private final Logger log = LoggerFactory.getLogger(ListSafetyClassService.class);

    private final ListSafetyClassRepository listSafetyClassRepository;

    private final ListSafetyClassMapper listSafetyClassMapper;

    public ListSafetyClassService(ListSafetyClassRepository listSafetyClassRepository, ListSafetyClassMapper listSafetyClassMapper) {
        this.listSafetyClassRepository = listSafetyClassRepository;
        this.listSafetyClassMapper = listSafetyClassMapper;
    }

    /**
     * Save a listSafetyClass.
     *
     * @param listSafetyClassDTO the entity to save.
     * @return the persisted entity.
     */
    public ListSafetyClassDTO save(ListSafetyClassDTO listSafetyClassDTO) {
        log.debug("Request to save ListSafetyClass : {}", listSafetyClassDTO);
        ListSafetyClass listSafetyClass = listSafetyClassMapper.toEntity(listSafetyClassDTO);
        listSafetyClass = listSafetyClassRepository.save(listSafetyClass);
        return listSafetyClassMapper.toDto(listSafetyClass);
    }

    /**
     * Get all the listSafetyClasses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSafetyClassDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListSafetyClasses");
        return listSafetyClassRepository.findAll(pageable)
            .map(listSafetyClassMapper::toDto);
    }


    /**
     * Get one listSafetyClass by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListSafetyClassDTO> findOne(Long id) {
        log.debug("Request to get ListSafetyClass : {}", id);
        return listSafetyClassRepository.findById(id)
            .map(listSafetyClassMapper::toDto);
    }

    /**
     * Delete the listSafetyClass by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListSafetyClass : {}", id);
        listSafetyClassRepository.deleteById(id);
    }
}
