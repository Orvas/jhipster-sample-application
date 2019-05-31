package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListBendSpecificationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListBendSpecificationDTO;
import io.github.jhipster.application.service.dto.ListBendSpecificationCriteria;
import io.github.jhipster.application.service.ListBendSpecificationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListBendSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ListBendSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ListBendSpecificationResource.class);

    private static final String ENTITY_NAME = "listBendSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListBendSpecificationService listBendSpecificationService;

    private final ListBendSpecificationQueryService listBendSpecificationQueryService;

    public ListBendSpecificationResource(ListBendSpecificationService listBendSpecificationService, ListBendSpecificationQueryService listBendSpecificationQueryService) {
        this.listBendSpecificationService = listBendSpecificationService;
        this.listBendSpecificationQueryService = listBendSpecificationQueryService;
    }

    /**
     * {@code POST  /list-bend-specifications} : Create a new listBendSpecification.
     *
     * @param listBendSpecificationDTO the listBendSpecificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listBendSpecificationDTO, or with status {@code 400 (Bad Request)} if the listBendSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-bend-specifications")
    public ResponseEntity<ListBendSpecificationDTO> createListBendSpecification(@Valid @RequestBody ListBendSpecificationDTO listBendSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to save ListBendSpecification : {}", listBendSpecificationDTO);
        if (listBendSpecificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listBendSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListBendSpecificationDTO result = listBendSpecificationService.save(listBendSpecificationDTO);
        return ResponseEntity.created(new URI("/api/list-bend-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-bend-specifications} : Updates an existing listBendSpecification.
     *
     * @param listBendSpecificationDTO the listBendSpecificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listBendSpecificationDTO,
     * or with status {@code 400 (Bad Request)} if the listBendSpecificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listBendSpecificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-bend-specifications")
    public ResponseEntity<ListBendSpecificationDTO> updateListBendSpecification(@Valid @RequestBody ListBendSpecificationDTO listBendSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to update ListBendSpecification : {}", listBendSpecificationDTO);
        if (listBendSpecificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListBendSpecificationDTO result = listBendSpecificationService.save(listBendSpecificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listBendSpecificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-bend-specifications} : get all the listBendSpecifications.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listBendSpecifications in body.
     */
    @GetMapping("/list-bend-specifications")
    public ResponseEntity<List<ListBendSpecificationDTO>> getAllListBendSpecifications(ListBendSpecificationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListBendSpecifications by criteria: {}", criteria);
        Page<ListBendSpecificationDTO> page = listBendSpecificationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-bend-specifications/count} : count all the listBendSpecifications.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-bend-specifications/count")
    public ResponseEntity<Long> countListBendSpecifications(ListBendSpecificationCriteria criteria) {
        log.debug("REST request to count ListBendSpecifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(listBendSpecificationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-bend-specifications/:id} : get the "id" listBendSpecification.
     *
     * @param id the id of the listBendSpecificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listBendSpecificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-bend-specifications/{id}")
    public ResponseEntity<ListBendSpecificationDTO> getListBendSpecification(@PathVariable Long id) {
        log.debug("REST request to get ListBendSpecification : {}", id);
        Optional<ListBendSpecificationDTO> listBendSpecificationDTO = listBendSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listBendSpecificationDTO);
    }

    /**
     * {@code DELETE  /list-bend-specifications/:id} : delete the "id" listBendSpecification.
     *
     * @param id the id of the listBendSpecificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-bend-specifications/{id}")
    public ResponseEntity<Void> deleteListBendSpecification(@PathVariable Long id) {
        log.debug("REST request to delete ListBendSpecification : {}", id);
        listBendSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
