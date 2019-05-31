package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListValveFunction;
import io.github.jhipster.application.repository.ListValveFunctionRepository;
import io.github.jhipster.application.service.dto.ListValveFunctionDTO;
import io.github.jhipster.application.service.mapper.ListValveFunctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListValveFunction}.
 */
@Service
@Transactional
public class ListValveFunctionService {

    private final Logger log = LoggerFactory.getLogger(ListValveFunctionService.class);

    private final ListValveFunctionRepository listValveFunctionRepository;

    private final ListValveFunctionMapper listValveFunctionMapper;

    public ListValveFunctionService(ListValveFunctionRepository listValveFunctionRepository, ListValveFunctionMapper listValveFunctionMapper) {
        this.listValveFunctionRepository = listValveFunctionRepository;
        this.listValveFunctionMapper = listValveFunctionMapper;
    }

    /**
     * Save a listValveFunction.
     *
     * @param listValveFunctionDTO the entity to save.
     * @return the persisted entity.
     */
    public ListValveFunctionDTO save(ListValveFunctionDTO listValveFunctionDTO) {
        log.debug("Request to save ListValveFunction : {}", listValveFunctionDTO);
        ListValveFunction listValveFunction = listValveFunctionMapper.toEntity(listValveFunctionDTO);
        listValveFunction = listValveFunctionRepository.save(listValveFunction);
        return listValveFunctionMapper.toDto(listValveFunction);
    }

    /**
     * Get all the listValveFunctions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveFunctionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListValveFunctions");
        return listValveFunctionRepository.findAll(pageable)
            .map(listValveFunctionMapper::toDto);
    }


    /**
     * Get one listValveFunction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListValveFunctionDTO> findOne(Long id) {
        log.debug("Request to get ListValveFunction : {}", id);
        return listValveFunctionRepository.findById(id)
            .map(listValveFunctionMapper::toDto);
    }

    /**
     * Delete the listValveFunction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListValveFunction : {}", id);
        listValveFunctionRepository.deleteById(id);
    }
}
