package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ValveService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ValveDTO;
import io.github.jhipster.application.service.dto.ValveCriteria;
import io.github.jhipster.application.service.ValveQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Valve}.
 */
@RestController
@RequestMapping("/api")
public class ValveResource {

    private final Logger log = LoggerFactory.getLogger(ValveResource.class);

    private static final String ENTITY_NAME = "valve";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValveService valveService;

    private final ValveQueryService valveQueryService;

    public ValveResource(ValveService valveService, ValveQueryService valveQueryService) {
        this.valveService = valveService;
        this.valveQueryService = valveQueryService;
    }

    /**
     * {@code POST  /valves} : Create a new valve.
     *
     * @param valveDTO the valveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new valveDTO, or with status {@code 400 (Bad Request)} if the valve has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/valves")
    public ResponseEntity<ValveDTO> createValve(@Valid @RequestBody ValveDTO valveDTO) throws URISyntaxException {
        log.debug("REST request to save Valve : {}", valveDTO);
        if (valveDTO.getId() != null) {
            throw new BadRequestAlertException("A new valve cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ValveDTO result = valveService.save(valveDTO);
        return ResponseEntity.created(new URI("/api/valves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /valves} : Updates an existing valve.
     *
     * @param valveDTO the valveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated valveDTO,
     * or with status {@code 400 (Bad Request)} if the valveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the valveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/valves")
    public ResponseEntity<ValveDTO> updateValve(@Valid @RequestBody ValveDTO valveDTO) throws URISyntaxException {
        log.debug("REST request to update Valve : {}", valveDTO);
        if (valveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ValveDTO result = valveService.save(valveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, valveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /valves} : get all the valves.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of valves in body.
     */
    @GetMapping("/valves")
    public ResponseEntity<List<ValveDTO>> getAllValves(ValveCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Valves by criteria: {}", criteria);
        Page<ValveDTO> page = valveQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /valves/count} : count all the valves.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/valves/count")
    public ResponseEntity<Long> countValves(ValveCriteria criteria) {
        log.debug("REST request to count Valves by criteria: {}", criteria);
        return ResponseEntity.ok().body(valveQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /valves/:id} : get the "id" valve.
     *
     * @param id the id of the valveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the valveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/valves/{id}")
    public ResponseEntity<ValveDTO> getValve(@PathVariable Long id) {
        log.debug("REST request to get Valve : {}", id);
        Optional<ValveDTO> valveDTO = valveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(valveDTO);
    }

    /**
     * {@code DELETE  /valves/:id} : delete the "id" valve.
     *
     * @param id the id of the valveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/valves/{id}")
    public ResponseEntity<Void> deleteValve(@PathVariable Long id) {
        log.debug("REST request to delete Valve : {}", id);
        valveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
