package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListPipejointTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListPipejointTypeDTO;
import io.github.jhipster.application.service.dto.ListPipejointTypeCriteria;
import io.github.jhipster.application.service.ListPipejointTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListPipejointType}.
 */
@RestController
@RequestMapping("/api")
public class ListPipejointTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListPipejointTypeResource.class);

    private static final String ENTITY_NAME = "listPipejointType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListPipejointTypeService listPipejointTypeService;

    private final ListPipejointTypeQueryService listPipejointTypeQueryService;

    public ListPipejointTypeResource(ListPipejointTypeService listPipejointTypeService, ListPipejointTypeQueryService listPipejointTypeQueryService) {
        this.listPipejointTypeService = listPipejointTypeService;
        this.listPipejointTypeQueryService = listPipejointTypeQueryService;
    }

    /**
     * {@code POST  /list-pipejoint-types} : Create a new listPipejointType.
     *
     * @param listPipejointTypeDTO the listPipejointTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listPipejointTypeDTO, or with status {@code 400 (Bad Request)} if the listPipejointType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-pipejoint-types")
    public ResponseEntity<ListPipejointTypeDTO> createListPipejointType(@Valid @RequestBody ListPipejointTypeDTO listPipejointTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListPipejointType : {}", listPipejointTypeDTO);
        if (listPipejointTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listPipejointType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListPipejointTypeDTO result = listPipejointTypeService.save(listPipejointTypeDTO);
        return ResponseEntity.created(new URI("/api/list-pipejoint-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-pipejoint-types} : Updates an existing listPipejointType.
     *
     * @param listPipejointTypeDTO the listPipejointTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listPipejointTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listPipejointTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listPipejointTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-pipejoint-types")
    public ResponseEntity<ListPipejointTypeDTO> updateListPipejointType(@Valid @RequestBody ListPipejointTypeDTO listPipejointTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListPipejointType : {}", listPipejointTypeDTO);
        if (listPipejointTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListPipejointTypeDTO result = listPipejointTypeService.save(listPipejointTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listPipejointTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-pipejoint-types} : get all the listPipejointTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listPipejointTypes in body.
     */
    @GetMapping("/list-pipejoint-types")
    public ResponseEntity<List<ListPipejointTypeDTO>> getAllListPipejointTypes(ListPipejointTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListPipejointTypes by criteria: {}", criteria);
        Page<ListPipejointTypeDTO> page = listPipejointTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-pipejoint-types/count} : count all the listPipejointTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-pipejoint-types/count")
    public ResponseEntity<Long> countListPipejointTypes(ListPipejointTypeCriteria criteria) {
        log.debug("REST request to count ListPipejointTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listPipejointTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-pipejoint-types/:id} : get the "id" listPipejointType.
     *
     * @param id the id of the listPipejointTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listPipejointTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-pipejoint-types/{id}")
    public ResponseEntity<ListPipejointTypeDTO> getListPipejointType(@PathVariable Long id) {
        log.debug("REST request to get ListPipejointType : {}", id);
        Optional<ListPipejointTypeDTO> listPipejointTypeDTO = listPipejointTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listPipejointTypeDTO);
    }

    /**
     * {@code DELETE  /list-pipejoint-types/:id} : delete the "id" listPipejointType.
     *
     * @param id the id of the listPipejointTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-pipejoint-types/{id}")
    public ResponseEntity<Void> deleteListPipejointType(@PathVariable Long id) {
        log.debug("REST request to delete ListPipejointType : {}", id);
        listPipejointTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
