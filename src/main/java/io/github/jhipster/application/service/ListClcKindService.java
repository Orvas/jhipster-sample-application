package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListClcKind;
import io.github.jhipster.application.repository.ListClcKindRepository;
import io.github.jhipster.application.service.dto.ListClcKindDTO;
import io.github.jhipster.application.service.mapper.ListClcKindMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListClcKind}.
 */
@Service
@Transactional
public class ListClcKindService {

    private final Logger log = LoggerFactory.getLogger(ListClcKindService.class);

    private final ListClcKindRepository listClcKindRepository;

    private final ListClcKindMapper listClcKindMapper;

    public ListClcKindService(ListClcKindRepository listClcKindRepository, ListClcKindMapper listClcKindMapper) {
        this.listClcKindRepository = listClcKindRepository;
        this.listClcKindMapper = listClcKindMapper;
    }

    /**
     * Save a listClcKind.
     *
     * @param listClcKindDTO the entity to save.
     * @return the persisted entity.
     */
    public ListClcKindDTO save(ListClcKindDTO listClcKindDTO) {
        log.debug("Request to save ListClcKind : {}", listClcKindDTO);
        ListClcKind listClcKind = listClcKindMapper.toEntity(listClcKindDTO);
        listClcKind = listClcKindRepository.save(listClcKind);
        return listClcKindMapper.toDto(listClcKind);
    }

    /**
     * Get all the listClcKinds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcKindDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListClcKinds");
        return listClcKindRepository.findAll(pageable)
            .map(listClcKindMapper::toDto);
    }


    /**
     * Get one listClcKind by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListClcKindDTO> findOne(Long id) {
        log.debug("Request to get ListClcKind : {}", id);
        return listClcKindRepository.findById(id)
            .map(listClcKindMapper::toDto);
    }

    /**
     * Delete the listClcKind by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListClcKind : {}", id);
        listClcKindRepository.deleteById(id);
    }
}
