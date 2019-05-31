package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListPipejointSpecificationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationDTO;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationCriteria;
import io.github.jhipster.application.service.ListPipejointSpecificationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListPipejointSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ListPipejointSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ListPipejointSpecificationResource.class);

    private static final String ENTITY_NAME = "listPipejointSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListPipejointSpecificationService listPipejointSpecificationService;

    private final ListPipejointSpecificationQueryService listPipejointSpecificationQueryService;

    public ListPipejointSpecificationResource(ListPipejointSpecificationService listPipejointSpecificationService, ListPipejointSpecificationQueryService listPipejointSpecificationQueryService) {
        this.listPipejointSpecificationService = listPipejointSpecificationService;
        this.listPipejointSpecificationQueryService = listPipejointSpecificationQueryService;
    }

    /**
     * {@code POST  /list-pipejoint-specifications} : Create a new listPipejointSpecification.
     *
     * @param listPipejointSpecificationDTO the listPipejointSpecificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listPipejointSpecificationDTO, or with status {@code 400 (Bad Request)} if the listPipejointSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-pipejoint-specifications")
    public ResponseEntity<ListPipejointSpecificationDTO> createListPipejointSpecification(@Valid @RequestBody ListPipejointSpecificationDTO listPipejointSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to save ListPipejointSpecification : {}", listPipejointSpecificationDTO);
        if (listPipejointSpecificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listPipejointSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListPipejointSpecificationDTO result = listPipejointSpecificationService.save(listPipejointSpecificationDTO);
        return ResponseEntity.created(new URI("/api/list-pipejoint-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-pipejoint-specifications} : Updates an existing listPipejointSpecification.
     *
     * @param listPipejointSpecificationDTO the listPipejointSpecificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listPipejointSpecificationDTO,
     * or with status {@code 400 (Bad Request)} if the listPipejointSpecificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listPipejointSpecificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-pipejoint-specifications")
    public ResponseEntity<ListPipejointSpecificationDTO> updateListPipejointSpecification(@Valid @RequestBody ListPipejointSpecificationDTO listPipejointSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to update ListPipejointSpecification : {}", listPipejointSpecificationDTO);
        if (listPipejointSpecificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListPipejointSpecificationDTO result = listPipejointSpecificationService.save(listPipejointSpecificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listPipejointSpecificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-pipejoint-specifications} : get all the listPipejointSpecifications.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listPipejointSpecifications in body.
     */
    @GetMapping("/list-pipejoint-specifications")
    public ResponseEntity<List<ListPipejointSpecificationDTO>> getAllListPipejointSpecifications(ListPipejointSpecificationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListPipejointSpecifications by criteria: {}", criteria);
        Page<ListPipejointSpecificationDTO> page = listPipejointSpecificationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-pipejoint-specifications/count} : count all the listPipejointSpecifications.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-pipejoint-specifications/count")
    public ResponseEntity<Long> countListPipejointSpecifications(ListPipejointSpecificationCriteria criteria) {
        log.debug("REST request to count ListPipejointSpecifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(listPipejointSpecificationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-pipejoint-specifications/:id} : get the "id" listPipejointSpecification.
     *
     * @param id the id of the listPipejointSpecificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listPipejointSpecificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-pipejoint-specifications/{id}")
    public ResponseEntity<ListPipejointSpecificationDTO> getListPipejointSpecification(@PathVariable Long id) {
        log.debug("REST request to get ListPipejointSpecification : {}", id);
        Optional<ListPipejointSpecificationDTO> listPipejointSpecificationDTO = listPipejointSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listPipejointSpecificationDTO);
    }

    /**
     * {@code DELETE  /list-pipejoint-specifications/:id} : delete the "id" listPipejointSpecification.
     *
     * @param id the id of the listPipejointSpecificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-pipejoint-specifications/{id}")
    public ResponseEntity<Void> deleteListPipejointSpecification(@PathVariable Long id) {
        log.debug("REST request to delete ListPipejointSpecification : {}", id);
        listPipejointSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
