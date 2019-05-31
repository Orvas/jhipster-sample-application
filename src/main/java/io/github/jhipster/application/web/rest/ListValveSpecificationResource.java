package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListValveSpecificationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListValveSpecificationDTO;
import io.github.jhipster.application.service.dto.ListValveSpecificationCriteria;
import io.github.jhipster.application.service.ListValveSpecificationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListValveSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ListValveSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ListValveSpecificationResource.class);

    private static final String ENTITY_NAME = "listValveSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListValveSpecificationService listValveSpecificationService;

    private final ListValveSpecificationQueryService listValveSpecificationQueryService;

    public ListValveSpecificationResource(ListValveSpecificationService listValveSpecificationService, ListValveSpecificationQueryService listValveSpecificationQueryService) {
        this.listValveSpecificationService = listValveSpecificationService;
        this.listValveSpecificationQueryService = listValveSpecificationQueryService;
    }

    /**
     * {@code POST  /list-valve-specifications} : Create a new listValveSpecification.
     *
     * @param listValveSpecificationDTO the listValveSpecificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listValveSpecificationDTO, or with status {@code 400 (Bad Request)} if the listValveSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-valve-specifications")
    public ResponseEntity<ListValveSpecificationDTO> createListValveSpecification(@Valid @RequestBody ListValveSpecificationDTO listValveSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to save ListValveSpecification : {}", listValveSpecificationDTO);
        if (listValveSpecificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listValveSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListValveSpecificationDTO result = listValveSpecificationService.save(listValveSpecificationDTO);
        return ResponseEntity.created(new URI("/api/list-valve-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-valve-specifications} : Updates an existing listValveSpecification.
     *
     * @param listValveSpecificationDTO the listValveSpecificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listValveSpecificationDTO,
     * or with status {@code 400 (Bad Request)} if the listValveSpecificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listValveSpecificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-valve-specifications")
    public ResponseEntity<ListValveSpecificationDTO> updateListValveSpecification(@Valid @RequestBody ListValveSpecificationDTO listValveSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to update ListValveSpecification : {}", listValveSpecificationDTO);
        if (listValveSpecificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListValveSpecificationDTO result = listValveSpecificationService.save(listValveSpecificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listValveSpecificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-valve-specifications} : get all the listValveSpecifications.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listValveSpecifications in body.
     */
    @GetMapping("/list-valve-specifications")
    public ResponseEntity<List<ListValveSpecificationDTO>> getAllListValveSpecifications(ListValveSpecificationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListValveSpecifications by criteria: {}", criteria);
        Page<ListValveSpecificationDTO> page = listValveSpecificationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-valve-specifications/count} : count all the listValveSpecifications.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-valve-specifications/count")
    public ResponseEntity<Long> countListValveSpecifications(ListValveSpecificationCriteria criteria) {
        log.debug("REST request to count ListValveSpecifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(listValveSpecificationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-valve-specifications/:id} : get the "id" listValveSpecification.
     *
     * @param id the id of the listValveSpecificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listValveSpecificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-valve-specifications/{id}")
    public ResponseEntity<ListValveSpecificationDTO> getListValveSpecification(@PathVariable Long id) {
        log.debug("REST request to get ListValveSpecification : {}", id);
        Optional<ListValveSpecificationDTO> listValveSpecificationDTO = listValveSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listValveSpecificationDTO);
    }

    /**
     * {@code DELETE  /list-valve-specifications/:id} : delete the "id" listValveSpecification.
     *
     * @param id the id of the listValveSpecificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-valve-specifications/{id}")
    public ResponseEntity<Void> deleteListValveSpecification(@PathVariable Long id) {
        log.debug("REST request to delete ListValveSpecification : {}", id);
        listValveSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
