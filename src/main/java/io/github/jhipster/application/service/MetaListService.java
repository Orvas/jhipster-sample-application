package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.MetaList;
import io.github.jhipster.application.repository.MetaListRepository;
import io.github.jhipster.application.service.dto.MetaListDTO;
import io.github.jhipster.application.service.mapper.MetaListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MetaList}.
 */
@Service
@Transactional
public class MetaListService {

    private final Logger log = LoggerFactory.getLogger(MetaListService.class);

    private final MetaListRepository metaListRepository;

    private final MetaListMapper metaListMapper;

    public MetaListService(MetaListRepository metaListRepository, MetaListMapper metaListMapper) {
        this.metaListRepository = metaListRepository;
        this.metaListMapper = metaListMapper;
    }

    /**
     * Save a metaList.
     *
     * @param metaListDTO the entity to save.
     * @return the persisted entity.
     */
    public MetaListDTO save(MetaListDTO metaListDTO) {
        log.debug("Request to save MetaList : {}", metaListDTO);
        MetaList metaList = metaListMapper.toEntity(metaListDTO);
        metaList = metaListRepository.save(metaList);
        return metaListMapper.toDto(metaList);
    }

    /**
     * Get all the metaLists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MetaListDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MetaLists");
        return metaListRepository.findAll(pageable)
            .map(metaListMapper::toDto);
    }


    /**
     * Get one metaList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MetaListDTO> findOne(Long id) {
        log.debug("Request to get MetaList : {}", id);
        return metaListRepository.findById(id)
            .map(metaListMapper::toDto);
    }

    /**
     * Delete the metaList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MetaList : {}", id);
        metaListRepository.deleteById(id);
    }
}
