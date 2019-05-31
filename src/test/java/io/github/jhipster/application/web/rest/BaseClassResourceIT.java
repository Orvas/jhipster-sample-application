package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.ListObjectType;
import io.github.jhipster.application.domain.Anode;
import io.github.jhipster.application.domain.Bend;
import io.github.jhipster.application.domain.BuckleArrestor;
import io.github.jhipster.application.domain.Cps;
import io.github.jhipster.application.domain.Displacement;
import io.github.jhipster.application.domain.FreeSpan;
import io.github.jhipster.application.domain.FreeSpanSupport;
import io.github.jhipster.application.domain.LaunchReceiver;
import io.github.jhipster.application.domain.Pipe;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.Pipeline;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.Tee;
import io.github.jhipster.application.domain.Valve;
import io.github.jhipster.application.repository.BaseClassRepository;
import io.github.jhipster.application.service.BaseClassService;
import io.github.jhipster.application.service.dto.BaseClassDTO;
import io.github.jhipster.application.service.mapper.BaseClassMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.BaseClassCriteria;
import io.github.jhipster.application.service.BaseClassQueryService;

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
 * Integration tests for the {@Link BaseClassResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class BaseClassResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private BaseClassRepository baseClassRepository;

    @Autowired
    private BaseClassMapper baseClassMapper;

    @Autowired
    private BaseClassService baseClassService;

    @Autowired
    private BaseClassQueryService baseClassQueryService;

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

    private MockMvc restBaseClassMockMvc;

    private BaseClass baseClass;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BaseClassResource baseClassResource = new BaseClassResource(baseClassService, baseClassQueryService);
        this.restBaseClassMockMvc = MockMvcBuilders.standaloneSetup(baseClassResource)
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
    public static BaseClass createEntity(EntityManager em) {
        BaseClass baseClass = new BaseClass()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        ListObjectType listObjectType;
        if (TestUtil.findAll(em, ListObjectType.class).isEmpty()) {
            listObjectType = ListObjectTypeResourceIT.createEntity(em);
            em.persist(listObjectType);
            em.flush();
        } else {
            listObjectType = TestUtil.findAll(em, ListObjectType.class).get(0);
        }
        baseClass.setIdType(listObjectType);
        return baseClass;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BaseClass createUpdatedEntity(EntityManager em) {
        BaseClass baseClass = new BaseClass()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        ListObjectType listObjectType;
        if (TestUtil.findAll(em, ListObjectType.class).isEmpty()) {
            listObjectType = ListObjectTypeResourceIT.createUpdatedEntity(em);
            em.persist(listObjectType);
            em.flush();
        } else {
            listObjectType = TestUtil.findAll(em, ListObjectType.class).get(0);
        }
        baseClass.setIdType(listObjectType);
        return baseClass;
    }

    @BeforeEach
    public void initTest() {
        baseClass = createEntity(em);
    }

    @Test
    @Transactional
    public void createBaseClass() throws Exception {
        int databaseSizeBeforeCreate = baseClassRepository.findAll().size();

        // Create the BaseClass
        BaseClassDTO baseClassDTO = baseClassMapper.toDto(baseClass);
        restBaseClassMockMvc.perform(post("/api/base-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baseClassDTO)))
            .andExpect(status().isCreated());

        // Validate the BaseClass in the database
        List<BaseClass> baseClassList = baseClassRepository.findAll();
        assertThat(baseClassList).hasSize(databaseSizeBeforeCreate + 1);
        BaseClass testBaseClass = baseClassList.get(baseClassList.size() - 1);
        assertThat(testBaseClass.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testBaseClass.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testBaseClass.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testBaseClass.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createBaseClassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = baseClassRepository.findAll().size();

        // Create the BaseClass with an existing ID
        baseClass.setId(1L);
        BaseClassDTO baseClassDTO = baseClassMapper.toDto(baseClass);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBaseClassMockMvc.perform(post("/api/base-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baseClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BaseClass in the database
        List<BaseClass> baseClassList = baseClassRepository.findAll();
        assertThat(baseClassList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBaseClasses() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList
        restBaseClassMockMvc.perform(get("/api/base-classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baseClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getBaseClass() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get the baseClass
        restBaseClassMockMvc.perform(get("/api/base-classes/{id}", baseClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(baseClass.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllBaseClassesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultBaseClassShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the baseClassList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBaseClassShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultBaseClassShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the baseClassList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBaseClassShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where dateCreate is not null
        defaultBaseClassShouldBeFound("dateCreate.specified=true");

        // Get all the baseClassList where dateCreate is null
        defaultBaseClassShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBaseClassesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultBaseClassShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the baseClassList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBaseClassShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultBaseClassShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the baseClassList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBaseClassShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where dateEdit is not null
        defaultBaseClassShouldBeFound("dateEdit.specified=true");

        // Get all the baseClassList where dateEdit is null
        defaultBaseClassShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllBaseClassesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where creator equals to DEFAULT_CREATOR
        defaultBaseClassShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the baseClassList where creator equals to UPDATED_CREATOR
        defaultBaseClassShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultBaseClassShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the baseClassList where creator equals to UPDATED_CREATOR
        defaultBaseClassShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where creator is not null
        defaultBaseClassShouldBeFound("creator.specified=true");

        // Get all the baseClassList where creator is null
        defaultBaseClassShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllBaseClassesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where editor equals to DEFAULT_EDITOR
        defaultBaseClassShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the baseClassList where editor equals to UPDATED_EDITOR
        defaultBaseClassShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultBaseClassShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the baseClassList where editor equals to UPDATED_EDITOR
        defaultBaseClassShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBaseClassesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        // Get all the baseClassList where editor is not null
        defaultBaseClassShouldBeFound("editor.specified=true");

        // Get all the baseClassList where editor is null
        defaultBaseClassShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllBaseClassesByIdTypeIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectType idType = baseClass.getIdType();
        baseClassRepository.saveAndFlush(baseClass);
        Long idTypeId = idType.getId();

        // Get all the baseClassList where idType equals to idTypeId
        defaultBaseClassShouldBeFound("idTypeId.equals=" + idTypeId);

        // Get all the baseClassList where idType equals to idTypeId + 1
        defaultBaseClassShouldNotBeFound("idTypeId.equals=" + (idTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByAnodeIsEqualToSomething() throws Exception {
        // Initialize the database
        Anode anode = AnodeResourceIT.createEntity(em);
        em.persist(anode);
        em.flush();
        baseClass.setAnode(anode);
        anode.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long anodeId = anode.getId();

        // Get all the baseClassList where anode equals to anodeId
        defaultBaseClassShouldBeFound("anodeId.equals=" + anodeId);

        // Get all the baseClassList where anode equals to anodeId + 1
        defaultBaseClassShouldNotBeFound("anodeId.equals=" + (anodeId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByBendIsEqualToSomething() throws Exception {
        // Initialize the database
        Bend bend = BendResourceIT.createEntity(em);
        em.persist(bend);
        em.flush();
        baseClass.setBend(bend);
        bend.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long bendId = bend.getId();

        // Get all the baseClassList where bend equals to bendId
        defaultBaseClassShouldBeFound("bendId.equals=" + bendId);

        // Get all the baseClassList where bend equals to bendId + 1
        defaultBaseClassShouldNotBeFound("bendId.equals=" + (bendId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByBuckleArrestorIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestor buckleArrestor = BuckleArrestorResourceIT.createEntity(em);
        em.persist(buckleArrestor);
        em.flush();
        baseClass.setBuckleArrestor(buckleArrestor);
        buckleArrestor.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long buckleArrestorId = buckleArrestor.getId();

        // Get all the baseClassList where buckleArrestor equals to buckleArrestorId
        defaultBaseClassShouldBeFound("buckleArrestorId.equals=" + buckleArrestorId);

        // Get all the baseClassList where buckleArrestor equals to buckleArrestorId + 1
        defaultBaseClassShouldNotBeFound("buckleArrestorId.equals=" + (buckleArrestorId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByCpsIsEqualToSomething() throws Exception {
        // Initialize the database
        Cps cps = CpsResourceIT.createEntity(em);
        em.persist(cps);
        em.flush();
        baseClass.setCps(cps);
        cps.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long cpsId = cps.getId();

        // Get all the baseClassList where cps equals to cpsId
        defaultBaseClassShouldBeFound("cpsId.equals=" + cpsId);

        // Get all the baseClassList where cps equals to cpsId + 1
        defaultBaseClassShouldNotBeFound("cpsId.equals=" + (cpsId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByDisplacementIsEqualToSomething() throws Exception {
        // Initialize the database
        Displacement displacement = DisplacementResourceIT.createEntity(em);
        em.persist(displacement);
        em.flush();
        baseClass.setDisplacement(displacement);
        displacement.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long displacementId = displacement.getId();

        // Get all the baseClassList where displacement equals to displacementId
        defaultBaseClassShouldBeFound("displacementId.equals=" + displacementId);

        // Get all the baseClassList where displacement equals to displacementId + 1
        defaultBaseClassShouldNotBeFound("displacementId.equals=" + (displacementId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByFreeSpanIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpan freeSpan = FreeSpanResourceIT.createEntity(em);
        em.persist(freeSpan);
        em.flush();
        baseClass.setFreeSpan(freeSpan);
        freeSpan.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long freeSpanId = freeSpan.getId();

        // Get all the baseClassList where freeSpan equals to freeSpanId
        defaultBaseClassShouldBeFound("freeSpanId.equals=" + freeSpanId);

        // Get all the baseClassList where freeSpan equals to freeSpanId + 1
        defaultBaseClassShouldNotBeFound("freeSpanId.equals=" + (freeSpanId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByFreeSpanSupportIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpanSupport freeSpanSupport = FreeSpanSupportResourceIT.createEntity(em);
        em.persist(freeSpanSupport);
        em.flush();
        baseClass.setFreeSpanSupport(freeSpanSupport);
        freeSpanSupport.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long freeSpanSupportId = freeSpanSupport.getId();

        // Get all the baseClassList where freeSpanSupport equals to freeSpanSupportId
        defaultBaseClassShouldBeFound("freeSpanSupportId.equals=" + freeSpanSupportId);

        // Get all the baseClassList where freeSpanSupport equals to freeSpanSupportId + 1
        defaultBaseClassShouldNotBeFound("freeSpanSupportId.equals=" + (freeSpanSupportId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByLaunchReceiverIsEqualToSomething() throws Exception {
        // Initialize the database
        LaunchReceiver launchReceiver = LaunchReceiverResourceIT.createEntity(em);
        em.persist(launchReceiver);
        em.flush();
        baseClass.setLaunchReceiver(launchReceiver);
        launchReceiver.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long launchReceiverId = launchReceiver.getId();

        // Get all the baseClassList where launchReceiver equals to launchReceiverId
        defaultBaseClassShouldBeFound("launchReceiverId.equals=" + launchReceiverId);

        // Get all the baseClassList where launchReceiver equals to launchReceiverId + 1
        defaultBaseClassShouldNotBeFound("launchReceiverId.equals=" + (launchReceiverId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByPipeIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipe pipe = PipeResourceIT.createEntity(em);
        em.persist(pipe);
        em.flush();
        baseClass.setPipe(pipe);
        pipe.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long pipeId = pipe.getId();

        // Get all the baseClassList where pipe equals to pipeId
        defaultBaseClassShouldBeFound("pipeId.equals=" + pipeId);

        // Get all the baseClassList where pipe equals to pipeId + 1
        defaultBaseClassShouldNotBeFound("pipeId.equals=" + (pipeId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByPipejointIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipejoint pipejoint = PipejointResourceIT.createEntity(em);
        em.persist(pipejoint);
        em.flush();
        baseClass.setPipejoint(pipejoint);
        pipejoint.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long pipejointId = pipejoint.getId();

        // Get all the baseClassList where pipejoint equals to pipejointId
        defaultBaseClassShouldBeFound("pipejointId.equals=" + pipejointId);

        // Get all the baseClassList where pipejoint equals to pipejointId + 1
        defaultBaseClassShouldNotBeFound("pipejointId.equals=" + (pipejointId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByPipelineIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipeline pipeline = PipelineResourceIT.createEntity(em);
        em.persist(pipeline);
        em.flush();
        baseClass.setPipeline(pipeline);
        pipeline.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long pipelineId = pipeline.getId();

        // Get all the baseClassList where pipeline equals to pipelineId
        defaultBaseClassShouldBeFound("pipelineId.equals=" + pipelineId);

        // Get all the baseClassList where pipeline equals to pipelineId + 1
        defaultBaseClassShouldNotBeFound("pipelineId.equals=" + (pipelineId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByPipelineSectionIsEqualToSomething() throws Exception {
        // Initialize the database
        PipelineSection pipelineSection = PipelineSectionResourceIT.createEntity(em);
        em.persist(pipelineSection);
        em.flush();
        baseClass.setPipelineSection(pipelineSection);
        pipelineSection.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long pipelineSectionId = pipelineSection.getId();

        // Get all the baseClassList where pipelineSection equals to pipelineSectionId
        defaultBaseClassShouldBeFound("pipelineSectionId.equals=" + pipelineSectionId);

        // Get all the baseClassList where pipelineSection equals to pipelineSectionId + 1
        defaultBaseClassShouldNotBeFound("pipelineSectionId.equals=" + (pipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByTeeIsEqualToSomething() throws Exception {
        // Initialize the database
        Tee tee = TeeResourceIT.createEntity(em);
        em.persist(tee);
        em.flush();
        baseClass.setTee(tee);
        tee.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long teeId = tee.getId();

        // Get all the baseClassList where tee equals to teeId
        defaultBaseClassShouldBeFound("teeId.equals=" + teeId);

        // Get all the baseClassList where tee equals to teeId + 1
        defaultBaseClassShouldNotBeFound("teeId.equals=" + (teeId + 1));
    }


    @Test
    @Transactional
    public void getAllBaseClassesByValveIsEqualToSomething() throws Exception {
        // Initialize the database
        Valve valve = ValveResourceIT.createEntity(em);
        em.persist(valve);
        em.flush();
        baseClass.setValve(valve);
        valve.setBaseClass(baseClass);
        baseClassRepository.saveAndFlush(baseClass);
        Long valveId = valve.getId();

        // Get all the baseClassList where valve equals to valveId
        defaultBaseClassShouldBeFound("valveId.equals=" + valveId);

        // Get all the baseClassList where valve equals to valveId + 1
        defaultBaseClassShouldNotBeFound("valveId.equals=" + (valveId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBaseClassShouldBeFound(String filter) throws Exception {
        restBaseClassMockMvc.perform(get("/api/base-classes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baseClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restBaseClassMockMvc.perform(get("/api/base-classes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBaseClassShouldNotBeFound(String filter) throws Exception {
        restBaseClassMockMvc.perform(get("/api/base-classes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBaseClassMockMvc.perform(get("/api/base-classes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBaseClass() throws Exception {
        // Get the baseClass
        restBaseClassMockMvc.perform(get("/api/base-classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBaseClass() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        int databaseSizeBeforeUpdate = baseClassRepository.findAll().size();

        // Update the baseClass
        BaseClass updatedBaseClass = baseClassRepository.findById(baseClass.getId()).get();
        // Disconnect from session so that the updates on updatedBaseClass are not directly saved in db
        em.detach(updatedBaseClass);
        updatedBaseClass
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        BaseClassDTO baseClassDTO = baseClassMapper.toDto(updatedBaseClass);

        restBaseClassMockMvc.perform(put("/api/base-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baseClassDTO)))
            .andExpect(status().isOk());

        // Validate the BaseClass in the database
        List<BaseClass> baseClassList = baseClassRepository.findAll();
        assertThat(baseClassList).hasSize(databaseSizeBeforeUpdate);
        BaseClass testBaseClass = baseClassList.get(baseClassList.size() - 1);
        assertThat(testBaseClass.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testBaseClass.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testBaseClass.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testBaseClass.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingBaseClass() throws Exception {
        int databaseSizeBeforeUpdate = baseClassRepository.findAll().size();

        // Create the BaseClass
        BaseClassDTO baseClassDTO = baseClassMapper.toDto(baseClass);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBaseClassMockMvc.perform(put("/api/base-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baseClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BaseClass in the database
        List<BaseClass> baseClassList = baseClassRepository.findAll();
        assertThat(baseClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBaseClass() throws Exception {
        // Initialize the database
        baseClassRepository.saveAndFlush(baseClass);

        int databaseSizeBeforeDelete = baseClassRepository.findAll().size();

        // Delete the baseClass
        restBaseClassMockMvc.perform(delete("/api/base-classes/{id}", baseClass.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<BaseClass> baseClassList = baseClassRepository.findAll();
        assertThat(baseClassList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaseClass.class);
        BaseClass baseClass1 = new BaseClass();
        baseClass1.setId(1L);
        BaseClass baseClass2 = new BaseClass();
        baseClass2.setId(baseClass1.getId());
        assertThat(baseClass1).isEqualTo(baseClass2);
        baseClass2.setId(2L);
        assertThat(baseClass1).isNotEqualTo(baseClass2);
        baseClass1.setId(null);
        assertThat(baseClass1).isNotEqualTo(baseClass2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaseClassDTO.class);
        BaseClassDTO baseClassDTO1 = new BaseClassDTO();
        baseClassDTO1.setId(1L);
        BaseClassDTO baseClassDTO2 = new BaseClassDTO();
        assertThat(baseClassDTO1).isNotEqualTo(baseClassDTO2);
        baseClassDTO2.setId(baseClassDTO1.getId());
        assertThat(baseClassDTO1).isEqualTo(baseClassDTO2);
        baseClassDTO2.setId(2L);
        assertThat(baseClassDTO1).isNotEqualTo(baseClassDTO2);
        baseClassDTO1.setId(null);
        assertThat(baseClassDTO1).isNotEqualTo(baseClassDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(baseClassMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(baseClassMapper.fromId(null)).isNull();
    }
}
