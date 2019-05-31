package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListSteelGradeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListSteelGradeDTO;
import io.github.jhipster.application.service.dto.ListSteelGradeCriteria;
import io.github.jhipster.application.service.ListSteelGradeQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.ListSteelGrade}.
 */
@RestController
@RequestMapping("/api")
public class ListSteelGradeResource {

    private final Logger log = LoggerFactory.getLogger(ListSteelGradeResource.class);

    private static final String ENTITY_NAME = "listSteelGrade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListSteelGradeService listSteelGradeService;

    private final ListSteelGradeQueryService listSteelGradeQueryService;

    public ListSteelGradeResource(ListSteelGradeService listSteelGradeService, ListSteelGradeQueryService listSteelGradeQueryService) {
        this.listSteelGradeService = listSteelGradeService;
        this.listSteelGradeQueryService = listSteelGradeQueryService;
    }

    /**
     * {@code POST  /list-steel-grades} : Create a new listSteelGrade.
     *
     * @param listSteelGradeDTO the listSteelGradeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listSteelGradeDTO, or with status {@code 400 (Bad Request)} if the listSteelGrade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-steel-grades")
    public ResponseEntity<ListSteelGradeDTO> createListSteelGrade(@Valid @RequestBody ListSteelGradeDTO listSteelGradeDTO) throws URISyntaxException {
        log.debug("REST request to save ListSteelGrade : {}", listSteelGradeDTO);
        if (listSteelGradeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listSteelGrade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListSteelGradeDTO result = listSteelGradeService.save(listSteelGradeDTO);
        return ResponseEntity.created(new URI("/api/list-steel-grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-steel-grades} : Updates an existing listSteelGrade.
     *
     * @param listSteelGradeDTO the listSteelGradeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listSteelGradeDTO,
     * or with status {@code 400 (Bad Request)} if the listSteelGradeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listSteelGradeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-steel-grades")
    public ResponseEntity<ListSteelGradeDTO> updateListSteelGrade(@Valid @RequestBody ListSteelGradeDTO listSteelGradeDTO) throws URISyntaxException {
        log.debug("REST request to update ListSteelGrade : {}", listSteelGradeDTO);
        if (listSteelGradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListSteelGradeDTO result = listSteelGradeService.save(listSteelGradeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listSteelGradeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-steel-grades} : get all the listSteelGrades.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listSteelGrades in body.
     */
    @GetMapping("/list-steel-grades")
    public ResponseEntity<List<ListSteelGradeDTO>> getAllListSteelGrades(ListSteelGradeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListSteelGrades by criteria: {}", criteria);
        Page<ListSteelGradeDTO> page = listSteelGradeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-steel-grades/count} : count all the listSteelGrades.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-steel-grades/count")
    public ResponseEntity<Long> countListSteelGrades(ListSteelGradeCriteria criteria) {
        log.debug("REST request to count ListSteelGrades by criteria: {}", criteria);
        return ResponseEntity.ok().body(listSteelGradeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-steel-grades/:id} : get the "id" listSteelGrade.
     *
     * @param id the id of the listSteelGradeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listSteelGradeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-steel-grades/{id}")
    public ResponseEntity<ListSteelGradeDTO> getListSteelGrade(@PathVariable Long id) {
        log.debug("REST request to get ListSteelGrade : {}", id);
        Optional<ListSteelGradeDTO> listSteelGradeDTO = listSteelGradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listSteelGradeDTO);
    }

    /**
     * {@code DELETE  /list-steel-grades/:id} : delete the "id" listSteelGrade.
     *
     * @param id the id of the listSteelGradeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-steel-grades/{id}")
    public ResponseEntity<Void> deleteListSteelGrade(@PathVariable Long id) {
        log.debug("REST request to delete ListSteelGrade : {}", id);
        listSteelGradeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
