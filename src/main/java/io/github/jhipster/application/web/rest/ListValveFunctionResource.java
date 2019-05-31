package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListValveFunctionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListValveFunctionDTO;
import io.github.jhipster.application.service.dto.ListValveFunctionCriteria;
import io.github.jhipster.application.service.ListValveFunctionQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListValveFunction}.
 */
@RestController
@RequestMapping("/api")
public class ListValveFunctionResource {

    private final Logger log = LoggerFactory.getLogger(ListValveFunctionResource.class);

    private static final String ENTITY_NAME = "listValveFunction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListValveFunctionService listValveFunctionService;

    private final ListValveFunctionQueryService listValveFunctionQueryService;

    public ListValveFunctionResource(ListValveFunctionService listValveFunctionService, ListValveFunctionQueryService listValveFunctionQueryService) {
        this.listValveFunctionService = listValveFunctionService;
        this.listValveFunctionQueryService = listValveFunctionQueryService;
    }

    /**
     * {@code POST  /list-valve-functions} : Create a new listValveFunction.
     *
     * @param listValveFunctionDTO the listValveFunctionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listValveFunctionDTO, or with status {@code 400 (Bad Request)} if the listValveFunction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-valve-functions")
    public ResponseEntity<ListValveFunctionDTO> createListValveFunction(@Valid @RequestBody ListValveFunctionDTO listValveFunctionDTO) throws URISyntaxException {
        log.debug("REST request to save ListValveFunction : {}", listValveFunctionDTO);
        if (listValveFunctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new listValveFunction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListValveFunctionDTO result = listValveFunctionService.save(listValveFunctionDTO);
        return ResponseEntity.created(new URI("/api/list-valve-functions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-valve-functions} : Updates an existing listValveFunction.
     *
     * @param listValveFunctionDTO the listValveFunctionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listValveFunctionDTO,
     * or with status {@code 400 (Bad Request)} if the listValveFunctionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listValveFunctionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-valve-functions")
    public ResponseEntity<ListValveFunctionDTO> updateListValveFunction(@Valid @RequestBody ListValveFunctionDTO listValveFunctionDTO) throws URISyntaxException {
        log.debug("REST request to update ListValveFunction : {}", listValveFunctionDTO);
        if (listValveFunctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListValveFunctionDTO result = listValveFunctionService.save(listValveFunctionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listValveFunctionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-valve-functions} : get all the listValveFunctions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listValveFunctions in body.
     */
    @GetMapping("/list-valve-functions")
    public ResponseEntity<List<ListValveFunctionDTO>> getAllListValveFunctions(ListValveFunctionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListValveFunctions by criteria: {}", criteria);
        Page<ListValveFunctionDTO> page = listValveFunctionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-valve-functions/count} : count all the listValveFunctions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-valve-functions/count")
    public ResponseEntity<Long> countListValveFunctions(ListValveFunctionCriteria criteria) {
        log.debug("REST request to count ListValveFunctions by criteria: {}", criteria);
        return ResponseEntity.ok().body(listValveFunctionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-valve-functions/:id} : get the "id" listValveFunction.
     *
     * @param id the id of the listValveFunctionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listValveFunctionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-valve-functions/{id}")
    public ResponseEntity<ListValveFunctionDTO> getListValveFunction(@PathVariable Long id) {
        log.debug("REST request to get ListValveFunction : {}", id);
        Optional<ListValveFunctionDTO> listValveFunctionDTO = listValveFunctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listValveFunctionDTO);
    }

    /**
     * {@code DELETE  /list-valve-functions/:id} : delete the "id" listValveFunction.
     *
     * @param id the id of the listValveFunctionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-valve-functions/{id}")
    public ResponseEntity<Void> deleteListValveFunction(@PathVariable Long id) {
        log.debug("REST request to delete ListValveFunction : {}", id);
        listValveFunctionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
