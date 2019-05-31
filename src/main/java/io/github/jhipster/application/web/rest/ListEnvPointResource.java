package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListEnvPointService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListEnvPointDTO;
import io.github.jhipster.application.service.dto.ListEnvPointCriteria;
import io.github.jhipster.application.service.ListEnvPointQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListEnvPoint}.
 */
@RestController
@RequestMapping("/api")
public class ListEnvPointResource {

    private final Logger log = LoggerFactory.getLogger(ListEnvPointResource.class);

    private static final String ENTITY_NAME = "listEnvPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListEnvPointService listEnvPointService;

    private final ListEnvPointQueryService listEnvPointQueryService;

    public ListEnvPointResource(ListEnvPointService listEnvPointService, ListEnvPointQueryService listEnvPointQueryService) {
        this.listEnvPointService = listEnvPointService;
        this.listEnvPointQueryService = listEnvPointQueryService;
    }

    /**
     * {@code POST  /list-env-points} : Create a new listEnvPoint.
     *
     * @param listEnvPointDTO the listEnvPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listEnvPointDTO, or with status {@code 400 (Bad Request)} if the listEnvPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-env-points")
    public ResponseEntity<ListEnvPointDTO> createListEnvPoint(@Valid @RequestBody ListEnvPointDTO listEnvPointDTO) throws URISyntaxException {
        log.debug("REST request to save ListEnvPoint : {}", listEnvPointDTO);
        if (listEnvPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new listEnvPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListEnvPointDTO result = listEnvPointService.save(listEnvPointDTO);
        return ResponseEntity.created(new URI("/api/list-env-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-env-points} : Updates an existing listEnvPoint.
     *
     * @param listEnvPointDTO the listEnvPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listEnvPointDTO,
     * or with status {@code 400 (Bad Request)} if the listEnvPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listEnvPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-env-points")
    public ResponseEntity<ListEnvPointDTO> updateListEnvPoint(@Valid @RequestBody ListEnvPointDTO listEnvPointDTO) throws URISyntaxException {
        log.debug("REST request to update ListEnvPoint : {}", listEnvPointDTO);
        if (listEnvPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListEnvPointDTO result = listEnvPointService.save(listEnvPointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listEnvPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-env-points} : get all the listEnvPoints.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listEnvPoints in body.
     */
    @GetMapping("/list-env-points")
    public ResponseEntity<List<ListEnvPointDTO>> getAllListEnvPoints(ListEnvPointCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListEnvPoints by criteria: {}", criteria);
        Page<ListEnvPointDTO> page = listEnvPointQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-env-points/count} : count all the listEnvPoints.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-env-points/count")
    public ResponseEntity<Long> countListEnvPoints(ListEnvPointCriteria criteria) {
        log.debug("REST request to count ListEnvPoints by criteria: {}", criteria);
        return ResponseEntity.ok().body(listEnvPointQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-env-points/:id} : get the "id" listEnvPoint.
     *
     * @param id the id of the listEnvPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listEnvPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-env-points/{id}")
    public ResponseEntity<ListEnvPointDTO> getListEnvPoint(@PathVariable Long id) {
        log.debug("REST request to get ListEnvPoint : {}", id);
        Optional<ListEnvPointDTO> listEnvPointDTO = listEnvPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listEnvPointDTO);
    }

    /**
     * {@code DELETE  /list-env-points/:id} : delete the "id" listEnvPoint.
     *
     * @param id the id of the listEnvPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-env-points/{id}")
    public ResponseEntity<Void> deleteListEnvPoint(@PathVariable Long id) {
        log.debug("REST request to delete ListEnvPoint : {}", id);
        listEnvPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
