package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListRiskConsequence;
import io.github.jhipster.application.repository.ListRiskConsequenceRepository;
import io.github.jhipster.application.service.dto.ListRiskConsequenceDTO;
import io.github.jhipster.application.service.mapper.ListRiskConsequenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListRiskConsequence}.
 */
@Service
@Transactional
public class ListRiskConsequenceService {

    private final Logger log = LoggerFactory.getLogger(ListRiskConsequenceService.class);

    private final ListRiskConsequenceRepository listRiskConsequenceRepository;

    private final ListRiskConsequenceMapper listRiskConsequenceMapper;

    public ListRiskConsequenceService(ListRiskConsequenceRepository listRiskConsequenceRepository, ListRiskConsequenceMapper listRiskConsequenceMapper) {
        this.listRiskConsequenceRepository = listRiskConsequenceRepository;
        this.listRiskConsequenceMapper = listRiskConsequenceMapper;
    }

    /**
     * Save a listRiskConsequence.
     *
     * @param listRiskConsequenceDTO the entity to save.
     * @return the persisted entity.
     */
    public ListRiskConsequenceDTO save(ListRiskConsequenceDTO listRiskConsequenceDTO) {
        log.debug("Request to save ListRiskConsequence : {}", listRiskConsequenceDTO);
        ListRiskConsequence listRiskConsequence = listRiskConsequenceMapper.toEntity(listRiskConsequenceDTO);
        listRiskConsequence = listRiskConsequenceRepository.save(listRiskConsequence);
        return listRiskConsequenceMapper.toDto(listRiskConsequence);
    }

    /**
     * Get all the listRiskConsequences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListRiskConsequenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListRiskConsequences");
        return listRiskConsequenceRepository.findAll(pageable)
            .map(listRiskConsequenceMapper::toDto);
    }


    /**
     * Get one listRiskConsequence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListRiskConsequenceDTO> findOne(Long id) {
        log.debug("Request to get ListRiskConsequence : {}", id);
        return listRiskConsequenceRepository.findById(id)
            .map(listRiskConsequenceMapper::toDto);
    }

    /**
     * Delete the listRiskConsequence by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListRiskConsequence : {}", id);
        listRiskConsequenceRepository.deleteById(id);
    }
}
