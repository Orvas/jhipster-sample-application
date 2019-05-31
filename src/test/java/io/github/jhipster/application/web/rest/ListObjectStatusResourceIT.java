package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.CpsHist;
import io.github.jhipster.application.domain.FreeSpanHist;
import io.github.jhipster.application.domain.FreeSpanSupportHist;
import io.github.jhipster.application.domain.LaunchReceiverHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.domain.PipelineHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListObjectStatusRepository;
import io.github.jhipster.application.service.ListObjectStatusService;
import io.github.jhipster.application.service.dto.ListObjectStatusDTO;
import io.github.jhipster.application.service.mapper.ListObjectStatusMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListObjectStatusCriteria;
import io.github.jhipster.application.service.ListObjectStatusQueryService;

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
 * Integration tests for the {@Link ListObjectStatusResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListObjectStatusResourceIT {

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
    private ListObjectStatusRepository listObjectStatusRepository;

    @Autowired
    private ListObjectStatusMapper listObjectStatusMapper;

    @Autowired
    private ListObjectStatusService listObjectStatusService;

    @Autowired
    private ListObjectStatusQueryService listObjectStatusQueryService;

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

    private MockMvc restListObjectStatusMockMvc;

    private ListObjectStatus listObjectStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListObjectStatusResource listObjectStatusResource = new ListObjectStatusResource(listObjectStatusService, listObjectStatusQueryService);
        this.restListObjectStatusMockMvc = MockMvcBuilders.standaloneSetup(listObjectStatusResource)
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
    public static ListObjectStatus createEntity(EntityManager em) {
        ListObjectStatus listObjectStatus = new ListObjectStatus()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listObjectStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListObjectStatus createUpdatedEntity(EntityManager em) {
        ListObjectStatus listObjectStatus = new ListObjectStatus()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listObjectStatus;
    }

    @BeforeEach
    public void initTest() {
        listObjectStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createListObjectStatus() throws Exception {
        int databaseSizeBeforeCreate = listObjectStatusRepository.findAll().size();

        // Create the ListObjectStatus
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(listObjectStatus);
        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ListObjectStatus testListObjectStatus = listObjectStatusList.get(listObjectStatusList.size() - 1);
        assertThat(testListObjectStatus.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListObjectStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListObjectStatus.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListObjectStatus.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListObjectStatus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListObjectStatus.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListObjectStatus.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListObjectStatus.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListObjectStatus.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListObjectStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listObjectStatusRepository.findAll().size();

        // Create the ListObjectStatus with an existing ID
        listObjectStatus.setId(1L);
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(listObjectStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectStatusRepository.findAll().size();
        // set the field null
        listObjectStatus.setCode(null);

        // Create the ListObjectStatus, which fails.
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(listObjectStatus);

        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectStatusRepository.findAll().size();
        // set the field null
        listObjectStatus.setName(null);

        // Create the ListObjectStatus, which fails.
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(listObjectStatus);

        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectStatusRepository.findAll().size();
        // set the field null
        listObjectStatus.setFullName(null);

        // Create the ListObjectStatus, which fails.
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(listObjectStatus);

        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectStatusRepository.findAll().size();
        // set the field null
        listObjectStatus.setIsCurrentFlag(null);

        // Create the ListObjectStatus, which fails.
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(listObjectStatus);

        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListObjectStatuses() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listObjectStatus.getId().intValue())))
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
    public void getListObjectStatus() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get the listObjectStatus
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses/{id}", listObjectStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listObjectStatus.getId().intValue()))
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
    public void getAllListObjectStatusesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where code equals to DEFAULT_CODE
        defaultListObjectStatusShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listObjectStatusList where code equals to UPDATED_CODE
        defaultListObjectStatusShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListObjectStatusShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listObjectStatusList where code equals to UPDATED_CODE
        defaultListObjectStatusShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where code is not null
        defaultListObjectStatusShouldBeFound("code.specified=true");

        // Get all the listObjectStatusList where code is null
        defaultListObjectStatusShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where name equals to DEFAULT_NAME
        defaultListObjectStatusShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listObjectStatusList where name equals to UPDATED_NAME
        defaultListObjectStatusShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListObjectStatusShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listObjectStatusList where name equals to UPDATED_NAME
        defaultListObjectStatusShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where name is not null
        defaultListObjectStatusShouldBeFound("name.specified=true");

        // Get all the listObjectStatusList where name is null
        defaultListObjectStatusShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where fullName equals to DEFAULT_FULL_NAME
        defaultListObjectStatusShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listObjectStatusList where fullName equals to UPDATED_FULL_NAME
        defaultListObjectStatusShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListObjectStatusShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listObjectStatusList where fullName equals to UPDATED_FULL_NAME
        defaultListObjectStatusShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where fullName is not null
        defaultListObjectStatusShouldBeFound("fullName.specified=true");

        // Get all the listObjectStatusList where fullName is null
        defaultListObjectStatusShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListObjectStatusShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listObjectStatusList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectStatusShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListObjectStatusShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listObjectStatusList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectStatusShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where isCurrentFlag is not null
        defaultListObjectStatusShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listObjectStatusList where isCurrentFlag is null
        defaultListObjectStatusShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListObjectStatusShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listObjectStatusList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectStatusShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListObjectStatusShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listObjectStatusList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectStatusShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where description equals to DEFAULT_DESCRIPTION
        defaultListObjectStatusShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listObjectStatusList where description equals to UPDATED_DESCRIPTION
        defaultListObjectStatusShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListObjectStatusShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listObjectStatusList where description equals to UPDATED_DESCRIPTION
        defaultListObjectStatusShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where description is not null
        defaultListObjectStatusShouldBeFound("description.specified=true");

        // Get all the listObjectStatusList where description is null
        defaultListObjectStatusShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListObjectStatusShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listObjectStatusList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListObjectStatusShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListObjectStatusShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listObjectStatusList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListObjectStatusShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where dateCreate is not null
        defaultListObjectStatusShouldBeFound("dateCreate.specified=true");

        // Get all the listObjectStatusList where dateCreate is null
        defaultListObjectStatusShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListObjectStatusShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listObjectStatusList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListObjectStatusShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListObjectStatusShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listObjectStatusList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListObjectStatusShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where dateEdit is not null
        defaultListObjectStatusShouldBeFound("dateEdit.specified=true");

        // Get all the listObjectStatusList where dateEdit is null
        defaultListObjectStatusShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where creator equals to DEFAULT_CREATOR
        defaultListObjectStatusShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listObjectStatusList where creator equals to UPDATED_CREATOR
        defaultListObjectStatusShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListObjectStatusShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listObjectStatusList where creator equals to UPDATED_CREATOR
        defaultListObjectStatusShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where creator is not null
        defaultListObjectStatusShouldBeFound("creator.specified=true");

        // Get all the listObjectStatusList where creator is null
        defaultListObjectStatusShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where editor equals to DEFAULT_EDITOR
        defaultListObjectStatusShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listObjectStatusList where editor equals to UPDATED_EDITOR
        defaultListObjectStatusShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListObjectStatusShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listObjectStatusList where editor equals to UPDATED_EDITOR
        defaultListObjectStatusShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList where editor is not null
        defaultListObjectStatusShouldBeFound("editor.specified=true");

        // Get all the listObjectStatusList where editor is null
        defaultListObjectStatusShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectStatusesByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listObjectStatus.addBendHist(bendHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long bendHistId = bendHist.getId();

        // Get all the listObjectStatusList where bendHist equals to bendHistId
        defaultListObjectStatusShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listObjectStatusList where bendHist equals to bendHistId + 1
        defaultListObjectStatusShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByCpsHistIsEqualToSomething() throws Exception {
        // Initialize the database
        CpsHist cpsHist = CpsHistResourceIT.createEntity(em);
        em.persist(cpsHist);
        em.flush();
        listObjectStatus.addCpsHist(cpsHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long cpsHistId = cpsHist.getId();

        // Get all the listObjectStatusList where cpsHist equals to cpsHistId
        defaultListObjectStatusShouldBeFound("cpsHistId.equals=" + cpsHistId);

        // Get all the listObjectStatusList where cpsHist equals to cpsHistId + 1
        defaultListObjectStatusShouldNotBeFound("cpsHistId.equals=" + (cpsHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByFreeSpanHistIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpanHist freeSpanHist = FreeSpanHistResourceIT.createEntity(em);
        em.persist(freeSpanHist);
        em.flush();
        listObjectStatus.addFreeSpanHist(freeSpanHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long freeSpanHistId = freeSpanHist.getId();

        // Get all the listObjectStatusList where freeSpanHist equals to freeSpanHistId
        defaultListObjectStatusShouldBeFound("freeSpanHistId.equals=" + freeSpanHistId);

        // Get all the listObjectStatusList where freeSpanHist equals to freeSpanHistId + 1
        defaultListObjectStatusShouldNotBeFound("freeSpanHistId.equals=" + (freeSpanHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByFreeSpanSupportHistIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpanSupportHist freeSpanSupportHist = FreeSpanSupportHistResourceIT.createEntity(em);
        em.persist(freeSpanSupportHist);
        em.flush();
        listObjectStatus.addFreeSpanSupportHist(freeSpanSupportHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long freeSpanSupportHistId = freeSpanSupportHist.getId();

        // Get all the listObjectStatusList where freeSpanSupportHist equals to freeSpanSupportHistId
        defaultListObjectStatusShouldBeFound("freeSpanSupportHistId.equals=" + freeSpanSupportHistId);

        // Get all the listObjectStatusList where freeSpanSupportHist equals to freeSpanSupportHistId + 1
        defaultListObjectStatusShouldNotBeFound("freeSpanSupportHistId.equals=" + (freeSpanSupportHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByLaunchReceiverHistIsEqualToSomething() throws Exception {
        // Initialize the database
        LaunchReceiverHist launchReceiverHist = LaunchReceiverHistResourceIT.createEntity(em);
        em.persist(launchReceiverHist);
        em.flush();
        listObjectStatus.addLaunchReceiverHist(launchReceiverHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long launchReceiverHistId = launchReceiverHist.getId();

        // Get all the listObjectStatusList where launchReceiverHist equals to launchReceiverHistId
        defaultListObjectStatusShouldBeFound("launchReceiverHistId.equals=" + launchReceiverHistId);

        // Get all the listObjectStatusList where launchReceiverHist equals to launchReceiverHistId + 1
        defaultListObjectStatusShouldNotBeFound("launchReceiverHistId.equals=" + (launchReceiverHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listObjectStatus.addPipeHist(pipeHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long pipeHistId = pipeHist.getId();

        // Get all the listObjectStatusList where pipeHist equals to pipeHistId
        defaultListObjectStatusShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listObjectStatusList where pipeHist equals to pipeHistId + 1
        defaultListObjectStatusShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByPipejointHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipejointHist pipejointHist = PipejointHistResourceIT.createEntity(em);
        em.persist(pipejointHist);
        em.flush();
        listObjectStatus.addPipejointHist(pipejointHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long pipejointHistId = pipejointHist.getId();

        // Get all the listObjectStatusList where pipejointHist equals to pipejointHistId
        defaultListObjectStatusShouldBeFound("pipejointHistId.equals=" + pipejointHistId);

        // Get all the listObjectStatusList where pipejointHist equals to pipejointHistId + 1
        defaultListObjectStatusShouldNotBeFound("pipejointHistId.equals=" + (pipejointHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByPipelineHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipelineHist pipelineHist = PipelineHistResourceIT.createEntity(em);
        em.persist(pipelineHist);
        em.flush();
        listObjectStatus.addPipelineHist(pipelineHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long pipelineHistId = pipelineHist.getId();

        // Get all the listObjectStatusList where pipelineHist equals to pipelineHistId
        defaultListObjectStatusShouldBeFound("pipelineHistId.equals=" + pipelineHistId);

        // Get all the listObjectStatusList where pipelineHist equals to pipelineHistId + 1
        defaultListObjectStatusShouldNotBeFound("pipelineHistId.equals=" + (pipelineHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listObjectStatus.addTeeHist(teeHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long teeHistId = teeHist.getId();

        // Get all the listObjectStatusList where teeHist equals to teeHistId
        defaultListObjectStatusShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listObjectStatusList where teeHist equals to teeHistId + 1
        defaultListObjectStatusShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListObjectStatusesByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listObjectStatus.addValveHist(valveHist);
        listObjectStatusRepository.saveAndFlush(listObjectStatus);
        Long valveHistId = valveHist.getId();

        // Get all the listObjectStatusList where valveHist equals to valveHistId
        defaultListObjectStatusShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listObjectStatusList where valveHist equals to valveHistId + 1
        defaultListObjectStatusShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListObjectStatusShouldBeFound(String filter) throws Exception {
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listObjectStatus.getId().intValue())))
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
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListObjectStatusShouldNotBeFound(String filter) throws Exception {
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListObjectStatus() throws Exception {
        // Get the listObjectStatus
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListObjectStatus() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        int databaseSizeBeforeUpdate = listObjectStatusRepository.findAll().size();

        // Update the listObjectStatus
        ListObjectStatus updatedListObjectStatus = listObjectStatusRepository.findById(listObjectStatus.getId()).get();
        // Disconnect from session so that the updates on updatedListObjectStatus are not directly saved in db
        em.detach(updatedListObjectStatus);
        updatedListObjectStatus
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(updatedListObjectStatus);

        restListObjectStatusMockMvc.perform(put("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeUpdate);
        ListObjectStatus testListObjectStatus = listObjectStatusList.get(listObjectStatusList.size() - 1);
        assertThat(testListObjectStatus.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListObjectStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListObjectStatus.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListObjectStatus.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListObjectStatus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListObjectStatus.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListObjectStatus.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListObjectStatus.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListObjectStatus.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListObjectStatus() throws Exception {
        int databaseSizeBeforeUpdate = listObjectStatusRepository.findAll().size();

        // Create the ListObjectStatus
        ListObjectStatusDTO listObjectStatusDTO = listObjectStatusMapper.toDto(listObjectStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListObjectStatusMockMvc.perform(put("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListObjectStatus() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        int databaseSizeBeforeDelete = listObjectStatusRepository.findAll().size();

        // Delete the listObjectStatus
        restListObjectStatusMockMvc.perform(delete("/api/list-object-statuses/{id}", listObjectStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListObjectStatus.class);
        ListObjectStatus listObjectStatus1 = new ListObjectStatus();
        listObjectStatus1.setId(1L);
        ListObjectStatus listObjectStatus2 = new ListObjectStatus();
        listObjectStatus2.setId(listObjectStatus1.getId());
        assertThat(listObjectStatus1).isEqualTo(listObjectStatus2);
        listObjectStatus2.setId(2L);
        assertThat(listObjectStatus1).isNotEqualTo(listObjectStatus2);
        listObjectStatus1.setId(null);
        assertThat(listObjectStatus1).isNotEqualTo(listObjectStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListObjectStatusDTO.class);
        ListObjectStatusDTO listObjectStatusDTO1 = new ListObjectStatusDTO();
        listObjectStatusDTO1.setId(1L);
        ListObjectStatusDTO listObjectStatusDTO2 = new ListObjectStatusDTO();
        assertThat(listObjectStatusDTO1).isNotEqualTo(listObjectStatusDTO2);
        listObjectStatusDTO2.setId(listObjectStatusDTO1.getId());
        assertThat(listObjectStatusDTO1).isEqualTo(listObjectStatusDTO2);
        listObjectStatusDTO2.setId(2L);
        assertThat(listObjectStatusDTO1).isNotEqualTo(listObjectStatusDTO2);
        listObjectStatusDTO1.setId(null);
        assertThat(listObjectStatusDTO1).isNotEqualTo(listObjectStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listObjectStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listObjectStatusMapper.fromId(null)).isNull();
    }
}
