package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListWrkcmmsStatus;
import io.github.jhipster.application.repository.ListWrkcmmsStatusRepository;
import io.github.jhipster.application.service.ListWrkcmmsStatusService;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusDTO;
import io.github.jhipster.application.service.mapper.ListWrkcmmsStatusMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusCriteria;
import io.github.jhipster.application.service.ListWrkcmmsStatusQueryService;

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
 * Integration tests for the {@Link ListWrkcmmsStatusResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListWrkcmmsStatusResourceIT {

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
    private ListWrkcmmsStatusRepository listWrkcmmsStatusRepository;

    @Autowired
    private ListWrkcmmsStatusMapper listWrkcmmsStatusMapper;

    @Autowired
    private ListWrkcmmsStatusService listWrkcmmsStatusService;

    @Autowired
    private ListWrkcmmsStatusQueryService listWrkcmmsStatusQueryService;

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

    private MockMvc restListWrkcmmsStatusMockMvc;

    private ListWrkcmmsStatus listWrkcmmsStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListWrkcmmsStatusResource listWrkcmmsStatusResource = new ListWrkcmmsStatusResource(listWrkcmmsStatusService, listWrkcmmsStatusQueryService);
        this.restListWrkcmmsStatusMockMvc = MockMvcBuilders.standaloneSetup(listWrkcmmsStatusResource)
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
    public static ListWrkcmmsStatus createEntity(EntityManager em) {
        ListWrkcmmsStatus listWrkcmmsStatus = new ListWrkcmmsStatus()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listWrkcmmsStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListWrkcmmsStatus createUpdatedEntity(EntityManager em) {
        ListWrkcmmsStatus listWrkcmmsStatus = new ListWrkcmmsStatus()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listWrkcmmsStatus;
    }

    @BeforeEach
    public void initTest() {
        listWrkcmmsStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createListWrkcmmsStatus() throws Exception {
        int databaseSizeBeforeCreate = listWrkcmmsStatusRepository.findAll().size();

        // Create the ListWrkcmmsStatus
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);
        restListWrkcmmsStatusMockMvc.perform(post("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ListWrkcmmsStatus in the database
        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ListWrkcmmsStatus testListWrkcmmsStatus = listWrkcmmsStatusList.get(listWrkcmmsStatusList.size() - 1);
        assertThat(testListWrkcmmsStatus.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListWrkcmmsStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListWrkcmmsStatus.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListWrkcmmsStatus.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListWrkcmmsStatus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListWrkcmmsStatus.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListWrkcmmsStatus.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListWrkcmmsStatus.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListWrkcmmsStatus.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListWrkcmmsStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listWrkcmmsStatusRepository.findAll().size();

        // Create the ListWrkcmmsStatus with an existing ID
        listWrkcmmsStatus.setId(1L);
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListWrkcmmsStatusMockMvc.perform(post("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkcmmsStatus in the database
        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkcmmsStatusRepository.findAll().size();
        // set the field null
        listWrkcmmsStatus.setCode(null);

        // Create the ListWrkcmmsStatus, which fails.
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);

        restListWrkcmmsStatusMockMvc.perform(post("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkcmmsStatusRepository.findAll().size();
        // set the field null
        listWrkcmmsStatus.setName(null);

        // Create the ListWrkcmmsStatus, which fails.
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);

        restListWrkcmmsStatusMockMvc.perform(post("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkcmmsStatusRepository.findAll().size();
        // set the field null
        listWrkcmmsStatus.setFullName(null);

        // Create the ListWrkcmmsStatus, which fails.
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);

        restListWrkcmmsStatusMockMvc.perform(post("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkcmmsStatusRepository.findAll().size();
        // set the field null
        listWrkcmmsStatus.setIsCurrentFlag(null);

        // Create the ListWrkcmmsStatus, which fails.
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);

        restListWrkcmmsStatusMockMvc.perform(post("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatuses() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList
        restListWrkcmmsStatusMockMvc.perform(get("/api/list-wrkcmms-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkcmmsStatus.getId().intValue())))
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
    public void getListWrkcmmsStatus() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get the listWrkcmmsStatus
        restListWrkcmmsStatusMockMvc.perform(get("/api/list-wrkcmms-statuses/{id}", listWrkcmmsStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listWrkcmmsStatus.getId().intValue()))
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
    public void getAllListWrkcmmsStatusesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where code equals to DEFAULT_CODE
        defaultListWrkcmmsStatusShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listWrkcmmsStatusList where code equals to UPDATED_CODE
        defaultListWrkcmmsStatusShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListWrkcmmsStatusShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listWrkcmmsStatusList where code equals to UPDATED_CODE
        defaultListWrkcmmsStatusShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where code is not null
        defaultListWrkcmmsStatusShouldBeFound("code.specified=true");

        // Get all the listWrkcmmsStatusList where code is null
        defaultListWrkcmmsStatusShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where name equals to DEFAULT_NAME
        defaultListWrkcmmsStatusShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listWrkcmmsStatusList where name equals to UPDATED_NAME
        defaultListWrkcmmsStatusShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListWrkcmmsStatusShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listWrkcmmsStatusList where name equals to UPDATED_NAME
        defaultListWrkcmmsStatusShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where name is not null
        defaultListWrkcmmsStatusShouldBeFound("name.specified=true");

        // Get all the listWrkcmmsStatusList where name is null
        defaultListWrkcmmsStatusShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where fullName equals to DEFAULT_FULL_NAME
        defaultListWrkcmmsStatusShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listWrkcmmsStatusList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkcmmsStatusShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListWrkcmmsStatusShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listWrkcmmsStatusList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkcmmsStatusShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where fullName is not null
        defaultListWrkcmmsStatusShouldBeFound("fullName.specified=true");

        // Get all the listWrkcmmsStatusList where fullName is null
        defaultListWrkcmmsStatusShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkcmmsStatusList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listWrkcmmsStatusList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where isCurrentFlag is not null
        defaultListWrkcmmsStatusShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listWrkcmmsStatusList where isCurrentFlag is null
        defaultListWrkcmmsStatusShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkcmmsStatusList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkcmmsStatusList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkcmmsStatusShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where description equals to DEFAULT_DESCRIPTION
        defaultListWrkcmmsStatusShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listWrkcmmsStatusList where description equals to UPDATED_DESCRIPTION
        defaultListWrkcmmsStatusShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListWrkcmmsStatusShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listWrkcmmsStatusList where description equals to UPDATED_DESCRIPTION
        defaultListWrkcmmsStatusShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where description is not null
        defaultListWrkcmmsStatusShouldBeFound("description.specified=true");

        // Get all the listWrkcmmsStatusList where description is null
        defaultListWrkcmmsStatusShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListWrkcmmsStatusShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listWrkcmmsStatusList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkcmmsStatusShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListWrkcmmsStatusShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listWrkcmmsStatusList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkcmmsStatusShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where dateCreate is not null
        defaultListWrkcmmsStatusShouldBeFound("dateCreate.specified=true");

        // Get all the listWrkcmmsStatusList where dateCreate is null
        defaultListWrkcmmsStatusShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListWrkcmmsStatusShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listWrkcmmsStatusList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkcmmsStatusShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListWrkcmmsStatusShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listWrkcmmsStatusList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkcmmsStatusShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where dateEdit is not null
        defaultListWrkcmmsStatusShouldBeFound("dateEdit.specified=true");

        // Get all the listWrkcmmsStatusList where dateEdit is null
        defaultListWrkcmmsStatusShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where creator equals to DEFAULT_CREATOR
        defaultListWrkcmmsStatusShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listWrkcmmsStatusList where creator equals to UPDATED_CREATOR
        defaultListWrkcmmsStatusShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListWrkcmmsStatusShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listWrkcmmsStatusList where creator equals to UPDATED_CREATOR
        defaultListWrkcmmsStatusShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where creator is not null
        defaultListWrkcmmsStatusShouldBeFound("creator.specified=true");

        // Get all the listWrkcmmsStatusList where creator is null
        defaultListWrkcmmsStatusShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where editor equals to DEFAULT_EDITOR
        defaultListWrkcmmsStatusShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listWrkcmmsStatusList where editor equals to UPDATED_EDITOR
        defaultListWrkcmmsStatusShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListWrkcmmsStatusShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listWrkcmmsStatusList where editor equals to UPDATED_EDITOR
        defaultListWrkcmmsStatusShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkcmmsStatusesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        // Get all the listWrkcmmsStatusList where editor is not null
        defaultListWrkcmmsStatusShouldBeFound("editor.specified=true");

        // Get all the listWrkcmmsStatusList where editor is null
        defaultListWrkcmmsStatusShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListWrkcmmsStatusShouldBeFound(String filter) throws Exception {
        restListWrkcmmsStatusMockMvc.perform(get("/api/list-wrkcmms-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkcmmsStatus.getId().intValue())))
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
        restListWrkcmmsStatusMockMvc.perform(get("/api/list-wrkcmms-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListWrkcmmsStatusShouldNotBeFound(String filter) throws Exception {
        restListWrkcmmsStatusMockMvc.perform(get("/api/list-wrkcmms-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListWrkcmmsStatusMockMvc.perform(get("/api/list-wrkcmms-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListWrkcmmsStatus() throws Exception {
        // Get the listWrkcmmsStatus
        restListWrkcmmsStatusMockMvc.perform(get("/api/list-wrkcmms-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListWrkcmmsStatus() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        int databaseSizeBeforeUpdate = listWrkcmmsStatusRepository.findAll().size();

        // Update the listWrkcmmsStatus
        ListWrkcmmsStatus updatedListWrkcmmsStatus = listWrkcmmsStatusRepository.findById(listWrkcmmsStatus.getId()).get();
        // Disconnect from session so that the updates on updatedListWrkcmmsStatus are not directly saved in db
        em.detach(updatedListWrkcmmsStatus);
        updatedListWrkcmmsStatus
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(updatedListWrkcmmsStatus);

        restListWrkcmmsStatusMockMvc.perform(put("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ListWrkcmmsStatus in the database
        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeUpdate);
        ListWrkcmmsStatus testListWrkcmmsStatus = listWrkcmmsStatusList.get(listWrkcmmsStatusList.size() - 1);
        assertThat(testListWrkcmmsStatus.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListWrkcmmsStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListWrkcmmsStatus.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListWrkcmmsStatus.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListWrkcmmsStatus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListWrkcmmsStatus.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListWrkcmmsStatus.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListWrkcmmsStatus.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListWrkcmmsStatus.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListWrkcmmsStatus() throws Exception {
        int databaseSizeBeforeUpdate = listWrkcmmsStatusRepository.findAll().size();

        // Create the ListWrkcmmsStatus
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO = listWrkcmmsStatusMapper.toDto(listWrkcmmsStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListWrkcmmsStatusMockMvc.perform(put("/api/list-wrkcmms-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkcmmsStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkcmmsStatus in the database
        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListWrkcmmsStatus() throws Exception {
        // Initialize the database
        listWrkcmmsStatusRepository.saveAndFlush(listWrkcmmsStatus);

        int databaseSizeBeforeDelete = listWrkcmmsStatusRepository.findAll().size();

        // Delete the listWrkcmmsStatus
        restListWrkcmmsStatusMockMvc.perform(delete("/api/list-wrkcmms-statuses/{id}", listWrkcmmsStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListWrkcmmsStatus> listWrkcmmsStatusList = listWrkcmmsStatusRepository.findAll();
        assertThat(listWrkcmmsStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkcmmsStatus.class);
        ListWrkcmmsStatus listWrkcmmsStatus1 = new ListWrkcmmsStatus();
        listWrkcmmsStatus1.setId(1L);
        ListWrkcmmsStatus listWrkcmmsStatus2 = new ListWrkcmmsStatus();
        listWrkcmmsStatus2.setId(listWrkcmmsStatus1.getId());
        assertThat(listWrkcmmsStatus1).isEqualTo(listWrkcmmsStatus2);
        listWrkcmmsStatus2.setId(2L);
        assertThat(listWrkcmmsStatus1).isNotEqualTo(listWrkcmmsStatus2);
        listWrkcmmsStatus1.setId(null);
        assertThat(listWrkcmmsStatus1).isNotEqualTo(listWrkcmmsStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkcmmsStatusDTO.class);
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO1 = new ListWrkcmmsStatusDTO();
        listWrkcmmsStatusDTO1.setId(1L);
        ListWrkcmmsStatusDTO listWrkcmmsStatusDTO2 = new ListWrkcmmsStatusDTO();
        assertThat(listWrkcmmsStatusDTO1).isNotEqualTo(listWrkcmmsStatusDTO2);
        listWrkcmmsStatusDTO2.setId(listWrkcmmsStatusDTO1.getId());
        assertThat(listWrkcmmsStatusDTO1).isEqualTo(listWrkcmmsStatusDTO2);
        listWrkcmmsStatusDTO2.setId(2L);
        assertThat(listWrkcmmsStatusDTO1).isNotEqualTo(listWrkcmmsStatusDTO2);
        listWrkcmmsStatusDTO1.setId(null);
        assertThat(listWrkcmmsStatusDTO1).isNotEqualTo(listWrkcmmsStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listWrkcmmsStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listWrkcmmsStatusMapper.fromId(null)).isNull();
    }
}
