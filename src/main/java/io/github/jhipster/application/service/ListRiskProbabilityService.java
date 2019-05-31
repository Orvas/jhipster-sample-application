package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListRiskProbability;
import io.github.jhipster.application.repository.ListRiskProbabilityRepository;
import io.github.jhipster.application.service.dto.ListRiskProbabilityDTO;
import io.github.jhipster.application.service.mapper.ListRiskProbabilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListRiskProbability}.
 */
@Service
@Transactional
public class ListRiskProbabilityService {

    private final Logger log = LoggerFactory.getLogger(ListRiskProbabilityService.class);

    private final ListRiskProbabilityRepository listRiskProbabilityRepository;

    private final ListRiskProbabilityMapper listRiskProbabilityMapper;

    public ListRiskProbabilityService(ListRiskProbabilityRepository listRiskProbabilityRepository, ListRiskProbabilityMapper listRiskProbabilityMapper) {
        this.listRiskProbabilityRepository = listRiskProbabilityRepository;
        this.listRiskProbabilityMapper = listRiskProbabilityMapper;
    }

    /**
     * Save a listRiskProbability.
     *
     * @param listRiskProbabilityDTO the entity to save.
     * @return the persisted entity.
     */
    public ListRiskProbabilityDTO save(ListRiskProbabilityDTO listRiskProbabilityDTO) {
        log.debug("Request to save ListRiskProbability : {}", listRiskProbabilityDTO);
        ListRiskProbability listRiskProbability = listRiskProbabilityMapper.toEntity(listRiskProbabilityDTO);
        listRiskProbability = listRiskProbabilityRepository.save(listRiskProbability);
        return listRiskProbabilityMapper.toDto(listRiskProbability);
    }

    /**
     * Get all the listRiskProbabilities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListRiskProbabilityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListRiskProbabilities");
        return listRiskProbabilityRepository.findAll(pageable)
            .map(listRiskProbabilityMapper::toDto);
    }


    /**
     * Get one listRiskProbability by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListRiskProbabilityDTO> findOne(Long id) {
        log.debug("Request to get ListRiskProbability : {}", id);
        return listRiskProbabilityRepository.findById(id)
            .map(listRiskProbabilityMapper::toDto);
    }

    /**
     * Delete the listRiskProbability by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListRiskProbability : {}", id);
        listRiskProbabilityRepository.deleteById(id);
    }
}
