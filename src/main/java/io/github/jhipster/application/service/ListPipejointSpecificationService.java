package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListPipejointSpecification;
import io.github.jhipster.application.repository.ListPipejointSpecificationRepository;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListPipejointSpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListPipejointSpecification}.
 */
@Service
@Transactional
public class ListPipejointSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ListPipejointSpecificationService.class);

    private final ListPipejointSpecificationRepository listPipejointSpecificationRepository;

    private final ListPipejointSpecificationMapper listPipejointSpecificationMapper;

    public ListPipejointSpecificationService(ListPipejointSpecificationRepository listPipejointSpecificationRepository, ListPipejointSpecificationMapper listPipejointSpecificationMapper) {
        this.listPipejointSpecificationRepository = listPipejointSpecificationRepository;
        this.listPipejointSpecificationMapper = listPipejointSpecificationMapper;
    }

    /**
     * Save a listPipejointSpecification.
     *
     * @param listPipejointSpecificationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListPipejointSpecificationDTO save(ListPipejointSpecificationDTO listPipejointSpecificationDTO) {
        log.debug("Request to save ListPipejointSpecification : {}", listPipejointSpecificationDTO);
        ListPipejointSpecification listPipejointSpecification = listPipejointSpecificationMapper.toEntity(listPipejointSpecificationDTO);
        listPipejointSpecification = listPipejointSpecificationRepository.save(listPipejointSpecification);
        return listPipejointSpecificationMapper.toDto(listPipejointSpecification);
    }

    /**
     * Get all the listPipejointSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipejointSpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListPipejointSpecifications");
        return listPipejointSpecificationRepository.findAll(pageable)
            .map(listPipejointSpecificationMapper::toDto);
    }


    /**
     * Get one listPipejointSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListPipejointSpecificationDTO> findOne(Long id) {
        log.debug("Request to get ListPipejointSpecification : {}", id);
        return listPipejointSpecificationRepository.findById(id)
            .map(listPipejointSpecificationMapper::toDto);
    }

    /**
     * Delete the listPipejointSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListPipejointSpecification : {}", id);
        listPipejointSpecificationRepository.deleteById(id);
    }
}
