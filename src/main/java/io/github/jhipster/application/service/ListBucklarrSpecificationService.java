package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListBucklarrSpecification;
import io.github.jhipster.application.repository.ListBucklarrSpecificationRepository;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrSpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListBucklarrSpecification}.
 */
@Service
@Transactional
public class ListBucklarrSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrSpecificationService.class);

    private final ListBucklarrSpecificationRepository listBucklarrSpecificationRepository;

    private final ListBucklarrSpecificationMapper listBucklarrSpecificationMapper;

    public ListBucklarrSpecificationService(ListBucklarrSpecificationRepository listBucklarrSpecificationRepository, ListBucklarrSpecificationMapper listBucklarrSpecificationMapper) {
        this.listBucklarrSpecificationRepository = listBucklarrSpecificationRepository;
        this.listBucklarrSpecificationMapper = listBucklarrSpecificationMapper;
    }

    /**
     * Save a listBucklarrSpecification.
     *
     * @param listBucklarrSpecificationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListBucklarrSpecificationDTO save(ListBucklarrSpecificationDTO listBucklarrSpecificationDTO) {
        log.debug("Request to save ListBucklarrSpecification : {}", listBucklarrSpecificationDTO);
        ListBucklarrSpecification listBucklarrSpecification = listBucklarrSpecificationMapper.toEntity(listBucklarrSpecificationDTO);
        listBucklarrSpecification = listBucklarrSpecificationRepository.save(listBucklarrSpecification);
        return listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);
    }

    /**
     * Get all the listBucklarrSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBucklarrSpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListBucklarrSpecifications");
        return listBucklarrSpecificationRepository.findAll(pageable)
            .map(listBucklarrSpecificationMapper::toDto);
    }


    /**
     * Get one listBucklarrSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListBucklarrSpecificationDTO> findOne(Long id) {
        log.debug("Request to get ListBucklarrSpecification : {}", id);
        return listBucklarrSpecificationRepository.findById(id)
            .map(listBucklarrSpecificationMapper::toDto);
    }

    /**
     * Delete the listBucklarrSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListBucklarrSpecification : {}", id);
        listBucklarrSpecificationRepository.deleteById(id);
    }
}
