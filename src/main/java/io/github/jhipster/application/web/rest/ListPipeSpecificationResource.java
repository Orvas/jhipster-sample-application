package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListPipeSpecificationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListPipeSpecificationDTO;
import io.github.jhipster.application.service.dto.ListPipeSpecificationCriteria;
import io.github.jhipster.application.service.ListPipeSpecificationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListPipeSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ListPipeSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ListPipeSpecificationResource.class);

    private static final String ENTITY_NAME = "listPipeSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListPipeSpecificationService listPipeSpecificationService;

    private final ListPipeSpecificationQueryService listPipeSpecificationQueryService;

    public ListPipeSpecificationResource(ListPipeSpecificationService listPipeSpecificationService, ListPipeSpecificationQueryService listPipeSpecificationQueryService) {
        this.listPipeSpecificationService = listPipeSpecificationService;
        this.listPipeSpecificationQueryService = listPipeSpecificationQueryService;
    }

    /**
     * {@code POST  /list-pipe-specifications} : Create a new listPipeSpecification.
     *
     * @param listPipeSpecificationDTO the listPipeSpecificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listPipeSpecificationDTO, or with status {@code 400 (Bad Request)} if the listPipeSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-pipe-specifications")
    public ResponseEntity<ListPipeSpecificationDTO> createListPipeSpecification(@Valid @RequestBody ListPipeSpecificationDTO listPipeSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to save ListPipeSpecification : {}", listPipeSpecificationDTO);
        if (listPipeSpecificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listPipeSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListPipeSpecificationDTO result = listPipeSpecificationService.save(listPipeSpecificationDTO);
        return ResponseEntity.created(new URI("/api/list-pipe-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-pipe-specifications} : Updates an existing listPipeSpecification.
     *
     * @param listPipeSpecificationDTO the listPipeSpecificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listPipeSpecificationDTO,
     * or with status {@code 400 (Bad Request)} if the listPipeSpecificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listPipeSpecificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-pipe-specifications")
    public ResponseEntity<ListPipeSpecificationDTO> updateListPipeSpecification(@Valid @RequestBody ListPipeSpecificationDTO listPipeSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to update ListPipeSpecification : {}", listPipeSpecificationDTO);
        if (listPipeSpecificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListPipeSpecificationDTO result = listPipeSpecificationService.save(listPipeSpecificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listPipeSpecificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-pipe-specifications} : get all the listPipeSpecifications.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listPipeSpecifications in body.
     */
    @GetMapping("/list-pipe-specifications")
    public ResponseEntity<List<ListPipeSpecificationDTO>> getAllListPipeSpecifications(ListPipeSpecificationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListPipeSpecifications by criteria: {}", criteria);
        Page<ListPipeSpecificationDTO> page = listPipeSpecificationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-pipe-specifications/count} : count all the listPipeSpecifications.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-pipe-specifications/count")
    public ResponseEntity<Long> countListPipeSpecifications(ListPipeSpecificationCriteria criteria) {
        log.debug("REST request to count ListPipeSpecifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(listPipeSpecificationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-pipe-specifications/:id} : get the "id" listPipeSpecification.
     *
     * @param id the id of the listPipeSpecificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listPipeSpecificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-pipe-specifications/{id}")
    public ResponseEntity<ListPipeSpecificationDTO> getListPipeSpecification(@PathVariable Long id) {
        log.debug("REST request to get ListPipeSpecification : {}", id);
        Optional<ListPipeSpecificationDTO> listPipeSpecificationDTO = listPipeSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listPipeSpecificationDTO);
    }

    /**
     * {@code DELETE  /list-pipe-specifications/:id} : delete the "id" listPipeSpecification.
     *
     * @param id the id of the listPipeSpecificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-pipe-specifications/{id}")
    public ResponseEntity<Void> deleteListPipeSpecification(@PathVariable Long id) {
        log.debug("REST request to delete ListPipeSpecification : {}", id);
        listPipeSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
