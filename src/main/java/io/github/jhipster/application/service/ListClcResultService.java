package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListClcResult;
import io.github.jhipster.application.repository.ListClcResultRepository;
import io.github.jhipster.application.service.dto.ListClcResultDTO;
import io.github.jhipster.application.service.mapper.ListClcResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListClcResult}.
 */
@Service
@Transactional
public class ListClcResultService {

    private final Logger log = LoggerFactory.getLogger(ListClcResultService.class);

    private final ListClcResultRepository listClcResultRepository;

    private final ListClcResultMapper listClcResultMapper;

    public ListClcResultService(ListClcResultRepository listClcResultRepository, ListClcResultMapper listClcResultMapper) {
        this.listClcResultRepository = listClcResultRepository;
        this.listClcResultMapper = listClcResultMapper;
    }

    /**
     * Save a listClcResult.
     *
     * @param listClcResultDTO the entity to save.
     * @return the persisted entity.
     */
    public ListClcResultDTO save(ListClcResultDTO listClcResultDTO) {
        log.debug("Request to save ListClcResult : {}", listClcResultDTO);
        ListClcResult listClcResult = listClcResultMapper.toEntity(listClcResultDTO);
        listClcResult = listClcResultRepository.save(listClcResult);
        return listClcResultMapper.toDto(listClcResult);
    }

    /**
     * Get all the listClcResults.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcResultDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListClcResults");
        return listClcResultRepository.findAll(pageable)
            .map(listClcResultMapper::toDto);
    }


    /**
     * Get one listClcResult by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListClcResultDTO> findOne(Long id) {
        log.debug("Request to get ListClcResult : {}", id);
        return listClcResultRepository.findById(id)
            .map(listClcResultMapper::toDto);
    }

    /**
     * Delete the listClcResult by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListClcResult : {}", id);
        listClcResultRepository.deleteById(id);
    }
}
