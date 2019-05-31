package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListValveManufacturerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListValveManufacturerDTO;
import io.github.jhipster.application.service.dto.ListValveManufacturerCriteria;
import io.github.jhipster.application.service.ListValveManufacturerQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListValveManufacturer}.
 */
@RestController
@RequestMapping("/api")
public class ListValveManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ListValveManufacturerResource.class);

    private static final String ENTITY_NAME = "listValveManufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListValveManufacturerService listValveManufacturerService;

    private final ListValveManufacturerQueryService listValveManufacturerQueryService;

    public ListValveManufacturerResource(ListValveManufacturerService listValveManufacturerService, ListValveManufacturerQueryService listValveManufacturerQueryService) {
        this.listValveManufacturerService = listValveManufacturerService;
        this.listValveManufacturerQueryService = listValveManufacturerQueryService;
    }

    /**
     * {@code POST  /list-valve-manufacturers} : Create a new listValveManufacturer.
     *
     * @param listValveManufacturerDTO the listValveManufacturerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listValveManufacturerDTO, or with status {@code 400 (Bad Request)} if the listValveManufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-valve-manufacturers")
    public ResponseEntity<ListValveManufacturerDTO> createListValveManufacturer(@Valid @RequestBody ListValveManufacturerDTO listValveManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to save ListValveManufacturer : {}", listValveManufacturerDTO);
        if (listValveManufacturerDTO.getId() != null) {
            throw new BadRequestAlertException("A new listValveManufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListValveManufacturerDTO result = listValveManufacturerService.save(listValveManufacturerDTO);
        return ResponseEntity.created(new URI("/api/list-valve-manufacturers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-valve-manufacturers} : Updates an existing listValveManufacturer.
     *
     * @param listValveManufacturerDTO the listValveManufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listValveManufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the listValveManufacturerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listValveManufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-valve-manufacturers")
    public ResponseEntity<ListValveManufacturerDTO> updateListValveManufacturer(@Valid @RequestBody ListValveManufacturerDTO listValveManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to update ListValveManufacturer : {}", listValveManufacturerDTO);
        if (listValveManufacturerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListValveManufacturerDTO result = listValveManufacturerService.save(listValveManufacturerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listValveManufacturerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-valve-manufacturers} : get all the listValveManufacturers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listValveManufacturers in body.
     */
    @GetMapping("/list-valve-manufacturers")
    public ResponseEntity<List<ListValveManufacturerDTO>> getAllListValveManufacturers(ListValveManufacturerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListValveManufacturers by criteria: {}", criteria);
        Page<ListValveManufacturerDTO> page = listValveManufacturerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-valve-manufacturers/count} : count all the listValveManufacturers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-valve-manufacturers/count")
    public ResponseEntity<Long> countListValveManufacturers(ListValveManufacturerCriteria criteria) {
        log.debug("REST request to count ListValveManufacturers by criteria: {}", criteria);
        return ResponseEntity.ok().body(listValveManufacturerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-valve-manufacturers/:id} : get the "id" listValveManufacturer.
     *
     * @param id the id of the listValveManufacturerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listValveManufacturerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-valve-manufacturers/{id}")
    public ResponseEntity<ListValveManufacturerDTO> getListValveManufacturer(@PathVariable Long id) {
        log.debug("REST request to get ListValveManufacturer : {}", id);
        Optional<ListValveManufacturerDTO> listValveManufacturerDTO = listValveManufacturerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listValveManufacturerDTO);
    }

    /**
     * {@code DELETE  /list-valve-manufacturers/:id} : delete the "id" listValveManufacturer.
     *
     * @param id the id of the listValveManufacturerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-valve-manufacturers/{id}")
    public ResponseEntity<Void> deleteListValveManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete ListValveManufacturer : {}", id);
        listValveManufacturerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
