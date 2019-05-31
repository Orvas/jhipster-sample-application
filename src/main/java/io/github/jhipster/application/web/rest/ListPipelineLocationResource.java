package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListPipelineLocationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListPipelineLocationDTO;
import io.github.jhipster.application.service.dto.ListPipelineLocationCriteria;
import io.github.jhipster.application.service.ListPipelineLocationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListPipelineLocation}.
 */
@RestController
@RequestMapping("/api")
public class ListPipelineLocationResource {

    private final Logger log = LoggerFactory.getLogger(ListPipelineLocationResource.class);

    private static final String ENTITY_NAME = "listPipelineLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListPipelineLocationService listPipelineLocationService;

    private final ListPipelineLocationQueryService listPipelineLocationQueryService;

    public ListPipelineLocationResource(ListPipelineLocationService listPipelineLocationService, ListPipelineLocationQueryService listPipelineLocationQueryService) {
        this.listPipelineLocationService = listPipelineLocationService;
        this.listPipelineLocationQueryService = listPipelineLocationQueryService;
    }

    /**
     * {@code POST  /list-pipeline-locations} : Create a new listPipelineLocation.
     *
     * @param listPipelineLocationDTO the listPipelineLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listPipelineLocationDTO, or with status {@code 400 (Bad Request)} if the listPipelineLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-pipeline-locations")
    public ResponseEntity<ListPipelineLocationDTO> createListPipelineLocation(@Valid @RequestBody ListPipelineLocationDTO listPipelineLocationDTO) throws URISyntaxException {
        log.debug("REST request to save ListPipelineLocation : {}", listPipelineLocationDTO);
        if (listPipelineLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listPipelineLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListPipelineLocationDTO result = listPipelineLocationService.save(listPipelineLocationDTO);
        return ResponseEntity.created(new URI("/api/list-pipeline-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-pipeline-locations} : Updates an existing listPipelineLocation.
     *
     * @param listPipelineLocationDTO the listPipelineLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listPipelineLocationDTO,
     * or with status {@code 400 (Bad Request)} if the listPipelineLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listPipelineLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-pipeline-locations")
    public ResponseEntity<ListPipelineLocationDTO> updateListPipelineLocation(@Valid @RequestBody ListPipelineLocationDTO listPipelineLocationDTO) throws URISyntaxException {
        log.debug("REST request to update ListPipelineLocation : {}", listPipelineLocationDTO);
        if (listPipelineLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListPipelineLocationDTO result = listPipelineLocationService.save(listPipelineLocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listPipelineLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-pipeline-locations} : get all the listPipelineLocations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listPipelineLocations in body.
     */
    @GetMapping("/list-pipeline-locations")
    public ResponseEntity<List<ListPipelineLocationDTO>> getAllListPipelineLocations(ListPipelineLocationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListPipelineLocations by criteria: {}", criteria);
        Page<ListPipelineLocationDTO> page = listPipelineLocationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-pipeline-locations/count} : count all the listPipelineLocations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-pipeline-locations/count")
    public ResponseEntity<Long> countListPipelineLocations(ListPipelineLocationCriteria criteria) {
        log.debug("REST request to count ListPipelineLocations by criteria: {}", criteria);
        return ResponseEntity.ok().body(listPipelineLocationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-pipeline-locations/:id} : get the "id" listPipelineLocation.
     *
     * @param id the id of the listPipelineLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listPipelineLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-pipeline-locations/{id}")
    public ResponseEntity<ListPipelineLocationDTO> getListPipelineLocation(@PathVariable Long id) {
        log.debug("REST request to get ListPipelineLocation : {}", id);
        Optional<ListPipelineLocationDTO> listPipelineLocationDTO = listPipelineLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listPipelineLocationDTO);
    }

    /**
     * {@code DELETE  /list-pipeline-locations/:id} : delete the "id" listPipelineLocation.
     *
     * @param id the id of the listPipelineLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-pipeline-locations/{id}")
    public ResponseEntity<Void> deleteListPipelineLocation(@PathVariable Long id) {
        log.debug("REST request to delete ListPipelineLocation : {}", id);
        listPipelineLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
