package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListTeeSpecificationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListTeeSpecificationDTO;
import io.github.jhipster.application.service.dto.ListTeeSpecificationCriteria;
import io.github.jhipster.application.service.ListTeeSpecificationQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListTeeSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ListTeeSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ListTeeSpecificationResource.class);

    private static final String ENTITY_NAME = "listTeeSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListTeeSpecificationService listTeeSpecificationService;

    private final ListTeeSpecificationQueryService listTeeSpecificationQueryService;

    public ListTeeSpecificationResource(ListTeeSpecificationService listTeeSpecificationService, ListTeeSpecificationQueryService listTeeSpecificationQueryService) {
        this.listTeeSpecificationService = listTeeSpecificationService;
        this.listTeeSpecificationQueryService = listTeeSpecificationQueryService;
    }

    /**
     * {@code POST  /list-tee-specifications} : Create a new listTeeSpecification.
     *
     * @param listTeeSpecificationDTO the listTeeSpecificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listTeeSpecificationDTO, or with status {@code 400 (Bad Request)} if the listTeeSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-tee-specifications")
    public ResponseEntity<ListTeeSpecificationDTO> createListTeeSpecification(@Valid @RequestBody ListTeeSpecificationDTO listTeeSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to save ListTeeSpecification : {}", listTeeSpecificationDTO);
        if (listTeeSpecificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new listTeeSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListTeeSpecificationDTO result = listTeeSpecificationService.save(listTeeSpecificationDTO);
        return ResponseEntity.created(new URI("/api/list-tee-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-tee-specifications} : Updates an existing listTeeSpecification.
     *
     * @param listTeeSpecificationDTO the listTeeSpecificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listTeeSpecificationDTO,
     * or with status {@code 400 (Bad Request)} if the listTeeSpecificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listTeeSpecificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-tee-specifications")
    public ResponseEntity<ListTeeSpecificationDTO> updateListTeeSpecification(@Valid @RequestBody ListTeeSpecificationDTO listTeeSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to update ListTeeSpecification : {}", listTeeSpecificationDTO);
        if (listTeeSpecificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListTeeSpecificationDTO result = listTeeSpecificationService.save(listTeeSpecificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listTeeSpecificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-tee-specifications} : get all the listTeeSpecifications.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listTeeSpecifications in body.
     */
    @GetMapping("/list-tee-specifications")
    public ResponseEntity<List<ListTeeSpecificationDTO>> getAllListTeeSpecifications(ListTeeSpecificationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListTeeSpecifications by criteria: {}", criteria);
        Page<ListTeeSpecificationDTO> page = listTeeSpecificationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-tee-specifications/count} : count all the listTeeSpecifications.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-tee-specifications/count")
    public ResponseEntity<Long> countListTeeSpecifications(ListTeeSpecificationCriteria criteria) {
        log.debug("REST request to count ListTeeSpecifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(listTeeSpecificationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-tee-specifications/:id} : get the "id" listTeeSpecification.
     *
     * @param id the id of the listTeeSpecificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listTeeSpecificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-tee-specifications/{id}")
    public ResponseEntity<ListTeeSpecificationDTO> getListTeeSpecification(@PathVariable Long id) {
        log.debug("REST request to get ListTeeSpecification : {}", id);
        Optional<ListTeeSpecificationDTO> listTeeSpecificationDTO = listTeeSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listTeeSpecificationDTO);
    }

    /**
     * {@code DELETE  /list-tee-specifications/:id} : delete the "id" listTeeSpecification.
     *
     * @param id the id of the listTeeSpecificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-tee-specifications/{id}")
    public ResponseEntity<Void> deleteListTeeSpecification(@PathVariable Long id) {
        log.debug("REST request to delete ListTeeSpecification : {}", id);
        listTeeSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
