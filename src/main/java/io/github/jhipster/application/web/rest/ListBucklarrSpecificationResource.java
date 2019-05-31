package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListBucklarrSpecificationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationDTO;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationCriteria;
import io.github.jhipster.application.service.ListBucklarrSpecificationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListBucklarrSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ListBucklarrSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrSpecificationResource.class);

    private static final String ENTITY_NAME = "listBucklarrSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListBucklarrSpecificationService listBucklarrSpecificationService;

    private final ListBucklarrSpecificationQueryService listBucklarrSpecificationQueryService;

    public ListBucklarrSpecificationResource(ListBucklarrSpecificationService listBucklarrSpecificationService, ListBucklarrSpecificationQueryService listBucklarrSpecificationQueryService) {
        this.listBucklarrSpecificationService = listBucklarrSpecificationService;
        this.listBucklarrSpecificationQueryService = listBucklarrSpecificationQueryService;
    }

    /**
     * {@code POST  /list-bucklarr-specifications} : Create a new listBucklarrSpecification.
     *
     * @param listBucklarrSpecificationDTO the listBucklarrSpecificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listBucklarrSpecificationDTO, or with status {@code 400 (Bad Request)} if the listBucklarrSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-bucklarr-specifications")
    public ResponseEntity<ListBucklarrSpecificationDTO> createListBucklarrSpecification(@Valid @RequestBody ListBucklarrSpecificationDTO listBucklarrSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to save ListBucklarrSpecification : {}", listBucklarrSpecificationDTO);
        if (listBucklarrSpecificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listBucklarrSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListBucklarrSpecificationDTO result = listBucklarrSpecificationService.save(listBucklarrSpecificationDTO);
        return ResponseEntity.created(new URI("/api/list-bucklarr-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-bucklarr-specifications} : Updates an existing listBucklarrSpecification.
     *
     * @param listBucklarrSpecificationDTO the listBucklarrSpecificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listBucklarrSpecificationDTO,
     * or with status {@code 400 (Bad Request)} if the listBucklarrSpecificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listBucklarrSpecificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-bucklarr-specifications")
    public ResponseEntity<ListBucklarrSpecificationDTO> updateListBucklarrSpecification(@Valid @RequestBody ListBucklarrSpecificationDTO listBucklarrSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to update ListBucklarrSpecification : {}", listBucklarrSpecificationDTO);
        if (listBucklarrSpecificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListBucklarrSpecificationDTO result = listBucklarrSpecificationService.save(listBucklarrSpecificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listBucklarrSpecificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-bucklarr-specifications} : get all the listBucklarrSpecifications.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listBucklarrSpecifications in body.
     */
    @GetMapping("/list-bucklarr-specifications")
    public ResponseEntity<List<ListBucklarrSpecificationDTO>> getAllListBucklarrSpecifications(ListBucklarrSpecificationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListBucklarrSpecifications by criteria: {}", criteria);
        Page<ListBucklarrSpecificationDTO> page = listBucklarrSpecificationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-bucklarr-specifications/count} : count all the listBucklarrSpecifications.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-bucklarr-specifications/count")
    public ResponseEntity<Long> countListBucklarrSpecifications(ListBucklarrSpecificationCriteria criteria) {
        log.debug("REST request to count ListBucklarrSpecifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(listBucklarrSpecificationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-bucklarr-specifications/:id} : get the "id" listBucklarrSpecification.
     *
     * @param id the id of the listBucklarrSpecificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listBucklarrSpecificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-bucklarr-specifications/{id}")
    public ResponseEntity<ListBucklarrSpecificationDTO> getListBucklarrSpecification(@PathVariable Long id) {
        log.debug("REST request to get ListBucklarrSpecification : {}", id);
        Optional<ListBucklarrSpecificationDTO> listBucklarrSpecificationDTO = listBucklarrSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listBucklarrSpecificationDTO);
    }

    /**
     * {@code DELETE  /list-bucklarr-specifications/:id} : delete the "id" listBucklarrSpecification.
     *
     * @param id the id of the listBucklarrSpecificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-bucklarr-specifications/{id}")
    public ResponseEntity<Void> deleteListBucklarrSpecification(@PathVariable Long id) {
        log.debug("REST request to delete ListBucklarrSpecification : {}", id);
        listBucklarrSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
