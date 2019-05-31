package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListBendTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListBendTypeDTO;
import io.github.jhipster.application.service.dto.ListBendTypeCriteria;
import io.github.jhipster.application.service.ListBendTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListBendType}.
 */
@RestController
@RequestMapping("/api")
public class ListBendTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListBendTypeResource.class);

    private static final String ENTITY_NAME = "listBendType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListBendTypeService listBendTypeService;

    private final ListBendTypeQueryService listBendTypeQueryService;

    public ListBendTypeResource(ListBendTypeService listBendTypeService, ListBendTypeQueryService listBendTypeQueryService) {
        this.listBendTypeService = listBendTypeService;
        this.listBendTypeQueryService = listBendTypeQueryService;
    }

    /**
     * {@code POST  /list-bend-types} : Create a new listBendType.
     *
     * @param listBendTypeDTO the listBendTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listBendTypeDTO, or with status {@code 400 (Bad Request)} if the listBendType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-bend-types")
    public ResponseEntity<ListBendTypeDTO> createListBendType(@Valid @RequestBody ListBendTypeDTO listBendTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListBendType : {}", listBendTypeDTO);
        if (listBendTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listBendType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListBendTypeDTO result = listBendTypeService.save(listBendTypeDTO);
        return ResponseEntity.created(new URI("/api/list-bend-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-bend-types} : Updates an existing listBendType.
     *
     * @param listBendTypeDTO the listBendTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listBendTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listBendTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listBendTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-bend-types")
    public ResponseEntity<ListBendTypeDTO> updateListBendType(@Valid @RequestBody ListBendTypeDTO listBendTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListBendType : {}", listBendTypeDTO);
        if (listBendTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListBendTypeDTO result = listBendTypeService.save(listBendTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listBendTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-bend-types} : get all the listBendTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listBendTypes in body.
     */
    @GetMapping("/list-bend-types")
    public ResponseEntity<List<ListBendTypeDTO>> getAllListBendTypes(ListBendTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListBendTypes by criteria: {}", criteria);
        Page<ListBendTypeDTO> page = listBendTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-bend-types/count} : count all the listBendTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-bend-types/count")
    public ResponseEntity<Long> countListBendTypes(ListBendTypeCriteria criteria) {
        log.debug("REST request to count ListBendTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listBendTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-bend-types/:id} : get the "id" listBendType.
     *
     * @param id the id of the listBendTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listBendTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-bend-types/{id}")
    public ResponseEntity<ListBendTypeDTO> getListBendType(@PathVariable Long id) {
        log.debug("REST request to get ListBendType : {}", id);
        Optional<ListBendTypeDTO> listBendTypeDTO = listBendTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listBendTypeDTO);
    }

    /**
     * {@code DELETE  /list-bend-types/:id} : delete the "id" listBendType.
     *
     * @param id the id of the listBendTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-bend-types/{id}")
    public ResponseEntity<Void> deleteListBendType(@PathVariable Long id) {
        log.debug("REST request to delete ListBendType : {}", id);
        listBendTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
