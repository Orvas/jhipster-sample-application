package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListMaterialService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListMaterialDTO;
import io.github.jhipster.application.service.dto.ListMaterialCriteria;
import io.github.jhipster.application.service.ListMaterialQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListMaterial}.
 */
@RestController
@RequestMapping("/api")
public class ListMaterialResource {

    private final Logger log = LoggerFactory.getLogger(ListMaterialResource.class);

    private static final String ENTITY_NAME = "listMaterial";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListMaterialService listMaterialService;

    private final ListMaterialQueryService listMaterialQueryService;

    public ListMaterialResource(ListMaterialService listMaterialService, ListMaterialQueryService listMaterialQueryService) {
        this.listMaterialService = listMaterialService;
        this.listMaterialQueryService = listMaterialQueryService;
    }

    /**
     * {@code POST  /list-materials} : Create a new listMaterial.
     *
     * @param listMaterialDTO the listMaterialDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listMaterialDTO, or with status {@code 400 (Bad Request)} if the listMaterial has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-materials")
    public ResponseEntity<ListMaterialDTO> createListMaterial(@Valid @RequestBody ListMaterialDTO listMaterialDTO) throws URISyntaxException {
        log.debug("REST request to save ListMaterial : {}", listMaterialDTO);
        if (listMaterialDTO.getId() != null) {
            throw new BadRequestAlertException("A new listMaterial cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListMaterialDTO result = listMaterialService.save(listMaterialDTO);
        return ResponseEntity.created(new URI("/api/list-materials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-materials} : Updates an existing listMaterial.
     *
     * @param listMaterialDTO the listMaterialDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listMaterialDTO,
     * or with status {@code 400 (Bad Request)} if the listMaterialDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listMaterialDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-materials")
    public ResponseEntity<ListMaterialDTO> updateListMaterial(@Valid @RequestBody ListMaterialDTO listMaterialDTO) throws URISyntaxException {
        log.debug("REST request to update ListMaterial : {}", listMaterialDTO);
        if (listMaterialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListMaterialDTO result = listMaterialService.save(listMaterialDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listMaterialDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-materials} : get all the listMaterials.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listMaterials in body.
     */
    @GetMapping("/list-materials")
    public ResponseEntity<List<ListMaterialDTO>> getAllListMaterials(ListMaterialCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListMaterials by criteria: {}", criteria);
        Page<ListMaterialDTO> page = listMaterialQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-materials/count} : count all the listMaterials.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-materials/count")
    public ResponseEntity<Long> countListMaterials(ListMaterialCriteria criteria) {
        log.debug("REST request to count ListMaterials by criteria: {}", criteria);
        return ResponseEntity.ok().body(listMaterialQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-materials/:id} : get the "id" listMaterial.
     *
     * @param id the id of the listMaterialDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listMaterialDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-materials/{id}")
    public ResponseEntity<ListMaterialDTO> getListMaterial(@PathVariable Long id) {
        log.debug("REST request to get ListMaterial : {}", id);
        Optional<ListMaterialDTO> listMaterialDTO = listMaterialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listMaterialDTO);
    }

    /**
     * {@code DELETE  /list-materials/:id} : delete the "id" listMaterial.
     *
     * @param id the id of the listMaterialDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-materials/{id}")
    public ResponseEntity<Void> deleteListMaterial(@PathVariable Long id) {
        log.debug("REST request to delete ListMaterial : {}", id);
        listMaterialService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
