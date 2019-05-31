package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListBucklarrManufacturer;
import io.github.jhipster.application.repository.ListBucklarrManufacturerRepository;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrManufacturerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListBucklarrManufacturer}.
 */
@Service
@Transactional
public class ListBucklarrManufacturerService {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrManufacturerService.class);

    private final ListBucklarrManufacturerRepository listBucklarrManufacturerRepository;

    private final ListBucklarrManufacturerMapper listBucklarrManufacturerMapper;

    public ListBucklarrManufacturerService(ListBucklarrManufacturerRepository listBucklarrManufacturerRepository, ListBucklarrManufacturerMapper listBucklarrManufacturerMapper) {
        this.listBucklarrManufacturerRepository = listBucklarrManufacturerRepository;
        this.listBucklarrManufacturerMapper = listBucklarrManufacturerMapper;
    }

    /**
     * Save a listBucklarrManufacturer.
     *
     * @param listBucklarrManufacturerDTO the entity to save.
     * @return the persisted entity.
     */
    public ListBucklarrManufacturerDTO save(ListBucklarrManufacturerDTO listBucklarrManufacturerDTO) {
        log.debug("Request to save ListBucklarrManufacturer : {}", listBucklarrManufacturerDTO);
        ListBucklarrManufacturer listBucklarrManufacturer = listBucklarrManufacturerMapper.toEntity(listBucklarrManufacturerDTO);
        listBucklarrManufacturer = listBucklarrManufacturerRepository.save(listBucklarrManufacturer);
        return listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);
    }

    /**
     * Get all the listBucklarrManufacturers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBucklarrManufacturerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListBucklarrManufacturers");
        return listBucklarrManufacturerRepository.findAll(pageable)
            .map(listBucklarrManufacturerMapper::toDto);
    }


    /**
     * Get one listBucklarrManufacturer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListBucklarrManufacturerDTO> findOne(Long id) {
        log.debug("Request to get ListBucklarrManufacturer : {}", id);
        return listBucklarrManufacturerRepository.findById(id)
            .map(listBucklarrManufacturerMapper::toDto);
    }

    /**
     * Delete the listBucklarrManufacturer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListBucklarrManufacturer : {}", id);
        listBucklarrManufacturerRepository.deleteById(id);
    }
}
