package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListBendManufacturer;
import io.github.jhipster.application.repository.ListBendManufacturerRepository;
import io.github.jhipster.application.service.dto.ListBendManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListBendManufacturerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListBendManufacturer}.
 */
@Service
@Transactional
public class ListBendManufacturerService {

    private final Logger log = LoggerFactory.getLogger(ListBendManufacturerService.class);

    private final ListBendManufacturerRepository listBendManufacturerRepository;

    private final ListBendManufacturerMapper listBendManufacturerMapper;

    public ListBendManufacturerService(ListBendManufacturerRepository listBendManufacturerRepository, ListBendManufacturerMapper listBendManufacturerMapper) {
        this.listBendManufacturerRepository = listBendManufacturerRepository;
        this.listBendManufacturerMapper = listBendManufacturerMapper;
    }

    /**
     * Save a listBendManufacturer.
     *
     * @param listBendManufacturerDTO the entity to save.
     * @return the persisted entity.
     */
    public ListBendManufacturerDTO save(ListBendManufacturerDTO listBendManufacturerDTO) {
        log.debug("Request to save ListBendManufacturer : {}", listBendManufacturerDTO);
        ListBendManufacturer listBendManufacturer = listBendManufacturerMapper.toEntity(listBendManufacturerDTO);
        listBendManufacturer = listBendManufacturerRepository.save(listBendManufacturer);
        return listBendManufacturerMapper.toDto(listBendManufacturer);
    }

    /**
     * Get all the listBendManufacturers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBendManufacturerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListBendManufacturers");
        return listBendManufacturerRepository.findAll(pageable)
            .map(listBendManufacturerMapper::toDto);
    }


    /**
     * Get one listBendManufacturer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListBendManufacturerDTO> findOne(Long id) {
        log.debug("Request to get ListBendManufacturer : {}", id);
        return listBendManufacturerRepository.findById(id)
            .map(listBendManufacturerMapper::toDto);
    }

    /**
     * Delete the listBendManufacturer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListBendManufacturer : {}", id);
        listBendManufacturerRepository.deleteById(id);
    }
}
