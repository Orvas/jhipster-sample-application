package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListLongSeamWeldTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeDTO;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeCriteria;
import io.github.jhipster.application.service.ListLongSeamWeldTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListLongSeamWeldType}.
 */
@RestController
@RequestMapping("/api")
public class ListLongSeamWeldTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListLongSeamWeldTypeResource.class);

    private static final String ENTITY_NAME = "listLongSeamWeldType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListLongSeamWeldTypeService listLongSeamWeldTypeService;

    private final ListLongSeamWeldTypeQueryService listLongSeamWeldTypeQueryService;

    public ListLongSeamWeldTypeResource(ListLongSeamWeldTypeService listLongSeamWeldTypeService, ListLongSeamWeldTypeQueryService listLongSeamWeldTypeQueryService) {
        this.listLongSeamWeldTypeService = listLongSeamWeldTypeService;
        this.listLongSeamWeldTypeQueryService = listLongSeamWeldTypeQueryService;
    }

    /**
     * {@code POST  /list-long-seam-weld-types} : Create a new listLongSeamWeldType.
     *
     * @param listLongSeamWeldTypeDTO the listLongSeamWeldTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listLongSeamWeldTypeDTO, or with status {@code 400 (Bad Request)} if the listLongSeamWeldType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-long-seam-weld-types")
    public ResponseEntity<ListLongSeamWeldTypeDTO> createListLongSeamWeldType(@Valid @RequestBody ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListLongSeamWeldType : {}", listLongSeamWeldTypeDTO);
        if (listLongSeamWeldTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listLongSeamWeldType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListLongSeamWeldTypeDTO result = listLongSeamWeldTypeService.save(listLongSeamWeldTypeDTO);
        return ResponseEntity.created(new URI("/api/list-long-seam-weld-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-long-seam-weld-types} : Updates an existing listLongSeamWeldType.
     *
     * @param listLongSeamWeldTypeDTO the listLongSeamWeldTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listLongSeamWeldTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listLongSeamWeldTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listLongSeamWeldTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-long-seam-weld-types")
    public ResponseEntity<ListLongSeamWeldTypeDTO> updateListLongSeamWeldType(@Valid @RequestBody ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListLongSeamWeldType : {}", listLongSeamWeldTypeDTO);
        if (listLongSeamWeldTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListLongSeamWeldTypeDTO result = listLongSeamWeldTypeService.save(listLongSeamWeldTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listLongSeamWeldTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-long-seam-weld-types} : get all the listLongSeamWeldTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listLongSeamWeldTypes in body.
     */
    @GetMapping("/list-long-seam-weld-types")
    public ResponseEntity<List<ListLongSeamWeldTypeDTO>> getAllListLongSeamWeldTypes(ListLongSeamWeldTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListLongSeamWeldTypes by criteria: {}", criteria);
        Page<ListLongSeamWeldTypeDTO> page = listLongSeamWeldTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-long-seam-weld-types/count} : count all the listLongSeamWeldTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-long-seam-weld-types/count")
    public ResponseEntity<Long> countListLongSeamWeldTypes(ListLongSeamWeldTypeCriteria criteria) {
        log.debug("REST request to count ListLongSeamWeldTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listLongSeamWeldTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-long-seam-weld-types/:id} : get the "id" listLongSeamWeldType.
     *
     * @param id the id of the listLongSeamWeldTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listLongSeamWeldTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-long-seam-weld-types/{id}")
    public ResponseEntity<ListLongSeamWeldTypeDTO> getListLongSeamWeldType(@PathVariable Long id) {
        log.debug("REST request to get ListLongSeamWeldType : {}", id);
        Optional<ListLongSeamWeldTypeDTO> listLongSeamWeldTypeDTO = listLongSeamWeldTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listLongSeamWeldTypeDTO);
    }

    /**
     * {@code DELETE  /list-long-seam-weld-types/:id} : delete the "id" listLongSeamWeldType.
     *
     * @param id the id of the listLongSeamWeldTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-long-seam-weld-types/{id}")
    public ResponseEntity<Void> deleteListLongSeamWeldType(@PathVariable Long id) {
        log.debug("REST request to delete ListLongSeamWeldType : {}", id);
        listLongSeamWeldTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
