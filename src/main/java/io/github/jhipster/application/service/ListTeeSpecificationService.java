package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListTeeSpecification;
import io.github.jhipster.application.repository.ListTeeSpecificationRepository;
import io.github.jhipster.application.service.dto.ListTeeSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListTeeSpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListTeeSpecification}.
 */
@Service
@Transactional
public class ListTeeSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ListTeeSpecificationService.class);

    private final ListTeeSpecificationRepository listTeeSpecificationRepository;

    private final ListTeeSpecificationMapper listTeeSpecificationMapper;

    public ListTeeSpecificationService(ListTeeSpecificationRepository listTeeSpecificationRepository, ListTeeSpecificationMapper listTeeSpecificationMapper) {
        this.listTeeSpecificationRepository = listTeeSpecificationRepository;
        this.listTeeSpecificationMapper = listTeeSpecificationMapper;
    }

    /**
     * Save a listTeeSpecification.
     *
     * @param listTeeSpecificationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListTeeSpecificationDTO save(ListTeeSpecificationDTO listTeeSpecificationDTO) {
        log.debug("Request to save ListTeeSpecification : {}", listTeeSpecificationDTO);
        ListTeeSpecification listTeeSpecification = listTeeSpecificationMapper.toEntity(listTeeSpecificationDTO);
        listTeeSpecification = listTeeSpecificationRepository.save(listTeeSpecification);
        return listTeeSpecificationMapper.toDto(listTeeSpecification);
    }

    /**
     * Get all the listTeeSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListTeeSpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListTeeSpecifications");
        return listTeeSpecificationRepository.findAll(pageable)
            .map(listTeeSpecificationMapper::toDto);
    }


    /**
     * Get one listTeeSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListTeeSpecificationDTO> findOne(Long id) {
        log.debug("Request to get ListTeeSpecification : {}", id);
        return listTeeSpecificationRepository.findById(id)
            .map(listTeeSpecificationMapper::toDto);
    }

    /**
     * Delete the listTeeSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListTeeSpecification : {}", id);
        listTeeSpecificationRepository.deleteById(id);
    }
}
