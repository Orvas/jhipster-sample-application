package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListTeeSpecification;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.repository.ListTeeSpecificationRepository;
import io.github.jhipster.application.service.ListTeeSpecificationService;
import io.github.jhipster.application.service.dto.ListTeeSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListTeeSpecificationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListTeeSpecificationCriteria;
import io.github.jhipster.application.service.ListTeeSpecificationQueryService;

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
 * Integration tests for the {@Link ListTeeSpecificationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListTeeSpecificationResourceIT {

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
    private ListTeeSpecificationRepository listTeeSpecificationRepository;

    @Autowired
    private ListTeeSpecificationMapper listTeeSpecificationMapper;

    @Autowired
    private ListTeeSpecificationService listTeeSpecificationService;

    @Autowired
    private ListTeeSpecificationQueryService listTeeSpecificationQueryService;

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

    private MockMvc restListTeeSpecificationMockMvc;

    private ListTeeSpecification listTeeSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListTeeSpecificationResource listTeeSpecificationResource = new ListTeeSpecificationResource(listTeeSpecificationService, listTeeSpecificationQueryService);
        this.restListTeeSpecificationMockMvc = MockMvcBuilders.standaloneSetup(listTeeSpecificationResource)
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
    public static ListTeeSpecification createEntity(EntityManager em) {
        ListTeeSpecification listTeeSpecification = new ListTeeSpecification()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listTeeSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListTeeSpecification createUpdatedEntity(EntityManager em) {
        ListTeeSpecification listTeeSpecification = new ListTeeSpecification()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listTeeSpecification;
    }

    @BeforeEach
    public void initTest() {
        listTeeSpecification = createEntity(em);
    }

    @Test
    @Transactional
    public void createListTeeSpecification() throws Exception {
        int databaseSizeBeforeCreate = listTeeSpecificationRepository.findAll().size();

        // Create the ListTeeSpecification
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(listTeeSpecification);
        restListTeeSpecificationMockMvc.perform(post("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListTeeSpecification in the database
        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ListTeeSpecification testListTeeSpecification = listTeeSpecificationList.get(listTeeSpecificationList.size() - 1);
        assertThat(testListTeeSpecification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListTeeSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListTeeSpecification.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListTeeSpecification.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListTeeSpecification.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListTeeSpecification.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListTeeSpecification.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListTeeSpecification.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListTeeSpecification.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListTeeSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listTeeSpecificationRepository.findAll().size();

        // Create the ListTeeSpecification with an existing ID
        listTeeSpecification.setId(1L);
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(listTeeSpecification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListTeeSpecificationMockMvc.perform(post("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListTeeSpecification in the database
        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeSpecificationRepository.findAll().size();
        // set the field null
        listTeeSpecification.setCode(null);

        // Create the ListTeeSpecification, which fails.
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(listTeeSpecification);

        restListTeeSpecificationMockMvc.perform(post("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeSpecificationRepository.findAll().size();
        // set the field null
        listTeeSpecification.setName(null);

        // Create the ListTeeSpecification, which fails.
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(listTeeSpecification);

        restListTeeSpecificationMockMvc.perform(post("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeSpecificationRepository.findAll().size();
        // set the field null
        listTeeSpecification.setFullName(null);

        // Create the ListTeeSpecification, which fails.
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(listTeeSpecification);

        restListTeeSpecificationMockMvc.perform(post("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeSpecificationRepository.findAll().size();
        // set the field null
        listTeeSpecification.setIsCurrentFlag(null);

        // Create the ListTeeSpecification, which fails.
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(listTeeSpecification);

        restListTeeSpecificationMockMvc.perform(post("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecifications() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList
        restListTeeSpecificationMockMvc.perform(get("/api/list-tee-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listTeeSpecification.getId().intValue())))
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
    public void getListTeeSpecification() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get the listTeeSpecification
        restListTeeSpecificationMockMvc.perform(get("/api/list-tee-specifications/{id}", listTeeSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listTeeSpecification.getId().intValue()))
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
    public void getAllListTeeSpecificationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where code equals to DEFAULT_CODE
        defaultListTeeSpecificationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listTeeSpecificationList where code equals to UPDATED_CODE
        defaultListTeeSpecificationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListTeeSpecificationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listTeeSpecificationList where code equals to UPDATED_CODE
        defaultListTeeSpecificationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where code is not null
        defaultListTeeSpecificationShouldBeFound("code.specified=true");

        // Get all the listTeeSpecificationList where code is null
        defaultListTeeSpecificationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where name equals to DEFAULT_NAME
        defaultListTeeSpecificationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listTeeSpecificationList where name equals to UPDATED_NAME
        defaultListTeeSpecificationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListTeeSpecificationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listTeeSpecificationList where name equals to UPDATED_NAME
        defaultListTeeSpecificationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where name is not null
        defaultListTeeSpecificationShouldBeFound("name.specified=true");

        // Get all the listTeeSpecificationList where name is null
        defaultListTeeSpecificationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where fullName equals to DEFAULT_FULL_NAME
        defaultListTeeSpecificationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listTeeSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListTeeSpecificationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListTeeSpecificationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listTeeSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListTeeSpecificationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where fullName is not null
        defaultListTeeSpecificationShouldBeFound("fullName.specified=true");

        // Get all the listTeeSpecificationList where fullName is null
        defaultListTeeSpecificationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listTeeSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where isCurrentFlag is not null
        defaultListTeeSpecificationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listTeeSpecificationList where isCurrentFlag is null
        defaultListTeeSpecificationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeSpecificationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeSpecificationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeSpecificationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where description equals to DEFAULT_DESCRIPTION
        defaultListTeeSpecificationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listTeeSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListTeeSpecificationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListTeeSpecificationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listTeeSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListTeeSpecificationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where description is not null
        defaultListTeeSpecificationShouldBeFound("description.specified=true");

        // Get all the listTeeSpecificationList where description is null
        defaultListTeeSpecificationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListTeeSpecificationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listTeeSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListTeeSpecificationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListTeeSpecificationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listTeeSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListTeeSpecificationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where dateCreate is not null
        defaultListTeeSpecificationShouldBeFound("dateCreate.specified=true");

        // Get all the listTeeSpecificationList where dateCreate is null
        defaultListTeeSpecificationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListTeeSpecificationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listTeeSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListTeeSpecificationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListTeeSpecificationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listTeeSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListTeeSpecificationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where dateEdit is not null
        defaultListTeeSpecificationShouldBeFound("dateEdit.specified=true");

        // Get all the listTeeSpecificationList where dateEdit is null
        defaultListTeeSpecificationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where creator equals to DEFAULT_CREATOR
        defaultListTeeSpecificationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listTeeSpecificationList where creator equals to UPDATED_CREATOR
        defaultListTeeSpecificationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListTeeSpecificationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listTeeSpecificationList where creator equals to UPDATED_CREATOR
        defaultListTeeSpecificationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where creator is not null
        defaultListTeeSpecificationShouldBeFound("creator.specified=true");

        // Get all the listTeeSpecificationList where creator is null
        defaultListTeeSpecificationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where editor equals to DEFAULT_EDITOR
        defaultListTeeSpecificationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listTeeSpecificationList where editor equals to UPDATED_EDITOR
        defaultListTeeSpecificationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListTeeSpecificationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listTeeSpecificationList where editor equals to UPDATED_EDITOR
        defaultListTeeSpecificationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        // Get all the listTeeSpecificationList where editor is not null
        defaultListTeeSpecificationShouldBeFound("editor.specified=true");

        // Get all the listTeeSpecificationList where editor is null
        defaultListTeeSpecificationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeSpecificationsByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listTeeSpecification.addTeeHist(teeHist);
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);
        Long teeHistId = teeHist.getId();

        // Get all the listTeeSpecificationList where teeHist equals to teeHistId
        defaultListTeeSpecificationShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listTeeSpecificationList where teeHist equals to teeHistId + 1
        defaultListTeeSpecificationShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListTeeSpecificationShouldBeFound(String filter) throws Exception {
        restListTeeSpecificationMockMvc.perform(get("/api/list-tee-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listTeeSpecification.getId().intValue())))
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
        restListTeeSpecificationMockMvc.perform(get("/api/list-tee-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListTeeSpecificationShouldNotBeFound(String filter) throws Exception {
        restListTeeSpecificationMockMvc.perform(get("/api/list-tee-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListTeeSpecificationMockMvc.perform(get("/api/list-tee-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListTeeSpecification() throws Exception {
        // Get the listTeeSpecification
        restListTeeSpecificationMockMvc.perform(get("/api/list-tee-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListTeeSpecification() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        int databaseSizeBeforeUpdate = listTeeSpecificationRepository.findAll().size();

        // Update the listTeeSpecification
        ListTeeSpecification updatedListTeeSpecification = listTeeSpecificationRepository.findById(listTeeSpecification.getId()).get();
        // Disconnect from session so that the updates on updatedListTeeSpecification are not directly saved in db
        em.detach(updatedListTeeSpecification);
        updatedListTeeSpecification
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(updatedListTeeSpecification);

        restListTeeSpecificationMockMvc.perform(put("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isOk());

        // Validate the ListTeeSpecification in the database
        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ListTeeSpecification testListTeeSpecification = listTeeSpecificationList.get(listTeeSpecificationList.size() - 1);
        assertThat(testListTeeSpecification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListTeeSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListTeeSpecification.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListTeeSpecification.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListTeeSpecification.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListTeeSpecification.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListTeeSpecification.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListTeeSpecification.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListTeeSpecification.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListTeeSpecification() throws Exception {
        int databaseSizeBeforeUpdate = listTeeSpecificationRepository.findAll().size();

        // Create the ListTeeSpecification
        ListTeeSpecificationDTO listTeeSpecificationDTO = listTeeSpecificationMapper.toDto(listTeeSpecification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListTeeSpecificationMockMvc.perform(put("/api/list-tee-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListTeeSpecification in the database
        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListTeeSpecification() throws Exception {
        // Initialize the database
        listTeeSpecificationRepository.saveAndFlush(listTeeSpecification);

        int databaseSizeBeforeDelete = listTeeSpecificationRepository.findAll().size();

        // Delete the listTeeSpecification
        restListTeeSpecificationMockMvc.perform(delete("/api/list-tee-specifications/{id}", listTeeSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListTeeSpecification> listTeeSpecificationList = listTeeSpecificationRepository.findAll();
        assertThat(listTeeSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListTeeSpecification.class);
        ListTeeSpecification listTeeSpecification1 = new ListTeeSpecification();
        listTeeSpecification1.setId(1L);
        ListTeeSpecification listTeeSpecification2 = new ListTeeSpecification();
        listTeeSpecification2.setId(listTeeSpecification1.getId());
        assertThat(listTeeSpecification1).isEqualTo(listTeeSpecification2);
        listTeeSpecification2.setId(2L);
        assertThat(listTeeSpecification1).isNotEqualTo(listTeeSpecification2);
        listTeeSpecification1.setId(null);
        assertThat(listTeeSpecification1).isNotEqualTo(listTeeSpecification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListTeeSpecificationDTO.class);
        ListTeeSpecificationDTO listTeeSpecificationDTO1 = new ListTeeSpecificationDTO();
        listTeeSpecificationDTO1.setId(1L);
        ListTeeSpecificationDTO listTeeSpecificationDTO2 = new ListTeeSpecificationDTO();
        assertThat(listTeeSpecificationDTO1).isNotEqualTo(listTeeSpecificationDTO2);
        listTeeSpecificationDTO2.setId(listTeeSpecificationDTO1.getId());
        assertThat(listTeeSpecificationDTO1).isEqualTo(listTeeSpecificationDTO2);
        listTeeSpecificationDTO2.setId(2L);
        assertThat(listTeeSpecificationDTO1).isNotEqualTo(listTeeSpecificationDTO2);
        listTeeSpecificationDTO1.setId(null);
        assertThat(listTeeSpecificationDTO1).isNotEqualTo(listTeeSpecificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listTeeSpecificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listTeeSpecificationMapper.fromId(null)).isNull();
    }
}
