package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.FreeSpanSupportService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.FreeSpanSupportDTO;
import io.github.jhipster.application.service.dto.FreeSpanSupportCriteria;
import io.github.jhipster.application.service.FreeSpanSupportQueryService;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.FreeSpanSupport}.
 */
@RestController
@RequestMapping("/api")
public class FreeSpanSupportResource {

    private final Logger log = LoggerFactory.getLogger(FreeSpanSupportResource.class);

    private static final String ENTITY_NAME = "freeSpanSupport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FreeSpanSupportService freeSpanSupportService;

    private final FreeSpanSupportQueryService freeSpanSupportQueryService;

    public FreeSpanSupportResource(FreeSpanSupportService freeSpanSupportService, FreeSpanSupportQueryService freeSpanSupportQueryService) {
        this.freeSpanSupportService = freeSpanSupportService;
        this.freeSpanSupportQueryService = freeSpanSupportQueryService;
    }

    /**
     * {@code POST  /free-span-supports} : Create a new freeSpanSupport.
     *
     * @param freeSpanSupportDTO the freeSpanSupportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new freeSpanSupportDTO, or with status {@code 400 (Bad Request)} if the freeSpanSupport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/free-span-supports")
    public ResponseEntity<FreeSpanSupportDTO> createFreeSpanSupport(@Valid @RequestBody FreeSpanSupportDTO freeSpanSupportDTO) throws URISyntaxException {
        log.debug("REST request to save FreeSpanSupport : {}", freeSpanSupportDTO);
        if (freeSpanSupportDTO.getId() != null) {
            throw new BadRequestAlertException("A new freeSpanSupport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreeSpanSupportDTO result = freeSpanSupportService.save(freeSpanSupportDTO);
        return ResponseEntity.created(new URI("/api/free-span-supports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /free-span-supports} : Updates an existing freeSpanSupport.
     *
     * @param freeSpanSupportDTO the freeSpanSupportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freeSpanSupportDTO,
     * or with status {@code 400 (Bad Request)} if the freeSpanSupportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the freeSpanSupportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/free-span-supports")
    public ResponseEntity<FreeSpanSupportDTO> updateFreeSpanSupport(@Valid @RequestBody FreeSpanSupportDTO freeSpanSupportDTO) throws URISyntaxException {
        log.debug("REST request to update FreeSpanSupport : {}", freeSpanSupportDTO);
        if (freeSpanSupportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FreeSpanSupportDTO result = freeSpanSupportService.save(freeSpanSupportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, freeSpanSupportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /free-span-supports} : get all the freeSpanSupports.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freeSpanSupports in body.
     */
    @GetMapping("/free-span-supports")
    public ResponseEntity<List<FreeSpanSupportDTO>> getAllFreeSpanSupports(FreeSpanSupportCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get FreeSpanSupports by criteria: {}", criteria);
        Page<FreeSpanSupportDTO> page = freeSpanSupportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /free-span-supports/count} : count all the freeSpanSupports.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/free-span-supports/count")
    public ResponseEntity<Long> countFreeSpanSupports(FreeSpanSupportCriteria criteria) {
        log.debug("REST request to count FreeSpanSupports by criteria: {}", criteria);
        return ResponseEntity.ok().body(freeSpanSupportQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /free-span-supports/:id} : get the "id" freeSpanSupport.
     *
     * @param id the id of the freeSpanSupportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the freeSpanSupportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/free-span-supports/{id}")
    public ResponseEntity<FreeSpanSupportDTO> getFreeSpanSupport(@PathVariable Long id) {
        log.debug("REST request to get FreeSpanSupport : {}", id);
        Optional<FreeSpanSupportDTO> freeSpanSupportDTO = freeSpanSupportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(freeSpanSupportDTO);
    }

    /**
     * {@code DELETE  /free-span-supports/:id} : delete the "id" freeSpanSupport.
     *
     * @param id the id of the freeSpanSupportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/free-span-supports/{id}")
    public ResponseEntity<Void> deleteFreeSpanSupport(@PathVariable Long id) {
        log.debug("REST request to delete FreeSpanSupport : {}", id);
        freeSpanSupportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
