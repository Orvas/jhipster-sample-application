package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.LaunchReceiverHist;
import io.github.jhipster.application.domain.LaunchReceiver;
import io.github.jhipster.application.domain.Pipeline;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.LaunchReceiverHistRepository;
import io.github.jhipster.application.service.LaunchReceiverHistService;
import io.github.jhipster.application.service.dto.LaunchReceiverHistDTO;
import io.github.jhipster.application.service.mapper.LaunchReceiverHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.LaunchReceiverHistCriteria;
import io.github.jhipster.application.service.LaunchReceiverHistQueryService;

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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link LaunchReceiverHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class LaunchReceiverHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COORD = "AAAAAAAAAA";
    private static final String UPDATED_COORD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_KP_INST = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_INST = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private LaunchReceiverHistRepository launchReceiverHistRepository;

    @Autowired
    private LaunchReceiverHistMapper launchReceiverHistMapper;

    @Autowired
    private LaunchReceiverHistService launchReceiverHistService;

    @Autowired
    private LaunchReceiverHistQueryService launchReceiverHistQueryService;

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

    private MockMvc restLaunchReceiverHistMockMvc;

    private LaunchReceiverHist launchReceiverHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LaunchReceiverHistResource launchReceiverHistResource = new LaunchReceiverHistResource(launchReceiverHistService, launchReceiverHistQueryService);
        this.restLaunchReceiverHistMockMvc = MockMvcBuilders.standaloneSetup(launchReceiverHistResource)
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
    public static LaunchReceiverHist createEntity(EntityManager em) {
        LaunchReceiverHist launchReceiverHist = new LaunchReceiverHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .name(DEFAULT_NAME)
            .coord(DEFAULT_COORD)
            .kpInst(DEFAULT_KP_INST)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        Pipeline pipeline;
        if (TestUtil.findAll(em, Pipeline.class).isEmpty()) {
            pipeline = PipelineResourceIT.createEntity(em);
            em.persist(pipeline);
            em.flush();
        } else {
            pipeline = TestUtil.findAll(em, Pipeline.class).get(0);
        }
        launchReceiverHist.setIdPipeline(pipeline);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        launchReceiverHist.setIdStatus(listObjectStatus);
        return launchReceiverHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaunchReceiverHist createUpdatedEntity(EntityManager em) {
        LaunchReceiverHist launchReceiverHist = new LaunchReceiverHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .name(UPDATED_NAME)
            .coord(UPDATED_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        Pipeline pipeline;
        if (TestUtil.findAll(em, Pipeline.class).isEmpty()) {
            pipeline = PipelineResourceIT.createUpdatedEntity(em);
            em.persist(pipeline);
            em.flush();
        } else {
            pipeline = TestUtil.findAll(em, Pipeline.class).get(0);
        }
        launchReceiverHist.setIdPipeline(pipeline);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        launchReceiverHist.setIdStatus(listObjectStatus);
        return launchReceiverHist;
    }

    @BeforeEach
    public void initTest() {
        launchReceiverHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createLaunchReceiverHist() throws Exception {
        int databaseSizeBeforeCreate = launchReceiverHistRepository.findAll().size();

        // Create the LaunchReceiverHist
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(launchReceiverHist);
        restLaunchReceiverHistMockMvc.perform(post("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isCreated());

        // Validate the LaunchReceiverHist in the database
        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeCreate + 1);
        LaunchReceiverHist testLaunchReceiverHist = launchReceiverHistList.get(launchReceiverHistList.size() - 1);
        assertThat(testLaunchReceiverHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testLaunchReceiverHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testLaunchReceiverHist.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLaunchReceiverHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testLaunchReceiverHist.getKpInst()).isEqualTo(DEFAULT_KP_INST);
        assertThat(testLaunchReceiverHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testLaunchReceiverHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testLaunchReceiverHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testLaunchReceiverHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testLaunchReceiverHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testLaunchReceiverHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createLaunchReceiverHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = launchReceiverHistRepository.findAll().size();

        // Create the LaunchReceiverHist with an existing ID
        launchReceiverHist.setId(1L);
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(launchReceiverHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaunchReceiverHistMockMvc.perform(post("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LaunchReceiverHist in the database
        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = launchReceiverHistRepository.findAll().size();
        // set the field null
        launchReceiverHist.setDateFrom(null);

        // Create the LaunchReceiverHist, which fails.
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(launchReceiverHist);

        restLaunchReceiverHistMockMvc.perform(post("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isBadRequest());

        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = launchReceiverHistRepository.findAll().size();
        // set the field null
        launchReceiverHist.setDateTo(null);

        // Create the LaunchReceiverHist, which fails.
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(launchReceiverHist);

        restLaunchReceiverHistMockMvc.perform(post("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isBadRequest());

        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = launchReceiverHistRepository.findAll().size();
        // set the field null
        launchReceiverHist.setName(null);

        // Create the LaunchReceiverHist, which fails.
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(launchReceiverHist);

        restLaunchReceiverHistMockMvc.perform(post("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isBadRequest());

        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = launchReceiverHistRepository.findAll().size();
        // set the field null
        launchReceiverHist.setIsCurrentFlag(null);

        // Create the LaunchReceiverHist, which fails.
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(launchReceiverHist);

        restLaunchReceiverHistMockMvc.perform(post("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isBadRequest());

        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHists() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList
        restLaunchReceiverHistMockMvc.perform(get("/api/launch-receiver-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(launchReceiverHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD.toString())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getLaunchReceiverHist() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get the launchReceiverHist
        restLaunchReceiverHistMockMvc.perform(get("/api/launch-receiver-hists/{id}", launchReceiverHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(launchReceiverHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.coord").value(DEFAULT_COORD.toString()))
            .andExpect(jsonPath("$.kpInst").value(DEFAULT_KP_INST.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultLaunchReceiverHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the launchReceiverHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultLaunchReceiverHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultLaunchReceiverHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the launchReceiverHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultLaunchReceiverHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateFrom is not null
        defaultLaunchReceiverHistShouldBeFound("dateFrom.specified=true");

        // Get all the launchReceiverHistList where dateFrom is null
        defaultLaunchReceiverHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultLaunchReceiverHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the launchReceiverHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultLaunchReceiverHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultLaunchReceiverHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the launchReceiverHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultLaunchReceiverHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateTo equals to DEFAULT_DATE_TO
        defaultLaunchReceiverHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the launchReceiverHistList where dateTo equals to UPDATED_DATE_TO
        defaultLaunchReceiverHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultLaunchReceiverHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the launchReceiverHistList where dateTo equals to UPDATED_DATE_TO
        defaultLaunchReceiverHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateTo is not null
        defaultLaunchReceiverHistShouldBeFound("dateTo.specified=true");

        // Get all the launchReceiverHistList where dateTo is null
        defaultLaunchReceiverHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultLaunchReceiverHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the launchReceiverHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultLaunchReceiverHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultLaunchReceiverHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the launchReceiverHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultLaunchReceiverHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where name equals to DEFAULT_NAME
        defaultLaunchReceiverHistShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the launchReceiverHistList where name equals to UPDATED_NAME
        defaultLaunchReceiverHistShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where name in DEFAULT_NAME or UPDATED_NAME
        defaultLaunchReceiverHistShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the launchReceiverHistList where name equals to UPDATED_NAME
        defaultLaunchReceiverHistShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where name is not null
        defaultLaunchReceiverHistShouldBeFound("name.specified=true");

        // Get all the launchReceiverHistList where name is null
        defaultLaunchReceiverHistShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where coord equals to DEFAULT_COORD
        defaultLaunchReceiverHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the launchReceiverHistList where coord equals to UPDATED_COORD
        defaultLaunchReceiverHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultLaunchReceiverHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the launchReceiverHistList where coord equals to UPDATED_COORD
        defaultLaunchReceiverHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where coord is not null
        defaultLaunchReceiverHistShouldBeFound("coord.specified=true");

        // Get all the launchReceiverHistList where coord is null
        defaultLaunchReceiverHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByKpInstIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where kpInst equals to DEFAULT_KP_INST
        defaultLaunchReceiverHistShouldBeFound("kpInst.equals=" + DEFAULT_KP_INST);

        // Get all the launchReceiverHistList where kpInst equals to UPDATED_KP_INST
        defaultLaunchReceiverHistShouldNotBeFound("kpInst.equals=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByKpInstIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where kpInst in DEFAULT_KP_INST or UPDATED_KP_INST
        defaultLaunchReceiverHistShouldBeFound("kpInst.in=" + DEFAULT_KP_INST + "," + UPDATED_KP_INST);

        // Get all the launchReceiverHistList where kpInst equals to UPDATED_KP_INST
        defaultLaunchReceiverHistShouldNotBeFound("kpInst.in=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByKpInstIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where kpInst is not null
        defaultLaunchReceiverHistShouldBeFound("kpInst.specified=true");

        // Get all the launchReceiverHistList where kpInst is null
        defaultLaunchReceiverHistShouldNotBeFound("kpInst.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the launchReceiverHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the launchReceiverHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where isCurrentFlag is not null
        defaultLaunchReceiverHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the launchReceiverHistList where isCurrentFlag is null
        defaultLaunchReceiverHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the launchReceiverHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the launchReceiverHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultLaunchReceiverHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where comment equals to DEFAULT_COMMENT
        defaultLaunchReceiverHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the launchReceiverHistList where comment equals to UPDATED_COMMENT
        defaultLaunchReceiverHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultLaunchReceiverHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the launchReceiverHistList where comment equals to UPDATED_COMMENT
        defaultLaunchReceiverHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where comment is not null
        defaultLaunchReceiverHistShouldBeFound("comment.specified=true");

        // Get all the launchReceiverHistList where comment is null
        defaultLaunchReceiverHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultLaunchReceiverHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the launchReceiverHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultLaunchReceiverHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultLaunchReceiverHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the launchReceiverHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultLaunchReceiverHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateCreate is not null
        defaultLaunchReceiverHistShouldBeFound("dateCreate.specified=true");

        // Get all the launchReceiverHistList where dateCreate is null
        defaultLaunchReceiverHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultLaunchReceiverHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the launchReceiverHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultLaunchReceiverHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultLaunchReceiverHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the launchReceiverHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultLaunchReceiverHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where dateEdit is not null
        defaultLaunchReceiverHistShouldBeFound("dateEdit.specified=true");

        // Get all the launchReceiverHistList where dateEdit is null
        defaultLaunchReceiverHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where creator equals to DEFAULT_CREATOR
        defaultLaunchReceiverHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the launchReceiverHistList where creator equals to UPDATED_CREATOR
        defaultLaunchReceiverHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultLaunchReceiverHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the launchReceiverHistList where creator equals to UPDATED_CREATOR
        defaultLaunchReceiverHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where creator is not null
        defaultLaunchReceiverHistShouldBeFound("creator.specified=true");

        // Get all the launchReceiverHistList where creator is null
        defaultLaunchReceiverHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where editor equals to DEFAULT_EDITOR
        defaultLaunchReceiverHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the launchReceiverHistList where editor equals to UPDATED_EDITOR
        defaultLaunchReceiverHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultLaunchReceiverHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the launchReceiverHistList where editor equals to UPDATED_EDITOR
        defaultLaunchReceiverHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        // Get all the launchReceiverHistList where editor is not null
        defaultLaunchReceiverHistShouldBeFound("editor.specified=true");

        // Get all the launchReceiverHistList where editor is null
        defaultLaunchReceiverHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByLaunchReceiverIsEqualToSomething() throws Exception {
        // Initialize the database
        LaunchReceiver launchReceiver = LaunchReceiverResourceIT.createEntity(em);
        em.persist(launchReceiver);
        em.flush();
        launchReceiverHist.setLaunchReceiver(launchReceiver);
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);
        Long launchReceiverId = launchReceiver.getId();

        // Get all the launchReceiverHistList where launchReceiver equals to launchReceiverId
        defaultLaunchReceiverHistShouldBeFound("launchReceiverId.equals=" + launchReceiverId);

        // Get all the launchReceiverHistList where launchReceiver equals to launchReceiverId + 1
        defaultLaunchReceiverHistShouldNotBeFound("launchReceiverId.equals=" + (launchReceiverId + 1));
    }


    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByIdPipelineIsEqualToSomething() throws Exception {
        // Get already existing entity
        Pipeline idPipeline = launchReceiverHist.getIdPipeline();
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);
        Long idPipelineId = idPipeline.getId();

        // Get all the launchReceiverHistList where idPipeline equals to idPipelineId
        defaultLaunchReceiverHistShouldBeFound("idPipelineId.equals=" + idPipelineId);

        // Get all the launchReceiverHistList where idPipeline equals to idPipelineId + 1
        defaultLaunchReceiverHistShouldNotBeFound("idPipelineId.equals=" + (idPipelineId + 1));
    }


    @Test
    @Transactional
    public void getAllLaunchReceiverHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = launchReceiverHist.getIdStatus();
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);
        Long idStatusId = idStatus.getId();

        // Get all the launchReceiverHistList where idStatus equals to idStatusId
        defaultLaunchReceiverHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the launchReceiverHistList where idStatus equals to idStatusId + 1
        defaultLaunchReceiverHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLaunchReceiverHistShouldBeFound(String filter) throws Exception {
        restLaunchReceiverHistMockMvc.perform(get("/api/launch-receiver-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(launchReceiverHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD)))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restLaunchReceiverHistMockMvc.perform(get("/api/launch-receiver-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLaunchReceiverHistShouldNotBeFound(String filter) throws Exception {
        restLaunchReceiverHistMockMvc.perform(get("/api/launch-receiver-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLaunchReceiverHistMockMvc.perform(get("/api/launch-receiver-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLaunchReceiverHist() throws Exception {
        // Get the launchReceiverHist
        restLaunchReceiverHistMockMvc.perform(get("/api/launch-receiver-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLaunchReceiverHist() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        int databaseSizeBeforeUpdate = launchReceiverHistRepository.findAll().size();

        // Update the launchReceiverHist
        LaunchReceiverHist updatedLaunchReceiverHist = launchReceiverHistRepository.findById(launchReceiverHist.getId()).get();
        // Disconnect from session so that the updates on updatedLaunchReceiverHist are not directly saved in db
        em.detach(updatedLaunchReceiverHist);
        updatedLaunchReceiverHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .name(UPDATED_NAME)
            .coord(UPDATED_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(updatedLaunchReceiverHist);

        restLaunchReceiverHistMockMvc.perform(put("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isOk());

        // Validate the LaunchReceiverHist in the database
        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeUpdate);
        LaunchReceiverHist testLaunchReceiverHist = launchReceiverHistList.get(launchReceiverHistList.size() - 1);
        assertThat(testLaunchReceiverHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testLaunchReceiverHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testLaunchReceiverHist.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLaunchReceiverHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testLaunchReceiverHist.getKpInst()).isEqualTo(UPDATED_KP_INST);
        assertThat(testLaunchReceiverHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testLaunchReceiverHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testLaunchReceiverHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testLaunchReceiverHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testLaunchReceiverHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testLaunchReceiverHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingLaunchReceiverHist() throws Exception {
        int databaseSizeBeforeUpdate = launchReceiverHistRepository.findAll().size();

        // Create the LaunchReceiverHist
        LaunchReceiverHistDTO launchReceiverHistDTO = launchReceiverHistMapper.toDto(launchReceiverHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaunchReceiverHistMockMvc.perform(put("/api/launch-receiver-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(launchReceiverHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LaunchReceiverHist in the database
        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLaunchReceiverHist() throws Exception {
        // Initialize the database
        launchReceiverHistRepository.saveAndFlush(launchReceiverHist);

        int databaseSizeBeforeDelete = launchReceiverHistRepository.findAll().size();

        // Delete the launchReceiverHist
        restLaunchReceiverHistMockMvc.perform(delete("/api/launch-receiver-hists/{id}", launchReceiverHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<LaunchReceiverHist> launchReceiverHistList = launchReceiverHistRepository.findAll();
        assertThat(launchReceiverHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaunchReceiverHist.class);
        LaunchReceiverHist launchReceiverHist1 = new LaunchReceiverHist();
        launchReceiverHist1.setId(1L);
        LaunchReceiverHist launchReceiverHist2 = new LaunchReceiverHist();
        launchReceiverHist2.setId(launchReceiverHist1.getId());
        assertThat(launchReceiverHist1).isEqualTo(launchReceiverHist2);
        launchReceiverHist2.setId(2L);
        assertThat(launchReceiverHist1).isNotEqualTo(launchReceiverHist2);
        launchReceiverHist1.setId(null);
        assertThat(launchReceiverHist1).isNotEqualTo(launchReceiverHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaunchReceiverHistDTO.class);
        LaunchReceiverHistDTO launchReceiverHistDTO1 = new LaunchReceiverHistDTO();
        launchReceiverHistDTO1.setId(1L);
        LaunchReceiverHistDTO launchReceiverHistDTO2 = new LaunchReceiverHistDTO();
        assertThat(launchReceiverHistDTO1).isNotEqualTo(launchReceiverHistDTO2);
        launchReceiverHistDTO2.setId(launchReceiverHistDTO1.getId());
        assertThat(launchReceiverHistDTO1).isEqualTo(launchReceiverHistDTO2);
        launchReceiverHistDTO2.setId(2L);
        assertThat(launchReceiverHistDTO1).isNotEqualTo(launchReceiverHistDTO2);
        launchReceiverHistDTO1.setId(null);
        assertThat(launchReceiverHistDTO1).isNotEqualTo(launchReceiverHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(launchReceiverHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(launchReceiverHistMapper.fromId(null)).isNull();
    }
}
