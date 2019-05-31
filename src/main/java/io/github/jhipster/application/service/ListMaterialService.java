package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.repository.ListMaterialRepository;
import io.github.jhipster.application.service.dto.ListMaterialDTO;
import io.github.jhipster.application.service.mapper.ListMaterialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListMaterial}.
 */
@Service
@Transactional
public class ListMaterialService {

    private final Logger log = LoggerFactory.getLogger(ListMaterialService.class);

    private final ListMaterialRepository listMaterialRepository;

    private final ListMaterialMapper listMaterialMapper;

    public ListMaterialService(ListMaterialRepository listMaterialRepository, ListMaterialMapper listMaterialMapper) {
        this.listMaterialRepository = listMaterialRepository;
        this.listMaterialMapper = listMaterialMapper;
    }

    /**
     * Save a listMaterial.
     *
     * @param listMaterialDTO the entity to save.
     * @return the persisted entity.
     */
    public ListMaterialDTO save(ListMaterialDTO listMaterialDTO) {
        log.debug("Request to save ListMaterial : {}", listMaterialDTO);
        ListMaterial listMaterial = listMaterialMapper.toEntity(listMaterialDTO);
        listMaterial = listMaterialRepository.save(listMaterial);
        return listMaterialMapper.toDto(listMaterial);
    }

    /**
     * Get all the listMaterials.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListMaterialDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListMaterials");
        return listMaterialRepository.findAll(pageable)
            .map(listMaterialMapper::toDto);
    }


    /**
     * Get one listMaterial by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListMaterialDTO> findOne(Long id) {
        log.debug("Request to get ListMaterial : {}", id);
        return listMaterialRepository.findById(id)
            .map(listMaterialMapper::toDto);
    }

    /**
     * Delete the listMaterial by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListMaterial : {}", id);
        listMaterialRepository.deleteById(id);
    }
}
