package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.BendService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.BendDTO;
import io.github.jhipster.application.service.dto.BendCriteria;
import io.github.jhipster.application.service.BendQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Bend}.
 */
@RestController
@RequestMapping("/api")
public class BendResource {

    private final Logger log = LoggerFactory.getLogger(BendResource.class);

    private static final String ENTITY_NAME = "bend";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BendService bendService;

    private final BendQueryService bendQueryService;

    public BendResource(BendService bendService, BendQueryService bendQueryService) {
        this.bendService = bendService;
        this.bendQueryService = bendQueryService;
    }

    /**
     * {@code POST  /bends} : Create a new bend.
     *
     * @param bendDTO the bendDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bendDTO, or with status {@code 400 (Bad Request)} if the bend has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bends")
    public ResponseEntity<BendDTO> createBend(@Valid @RequestBody BendDTO bendDTO) throws URISyntaxException {
        log.debug("REST request to save Bend : {}", bendDTO);
        if (bendDTO.getId() != null) {
            throw new BadRequestAlertException("A new bend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BendDTO result = bendService.save(bendDTO);
        return ResponseEntity.created(new URI("/api/bends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bends} : Updates an existing bend.
     *
     * @param bendDTO the bendDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bendDTO,
     * or with status {@code 400 (Bad Request)} if the bendDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bendDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bends")
    public ResponseEntity<BendDTO> updateBend(@Valid @RequestBody BendDTO bendDTO) throws URISyntaxException {
        log.debug("REST request to update Bend : {}", bendDTO);
        if (bendDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BendDTO result = bendService.save(bendDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bendDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bends} : get all the bends.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bends in body.
     */
    @GetMapping("/bends")
    public ResponseEntity<List<BendDTO>> getAllBends(BendCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Bends by criteria: {}", criteria);
        Page<BendDTO> page = bendQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /bends/count} : count all the bends.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/bends/count")
    public ResponseEntity<Long> countBends(BendCriteria criteria) {
        log.debug("REST request to count Bends by criteria: {}", criteria);
        return ResponseEntity.ok().body(bendQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bends/:id} : get the "id" bend.
     *
     * @param id the id of the bendDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bendDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bends/{id}")
    public ResponseEntity<BendDTO> getBend(@PathVariable Long id) {
        log.debug("REST request to get Bend : {}", id);
        Optional<BendDTO> bendDTO = bendService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bendDTO);
    }

    /**
     * {@code DELETE  /bends/:id} : delete the "id" bend.
     *
     * @param id the id of the bendDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bends/{id}")
    public ResponseEntity<Void> deleteBend(@PathVariable Long id) {
        log.debug("REST request to delete Bend : {}", id);
        bendService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
