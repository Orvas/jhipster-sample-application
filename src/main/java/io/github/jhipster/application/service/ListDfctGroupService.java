package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListDfctGroup;
import io.github.jhipster.application.repository.ListDfctGroupRepository;
import io.github.jhipster.application.service.dto.ListDfctGroupDTO;
import io.github.jhipster.application.service.mapper.ListDfctGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListDfctGroup}.
 */
@Service
@Transactional
public class ListDfctGroupService {

    private final Logger log = LoggerFactory.getLogger(ListDfctGroupService.class);

    private final ListDfctGroupRepository listDfctGroupRepository;

    private final ListDfctGroupMapper listDfctGroupMapper;

    public ListDfctGroupService(ListDfctGroupRepository listDfctGroupRepository, ListDfctGroupMapper listDfctGroupMapper) {
        this.listDfctGroupRepository = listDfctGroupRepository;
        this.listDfctGroupMapper = listDfctGroupMapper;
    }

    /**
     * Save a listDfctGroup.
     *
     * @param listDfctGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public ListDfctGroupDTO save(ListDfctGroupDTO listDfctGroupDTO) {
        log.debug("Request to save ListDfctGroup : {}", listDfctGroupDTO);
        ListDfctGroup listDfctGroup = listDfctGroupMapper.toEntity(listDfctGroupDTO);
        listDfctGroup = listDfctGroupRepository.save(listDfctGroup);
        return listDfctGroupMapper.toDto(listDfctGroup);
    }

    /**
     * Get all the listDfctGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListDfctGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListDfctGroups");
        return listDfctGroupRepository.findAll(pageable)
            .map(listDfctGroupMapper::toDto);
    }


    /**
     * Get one listDfctGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListDfctGroupDTO> findOne(Long id) {
        log.debug("Request to get ListDfctGroup : {}", id);
        return listDfctGroupRepository.findById(id)
            .map(listDfctGroupMapper::toDto);
    }

    /**
     * Delete the listDfctGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListDfctGroup : {}", id);
        listDfctGroupRepository.deleteById(id);
    }
}
