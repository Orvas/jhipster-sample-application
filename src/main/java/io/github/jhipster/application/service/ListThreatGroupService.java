package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListThreatGroup;
import io.github.jhipster.application.repository.ListThreatGroupRepository;
import io.github.jhipster.application.service.dto.ListThreatGroupDTO;
import io.github.jhipster.application.service.mapper.ListThreatGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListThreatGroup}.
 */
@Service
@Transactional
public class ListThreatGroupService {

    private final Logger log = LoggerFactory.getLogger(ListThreatGroupService.class);

    private final ListThreatGroupRepository listThreatGroupRepository;

    private final ListThreatGroupMapper listThreatGroupMapper;

    public ListThreatGroupService(ListThreatGroupRepository listThreatGroupRepository, ListThreatGroupMapper listThreatGroupMapper) {
        this.listThreatGroupRepository = listThreatGroupRepository;
        this.listThreatGroupMapper = listThreatGroupMapper;
    }

    /**
     * Save a listThreatGroup.
     *
     * @param listThreatGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public ListThreatGroupDTO save(ListThreatGroupDTO listThreatGroupDTO) {
        log.debug("Request to save ListThreatGroup : {}", listThreatGroupDTO);
        ListThreatGroup listThreatGroup = listThreatGroupMapper.toEntity(listThreatGroupDTO);
        listThreatGroup = listThreatGroupRepository.save(listThreatGroup);
        return listThreatGroupMapper.toDto(listThreatGroup);
    }

    /**
     * Get all the listThreatGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListThreatGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListThreatGroups");
        return listThreatGroupRepository.findAll(pageable)
            .map(listThreatGroupMapper::toDto);
    }


    /**
     * Get one listThreatGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListThreatGroupDTO> findOne(Long id) {
        log.debug("Request to get ListThreatGroup : {}", id);
        return listThreatGroupRepository.findById(id)
            .map(listThreatGroupMapper::toDto);
    }

    /**
     * Delete the listThreatGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListThreatGroup : {}", id);
        listThreatGroupRepository.deleteById(id);
    }
}
