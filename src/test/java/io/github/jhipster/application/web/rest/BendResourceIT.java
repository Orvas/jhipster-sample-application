package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Bend;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.repository.BendRepository;
import io.github.jhipster.application.service.BendService;
import io.github.jhipster.application.service.dto.BendDTO;
import io.github.jhipster.application.service.mapper.BendMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.BendCriteria;
import io.github.jhipster.application.service.BendQueryService;

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
 * Integration tests for the {@Link BendResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class BendResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private BendRepository bendRepository;

    @Autowired
    private BendMapper bendMapper;

    @Autowired
    private BendService bendService;

    @Autowired
    private BendQueryService bendQueryService;

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

    private MockMvc restBendMockMvc;

    private Bend bend;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BendResource bendResource = new BendResource(bendService, bendQueryService);
        this.restBendMockMvc = MockMvcBuilders.standaloneSetup(bendResource)
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
    public static Bend createEntity(EntityManager em) {
        Bend bend = new Bend()
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
        bend.setId(baseClass);
        return bend;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bend createUpdatedEntity(EntityManager em) {
        Bend bend = new Bend()
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
        bend.setId(baseClass);
        return bend;
    }

    @BeforeEach
    public void initTest() {
        bend = createEntity(em);
    }

    @Test
    @Transactional
    public void createBend() throws Exception {
        int databaseSizeBeforeCreate = bendRepository.findAll().size();

        // Create the Bend
        BendDTO bendDTO = bendMapper.toDto(bend);
        restBendMockMvc.perform(post("/api/bends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendDTO)))
            .andExpect(status().isCreated());

        // Validate the Bend in the database
        List<Bend> bendList = bendRepository.findAll();
        assertThat(bendList).hasSize(databaseSizeBeforeCreate + 1);
        Bend testBend = bendList.get(bendList.size() - 1);
        assertThat(testBend.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testBend.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testBend.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testBend.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createBendWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bendRepository.findAll().size();

        // Create the Bend with an existing ID
        bend.setId(1L);
        BendDTO bendDTO = bendMapper.toDto(bend);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBendMockMvc.perform(post("/api/bends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bend in the database
        List<Bend> bendList = bendRepository.findAll();
        assertThat(bendList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBends() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList
        restBendMockMvc.perform(get("/api/bends?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bend.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getBend() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get the bend
        restBendMockMvc.perform(get("/api/bends/{id}", bend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bend.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllBendsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultBendShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the bendList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBendShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBendsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultBendShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the bendList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBendShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBendsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where dateCreate is not null
        defaultBendShouldBeFound("dateCreate.specified=true");

        // Get all the bendList where dateCreate is null
        defaultBendShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultBendShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the bendList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBendShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBendsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultBendShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the bendList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBendShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBendsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where dateEdit is not null
        defaultBendShouldBeFound("dateEdit.specified=true");

        // Get all the bendList where dateEdit is null
        defaultBendShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where creator equals to DEFAULT_CREATOR
        defaultBendShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the bendList where creator equals to UPDATED_CREATOR
        defaultBendShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBendsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultBendShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the bendList where creator equals to UPDATED_CREATOR
        defaultBendShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBendsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where creator is not null
        defaultBendShouldBeFound("creator.specified=true");

        // Get all the bendList where creator is null
        defaultBendShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where editor equals to DEFAULT_EDITOR
        defaultBendShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the bendList where editor equals to UPDATED_EDITOR
        defaultBendShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBendsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultBendShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the bendList where editor equals to UPDATED_EDITOR
        defaultBendShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBendsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        // Get all the bendList where editor is not null
        defaultBendShouldBeFound("editor.specified=true");

        // Get all the bendList where editor is null
        defaultBendShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        BaseClass id = bend.getId();
        bendRepository.saveAndFlush(bend);
        Long idId = id.getId();

        // Get all the bendList where id equals to idId
        defaultBendShouldBeFound("idId.equals=" + idId);

        // Get all the bendList where id equals to idId + 1
        defaultBendShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllBendsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        bend.addBendHist(bendHist);
        bendRepository.saveAndFlush(bend);
        Long bendHistId = bendHist.getId();

        // Get all the bendList where bendHist equals to bendHistId
        defaultBendShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the bendList where bendHist equals to bendHistId + 1
        defaultBendShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBendShouldBeFound(String filter) throws Exception {
        restBendMockMvc.perform(get("/api/bends?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bend.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restBendMockMvc.perform(get("/api/bends/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBendShouldNotBeFound(String filter) throws Exception {
        restBendMockMvc.perform(get("/api/bends?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBendMockMvc.perform(get("/api/bends/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBend() throws Exception {
        // Get the bend
        restBendMockMvc.perform(get("/api/bends/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBend() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        int databaseSizeBeforeUpdate = bendRepository.findAll().size();

        // Update the bend
        Bend updatedBend = bendRepository.findById(bend.getId()).get();
        // Disconnect from session so that the updates on updatedBend are not directly saved in db
        em.detach(updatedBend);
        updatedBend
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        BendDTO bendDTO = bendMapper.toDto(updatedBend);

        restBendMockMvc.perform(put("/api/bends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendDTO)))
            .andExpect(status().isOk());

        // Validate the Bend in the database
        List<Bend> bendList = bendRepository.findAll();
        assertThat(bendList).hasSize(databaseSizeBeforeUpdate);
        Bend testBend = bendList.get(bendList.size() - 1);
        assertThat(testBend.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testBend.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testBend.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testBend.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingBend() throws Exception {
        int databaseSizeBeforeUpdate = bendRepository.findAll().size();

        // Create the Bend
        BendDTO bendDTO = bendMapper.toDto(bend);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBendMockMvc.perform(put("/api/bends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bend in the database
        List<Bend> bendList = bendRepository.findAll();
        assertThat(bendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBend() throws Exception {
        // Initialize the database
        bendRepository.saveAndFlush(bend);

        int databaseSizeBeforeDelete = bendRepository.findAll().size();

        // Delete the bend
        restBendMockMvc.perform(delete("/api/bends/{id}", bend.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Bend> bendList = bendRepository.findAll();
        assertThat(bendList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bend.class);
        Bend bend1 = new Bend();
        bend1.setId(1L);
        Bend bend2 = new Bend();
        bend2.setId(bend1.getId());
        assertThat(bend1).isEqualTo(bend2);
        bend2.setId(2L);
        assertThat(bend1).isNotEqualTo(bend2);
        bend1.setId(null);
        assertThat(bend1).isNotEqualTo(bend2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BendDTO.class);
        BendDTO bendDTO1 = new BendDTO();
        bendDTO1.setId(1L);
        BendDTO bendDTO2 = new BendDTO();
        assertThat(bendDTO1).isNotEqualTo(bendDTO2);
        bendDTO2.setId(bendDTO1.getId());
        assertThat(bendDTO1).isEqualTo(bendDTO2);
        bendDTO2.setId(2L);
        assertThat(bendDTO1).isNotEqualTo(bendDTO2);
        bendDTO1.setId(null);
        assertThat(bendDTO1).isNotEqualTo(bendDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bendMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bendMapper.fromId(null)).isNull();
    }
}
