package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.LaunchReceiverHistService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.LaunchReceiverHistDTO;
import io.github.jhipster.application.service.dto.LaunchReceiverHistCriteria;
import io.github.jhipster.application.service.LaunchReceiverHistQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.LaunchReceiverHist}.
 */
@RestController
@RequestMapping("/api")
public class LaunchReceiverHistResource {

    private final Logger log = LoggerFactory.getLogger(LaunchReceiverHistResource.class);

    private static final String ENTITY_NAME = "launchReceiverHist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LaunchReceiverHistService launchReceiverHistService;

    private final LaunchReceiverHistQueryService launchReceiverHistQueryService;

    public LaunchReceiverHistResource(LaunchReceiverHistService launchReceiverHistService, LaunchReceiverHistQueryService launchReceiverHistQueryService) {
        this.launchReceiverHistService = launchReceiverHistService;
        this.launchReceiverHistQueryService = launchReceiverHistQueryService;
    }

    /**
     * {@code POST  /launch-receiver-hists} : Create a new launchReceiverHist.
     *
     * @param launchReceiverHistDTO the launchReceiverHistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new launchReceiverHistDTO, or with status {@code 400 (Bad Request)} if the launchReceiverHist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/launch-receiver-hists")
    public ResponseEntity<LaunchReceiverHistDTO> createLaunchReceiverHist(@Valid @RequestBody LaunchReceiverHistDTO launchReceiverHistDTO) throws URISyntaxException {
        log.debug("REST request to save LaunchReceiverHist : {}", launchReceiverHistDTO);
        if (launchReceiverHistDTO.getId() != null) {
            throw new BadRequestAlertException("A new launchReceiverHist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LaunchReceiverHistDTO result = launchReceiverHistService.save(launchReceiverHistDTO);
        return ResponseEntity.created(new URI("/api/launch-receiver-hists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /launch-receiver-hists} : Updates an existing launchReceiverHist.
     *
     * @param launchReceiverHistDTO the launchReceiverHistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated launchReceiverHistDTO,
     * or with status {@code 400 (Bad Request)} if the launchReceiverHistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the launchReceiverHistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/launch-receiver-hists")
    public ResponseEntity<LaunchReceiverHistDTO> updateLaunchReceiverHist(@Valid @RequestBody LaunchReceiverHistDTO launchReceiverHistDTO) throws URISyntaxException {
        log.debug("REST request to update LaunchReceiverHist : {}", launchReceiverHistDTO);
        if (launchReceiverHistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LaunchReceiverHistDTO result = launchReceiverHistService.save(launchReceiverHistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, launchReceiverHistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /launch-receiver-hists} : get all the launchReceiverHists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of launchReceiverHists in body.
     */
    @GetMapping("/launch-receiver-hists")
    public ResponseEntity<List<LaunchReceiverHistDTO>> getAllLaunchReceiverHists(LaunchReceiverHistCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get LaunchReceiverHists by criteria: {}", criteria);
        Page<LaunchReceiverHistDTO> page = launchReceiverHistQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /launch-receiver-hists/count} : count all the launchReceiverHists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/launch-receiver-hists/count")
    public ResponseEntity<Long> countLaunchReceiverHists(LaunchReceiverHistCriteria criteria) {
        log.debug("REST request to count LaunchReceiverHists by criteria: {}", criteria);
        return ResponseEntity.ok().body(launchReceiverHistQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /launch-receiver-hists/:id} : get the "id" launchReceiverHist.
     *
     * @param id the id of the launchReceiverHistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the launchReceiverHistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/launch-receiver-hists/{id}")
    public ResponseEntity<LaunchReceiverHistDTO> getLaunchReceiverHist(@PathVariable Long id) {
        log.debug("REST request to get LaunchReceiverHist : {}", id);
        Optional<LaunchReceiverHistDTO> launchReceiverHistDTO = launchReceiverHistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(launchReceiverHistDTO);
    }

    /**
     * {@code DELETE  /launch-receiver-hists/:id} : delete the "id" launchReceiverHist.
     *
     * @param id the id of the launchReceiverHistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/launch-receiver-hists/{id}")
    public ResponseEntity<Void> deleteLaunchReceiverHist(@PathVariable Long id) {
        log.debug("REST request to delete LaunchReceiverHist : {}", id);
        launchReceiverHistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
