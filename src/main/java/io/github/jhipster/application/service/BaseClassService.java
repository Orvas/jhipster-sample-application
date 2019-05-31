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
    *  Get all the baseClasses where Anode is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereAnodeIsNull() {
        log.debug("Request to get all baseClasses where Anode is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getAnode() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where Bend is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereBendIsNull() {
        log.debug("Request to get all baseClasses where Bend is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getBend() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where BuckleArrestor is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereBuckleArrestorIsNull() {
        log.debug("Request to get all baseClasses where BuckleArrestor is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getBuckleArrestor() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where Cps is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereCpsIsNull() {
        log.debug("Request to get all baseClasses where Cps is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getCps() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where Displacement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereDisplacementIsNull() {
        log.debug("Request to get all baseClasses where Displacement is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getDisplacement() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where FreeSpan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereFreeSpanIsNull() {
        log.debug("Request to get all baseClasses where FreeSpan is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getFreeSpan() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where FreeSpanSupport is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereFreeSpanSupportIsNull() {
        log.debug("Request to get all baseClasses where FreeSpanSupport is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getFreeSpanSupport() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where LaunchReceiver is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereLaunchReceiverIsNull() {
        log.debug("Request to get all baseClasses where LaunchReceiver is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getLaunchReceiver() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where Pipe is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWherePipeIsNull() {
        log.debug("Request to get all baseClasses where Pipe is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getPipe() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
    *  Get all the baseClasses where Pipeline is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWherePipelineIsNull() {
        log.debug("Request to get all baseClasses where Pipeline is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getPipeline() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where PipelineSection is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWherePipelineSectionIsNull() {
        log.debug("Request to get all baseClasses where PipelineSection is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getPipelineSection() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where Tee is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereTeeIsNull() {
        log.debug("Request to get all baseClasses where Tee is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getTee() == null)
            .map(baseClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the baseClasses where Valve is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BaseClassDTO> findAllWhereValveIsNull() {
        log.debug("Request to get all baseClasses where Valve is null");
        return StreamSupport
            .stream(baseClassRepository.findAll().spliterator(), false)
            .filter(baseClass -> baseClass.getValve() == null)
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
