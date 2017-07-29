package com.liutianhong.yikatong.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.liutianhong.yikatong.domain.Duguletian;

import com.liutianhong.yikatong.repository.DuguletianRepository;
import com.liutianhong.yikatong.web.rest.util.HeaderUtil;
import com.liutianhong.yikatong.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Duguletian.
 */
@RestController
@RequestMapping("/api")
public class DuguletianResource {

    private final Logger log = LoggerFactory.getLogger(DuguletianResource.class);

    private static final String ENTITY_NAME = "duguletian";
        
    private final DuguletianRepository duguletianRepository;

    public DuguletianResource(DuguletianRepository duguletianRepository) {
        this.duguletianRepository = duguletianRepository;
    }

    /**
     * POST  /duguletians : Create a new duguletian.
     *
     * @param duguletian the duguletian to create
     * @return the ResponseEntity with status 201 (Created) and with body the new duguletian, or with status 400 (Bad Request) if the duguletian has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/duguletians")
    @Timed
    public ResponseEntity<Duguletian> createDuguletian(@RequestBody Duguletian duguletian) throws URISyntaxException {
        log.debug("REST request to save Duguletian : {}", duguletian);
        if (duguletian.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new duguletian cannot already have an ID")).body(null);
        }
        Duguletian result = duguletianRepository.save(duguletian);
        return ResponseEntity.created(new URI("/api/duguletians/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /duguletians : Updates an existing duguletian.
     *
     * @param duguletian the duguletian to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated duguletian,
     * or with status 400 (Bad Request) if the duguletian is not valid,
     * or with status 500 (Internal Server Error) if the duguletian couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/duguletians")
    @Timed
    public ResponseEntity<Duguletian> updateDuguletian(@RequestBody Duguletian duguletian) throws URISyntaxException {
        log.debug("REST request to update Duguletian : {}", duguletian);
        if (duguletian.getId() == null) {
            return createDuguletian(duguletian);
        }
        Duguletian result = duguletianRepository.save(duguletian);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, duguletian.getId().toString()))
            .body(result);
    }

    /**
     * GET  /duguletians : get all the duguletians.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of duguletians in body
     */
    @GetMapping("/duguletians")
    @Timed
    public ResponseEntity<List<Duguletian>> getAllDuguletians(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Duguletians");
        Page<Duguletian> page = duguletianRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/duguletians");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /duguletians/:id : get the "id" duguletian.
     *
     * @param id the id of the duguletian to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the duguletian, or with status 404 (Not Found)
     */
    @GetMapping("/duguletians/{id}")
    @Timed
    public ResponseEntity<Duguletian> getDuguletian(@PathVariable Long id) {
        log.debug("REST request to get Duguletian : {}", id);
        Duguletian duguletian = duguletianRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(duguletian));
    }

    /**
     * DELETE  /duguletians/:id : delete the "id" duguletian.
     *
     * @param id the id of the duguletian to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/duguletians/{id}")
    @Timed
    public ResponseEntity<Void> deleteDuguletian(@PathVariable Long id) {
        log.debug("REST request to delete Duguletian : {}", id);
        duguletianRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
