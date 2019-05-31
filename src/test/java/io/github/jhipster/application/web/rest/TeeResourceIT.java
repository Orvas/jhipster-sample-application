package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Tee;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.repository.TeeRepository;
import io.github.jhipster.application.service.TeeService;
import io.github.jhipster.application.service.dto.TeeDTO;
import io.github.jhipster.application.service.mapper.TeeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.TeeCriteria;
import io.github.jhipster.application.service.TeeQueryService;

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
 * Integration tests for the {@Link TeeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TeeResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private TeeRepository teeRepository;

    @Autowired
    private TeeMapper teeMapper;

    @Autowired
    private TeeService teeService;

    @Autowired
    private TeeQueryService teeQueryService;

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

    private MockMvc restTeeMockMvc;

    private Tee tee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TeeResource teeResource = new TeeResource(teeService, teeQueryService);
        this.restTeeMockMvc = MockMvcBuilders.standaloneSetup(teeResource)
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
    public static Tee createEntity(EntityManager em) {
        Tee tee = new Tee()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return tee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tee createUpdatedEntity(EntityManager em) {
        Tee tee = new Tee()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return tee;
    }

    @BeforeEach
    public void initTest() {
        tee = createEntity(em);
    }

    @Test
    @Transactional
    public void createTee() throws Exception {
        int databaseSizeBeforeCreate = teeRepository.findAll().size();

        // Create the Tee
        TeeDTO teeDTO = teeMapper.toDto(tee);
        restTeeMockMvc.perform(post("/api/tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeDTO)))
            .andExpect(status().isCreated());

        // Validate the Tee in the database
        List<Tee> teeList = teeRepository.findAll();
        assertThat(teeList).hasSize(databaseSizeBeforeCreate + 1);
        Tee testTee = teeList.get(teeList.size() - 1);
        assertThat(testTee.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testTee.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testTee.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testTee.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createTeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = teeRepository.findAll().size();

        // Create the Tee with an existing ID
        tee.setId(1L);
        TeeDTO teeDTO = teeMapper.toDto(tee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeeMockMvc.perform(post("/api/tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tee in the database
        List<Tee> teeList = teeRepository.findAll();
        assertThat(teeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTees() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList
        restTeeMockMvc.perform(get("/api/tees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tee.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getTee() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get the tee
        restTeeMockMvc.perform(get("/api/tees/{id}", tee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tee.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllTeesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultTeeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the teeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultTeeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllTeesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultTeeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the teeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultTeeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllTeesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where dateCreate is not null
        defaultTeeShouldBeFound("dateCreate.specified=true");

        // Get all the teeList where dateCreate is null
        defaultTeeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultTeeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the teeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultTeeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllTeesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultTeeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the teeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultTeeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllTeesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where dateEdit is not null
        defaultTeeShouldBeFound("dateEdit.specified=true");

        // Get all the teeList where dateEdit is null
        defaultTeeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where creator equals to DEFAULT_CREATOR
        defaultTeeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the teeList where creator equals to UPDATED_CREATOR
        defaultTeeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllTeesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultTeeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the teeList where creator equals to UPDATED_CREATOR
        defaultTeeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllTeesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where creator is not null
        defaultTeeShouldBeFound("creator.specified=true");

        // Get all the teeList where creator is null
        defaultTeeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where editor equals to DEFAULT_EDITOR
        defaultTeeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the teeList where editor equals to UPDATED_EDITOR
        defaultTeeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllTeesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultTeeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the teeList where editor equals to UPDATED_EDITOR
        defaultTeeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllTeesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        // Get all the teeList where editor is not null
        defaultTeeShouldBeFound("editor.specified=true");

        // Get all the teeList where editor is null
        defaultTeeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeesByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        tee.setBaseClass(baseClass);
        teeRepository.saveAndFlush(tee);
        Long baseClassId = baseClass.getId();

        // Get all the teeList where baseClass equals to baseClassId
        defaultTeeShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the teeList where baseClass equals to baseClassId + 1
        defaultTeeShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllTeesByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        tee.setTeeHist(teeHist);
        teeHist.setTee(tee);
        teeRepository.saveAndFlush(tee);
        Long teeHistId = teeHist.getId();

        // Get all the teeList where teeHist equals to teeHistId
        defaultTeeShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the teeList where teeHist equals to teeHistId + 1
        defaultTeeShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTeeShouldBeFound(String filter) throws Exception {
        restTeeMockMvc.perform(get("/api/tees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tee.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restTeeMockMvc.perform(get("/api/tees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTeeShouldNotBeFound(String filter) throws Exception {
        restTeeMockMvc.perform(get("/api/tees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTeeMockMvc.perform(get("/api/tees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTee() throws Exception {
        // Get the tee
        restTeeMockMvc.perform(get("/api/tees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTee() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        int databaseSizeBeforeUpdate = teeRepository.findAll().size();

        // Update the tee
        Tee updatedTee = teeRepository.findById(tee.getId()).get();
        // Disconnect from session so that the updates on updatedTee are not directly saved in db
        em.detach(updatedTee);
        updatedTee
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        TeeDTO teeDTO = teeMapper.toDto(updatedTee);

        restTeeMockMvc.perform(put("/api/tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeDTO)))
            .andExpect(status().isOk());

        // Validate the Tee in the database
        List<Tee> teeList = teeRepository.findAll();
        assertThat(teeList).hasSize(databaseSizeBeforeUpdate);
        Tee testTee = teeList.get(teeList.size() - 1);
        assertThat(testTee.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testTee.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testTee.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testTee.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingTee() throws Exception {
        int databaseSizeBeforeUpdate = teeRepository.findAll().size();

        // Create the Tee
        TeeDTO teeDTO = teeMapper.toDto(tee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeeMockMvc.perform(put("/api/tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tee in the database
        List<Tee> teeList = teeRepository.findAll();
        assertThat(teeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTee() throws Exception {
        // Initialize the database
        teeRepository.saveAndFlush(tee);

        int databaseSizeBeforeDelete = teeRepository.findAll().size();

        // Delete the tee
        restTeeMockMvc.perform(delete("/api/tees/{id}", tee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Tee> teeList = teeRepository.findAll();
        assertThat(teeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tee.class);
        Tee tee1 = new Tee();
        tee1.setId(1L);
        Tee tee2 = new Tee();
        tee2.setId(tee1.getId());
        assertThat(tee1).isEqualTo(tee2);
        tee2.setId(2L);
        assertThat(tee1).isNotEqualTo(tee2);
        tee1.setId(null);
        assertThat(tee1).isNotEqualTo(tee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeeDTO.class);
        TeeDTO teeDTO1 = new TeeDTO();
        teeDTO1.setId(1L);
        TeeDTO teeDTO2 = new TeeDTO();
        assertThat(teeDTO1).isNotEqualTo(teeDTO2);
        teeDTO2.setId(teeDTO1.getId());
        assertThat(teeDTO1).isEqualTo(teeDTO2);
        teeDTO2.setId(2L);
        assertThat(teeDTO1).isNotEqualTo(teeDTO2);
        teeDTO1.setId(null);
        assertThat(teeDTO1).isNotEqualTo(teeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(teeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(teeMapper.fromId(null)).isNull();
    }
}
