package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListValveSpecification;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListValveSpecificationRepository;
import io.github.jhipster.application.service.ListValveSpecificationService;
import io.github.jhipster.application.service.dto.ListValveSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListValveSpecificationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListValveSpecificationCriteria;
import io.github.jhipster.application.service.ListValveSpecificationQueryService;

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
 * Integration tests for the {@Link ListValveSpecificationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListValveSpecificationResourceIT {

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
    private ListValveSpecificationRepository listValveSpecificationRepository;

    @Autowired
    private ListValveSpecificationMapper listValveSpecificationMapper;

    @Autowired
    private ListValveSpecificationService listValveSpecificationService;

    @Autowired
    private ListValveSpecificationQueryService listValveSpecificationQueryService;

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

    private MockMvc restListValveSpecificationMockMvc;

    private ListValveSpecification listValveSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListValveSpecificationResource listValveSpecificationResource = new ListValveSpecificationResource(listValveSpecificationService, listValveSpecificationQueryService);
        this.restListValveSpecificationMockMvc = MockMvcBuilders.standaloneSetup(listValveSpecificationResource)
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
    public static ListValveSpecification createEntity(EntityManager em) {
        ListValveSpecification listValveSpecification = new ListValveSpecification()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listValveSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListValveSpecification createUpdatedEntity(EntityManager em) {
        ListValveSpecification listValveSpecification = new ListValveSpecification()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listValveSpecification;
    }

    @BeforeEach
    public void initTest() {
        listValveSpecification = createEntity(em);
    }

    @Test
    @Transactional
    public void createListValveSpecification() throws Exception {
        int databaseSizeBeforeCreate = listValveSpecificationRepository.findAll().size();

        // Create the ListValveSpecification
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(listValveSpecification);
        restListValveSpecificationMockMvc.perform(post("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListValveSpecification in the database
        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ListValveSpecification testListValveSpecification = listValveSpecificationList.get(listValveSpecificationList.size() - 1);
        assertThat(testListValveSpecification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListValveSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListValveSpecification.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListValveSpecification.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListValveSpecification.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListValveSpecification.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListValveSpecification.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListValveSpecification.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListValveSpecification.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListValveSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listValveSpecificationRepository.findAll().size();

        // Create the ListValveSpecification with an existing ID
        listValveSpecification.setId(1L);
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(listValveSpecification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListValveSpecificationMockMvc.perform(post("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveSpecification in the database
        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveSpecificationRepository.findAll().size();
        // set the field null
        listValveSpecification.setCode(null);

        // Create the ListValveSpecification, which fails.
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(listValveSpecification);

        restListValveSpecificationMockMvc.perform(post("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveSpecificationRepository.findAll().size();
        // set the field null
        listValveSpecification.setName(null);

        // Create the ListValveSpecification, which fails.
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(listValveSpecification);

        restListValveSpecificationMockMvc.perform(post("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveSpecificationRepository.findAll().size();
        // set the field null
        listValveSpecification.setFullName(null);

        // Create the ListValveSpecification, which fails.
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(listValveSpecification);

        restListValveSpecificationMockMvc.perform(post("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveSpecificationRepository.findAll().size();
        // set the field null
        listValveSpecification.setIsCurrentFlag(null);

        // Create the ListValveSpecification, which fails.
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(listValveSpecification);

        restListValveSpecificationMockMvc.perform(post("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListValveSpecifications() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList
        restListValveSpecificationMockMvc.perform(get("/api/list-valve-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveSpecification.getId().intValue())))
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
    public void getListValveSpecification() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get the listValveSpecification
        restListValveSpecificationMockMvc.perform(get("/api/list-valve-specifications/{id}", listValveSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listValveSpecification.getId().intValue()))
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
    public void getAllListValveSpecificationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where code equals to DEFAULT_CODE
        defaultListValveSpecificationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listValveSpecificationList where code equals to UPDATED_CODE
        defaultListValveSpecificationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListValveSpecificationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listValveSpecificationList where code equals to UPDATED_CODE
        defaultListValveSpecificationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where code is not null
        defaultListValveSpecificationShouldBeFound("code.specified=true");

        // Get all the listValveSpecificationList where code is null
        defaultListValveSpecificationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where name equals to DEFAULT_NAME
        defaultListValveSpecificationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listValveSpecificationList where name equals to UPDATED_NAME
        defaultListValveSpecificationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListValveSpecificationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listValveSpecificationList where name equals to UPDATED_NAME
        defaultListValveSpecificationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where name is not null
        defaultListValveSpecificationShouldBeFound("name.specified=true");

        // Get all the listValveSpecificationList where name is null
        defaultListValveSpecificationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where fullName equals to DEFAULT_FULL_NAME
        defaultListValveSpecificationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listValveSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListValveSpecificationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListValveSpecificationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listValveSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListValveSpecificationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where fullName is not null
        defaultListValveSpecificationShouldBeFound("fullName.specified=true");

        // Get all the listValveSpecificationList where fullName is null
        defaultListValveSpecificationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listValveSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where isCurrentFlag is not null
        defaultListValveSpecificationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listValveSpecificationList where isCurrentFlag is null
        defaultListValveSpecificationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveSpecificationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveSpecificationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveSpecificationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListValveSpecificationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where description equals to DEFAULT_DESCRIPTION
        defaultListValveSpecificationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listValveSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListValveSpecificationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListValveSpecificationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listValveSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListValveSpecificationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where description is not null
        defaultListValveSpecificationShouldBeFound("description.specified=true");

        // Get all the listValveSpecificationList where description is null
        defaultListValveSpecificationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListValveSpecificationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listValveSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveSpecificationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListValveSpecificationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listValveSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveSpecificationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where dateCreate is not null
        defaultListValveSpecificationShouldBeFound("dateCreate.specified=true");

        // Get all the listValveSpecificationList where dateCreate is null
        defaultListValveSpecificationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListValveSpecificationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listValveSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveSpecificationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListValveSpecificationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listValveSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveSpecificationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where dateEdit is not null
        defaultListValveSpecificationShouldBeFound("dateEdit.specified=true");

        // Get all the listValveSpecificationList where dateEdit is null
        defaultListValveSpecificationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where creator equals to DEFAULT_CREATOR
        defaultListValveSpecificationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listValveSpecificationList where creator equals to UPDATED_CREATOR
        defaultListValveSpecificationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListValveSpecificationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listValveSpecificationList where creator equals to UPDATED_CREATOR
        defaultListValveSpecificationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where creator is not null
        defaultListValveSpecificationShouldBeFound("creator.specified=true");

        // Get all the listValveSpecificationList where creator is null
        defaultListValveSpecificationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where editor equals to DEFAULT_EDITOR
        defaultListValveSpecificationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listValveSpecificationList where editor equals to UPDATED_EDITOR
        defaultListValveSpecificationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListValveSpecificationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listValveSpecificationList where editor equals to UPDATED_EDITOR
        defaultListValveSpecificationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        // Get all the listValveSpecificationList where editor is not null
        defaultListValveSpecificationShouldBeFound("editor.specified=true");

        // Get all the listValveSpecificationList where editor is null
        defaultListValveSpecificationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveSpecificationsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listValveSpecification.addValveHist(valveHist);
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);
        Long valveHistId = valveHist.getId();

        // Get all the listValveSpecificationList where valveHist equals to valveHistId
        defaultListValveSpecificationShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listValveSpecificationList where valveHist equals to valveHistId + 1
        defaultListValveSpecificationShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListValveSpecificationShouldBeFound(String filter) throws Exception {
        restListValveSpecificationMockMvc.perform(get("/api/list-valve-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveSpecification.getId().intValue())))
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
        restListValveSpecificationMockMvc.perform(get("/api/list-valve-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListValveSpecificationShouldNotBeFound(String filter) throws Exception {
        restListValveSpecificationMockMvc.perform(get("/api/list-valve-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListValveSpecificationMockMvc.perform(get("/api/list-valve-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListValveSpecification() throws Exception {
        // Get the listValveSpecification
        restListValveSpecificationMockMvc.perform(get("/api/list-valve-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListValveSpecification() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        int databaseSizeBeforeUpdate = listValveSpecificationRepository.findAll().size();

        // Update the listValveSpecification
        ListValveSpecification updatedListValveSpecification = listValveSpecificationRepository.findById(listValveSpecification.getId()).get();
        // Disconnect from session so that the updates on updatedListValveSpecification are not directly saved in db
        em.detach(updatedListValveSpecification);
        updatedListValveSpecification
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(updatedListValveSpecification);

        restListValveSpecificationMockMvc.perform(put("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isOk());

        // Validate the ListValveSpecification in the database
        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ListValveSpecification testListValveSpecification = listValveSpecificationList.get(listValveSpecificationList.size() - 1);
        assertThat(testListValveSpecification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListValveSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListValveSpecification.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListValveSpecification.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListValveSpecification.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListValveSpecification.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListValveSpecification.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListValveSpecification.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListValveSpecification.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListValveSpecification() throws Exception {
        int databaseSizeBeforeUpdate = listValveSpecificationRepository.findAll().size();

        // Create the ListValveSpecification
        ListValveSpecificationDTO listValveSpecificationDTO = listValveSpecificationMapper.toDto(listValveSpecification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListValveSpecificationMockMvc.perform(put("/api/list-valve-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveSpecification in the database
        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListValveSpecification() throws Exception {
        // Initialize the database
        listValveSpecificationRepository.saveAndFlush(listValveSpecification);

        int databaseSizeBeforeDelete = listValveSpecificationRepository.findAll().size();

        // Delete the listValveSpecification
        restListValveSpecificationMockMvc.perform(delete("/api/list-valve-specifications/{id}", listValveSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListValveSpecification> listValveSpecificationList = listValveSpecificationRepository.findAll();
        assertThat(listValveSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveSpecification.class);
        ListValveSpecification listValveSpecification1 = new ListValveSpecification();
        listValveSpecification1.setId(1L);
        ListValveSpecification listValveSpecification2 = new ListValveSpecification();
        listValveSpecification2.setId(listValveSpecification1.getId());
        assertThat(listValveSpecification1).isEqualTo(listValveSpecification2);
        listValveSpecification2.setId(2L);
        assertThat(listValveSpecification1).isNotEqualTo(listValveSpecification2);
        listValveSpecification1.setId(null);
        assertThat(listValveSpecification1).isNotEqualTo(listValveSpecification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveSpecificationDTO.class);
        ListValveSpecificationDTO listValveSpecificationDTO1 = new ListValveSpecificationDTO();
        listValveSpecificationDTO1.setId(1L);
        ListValveSpecificationDTO listValveSpecificationDTO2 = new ListValveSpecificationDTO();
        assertThat(listValveSpecificationDTO1).isNotEqualTo(listValveSpecificationDTO2);
        listValveSpecificationDTO2.setId(listValveSpecificationDTO1.getId());
        assertThat(listValveSpecificationDTO1).isEqualTo(listValveSpecificationDTO2);
        listValveSpecificationDTO2.setId(2L);
        assertThat(listValveSpecificationDTO1).isNotEqualTo(listValveSpecificationDTO2);
        listValveSpecificationDTO1.setId(null);
        assertThat(listValveSpecificationDTO1).isNotEqualTo(listValveSpecificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listValveSpecificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listValveSpecificationMapper.fromId(null)).isNull();
    }
}
