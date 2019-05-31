package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListThreatService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListThreatDTO;
import io.github.jhipster.application.service.dto.ListThreatCriteria;
import io.github.jhipster.application.service.ListThreatQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListThreat}.
 */
@RestController
@RequestMapping("/api")
public class ListThreatResource {

    private final Logger log = LoggerFactory.getLogger(ListThreatResource.class);

    private static final String ENTITY_NAME = "listThreat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListThreatService listThreatService;

    private final ListThreatQueryService listThreatQueryService;

    public ListThreatResource(ListThreatService listThreatService, ListThreatQueryService listThreatQueryService) {
        this.listThreatService = listThreatService;
        this.listThreatQueryService = listThreatQueryService;
    }

    /**
     * {@code POST  /list-threats} : Create a new listThreat.
     *
     * @param listThreatDTO the listThreatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listThreatDTO, or with status {@code 400 (Bad Request)} if the listThreat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-threats")
    public ResponseEntity<ListThreatDTO> createListThreat(@Valid @RequestBody ListThreatDTO listThreatDTO) throws URISyntaxException {
        log.debug("REST request to save ListThreat : {}", listThreatDTO);
        if (listThreatDTO.getId() != null) {
            throw new BadRequestAlertException("A new listThreat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListThreatDTO result = listThreatService.save(listThreatDTO);
        return ResponseEntity.created(new URI("/api/list-threats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-threats} : Updates an existing listThreat.
     *
     * @param listThreatDTO the listThreatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listThreatDTO,
     * or with status {@code 400 (Bad Request)} if the listThreatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listThreatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-threats")
    public ResponseEntity<ListThreatDTO> updateListThreat(@Valid @RequestBody ListThreatDTO listThreatDTO) throws URISyntaxException {
        log.debug("REST request to update ListThreat : {}", listThreatDTO);
        if (listThreatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListThreatDTO result = listThreatService.save(listThreatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listThreatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-threats} : get all the listThreats.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listThreats in body.
     */
    @GetMapping("/list-threats")
    public ResponseEntity<List<ListThreatDTO>> getAllListThreats(ListThreatCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListThreats by criteria: {}", criteria);
        Page<ListThreatDTO> page = listThreatQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-threats/count} : count all the listThreats.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-threats/count")
    public ResponseEntity<Long> countListThreats(ListThreatCriteria criteria) {
        log.debug("REST request to count ListThreats by criteria: {}", criteria);
        return ResponseEntity.ok().body(listThreatQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-threats/:id} : get the "id" listThreat.
     *
     * @param id the id of the listThreatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listThreatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-threats/{id}")
    public ResponseEntity<ListThreatDTO> getListThreat(@PathVariable Long id) {
        log.debug("REST request to get ListThreat : {}", id);
        Optional<ListThreatDTO> listThreatDTO = listThreatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listThreatDTO);
    }

    /**
     * {@code DELETE  /list-threats/:id} : delete the "id" listThreat.
     *
     * @param id the id of the listThreatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-threats/{id}")
    public ResponseEntity<Void> deleteListThreat(@PathVariable Long id) {
        log.debug("REST request to delete ListThreat : {}", id);
        listThreatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
