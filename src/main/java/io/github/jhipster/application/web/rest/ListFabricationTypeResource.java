package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListFabricationTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListFabricationTypeDTO;
import io.github.jhipster.application.service.dto.ListFabricationTypeCriteria;
import io.github.jhipster.application.service.ListFabricationTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListFabricationType}.
 */
@RestController
@RequestMapping("/api")
public class ListFabricationTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListFabricationTypeResource.class);

    private static final String ENTITY_NAME = "listFabricationType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListFabricationTypeService listFabricationTypeService;

    private final ListFabricationTypeQueryService listFabricationTypeQueryService;

    public ListFabricationTypeResource(ListFabricationTypeService listFabricationTypeService, ListFabricationTypeQueryService listFabricationTypeQueryService) {
        this.listFabricationTypeService = listFabricationTypeService;
        this.listFabricationTypeQueryService = listFabricationTypeQueryService;
    }

    /**
     * {@code POST  /list-fabrication-types} : Create a new listFabricationType.
     *
     * @param listFabricationTypeDTO the listFabricationTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listFabricationTypeDTO, or with status {@code 400 (Bad Request)} if the listFabricationType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-fabrication-types")
    public ResponseEntity<ListFabricationTypeDTO> createListFabricationType(@Valid @RequestBody ListFabricationTypeDTO listFabricationTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListFabricationType : {}", listFabricationTypeDTO);
        if (listFabricationTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listFabricationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListFabricationTypeDTO result = listFabricationTypeService.save(listFabricationTypeDTO);
        return ResponseEntity.created(new URI("/api/list-fabrication-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-fabrication-types} : Updates an existing listFabricationType.
     *
     * @param listFabricationTypeDTO the listFabricationTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listFabricationTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listFabricationTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listFabricationTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-fabrication-types")
    public ResponseEntity<ListFabricationTypeDTO> updateListFabricationType(@Valid @RequestBody ListFabricationTypeDTO listFabricationTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListFabricationType : {}", listFabricationTypeDTO);
        if (listFabricationTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListFabricationTypeDTO result = listFabricationTypeService.save(listFabricationTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listFabricationTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-fabrication-types} : get all the listFabricationTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listFabricationTypes in body.
     */
    @GetMapping("/list-fabrication-types")
    public ResponseEntity<List<ListFabricationTypeDTO>> getAllListFabricationTypes(ListFabricationTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListFabricationTypes by criteria: {}", criteria);
        Page<ListFabricationTypeDTO> page = listFabricationTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-fabrication-types/count} : count all the listFabricationTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-fabrication-types/count")
    public ResponseEntity<Long> countListFabricationTypes(ListFabricationTypeCriteria criteria) {
        log.debug("REST request to count ListFabricationTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listFabricationTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-fabrication-types/:id} : get the "id" listFabricationType.
     *
     * @param id the id of the listFabricationTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listFabricationTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-fabrication-types/{id}")
    public ResponseEntity<ListFabricationTypeDTO> getListFabricationType(@PathVariable Long id) {
        log.debug("REST request to get ListFabricationType : {}", id);
        Optional<ListFabricationTypeDTO> listFabricationTypeDTO = listFabricationTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listFabricationTypeDTO);
    }

    /**
     * {@code DELETE  /list-fabrication-types/:id} : delete the "id" listFabricationType.
     *
     * @param id the id of the listFabricationTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-fabrication-types/{id}")
    public ResponseEntity<Void> deleteListFabricationType(@PathVariable Long id) {
        log.debug("REST request to delete ListFabricationType : {}", id);
        listFabricationTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
