package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListPipeManufacturerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListPipeManufacturerDTO;
import io.github.jhipster.application.service.dto.ListPipeManufacturerCriteria;
import io.github.jhipster.application.service.ListPipeManufacturerQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListPipeManufacturer}.
 */
@RestController
@RequestMapping("/api")
public class ListPipeManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ListPipeManufacturerResource.class);

    private static final String ENTITY_NAME = "listPipeManufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListPipeManufacturerService listPipeManufacturerService;

    private final ListPipeManufacturerQueryService listPipeManufacturerQueryService;

    public ListPipeManufacturerResource(ListPipeManufacturerService listPipeManufacturerService, ListPipeManufacturerQueryService listPipeManufacturerQueryService) {
        this.listPipeManufacturerService = listPipeManufacturerService;
        this.listPipeManufacturerQueryService = listPipeManufacturerQueryService;
    }

    /**
     * {@code POST  /list-pipe-manufacturers} : Create a new listPipeManufacturer.
     *
     * @param listPipeManufacturerDTO the listPipeManufacturerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listPipeManufacturerDTO, or with status {@code 400 (Bad Request)} if the listPipeManufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-pipe-manufacturers")
    public ResponseEntity<ListPipeManufacturerDTO> createListPipeManufacturer(@Valid @RequestBody ListPipeManufacturerDTO listPipeManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to save ListPipeManufacturer : {}", listPipeManufacturerDTO);
        if (listPipeManufacturerDTO.getId() != null) {
            throw new BadRequestAlertException("A new listPipeManufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListPipeManufacturerDTO result = listPipeManufacturerService.save(listPipeManufacturerDTO);
        return ResponseEntity.created(new URI("/api/list-pipe-manufacturers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-pipe-manufacturers} : Updates an existing listPipeManufacturer.
     *
     * @param listPipeManufacturerDTO the listPipeManufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listPipeManufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the listPipeManufacturerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listPipeManufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-pipe-manufacturers")
    public ResponseEntity<ListPipeManufacturerDTO> updateListPipeManufacturer(@Valid @RequestBody ListPipeManufacturerDTO listPipeManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to update ListPipeManufacturer : {}", listPipeManufacturerDTO);
        if (listPipeManufacturerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListPipeManufacturerDTO result = listPipeManufacturerService.save(listPipeManufacturerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listPipeManufacturerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-pipe-manufacturers} : get all the listPipeManufacturers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listPipeManufacturers in body.
     */
    @GetMapping("/list-pipe-manufacturers")
    public ResponseEntity<List<ListPipeManufacturerDTO>> getAllListPipeManufacturers(ListPipeManufacturerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListPipeManufacturers by criteria: {}", criteria);
        Page<ListPipeManufacturerDTO> page = listPipeManufacturerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-pipe-manufacturers/count} : count all the listPipeManufacturers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-pipe-manufacturers/count")
    public ResponseEntity<Long> countListPipeManufacturers(ListPipeManufacturerCriteria criteria) {
        log.debug("REST request to count ListPipeManufacturers by criteria: {}", criteria);
        return ResponseEntity.ok().body(listPipeManufacturerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-pipe-manufacturers/:id} : get the "id" listPipeManufacturer.
     *
     * @param id the id of the listPipeManufacturerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listPipeManufacturerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-pipe-manufacturers/{id}")
    public ResponseEntity<ListPipeManufacturerDTO> getListPipeManufacturer(@PathVariable Long id) {
        log.debug("REST request to get ListPipeManufacturer : {}", id);
        Optional<ListPipeManufacturerDTO> listPipeManufacturerDTO = listPipeManufacturerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listPipeManufacturerDTO);
    }

    /**
     * {@code DELETE  /list-pipe-manufacturers/:id} : delete the "id" listPipeManufacturer.
     *
     * @param id the id of the listPipeManufacturerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-pipe-manufacturers/{id}")
    public ResponseEntity<Void> deleteListPipeManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete ListPipeManufacturer : {}", id);
        listPipeManufacturerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
