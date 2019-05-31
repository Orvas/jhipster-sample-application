package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListBoundaryCondUcaseService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseDTO;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseCriteria;
import io.github.jhipster.application.service.ListBoundaryCondUcaseQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListBoundaryCondUcase}.
 */
@RestController
@RequestMapping("/api")
public class ListBoundaryCondUcaseResource {

    private final Logger log = LoggerFactory.getLogger(ListBoundaryCondUcaseResource.class);

    private static final String ENTITY_NAME = "listBoundaryCondUcase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListBoundaryCondUcaseService listBoundaryCondUcaseService;

    private final ListBoundaryCondUcaseQueryService listBoundaryCondUcaseQueryService;

    public ListBoundaryCondUcaseResource(ListBoundaryCondUcaseService listBoundaryCondUcaseService, ListBoundaryCondUcaseQueryService listBoundaryCondUcaseQueryService) {
        this.listBoundaryCondUcaseService = listBoundaryCondUcaseService;
        this.listBoundaryCondUcaseQueryService = listBoundaryCondUcaseQueryService;
    }

    /**
     * {@code POST  /list-boundary-cond-ucases} : Create a new listBoundaryCondUcase.
     *
     * @param listBoundaryCondUcaseDTO the listBoundaryCondUcaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listBoundaryCondUcaseDTO, or with status {@code 400 (Bad Request)} if the listBoundaryCondUcase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-boundary-cond-ucases")
    public ResponseEntity<ListBoundaryCondUcaseDTO> createListBoundaryCondUcase(@Valid @RequestBody ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO) throws URISyntaxException {
        log.debug("REST request to save ListBoundaryCondUcase : {}", listBoundaryCondUcaseDTO);
        if (listBoundaryCondUcaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new listBoundaryCondUcase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListBoundaryCondUcaseDTO result = listBoundaryCondUcaseService.save(listBoundaryCondUcaseDTO);
        return ResponseEntity.created(new URI("/api/list-boundary-cond-ucases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-boundary-cond-ucases} : Updates an existing listBoundaryCondUcase.
     *
     * @param listBoundaryCondUcaseDTO the listBoundaryCondUcaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listBoundaryCondUcaseDTO,
     * or with status {@code 400 (Bad Request)} if the listBoundaryCondUcaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listBoundaryCondUcaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-boundary-cond-ucases")
    public ResponseEntity<ListBoundaryCondUcaseDTO> updateListBoundaryCondUcase(@Valid @RequestBody ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO) throws URISyntaxException {
        log.debug("REST request to update ListBoundaryCondUcase : {}", listBoundaryCondUcaseDTO);
        if (listBoundaryCondUcaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListBoundaryCondUcaseDTO result = listBoundaryCondUcaseService.save(listBoundaryCondUcaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listBoundaryCondUcaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-boundary-cond-ucases} : get all the listBoundaryCondUcases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listBoundaryCondUcases in body.
     */
    @GetMapping("/list-boundary-cond-ucases")
    public ResponseEntity<List<ListBoundaryCondUcaseDTO>> getAllListBoundaryCondUcases(ListBoundaryCondUcaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListBoundaryCondUcases by criteria: {}", criteria);
        Page<ListBoundaryCondUcaseDTO> page = listBoundaryCondUcaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-boundary-cond-ucases/count} : count all the listBoundaryCondUcases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-boundary-cond-ucases/count")
    public ResponseEntity<Long> countListBoundaryCondUcases(ListBoundaryCondUcaseCriteria criteria) {
        log.debug("REST request to count ListBoundaryCondUcases by criteria: {}", criteria);
        return ResponseEntity.ok().body(listBoundaryCondUcaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-boundary-cond-ucases/:id} : get the "id" listBoundaryCondUcase.
     *
     * @param id the id of the listBoundaryCondUcaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listBoundaryCondUcaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-boundary-cond-ucases/{id}")
    public ResponseEntity<ListBoundaryCondUcaseDTO> getListBoundaryCondUcase(@PathVariable Long id) {
        log.debug("REST request to get ListBoundaryCondUcase : {}", id);
        Optional<ListBoundaryCondUcaseDTO> listBoundaryCondUcaseDTO = listBoundaryCondUcaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listBoundaryCondUcaseDTO);
    }

    /**
     * {@code DELETE  /list-boundary-cond-ucases/:id} : delete the "id" listBoundaryCondUcase.
     *
     * @param id the id of the listBoundaryCondUcaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-boundary-cond-ucases/{id}")
    public ResponseEntity<Void> deleteListBoundaryCondUcase(@PathVariable Long id) {
        log.debug("REST request to delete ListBoundaryCondUcase : {}", id);
        listBoundaryCondUcaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
