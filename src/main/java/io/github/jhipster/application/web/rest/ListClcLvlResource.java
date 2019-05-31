package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListClcLvlService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListClcLvlDTO;
import io.github.jhipster.application.service.dto.ListClcLvlCriteria;
import io.github.jhipster.application.service.ListClcLvlQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListClcLvl}.
 */
@RestController
@RequestMapping("/api")
public class ListClcLvlResource {

    private final Logger log = LoggerFactory.getLogger(ListClcLvlResource.class);

    private static final String ENTITY_NAME = "listClcLvl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListClcLvlService listClcLvlService;

    private final ListClcLvlQueryService listClcLvlQueryService;

    public ListClcLvlResource(ListClcLvlService listClcLvlService, ListClcLvlQueryService listClcLvlQueryService) {
        this.listClcLvlService = listClcLvlService;
        this.listClcLvlQueryService = listClcLvlQueryService;
    }

    /**
     * {@code POST  /list-clc-lvls} : Create a new listClcLvl.
     *
     * @param listClcLvlDTO the listClcLvlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listClcLvlDTO, or with status {@code 400 (Bad Request)} if the listClcLvl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-clc-lvls")
    public ResponseEntity<ListClcLvlDTO> createListClcLvl(@Valid @RequestBody ListClcLvlDTO listClcLvlDTO) throws URISyntaxException {
        log.debug("REST request to save ListClcLvl : {}", listClcLvlDTO);
        if (listClcLvlDTO.getId() != null) {
            throw new BadRequestAlertException("A new listClcLvl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListClcLvlDTO result = listClcLvlService.save(listClcLvlDTO);
        return ResponseEntity.created(new URI("/api/list-clc-lvls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-clc-lvls} : Updates an existing listClcLvl.
     *
     * @param listClcLvlDTO the listClcLvlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listClcLvlDTO,
     * or with status {@code 400 (Bad Request)} if the listClcLvlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listClcLvlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-clc-lvls")
    public ResponseEntity<ListClcLvlDTO> updateListClcLvl(@Valid @RequestBody ListClcLvlDTO listClcLvlDTO) throws URISyntaxException {
        log.debug("REST request to update ListClcLvl : {}", listClcLvlDTO);
        if (listClcLvlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListClcLvlDTO result = listClcLvlService.save(listClcLvlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listClcLvlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-clc-lvls} : get all the listClcLvls.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listClcLvls in body.
     */
    @GetMapping("/list-clc-lvls")
    public ResponseEntity<List<ListClcLvlDTO>> getAllListClcLvls(ListClcLvlCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListClcLvls by criteria: {}", criteria);
        Page<ListClcLvlDTO> page = listClcLvlQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-clc-lvls/count} : count all the listClcLvls.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-clc-lvls/count")
    public ResponseEntity<Long> countListClcLvls(ListClcLvlCriteria criteria) {
        log.debug("REST request to count ListClcLvls by criteria: {}", criteria);
        return ResponseEntity.ok().body(listClcLvlQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-clc-lvls/:id} : get the "id" listClcLvl.
     *
     * @param id the id of the listClcLvlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listClcLvlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-clc-lvls/{id}")
    public ResponseEntity<ListClcLvlDTO> getListClcLvl(@PathVariable Long id) {
        log.debug("REST request to get ListClcLvl : {}", id);
        Optional<ListClcLvlDTO> listClcLvlDTO = listClcLvlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listClcLvlDTO);
    }

    /**
     * {@code DELETE  /list-clc-lvls/:id} : delete the "id" listClcLvl.
     *
     * @param id the id of the listClcLvlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-clc-lvls/{id}")
    public ResponseEntity<Void> deleteListClcLvl(@PathVariable Long id) {
        log.debug("REST request to delete ListClcLvl : {}", id);
        listClcLvlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
