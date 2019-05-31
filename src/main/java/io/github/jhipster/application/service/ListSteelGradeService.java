package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.repository.ListSteelGradeRepository;
import io.github.jhipster.application.service.dto.ListSteelGradeDTO;
import io.github.jhipster.application.service.mapper.ListSteelGradeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListSteelGrade}.
 */
@Service
@Transactional
public class ListSteelGradeService {

    private final Logger log = LoggerFactory.getLogger(ListSteelGradeService.class);

    private final ListSteelGradeRepository listSteelGradeRepository;

    private final ListSteelGradeMapper listSteelGradeMapper;

    public ListSteelGradeService(ListSteelGradeRepository listSteelGradeRepository, ListSteelGradeMapper listSteelGradeMapper) {
        this.listSteelGradeRepository = listSteelGradeRepository;
        this.listSteelGradeMapper = listSteelGradeMapper;
    }

    /**
     * Save a listSteelGrade.
     *
     * @param listSteelGradeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListSteelGradeDTO save(ListSteelGradeDTO listSteelGradeDTO) {
        log.debug("Request to save ListSteelGrade : {}", listSteelGradeDTO);
        ListSteelGrade listSteelGrade = listSteelGradeMapper.toEntity(listSteelGradeDTO);
        listSteelGrade = listSteelGradeRepository.save(listSteelGrade);
        return listSteelGradeMapper.toDto(listSteelGrade);
    }

    /**
     * Get all the listSteelGrades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSteelGradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListSteelGrades");
        return listSteelGradeRepository.findAll(pageable)
            .map(listSteelGradeMapper::toDto);
    }


    /**
     * Get one listSteelGrade by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListSteelGradeDTO> findOne(Long id) {
        log.debug("Request to get ListSteelGrade : {}", id);
        return listSteelGradeRepository.findById(id)
            .map(listSteelGradeMapper::toDto);
    }

    /**
     * Delete the listSteelGrade by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListSteelGrade : {}", id);
        listSteelGradeRepository.deleteById(id);
    }
}
