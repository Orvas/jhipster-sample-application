package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListThreat;
import io.github.jhipster.application.repository.ListThreatRepository;
import io.github.jhipster.application.service.dto.ListThreatDTO;
import io.github.jhipster.application.service.mapper.ListThreatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListThreat}.
 */
@Service
@Transactional
public class ListThreatService {

    private final Logger log = LoggerFactory.getLogger(ListThreatService.class);

    private final ListThreatRepository listThreatRepository;

    private final ListThreatMapper listThreatMapper;

    public ListThreatService(ListThreatRepository listThreatRepository, ListThreatMapper listThreatMapper) {
        this.listThreatRepository = listThreatRepository;
        this.listThreatMapper = listThreatMapper;
    }

    /**
     * Save a listThreat.
     *
     * @param listThreatDTO the entity to save.
     * @return the persisted entity.
     */
    public ListThreatDTO save(ListThreatDTO listThreatDTO) {
        log.debug("Request to save ListThreat : {}", listThreatDTO);
        ListThreat listThreat = listThreatMapper.toEntity(listThreatDTO);
        listThreat = listThreatRepository.save(listThreat);
        return listThreatMapper.toDto(listThreat);
    }

    /**
     * Get all the listThreats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListThreatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListThreats");
        return listThreatRepository.findAll(pageable)
            .map(listThreatMapper::toDto);
    }


    /**
     * Get one listThreat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListThreatDTO> findOne(Long id) {
        log.debug("Request to get ListThreat : {}", id);
        return listThreatRepository.findById(id)
            .map(listThreatMapper::toDto);
    }

    /**
     * Delete the listThreat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListThreat : {}", id);
        listThreatRepository.deleteById(id);
    }
}
