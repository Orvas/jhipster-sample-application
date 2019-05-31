package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListBendSpecification;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.repository.ListBendSpecificationRepository;
import io.github.jhipster.application.service.ListBendSpecificationService;
import io.github.jhipster.application.service.dto.ListBendSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListBendSpecificationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListBendSpecificationCriteria;
import io.github.jhipster.application.service.ListBendSpecificationQueryService;

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
 * Integration tests for the {@Link ListBendSpecificationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListBendSpecificationResourceIT {

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
    private ListBendSpecificationRepository listBendSpecificationRepository;

    @Autowired
    private ListBendSpecificationMapper listBendSpecificationMapper;

    @Autowired
    private ListBendSpecificationService listBendSpecificationService;

    @Autowired
    private ListBendSpecificationQueryService listBendSpecificationQueryService;

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

    private MockMvc restListBendSpecificationMockMvc;

    private ListBendSpecification listBendSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListBendSpecificationResource listBendSpecificationResource = new ListBendSpecificationResource(listBendSpecificationService, listBendSpecificationQueryService);
        this.restListBendSpecificationMockMvc = MockMvcBuilders.standaloneSetup(listBendSpecificationResource)
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
    public static ListBendSpecification createEntity(EntityManager em) {
        ListBendSpecification listBendSpecification = new ListBendSpecification()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listBendSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListBendSpecification createUpdatedEntity(EntityManager em) {
        ListBendSpecification listBendSpecification = new ListBendSpecification()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listBendSpecification;
    }

    @BeforeEach
    public void initTest() {
        listBendSpecification = createEntity(em);
    }

    @Test
    @Transactional
    public void createListBendSpecification() throws Exception {
        int databaseSizeBeforeCreate = listBendSpecificationRepository.findAll().size();

        // Create the ListBendSpecification
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(listBendSpecification);
        restListBendSpecificationMockMvc.perform(post("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListBendSpecification in the database
        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ListBendSpecification testListBendSpecification = listBendSpecificationList.get(listBendSpecificationList.size() - 1);
        assertThat(testListBendSpecification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListBendSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListBendSpecification.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListBendSpecification.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListBendSpecification.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListBendSpecification.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListBendSpecification.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListBendSpecification.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListBendSpecification.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListBendSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listBendSpecificationRepository.findAll().size();

        // Create the ListBendSpecification with an existing ID
        listBendSpecification.setId(1L);
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(listBendSpecification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListBendSpecificationMockMvc.perform(post("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBendSpecification in the database
        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendSpecificationRepository.findAll().size();
        // set the field null
        listBendSpecification.setCode(null);

        // Create the ListBendSpecification, which fails.
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(listBendSpecification);

        restListBendSpecificationMockMvc.perform(post("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendSpecificationRepository.findAll().size();
        // set the field null
        listBendSpecification.setName(null);

        // Create the ListBendSpecification, which fails.
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(listBendSpecification);

        restListBendSpecificationMockMvc.perform(post("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendSpecificationRepository.findAll().size();
        // set the field null
        listBendSpecification.setFullName(null);

        // Create the ListBendSpecification, which fails.
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(listBendSpecification);

        restListBendSpecificationMockMvc.perform(post("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendSpecificationRepository.findAll().size();
        // set the field null
        listBendSpecification.setIsCurrentFlag(null);

        // Create the ListBendSpecification, which fails.
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(listBendSpecification);

        restListBendSpecificationMockMvc.perform(post("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListBendSpecifications() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList
        restListBendSpecificationMockMvc.perform(get("/api/list-bend-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBendSpecification.getId().intValue())))
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
    public void getListBendSpecification() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get the listBendSpecification
        restListBendSpecificationMockMvc.perform(get("/api/list-bend-specifications/{id}", listBendSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listBendSpecification.getId().intValue()))
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
    public void getAllListBendSpecificationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where code equals to DEFAULT_CODE
        defaultListBendSpecificationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listBendSpecificationList where code equals to UPDATED_CODE
        defaultListBendSpecificationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListBendSpecificationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listBendSpecificationList where code equals to UPDATED_CODE
        defaultListBendSpecificationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where code is not null
        defaultListBendSpecificationShouldBeFound("code.specified=true");

        // Get all the listBendSpecificationList where code is null
        defaultListBendSpecificationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where name equals to DEFAULT_NAME
        defaultListBendSpecificationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listBendSpecificationList where name equals to UPDATED_NAME
        defaultListBendSpecificationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListBendSpecificationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listBendSpecificationList where name equals to UPDATED_NAME
        defaultListBendSpecificationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where name is not null
        defaultListBendSpecificationShouldBeFound("name.specified=true");

        // Get all the listBendSpecificationList where name is null
        defaultListBendSpecificationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where fullName equals to DEFAULT_FULL_NAME
        defaultListBendSpecificationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listBendSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListBendSpecificationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListBendSpecificationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listBendSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListBendSpecificationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where fullName is not null
        defaultListBendSpecificationShouldBeFound("fullName.specified=true");

        // Get all the listBendSpecificationList where fullName is null
        defaultListBendSpecificationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listBendSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where isCurrentFlag is not null
        defaultListBendSpecificationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listBendSpecificationList where isCurrentFlag is null
        defaultListBendSpecificationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendSpecificationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendSpecificationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendSpecificationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListBendSpecificationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where description equals to DEFAULT_DESCRIPTION
        defaultListBendSpecificationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listBendSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListBendSpecificationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListBendSpecificationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listBendSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListBendSpecificationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where description is not null
        defaultListBendSpecificationShouldBeFound("description.specified=true");

        // Get all the listBendSpecificationList where description is null
        defaultListBendSpecificationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListBendSpecificationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listBendSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBendSpecificationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListBendSpecificationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listBendSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBendSpecificationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where dateCreate is not null
        defaultListBendSpecificationShouldBeFound("dateCreate.specified=true");

        // Get all the listBendSpecificationList where dateCreate is null
        defaultListBendSpecificationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListBendSpecificationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listBendSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBendSpecificationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListBendSpecificationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listBendSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBendSpecificationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where dateEdit is not null
        defaultListBendSpecificationShouldBeFound("dateEdit.specified=true");

        // Get all the listBendSpecificationList where dateEdit is null
        defaultListBendSpecificationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where creator equals to DEFAULT_CREATOR
        defaultListBendSpecificationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listBendSpecificationList where creator equals to UPDATED_CREATOR
        defaultListBendSpecificationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListBendSpecificationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listBendSpecificationList where creator equals to UPDATED_CREATOR
        defaultListBendSpecificationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where creator is not null
        defaultListBendSpecificationShouldBeFound("creator.specified=true");

        // Get all the listBendSpecificationList where creator is null
        defaultListBendSpecificationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where editor equals to DEFAULT_EDITOR
        defaultListBendSpecificationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listBendSpecificationList where editor equals to UPDATED_EDITOR
        defaultListBendSpecificationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListBendSpecificationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listBendSpecificationList where editor equals to UPDATED_EDITOR
        defaultListBendSpecificationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        // Get all the listBendSpecificationList where editor is not null
        defaultListBendSpecificationShouldBeFound("editor.specified=true");

        // Get all the listBendSpecificationList where editor is null
        defaultListBendSpecificationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendSpecificationsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listBendSpecification.addBendHist(bendHist);
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);
        Long bendHistId = bendHist.getId();

        // Get all the listBendSpecificationList where bendHist equals to bendHistId
        defaultListBendSpecificationShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listBendSpecificationList where bendHist equals to bendHistId + 1
        defaultListBendSpecificationShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListBendSpecificationShouldBeFound(String filter) throws Exception {
        restListBendSpecificationMockMvc.perform(get("/api/list-bend-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBendSpecification.getId().intValue())))
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
        restListBendSpecificationMockMvc.perform(get("/api/list-bend-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListBendSpecificationShouldNotBeFound(String filter) throws Exception {
        restListBendSpecificationMockMvc.perform(get("/api/list-bend-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListBendSpecificationMockMvc.perform(get("/api/list-bend-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListBendSpecification() throws Exception {
        // Get the listBendSpecification
        restListBendSpecificationMockMvc.perform(get("/api/list-bend-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListBendSpecification() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        int databaseSizeBeforeUpdate = listBendSpecificationRepository.findAll().size();

        // Update the listBendSpecification
        ListBendSpecification updatedListBendSpecification = listBendSpecificationRepository.findById(listBendSpecification.getId()).get();
        // Disconnect from session so that the updates on updatedListBendSpecification are not directly saved in db
        em.detach(updatedListBendSpecification);
        updatedListBendSpecification
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(updatedListBendSpecification);

        restListBendSpecificationMockMvc.perform(put("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isOk());

        // Validate the ListBendSpecification in the database
        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ListBendSpecification testListBendSpecification = listBendSpecificationList.get(listBendSpecificationList.size() - 1);
        assertThat(testListBendSpecification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListBendSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListBendSpecification.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListBendSpecification.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListBendSpecification.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListBendSpecification.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListBendSpecification.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListBendSpecification.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListBendSpecification.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListBendSpecification() throws Exception {
        int databaseSizeBeforeUpdate = listBendSpecificationRepository.findAll().size();

        // Create the ListBendSpecification
        ListBendSpecificationDTO listBendSpecificationDTO = listBendSpecificationMapper.toDto(listBendSpecification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListBendSpecificationMockMvc.perform(put("/api/list-bend-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBendSpecification in the database
        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListBendSpecification() throws Exception {
        // Initialize the database
        listBendSpecificationRepository.saveAndFlush(listBendSpecification);

        int databaseSizeBeforeDelete = listBendSpecificationRepository.findAll().size();

        // Delete the listBendSpecification
        restListBendSpecificationMockMvc.perform(delete("/api/list-bend-specifications/{id}", listBendSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListBendSpecification> listBendSpecificationList = listBendSpecificationRepository.findAll();
        assertThat(listBendSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBendSpecification.class);
        ListBendSpecification listBendSpecification1 = new ListBendSpecification();
        listBendSpecification1.setId(1L);
        ListBendSpecification listBendSpecification2 = new ListBendSpecification();
        listBendSpecification2.setId(listBendSpecification1.getId());
        assertThat(listBendSpecification1).isEqualTo(listBendSpecification2);
        listBendSpecification2.setId(2L);
        assertThat(listBendSpecification1).isNotEqualTo(listBendSpecification2);
        listBendSpecification1.setId(null);
        assertThat(listBendSpecification1).isNotEqualTo(listBendSpecification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBendSpecificationDTO.class);
        ListBendSpecificationDTO listBendSpecificationDTO1 = new ListBendSpecificationDTO();
        listBendSpecificationDTO1.setId(1L);
        ListBendSpecificationDTO listBendSpecificationDTO2 = new ListBendSpecificationDTO();
        assertThat(listBendSpecificationDTO1).isNotEqualTo(listBendSpecificationDTO2);
        listBendSpecificationDTO2.setId(listBendSpecificationDTO1.getId());
        assertThat(listBendSpecificationDTO1).isEqualTo(listBendSpecificationDTO2);
        listBendSpecificationDTO2.setId(2L);
        assertThat(listBendSpecificationDTO1).isNotEqualTo(listBendSpecificationDTO2);
        listBendSpecificationDTO1.setId(null);
        assertThat(listBendSpecificationDTO1).isNotEqualTo(listBendSpecificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listBendSpecificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listBendSpecificationMapper.fromId(null)).isNull();
    }
}
