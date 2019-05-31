package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Cps;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.CpsHist;
import io.github.jhipster.application.domain.CpsRange;
import io.github.jhipster.application.repository.CpsRepository;
import io.github.jhipster.application.service.CpsService;
import io.github.jhipster.application.service.dto.CpsDTO;
import io.github.jhipster.application.service.mapper.CpsMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.CpsCriteria;
import io.github.jhipster.application.service.CpsQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CpsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class CpsResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private CpsRepository cpsRepository;

    @Autowired
    private CpsMapper cpsMapper;

    @Autowired
    private CpsService cpsService;

    @Autowired
    private CpsQueryService cpsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCpsMockMvc;

    private Cps cps;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CpsResource cpsResource = new CpsResource(cpsService, cpsQueryService);
        this.restCpsMockMvc = MockMvcBuilders.standaloneSetup(cpsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cps createEntity(EntityManager em) {
        Cps cps = new Cps()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return cps;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cps createUpdatedEntity(EntityManager em) {
        Cps cps = new Cps()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return cps;
    }

    @BeforeEach
    public void initTest() {
        cps = createEntity(em);
    }

    @Test
    @Transactional
    public void createCps() throws Exception {
        int databaseSizeBeforeCreate = cpsRepository.findAll().size();

        // Create the Cps
        CpsDTO cpsDTO = cpsMapper.toDto(cps);
        restCpsMockMvc.perform(post("/api/cps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsDTO)))
            .andExpect(status().isCreated());

        // Validate the Cps in the database
        List<Cps> cpsList = cpsRepository.findAll();
        assertThat(cpsList).hasSize(databaseSizeBeforeCreate + 1);
        Cps testCps = cpsList.get(cpsList.size() - 1);
        assertThat(testCps.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testCps.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testCps.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testCps.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createCpsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cpsRepository.findAll().size();

        // Create the Cps with an existing ID
        cps.setId(1L);
        CpsDTO cpsDTO = cpsMapper.toDto(cps);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCpsMockMvc.perform(post("/api/cps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cps in the database
        List<Cps> cpsList = cpsRepository.findAll();
        assertThat(cpsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCps() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList
        restCpsMockMvc.perform(get("/api/cps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cps.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getCps() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get the cps
        restCpsMockMvc.perform(get("/api/cps/{id}", cps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cps.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllCpsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultCpsShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the cpsList where dateCreate equals to UPDATED_DATE_CREATE
        defaultCpsShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllCpsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultCpsShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the cpsList where dateCreate equals to UPDATED_DATE_CREATE
        defaultCpsShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllCpsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where dateCreate is not null
        defaultCpsShouldBeFound("dateCreate.specified=true");

        // Get all the cpsList where dateCreate is null
        defaultCpsShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultCpsShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the cpsList where dateEdit equals to UPDATED_DATE_EDIT
        defaultCpsShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllCpsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultCpsShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the cpsList where dateEdit equals to UPDATED_DATE_EDIT
        defaultCpsShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllCpsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where dateEdit is not null
        defaultCpsShouldBeFound("dateEdit.specified=true");

        // Get all the cpsList where dateEdit is null
        defaultCpsShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where creator equals to DEFAULT_CREATOR
        defaultCpsShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the cpsList where creator equals to UPDATED_CREATOR
        defaultCpsShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllCpsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultCpsShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the cpsList where creator equals to UPDATED_CREATOR
        defaultCpsShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllCpsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where creator is not null
        defaultCpsShouldBeFound("creator.specified=true");

        // Get all the cpsList where creator is null
        defaultCpsShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where editor equals to DEFAULT_EDITOR
        defaultCpsShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the cpsList where editor equals to UPDATED_EDITOR
        defaultCpsShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllCpsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultCpsShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the cpsList where editor equals to UPDATED_EDITOR
        defaultCpsShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllCpsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        // Get all the cpsList where editor is not null
        defaultCpsShouldBeFound("editor.specified=true");

        // Get all the cpsList where editor is null
        defaultCpsShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        cps.setBaseClass(baseClass);
        cpsRepository.saveAndFlush(cps);
        Long baseClassId = baseClass.getId();

        // Get all the cpsList where baseClass equals to baseClassId
        defaultCpsShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the cpsList where baseClass equals to baseClassId + 1
        defaultCpsShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllCpsByCpsHistIsEqualToSomething() throws Exception {
        // Initialize the database
        CpsHist cpsHist = CpsHistResourceIT.createEntity(em);
        em.persist(cpsHist);
        em.flush();
        cps.setCpsHist(cpsHist);
        cpsHist.setCps(cps);
        cpsRepository.saveAndFlush(cps);
        Long cpsHistId = cpsHist.getId();

        // Get all the cpsList where cpsHist equals to cpsHistId
        defaultCpsShouldBeFound("cpsHistId.equals=" + cpsHistId);

        // Get all the cpsList where cpsHist equals to cpsHistId + 1
        defaultCpsShouldNotBeFound("cpsHistId.equals=" + (cpsHistId + 1));
    }


    @Test
    @Transactional
    public void getAllCpsByCpsRangeIsEqualToSomething() throws Exception {
        // Initialize the database
        CpsRange cpsRange = CpsRangeResourceIT.createEntity(em);
        em.persist(cpsRange);
        em.flush();
        cps.addCpsRange(cpsRange);
        cpsRepository.saveAndFlush(cps);
        Long cpsRangeId = cpsRange.getId();

        // Get all the cpsList where cpsRange equals to cpsRangeId
        defaultCpsShouldBeFound("cpsRangeId.equals=" + cpsRangeId);

        // Get all the cpsList where cpsRange equals to cpsRangeId + 1
        defaultCpsShouldNotBeFound("cpsRangeId.equals=" + (cpsRangeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCpsShouldBeFound(String filter) throws Exception {
        restCpsMockMvc.perform(get("/api/cps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cps.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restCpsMockMvc.perform(get("/api/cps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCpsShouldNotBeFound(String filter) throws Exception {
        restCpsMockMvc.perform(get("/api/cps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCpsMockMvc.perform(get("/api/cps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCps() throws Exception {
        // Get the cps
        restCpsMockMvc.perform(get("/api/cps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCps() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        int databaseSizeBeforeUpdate = cpsRepository.findAll().size();

        // Update the cps
        Cps updatedCps = cpsRepository.findById(cps.getId()).get();
        // Disconnect from session so that the updates on updatedCps are not directly saved in db
        em.detach(updatedCps);
        updatedCps
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        CpsDTO cpsDTO = cpsMapper.toDto(updatedCps);

        restCpsMockMvc.perform(put("/api/cps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsDTO)))
            .andExpect(status().isOk());

        // Validate the Cps in the database
        List<Cps> cpsList = cpsRepository.findAll();
        assertThat(cpsList).hasSize(databaseSizeBeforeUpdate);
        Cps testCps = cpsList.get(cpsList.size() - 1);
        assertThat(testCps.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testCps.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testCps.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testCps.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingCps() throws Exception {
        int databaseSizeBeforeUpdate = cpsRepository.findAll().size();

        // Create the Cps
        CpsDTO cpsDTO = cpsMapper.toDto(cps);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCpsMockMvc.perform(put("/api/cps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cps in the database
        List<Cps> cpsList = cpsRepository.findAll();
        assertThat(cpsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCps() throws Exception {
        // Initialize the database
        cpsRepository.saveAndFlush(cps);

        int databaseSizeBeforeDelete = cpsRepository.findAll().size();

        // Delete the cps
        restCpsMockMvc.perform(delete("/api/cps/{id}", cps.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Cps> cpsList = cpsRepository.findAll();
        assertThat(cpsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cps.class);
        Cps cps1 = new Cps();
        cps1.setId(1L);
        Cps cps2 = new Cps();
        cps2.setId(cps1.getId());
        assertThat(cps1).isEqualTo(cps2);
        cps2.setId(2L);
        assertThat(cps1).isNotEqualTo(cps2);
        cps1.setId(null);
        assertThat(cps1).isNotEqualTo(cps2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CpsDTO.class);
        CpsDTO cpsDTO1 = new CpsDTO();
        cpsDTO1.setId(1L);
        CpsDTO cpsDTO2 = new CpsDTO();
        assertThat(cpsDTO1).isNotEqualTo(cpsDTO2);
        cpsDTO2.setId(cpsDTO1.getId());
        assertThat(cpsDTO1).isEqualTo(cpsDTO2);
        cpsDTO2.setId(2L);
        assertThat(cpsDTO1).isNotEqualTo(cpsDTO2);
        cpsDTO1.setId(null);
        assertThat(cpsDTO1).isNotEqualTo(cpsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cpsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cpsMapper.fromId(null)).isNull();
    }
}
