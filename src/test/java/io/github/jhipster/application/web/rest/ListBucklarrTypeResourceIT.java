package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListBucklarrType;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.repository.ListBucklarrTypeRepository;
import io.github.jhipster.application.service.ListBucklarrTypeService;
import io.github.jhipster.application.service.dto.ListBucklarrTypeDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListBucklarrTypeCriteria;
import io.github.jhipster.application.service.ListBucklarrTypeQueryService;

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
 * Integration tests for the {@Link ListBucklarrTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListBucklarrTypeResourceIT {

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
    private ListBucklarrTypeRepository listBucklarrTypeRepository;

    @Autowired
    private ListBucklarrTypeMapper listBucklarrTypeMapper;

    @Autowired
    private ListBucklarrTypeService listBucklarrTypeService;

    @Autowired
    private ListBucklarrTypeQueryService listBucklarrTypeQueryService;

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

    private MockMvc restListBucklarrTypeMockMvc;

    private ListBucklarrType listBucklarrType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListBucklarrTypeResource listBucklarrTypeResource = new ListBucklarrTypeResource(listBucklarrTypeService, listBucklarrTypeQueryService);
        this.restListBucklarrTypeMockMvc = MockMvcBuilders.standaloneSetup(listBucklarrTypeResource)
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
    public static ListBucklarrType createEntity(EntityManager em) {
        ListBucklarrType listBucklarrType = new ListBucklarrType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listBucklarrType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListBucklarrType createUpdatedEntity(EntityManager em) {
        ListBucklarrType listBucklarrType = new ListBucklarrType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listBucklarrType;
    }

    @BeforeEach
    public void initTest() {
        listBucklarrType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListBucklarrType() throws Exception {
        int databaseSizeBeforeCreate = listBucklarrTypeRepository.findAll().size();

        // Create the ListBucklarrType
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(listBucklarrType);
        restListBucklarrTypeMockMvc.perform(post("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListBucklarrType in the database
        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListBucklarrType testListBucklarrType = listBucklarrTypeList.get(listBucklarrTypeList.size() - 1);
        assertThat(testListBucklarrType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListBucklarrType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListBucklarrType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListBucklarrType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListBucklarrType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListBucklarrType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListBucklarrType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListBucklarrType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListBucklarrType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListBucklarrTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listBucklarrTypeRepository.findAll().size();

        // Create the ListBucklarrType with an existing ID
        listBucklarrType.setId(1L);
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(listBucklarrType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListBucklarrTypeMockMvc.perform(post("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBucklarrType in the database
        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrTypeRepository.findAll().size();
        // set the field null
        listBucklarrType.setCode(null);

        // Create the ListBucklarrType, which fails.
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(listBucklarrType);

        restListBucklarrTypeMockMvc.perform(post("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrTypeRepository.findAll().size();
        // set the field null
        listBucklarrType.setName(null);

        // Create the ListBucklarrType, which fails.
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(listBucklarrType);

        restListBucklarrTypeMockMvc.perform(post("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrTypeRepository.findAll().size();
        // set the field null
        listBucklarrType.setFullName(null);

        // Create the ListBucklarrType, which fails.
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(listBucklarrType);

        restListBucklarrTypeMockMvc.perform(post("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrTypeRepository.findAll().size();
        // set the field null
        listBucklarrType.setIsCurrentFlag(null);

        // Create the ListBucklarrType, which fails.
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(listBucklarrType);

        restListBucklarrTypeMockMvc.perform(post("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypes() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList
        restListBucklarrTypeMockMvc.perform(get("/api/list-bucklarr-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBucklarrType.getId().intValue())))
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
    public void getListBucklarrType() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get the listBucklarrType
        restListBucklarrTypeMockMvc.perform(get("/api/list-bucklarr-types/{id}", listBucklarrType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listBucklarrType.getId().intValue()))
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
    public void getAllListBucklarrTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where code equals to DEFAULT_CODE
        defaultListBucklarrTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listBucklarrTypeList where code equals to UPDATED_CODE
        defaultListBucklarrTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListBucklarrTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listBucklarrTypeList where code equals to UPDATED_CODE
        defaultListBucklarrTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where code is not null
        defaultListBucklarrTypeShouldBeFound("code.specified=true");

        // Get all the listBucklarrTypeList where code is null
        defaultListBucklarrTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where name equals to DEFAULT_NAME
        defaultListBucklarrTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listBucklarrTypeList where name equals to UPDATED_NAME
        defaultListBucklarrTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListBucklarrTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listBucklarrTypeList where name equals to UPDATED_NAME
        defaultListBucklarrTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where name is not null
        defaultListBucklarrTypeShouldBeFound("name.specified=true");

        // Get all the listBucklarrTypeList where name is null
        defaultListBucklarrTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListBucklarrTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listBucklarrTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListBucklarrTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListBucklarrTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listBucklarrTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListBucklarrTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where fullName is not null
        defaultListBucklarrTypeShouldBeFound("fullName.specified=true");

        // Get all the listBucklarrTypeList where fullName is null
        defaultListBucklarrTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listBucklarrTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where isCurrentFlag is not null
        defaultListBucklarrTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listBucklarrTypeList where isCurrentFlag is null
        defaultListBucklarrTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListBucklarrTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListBucklarrTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listBucklarrTypeList where description equals to UPDATED_DESCRIPTION
        defaultListBucklarrTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListBucklarrTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listBucklarrTypeList where description equals to UPDATED_DESCRIPTION
        defaultListBucklarrTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where description is not null
        defaultListBucklarrTypeShouldBeFound("description.specified=true");

        // Get all the listBucklarrTypeList where description is null
        defaultListBucklarrTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListBucklarrTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listBucklarrTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBucklarrTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListBucklarrTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listBucklarrTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBucklarrTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where dateCreate is not null
        defaultListBucklarrTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listBucklarrTypeList where dateCreate is null
        defaultListBucklarrTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListBucklarrTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listBucklarrTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBucklarrTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListBucklarrTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listBucklarrTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBucklarrTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where dateEdit is not null
        defaultListBucklarrTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listBucklarrTypeList where dateEdit is null
        defaultListBucklarrTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where creator equals to DEFAULT_CREATOR
        defaultListBucklarrTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listBucklarrTypeList where creator equals to UPDATED_CREATOR
        defaultListBucklarrTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListBucklarrTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listBucklarrTypeList where creator equals to UPDATED_CREATOR
        defaultListBucklarrTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where creator is not null
        defaultListBucklarrTypeShouldBeFound("creator.specified=true");

        // Get all the listBucklarrTypeList where creator is null
        defaultListBucklarrTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where editor equals to DEFAULT_EDITOR
        defaultListBucklarrTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listBucklarrTypeList where editor equals to UPDATED_EDITOR
        defaultListBucklarrTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListBucklarrTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listBucklarrTypeList where editor equals to UPDATED_EDITOR
        defaultListBucklarrTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        // Get all the listBucklarrTypeList where editor is not null
        defaultListBucklarrTypeShouldBeFound("editor.specified=true");

        // Get all the listBucklarrTypeList where editor is null
        defaultListBucklarrTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrTypesByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listBucklarrType.addBuckleArrestorHist(buckleArrestorHist);
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listBucklarrTypeList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListBucklarrTypeShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listBucklarrTypeList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListBucklarrTypeShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListBucklarrTypeShouldBeFound(String filter) throws Exception {
        restListBucklarrTypeMockMvc.perform(get("/api/list-bucklarr-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBucklarrType.getId().intValue())))
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
        restListBucklarrTypeMockMvc.perform(get("/api/list-bucklarr-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListBucklarrTypeShouldNotBeFound(String filter) throws Exception {
        restListBucklarrTypeMockMvc.perform(get("/api/list-bucklarr-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListBucklarrTypeMockMvc.perform(get("/api/list-bucklarr-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListBucklarrType() throws Exception {
        // Get the listBucklarrType
        restListBucklarrTypeMockMvc.perform(get("/api/list-bucklarr-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListBucklarrType() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        int databaseSizeBeforeUpdate = listBucklarrTypeRepository.findAll().size();

        // Update the listBucklarrType
        ListBucklarrType updatedListBucklarrType = listBucklarrTypeRepository.findById(listBucklarrType.getId()).get();
        // Disconnect from session so that the updates on updatedListBucklarrType are not directly saved in db
        em.detach(updatedListBucklarrType);
        updatedListBucklarrType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(updatedListBucklarrType);

        restListBucklarrTypeMockMvc.perform(put("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListBucklarrType in the database
        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeUpdate);
        ListBucklarrType testListBucklarrType = listBucklarrTypeList.get(listBucklarrTypeList.size() - 1);
        assertThat(testListBucklarrType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListBucklarrType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListBucklarrType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListBucklarrType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListBucklarrType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListBucklarrType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListBucklarrType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListBucklarrType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListBucklarrType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListBucklarrType() throws Exception {
        int databaseSizeBeforeUpdate = listBucklarrTypeRepository.findAll().size();

        // Create the ListBucklarrType
        ListBucklarrTypeDTO listBucklarrTypeDTO = listBucklarrTypeMapper.toDto(listBucklarrType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListBucklarrTypeMockMvc.perform(put("/api/list-bucklarr-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBucklarrType in the database
        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListBucklarrType() throws Exception {
        // Initialize the database
        listBucklarrTypeRepository.saveAndFlush(listBucklarrType);

        int databaseSizeBeforeDelete = listBucklarrTypeRepository.findAll().size();

        // Delete the listBucklarrType
        restListBucklarrTypeMockMvc.perform(delete("/api/list-bucklarr-types/{id}", listBucklarrType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListBucklarrType> listBucklarrTypeList = listBucklarrTypeRepository.findAll();
        assertThat(listBucklarrTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBucklarrType.class);
        ListBucklarrType listBucklarrType1 = new ListBucklarrType();
        listBucklarrType1.setId(1L);
        ListBucklarrType listBucklarrType2 = new ListBucklarrType();
        listBucklarrType2.setId(listBucklarrType1.getId());
        assertThat(listBucklarrType1).isEqualTo(listBucklarrType2);
        listBucklarrType2.setId(2L);
        assertThat(listBucklarrType1).isNotEqualTo(listBucklarrType2);
        listBucklarrType1.setId(null);
        assertThat(listBucklarrType1).isNotEqualTo(listBucklarrType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBucklarrTypeDTO.class);
        ListBucklarrTypeDTO listBucklarrTypeDTO1 = new ListBucklarrTypeDTO();
        listBucklarrTypeDTO1.setId(1L);
        ListBucklarrTypeDTO listBucklarrTypeDTO2 = new ListBucklarrTypeDTO();
        assertThat(listBucklarrTypeDTO1).isNotEqualTo(listBucklarrTypeDTO2);
        listBucklarrTypeDTO2.setId(listBucklarrTypeDTO1.getId());
        assertThat(listBucklarrTypeDTO1).isEqualTo(listBucklarrTypeDTO2);
        listBucklarrTypeDTO2.setId(2L);
        assertThat(listBucklarrTypeDTO1).isNotEqualTo(listBucklarrTypeDTO2);
        listBucklarrTypeDTO1.setId(null);
        assertThat(listBucklarrTypeDTO1).isNotEqualTo(listBucklarrTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listBucklarrTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listBucklarrTypeMapper.fromId(null)).isNull();
    }
}
