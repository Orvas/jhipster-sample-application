package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.repository.BaseClassRepository;
import io.github.jhipster.application.service.dto.BaseClassDTO;
import io.github.jhipster.application.service.mapper.BaseClassMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link BaseClass}.
 */
@Service
@Transactional
public class BaseClassService {

    private final Logger log = LoggerFactory.getLogger(BaseClassService.class);

    private final BaseClassRepository baseClassRepository;

    private final BaseClassMapper baseClassMapper;

    public BaseClassService(BaseClassRepository baseClassRepository, BaseClassMapper baseClassMapper) {
        this.baseClassRepository = baseClassRepository;
        this.baseClassMapper = baseClassMapper;
    }

    /**
     * Save a baseClass.
     *
     * @param baseClassDTO the entity to save.
     * @return the persisted entity.
     */
    public BaseClassDTO save(BaseClassDTO baseClassDTO) {
        log.debug("Request to save BaseClass : {}", baseClassDTO);
        BaseClass baseClass = baseClassMapper.toEntity(baseClassDTO);
        baseClass = baseClassRepository.save(baseClass);
        return baseClassMapper.toDto(baseClass);
    }

    /**
     * Get all the baseClasses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BaseClassDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BaseClasses");
        return baseClassRepository.findAll(pageable)
            .map(baseClassMapper::toDto);
    }



    /**
    *  Get all the baseClasses where Pipejoint is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWherePipejointIsNull() {
        log.debug("Request to get all baseClasses where Pipejoint is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getPipejoint() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one baseClass by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BaseClassDTO> findOne(Long id) {
        log.debug("Request to get BaseClass : {}", id);
        return baseClassRepository.findById(id)
            .map(baseClassMapper::toDto);
    }

    /**
     * Delete the baseClass by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BaseClass : {}", id);
        baseClassRepository.deleteById(id);
    }
}
