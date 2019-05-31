package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListEnvDirectionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListEnvDirectionDTO;
import io.github.jhipster.application.service.dto.ListEnvDirectionCriteria;
import io.github.jhipster.application.service.ListEnvDirectionQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListEnvDirection}.
 */
@RestController
@RequestMapping("/api")
public class ListEnvDirectionResource {

    private final Logger log = LoggerFactory.getLogger(ListEnvDirectionResource.class);

    private static final String ENTITY_NAME = "listEnvDirection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListEnvDirectionService listEnvDirectionService;

    private final ListEnvDirectionQueryService listEnvDirectionQueryService;

    public ListEnvDirectionResource(ListEnvDirectionService listEnvDirectionService, ListEnvDirectionQueryService listEnvDirectionQueryService) {
        this.listEnvDirectionService = listEnvDirectionService;
        this.listEnvDirectionQueryService = listEnvDirectionQueryService;
    }

    /**
     * {@code POST  /list-env-directions} : Create a new listEnvDirection.
     *
     * @param listEnvDirectionDTO the listEnvDirectionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listEnvDirectionDTO, or with status {@code 400 (Bad Request)} if the listEnvDirection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-env-directions")
    public ResponseEntity<ListEnvDirectionDTO> createListEnvDirection(@Valid @RequestBody ListEnvDirectionDTO listEnvDirectionDTO) throws URISyntaxException {
        log.debug("REST request to save ListEnvDirection : {}", listEnvDirectionDTO);
        if (listEnvDirectionDTO.getId() != null) {
            throw new BadRequestAlertException("A new listEnvDirection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListEnvDirectionDTO result = listEnvDirectionService.save(listEnvDirectionDTO);
        return ResponseEntity.created(new URI("/api/list-env-directions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-env-directions} : Updates an existing listEnvDirection.
     *
     * @param listEnvDirectionDTO the listEnvDirectionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listEnvDirectionDTO,
     * or with status {@code 400 (Bad Request)} if the listEnvDirectionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listEnvDirectionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-env-directions")
    public ResponseEntity<ListEnvDirectionDTO> updateListEnvDirection(@Valid @RequestBody ListEnvDirectionDTO listEnvDirectionDTO) throws URISyntaxException {
        log.debug("REST request to update ListEnvDirection : {}", listEnvDirectionDTO);
        if (listEnvDirectionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListEnvDirectionDTO result = listEnvDirectionService.save(listEnvDirectionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listEnvDirectionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-env-directions} : get all the listEnvDirections.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listEnvDirections in body.
     */
    @GetMapping("/list-env-directions")
    public ResponseEntity<List<ListEnvDirectionDTO>> getAllListEnvDirections(ListEnvDirectionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListEnvDirections by criteria: {}", criteria);
        Page<ListEnvDirectionDTO> page = listEnvDirectionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-env-directions/count} : count all the listEnvDirections.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-env-directions/count")
    public ResponseEntity<Long> countListEnvDirections(ListEnvDirectionCriteria criteria) {
        log.debug("REST request to count ListEnvDirections by criteria: {}", criteria);
        return ResponseEntity.ok().body(listEnvDirectionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-env-directions/:id} : get the "id" listEnvDirection.
     *
     * @param id the id of the listEnvDirectionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listEnvDirectionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-env-directions/{id}")
    public ResponseEntity<ListEnvDirectionDTO> getListEnvDirection(@PathVariable Long id) {
        log.debug("REST request to get ListEnvDirection : {}", id);
        Optional<ListEnvDirectionDTO> listEnvDirectionDTO = listEnvDirectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listEnvDirectionDTO);
    }

    /**
     * {@code DELETE  /list-env-directions/:id} : delete the "id" listEnvDirection.
     *
     * @param id the id of the listEnvDirectionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-env-directions/{id}")
    public ResponseEntity<Void> deleteListEnvDirection(@PathVariable Long id) {
        log.debug("REST request to delete ListEnvDirection : {}", id);
        listEnvDirectionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
