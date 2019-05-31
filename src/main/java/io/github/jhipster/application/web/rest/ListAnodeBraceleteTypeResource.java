package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListAnodeBraceleteTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeDTO;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeCriteria;
import io.github.jhipster.application.service.ListAnodeBraceleteTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListAnodeBraceleteType}.
 */
@RestController
@RequestMapping("/api")
public class ListAnodeBraceleteTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListAnodeBraceleteTypeResource.class);

    private static final String ENTITY_NAME = "listAnodeBraceleteType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListAnodeBraceleteTypeService listAnodeBraceleteTypeService;

    private final ListAnodeBraceleteTypeQueryService listAnodeBraceleteTypeQueryService;

    public ListAnodeBraceleteTypeResource(ListAnodeBraceleteTypeService listAnodeBraceleteTypeService, ListAnodeBraceleteTypeQueryService listAnodeBraceleteTypeQueryService) {
        this.listAnodeBraceleteTypeService = listAnodeBraceleteTypeService;
        this.listAnodeBraceleteTypeQueryService = listAnodeBraceleteTypeQueryService;
    }

    /**
     * {@code POST  /list-anode-bracelete-types} : Create a new listAnodeBraceleteType.
     *
     * @param listAnodeBraceleteTypeDTO the listAnodeBraceleteTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listAnodeBraceleteTypeDTO, or with status {@code 400 (Bad Request)} if the listAnodeBraceleteType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-anode-bracelete-types")
    public ResponseEntity<ListAnodeBraceleteTypeDTO> createListAnodeBraceleteType(@Valid @RequestBody ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListAnodeBraceleteType : {}", listAnodeBraceleteTypeDTO);
        if (listAnodeBraceleteTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listAnodeBraceleteType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListAnodeBraceleteTypeDTO result = listAnodeBraceleteTypeService.save(listAnodeBraceleteTypeDTO);
        return ResponseEntity.created(new URI("/api/list-anode-bracelete-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-anode-bracelete-types} : Updates an existing listAnodeBraceleteType.
     *
     * @param listAnodeBraceleteTypeDTO the listAnodeBraceleteTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listAnodeBraceleteTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listAnodeBraceleteTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listAnodeBraceleteTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-anode-bracelete-types")
    public ResponseEntity<ListAnodeBraceleteTypeDTO> updateListAnodeBraceleteType(@Valid @RequestBody ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListAnodeBraceleteType : {}", listAnodeBraceleteTypeDTO);
        if (listAnodeBraceleteTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListAnodeBraceleteTypeDTO result = listAnodeBraceleteTypeService.save(listAnodeBraceleteTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listAnodeBraceleteTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-anode-bracelete-types} : get all the listAnodeBraceleteTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listAnodeBraceleteTypes in body.
     */
    @GetMapping("/list-anode-bracelete-types")
    public ResponseEntity<List<ListAnodeBraceleteTypeDTO>> getAllListAnodeBraceleteTypes(ListAnodeBraceleteTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListAnodeBraceleteTypes by criteria: {}", criteria);
        Page<ListAnodeBraceleteTypeDTO> page = listAnodeBraceleteTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-anode-bracelete-types/count} : count all the listAnodeBraceleteTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-anode-bracelete-types/count")
    public ResponseEntity<Long> countListAnodeBraceleteTypes(ListAnodeBraceleteTypeCriteria criteria) {
        log.debug("REST request to count ListAnodeBraceleteTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listAnodeBraceleteTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-anode-bracelete-types/:id} : get the "id" listAnodeBraceleteType.
     *
     * @param id the id of the listAnodeBraceleteTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listAnodeBraceleteTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-anode-bracelete-types/{id}")
    public ResponseEntity<ListAnodeBraceleteTypeDTO> getListAnodeBraceleteType(@PathVariable Long id) {
        log.debug("REST request to get ListAnodeBraceleteType : {}", id);
        Optional<ListAnodeBraceleteTypeDTO> listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listAnodeBraceleteTypeDTO);
    }

    /**
     * {@code DELETE  /list-anode-bracelete-types/:id} : delete the "id" listAnodeBraceleteType.
     *
     * @param id the id of the listAnodeBraceleteTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-anode-bracelete-types/{id}")
    public ResponseEntity<Void> deleteListAnodeBraceleteType(@PathVariable Long id) {
        log.debug("REST request to delete ListAnodeBraceleteType : {}", id);
        listAnodeBraceleteTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
