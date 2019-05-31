package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListEnvPoint;
import io.github.jhipster.application.repository.ListEnvPointRepository;
import io.github.jhipster.application.service.ListEnvPointService;
import io.github.jhipster.application.service.dto.ListEnvPointDTO;
import io.github.jhipster.application.service.mapper.ListEnvPointMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListEnvPointCriteria;
import io.github.jhipster.application.service.ListEnvPointQueryService;

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
 * Integration tests for the {@Link ListEnvPointResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListEnvPointResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DEGREE_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEGREE_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEGREE_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEGREE_END = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private ListEnvPointRepository listEnvPointRepository;

    @Autowired
    private ListEnvPointMapper listEnvPointMapper;

    @Autowired
    private ListEnvPointService listEnvPointService;

    @Autowired
    private ListEnvPointQueryService listEnvPointQueryService;

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

    private MockMvc restListEnvPointMockMvc;

    private ListEnvPoint listEnvPoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListEnvPointResource listEnvPointResource = new ListEnvPointResource(listEnvPointService, listEnvPointQueryService);
        this.restListEnvPointMockMvc = MockMvcBuilders.standaloneSetup(listEnvPointResource)
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
    public static ListEnvPoint createEntity(EntityManager em) {
        ListEnvPoint listEnvPoint = new ListEnvPoint()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .degreeStart(DEFAULT_DEGREE_START)
            .degreeEnd(DEFAULT_DEGREE_END)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listEnvPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListEnvPoint createUpdatedEntity(EntityManager em) {
        ListEnvPoint listEnvPoint = new ListEnvPoint()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .degreeStart(UPDATED_DEGREE_START)
            .degreeEnd(UPDATED_DEGREE_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listEnvPoint;
    }

    @BeforeEach
    public void initTest() {
        listEnvPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createListEnvPoint() throws Exception {
        int databaseSizeBeforeCreate = listEnvPointRepository.findAll().size();

        // Create the ListEnvPoint
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);
        restListEnvPointMockMvc.perform(post("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isCreated());

        // Validate the ListEnvPoint in the database
        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeCreate + 1);
        ListEnvPoint testListEnvPoint = listEnvPointList.get(listEnvPointList.size() - 1);
        assertThat(testListEnvPoint.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListEnvPoint.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListEnvPoint.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListEnvPoint.getDegreeStart()).isEqualTo(DEFAULT_DEGREE_START);
        assertThat(testListEnvPoint.getDegreeEnd()).isEqualTo(DEFAULT_DEGREE_END);
        assertThat(testListEnvPoint.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListEnvPoint.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListEnvPoint.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListEnvPoint.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListEnvPoint.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListEnvPoint.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListEnvPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listEnvPointRepository.findAll().size();

        // Create the ListEnvPoint with an existing ID
        listEnvPoint.setId(1L);
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListEnvPointMockMvc.perform(post("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListEnvPoint in the database
        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvPointRepository.findAll().size();
        // set the field null
        listEnvPoint.setCode(null);

        // Create the ListEnvPoint, which fails.
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);

        restListEnvPointMockMvc.perform(post("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvPointRepository.findAll().size();
        // set the field null
        listEnvPoint.setName(null);

        // Create the ListEnvPoint, which fails.
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);

        restListEnvPointMockMvc.perform(post("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvPointRepository.findAll().size();
        // set the field null
        listEnvPoint.setFullName(null);

        // Create the ListEnvPoint, which fails.
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);

        restListEnvPointMockMvc.perform(post("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDegreeStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvPointRepository.findAll().size();
        // set the field null
        listEnvPoint.setDegreeStart(null);

        // Create the ListEnvPoint, which fails.
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);

        restListEnvPointMockMvc.perform(post("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvPointRepository.findAll().size();
        // set the field null
        listEnvPoint.setIsCurrentFlag(null);

        // Create the ListEnvPoint, which fails.
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);

        restListEnvPointMockMvc.perform(post("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListEnvPoints() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList
        restListEnvPointMockMvc.perform(get("/api/list-env-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listEnvPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].degreeStart").value(hasItem(DEFAULT_DEGREE_START.intValue())))
            .andExpect(jsonPath("$.[*].degreeEnd").value(hasItem(DEFAULT_DEGREE_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListEnvPoint() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get the listEnvPoint
        restListEnvPointMockMvc.perform(get("/api/list-env-points/{id}", listEnvPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listEnvPoint.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.degreeStart").value(DEFAULT_DEGREE_START.intValue()))
            .andExpect(jsonPath("$.degreeEnd").value(DEFAULT_DEGREE_END.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where code equals to DEFAULT_CODE
        defaultListEnvPointShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listEnvPointList where code equals to UPDATED_CODE
        defaultListEnvPointShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListEnvPointShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listEnvPointList where code equals to UPDATED_CODE
        defaultListEnvPointShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where code is not null
        defaultListEnvPointShouldBeFound("code.specified=true");

        // Get all the listEnvPointList where code is null
        defaultListEnvPointShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where name equals to DEFAULT_NAME
        defaultListEnvPointShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listEnvPointList where name equals to UPDATED_NAME
        defaultListEnvPointShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListEnvPointShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listEnvPointList where name equals to UPDATED_NAME
        defaultListEnvPointShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where name is not null
        defaultListEnvPointShouldBeFound("name.specified=true");

        // Get all the listEnvPointList where name is null
        defaultListEnvPointShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where fullName equals to DEFAULT_FULL_NAME
        defaultListEnvPointShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listEnvPointList where fullName equals to UPDATED_FULL_NAME
        defaultListEnvPointShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListEnvPointShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listEnvPointList where fullName equals to UPDATED_FULL_NAME
        defaultListEnvPointShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where fullName is not null
        defaultListEnvPointShouldBeFound("fullName.specified=true");

        // Get all the listEnvPointList where fullName is null
        defaultListEnvPointShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDegreeStartIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where degreeStart equals to DEFAULT_DEGREE_START
        defaultListEnvPointShouldBeFound("degreeStart.equals=" + DEFAULT_DEGREE_START);

        // Get all the listEnvPointList where degreeStart equals to UPDATED_DEGREE_START
        defaultListEnvPointShouldNotBeFound("degreeStart.equals=" + UPDATED_DEGREE_START);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDegreeStartIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where degreeStart in DEFAULT_DEGREE_START or UPDATED_DEGREE_START
        defaultListEnvPointShouldBeFound("degreeStart.in=" + DEFAULT_DEGREE_START + "," + UPDATED_DEGREE_START);

        // Get all the listEnvPointList where degreeStart equals to UPDATED_DEGREE_START
        defaultListEnvPointShouldNotBeFound("degreeStart.in=" + UPDATED_DEGREE_START);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDegreeStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where degreeStart is not null
        defaultListEnvPointShouldBeFound("degreeStart.specified=true");

        // Get all the listEnvPointList where degreeStart is null
        defaultListEnvPointShouldNotBeFound("degreeStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDegreeEndIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where degreeEnd equals to DEFAULT_DEGREE_END
        defaultListEnvPointShouldBeFound("degreeEnd.equals=" + DEFAULT_DEGREE_END);

        // Get all the listEnvPointList where degreeEnd equals to UPDATED_DEGREE_END
        defaultListEnvPointShouldNotBeFound("degreeEnd.equals=" + UPDATED_DEGREE_END);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDegreeEndIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where degreeEnd in DEFAULT_DEGREE_END or UPDATED_DEGREE_END
        defaultListEnvPointShouldBeFound("degreeEnd.in=" + DEFAULT_DEGREE_END + "," + UPDATED_DEGREE_END);

        // Get all the listEnvPointList where degreeEnd equals to UPDATED_DEGREE_END
        defaultListEnvPointShouldNotBeFound("degreeEnd.in=" + UPDATED_DEGREE_END);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDegreeEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where degreeEnd is not null
        defaultListEnvPointShouldBeFound("degreeEnd.specified=true");

        // Get all the listEnvPointList where degreeEnd is null
        defaultListEnvPointShouldNotBeFound("degreeEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEnvPointShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEnvPointList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvPointShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListEnvPointShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listEnvPointList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvPointShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where isCurrentFlag is not null
        defaultListEnvPointShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listEnvPointList where isCurrentFlag is null
        defaultListEnvPointShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEnvPointShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEnvPointList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvPointShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEnvPointShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEnvPointList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvPointShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListEnvPointsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where description equals to DEFAULT_DESCRIPTION
        defaultListEnvPointShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listEnvPointList where description equals to UPDATED_DESCRIPTION
        defaultListEnvPointShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListEnvPointShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listEnvPointList where description equals to UPDATED_DESCRIPTION
        defaultListEnvPointShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where description is not null
        defaultListEnvPointShouldBeFound("description.specified=true");

        // Get all the listEnvPointList where description is null
        defaultListEnvPointShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListEnvPointShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listEnvPointList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListEnvPointShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListEnvPointShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listEnvPointList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListEnvPointShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where dateCreate is not null
        defaultListEnvPointShouldBeFound("dateCreate.specified=true");

        // Get all the listEnvPointList where dateCreate is null
        defaultListEnvPointShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListEnvPointShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listEnvPointList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListEnvPointShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListEnvPointShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listEnvPointList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListEnvPointShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where dateEdit is not null
        defaultListEnvPointShouldBeFound("dateEdit.specified=true");

        // Get all the listEnvPointList where dateEdit is null
        defaultListEnvPointShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where creator equals to DEFAULT_CREATOR
        defaultListEnvPointShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listEnvPointList where creator equals to UPDATED_CREATOR
        defaultListEnvPointShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListEnvPointShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listEnvPointList where creator equals to UPDATED_CREATOR
        defaultListEnvPointShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where creator is not null
        defaultListEnvPointShouldBeFound("creator.specified=true");

        // Get all the listEnvPointList where creator is null
        defaultListEnvPointShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where editor equals to DEFAULT_EDITOR
        defaultListEnvPointShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listEnvPointList where editor equals to UPDATED_EDITOR
        defaultListEnvPointShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListEnvPointShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listEnvPointList where editor equals to UPDATED_EDITOR
        defaultListEnvPointShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListEnvPointsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        // Get all the listEnvPointList where editor is not null
        defaultListEnvPointShouldBeFound("editor.specified=true");

        // Get all the listEnvPointList where editor is null
        defaultListEnvPointShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListEnvPointShouldBeFound(String filter) throws Exception {
        restListEnvPointMockMvc.perform(get("/api/list-env-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listEnvPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].degreeStart").value(hasItem(DEFAULT_DEGREE_START.intValue())))
            .andExpect(jsonPath("$.[*].degreeEnd").value(hasItem(DEFAULT_DEGREE_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListEnvPointMockMvc.perform(get("/api/list-env-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListEnvPointShouldNotBeFound(String filter) throws Exception {
        restListEnvPointMockMvc.perform(get("/api/list-env-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListEnvPointMockMvc.perform(get("/api/list-env-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListEnvPoint() throws Exception {
        // Get the listEnvPoint
        restListEnvPointMockMvc.perform(get("/api/list-env-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListEnvPoint() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        int databaseSizeBeforeUpdate = listEnvPointRepository.findAll().size();

        // Update the listEnvPoint
        ListEnvPoint updatedListEnvPoint = listEnvPointRepository.findById(listEnvPoint.getId()).get();
        // Disconnect from session so that the updates on updatedListEnvPoint are not directly saved in db
        em.detach(updatedListEnvPoint);
        updatedListEnvPoint
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .degreeStart(UPDATED_DEGREE_START)
            .degreeEnd(UPDATED_DEGREE_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(updatedListEnvPoint);

        restListEnvPointMockMvc.perform(put("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isOk());

        // Validate the ListEnvPoint in the database
        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeUpdate);
        ListEnvPoint testListEnvPoint = listEnvPointList.get(listEnvPointList.size() - 1);
        assertThat(testListEnvPoint.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListEnvPoint.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListEnvPoint.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListEnvPoint.getDegreeStart()).isEqualTo(UPDATED_DEGREE_START);
        assertThat(testListEnvPoint.getDegreeEnd()).isEqualTo(UPDATED_DEGREE_END);
        assertThat(testListEnvPoint.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListEnvPoint.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListEnvPoint.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListEnvPoint.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListEnvPoint.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListEnvPoint.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListEnvPoint() throws Exception {
        int databaseSizeBeforeUpdate = listEnvPointRepository.findAll().size();

        // Create the ListEnvPoint
        ListEnvPointDTO listEnvPointDTO = listEnvPointMapper.toDto(listEnvPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListEnvPointMockMvc.perform(put("/api/list-env-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListEnvPoint in the database
        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListEnvPoint() throws Exception {
        // Initialize the database
        listEnvPointRepository.saveAndFlush(listEnvPoint);

        int databaseSizeBeforeDelete = listEnvPointRepository.findAll().size();

        // Delete the listEnvPoint
        restListEnvPointMockMvc.perform(delete("/api/list-env-points/{id}", listEnvPoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListEnvPoint> listEnvPointList = listEnvPointRepository.findAll();
        assertThat(listEnvPointList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListEnvPoint.class);
        ListEnvPoint listEnvPoint1 = new ListEnvPoint();
        listEnvPoint1.setId(1L);
        ListEnvPoint listEnvPoint2 = new ListEnvPoint();
        listEnvPoint2.setId(listEnvPoint1.getId());
        assertThat(listEnvPoint1).isEqualTo(listEnvPoint2);
        listEnvPoint2.setId(2L);
        assertThat(listEnvPoint1).isNotEqualTo(listEnvPoint2);
        listEnvPoint1.setId(null);
        assertThat(listEnvPoint1).isNotEqualTo(listEnvPoint2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListEnvPointDTO.class);
        ListEnvPointDTO listEnvPointDTO1 = new ListEnvPointDTO();
        listEnvPointDTO1.setId(1L);
        ListEnvPointDTO listEnvPointDTO2 = new ListEnvPointDTO();
        assertThat(listEnvPointDTO1).isNotEqualTo(listEnvPointDTO2);
        listEnvPointDTO2.setId(listEnvPointDTO1.getId());
        assertThat(listEnvPointDTO1).isEqualTo(listEnvPointDTO2);
        listEnvPointDTO2.setId(2L);
        assertThat(listEnvPointDTO1).isNotEqualTo(listEnvPointDTO2);
        listEnvPointDTO1.setId(null);
        assertThat(listEnvPointDTO1).isNotEqualTo(listEnvPointDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listEnvPointMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listEnvPointMapper.fromId(null)).isNull();
    }
}
