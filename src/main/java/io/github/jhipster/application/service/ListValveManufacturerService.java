package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListValveManufacturer;
import io.github.jhipster.application.repository.ListValveManufacturerRepository;
import io.github.jhipster.application.service.dto.ListValveManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListValveManufacturerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListValveManufacturer}.
 */
@Service
@Transactional
public class ListValveManufacturerService {

    private final Logger log = LoggerFactory.getLogger(ListValveManufacturerService.class);

    private final ListValveManufacturerRepository listValveManufacturerRepository;

    private final ListValveManufacturerMapper listValveManufacturerMapper;

    public ListValveManufacturerService(ListValveManufacturerRepository listValveManufacturerRepository, ListValveManufacturerMapper listValveManufacturerMapper) {
        this.listValveManufacturerRepository = listValveManufacturerRepository;
        this.listValveManufacturerMapper = listValveManufacturerMapper;
    }

    /**
     * Save a listValveManufacturer.
     *
     * @param listValveManufacturerDTO the entity to save.
     * @return the persisted entity.
     */
    public ListValveManufacturerDTO save(ListValveManufacturerDTO listValveManufacturerDTO) {
        log.debug("Request to save ListValveManufacturer : {}", listValveManufacturerDTO);
        ListValveManufacturer listValveManufacturer = listValveManufacturerMapper.toEntity(listValveManufacturerDTO);
        listValveManufacturer = listValveManufacturerRepository.save(listValveManufacturer);
        return listValveManufacturerMapper.toDto(listValveManufacturer);
    }

    /**
     * Get all the listValveManufacturers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveManufacturerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListValveManufacturers");
        return listValveManufacturerRepository.findAll(pageable)
            .map(listValveManufacturerMapper::toDto);
    }


    /**
     * Get one listValveManufacturer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListValveManufacturerDTO> findOne(Long id) {
        log.debug("Request to get ListValveManufacturer : {}", id);
        return listValveManufacturerRepository.findById(id)
            .map(listValveManufacturerMapper::toDto);
    }

    /**
     * Delete the listValveManufacturer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListValveManufacturer : {}", id);
        listValveManufacturerRepository.deleteById(id);
    }
}
