package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Anode;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.repository.AnodeRepository;
import io.github.jhipster.application.service.AnodeService;
import io.github.jhipster.application.service.dto.AnodeDTO;
import io.github.jhipster.application.service.mapper.AnodeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.AnodeCriteria;
import io.github.jhipster.application.service.AnodeQueryService;

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
 * Integration tests for the {@Link AnodeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AnodeResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private AnodeRepository anodeRepository;

    @Autowired
    private AnodeMapper anodeMapper;

    @Autowired
    private AnodeService anodeService;

    @Autowired
    private AnodeQueryService anodeQueryService;

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

    private MockMvc restAnodeMockMvc;

    private Anode anode;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnodeResource anodeResource = new AnodeResource(anodeService, anodeQueryService);
        this.restAnodeMockMvc = MockMvcBuilders.standaloneSetup(anodeResource)
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
    public static Anode createEntity(EntityManager em) {
        Anode anode = new Anode()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        BaseClass baseClass;
        if (TestUtil.findAll(em, BaseClass.class).isEmpty()) {
            baseClass = BaseClassResourceIT.createEntity(em);
            em.persist(baseClass);
            em.flush();
        } else {
            baseClass = TestUtil.findAll(em, BaseClass.class).get(0);
        }
        anode.setId(baseClass);
        return anode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Anode createUpdatedEntity(EntityManager em) {
        Anode anode = new Anode()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        BaseClass baseClass;
        if (TestUtil.findAll(em, BaseClass.class).isEmpty()) {
            baseClass = BaseClassResourceIT.createUpdatedEntity(em);
            em.persist(baseClass);
            em.flush();
        } else {
            baseClass = TestUtil.findAll(em, BaseClass.class).get(0);
        }
        anode.setId(baseClass);
        return anode;
    }

    @BeforeEach
    public void initTest() {
        anode = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnode() throws Exception {
        int databaseSizeBeforeCreate = anodeRepository.findAll().size();

        // Create the Anode
        AnodeDTO anodeDTO = anodeMapper.toDto(anode);
        restAnodeMockMvc.perform(post("/api/anodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeDTO)))
            .andExpect(status().isCreated());

        // Validate the Anode in the database
        List<Anode> anodeList = anodeRepository.findAll();
        assertThat(anodeList).hasSize(databaseSizeBeforeCreate + 1);
        Anode testAnode = anodeList.get(anodeList.size() - 1);
        assertThat(testAnode.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testAnode.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testAnode.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testAnode.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createAnodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = anodeRepository.findAll().size();

        // Create the Anode with an existing ID
        anode.setId(1L);
        AnodeDTO anodeDTO = anodeMapper.toDto(anode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnodeMockMvc.perform(post("/api/anodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Anode in the database
        List<Anode> anodeList = anodeRepository.findAll();
        assertThat(anodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAnodes() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList
        restAnodeMockMvc.perform(get("/api/anodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anode.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getAnode() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get the anode
        restAnodeMockMvc.perform(get("/api/anodes/{id}", anode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(anode.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllAnodesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultAnodeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the anodeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultAnodeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllAnodesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultAnodeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the anodeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultAnodeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllAnodesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where dateCreate is not null
        defaultAnodeShouldBeFound("dateCreate.specified=true");

        // Get all the anodeList where dateCreate is null
        defaultAnodeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultAnodeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the anodeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultAnodeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllAnodesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultAnodeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the anodeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultAnodeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllAnodesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where dateEdit is not null
        defaultAnodeShouldBeFound("dateEdit.specified=true");

        // Get all the anodeList where dateEdit is null
        defaultAnodeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where creator equals to DEFAULT_CREATOR
        defaultAnodeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the anodeList where creator equals to UPDATED_CREATOR
        defaultAnodeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllAnodesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultAnodeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the anodeList where creator equals to UPDATED_CREATOR
        defaultAnodeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllAnodesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where creator is not null
        defaultAnodeShouldBeFound("creator.specified=true");

        // Get all the anodeList where creator is null
        defaultAnodeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where editor equals to DEFAULT_EDITOR
        defaultAnodeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the anodeList where editor equals to UPDATED_EDITOR
        defaultAnodeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllAnodesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultAnodeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the anodeList where editor equals to UPDATED_EDITOR
        defaultAnodeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllAnodesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        // Get all the anodeList where editor is not null
        defaultAnodeShouldBeFound("editor.specified=true");

        // Get all the anodeList where editor is null
        defaultAnodeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        BaseClass id = anode.getId();
        anodeRepository.saveAndFlush(anode);
        Long idId = id.getId();

        // Get all the anodeList where id equals to idId
        defaultAnodeShouldBeFound("idId.equals=" + idId);

        // Get all the anodeList where id equals to idId + 1
        defaultAnodeShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllAnodesByAnodeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        AnodeHist anodeHist = AnodeHistResourceIT.createEntity(em);
        em.persist(anodeHist);
        em.flush();
        anode.addAnodeHist(anodeHist);
        anodeRepository.saveAndFlush(anode);
        Long anodeHistId = anodeHist.getId();

        // Get all the anodeList where anodeHist equals to anodeHistId
        defaultAnodeShouldBeFound("anodeHistId.equals=" + anodeHistId);

        // Get all the anodeList where anodeHist equals to anodeHistId + 1
        defaultAnodeShouldNotBeFound("anodeHistId.equals=" + (anodeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAnodeShouldBeFound(String filter) throws Exception {
        restAnodeMockMvc.perform(get("/api/anodes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anode.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restAnodeMockMvc.perform(get("/api/anodes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAnodeShouldNotBeFound(String filter) throws Exception {
        restAnodeMockMvc.perform(get("/api/anodes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAnodeMockMvc.perform(get("/api/anodes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAnode() throws Exception {
        // Get the anode
        restAnodeMockMvc.perform(get("/api/anodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnode() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        int databaseSizeBeforeUpdate = anodeRepository.findAll().size();

        // Update the anode
        Anode updatedAnode = anodeRepository.findById(anode.getId()).get();
        // Disconnect from session so that the updates on updatedAnode are not directly saved in db
        em.detach(updatedAnode);
        updatedAnode
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        AnodeDTO anodeDTO = anodeMapper.toDto(updatedAnode);

        restAnodeMockMvc.perform(put("/api/anodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeDTO)))
            .andExpect(status().isOk());

        // Validate the Anode in the database
        List<Anode> anodeList = anodeRepository.findAll();
        assertThat(anodeList).hasSize(databaseSizeBeforeUpdate);
        Anode testAnode = anodeList.get(anodeList.size() - 1);
        assertThat(testAnode.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testAnode.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testAnode.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testAnode.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingAnode() throws Exception {
        int databaseSizeBeforeUpdate = anodeRepository.findAll().size();

        // Create the Anode
        AnodeDTO anodeDTO = anodeMapper.toDto(anode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnodeMockMvc.perform(put("/api/anodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Anode in the database
        List<Anode> anodeList = anodeRepository.findAll();
        assertThat(anodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnode() throws Exception {
        // Initialize the database
        anodeRepository.saveAndFlush(anode);

        int databaseSizeBeforeDelete = anodeRepository.findAll().size();

        // Delete the anode
        restAnodeMockMvc.perform(delete("/api/anodes/{id}", anode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Anode> anodeList = anodeRepository.findAll();
        assertThat(anodeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Anode.class);
        Anode anode1 = new Anode();
        anode1.setId(1L);
        Anode anode2 = new Anode();
        anode2.setId(anode1.getId());
        assertThat(anode1).isEqualTo(anode2);
        anode2.setId(2L);
        assertThat(anode1).isNotEqualTo(anode2);
        anode1.setId(null);
        assertThat(anode1).isNotEqualTo(anode2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnodeDTO.class);
        AnodeDTO anodeDTO1 = new AnodeDTO();
        anodeDTO1.setId(1L);
        AnodeDTO anodeDTO2 = new AnodeDTO();
        assertThat(anodeDTO1).isNotEqualTo(anodeDTO2);
        anodeDTO2.setId(anodeDTO1.getId());
        assertThat(anodeDTO1).isEqualTo(anodeDTO2);
        anodeDTO2.setId(2L);
        assertThat(anodeDTO1).isNotEqualTo(anodeDTO2);
        anodeDTO1.setId(null);
        assertThat(anodeDTO1).isNotEqualTo(anodeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(anodeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(anodeMapper.fromId(null)).isNull();
    }
}
