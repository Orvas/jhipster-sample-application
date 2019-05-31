package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Pipe;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.repository.PipeRepository;
import io.github.jhipster.application.service.PipeService;
import io.github.jhipster.application.service.dto.PipeDTO;
import io.github.jhipster.application.service.mapper.PipeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipeCriteria;
import io.github.jhipster.application.service.PipeQueryService;

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
 * Integration tests for the {@Link PipeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipeResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private PipeRepository pipeRepository;

    @Autowired
    private PipeMapper pipeMapper;

    @Autowired
    private PipeService pipeService;

    @Autowired
    private PipeQueryService pipeQueryService;

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

    private MockMvc restPipeMockMvc;

    private Pipe pipe;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipeResource pipeResource = new PipeResource(pipeService, pipeQueryService);
        this.restPipeMockMvc = MockMvcBuilders.standaloneSetup(pipeResource)
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
    public static Pipe createEntity(EntityManager em) {
        Pipe pipe = new Pipe()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return pipe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pipe createUpdatedEntity(EntityManager em) {
        Pipe pipe = new Pipe()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return pipe;
    }

    @BeforeEach
    public void initTest() {
        pipe = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipe() throws Exception {
        int databaseSizeBeforeCreate = pipeRepository.findAll().size();

        // Create the Pipe
        PipeDTO pipeDTO = pipeMapper.toDto(pipe);
        restPipeMockMvc.perform(post("/api/pipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeDTO)))
            .andExpect(status().isCreated());

        // Validate the Pipe in the database
        List<Pipe> pipeList = pipeRepository.findAll();
        assertThat(pipeList).hasSize(databaseSizeBeforeCreate + 1);
        Pipe testPipe = pipeList.get(pipeList.size() - 1);
        assertThat(testPipe.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipe.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipe.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipe.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipeRepository.findAll().size();

        // Create the Pipe with an existing ID
        pipe.setId(1L);
        PipeDTO pipeDTO = pipeMapper.toDto(pipe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipeMockMvc.perform(post("/api/pipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pipe in the database
        List<Pipe> pipeList = pipeRepository.findAll();
        assertThat(pipeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPipes() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList
        restPipeMockMvc.perform(get("/api/pipes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipe.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getPipe() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get the pipe
        restPipeMockMvc.perform(get("/api/pipes/{id}", pipe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipe.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllPipesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where dateCreate is not null
        defaultPipeShouldBeFound("dateCreate.specified=true");

        // Get all the pipeList where dateCreate is null
        defaultPipeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where dateEdit is not null
        defaultPipeShouldBeFound("dateEdit.specified=true");

        // Get all the pipeList where dateEdit is null
        defaultPipeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where creator equals to DEFAULT_CREATOR
        defaultPipeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipeList where creator equals to UPDATED_CREATOR
        defaultPipeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipeList where creator equals to UPDATED_CREATOR
        defaultPipeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where creator is not null
        defaultPipeShouldBeFound("creator.specified=true");

        // Get all the pipeList where creator is null
        defaultPipeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where editor equals to DEFAULT_EDITOR
        defaultPipeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipeList where editor equals to UPDATED_EDITOR
        defaultPipeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipeList where editor equals to UPDATED_EDITOR
        defaultPipeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        // Get all the pipeList where editor is not null
        defaultPipeShouldBeFound("editor.specified=true");

        // Get all the pipeList where editor is null
        defaultPipeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipesByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        pipe.setBaseClass(baseClass);
        pipeRepository.saveAndFlush(pipe);
        Long baseClassId = baseClass.getId();

        // Get all the pipeList where baseClass equals to baseClassId
        defaultPipeShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the pipeList where baseClass equals to baseClassId + 1
        defaultPipeShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllPipesByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        pipe.setPipeHist(pipeHist);
        pipeHist.setPipe(pipe);
        pipeRepository.saveAndFlush(pipe);
        Long pipeHistId = pipeHist.getId();

        // Get all the pipeList where pipeHist equals to pipeHistId
        defaultPipeShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the pipeList where pipeHist equals to pipeHistId + 1
        defaultPipeShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipeShouldBeFound(String filter) throws Exception {
        restPipeMockMvc.perform(get("/api/pipes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipe.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipeMockMvc.perform(get("/api/pipes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipeShouldNotBeFound(String filter) throws Exception {
        restPipeMockMvc.perform(get("/api/pipes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipeMockMvc.perform(get("/api/pipes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPipe() throws Exception {
        // Get the pipe
        restPipeMockMvc.perform(get("/api/pipes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipe() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        int databaseSizeBeforeUpdate = pipeRepository.findAll().size();

        // Update the pipe
        Pipe updatedPipe = pipeRepository.findById(pipe.getId()).get();
        // Disconnect from session so that the updates on updatedPipe are not directly saved in db
        em.detach(updatedPipe);
        updatedPipe
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipeDTO pipeDTO = pipeMapper.toDto(updatedPipe);

        restPipeMockMvc.perform(put("/api/pipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeDTO)))
            .andExpect(status().isOk());

        // Validate the Pipe in the database
        List<Pipe> pipeList = pipeRepository.findAll();
        assertThat(pipeList).hasSize(databaseSizeBeforeUpdate);
        Pipe testPipe = pipeList.get(pipeList.size() - 1);
        assertThat(testPipe.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipe.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipe.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipe.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipe() throws Exception {
        int databaseSizeBeforeUpdate = pipeRepository.findAll().size();

        // Create the Pipe
        PipeDTO pipeDTO = pipeMapper.toDto(pipe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipeMockMvc.perform(put("/api/pipes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pipe in the database
        List<Pipe> pipeList = pipeRepository.findAll();
        assertThat(pipeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipe() throws Exception {
        // Initialize the database
        pipeRepository.saveAndFlush(pipe);

        int databaseSizeBeforeDelete = pipeRepository.findAll().size();

        // Delete the pipe
        restPipeMockMvc.perform(delete("/api/pipes/{id}", pipe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Pipe> pipeList = pipeRepository.findAll();
        assertThat(pipeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pipe.class);
        Pipe pipe1 = new Pipe();
        pipe1.setId(1L);
        Pipe pipe2 = new Pipe();
        pipe2.setId(pipe1.getId());
        assertThat(pipe1).isEqualTo(pipe2);
        pipe2.setId(2L);
        assertThat(pipe1).isNotEqualTo(pipe2);
        pipe1.setId(null);
        assertThat(pipe1).isNotEqualTo(pipe2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipeDTO.class);
        PipeDTO pipeDTO1 = new PipeDTO();
        pipeDTO1.setId(1L);
        PipeDTO pipeDTO2 = new PipeDTO();
        assertThat(pipeDTO1).isNotEqualTo(pipeDTO2);
        pipeDTO2.setId(pipeDTO1.getId());
        assertThat(pipeDTO1).isEqualTo(pipeDTO2);
        pipeDTO2.setId(2L);
        assertThat(pipeDTO1).isNotEqualTo(pipeDTO2);
        pipeDTO1.setId(null);
        assertThat(pipeDTO1).isNotEqualTo(pipeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipeMapper.fromId(null)).isNull();
    }
}
