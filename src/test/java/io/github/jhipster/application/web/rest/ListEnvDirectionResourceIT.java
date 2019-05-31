package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListEnvDirection;
import io.github.jhipster.application.repository.ListEnvDirectionRepository;
import io.github.jhipster.application.service.ListEnvDirectionService;
import io.github.jhipster.application.service.dto.ListEnvDirectionDTO;
import io.github.jhipster.application.service.mapper.ListEnvDirectionMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListEnvDirectionCriteria;
import io.github.jhipster.application.service.ListEnvDirectionQueryService;

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
 * Integration tests for the {@Link ListEnvDirectionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListEnvDirectionResourceIT {

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
    private ListEnvDirectionRepository listEnvDirectionRepository;

    @Autowired
    private ListEnvDirectionMapper listEnvDirectionMapper;

    @Autowired
    private ListEnvDirectionService listEnvDirectionService;

    @Autowired
    private ListEnvDirectionQueryService listEnvDirectionQueryService;

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

    private MockMvc restListEnvDirectionMockMvc;

    private ListEnvDirection listEnvDirection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListEnvDirectionResource listEnvDirectionResource = new ListEnvDirectionResource(listEnvDirectionService, listEnvDirectionQueryService);
        this.restListEnvDirectionMockMvc = MockMvcBuilders.standaloneSetup(listEnvDirectionResource)
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
    public static ListEnvDirection createEntity(EntityManager em) {
        ListEnvDirection listEnvDirection = new ListEnvDirection()
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
        return listEnvDirection;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListEnvDirection createUpdatedEntity(EntityManager em) {
        ListEnvDirection listEnvDirection = new ListEnvDirection()
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
        return listEnvDirection;
    }

    @BeforeEach
    public void initTest() {
        listEnvDirection = createEntity(em);
    }

    @Test
    @Transactional
    public void createListEnvDirection() throws Exception {
        int databaseSizeBeforeCreate = listEnvDirectionRepository.findAll().size();

        // Create the ListEnvDirection
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);
        restListEnvDirectionMockMvc.perform(post("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isCreated());

        // Validate the ListEnvDirection in the database
        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeCreate + 1);
        ListEnvDirection testListEnvDirection = listEnvDirectionList.get(listEnvDirectionList.size() - 1);
        assertThat(testListEnvDirection.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListEnvDirection.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListEnvDirection.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListEnvDirection.getDegreeStart()).isEqualTo(DEFAULT_DEGREE_START);
        assertThat(testListEnvDirection.getDegreeEnd()).isEqualTo(DEFAULT_DEGREE_END);
        assertThat(testListEnvDirection.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListEnvDirection.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListEnvDirection.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListEnvDirection.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListEnvDirection.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListEnvDirection.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListEnvDirectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listEnvDirectionRepository.findAll().size();

        // Create the ListEnvDirection with an existing ID
        listEnvDirection.setId(1L);
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListEnvDirectionMockMvc.perform(post("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListEnvDirection in the database
        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvDirectionRepository.findAll().size();
        // set the field null
        listEnvDirection.setCode(null);

        // Create the ListEnvDirection, which fails.
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);

        restListEnvDirectionMockMvc.perform(post("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvDirectionRepository.findAll().size();
        // set the field null
        listEnvDirection.setName(null);

        // Create the ListEnvDirection, which fails.
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);

        restListEnvDirectionMockMvc.perform(post("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvDirectionRepository.findAll().size();
        // set the field null
        listEnvDirection.setFullName(null);

        // Create the ListEnvDirection, which fails.
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);

        restListEnvDirectionMockMvc.perform(post("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDegreeStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvDirectionRepository.findAll().size();
        // set the field null
        listEnvDirection.setDegreeStart(null);

        // Create the ListEnvDirection, which fails.
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);

        restListEnvDirectionMockMvc.perform(post("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEnvDirectionRepository.findAll().size();
        // set the field null
        listEnvDirection.setIsCurrentFlag(null);

        // Create the ListEnvDirection, which fails.
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);

        restListEnvDirectionMockMvc.perform(post("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isBadRequest());

        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListEnvDirections() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList
        restListEnvDirectionMockMvc.perform(get("/api/list-env-directions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listEnvDirection.getId().intValue())))
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
    public void getListEnvDirection() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get the listEnvDirection
        restListEnvDirectionMockMvc.perform(get("/api/list-env-directions/{id}", listEnvDirection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listEnvDirection.getId().intValue()))
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
    public void getAllListEnvDirectionsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where code equals to DEFAULT_CODE
        defaultListEnvDirectionShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listEnvDirectionList where code equals to UPDATED_CODE
        defaultListEnvDirectionShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListEnvDirectionShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listEnvDirectionList where code equals to UPDATED_CODE
        defaultListEnvDirectionShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where code is not null
        defaultListEnvDirectionShouldBeFound("code.specified=true");

        // Get all the listEnvDirectionList where code is null
        defaultListEnvDirectionShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where name equals to DEFAULT_NAME
        defaultListEnvDirectionShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listEnvDirectionList where name equals to UPDATED_NAME
        defaultListEnvDirectionShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListEnvDirectionShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listEnvDirectionList where name equals to UPDATED_NAME
        defaultListEnvDirectionShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where name is not null
        defaultListEnvDirectionShouldBeFound("name.specified=true");

        // Get all the listEnvDirectionList where name is null
        defaultListEnvDirectionShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where fullName equals to DEFAULT_FULL_NAME
        defaultListEnvDirectionShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listEnvDirectionList where fullName equals to UPDATED_FULL_NAME
        defaultListEnvDirectionShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListEnvDirectionShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listEnvDirectionList where fullName equals to UPDATED_FULL_NAME
        defaultListEnvDirectionShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where fullName is not null
        defaultListEnvDirectionShouldBeFound("fullName.specified=true");

        // Get all the listEnvDirectionList where fullName is null
        defaultListEnvDirectionShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDegreeStartIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where degreeStart equals to DEFAULT_DEGREE_START
        defaultListEnvDirectionShouldBeFound("degreeStart.equals=" + DEFAULT_DEGREE_START);

        // Get all the listEnvDirectionList where degreeStart equals to UPDATED_DEGREE_START
        defaultListEnvDirectionShouldNotBeFound("degreeStart.equals=" + UPDATED_DEGREE_START);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDegreeStartIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where degreeStart in DEFAULT_DEGREE_START or UPDATED_DEGREE_START
        defaultListEnvDirectionShouldBeFound("degreeStart.in=" + DEFAULT_DEGREE_START + "," + UPDATED_DEGREE_START);

        // Get all the listEnvDirectionList where degreeStart equals to UPDATED_DEGREE_START
        defaultListEnvDirectionShouldNotBeFound("degreeStart.in=" + UPDATED_DEGREE_START);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDegreeStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where degreeStart is not null
        defaultListEnvDirectionShouldBeFound("degreeStart.specified=true");

        // Get all the listEnvDirectionList where degreeStart is null
        defaultListEnvDirectionShouldNotBeFound("degreeStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDegreeEndIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where degreeEnd equals to DEFAULT_DEGREE_END
        defaultListEnvDirectionShouldBeFound("degreeEnd.equals=" + DEFAULT_DEGREE_END);

        // Get all the listEnvDirectionList where degreeEnd equals to UPDATED_DEGREE_END
        defaultListEnvDirectionShouldNotBeFound("degreeEnd.equals=" + UPDATED_DEGREE_END);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDegreeEndIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where degreeEnd in DEFAULT_DEGREE_END or UPDATED_DEGREE_END
        defaultListEnvDirectionShouldBeFound("degreeEnd.in=" + DEFAULT_DEGREE_END + "," + UPDATED_DEGREE_END);

        // Get all the listEnvDirectionList where degreeEnd equals to UPDATED_DEGREE_END
        defaultListEnvDirectionShouldNotBeFound("degreeEnd.in=" + UPDATED_DEGREE_END);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDegreeEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where degreeEnd is not null
        defaultListEnvDirectionShouldBeFound("degreeEnd.specified=true");

        // Get all the listEnvDirectionList where degreeEnd is null
        defaultListEnvDirectionShouldNotBeFound("degreeEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEnvDirectionList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listEnvDirectionList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where isCurrentFlag is not null
        defaultListEnvDirectionShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listEnvDirectionList where isCurrentFlag is null
        defaultListEnvDirectionShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEnvDirectionList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEnvDirectionList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListEnvDirectionShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListEnvDirectionsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where description equals to DEFAULT_DESCRIPTION
        defaultListEnvDirectionShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listEnvDirectionList where description equals to UPDATED_DESCRIPTION
        defaultListEnvDirectionShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListEnvDirectionShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listEnvDirectionList where description equals to UPDATED_DESCRIPTION
        defaultListEnvDirectionShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where description is not null
        defaultListEnvDirectionShouldBeFound("description.specified=true");

        // Get all the listEnvDirectionList where description is null
        defaultListEnvDirectionShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListEnvDirectionShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listEnvDirectionList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListEnvDirectionShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListEnvDirectionShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listEnvDirectionList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListEnvDirectionShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where dateCreate is not null
        defaultListEnvDirectionShouldBeFound("dateCreate.specified=true");

        // Get all the listEnvDirectionList where dateCreate is null
        defaultListEnvDirectionShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListEnvDirectionShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listEnvDirectionList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListEnvDirectionShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListEnvDirectionShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listEnvDirectionList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListEnvDirectionShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where dateEdit is not null
        defaultListEnvDirectionShouldBeFound("dateEdit.specified=true");

        // Get all the listEnvDirectionList where dateEdit is null
        defaultListEnvDirectionShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where creator equals to DEFAULT_CREATOR
        defaultListEnvDirectionShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listEnvDirectionList where creator equals to UPDATED_CREATOR
        defaultListEnvDirectionShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListEnvDirectionShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listEnvDirectionList where creator equals to UPDATED_CREATOR
        defaultListEnvDirectionShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where creator is not null
        defaultListEnvDirectionShouldBeFound("creator.specified=true");

        // Get all the listEnvDirectionList where creator is null
        defaultListEnvDirectionShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where editor equals to DEFAULT_EDITOR
        defaultListEnvDirectionShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listEnvDirectionList where editor equals to UPDATED_EDITOR
        defaultListEnvDirectionShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListEnvDirectionShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listEnvDirectionList where editor equals to UPDATED_EDITOR
        defaultListEnvDirectionShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListEnvDirectionsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        // Get all the listEnvDirectionList where editor is not null
        defaultListEnvDirectionShouldBeFound("editor.specified=true");

        // Get all the listEnvDirectionList where editor is null
        defaultListEnvDirectionShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListEnvDirectionShouldBeFound(String filter) throws Exception {
        restListEnvDirectionMockMvc.perform(get("/api/list-env-directions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listEnvDirection.getId().intValue())))
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
        restListEnvDirectionMockMvc.perform(get("/api/list-env-directions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListEnvDirectionShouldNotBeFound(String filter) throws Exception {
        restListEnvDirectionMockMvc.perform(get("/api/list-env-directions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListEnvDirectionMockMvc.perform(get("/api/list-env-directions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListEnvDirection() throws Exception {
        // Get the listEnvDirection
        restListEnvDirectionMockMvc.perform(get("/api/list-env-directions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListEnvDirection() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        int databaseSizeBeforeUpdate = listEnvDirectionRepository.findAll().size();

        // Update the listEnvDirection
        ListEnvDirection updatedListEnvDirection = listEnvDirectionRepository.findById(listEnvDirection.getId()).get();
        // Disconnect from session so that the updates on updatedListEnvDirection are not directly saved in db
        em.detach(updatedListEnvDirection);
        updatedListEnvDirection
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
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(updatedListEnvDirection);

        restListEnvDirectionMockMvc.perform(put("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isOk());

        // Validate the ListEnvDirection in the database
        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeUpdate);
        ListEnvDirection testListEnvDirection = listEnvDirectionList.get(listEnvDirectionList.size() - 1);
        assertThat(testListEnvDirection.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListEnvDirection.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListEnvDirection.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListEnvDirection.getDegreeStart()).isEqualTo(UPDATED_DEGREE_START);
        assertThat(testListEnvDirection.getDegreeEnd()).isEqualTo(UPDATED_DEGREE_END);
        assertThat(testListEnvDirection.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListEnvDirection.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListEnvDirection.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListEnvDirection.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListEnvDirection.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListEnvDirection.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListEnvDirection() throws Exception {
        int databaseSizeBeforeUpdate = listEnvDirectionRepository.findAll().size();

        // Create the ListEnvDirection
        ListEnvDirectionDTO listEnvDirectionDTO = listEnvDirectionMapper.toDto(listEnvDirection);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListEnvDirectionMockMvc.perform(put("/api/list-env-directions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEnvDirectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListEnvDirection in the database
        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListEnvDirection() throws Exception {
        // Initialize the database
        listEnvDirectionRepository.saveAndFlush(listEnvDirection);

        int databaseSizeBeforeDelete = listEnvDirectionRepository.findAll().size();

        // Delete the listEnvDirection
        restListEnvDirectionMockMvc.perform(delete("/api/list-env-directions/{id}", listEnvDirection.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListEnvDirection> listEnvDirectionList = listEnvDirectionRepository.findAll();
        assertThat(listEnvDirectionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListEnvDirection.class);
        ListEnvDirection listEnvDirection1 = new ListEnvDirection();
        listEnvDirection1.setId(1L);
        ListEnvDirection listEnvDirection2 = new ListEnvDirection();
        listEnvDirection2.setId(listEnvDirection1.getId());
        assertThat(listEnvDirection1).isEqualTo(listEnvDirection2);
        listEnvDirection2.setId(2L);
        assertThat(listEnvDirection1).isNotEqualTo(listEnvDirection2);
        listEnvDirection1.setId(null);
        assertThat(listEnvDirection1).isNotEqualTo(listEnvDirection2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListEnvDirectionDTO.class);
        ListEnvDirectionDTO listEnvDirectionDTO1 = new ListEnvDirectionDTO();
        listEnvDirectionDTO1.setId(1L);
        ListEnvDirectionDTO listEnvDirectionDTO2 = new ListEnvDirectionDTO();
        assertThat(listEnvDirectionDTO1).isNotEqualTo(listEnvDirectionDTO2);
        listEnvDirectionDTO2.setId(listEnvDirectionDTO1.getId());
        assertThat(listEnvDirectionDTO1).isEqualTo(listEnvDirectionDTO2);
        listEnvDirectionDTO2.setId(2L);
        assertThat(listEnvDirectionDTO1).isNotEqualTo(listEnvDirectionDTO2);
        listEnvDirectionDTO1.setId(null);
        assertThat(listEnvDirectionDTO1).isNotEqualTo(listEnvDirectionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listEnvDirectionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listEnvDirectionMapper.fromId(null)).isNull();
    }
}
