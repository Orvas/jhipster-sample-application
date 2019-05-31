package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListInternalPressUcaseService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseDTO;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseCriteria;
import io.github.jhipster.application.service.ListInternalPressUcaseQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListInternalPressUcase}.
 */
@RestController
@RequestMapping("/api")
public class ListInternalPressUcaseResource {

    private final Logger log = LoggerFactory.getLogger(ListInternalPressUcaseResource.class);

    private static final String ENTITY_NAME = "listInternalPressUcase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListInternalPressUcaseService listInternalPressUcaseService;

    private final ListInternalPressUcaseQueryService listInternalPressUcaseQueryService;

    public ListInternalPressUcaseResource(ListInternalPressUcaseService listInternalPressUcaseService, ListInternalPressUcaseQueryService listInternalPressUcaseQueryService) {
        this.listInternalPressUcaseService = listInternalPressUcaseService;
        this.listInternalPressUcaseQueryService = listInternalPressUcaseQueryService;
    }

    /**
     * {@code POST  /list-internal-press-ucases} : Create a new listInternalPressUcase.
     *
     * @param listInternalPressUcaseDTO the listInternalPressUcaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listInternalPressUcaseDTO, or with status {@code 400 (Bad Request)} if the listInternalPressUcase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-internal-press-ucases")
    public ResponseEntity<ListInternalPressUcaseDTO> createListInternalPressUcase(@Valid @RequestBody ListInternalPressUcaseDTO listInternalPressUcaseDTO) throws URISyntaxException {
        log.debug("REST request to save ListInternalPressUcase : {}", listInternalPressUcaseDTO);
        if (listInternalPressUcaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new listInternalPressUcase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListInternalPressUcaseDTO result = listInternalPressUcaseService.save(listInternalPressUcaseDTO);
        return ResponseEntity.created(new URI("/api/list-internal-press-ucases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-internal-press-ucases} : Updates an existing listInternalPressUcase.
     *
     * @param listInternalPressUcaseDTO the listInternalPressUcaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listInternalPressUcaseDTO,
     * or with status {@code 400 (Bad Request)} if the listInternalPressUcaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listInternalPressUcaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-internal-press-ucases")
    public ResponseEntity<ListInternalPressUcaseDTO> updateListInternalPressUcase(@Valid @RequestBody ListInternalPressUcaseDTO listInternalPressUcaseDTO) throws URISyntaxException {
        log.debug("REST request to update ListInternalPressUcase : {}", listInternalPressUcaseDTO);
        if (listInternalPressUcaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListInternalPressUcaseDTO result = listInternalPressUcaseService.save(listInternalPressUcaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listInternalPressUcaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-internal-press-ucases} : get all the listInternalPressUcases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listInternalPressUcases in body.
     */
    @GetMapping("/list-internal-press-ucases")
    public ResponseEntity<List<ListInternalPressUcaseDTO>> getAllListInternalPressUcases(ListInternalPressUcaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListInternalPressUcases by criteria: {}", criteria);
        Page<ListInternalPressUcaseDTO> page = listInternalPressUcaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-internal-press-ucases/count} : count all the listInternalPressUcases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-internal-press-ucases/count")
    public ResponseEntity<Long> countListInternalPressUcases(ListInternalPressUcaseCriteria criteria) {
        log.debug("REST request to count ListInternalPressUcases by criteria: {}", criteria);
        return ResponseEntity.ok().body(listInternalPressUcaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-internal-press-ucases/:id} : get the "id" listInternalPressUcase.
     *
     * @param id the id of the listInternalPressUcaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listInternalPressUcaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-internal-press-ucases/{id}")
    public ResponseEntity<ListInternalPressUcaseDTO> getListInternalPressUcase(@PathVariable Long id) {
        log.debug("REST request to get ListInternalPressUcase : {}", id);
        Optional<ListInternalPressUcaseDTO> listInternalPressUcaseDTO = listInternalPressUcaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listInternalPressUcaseDTO);
    }

    /**
     * {@code DELETE  /list-internal-press-ucases/:id} : delete the "id" listInternalPressUcase.
     *
     * @param id the id of the listInternalPressUcaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-internal-press-ucases/{id}")
    public ResponseEntity<Void> deleteListInternalPressUcase(@PathVariable Long id) {
        log.debug("REST request to delete ListInternalPressUcase : {}", id);
        listInternalPressUcaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
