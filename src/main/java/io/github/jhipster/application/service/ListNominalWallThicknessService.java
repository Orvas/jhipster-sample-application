package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.repository.ListNominalWallThicknessRepository;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessDTO;
import io.github.jhipster.application.service.mapper.ListNominalWallThicknessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListNominalWallThickness}.
 */
@Service
@Transactional
public class ListNominalWallThicknessService {

    private final Logger log = LoggerFactory.getLogger(ListNominalWallThicknessService.class);

    private final ListNominalWallThicknessRepository listNominalWallThicknessRepository;

    private final ListNominalWallThicknessMapper listNominalWallThicknessMapper;

    public ListNominalWallThicknessService(ListNominalWallThicknessRepository listNominalWallThicknessRepository, ListNominalWallThicknessMapper listNominalWallThicknessMapper) {
        this.listNominalWallThicknessRepository = listNominalWallThicknessRepository;
        this.listNominalWallThicknessMapper = listNominalWallThicknessMapper;
    }

    /**
     * Save a listNominalWallThickness.
     *
     * @param listNominalWallThicknessDTO the entity to save.
     * @return the persisted entity.
     */
    public ListNominalWallThicknessDTO save(ListNominalWallThicknessDTO listNominalWallThicknessDTO) {
        log.debug("Request to save ListNominalWallThickness : {}", listNominalWallThicknessDTO);
        ListNominalWallThickness listNominalWallThickness = listNominalWallThicknessMapper.toEntity(listNominalWallThicknessDTO);
        listNominalWallThickness = listNominalWallThicknessRepository.save(listNominalWallThickness);
        return listNominalWallThicknessMapper.toDto(listNominalWallThickness);
    }

    /**
     * Get all the listNominalWallThicknesses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListNominalWallThicknessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListNominalWallThicknesses");
        return listNominalWallThicknessRepository.findAll(pageable)
            .map(listNominalWallThicknessMapper::toDto);
    }


    /**
     * Get one listNominalWallThickness by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListNominalWallThicknessDTO> findOne(Long id) {
        log.debug("Request to get ListNominalWallThickness : {}", id);
        return listNominalWallThicknessRepository.findById(id)
            .map(listNominalWallThicknessMapper::toDto);
    }

    /**
     * Delete the listNominalWallThickness by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListNominalWallThickness : {}", id);
        listNominalWallThicknessRepository.deleteById(id);
    }
}
