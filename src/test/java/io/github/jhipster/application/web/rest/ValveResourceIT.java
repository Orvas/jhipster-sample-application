package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Valve;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ValveRepository;
import io.github.jhipster.application.service.ValveService;
import io.github.jhipster.application.service.dto.ValveDTO;
import io.github.jhipster.application.service.mapper.ValveMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ValveCriteria;
import io.github.jhipster.application.service.ValveQueryService;

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
 * Integration tests for the {@Link ValveResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ValveResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private ValveRepository valveRepository;

    @Autowired
    private ValveMapper valveMapper;

    @Autowired
    private ValveService valveService;

    @Autowired
    private ValveQueryService valveQueryService;

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

    private MockMvc restValveMockMvc;

    private Valve valve;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ValveResource valveResource = new ValveResource(valveService, valveQueryService);
        this.restValveMockMvc = MockMvcBuilders.standaloneSetup(valveResource)
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
    public static Valve createEntity(EntityManager em) {
        Valve valve = new Valve()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return valve;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Valve createUpdatedEntity(EntityManager em) {
        Valve valve = new Valve()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return valve;
    }

    @BeforeEach
    public void initTest() {
        valve = createEntity(em);
    }

    @Test
    @Transactional
    public void createValve() throws Exception {
        int databaseSizeBeforeCreate = valveRepository.findAll().size();

        // Create the Valve
        ValveDTO valveDTO = valveMapper.toDto(valve);
        restValveMockMvc.perform(post("/api/valves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveDTO)))
            .andExpect(status().isCreated());

        // Validate the Valve in the database
        List<Valve> valveList = valveRepository.findAll();
        assertThat(valveList).hasSize(databaseSizeBeforeCreate + 1);
        Valve testValve = valveList.get(valveList.size() - 1);
        assertThat(testValve.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testValve.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testValve.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testValve.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createValveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = valveRepository.findAll().size();

        // Create the Valve with an existing ID
        valve.setId(1L);
        ValveDTO valveDTO = valveMapper.toDto(valve);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValveMockMvc.perform(post("/api/valves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Valve in the database
        List<Valve> valveList = valveRepository.findAll();
        assertThat(valveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllValves() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList
        restValveMockMvc.perform(get("/api/valves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valve.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getValve() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get the valve
        restValveMockMvc.perform(get("/api/valves/{id}", valve.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(valve.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllValvesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultValveShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the valveList where dateCreate equals to UPDATED_DATE_CREATE
        defaultValveShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllValvesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultValveShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the valveList where dateCreate equals to UPDATED_DATE_CREATE
        defaultValveShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllValvesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where dateCreate is not null
        defaultValveShouldBeFound("dateCreate.specified=true");

        // Get all the valveList where dateCreate is null
        defaultValveShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllValvesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultValveShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the valveList where dateEdit equals to UPDATED_DATE_EDIT
        defaultValveShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllValvesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultValveShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the valveList where dateEdit equals to UPDATED_DATE_EDIT
        defaultValveShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllValvesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where dateEdit is not null
        defaultValveShouldBeFound("dateEdit.specified=true");

        // Get all the valveList where dateEdit is null
        defaultValveShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllValvesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where creator equals to DEFAULT_CREATOR
        defaultValveShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the valveList where creator equals to UPDATED_CREATOR
        defaultValveShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllValvesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultValveShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the valveList where creator equals to UPDATED_CREATOR
        defaultValveShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllValvesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where creator is not null
        defaultValveShouldBeFound("creator.specified=true");

        // Get all the valveList where creator is null
        defaultValveShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllValvesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where editor equals to DEFAULT_EDITOR
        defaultValveShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the valveList where editor equals to UPDATED_EDITOR
        defaultValveShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllValvesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultValveShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the valveList where editor equals to UPDATED_EDITOR
        defaultValveShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllValvesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        // Get all the valveList where editor is not null
        defaultValveShouldBeFound("editor.specified=true");

        // Get all the valveList where editor is null
        defaultValveShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllValvesByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        valve.setBaseClass(baseClass);
        valveRepository.saveAndFlush(valve);
        Long baseClassId = baseClass.getId();

        // Get all the valveList where baseClass equals to baseClassId
        defaultValveShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the valveList where baseClass equals to baseClassId + 1
        defaultValveShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllValvesByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        valve.setValveHist(valveHist);
        valveHist.setValve(valve);
        valveRepository.saveAndFlush(valve);
        Long valveHistId = valveHist.getId();

        // Get all the valveList where valveHist equals to valveHistId
        defaultValveShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the valveList where valveHist equals to valveHistId + 1
        defaultValveShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultValveShouldBeFound(String filter) throws Exception {
        restValveMockMvc.perform(get("/api/valves?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valve.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restValveMockMvc.perform(get("/api/valves/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultValveShouldNotBeFound(String filter) throws Exception {
        restValveMockMvc.perform(get("/api/valves?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restValveMockMvc.perform(get("/api/valves/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingValve() throws Exception {
        // Get the valve
        restValveMockMvc.perform(get("/api/valves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValve() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        int databaseSizeBeforeUpdate = valveRepository.findAll().size();

        // Update the valve
        Valve updatedValve = valveRepository.findById(valve.getId()).get();
        // Disconnect from session so that the updates on updatedValve are not directly saved in db
        em.detach(updatedValve);
        updatedValve
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ValveDTO valveDTO = valveMapper.toDto(updatedValve);

        restValveMockMvc.perform(put("/api/valves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveDTO)))
            .andExpect(status().isOk());

        // Validate the Valve in the database
        List<Valve> valveList = valveRepository.findAll();
        assertThat(valveList).hasSize(databaseSizeBeforeUpdate);
        Valve testValve = valveList.get(valveList.size() - 1);
        assertThat(testValve.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testValve.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testValve.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testValve.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingValve() throws Exception {
        int databaseSizeBeforeUpdate = valveRepository.findAll().size();

        // Create the Valve
        ValveDTO valveDTO = valveMapper.toDto(valve);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValveMockMvc.perform(put("/api/valves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Valve in the database
        List<Valve> valveList = valveRepository.findAll();
        assertThat(valveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteValve() throws Exception {
        // Initialize the database
        valveRepository.saveAndFlush(valve);

        int databaseSizeBeforeDelete = valveRepository.findAll().size();

        // Delete the valve
        restValveMockMvc.perform(delete("/api/valves/{id}", valve.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Valve> valveList = valveRepository.findAll();
        assertThat(valveList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Valve.class);
        Valve valve1 = new Valve();
        valve1.setId(1L);
        Valve valve2 = new Valve();
        valve2.setId(valve1.getId());
        assertThat(valve1).isEqualTo(valve2);
        valve2.setId(2L);
        assertThat(valve1).isNotEqualTo(valve2);
        valve1.setId(null);
        assertThat(valve1).isNotEqualTo(valve2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValveDTO.class);
        ValveDTO valveDTO1 = new ValveDTO();
        valveDTO1.setId(1L);
        ValveDTO valveDTO2 = new ValveDTO();
        assertThat(valveDTO1).isNotEqualTo(valveDTO2);
        valveDTO2.setId(valveDTO1.getId());
        assertThat(valveDTO1).isEqualTo(valveDTO2);
        valveDTO2.setId(2L);
        assertThat(valveDTO1).isNotEqualTo(valveDTO2);
        valveDTO1.setId(null);
        assertThat(valveDTO1).isNotEqualTo(valveDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(valveMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(valveMapper.fromId(null)).isNull();
    }
}
