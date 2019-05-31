package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListWrkKind;
import io.github.jhipster.application.repository.ListWrkKindRepository;
import io.github.jhipster.application.service.dto.ListWrkKindDTO;
import io.github.jhipster.application.service.mapper.ListWrkKindMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListWrkKind}.
 */
@Service
@Transactional
public class ListWrkKindService {

    private final Logger log = LoggerFactory.getLogger(ListWrkKindService.class);

    private final ListWrkKindRepository listWrkKindRepository;

    private final ListWrkKindMapper listWrkKindMapper;

    public ListWrkKindService(ListWrkKindRepository listWrkKindRepository, ListWrkKindMapper listWrkKindMapper) {
        this.listWrkKindRepository = listWrkKindRepository;
        this.listWrkKindMapper = listWrkKindMapper;
    }

    /**
     * Save a listWrkKind.
     *
     * @param listWrkKindDTO the entity to save.
     * @return the persisted entity.
     */
    public ListWrkKindDTO save(ListWrkKindDTO listWrkKindDTO) {
        log.debug("Request to save ListWrkKind : {}", listWrkKindDTO);
        ListWrkKind listWrkKind = listWrkKindMapper.toEntity(listWrkKindDTO);
        listWrkKind = listWrkKindRepository.save(listWrkKind);
        return listWrkKindMapper.toDto(listWrkKind);
    }

    /**
     * Get all the listWrkKinds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListWrkKindDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListWrkKinds");
        return listWrkKindRepository.findAll(pageable)
            .map(listWrkKindMapper::toDto);
    }


    /**
     * Get one listWrkKind by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListWrkKindDTO> findOne(Long id) {
        log.debug("Request to get ListWrkKind : {}", id);
        return listWrkKindRepository.findById(id)
            .map(listWrkKindMapper::toDto);
    }

    /**
     * Delete the listWrkKind by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListWrkKind : {}", id);
        listWrkKindRepository.deleteById(id);
    }
}
