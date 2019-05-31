package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListMinpressUcaseService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListMinpressUcaseDTO;
import io.github.jhipster.application.service.dto.ListMinpressUcaseCriteria;
import io.github.jhipster.application.service.ListMinpressUcaseQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListMinpressUcase}.
 */
@RestController
@RequestMapping("/api")
public class ListMinpressUcaseResource {

    private final Logger log = LoggerFactory.getLogger(ListMinpressUcaseResource.class);

    private static final String ENTITY_NAME = "listMinpressUcase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListMinpressUcaseService listMinpressUcaseService;

    private final ListMinpressUcaseQueryService listMinpressUcaseQueryService;

    public ListMinpressUcaseResource(ListMinpressUcaseService listMinpressUcaseService, ListMinpressUcaseQueryService listMinpressUcaseQueryService) {
        this.listMinpressUcaseService = listMinpressUcaseService;
        this.listMinpressUcaseQueryService = listMinpressUcaseQueryService;
    }

    /**
     * {@code POST  /list-minpress-ucases} : Create a new listMinpressUcase.
     *
     * @param listMinpressUcaseDTO the listMinpressUcaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listMinpressUcaseDTO, or with status {@code 400 (Bad Request)} if the listMinpressUcase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-minpress-ucases")
    public ResponseEntity<ListMinpressUcaseDTO> createListMinpressUcase(@Valid @RequestBody ListMinpressUcaseDTO listMinpressUcaseDTO) throws URISyntaxException {
        log.debug("REST request to save ListMinpressUcase : {}", listMinpressUcaseDTO);
        if (listMinpressUcaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new listMinpressUcase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListMinpressUcaseDTO result = listMinpressUcaseService.save(listMinpressUcaseDTO);
        return ResponseEntity.created(new URI("/api/list-minpress-ucases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-minpress-ucases} : Updates an existing listMinpressUcase.
     *
     * @param listMinpressUcaseDTO the listMinpressUcaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listMinpressUcaseDTO,
     * or with status {@code 400 (Bad Request)} if the listMinpressUcaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listMinpressUcaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-minpress-ucases")
    public ResponseEntity<ListMinpressUcaseDTO> updateListMinpressUcase(@Valid @RequestBody ListMinpressUcaseDTO listMinpressUcaseDTO) throws URISyntaxException {
        log.debug("REST request to update ListMinpressUcase : {}", listMinpressUcaseDTO);
        if (listMinpressUcaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListMinpressUcaseDTO result = listMinpressUcaseService.save(listMinpressUcaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listMinpressUcaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-minpress-ucases} : get all the listMinpressUcases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listMinpressUcases in body.
     */
    @GetMapping("/list-minpress-ucases")
    public ResponseEntity<List<ListMinpressUcaseDTO>> getAllListMinpressUcases(ListMinpressUcaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListMinpressUcases by criteria: {}", criteria);
        Page<ListMinpressUcaseDTO> page = listMinpressUcaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-minpress-ucases/count} : count all the listMinpressUcases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-minpress-ucases/count")
    public ResponseEntity<Long> countListMinpressUcases(ListMinpressUcaseCriteria criteria) {
        log.debug("REST request to count ListMinpressUcases by criteria: {}", criteria);
        return ResponseEntity.ok().body(listMinpressUcaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-minpress-ucases/:id} : get the "id" listMinpressUcase.
     *
     * @param id the id of the listMinpressUcaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listMinpressUcaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-minpress-ucases/{id}")
    public ResponseEntity<ListMinpressUcaseDTO> getListMinpressUcase(@PathVariable Long id) {
        log.debug("REST request to get ListMinpressUcase : {}", id);
        Optional<ListMinpressUcaseDTO> listMinpressUcaseDTO = listMinpressUcaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listMinpressUcaseDTO);
    }

    /**
     * {@code DELETE  /list-minpress-ucases/:id} : delete the "id" listMinpressUcase.
     *
     * @param id the id of the listMinpressUcaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-minpress-ucases/{id}")
    public ResponseEntity<Void> deleteListMinpressUcase(@PathVariable Long id) {
        log.debug("REST request to delete ListMinpressUcase : {}", id);
        listMinpressUcaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
