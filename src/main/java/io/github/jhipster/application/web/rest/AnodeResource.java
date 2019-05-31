package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.AnodeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.AnodeDTO;
import io.github.jhipster.application.service.dto.AnodeCriteria;
import io.github.jhipster.application.service.AnodeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Anode}.
 */
@RestController
@RequestMapping("/api")
public class AnodeResource {

    private final Logger log = LoggerFactory.getLogger(AnodeResource.class);

    private static final String ENTITY_NAME = "anode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnodeService anodeService;

    private final AnodeQueryService anodeQueryService;

    public AnodeResource(AnodeService anodeService, AnodeQueryService anodeQueryService) {
        this.anodeService = anodeService;
        this.anodeQueryService = anodeQueryService;
    }

    /**
     * {@code POST  /anodes} : Create a new anode.
     *
     * @param anodeDTO the anodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new anodeDTO, or with status {@code 400 (Bad Request)} if the anode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/anodes")
    public ResponseEntity<AnodeDTO> createAnode(@Valid @RequestBody AnodeDTO anodeDTO) throws URISyntaxException {
        log.debug("REST request to save Anode : {}", anodeDTO);
        if (anodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new anode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnodeDTO result = anodeService.save(anodeDTO);
        return ResponseEntity.created(new URI("/api/anodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /anodes} : Updates an existing anode.
     *
     * @param anodeDTO the anodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anodeDTO,
     * or with status {@code 400 (Bad Request)} if the anodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the anodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/anodes")
    public ResponseEntity<AnodeDTO> updateAnode(@Valid @RequestBody AnodeDTO anodeDTO) throws URISyntaxException {
        log.debug("REST request to update Anode : {}", anodeDTO);
        if (anodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnodeDTO result = anodeService.save(anodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, anodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /anodes} : get all the anodes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of anodes in body.
     */
    @GetMapping("/anodes")
    public ResponseEntity<List<AnodeDTO>> getAllAnodes(AnodeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Anodes by criteria: {}", criteria);
        Page<AnodeDTO> page = anodeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /anodes/count} : count all the anodes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/anodes/count")
    public ResponseEntity<Long> countAnodes(AnodeCriteria criteria) {
        log.debug("REST request to count Anodes by criteria: {}", criteria);
        return ResponseEntity.ok().body(anodeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /anodes/:id} : get the "id" anode.
     *
     * @param id the id of the anodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the anodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/anodes/{id}")
    public ResponseEntity<AnodeDTO> getAnode(@PathVariable Long id) {
        log.debug("REST request to get Anode : {}", id);
        Optional<AnodeDTO> anodeDTO = anodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anodeDTO);
    }

    /**
     * {@code DELETE  /anodes/:id} : delete the "id" anode.
     *
     * @param id the id of the anodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/anodes/{id}")
    public ResponseEntity<Void> deleteAnode(@PathVariable Long id) {
        log.debug("REST request to delete Anode : {}", id);
        anodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
