package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListSandTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListSandTypeDTO;
import io.github.jhipster.application.service.dto.ListSandTypeCriteria;
import io.github.jhipster.application.service.ListSandTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListSandType}.
 */
@RestController
@RequestMapping("/api")
public class ListSandTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListSandTypeResource.class);

    private static final String ENTITY_NAME = "listSandType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListSandTypeService listSandTypeService;

    private final ListSandTypeQueryService listSandTypeQueryService;

    public ListSandTypeResource(ListSandTypeService listSandTypeService, ListSandTypeQueryService listSandTypeQueryService) {
        this.listSandTypeService = listSandTypeService;
        this.listSandTypeQueryService = listSandTypeQueryService;
    }

    /**
     * {@code POST  /list-sand-types} : Create a new listSandType.
     *
     * @param listSandTypeDTO the listSandTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listSandTypeDTO, or with status {@code 400 (Bad Request)} if the listSandType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-sand-types")
    public ResponseEntity<ListSandTypeDTO> createListSandType(@Valid @RequestBody ListSandTypeDTO listSandTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListSandType : {}", listSandTypeDTO);
        if (listSandTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listSandType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListSandTypeDTO result = listSandTypeService.save(listSandTypeDTO);
        return ResponseEntity.created(new URI("/api/list-sand-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-sand-types} : Updates an existing listSandType.
     *
     * @param listSandTypeDTO the listSandTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listSandTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listSandTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listSandTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-sand-types")
    public ResponseEntity<ListSandTypeDTO> updateListSandType(@Valid @RequestBody ListSandTypeDTO listSandTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListSandType : {}", listSandTypeDTO);
        if (listSandTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListSandTypeDTO result = listSandTypeService.save(listSandTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listSandTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-sand-types} : get all the listSandTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listSandTypes in body.
     */
    @GetMapping("/list-sand-types")
    public ResponseEntity<List<ListSandTypeDTO>> getAllListSandTypes(ListSandTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListSandTypes by criteria: {}", criteria);
        Page<ListSandTypeDTO> page = listSandTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-sand-types/count} : count all the listSandTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-sand-types/count")
    public ResponseEntity<Long> countListSandTypes(ListSandTypeCriteria criteria) {
        log.debug("REST request to count ListSandTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listSandTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-sand-types/:id} : get the "id" listSandType.
     *
     * @param id the id of the listSandTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listSandTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-sand-types/{id}")
    public ResponseEntity<ListSandTypeDTO> getListSandType(@PathVariable Long id) {
        log.debug("REST request to get ListSandType : {}", id);
        Optional<ListSandTypeDTO> listSandTypeDTO = listSandTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listSandTypeDTO);
    }

    /**
     * {@code DELETE  /list-sand-types/:id} : delete the "id" listSandType.
     *
     * @param id the id of the listSandTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-sand-types/{id}")
    public ResponseEntity<Void> deleteListSandType(@PathVariable Long id) {
        log.debug("REST request to delete ListSandType : {}", id);
        listSandTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
