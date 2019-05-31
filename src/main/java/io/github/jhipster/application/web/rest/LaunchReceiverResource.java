package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.LaunchReceiverService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.LaunchReceiverDTO;
import io.github.jhipster.application.service.dto.LaunchReceiverCriteria;
import io.github.jhipster.application.service.LaunchReceiverQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.LaunchReceiver}.
 */
@RestController
@RequestMapping("/api")
public class LaunchReceiverResource {

    private final Logger log = LoggerFactory.getLogger(LaunchReceiverResource.class);

    private static final String ENTITY_NAME = "launchReceiver";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LaunchReceiverService launchReceiverService;

    private final LaunchReceiverQueryService launchReceiverQueryService;

    public LaunchReceiverResource(LaunchReceiverService launchReceiverService, LaunchReceiverQueryService launchReceiverQueryService) {
        this.launchReceiverService = launchReceiverService;
        this.launchReceiverQueryService = launchReceiverQueryService;
    }

    /**
     * {@code POST  /launch-receivers} : Create a new launchReceiver.
     *
     * @param launchReceiverDTO the launchReceiverDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new launchReceiverDTO, or with status {@code 400 (Bad Request)} if the launchReceiver has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/launch-receivers")
    public ResponseEntity<LaunchReceiverDTO> createLaunchReceiver(@Valid @RequestBody LaunchReceiverDTO launchReceiverDTO) throws URISyntaxException {
        log.debug("REST request to save LaunchReceiver : {}", launchReceiverDTO);
        if (launchReceiverDTO.getId() != null) {
            throw new BadRequestAlertException("A new launchReceiver cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LaunchReceiverDTO result = launchReceiverService.save(launchReceiverDTO);
        return ResponseEntity.created(new URI("/api/launch-receivers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /launch-receivers} : Updates an existing launchReceiver.
     *
     * @param launchReceiverDTO the launchReceiverDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated launchReceiverDTO,
     * or with status {@code 400 (Bad Request)} if the launchReceiverDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the launchReceiverDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/launch-receivers")
    public ResponseEntity<LaunchReceiverDTO> updateLaunchReceiver(@Valid @RequestBody LaunchReceiverDTO launchReceiverDTO) throws URISyntaxException {
        log.debug("REST request to update LaunchReceiver : {}", launchReceiverDTO);
        if (launchReceiverDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LaunchReceiverDTO result = launchReceiverService.save(launchReceiverDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, launchReceiverDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /launch-receivers} : get all the launchReceivers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of launchReceivers in body.
     */
    @GetMapping("/launch-receivers")
    public ResponseEntity<List<LaunchReceiverDTO>> getAllLaunchReceivers(LaunchReceiverCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get LaunchReceivers by criteria: {}", criteria);
        Page<LaunchReceiverDTO> page = launchReceiverQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /launch-receivers/count} : count all the launchReceivers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/launch-receivers/count")
    public ResponseEntity<Long> countLaunchReceivers(LaunchReceiverCriteria criteria) {
        log.debug("REST request to count LaunchReceivers by criteria: {}", criteria);
        return ResponseEntity.ok().body(launchReceiverQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /launch-receivers/:id} : get the "id" launchReceiver.
     *
     * @param id the id of the launchReceiverDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the launchReceiverDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/launch-receivers/{id}")
    public ResponseEntity<LaunchReceiverDTO> getLaunchReceiver(@PathVariable Long id) {
        log.debug("REST request to get LaunchReceiver : {}", id);
        Optional<LaunchReceiverDTO> launchReceiverDTO = launchReceiverService.findOne(id);
        return ResponseUtil.wrapOrNotFound(launchReceiverDTO);
    }

    /**
     * {@code DELETE  /launch-receivers/:id} : delete the "id" launchReceiver.
     *
     * @param id the id of the launchReceiverDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/launch-receivers/{id}")
    public ResponseEntity<Void> deleteLaunchReceiver(@PathVariable Long id) {
        log.debug("REST request to delete LaunchReceiver : {}", id);
        launchReceiverService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
