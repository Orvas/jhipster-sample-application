package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListWrkPurpose;
import io.github.jhipster.application.repository.ListWrkPurposeRepository;
import io.github.jhipster.application.service.ListWrkPurposeService;
import io.github.jhipster.application.service.dto.ListWrkPurposeDTO;
import io.github.jhipster.application.service.mapper.ListWrkPurposeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListWrkPurposeCriteria;
import io.github.jhipster.application.service.ListWrkPurposeQueryService;

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
 * Integration tests for the {@Link ListWrkPurposeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListWrkPurposeResourceIT {

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
    private ListWrkPurposeRepository listWrkPurposeRepository;

    @Autowired
    private ListWrkPurposeMapper listWrkPurposeMapper;

    @Autowired
    private ListWrkPurposeService listWrkPurposeService;

    @Autowired
    private ListWrkPurposeQueryService listWrkPurposeQueryService;

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

    private MockMvc restListWrkPurposeMockMvc;

    private ListWrkPurpose listWrkPurpose;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListWrkPurposeResource listWrkPurposeResource = new ListWrkPurposeResource(listWrkPurposeService, listWrkPurposeQueryService);
        this.restListWrkPurposeMockMvc = MockMvcBuilders.standaloneSetup(listWrkPurposeResource)
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
    public static ListWrkPurpose createEntity(EntityManager em) {
        ListWrkPurpose listWrkPurpose = new ListWrkPurpose()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listWrkPurpose;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListWrkPurpose createUpdatedEntity(EntityManager em) {
        ListWrkPurpose listWrkPurpose = new ListWrkPurpose()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listWrkPurpose;
    }

    @BeforeEach
    public void initTest() {
        listWrkPurpose = createEntity(em);
    }

    @Test
    @Transactional
    public void createListWrkPurpose() throws Exception {
        int databaseSizeBeforeCreate = listWrkPurposeRepository.findAll().size();

        // Create the ListWrkPurpose
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(listWrkPurpose);
        restListWrkPurposeMockMvc.perform(post("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListWrkPurpose in the database
        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeCreate + 1);
        ListWrkPurpose testListWrkPurpose = listWrkPurposeList.get(listWrkPurposeList.size() - 1);
        assertThat(testListWrkPurpose.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListWrkPurpose.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListWrkPurpose.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListWrkPurpose.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListWrkPurpose.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListWrkPurpose.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListWrkPurpose.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListWrkPurpose.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListWrkPurpose.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListWrkPurposeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listWrkPurposeRepository.findAll().size();

        // Create the ListWrkPurpose with an existing ID
        listWrkPurpose.setId(1L);
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(listWrkPurpose);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListWrkPurposeMockMvc.perform(post("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkPurpose in the database
        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkPurposeRepository.findAll().size();
        // set the field null
        listWrkPurpose.setCode(null);

        // Create the ListWrkPurpose, which fails.
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(listWrkPurpose);

        restListWrkPurposeMockMvc.perform(post("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkPurposeRepository.findAll().size();
        // set the field null
        listWrkPurpose.setName(null);

        // Create the ListWrkPurpose, which fails.
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(listWrkPurpose);

        restListWrkPurposeMockMvc.perform(post("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkPurposeRepository.findAll().size();
        // set the field null
        listWrkPurpose.setFullName(null);

        // Create the ListWrkPurpose, which fails.
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(listWrkPurpose);

        restListWrkPurposeMockMvc.perform(post("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkPurposeRepository.findAll().size();
        // set the field null
        listWrkPurpose.setIsCurrentFlag(null);

        // Create the ListWrkPurpose, which fails.
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(listWrkPurpose);

        restListWrkPurposeMockMvc.perform(post("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposes() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList
        restListWrkPurposeMockMvc.perform(get("/api/list-wrk-purposes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkPurpose.getId().intValue())))
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
    public void getListWrkPurpose() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get the listWrkPurpose
        restListWrkPurposeMockMvc.perform(get("/api/list-wrk-purposes/{id}", listWrkPurpose.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listWrkPurpose.getId().intValue()))
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
    public void getAllListWrkPurposesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where code equals to DEFAULT_CODE
        defaultListWrkPurposeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listWrkPurposeList where code equals to UPDATED_CODE
        defaultListWrkPurposeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListWrkPurposeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listWrkPurposeList where code equals to UPDATED_CODE
        defaultListWrkPurposeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where code is not null
        defaultListWrkPurposeShouldBeFound("code.specified=true");

        // Get all the listWrkPurposeList where code is null
        defaultListWrkPurposeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where name equals to DEFAULT_NAME
        defaultListWrkPurposeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listWrkPurposeList where name equals to UPDATED_NAME
        defaultListWrkPurposeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListWrkPurposeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listWrkPurposeList where name equals to UPDATED_NAME
        defaultListWrkPurposeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where name is not null
        defaultListWrkPurposeShouldBeFound("name.specified=true");

        // Get all the listWrkPurposeList where name is null
        defaultListWrkPurposeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where fullName equals to DEFAULT_FULL_NAME
        defaultListWrkPurposeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listWrkPurposeList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkPurposeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListWrkPurposeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listWrkPurposeList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkPurposeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where fullName is not null
        defaultListWrkPurposeShouldBeFound("fullName.specified=true");

        // Get all the listWrkPurposeList where fullName is null
        defaultListWrkPurposeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkPurposeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listWrkPurposeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where isCurrentFlag is not null
        defaultListWrkPurposeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listWrkPurposeList where isCurrentFlag is null
        defaultListWrkPurposeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkPurposeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkPurposeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkPurposeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListWrkPurposesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where description equals to DEFAULT_DESCRIPTION
        defaultListWrkPurposeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listWrkPurposeList where description equals to UPDATED_DESCRIPTION
        defaultListWrkPurposeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListWrkPurposeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listWrkPurposeList where description equals to UPDATED_DESCRIPTION
        defaultListWrkPurposeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where description is not null
        defaultListWrkPurposeShouldBeFound("description.specified=true");

        // Get all the listWrkPurposeList where description is null
        defaultListWrkPurposeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListWrkPurposeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listWrkPurposeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkPurposeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListWrkPurposeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listWrkPurposeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkPurposeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where dateCreate is not null
        defaultListWrkPurposeShouldBeFound("dateCreate.specified=true");

        // Get all the listWrkPurposeList where dateCreate is null
        defaultListWrkPurposeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListWrkPurposeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listWrkPurposeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkPurposeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListWrkPurposeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listWrkPurposeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkPurposeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where dateEdit is not null
        defaultListWrkPurposeShouldBeFound("dateEdit.specified=true");

        // Get all the listWrkPurposeList where dateEdit is null
        defaultListWrkPurposeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where creator equals to DEFAULT_CREATOR
        defaultListWrkPurposeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listWrkPurposeList where creator equals to UPDATED_CREATOR
        defaultListWrkPurposeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListWrkPurposeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listWrkPurposeList where creator equals to UPDATED_CREATOR
        defaultListWrkPurposeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where creator is not null
        defaultListWrkPurposeShouldBeFound("creator.specified=true");

        // Get all the listWrkPurposeList where creator is null
        defaultListWrkPurposeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where editor equals to DEFAULT_EDITOR
        defaultListWrkPurposeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listWrkPurposeList where editor equals to UPDATED_EDITOR
        defaultListWrkPurposeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListWrkPurposeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listWrkPurposeList where editor equals to UPDATED_EDITOR
        defaultListWrkPurposeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkPurposesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        // Get all the listWrkPurposeList where editor is not null
        defaultListWrkPurposeShouldBeFound("editor.specified=true");

        // Get all the listWrkPurposeList where editor is null
        defaultListWrkPurposeShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListWrkPurposeShouldBeFound(String filter) throws Exception {
        restListWrkPurposeMockMvc.perform(get("/api/list-wrk-purposes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkPurpose.getId().intValue())))
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
        restListWrkPurposeMockMvc.perform(get("/api/list-wrk-purposes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListWrkPurposeShouldNotBeFound(String filter) throws Exception {
        restListWrkPurposeMockMvc.perform(get("/api/list-wrk-purposes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListWrkPurposeMockMvc.perform(get("/api/list-wrk-purposes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListWrkPurpose() throws Exception {
        // Get the listWrkPurpose
        restListWrkPurposeMockMvc.perform(get("/api/list-wrk-purposes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListWrkPurpose() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        int databaseSizeBeforeUpdate = listWrkPurposeRepository.findAll().size();

        // Update the listWrkPurpose
        ListWrkPurpose updatedListWrkPurpose = listWrkPurposeRepository.findById(listWrkPurpose.getId()).get();
        // Disconnect from session so that the updates on updatedListWrkPurpose are not directly saved in db
        em.detach(updatedListWrkPurpose);
        updatedListWrkPurpose
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(updatedListWrkPurpose);

        restListWrkPurposeMockMvc.perform(put("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isOk());

        // Validate the ListWrkPurpose in the database
        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeUpdate);
        ListWrkPurpose testListWrkPurpose = listWrkPurposeList.get(listWrkPurposeList.size() - 1);
        assertThat(testListWrkPurpose.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListWrkPurpose.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListWrkPurpose.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListWrkPurpose.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListWrkPurpose.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListWrkPurpose.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListWrkPurpose.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListWrkPurpose.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListWrkPurpose.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListWrkPurpose() throws Exception {
        int databaseSizeBeforeUpdate = listWrkPurposeRepository.findAll().size();

        // Create the ListWrkPurpose
        ListWrkPurposeDTO listWrkPurposeDTO = listWrkPurposeMapper.toDto(listWrkPurpose);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListWrkPurposeMockMvc.perform(put("/api/list-wrk-purposes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkPurposeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkPurpose in the database
        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListWrkPurpose() throws Exception {
        // Initialize the database
        listWrkPurposeRepository.saveAndFlush(listWrkPurpose);

        int databaseSizeBeforeDelete = listWrkPurposeRepository.findAll().size();

        // Delete the listWrkPurpose
        restListWrkPurposeMockMvc.perform(delete("/api/list-wrk-purposes/{id}", listWrkPurpose.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListWrkPurpose> listWrkPurposeList = listWrkPurposeRepository.findAll();
        assertThat(listWrkPurposeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkPurpose.class);
        ListWrkPurpose listWrkPurpose1 = new ListWrkPurpose();
        listWrkPurpose1.setId(1L);
        ListWrkPurpose listWrkPurpose2 = new ListWrkPurpose();
        listWrkPurpose2.setId(listWrkPurpose1.getId());
        assertThat(listWrkPurpose1).isEqualTo(listWrkPurpose2);
        listWrkPurpose2.setId(2L);
        assertThat(listWrkPurpose1).isNotEqualTo(listWrkPurpose2);
        listWrkPurpose1.setId(null);
        assertThat(listWrkPurpose1).isNotEqualTo(listWrkPurpose2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkPurposeDTO.class);
        ListWrkPurposeDTO listWrkPurposeDTO1 = new ListWrkPurposeDTO();
        listWrkPurposeDTO1.setId(1L);
        ListWrkPurposeDTO listWrkPurposeDTO2 = new ListWrkPurposeDTO();
        assertThat(listWrkPurposeDTO1).isNotEqualTo(listWrkPurposeDTO2);
        listWrkPurposeDTO2.setId(listWrkPurposeDTO1.getId());
        assertThat(listWrkPurposeDTO1).isEqualTo(listWrkPurposeDTO2);
        listWrkPurposeDTO2.setId(2L);
        assertThat(listWrkPurposeDTO1).isNotEqualTo(listWrkPurposeDTO2);
        listWrkPurposeDTO1.setId(null);
        assertThat(listWrkPurposeDTO1).isNotEqualTo(listWrkPurposeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listWrkPurposeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listWrkPurposeMapper.fromId(null)).isNull();
    }
}
