package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListMillLocationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListMillLocationDTO;
import io.github.jhipster.application.service.dto.ListMillLocationCriteria;
import io.github.jhipster.application.service.ListMillLocationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListMillLocation}.
 */
@RestController
@RequestMapping("/api")
public class ListMillLocationResource {

    private final Logger log = LoggerFactory.getLogger(ListMillLocationResource.class);

    private static final String ENTITY_NAME = "listMillLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListMillLocationService listMillLocationService;

    private final ListMillLocationQueryService listMillLocationQueryService;

    public ListMillLocationResource(ListMillLocationService listMillLocationService, ListMillLocationQueryService listMillLocationQueryService) {
        this.listMillLocationService = listMillLocationService;
        this.listMillLocationQueryService = listMillLocationQueryService;
    }

    /**
     * {@code POST  /list-mill-locations} : Create a new listMillLocation.
     *
     * @param listMillLocationDTO the listMillLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listMillLocationDTO, or with status {@code 400 (Bad Request)} if the listMillLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-mill-locations")
    public ResponseEntity<ListMillLocationDTO> createListMillLocation(@Valid @RequestBody ListMillLocationDTO listMillLocationDTO) throws URISyntaxException {
        log.debug("REST request to save ListMillLocation : {}", listMillLocationDTO);
        if (listMillLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listMillLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListMillLocationDTO result = listMillLocationService.save(listMillLocationDTO);
        return ResponseEntity.created(new URI("/api/list-mill-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-mill-locations} : Updates an existing listMillLocation.
     *
     * @param listMillLocationDTO the listMillLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listMillLocationDTO,
     * or with status {@code 400 (Bad Request)} if the listMillLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listMillLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-mill-locations")
    public ResponseEntity<ListMillLocationDTO> updateListMillLocation(@Valid @RequestBody ListMillLocationDTO listMillLocationDTO) throws URISyntaxException {
        log.debug("REST request to update ListMillLocation : {}", listMillLocationDTO);
        if (listMillLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListMillLocationDTO result = listMillLocationService.save(listMillLocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listMillLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-mill-locations} : get all the listMillLocations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listMillLocations in body.
     */
    @GetMapping("/list-mill-locations")
    public ResponseEntity<List<ListMillLocationDTO>> getAllListMillLocations(ListMillLocationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListMillLocations by criteria: {}", criteria);
        Page<ListMillLocationDTO> page = listMillLocationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-mill-locations/count} : count all the listMillLocations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-mill-locations/count")
    public ResponseEntity<Long> countListMillLocations(ListMillLocationCriteria criteria) {
        log.debug("REST request to count ListMillLocations by criteria: {}", criteria);
        return ResponseEntity.ok().body(listMillLocationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-mill-locations/:id} : get the "id" listMillLocation.
     *
     * @param id the id of the listMillLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listMillLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-mill-locations/{id}")
    public ResponseEntity<ListMillLocationDTO> getListMillLocation(@PathVariable Long id) {
        log.debug("REST request to get ListMillLocation : {}", id);
        Optional<ListMillLocationDTO> listMillLocationDTO = listMillLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listMillLocationDTO);
    }

    /**
     * {@code DELETE  /list-mill-locations/:id} : delete the "id" listMillLocation.
     *
     * @param id the id of the listMillLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-mill-locations/{id}")
    public ResponseEntity<Void> deleteListMillLocation(@PathVariable Long id) {
        log.debug("REST request to delete ListMillLocation : {}", id);
        listMillLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
