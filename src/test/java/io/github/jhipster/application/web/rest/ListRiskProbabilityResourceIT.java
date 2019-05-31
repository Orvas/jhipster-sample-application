package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListRiskProbability;
import io.github.jhipster.application.repository.ListRiskProbabilityRepository;
import io.github.jhipster.application.service.ListRiskProbabilityService;
import io.github.jhipster.application.service.dto.ListRiskProbabilityDTO;
import io.github.jhipster.application.service.mapper.ListRiskProbabilityMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListRiskProbabilityCriteria;
import io.github.jhipster.application.service.ListRiskProbabilityQueryService;

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
 * Integration tests for the {@Link ListRiskProbabilityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListRiskProbabilityResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

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
    private ListRiskProbabilityRepository listRiskProbabilityRepository;

    @Autowired
    private ListRiskProbabilityMapper listRiskProbabilityMapper;

    @Autowired
    private ListRiskProbabilityService listRiskProbabilityService;

    @Autowired
    private ListRiskProbabilityQueryService listRiskProbabilityQueryService;

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

    private MockMvc restListRiskProbabilityMockMvc;

    private ListRiskProbability listRiskProbability;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListRiskProbabilityResource listRiskProbabilityResource = new ListRiskProbabilityResource(listRiskProbabilityService, listRiskProbabilityQueryService);
        this.restListRiskProbabilityMockMvc = MockMvcBuilders.standaloneSetup(listRiskProbabilityResource)
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
    public static ListRiskProbability createEntity(EntityManager em) {
        ListRiskProbability listRiskProbability = new ListRiskProbability()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listRiskProbability;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListRiskProbability createUpdatedEntity(EntityManager em) {
        ListRiskProbability listRiskProbability = new ListRiskProbability()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listRiskProbability;
    }

    @BeforeEach
    public void initTest() {
        listRiskProbability = createEntity(em);
    }

    @Test
    @Transactional
    public void createListRiskProbability() throws Exception {
        int databaseSizeBeforeCreate = listRiskProbabilityRepository.findAll().size();

        // Create the ListRiskProbability
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(listRiskProbability);
        restListRiskProbabilityMockMvc.perform(post("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isCreated());

        // Validate the ListRiskProbability in the database
        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeCreate + 1);
        ListRiskProbability testListRiskProbability = listRiskProbabilityList.get(listRiskProbabilityList.size() - 1);
        assertThat(testListRiskProbability.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListRiskProbability.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListRiskProbability.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListRiskProbability.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListRiskProbability.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListRiskProbability.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListRiskProbability.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListRiskProbability.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListRiskProbability.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListRiskProbabilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listRiskProbabilityRepository.findAll().size();

        // Create the ListRiskProbability with an existing ID
        listRiskProbability.setId(1L);
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(listRiskProbability);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListRiskProbabilityMockMvc.perform(post("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListRiskProbability in the database
        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskProbabilityRepository.findAll().size();
        // set the field null
        listRiskProbability.setCode(null);

        // Create the ListRiskProbability, which fails.
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(listRiskProbability);

        restListRiskProbabilityMockMvc.perform(post("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskProbabilityRepository.findAll().size();
        // set the field null
        listRiskProbability.setName(null);

        // Create the ListRiskProbability, which fails.
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(listRiskProbability);

        restListRiskProbabilityMockMvc.perform(post("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskProbabilityRepository.findAll().size();
        // set the field null
        listRiskProbability.setFullName(null);

        // Create the ListRiskProbability, which fails.
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(listRiskProbability);

        restListRiskProbabilityMockMvc.perform(post("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskProbabilityRepository.findAll().size();
        // set the field null
        listRiskProbability.setIsCurrentFlag(null);

        // Create the ListRiskProbability, which fails.
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(listRiskProbability);

        restListRiskProbabilityMockMvc.perform(post("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilities() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList
        restListRiskProbabilityMockMvc.perform(get("/api/list-risk-probabilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listRiskProbability.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListRiskProbability() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get the listRiskProbability
        restListRiskProbabilityMockMvc.perform(get("/api/list-risk-probabilities/{id}", listRiskProbability.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listRiskProbability.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where code equals to DEFAULT_CODE
        defaultListRiskProbabilityShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listRiskProbabilityList where code equals to UPDATED_CODE
        defaultListRiskProbabilityShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListRiskProbabilityShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listRiskProbabilityList where code equals to UPDATED_CODE
        defaultListRiskProbabilityShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where code is not null
        defaultListRiskProbabilityShouldBeFound("code.specified=true");

        // Get all the listRiskProbabilityList where code is null
        defaultListRiskProbabilityShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where name equals to DEFAULT_NAME
        defaultListRiskProbabilityShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listRiskProbabilityList where name equals to UPDATED_NAME
        defaultListRiskProbabilityShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListRiskProbabilityShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listRiskProbabilityList where name equals to UPDATED_NAME
        defaultListRiskProbabilityShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where name is not null
        defaultListRiskProbabilityShouldBeFound("name.specified=true");

        // Get all the listRiskProbabilityList where name is null
        defaultListRiskProbabilityShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where fullName equals to DEFAULT_FULL_NAME
        defaultListRiskProbabilityShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listRiskProbabilityList where fullName equals to UPDATED_FULL_NAME
        defaultListRiskProbabilityShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListRiskProbabilityShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listRiskProbabilityList where fullName equals to UPDATED_FULL_NAME
        defaultListRiskProbabilityShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where fullName is not null
        defaultListRiskProbabilityShouldBeFound("fullName.specified=true");

        // Get all the listRiskProbabilityList where fullName is null
        defaultListRiskProbabilityShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listRiskProbabilityList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listRiskProbabilityList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where isCurrentFlag is not null
        defaultListRiskProbabilityShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listRiskProbabilityList where isCurrentFlag is null
        defaultListRiskProbabilityShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listRiskProbabilityList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listRiskProbabilityList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskProbabilityShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where description equals to DEFAULT_DESCRIPTION
        defaultListRiskProbabilityShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listRiskProbabilityList where description equals to UPDATED_DESCRIPTION
        defaultListRiskProbabilityShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListRiskProbabilityShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listRiskProbabilityList where description equals to UPDATED_DESCRIPTION
        defaultListRiskProbabilityShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where description is not null
        defaultListRiskProbabilityShouldBeFound("description.specified=true");

        // Get all the listRiskProbabilityList where description is null
        defaultListRiskProbabilityShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListRiskProbabilityShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listRiskProbabilityList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListRiskProbabilityShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListRiskProbabilityShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listRiskProbabilityList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListRiskProbabilityShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where dateCreate is not null
        defaultListRiskProbabilityShouldBeFound("dateCreate.specified=true");

        // Get all the listRiskProbabilityList where dateCreate is null
        defaultListRiskProbabilityShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListRiskProbabilityShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listRiskProbabilityList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListRiskProbabilityShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListRiskProbabilityShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listRiskProbabilityList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListRiskProbabilityShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where dateEdit is not null
        defaultListRiskProbabilityShouldBeFound("dateEdit.specified=true");

        // Get all the listRiskProbabilityList where dateEdit is null
        defaultListRiskProbabilityShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where creator equals to DEFAULT_CREATOR
        defaultListRiskProbabilityShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listRiskProbabilityList where creator equals to UPDATED_CREATOR
        defaultListRiskProbabilityShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListRiskProbabilityShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listRiskProbabilityList where creator equals to UPDATED_CREATOR
        defaultListRiskProbabilityShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where creator is not null
        defaultListRiskProbabilityShouldBeFound("creator.specified=true");

        // Get all the listRiskProbabilityList where creator is null
        defaultListRiskProbabilityShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where editor equals to DEFAULT_EDITOR
        defaultListRiskProbabilityShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listRiskProbabilityList where editor equals to UPDATED_EDITOR
        defaultListRiskProbabilityShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListRiskProbabilityShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listRiskProbabilityList where editor equals to UPDATED_EDITOR
        defaultListRiskProbabilityShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListRiskProbabilitiesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        // Get all the listRiskProbabilityList where editor is not null
        defaultListRiskProbabilityShouldBeFound("editor.specified=true");

        // Get all the listRiskProbabilityList where editor is null
        defaultListRiskProbabilityShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListRiskProbabilityShouldBeFound(String filter) throws Exception {
        restListRiskProbabilityMockMvc.perform(get("/api/list-risk-probabilities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listRiskProbability.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListRiskProbabilityMockMvc.perform(get("/api/list-risk-probabilities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListRiskProbabilityShouldNotBeFound(String filter) throws Exception {
        restListRiskProbabilityMockMvc.perform(get("/api/list-risk-probabilities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListRiskProbabilityMockMvc.perform(get("/api/list-risk-probabilities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListRiskProbability() throws Exception {
        // Get the listRiskProbability
        restListRiskProbabilityMockMvc.perform(get("/api/list-risk-probabilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListRiskProbability() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        int databaseSizeBeforeUpdate = listRiskProbabilityRepository.findAll().size();

        // Update the listRiskProbability
        ListRiskProbability updatedListRiskProbability = listRiskProbabilityRepository.findById(listRiskProbability.getId()).get();
        // Disconnect from session so that the updates on updatedListRiskProbability are not directly saved in db
        em.detach(updatedListRiskProbability);
        updatedListRiskProbability
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(updatedListRiskProbability);

        restListRiskProbabilityMockMvc.perform(put("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isOk());

        // Validate the ListRiskProbability in the database
        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeUpdate);
        ListRiskProbability testListRiskProbability = listRiskProbabilityList.get(listRiskProbabilityList.size() - 1);
        assertThat(testListRiskProbability.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListRiskProbability.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListRiskProbability.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListRiskProbability.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListRiskProbability.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListRiskProbability.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListRiskProbability.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListRiskProbability.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListRiskProbability.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListRiskProbability() throws Exception {
        int databaseSizeBeforeUpdate = listRiskProbabilityRepository.findAll().size();

        // Create the ListRiskProbability
        ListRiskProbabilityDTO listRiskProbabilityDTO = listRiskProbabilityMapper.toDto(listRiskProbability);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListRiskProbabilityMockMvc.perform(put("/api/list-risk-probabilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskProbabilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListRiskProbability in the database
        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListRiskProbability() throws Exception {
        // Initialize the database
        listRiskProbabilityRepository.saveAndFlush(listRiskProbability);

        int databaseSizeBeforeDelete = listRiskProbabilityRepository.findAll().size();

        // Delete the listRiskProbability
        restListRiskProbabilityMockMvc.perform(delete("/api/list-risk-probabilities/{id}", listRiskProbability.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListRiskProbability> listRiskProbabilityList = listRiskProbabilityRepository.findAll();
        assertThat(listRiskProbabilityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListRiskProbability.class);
        ListRiskProbability listRiskProbability1 = new ListRiskProbability();
        listRiskProbability1.setId(1L);
        ListRiskProbability listRiskProbability2 = new ListRiskProbability();
        listRiskProbability2.setId(listRiskProbability1.getId());
        assertThat(listRiskProbability1).isEqualTo(listRiskProbability2);
        listRiskProbability2.setId(2L);
        assertThat(listRiskProbability1).isNotEqualTo(listRiskProbability2);
        listRiskProbability1.setId(null);
        assertThat(listRiskProbability1).isNotEqualTo(listRiskProbability2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListRiskProbabilityDTO.class);
        ListRiskProbabilityDTO listRiskProbabilityDTO1 = new ListRiskProbabilityDTO();
        listRiskProbabilityDTO1.setId(1L);
        ListRiskProbabilityDTO listRiskProbabilityDTO2 = new ListRiskProbabilityDTO();
        assertThat(listRiskProbabilityDTO1).isNotEqualTo(listRiskProbabilityDTO2);
        listRiskProbabilityDTO2.setId(listRiskProbabilityDTO1.getId());
        assertThat(listRiskProbabilityDTO1).isEqualTo(listRiskProbabilityDTO2);
        listRiskProbabilityDTO2.setId(2L);
        assertThat(listRiskProbabilityDTO1).isNotEqualTo(listRiskProbabilityDTO2);
        listRiskProbabilityDTO1.setId(null);
        assertThat(listRiskProbabilityDTO1).isNotEqualTo(listRiskProbabilityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listRiskProbabilityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listRiskProbabilityMapper.fromId(null)).isNull();
    }
}
