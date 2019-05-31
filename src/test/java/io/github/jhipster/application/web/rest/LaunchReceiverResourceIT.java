package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.LaunchReceiver;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.LaunchReceiverHist;
import io.github.jhipster.application.repository.LaunchReceiverRepository;
import io.github.jhipster.application.service.LaunchReceiverService;
import io.github.jhipster.application.service.dto.LaunchReceiverDTO;
import io.github.jhipster.application.service.mapper.LaunchReceiverMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.LaunchReceiverCriteria;
import io.github.jhipster.application.service.LaunchReceiverQueryService;

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
 * Integration tests for the {@Link LaunchReceiverResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class LaunchReceiverResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private LaunchReceiverRepository launchReceiverRepository;

    @Autowired
    private LaunchReceiverMapper launchReceiverMapper;

    @Autowired
    private LaunchReceiverService launchReceiverService;

    @Autowired
    private LaunchReceiverQueryService launchReceiverQueryService;

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

    private MockMvc restLaunchReceiverMockMvc;

    private LaunchReceiver launchReceiver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LaunchReceiverResource launchReceiverResource = new LaunchReceiverResource(launchReceiverService, launchReceiverQueryService);
        this.restLaunchReceiverMockMvc = MockMvcBuilders.standaloneSetup(launchReceiverResource)
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
    public static LaunchReceiver createEntity(EntityManager em) {
        LaunchReceiver launchReceiver = new LaunchReceiver()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return launchReceiver;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaunchReceiver createUpdatedEntity(EntityManager em) {
        LaunchReceiver launchReceiver = new LaunchReceiver()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return launchReceiver;
    }

    @BeforeEach
    public void initTest() {
        launchReceiver = createEntity(em);
    }

    @Test
    @Transactional
    public void createLaunchReceiver() throws Exception {
        int databaseSizeBeforeCreate = launchReceiverRepository.findAll().size();

        // Create the LaunchReceiver
        LaunchReceiverDTO launchReceiverDTO = launchReceiverMapper.toDto(launchReceiver);
        restLaunchReceiverMockMvc.perform(post("/api/launch-receivers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverDTO)))
            .andExpect(status().isCreated());

        // Validate the LaunchReceiver in the database
        List<LaunchReceiver> launchReceiverList = launchReceiverRepository.findAll();
        assertThat(launchReceiverList).hasSize(databaseSizeBeforeCreate + 1);
        LaunchReceiver testLaunchReceiver = launchReceiverList.get(launchReceiverList.size() - 1);
        assertThat(testLaunchReceiver.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testLaunchReceiver.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testLaunchReceiver.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testLaunchReceiver.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createLaunchReceiverWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = launchReceiverRepository.findAll().size();

        // Create the LaunchReceiver with an existing ID
        launchReceiver.setId(1L);
        LaunchReceiverDTO launchReceiverDTO = launchReceiverMapper.toDto(launchReceiver);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaunchReceiverMockMvc.perform(post("/api/launch-receivers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LaunchReceiver in the database
        List<LaunchReceiver> launchReceiverList = launchReceiverRepository.findAll();
        assertThat(launchReceiverList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLaunchReceivers() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList
        restLaunchReceiverMockMvc.perform(get("/api/launch-receivers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(launchReceiver.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getLaunchReceiver() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get the launchReceiver
        restLaunchReceiverMockMvc.perform(get("/api/launch-receivers/{id}", launchReceiver.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(launchReceiver.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultLaunchReceiverShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the launchReceiverList where dateCreate equals to UPDATED_DATE_CREATE
        defaultLaunchReceiverShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultLaunchReceiverShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the launchReceiverList where dateCreate equals to UPDATED_DATE_CREATE
        defaultLaunchReceiverShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where dateCreate is not null
        defaultLaunchReceiverShouldBeFound("dateCreate.specified=true");

        // Get all the launchReceiverList where dateCreate is null
        defaultLaunchReceiverShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultLaunchReceiverShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the launchReceiverList where dateEdit equals to UPDATED_DATE_EDIT
        defaultLaunchReceiverShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultLaunchReceiverShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the launchReceiverList where dateEdit equals to UPDATED_DATE_EDIT
        defaultLaunchReceiverShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where dateEdit is not null
        defaultLaunchReceiverShouldBeFound("dateEdit.specified=true");

        // Get all the launchReceiverList where dateEdit is null
        defaultLaunchReceiverShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where creator equals to DEFAULT_CREATOR
        defaultLaunchReceiverShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the launchReceiverList where creator equals to UPDATED_CREATOR
        defaultLaunchReceiverShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultLaunchReceiverShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the launchReceiverList where creator equals to UPDATED_CREATOR
        defaultLaunchReceiverShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where creator is not null
        defaultLaunchReceiverShouldBeFound("creator.specified=true");

        // Get all the launchReceiverList where creator is null
        defaultLaunchReceiverShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where editor equals to DEFAULT_EDITOR
        defaultLaunchReceiverShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the launchReceiverList where editor equals to UPDATED_EDITOR
        defaultLaunchReceiverShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultLaunchReceiverShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the launchReceiverList where editor equals to UPDATED_EDITOR
        defaultLaunchReceiverShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        // Get all the launchReceiverList where editor is not null
        defaultLaunchReceiverShouldBeFound("editor.specified=true");

        // Get all the launchReceiverList where editor is null
        defaultLaunchReceiverShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiversByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        launchReceiver.setBaseClass(baseClass);
        launchReceiverRepository.saveAndFlush(launchReceiver);
        Long baseClassId = baseClass.getId();

        // Get all the launchReceiverList where baseClass equals to baseClassId
        defaultLaunchReceiverShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the launchReceiverList where baseClass equals to baseClassId + 1
        defaultLaunchReceiverShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllLaunchReceiversByLaunchReceiverHistIsEqualToSomething() throws Exception {
        // Initialize the database
        LaunchReceiverHist launchReceiverHist = LaunchReceiverHistResourceIT.createEntity(em);
        em.persist(launchReceiverHist);
        em.flush();
        launchReceiver.setLaunchReceiverHist(launchReceiverHist);
        launchReceiverHist.setLaunchReceiver(launchReceiver);
        launchReceiverRepository.saveAndFlush(launchReceiver);
        Long launchReceiverHistId = launchReceiverHist.getId();

        // Get all the launchReceiverList where launchReceiverHist equals to launchReceiverHistId
        defaultLaunchReceiverShouldBeFound("launchReceiverHistId.equals=" + launchReceiverHistId);

        // Get all the launchReceiverList where launchReceiverHist equals to launchReceiverHistId + 1
        defaultLaunchReceiverShouldNotBeFound("launchReceiverHistId.equals=" + (launchReceiverHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLaunchReceiverShouldBeFound(String filter) throws Exception {
        restLaunchReceiverMockMvc.perform(get("/api/launch-receivers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(launchReceiver.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restLaunchReceiverMockMvc.perform(get("/api/launch-receivers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLaunchReceiverShouldNotBeFound(String filter) throws Exception {
        restLaunchReceiverMockMvc.perform(get("/api/launch-receivers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLaunchReceiverMockMvc.perform(get("/api/launch-receivers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLaunchReceiver() throws Exception {
        // Get the launchReceiver
        restLaunchReceiverMockMvc.perform(get("/api/launch-receivers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLaunchReceiver() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        int databaseSizeBeforeUpdate = launchReceiverRepository.findAll().size();

        // Update the launchReceiver
        LaunchReceiver updatedLaunchReceiver = launchReceiverRepository.findById(launchReceiver.getId()).get();
        // Disconnect from session so that the updates on updatedLaunchReceiver are not directly saved in db
        em.detach(updatedLaunchReceiver);
        updatedLaunchReceiver
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        LaunchReceiverDTO launchReceiverDTO = launchReceiverMapper.toDto(updatedLaunchReceiver);

        restLaunchReceiverMockMvc.perform(put("/api/launch-receivers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverDTO)))
            .andExpect(status().isOk());

        // Validate the LaunchReceiver in the database
        List<LaunchReceiver> launchReceiverList = launchReceiverRepository.findAll();
        assertThat(launchReceiverList).hasSize(databaseSizeBeforeUpdate);
        LaunchReceiver testLaunchReceiver = launchReceiverList.get(launchReceiverList.size() - 1);
        assertThat(testLaunchReceiver.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testLaunchReceiver.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testLaunchReceiver.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testLaunchReceiver.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingLaunchReceiver() throws Exception {
        int databaseSizeBeforeUpdate = launchReceiverRepository.findAll().size();

        // Create the LaunchReceiver
        LaunchReceiverDTO launchReceiverDTO = launchReceiverMapper.toDto(launchReceiver);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaunchReceiverMockMvc.perform(put("/api/launch-receivers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LaunchReceiver in the database
        List<LaunchReceiver> launchReceiverList = launchReceiverRepository.findAll();
        assertThat(launchReceiverList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLaunchReceiver() throws Exception {
        // Initialize the database
        launchReceiverRepository.saveAndFlush(launchReceiver);

        int databaseSizeBeforeDelete = launchReceiverRepository.findAll().size();

        // Delete the launchReceiver
        restLaunchReceiverMockMvc.perform(delete("/api/launch-receivers/{id}", launchReceiver.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<LaunchReceiver> launchReceiverList = launchReceiverRepository.findAll();
        assertThat(launchReceiverList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaunchReceiver.class);
        LaunchReceiver launchReceiver1 = new LaunchReceiver();
        launchReceiver1.setId(1L);
        LaunchReceiver launchReceiver2 = new LaunchReceiver();
        launchReceiver2.setId(launchReceiver1.getId());
        assertThat(launchReceiver1).isEqualTo(launchReceiver2);
        launchReceiver2.setId(2L);
        assertThat(launchReceiver1).isNotEqualTo(launchReceiver2);
        launchReceiver1.setId(null);
        assertThat(launchReceiver1).isNotEqualTo(launchReceiver2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaunchReceiverDTO.class);
        LaunchReceiverDTO launchReceiverDTO1 = new LaunchReceiverDTO();
        launchReceiverDTO1.setId(1L);
        LaunchReceiverDTO launchReceiverDTO2 = new LaunchReceiverDTO();
        assertThat(launchReceiverDTO1).isNotEqualTo(launchReceiverDTO2);
        launchReceiverDTO2.setId(launchReceiverDTO1.getId());
        assertThat(launchReceiverDTO1).isEqualTo(launchReceiverDTO2);
        launchReceiverDTO2.setId(2L);
        assertThat(launchReceiverDTO1).isNotEqualTo(launchReceiverDTO2);
        launchReceiverDTO1.setId(null);
        assertThat(launchReceiverDTO1).isNotEqualTo(launchReceiverDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(launchReceiverMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(launchReceiverMapper.fromId(null)).isNull();
    }
}
