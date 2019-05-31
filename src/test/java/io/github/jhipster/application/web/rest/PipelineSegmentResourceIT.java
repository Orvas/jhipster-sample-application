package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.PipelineSegment;
import io.github.jhipster.application.repository.PipelineSegmentRepository;
import io.github.jhipster.application.service.PipelineSegmentService;
import io.github.jhipster.application.service.dto.PipelineSegmentDTO;
import io.github.jhipster.application.service.mapper.PipelineSegmentMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipelineSegmentCriteria;
import io.github.jhipster.application.service.PipelineSegmentQueryService;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link PipelineSegmentResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipelineSegmentResourceIT {

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_KP_START_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_START_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END_4 = new BigDecimal(2);

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private PipelineSegmentRepository pipelineSegmentRepository;

    @Autowired
    private PipelineSegmentMapper pipelineSegmentMapper;

    @Autowired
    private PipelineSegmentService pipelineSegmentService;

    @Autowired
    private PipelineSegmentQueryService pipelineSegmentQueryService;

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

    private MockMvc restPipelineSegmentMockMvc;

    private PipelineSegment pipelineSegment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipelineSegmentResource pipelineSegmentResource = new PipelineSegmentResource(pipelineSegmentService, pipelineSegmentQueryService);
        this.restPipelineSegmentMockMvc = MockMvcBuilders.standaloneSetup(pipelineSegmentResource)
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
    public static PipelineSegment createEntity(EntityManager em) {
        PipelineSegment pipelineSegment = new PipelineSegment()
            .num(DEFAULT_NUM)
            .name(DEFAULT_NAME)
            .kpStart1(DEFAULT_KP_START_1)
            .kpEnd1(DEFAULT_KP_END_1)
            .kpStart4(DEFAULT_KP_START_4)
            .kpEnd4(DEFAULT_KP_END_4)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return pipelineSegment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PipelineSegment createUpdatedEntity(EntityManager em) {
        PipelineSegment pipelineSegment = new PipelineSegment()
            .num(UPDATED_NUM)
            .name(UPDATED_NAME)
            .kpStart1(UPDATED_KP_START_1)
            .kpEnd1(UPDATED_KP_END_1)
            .kpStart4(UPDATED_KP_START_4)
            .kpEnd4(UPDATED_KP_END_4)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return pipelineSegment;
    }

    @BeforeEach
    public void initTest() {
        pipelineSegment = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipelineSegment() throws Exception {
        int databaseSizeBeforeCreate = pipelineSegmentRepository.findAll().size();

        // Create the PipelineSegment
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);
        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isCreated());

        // Validate the PipelineSegment in the database
        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeCreate + 1);
        PipelineSegment testPipelineSegment = pipelineSegmentList.get(pipelineSegmentList.size() - 1);
        assertThat(testPipelineSegment.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testPipelineSegment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPipelineSegment.getKpStart1()).isEqualTo(DEFAULT_KP_START_1);
        assertThat(testPipelineSegment.getKpEnd1()).isEqualTo(DEFAULT_KP_END_1);
        assertThat(testPipelineSegment.getKpStart4()).isEqualTo(DEFAULT_KP_START_4);
        assertThat(testPipelineSegment.getKpEnd4()).isEqualTo(DEFAULT_KP_END_4);
        assertThat(testPipelineSegment.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipelineSegment.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipelineSegment.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipelineSegment.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipelineSegmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipelineSegmentRepository.findAll().size();

        // Create the PipelineSegment with an existing ID
        pipelineSegment.setId(1L);
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipelineSegment in the database
        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSegmentRepository.findAll().size();
        // set the field null
        pipelineSegment.setNum(null);

        // Create the PipelineSegment, which fails.
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSegmentRepository.findAll().size();
        // set the field null
        pipelineSegment.setName(null);

        // Create the PipelineSegment, which fails.
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpStart1IsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSegmentRepository.findAll().size();
        // set the field null
        pipelineSegment.setKpStart1(null);

        // Create the PipelineSegment, which fails.
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpEnd1IsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSegmentRepository.findAll().size();
        // set the field null
        pipelineSegment.setKpEnd1(null);

        // Create the PipelineSegment, which fails.
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpStart4IsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSegmentRepository.findAll().size();
        // set the field null
        pipelineSegment.setKpStart4(null);

        // Create the PipelineSegment, which fails.
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpEnd4IsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSegmentRepository.findAll().size();
        // set the field null
        pipelineSegment.setKpEnd4(null);

        // Create the PipelineSegment, which fails.
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        restPipelineSegmentMockMvc.perform(post("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPipelineSegments() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList
        restPipelineSegmentMockMvc.perform(get("/api/pipeline-segments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipelineSegment.getId().intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].kpStart1").value(hasItem(DEFAULT_KP_START_1.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd1").value(hasItem(DEFAULT_KP_END_1.intValue())))
            .andExpect(jsonPath("$.[*].kpStart4").value(hasItem(DEFAULT_KP_START_4.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd4").value(hasItem(DEFAULT_KP_END_4.intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getPipelineSegment() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get the pipelineSegment
        restPipelineSegmentMockMvc.perform(get("/api/pipeline-segments/{id}", pipelineSegment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipelineSegment.getId().intValue()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.kpStart1").value(DEFAULT_KP_START_1.intValue()))
            .andExpect(jsonPath("$.kpEnd1").value(DEFAULT_KP_END_1.intValue()))
            .andExpect(jsonPath("$.kpStart4").value(DEFAULT_KP_START_4.intValue()))
            .andExpect(jsonPath("$.kpEnd4").value(DEFAULT_KP_END_4.intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByNumIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where num equals to DEFAULT_NUM
        defaultPipelineSegmentShouldBeFound("num.equals=" + DEFAULT_NUM);

        // Get all the pipelineSegmentList where num equals to UPDATED_NUM
        defaultPipelineSegmentShouldNotBeFound("num.equals=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByNumIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where num in DEFAULT_NUM or UPDATED_NUM
        defaultPipelineSegmentShouldBeFound("num.in=" + DEFAULT_NUM + "," + UPDATED_NUM);

        // Get all the pipelineSegmentList where num equals to UPDATED_NUM
        defaultPipelineSegmentShouldNotBeFound("num.in=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where num is not null
        defaultPipelineSegmentShouldBeFound("num.specified=true");

        // Get all the pipelineSegmentList where num is null
        defaultPipelineSegmentShouldNotBeFound("num.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where name equals to DEFAULT_NAME
        defaultPipelineSegmentShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the pipelineSegmentList where name equals to UPDATED_NAME
        defaultPipelineSegmentShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPipelineSegmentShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the pipelineSegmentList where name equals to UPDATED_NAME
        defaultPipelineSegmentShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where name is not null
        defaultPipelineSegmentShouldBeFound("name.specified=true");

        // Get all the pipelineSegmentList where name is null
        defaultPipelineSegmentShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpStart1IsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpStart1 equals to DEFAULT_KP_START_1
        defaultPipelineSegmentShouldBeFound("kpStart1.equals=" + DEFAULT_KP_START_1);

        // Get all the pipelineSegmentList where kpStart1 equals to UPDATED_KP_START_1
        defaultPipelineSegmentShouldNotBeFound("kpStart1.equals=" + UPDATED_KP_START_1);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpStart1IsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpStart1 in DEFAULT_KP_START_1 or UPDATED_KP_START_1
        defaultPipelineSegmentShouldBeFound("kpStart1.in=" + DEFAULT_KP_START_1 + "," + UPDATED_KP_START_1);

        // Get all the pipelineSegmentList where kpStart1 equals to UPDATED_KP_START_1
        defaultPipelineSegmentShouldNotBeFound("kpStart1.in=" + UPDATED_KP_START_1);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpStart1IsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpStart1 is not null
        defaultPipelineSegmentShouldBeFound("kpStart1.specified=true");

        // Get all the pipelineSegmentList where kpStart1 is null
        defaultPipelineSegmentShouldNotBeFound("kpStart1.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpEnd1IsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpEnd1 equals to DEFAULT_KP_END_1
        defaultPipelineSegmentShouldBeFound("kpEnd1.equals=" + DEFAULT_KP_END_1);

        // Get all the pipelineSegmentList where kpEnd1 equals to UPDATED_KP_END_1
        defaultPipelineSegmentShouldNotBeFound("kpEnd1.equals=" + UPDATED_KP_END_1);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpEnd1IsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpEnd1 in DEFAULT_KP_END_1 or UPDATED_KP_END_1
        defaultPipelineSegmentShouldBeFound("kpEnd1.in=" + DEFAULT_KP_END_1 + "," + UPDATED_KP_END_1);

        // Get all the pipelineSegmentList where kpEnd1 equals to UPDATED_KP_END_1
        defaultPipelineSegmentShouldNotBeFound("kpEnd1.in=" + UPDATED_KP_END_1);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpEnd1IsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpEnd1 is not null
        defaultPipelineSegmentShouldBeFound("kpEnd1.specified=true");

        // Get all the pipelineSegmentList where kpEnd1 is null
        defaultPipelineSegmentShouldNotBeFound("kpEnd1.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpStart4IsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpStart4 equals to DEFAULT_KP_START_4
        defaultPipelineSegmentShouldBeFound("kpStart4.equals=" + DEFAULT_KP_START_4);

        // Get all the pipelineSegmentList where kpStart4 equals to UPDATED_KP_START_4
        defaultPipelineSegmentShouldNotBeFound("kpStart4.equals=" + UPDATED_KP_START_4);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpStart4IsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpStart4 in DEFAULT_KP_START_4 or UPDATED_KP_START_4
        defaultPipelineSegmentShouldBeFound("kpStart4.in=" + DEFAULT_KP_START_4 + "," + UPDATED_KP_START_4);

        // Get all the pipelineSegmentList where kpStart4 equals to UPDATED_KP_START_4
        defaultPipelineSegmentShouldNotBeFound("kpStart4.in=" + UPDATED_KP_START_4);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpStart4IsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpStart4 is not null
        defaultPipelineSegmentShouldBeFound("kpStart4.specified=true");

        // Get all the pipelineSegmentList where kpStart4 is null
        defaultPipelineSegmentShouldNotBeFound("kpStart4.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpEnd4IsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpEnd4 equals to DEFAULT_KP_END_4
        defaultPipelineSegmentShouldBeFound("kpEnd4.equals=" + DEFAULT_KP_END_4);

        // Get all the pipelineSegmentList where kpEnd4 equals to UPDATED_KP_END_4
        defaultPipelineSegmentShouldNotBeFound("kpEnd4.equals=" + UPDATED_KP_END_4);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpEnd4IsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpEnd4 in DEFAULT_KP_END_4 or UPDATED_KP_END_4
        defaultPipelineSegmentShouldBeFound("kpEnd4.in=" + DEFAULT_KP_END_4 + "," + UPDATED_KP_END_4);

        // Get all the pipelineSegmentList where kpEnd4 equals to UPDATED_KP_END_4
        defaultPipelineSegmentShouldNotBeFound("kpEnd4.in=" + UPDATED_KP_END_4);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByKpEnd4IsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where kpEnd4 is not null
        defaultPipelineSegmentShouldBeFound("kpEnd4.specified=true");

        // Get all the pipelineSegmentList where kpEnd4 is null
        defaultPipelineSegmentShouldNotBeFound("kpEnd4.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipelineSegmentShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipelineSegmentList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineSegmentShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipelineSegmentShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipelineSegmentList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineSegmentShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where dateCreate is not null
        defaultPipelineSegmentShouldBeFound("dateCreate.specified=true");

        // Get all the pipelineSegmentList where dateCreate is null
        defaultPipelineSegmentShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipelineSegmentShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipelineSegmentList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineSegmentShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipelineSegmentShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipelineSegmentList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineSegmentShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where dateEdit is not null
        defaultPipelineSegmentShouldBeFound("dateEdit.specified=true");

        // Get all the pipelineSegmentList where dateEdit is null
        defaultPipelineSegmentShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where creator equals to DEFAULT_CREATOR
        defaultPipelineSegmentShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipelineSegmentList where creator equals to UPDATED_CREATOR
        defaultPipelineSegmentShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipelineSegmentShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipelineSegmentList where creator equals to UPDATED_CREATOR
        defaultPipelineSegmentShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where creator is not null
        defaultPipelineSegmentShouldBeFound("creator.specified=true");

        // Get all the pipelineSegmentList where creator is null
        defaultPipelineSegmentShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where editor equals to DEFAULT_EDITOR
        defaultPipelineSegmentShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipelineSegmentList where editor equals to UPDATED_EDITOR
        defaultPipelineSegmentShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipelineSegmentShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipelineSegmentList where editor equals to UPDATED_EDITOR
        defaultPipelineSegmentShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSegmentsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        // Get all the pipelineSegmentList where editor is not null
        defaultPipelineSegmentShouldBeFound("editor.specified=true");

        // Get all the pipelineSegmentList where editor is null
        defaultPipelineSegmentShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipelineSegmentShouldBeFound(String filter) throws Exception {
        restPipelineSegmentMockMvc.perform(get("/api/pipeline-segments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipelineSegment.getId().intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].kpStart1").value(hasItem(DEFAULT_KP_START_1.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd1").value(hasItem(DEFAULT_KP_END_1.intValue())))
            .andExpect(jsonPath("$.[*].kpStart4").value(hasItem(DEFAULT_KP_START_4.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd4").value(hasItem(DEFAULT_KP_END_4.intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipelineSegmentMockMvc.perform(get("/api/pipeline-segments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipelineSegmentShouldNotBeFound(String filter) throws Exception {
        restPipelineSegmentMockMvc.perform(get("/api/pipeline-segments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipelineSegmentMockMvc.perform(get("/api/pipeline-segments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPipelineSegment() throws Exception {
        // Get the pipelineSegment
        restPipelineSegmentMockMvc.perform(get("/api/pipeline-segments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipelineSegment() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        int databaseSizeBeforeUpdate = pipelineSegmentRepository.findAll().size();

        // Update the pipelineSegment
        PipelineSegment updatedPipelineSegment = pipelineSegmentRepository.findById(pipelineSegment.getId()).get();
        // Disconnect from session so that the updates on updatedPipelineSegment are not directly saved in db
        em.detach(updatedPipelineSegment);
        updatedPipelineSegment
            .num(UPDATED_NUM)
            .name(UPDATED_NAME)
            .kpStart1(UPDATED_KP_START_1)
            .kpEnd1(UPDATED_KP_END_1)
            .kpStart4(UPDATED_KP_START_4)
            .kpEnd4(UPDATED_KP_END_4)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(updatedPipelineSegment);

        restPipelineSegmentMockMvc.perform(put("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isOk());

        // Validate the PipelineSegment in the database
        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeUpdate);
        PipelineSegment testPipelineSegment = pipelineSegmentList.get(pipelineSegmentList.size() - 1);
        assertThat(testPipelineSegment.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testPipelineSegment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPipelineSegment.getKpStart1()).isEqualTo(UPDATED_KP_START_1);
        assertThat(testPipelineSegment.getKpEnd1()).isEqualTo(UPDATED_KP_END_1);
        assertThat(testPipelineSegment.getKpStart4()).isEqualTo(UPDATED_KP_START_4);
        assertThat(testPipelineSegment.getKpEnd4()).isEqualTo(UPDATED_KP_END_4);
        assertThat(testPipelineSegment.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipelineSegment.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipelineSegment.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipelineSegment.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipelineSegment() throws Exception {
        int databaseSizeBeforeUpdate = pipelineSegmentRepository.findAll().size();

        // Create the PipelineSegment
        PipelineSegmentDTO pipelineSegmentDTO = pipelineSegmentMapper.toDto(pipelineSegment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipelineSegmentMockMvc.perform(put("/api/pipeline-segments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSegmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipelineSegment in the database
        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipelineSegment() throws Exception {
        // Initialize the database
        pipelineSegmentRepository.saveAndFlush(pipelineSegment);

        int databaseSizeBeforeDelete = pipelineSegmentRepository.findAll().size();

        // Delete the pipelineSegment
        restPipelineSegmentMockMvc.perform(delete("/api/pipeline-segments/{id}", pipelineSegment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<PipelineSegment> pipelineSegmentList = pipelineSegmentRepository.findAll();
        assertThat(pipelineSegmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipelineSegment.class);
        PipelineSegment pipelineSegment1 = new PipelineSegment();
        pipelineSegment1.setId(1L);
        PipelineSegment pipelineSegment2 = new PipelineSegment();
        pipelineSegment2.setId(pipelineSegment1.getId());
        assertThat(pipelineSegment1).isEqualTo(pipelineSegment2);
        pipelineSegment2.setId(2L);
        assertThat(pipelineSegment1).isNotEqualTo(pipelineSegment2);
        pipelineSegment1.setId(null);
        assertThat(pipelineSegment1).isNotEqualTo(pipelineSegment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipelineSegmentDTO.class);
        PipelineSegmentDTO pipelineSegmentDTO1 = new PipelineSegmentDTO();
        pipelineSegmentDTO1.setId(1L);
        PipelineSegmentDTO pipelineSegmentDTO2 = new PipelineSegmentDTO();
        assertThat(pipelineSegmentDTO1).isNotEqualTo(pipelineSegmentDTO2);
        pipelineSegmentDTO2.setId(pipelineSegmentDTO1.getId());
        assertThat(pipelineSegmentDTO1).isEqualTo(pipelineSegmentDTO2);
        pipelineSegmentDTO2.setId(2L);
        assertThat(pipelineSegmentDTO1).isNotEqualTo(pipelineSegmentDTO2);
        pipelineSegmentDTO1.setId(null);
        assertThat(pipelineSegmentDTO1).isNotEqualTo(pipelineSegmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipelineSegmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipelineSegmentMapper.fromId(null)).isNull();
    }
}
