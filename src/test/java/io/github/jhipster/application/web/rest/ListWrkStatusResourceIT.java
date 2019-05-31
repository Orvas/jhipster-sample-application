package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListWrkStatus;
import io.github.jhipster.application.domain.ListWrkKind;
import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.repository.ListWrkStatusRepository;
import io.github.jhipster.application.service.ListWrkStatusService;
import io.github.jhipster.application.service.dto.ListWrkStatusDTO;
import io.github.jhipster.application.service.mapper.ListWrkStatusMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListWrkStatusCriteria;
import io.github.jhipster.application.service.ListWrkStatusQueryService;

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
 * Integration tests for the {@Link ListWrkStatusResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListWrkStatusResourceIT {

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
    private ListWrkStatusRepository listWrkStatusRepository;

    @Autowired
    private ListWrkStatusMapper listWrkStatusMapper;

    @Autowired
    private ListWrkStatusService listWrkStatusService;

    @Autowired
    private ListWrkStatusQueryService listWrkStatusQueryService;

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

    private MockMvc restListWrkStatusMockMvc;

    private ListWrkStatus listWrkStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListWrkStatusResource listWrkStatusResource = new ListWrkStatusResource(listWrkStatusService, listWrkStatusQueryService);
        this.restListWrkStatusMockMvc = MockMvcBuilders.standaloneSetup(listWrkStatusResource)
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
    public static ListWrkStatus createEntity(EntityManager em) {
        ListWrkStatus listWrkStatus = new ListWrkStatus()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        ListWrkKind listWrkKind;
        if (TestUtil.findAll(em, ListWrkKind.class).isEmpty()) {
            listWrkKind = ListWrkKindResourceIT.createEntity(em);
            em.persist(listWrkKind);
            em.flush();
        } else {
            listWrkKind = TestUtil.findAll(em, ListWrkKind.class).get(0);
        }
        listWrkStatus.setIdWrkKind(listWrkKind);
        return listWrkStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListWrkStatus createUpdatedEntity(EntityManager em) {
        ListWrkStatus listWrkStatus = new ListWrkStatus()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        ListWrkKind listWrkKind;
        if (TestUtil.findAll(em, ListWrkKind.class).isEmpty()) {
            listWrkKind = ListWrkKindResourceIT.createUpdatedEntity(em);
            em.persist(listWrkKind);
            em.flush();
        } else {
            listWrkKind = TestUtil.findAll(em, ListWrkKind.class).get(0);
        }
        listWrkStatus.setIdWrkKind(listWrkKind);
        return listWrkStatus;
    }

    @BeforeEach
    public void initTest() {
        listWrkStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createListWrkStatus() throws Exception {
        int databaseSizeBeforeCreate = listWrkStatusRepository.findAll().size();

        // Create the ListWrkStatus
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(listWrkStatus);
        restListWrkStatusMockMvc.perform(post("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ListWrkStatus in the database
        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ListWrkStatus testListWrkStatus = listWrkStatusList.get(listWrkStatusList.size() - 1);
        assertThat(testListWrkStatus.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListWrkStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListWrkStatus.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListWrkStatus.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListWrkStatus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListWrkStatus.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListWrkStatus.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListWrkStatus.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListWrkStatus.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListWrkStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listWrkStatusRepository.findAll().size();

        // Create the ListWrkStatus with an existing ID
        listWrkStatus.setId(1L);
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(listWrkStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListWrkStatusMockMvc.perform(post("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkStatus in the database
        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkStatusRepository.findAll().size();
        // set the field null
        listWrkStatus.setCode(null);

        // Create the ListWrkStatus, which fails.
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(listWrkStatus);

        restListWrkStatusMockMvc.perform(post("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkStatusRepository.findAll().size();
        // set the field null
        listWrkStatus.setName(null);

        // Create the ListWrkStatus, which fails.
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(listWrkStatus);

        restListWrkStatusMockMvc.perform(post("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkStatusRepository.findAll().size();
        // set the field null
        listWrkStatus.setFullName(null);

        // Create the ListWrkStatus, which fails.
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(listWrkStatus);

        restListWrkStatusMockMvc.perform(post("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkStatusRepository.findAll().size();
        // set the field null
        listWrkStatus.setIsCurrentFlag(null);

        // Create the ListWrkStatus, which fails.
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(listWrkStatus);

        restListWrkStatusMockMvc.perform(post("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListWrkStatuses() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList
        restListWrkStatusMockMvc.perform(get("/api/list-wrk-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkStatus.getId().intValue())))
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
    public void getListWrkStatus() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get the listWrkStatus
        restListWrkStatusMockMvc.perform(get("/api/list-wrk-statuses/{id}", listWrkStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listWrkStatus.getId().intValue()))
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
    public void getAllListWrkStatusesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where code equals to DEFAULT_CODE
        defaultListWrkStatusShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listWrkStatusList where code equals to UPDATED_CODE
        defaultListWrkStatusShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListWrkStatusShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listWrkStatusList where code equals to UPDATED_CODE
        defaultListWrkStatusShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where code is not null
        defaultListWrkStatusShouldBeFound("code.specified=true");

        // Get all the listWrkStatusList where code is null
        defaultListWrkStatusShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where name equals to DEFAULT_NAME
        defaultListWrkStatusShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listWrkStatusList where name equals to UPDATED_NAME
        defaultListWrkStatusShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListWrkStatusShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listWrkStatusList where name equals to UPDATED_NAME
        defaultListWrkStatusShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where name is not null
        defaultListWrkStatusShouldBeFound("name.specified=true");

        // Get all the listWrkStatusList where name is null
        defaultListWrkStatusShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where fullName equals to DEFAULT_FULL_NAME
        defaultListWrkStatusShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listWrkStatusList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkStatusShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListWrkStatusShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listWrkStatusList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkStatusShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where fullName is not null
        defaultListWrkStatusShouldBeFound("fullName.specified=true");

        // Get all the listWrkStatusList where fullName is null
        defaultListWrkStatusShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkStatusShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkStatusList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkStatusShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListWrkStatusShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listWrkStatusList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkStatusShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where isCurrentFlag is not null
        defaultListWrkStatusShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listWrkStatusList where isCurrentFlag is null
        defaultListWrkStatusShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkStatusShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkStatusList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkStatusShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkStatusShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkStatusList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkStatusShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListWrkStatusesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where description equals to DEFAULT_DESCRIPTION
        defaultListWrkStatusShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listWrkStatusList where description equals to UPDATED_DESCRIPTION
        defaultListWrkStatusShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListWrkStatusShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listWrkStatusList where description equals to UPDATED_DESCRIPTION
        defaultListWrkStatusShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where description is not null
        defaultListWrkStatusShouldBeFound("description.specified=true");

        // Get all the listWrkStatusList where description is null
        defaultListWrkStatusShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListWrkStatusShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listWrkStatusList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkStatusShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListWrkStatusShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listWrkStatusList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkStatusShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where dateCreate is not null
        defaultListWrkStatusShouldBeFound("dateCreate.specified=true");

        // Get all the listWrkStatusList where dateCreate is null
        defaultListWrkStatusShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListWrkStatusShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listWrkStatusList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkStatusShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListWrkStatusShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listWrkStatusList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkStatusShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where dateEdit is not null
        defaultListWrkStatusShouldBeFound("dateEdit.specified=true");

        // Get all the listWrkStatusList where dateEdit is null
        defaultListWrkStatusShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where creator equals to DEFAULT_CREATOR
        defaultListWrkStatusShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listWrkStatusList where creator equals to UPDATED_CREATOR
        defaultListWrkStatusShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListWrkStatusShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listWrkStatusList where creator equals to UPDATED_CREATOR
        defaultListWrkStatusShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where creator is not null
        defaultListWrkStatusShouldBeFound("creator.specified=true");

        // Get all the listWrkStatusList where creator is null
        defaultListWrkStatusShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where editor equals to DEFAULT_EDITOR
        defaultListWrkStatusShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listWrkStatusList where editor equals to UPDATED_EDITOR
        defaultListWrkStatusShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListWrkStatusShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listWrkStatusList where editor equals to UPDATED_EDITOR
        defaultListWrkStatusShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        // Get all the listWrkStatusList where editor is not null
        defaultListWrkStatusShouldBeFound("editor.specified=true");

        // Get all the listWrkStatusList where editor is null
        defaultListWrkStatusShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkStatusesByIdWrkKindIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListWrkKind idWrkKind = listWrkStatus.getIdWrkKind();
        listWrkStatusRepository.saveAndFlush(listWrkStatus);
        Long idWrkKindId = idWrkKind.getId();

        // Get all the listWrkStatusList where idWrkKind equals to idWrkKindId
        defaultListWrkStatusShouldBeFound("idWrkKindId.equals=" + idWrkKindId);

        // Get all the listWrkStatusList where idWrkKind equals to idWrkKindId + 1
        defaultListWrkStatusShouldNotBeFound("idWrkKindId.equals=" + (idWrkKindId + 1));
    }


    @Test
    @Transactional
    public void getAllListWrkStatusesByAnodeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        AnodeHist anodeHist = AnodeHistResourceIT.createEntity(em);
        em.persist(anodeHist);
        em.flush();
        listWrkStatus.addAnodeHist(anodeHist);
        listWrkStatusRepository.saveAndFlush(listWrkStatus);
        Long anodeHistId = anodeHist.getId();

        // Get all the listWrkStatusList where anodeHist equals to anodeHistId
        defaultListWrkStatusShouldBeFound("anodeHistId.equals=" + anodeHistId);

        // Get all the listWrkStatusList where anodeHist equals to anodeHistId + 1
        defaultListWrkStatusShouldNotBeFound("anodeHistId.equals=" + (anodeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListWrkStatusShouldBeFound(String filter) throws Exception {
        restListWrkStatusMockMvc.perform(get("/api/list-wrk-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkStatus.getId().intValue())))
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
        restListWrkStatusMockMvc.perform(get("/api/list-wrk-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListWrkStatusShouldNotBeFound(String filter) throws Exception {
        restListWrkStatusMockMvc.perform(get("/api/list-wrk-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListWrkStatusMockMvc.perform(get("/api/list-wrk-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListWrkStatus() throws Exception {
        // Get the listWrkStatus
        restListWrkStatusMockMvc.perform(get("/api/list-wrk-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListWrkStatus() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        int databaseSizeBeforeUpdate = listWrkStatusRepository.findAll().size();

        // Update the listWrkStatus
        ListWrkStatus updatedListWrkStatus = listWrkStatusRepository.findById(listWrkStatus.getId()).get();
        // Disconnect from session so that the updates on updatedListWrkStatus are not directly saved in db
        em.detach(updatedListWrkStatus);
        updatedListWrkStatus
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(updatedListWrkStatus);

        restListWrkStatusMockMvc.perform(put("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ListWrkStatus in the database
        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeUpdate);
        ListWrkStatus testListWrkStatus = listWrkStatusList.get(listWrkStatusList.size() - 1);
        assertThat(testListWrkStatus.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListWrkStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListWrkStatus.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListWrkStatus.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListWrkStatus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListWrkStatus.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListWrkStatus.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListWrkStatus.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListWrkStatus.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListWrkStatus() throws Exception {
        int databaseSizeBeforeUpdate = listWrkStatusRepository.findAll().size();

        // Create the ListWrkStatus
        ListWrkStatusDTO listWrkStatusDTO = listWrkStatusMapper.toDto(listWrkStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListWrkStatusMockMvc.perform(put("/api/list-wrk-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkStatus in the database
        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListWrkStatus() throws Exception {
        // Initialize the database
        listWrkStatusRepository.saveAndFlush(listWrkStatus);

        int databaseSizeBeforeDelete = listWrkStatusRepository.findAll().size();

        // Delete the listWrkStatus
        restListWrkStatusMockMvc.perform(delete("/api/list-wrk-statuses/{id}", listWrkStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListWrkStatus> listWrkStatusList = listWrkStatusRepository.findAll();
        assertThat(listWrkStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkStatus.class);
        ListWrkStatus listWrkStatus1 = new ListWrkStatus();
        listWrkStatus1.setId(1L);
        ListWrkStatus listWrkStatus2 = new ListWrkStatus();
        listWrkStatus2.setId(listWrkStatus1.getId());
        assertThat(listWrkStatus1).isEqualTo(listWrkStatus2);
        listWrkStatus2.setId(2L);
        assertThat(listWrkStatus1).isNotEqualTo(listWrkStatus2);
        listWrkStatus1.setId(null);
        assertThat(listWrkStatus1).isNotEqualTo(listWrkStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkStatusDTO.class);
        ListWrkStatusDTO listWrkStatusDTO1 = new ListWrkStatusDTO();
        listWrkStatusDTO1.setId(1L);
        ListWrkStatusDTO listWrkStatusDTO2 = new ListWrkStatusDTO();
        assertThat(listWrkStatusDTO1).isNotEqualTo(listWrkStatusDTO2);
        listWrkStatusDTO2.setId(listWrkStatusDTO1.getId());
        assertThat(listWrkStatusDTO1).isEqualTo(listWrkStatusDTO2);
        listWrkStatusDTO2.setId(2L);
        assertThat(listWrkStatusDTO1).isNotEqualTo(listWrkStatusDTO2);
        listWrkStatusDTO1.setId(null);
        assertThat(listWrkStatusDTO1).isNotEqualTo(listWrkStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listWrkStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listWrkStatusMapper.fromId(null)).isNull();
    }
}
