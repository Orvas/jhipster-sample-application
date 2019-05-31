package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.ListPipejointType;
import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.ListPipejointSpecification;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.PipejointHistRepository;
import io.github.jhipster.application.service.PipejointHistService;
import io.github.jhipster.application.service.dto.PipejointHistDTO;
import io.github.jhipster.application.service.mapper.PipejointHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipejointHistCriteria;
import io.github.jhipster.application.service.PipejointHistQueryService;

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
 * Integration tests for the {@Link PipejointHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipejointHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_EXTERNAL_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTERNAL_COAT_THICKNESS = new BigDecimal(2);

    private static final String DEFAULT_COORD = "AAAAAAAAAA";
    private static final String UPDATED_COORD = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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
    private PipejointHistRepository pipejointHistRepository;

    @Autowired
    private PipejointHistMapper pipejointHistMapper;

    @Autowired
    private PipejointHistService pipejointHistService;

    @Autowired
    private PipejointHistQueryService pipejointHistQueryService;

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

    private MockMvc restPipejointHistMockMvc;

    private PipejointHist pipejointHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipejointHistResource pipejointHistResource = new PipejointHistResource(pipejointHistService, pipejointHistQueryService);
        this.restPipejointHistMockMvc = MockMvcBuilders.standaloneSetup(pipejointHistResource)
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
    public static PipejointHist createEntity(EntityManager em) {
        PipejointHist pipejointHist = new PipejointHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .name(DEFAULT_NAME)
            .externalCoatThickness(DEFAULT_EXTERNAL_COAT_THICKNESS)
            .coord(DEFAULT_COORD)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        pipejointHist.setIdStatus(listObjectStatus);
        return pipejointHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PipejointHist createUpdatedEntity(EntityManager em) {
        PipejointHist pipejointHist = new PipejointHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .name(UPDATED_NAME)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .coord(UPDATED_COORD)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        pipejointHist.setIdStatus(listObjectStatus);
        return pipejointHist;
    }

    @BeforeEach
    public void initTest() {
        pipejointHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipejointHist() throws Exception {
        int databaseSizeBeforeCreate = pipejointHistRepository.findAll().size();

        // Create the PipejointHist
        PipejointHistDTO pipejointHistDTO = pipejointHistMapper.toDto(pipejointHist);
        restPipejointHistMockMvc.perform(post("/api/pipejoint-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointHistDTO)))
            .andExpect(status().isCreated());

        // Validate the PipejointHist in the database
        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeCreate + 1);
        PipejointHist testPipejointHist = pipejointHistList.get(pipejointHistList.size() - 1);
        assertThat(testPipejointHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testPipejointHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testPipejointHist.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPipejointHist.getExternalCoatThickness()).isEqualTo(DEFAULT_EXTERNAL_COAT_THICKNESS);
        assertThat(testPipejointHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testPipejointHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testPipejointHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPipejointHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testPipejointHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipejointHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipejointHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipejointHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipejointHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipejointHistRepository.findAll().size();

        // Create the PipejointHist with an existing ID
        pipejointHist.setId(1L);
        PipejointHistDTO pipejointHistDTO = pipejointHistMapper.toDto(pipejointHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipejointHistMockMvc.perform(post("/api/pipejoint-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipejointHist in the database
        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipejointHistRepository.findAll().size();
        // set the field null
        pipejointHist.setDateFrom(null);

        // Create the PipejointHist, which fails.
        PipejointHistDTO pipejointHistDTO = pipejointHistMapper.toDto(pipejointHist);

        restPipejointHistMockMvc.perform(post("/api/pipejoint-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipejointHistRepository.findAll().size();
        // set the field null
        pipejointHist.setDateTo(null);

        // Create the PipejointHist, which fails.
        PipejointHistDTO pipejointHistDTO = pipejointHistMapper.toDto(pipejointHist);

        restPipejointHistMockMvc.perform(post("/api/pipejoint-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipejointHistRepository.findAll().size();
        // set the field null
        pipejointHist.setIsCurrentFlag(null);

        // Create the PipejointHist, which fails.
        PipejointHistDTO pipejointHistDTO = pipejointHistMapper.toDto(pipejointHist);

        restPipejointHistMockMvc.perform(post("/api/pipejoint-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPipejointHists() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList
        restPipejointHistMockMvc.perform(get("/api/pipejoint-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipejointHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getPipejointHist() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get the pipejointHist
        restPipejointHistMockMvc.perform(get("/api/pipejoint-hists/{id}", pipejointHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipejointHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.externalCoatThickness").value(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.coord").value(DEFAULT_COORD.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultPipejointHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the pipejointHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultPipejointHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultPipejointHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the pipejointHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultPipejointHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateFrom is not null
        defaultPipejointHistShouldBeFound("dateFrom.specified=true");

        // Get all the pipejointHistList where dateFrom is null
        defaultPipejointHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultPipejointHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the pipejointHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultPipejointHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultPipejointHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the pipejointHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultPipejointHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateTo equals to DEFAULT_DATE_TO
        defaultPipejointHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the pipejointHistList where dateTo equals to UPDATED_DATE_TO
        defaultPipejointHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultPipejointHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the pipejointHistList where dateTo equals to UPDATED_DATE_TO
        defaultPipejointHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateTo is not null
        defaultPipejointHistShouldBeFound("dateTo.specified=true");

        // Get all the pipejointHistList where dateTo is null
        defaultPipejointHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultPipejointHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the pipejointHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultPipejointHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultPipejointHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the pipejointHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultPipejointHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where name equals to DEFAULT_NAME
        defaultPipejointHistShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the pipejointHistList where name equals to UPDATED_NAME
        defaultPipejointHistShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPipejointHistShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the pipejointHistList where name equals to UPDATED_NAME
        defaultPipejointHistShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where name is not null
        defaultPipejointHistShouldBeFound("name.specified=true");

        // Get all the pipejointHistList where name is null
        defaultPipejointHistShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByExternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where externalCoatThickness equals to DEFAULT_EXTERNAL_COAT_THICKNESS
        defaultPipejointHistShouldBeFound("externalCoatThickness.equals=" + DEFAULT_EXTERNAL_COAT_THICKNESS);

        // Get all the pipejointHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultPipejointHistShouldNotBeFound("externalCoatThickness.equals=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByExternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where externalCoatThickness in DEFAULT_EXTERNAL_COAT_THICKNESS or UPDATED_EXTERNAL_COAT_THICKNESS
        defaultPipejointHistShouldBeFound("externalCoatThickness.in=" + DEFAULT_EXTERNAL_COAT_THICKNESS + "," + UPDATED_EXTERNAL_COAT_THICKNESS);

        // Get all the pipejointHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultPipejointHistShouldNotBeFound("externalCoatThickness.in=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByExternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where externalCoatThickness is not null
        defaultPipejointHistShouldBeFound("externalCoatThickness.specified=true");

        // Get all the pipejointHistList where externalCoatThickness is null
        defaultPipejointHistShouldNotBeFound("externalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where coord equals to DEFAULT_COORD
        defaultPipejointHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the pipejointHistList where coord equals to UPDATED_COORD
        defaultPipejointHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultPipejointHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the pipejointHistList where coord equals to UPDATED_COORD
        defaultPipejointHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where coord is not null
        defaultPipejointHistShouldBeFound("coord.specified=true");

        // Get all the pipejointHistList where coord is null
        defaultPipejointHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipejointHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipejointHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultPipejointHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultPipejointHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the pipejointHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultPipejointHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where isCurrentFlag is not null
        defaultPipejointHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the pipejointHistList where isCurrentFlag is null
        defaultPipejointHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipejointHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipejointHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultPipejointHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipejointHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipejointHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultPipejointHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where description equals to DEFAULT_DESCRIPTION
        defaultPipejointHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the pipejointHistList where description equals to UPDATED_DESCRIPTION
        defaultPipejointHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultPipejointHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the pipejointHistList where description equals to UPDATED_DESCRIPTION
        defaultPipejointHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where description is not null
        defaultPipejointHistShouldBeFound("description.specified=true");

        // Get all the pipejointHistList where description is null
        defaultPipejointHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where comment equals to DEFAULT_COMMENT
        defaultPipejointHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the pipejointHistList where comment equals to UPDATED_COMMENT
        defaultPipejointHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultPipejointHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the pipejointHistList where comment equals to UPDATED_COMMENT
        defaultPipejointHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where comment is not null
        defaultPipejointHistShouldBeFound("comment.specified=true");

        // Get all the pipejointHistList where comment is null
        defaultPipejointHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipejointHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipejointHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipejointHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipejointHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipejointHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipejointHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateCreate is not null
        defaultPipejointHistShouldBeFound("dateCreate.specified=true");

        // Get all the pipejointHistList where dateCreate is null
        defaultPipejointHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipejointHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipejointHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipejointHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipejointHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipejointHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipejointHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where dateEdit is not null
        defaultPipejointHistShouldBeFound("dateEdit.specified=true");

        // Get all the pipejointHistList where dateEdit is null
        defaultPipejointHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where creator equals to DEFAULT_CREATOR
        defaultPipejointHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipejointHistList where creator equals to UPDATED_CREATOR
        defaultPipejointHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipejointHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipejointHistList where creator equals to UPDATED_CREATOR
        defaultPipejointHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where creator is not null
        defaultPipejointHistShouldBeFound("creator.specified=true");

        // Get all the pipejointHistList where creator is null
        defaultPipejointHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where editor equals to DEFAULT_EDITOR
        defaultPipejointHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipejointHistList where editor equals to UPDATED_EDITOR
        defaultPipejointHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipejointHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipejointHistList where editor equals to UPDATED_EDITOR
        defaultPipejointHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        // Get all the pipejointHistList where editor is not null
        defaultPipejointHistShouldBeFound("editor.specified=true");

        // Get all the pipejointHistList where editor is null
        defaultPipejointHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointHistsByPipejointIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipejoint pipejoint = PipejointResourceIT.createEntity(em);
        em.persist(pipejoint);
        em.flush();
        pipejointHist.setPipejoint(pipejoint);
        pipejointHistRepository.saveAndFlush(pipejointHist);
        Long pipejointId = pipejoint.getId();

        // Get all the pipejointHistList where pipejoint equals to pipejointId
        defaultPipejointHistShouldBeFound("pipejointId.equals=" + pipejointId);

        // Get all the pipejointHistList where pipejoint equals to pipejointId + 1
        defaultPipejointHistShouldNotBeFound("pipejointId.equals=" + (pipejointId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByIdTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListPipejointType idType = ListPipejointTypeResourceIT.createEntity(em);
        em.persist(idType);
        em.flush();
        pipejointHist.setIdType(idType);
        pipejointHistRepository.saveAndFlush(pipejointHist);
        Long idTypeId = idType.getId();

        // Get all the pipejointHistList where idType equals to idTypeId
        defaultPipejointHistShouldBeFound("idTypeId.equals=" + idTypeId);

        // Get all the pipejointHistList where idType equals to idTypeId + 1
        defaultPipejointHistShouldNotBeFound("idTypeId.equals=" + (idTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByIdExternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListExternalCoating idExternalCoatType = ListExternalCoatingResourceIT.createEntity(em);
        em.persist(idExternalCoatType);
        em.flush();
        pipejointHist.setIdExternalCoatType(idExternalCoatType);
        pipejointHistRepository.saveAndFlush(pipejointHist);
        Long idExternalCoatTypeId = idExternalCoatType.getId();

        // Get all the pipejointHistList where idExternalCoatType equals to idExternalCoatTypeId
        defaultPipejointHistShouldBeFound("idExternalCoatTypeId.equals=" + idExternalCoatTypeId);

        // Get all the pipejointHistList where idExternalCoatType equals to idExternalCoatTypeId + 1
        defaultPipejointHistShouldNotBeFound("idExternalCoatTypeId.equals=" + (idExternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByIdMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMaterial idMaterial = ListMaterialResourceIT.createEntity(em);
        em.persist(idMaterial);
        em.flush();
        pipejointHist.setIdMaterial(idMaterial);
        pipejointHistRepository.saveAndFlush(pipejointHist);
        Long idMaterialId = idMaterial.getId();

        // Get all the pipejointHistList where idMaterial equals to idMaterialId
        defaultPipejointHistShouldBeFound("idMaterialId.equals=" + idMaterialId);

        // Get all the pipejointHistList where idMaterial equals to idMaterialId + 1
        defaultPipejointHistShouldNotBeFound("idMaterialId.equals=" + (idMaterialId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByIdSpecificationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListPipejointSpecification idSpecification = ListPipejointSpecificationResourceIT.createEntity(em);
        em.persist(idSpecification);
        em.flush();
        pipejointHist.setIdSpecification(idSpecification);
        pipejointHistRepository.saveAndFlush(pipejointHist);
        Long idSpecificationId = idSpecification.getId();

        // Get all the pipejointHistList where idSpecification equals to idSpecificationId
        defaultPipejointHistShouldBeFound("idSpecificationId.equals=" + idSpecificationId);

        // Get all the pipejointHistList where idSpecification equals to idSpecificationId + 1
        defaultPipejointHistShouldNotBeFound("idSpecificationId.equals=" + (idSpecificationId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = pipejointHist.getIdStatus();
        pipejointHistRepository.saveAndFlush(pipejointHist);
        Long idStatusId = idStatus.getId();

        // Get all the pipejointHistList where idStatus equals to idStatusId
        defaultPipejointHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the pipejointHistList where idStatus equals to idStatusId + 1
        defaultPipejointHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipejointHistShouldBeFound(String filter) throws Exception {
        restPipejointHistMockMvc.perform(get("/api/pipejoint-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipejointHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipejointHistMockMvc.perform(get("/api/pipejoint-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipejointHistShouldNotBeFound(String filter) throws Exception {
        restPipejointHistMockMvc.perform(get("/api/pipejoint-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipejointHistMockMvc.perform(get("/api/pipejoint-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPipejointHist() throws Exception {
        // Get the pipejointHist
        restPipejointHistMockMvc.perform(get("/api/pipejoint-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipejointHist() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        int databaseSizeBeforeUpdate = pipejointHistRepository.findAll().size();

        // Update the pipejointHist
        PipejointHist updatedPipejointHist = pipejointHistRepository.findById(pipejointHist.getId()).get();
        // Disconnect from session so that the updates on updatedPipejointHist are not directly saved in db
        em.detach(updatedPipejointHist);
        updatedPipejointHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .name(UPDATED_NAME)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .coord(UPDATED_COORD)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipejointHistDTO pipejointHistDTO = pipejointHistMapper.toDto(updatedPipejointHist);

        restPipejointHistMockMvc.perform(put("/api/pipejoint-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointHistDTO)))
            .andExpect(status().isOk());

        // Validate the PipejointHist in the database
        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeUpdate);
        PipejointHist testPipejointHist = pipejointHistList.get(pipejointHistList.size() - 1);
        assertThat(testPipejointHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testPipejointHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testPipejointHist.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPipejointHist.getExternalCoatThickness()).isEqualTo(UPDATED_EXTERNAL_COAT_THICKNESS);
        assertThat(testPipejointHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testPipejointHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testPipejointHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPipejointHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testPipejointHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipejointHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipejointHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipejointHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipejointHist() throws Exception {
        int databaseSizeBeforeUpdate = pipejointHistRepository.findAll().size();

        // Create the PipejointHist
        PipejointHistDTO pipejointHistDTO = pipejointHistMapper.toDto(pipejointHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipejointHistMockMvc.perform(put("/api/pipejoint-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipejointHist in the database
        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipejointHist() throws Exception {
        // Initialize the database
        pipejointHistRepository.saveAndFlush(pipejointHist);

        int databaseSizeBeforeDelete = pipejointHistRepository.findAll().size();

        // Delete the pipejointHist
        restPipejointHistMockMvc.perform(delete("/api/pipejoint-hists/{id}", pipejointHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<PipejointHist> pipejointHistList = pipejointHistRepository.findAll();
        assertThat(pipejointHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipejointHist.class);
        PipejointHist pipejointHist1 = new PipejointHist();
        pipejointHist1.setId(1L);
        PipejointHist pipejointHist2 = new PipejointHist();
        pipejointHist2.setId(pipejointHist1.getId());
        assertThat(pipejointHist1).isEqualTo(pipejointHist2);
        pipejointHist2.setId(2L);
        assertThat(pipejointHist1).isNotEqualTo(pipejointHist2);
        pipejointHist1.setId(null);
        assertThat(pipejointHist1).isNotEqualTo(pipejointHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipejointHistDTO.class);
        PipejointHistDTO pipejointHistDTO1 = new PipejointHistDTO();
        pipejointHistDTO1.setId(1L);
        PipejointHistDTO pipejointHistDTO2 = new PipejointHistDTO();
        assertThat(pipejointHistDTO1).isNotEqualTo(pipejointHistDTO2);
        pipejointHistDTO2.setId(pipejointHistDTO1.getId());
        assertThat(pipejointHistDTO1).isEqualTo(pipejointHistDTO2);
        pipejointHistDTO2.setId(2L);
        assertThat(pipejointHistDTO1).isNotEqualTo(pipejointHistDTO2);
        pipejointHistDTO1.setId(null);
        assertThat(pipejointHistDTO1).isNotEqualTo(pipejointHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipejointHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipejointHistMapper.fromId(null)).isNull();
    }
}
