package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Displacement;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.DisplacementHist;
import io.github.jhipster.application.repository.DisplacementRepository;
import io.github.jhipster.application.service.DisplacementService;
import io.github.jhipster.application.service.dto.DisplacementDTO;
import io.github.jhipster.application.service.mapper.DisplacementMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.DisplacementCriteria;
import io.github.jhipster.application.service.DisplacementQueryService;

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
 * Integration tests for the {@Link DisplacementResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class DisplacementResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private DisplacementRepository displacementRepository;

    @Autowired
    private DisplacementMapper displacementMapper;

    @Autowired
    private DisplacementService displacementService;

    @Autowired
    private DisplacementQueryService displacementQueryService;

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

    private MockMvc restDisplacementMockMvc;

    private Displacement displacement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisplacementResource displacementResource = new DisplacementResource(displacementService, displacementQueryService);
        this.restDisplacementMockMvc = MockMvcBuilders.standaloneSetup(displacementResource)
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
    public static Displacement createEntity(EntityManager em) {
        Displacement displacement = new Displacement()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return displacement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Displacement createUpdatedEntity(EntityManager em) {
        Displacement displacement = new Displacement()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return displacement;
    }

    @BeforeEach
    public void initTest() {
        displacement = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisplacement() throws Exception {
        int databaseSizeBeforeCreate = displacementRepository.findAll().size();

        // Create the Displacement
        DisplacementDTO displacementDTO = displacementMapper.toDto(displacement);
        restDisplacementMockMvc.perform(post("/api/displacements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementDTO)))
            .andExpect(status().isCreated());

        // Validate the Displacement in the database
        List<Displacement> displacementList = displacementRepository.findAll();
        assertThat(displacementList).hasSize(databaseSizeBeforeCreate + 1);
        Displacement testDisplacement = displacementList.get(displacementList.size() - 1);
        assertThat(testDisplacement.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testDisplacement.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testDisplacement.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testDisplacement.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createDisplacementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = displacementRepository.findAll().size();

        // Create the Displacement with an existing ID
        displacement.setId(1L);
        DisplacementDTO displacementDTO = displacementMapper.toDto(displacement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisplacementMockMvc.perform(post("/api/displacements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Displacement in the database
        List<Displacement> displacementList = displacementRepository.findAll();
        assertThat(displacementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDisplacements() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList
        restDisplacementMockMvc.perform(get("/api/displacements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(displacement.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getDisplacement() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get the displacement
        restDisplacementMockMvc.perform(get("/api/displacements/{id}", displacement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(displacement.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllDisplacementsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultDisplacementShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the displacementList where dateCreate equals to UPDATED_DATE_CREATE
        defaultDisplacementShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultDisplacementShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the displacementList where dateCreate equals to UPDATED_DATE_CREATE
        defaultDisplacementShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where dateCreate is not null
        defaultDisplacementShouldBeFound("dateCreate.specified=true");

        // Get all the displacementList where dateCreate is null
        defaultDisplacementShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultDisplacementShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the displacementList where dateEdit equals to UPDATED_DATE_EDIT
        defaultDisplacementShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultDisplacementShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the displacementList where dateEdit equals to UPDATED_DATE_EDIT
        defaultDisplacementShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where dateEdit is not null
        defaultDisplacementShouldBeFound("dateEdit.specified=true");

        // Get all the displacementList where dateEdit is null
        defaultDisplacementShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where creator equals to DEFAULT_CREATOR
        defaultDisplacementShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the displacementList where creator equals to UPDATED_CREATOR
        defaultDisplacementShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultDisplacementShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the displacementList where creator equals to UPDATED_CREATOR
        defaultDisplacementShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where creator is not null
        defaultDisplacementShouldBeFound("creator.specified=true");

        // Get all the displacementList where creator is null
        defaultDisplacementShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where editor equals to DEFAULT_EDITOR
        defaultDisplacementShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the displacementList where editor equals to UPDATED_EDITOR
        defaultDisplacementShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultDisplacementShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the displacementList where editor equals to UPDATED_EDITOR
        defaultDisplacementShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        // Get all the displacementList where editor is not null
        defaultDisplacementShouldBeFound("editor.specified=true");

        // Get all the displacementList where editor is null
        defaultDisplacementShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementsByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        displacement.setBaseClass(baseClass);
        displacementRepository.saveAndFlush(displacement);
        Long baseClassId = baseClass.getId();

        // Get all the displacementList where baseClass equals to baseClassId
        defaultDisplacementShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the displacementList where baseClass equals to baseClassId + 1
        defaultDisplacementShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllDisplacementsByDisplacementHistIsEqualToSomething() throws Exception {
        // Initialize the database
        DisplacementHist displacementHist = DisplacementHistResourceIT.createEntity(em);
        em.persist(displacementHist);
        em.flush();
        displacement.setDisplacementHist(displacementHist);
        displacementHist.setDisplacement(displacement);
        displacementRepository.saveAndFlush(displacement);
        Long displacementHistId = displacementHist.getId();

        // Get all the displacementList where displacementHist equals to displacementHistId
        defaultDisplacementShouldBeFound("displacementHistId.equals=" + displacementHistId);

        // Get all the displacementList where displacementHist equals to displacementHistId + 1
        defaultDisplacementShouldNotBeFound("displacementHistId.equals=" + (displacementHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDisplacementShouldBeFound(String filter) throws Exception {
        restDisplacementMockMvc.perform(get("/api/displacements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(displacement.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restDisplacementMockMvc.perform(get("/api/displacements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDisplacementShouldNotBeFound(String filter) throws Exception {
        restDisplacementMockMvc.perform(get("/api/displacements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDisplacementMockMvc.perform(get("/api/displacements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDisplacement() throws Exception {
        // Get the displacement
        restDisplacementMockMvc.perform(get("/api/displacements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisplacement() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        int databaseSizeBeforeUpdate = displacementRepository.findAll().size();

        // Update the displacement
        Displacement updatedDisplacement = displacementRepository.findById(displacement.getId()).get();
        // Disconnect from session so that the updates on updatedDisplacement are not directly saved in db
        em.detach(updatedDisplacement);
        updatedDisplacement
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        DisplacementDTO displacementDTO = displacementMapper.toDto(updatedDisplacement);

        restDisplacementMockMvc.perform(put("/api/displacements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementDTO)))
            .andExpect(status().isOk());

        // Validate the Displacement in the database
        List<Displacement> displacementList = displacementRepository.findAll();
        assertThat(displacementList).hasSize(databaseSizeBeforeUpdate);
        Displacement testDisplacement = displacementList.get(displacementList.size() - 1);
        assertThat(testDisplacement.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testDisplacement.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testDisplacement.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testDisplacement.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingDisplacement() throws Exception {
        int databaseSizeBeforeUpdate = displacementRepository.findAll().size();

        // Create the Displacement
        DisplacementDTO displacementDTO = displacementMapper.toDto(displacement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisplacementMockMvc.perform(put("/api/displacements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Displacement in the database
        List<Displacement> displacementList = displacementRepository.findAll();
        assertThat(displacementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisplacement() throws Exception {
        // Initialize the database
        displacementRepository.saveAndFlush(displacement);

        int databaseSizeBeforeDelete = displacementRepository.findAll().size();

        // Delete the displacement
        restDisplacementMockMvc.perform(delete("/api/displacements/{id}", displacement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Displacement> displacementList = displacementRepository.findAll();
        assertThat(displacementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Displacement.class);
        Displacement displacement1 = new Displacement();
        displacement1.setId(1L);
        Displacement displacement2 = new Displacement();
        displacement2.setId(displacement1.getId());
        assertThat(displacement1).isEqualTo(displacement2);
        displacement2.setId(2L);
        assertThat(displacement1).isNotEqualTo(displacement2);
        displacement1.setId(null);
        assertThat(displacement1).isNotEqualTo(displacement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisplacementDTO.class);
        DisplacementDTO displacementDTO1 = new DisplacementDTO();
        displacementDTO1.setId(1L);
        DisplacementDTO displacementDTO2 = new DisplacementDTO();
        assertThat(displacementDTO1).isNotEqualTo(displacementDTO2);
        displacementDTO2.setId(displacementDTO1.getId());
        assertThat(displacementDTO1).isEqualTo(displacementDTO2);
        displacementDTO2.setId(2L);
        assertThat(displacementDTO1).isNotEqualTo(displacementDTO2);
        displacementDTO1.setId(null);
        assertThat(displacementDTO1).isNotEqualTo(displacementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(displacementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(displacementMapper.fromId(null)).isNull();
    }
}
