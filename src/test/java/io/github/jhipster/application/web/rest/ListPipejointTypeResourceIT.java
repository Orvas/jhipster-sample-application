package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListPipejointType;
import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.repository.ListPipejointTypeRepository;
import io.github.jhipster.application.service.ListPipejointTypeService;
import io.github.jhipster.application.service.dto.ListPipejointTypeDTO;
import io.github.jhipster.application.service.mapper.ListPipejointTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListPipejointTypeCriteria;
import io.github.jhipster.application.service.ListPipejointTypeQueryService;

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
 * Integration tests for the {@Link ListPipejointTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListPipejointTypeResourceIT {

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
    private ListPipejointTypeRepository listPipejointTypeRepository;

    @Autowired
    private ListPipejointTypeMapper listPipejointTypeMapper;

    @Autowired
    private ListPipejointTypeService listPipejointTypeService;

    @Autowired
    private ListPipejointTypeQueryService listPipejointTypeQueryService;

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

    private MockMvc restListPipejointTypeMockMvc;

    private ListPipejointType listPipejointType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListPipejointTypeResource listPipejointTypeResource = new ListPipejointTypeResource(listPipejointTypeService, listPipejointTypeQueryService);
        this.restListPipejointTypeMockMvc = MockMvcBuilders.standaloneSetup(listPipejointTypeResource)
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
    public static ListPipejointType createEntity(EntityManager em) {
        ListPipejointType listPipejointType = new ListPipejointType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listPipejointType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListPipejointType createUpdatedEntity(EntityManager em) {
        ListPipejointType listPipejointType = new ListPipejointType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listPipejointType;
    }

    @BeforeEach
    public void initTest() {
        listPipejointType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListPipejointType() throws Exception {
        int databaseSizeBeforeCreate = listPipejointTypeRepository.findAll().size();

        // Create the ListPipejointType
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(listPipejointType);
        restListPipejointTypeMockMvc.perform(post("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListPipejointType in the database
        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListPipejointType testListPipejointType = listPipejointTypeList.get(listPipejointTypeList.size() - 1);
        assertThat(testListPipejointType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListPipejointType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListPipejointType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListPipejointType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListPipejointType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListPipejointType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListPipejointType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListPipejointType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListPipejointType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListPipejointTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listPipejointTypeRepository.findAll().size();

        // Create the ListPipejointType with an existing ID
        listPipejointType.setId(1L);
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(listPipejointType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListPipejointTypeMockMvc.perform(post("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipejointType in the database
        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointTypeRepository.findAll().size();
        // set the field null
        listPipejointType.setCode(null);

        // Create the ListPipejointType, which fails.
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(listPipejointType);

        restListPipejointTypeMockMvc.perform(post("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointTypeRepository.findAll().size();
        // set the field null
        listPipejointType.setName(null);

        // Create the ListPipejointType, which fails.
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(listPipejointType);

        restListPipejointTypeMockMvc.perform(post("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointTypeRepository.findAll().size();
        // set the field null
        listPipejointType.setFullName(null);

        // Create the ListPipejointType, which fails.
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(listPipejointType);

        restListPipejointTypeMockMvc.perform(post("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointTypeRepository.findAll().size();
        // set the field null
        listPipejointType.setIsCurrentFlag(null);

        // Create the ListPipejointType, which fails.
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(listPipejointType);

        restListPipejointTypeMockMvc.perform(post("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypes() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList
        restListPipejointTypeMockMvc.perform(get("/api/list-pipejoint-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipejointType.getId().intValue())))
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
    public void getListPipejointType() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get the listPipejointType
        restListPipejointTypeMockMvc.perform(get("/api/list-pipejoint-types/{id}", listPipejointType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listPipejointType.getId().intValue()))
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
    public void getAllListPipejointTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where code equals to DEFAULT_CODE
        defaultListPipejointTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listPipejointTypeList where code equals to UPDATED_CODE
        defaultListPipejointTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListPipejointTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listPipejointTypeList where code equals to UPDATED_CODE
        defaultListPipejointTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where code is not null
        defaultListPipejointTypeShouldBeFound("code.specified=true");

        // Get all the listPipejointTypeList where code is null
        defaultListPipejointTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where name equals to DEFAULT_NAME
        defaultListPipejointTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listPipejointTypeList where name equals to UPDATED_NAME
        defaultListPipejointTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListPipejointTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listPipejointTypeList where name equals to UPDATED_NAME
        defaultListPipejointTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where name is not null
        defaultListPipejointTypeShouldBeFound("name.specified=true");

        // Get all the listPipejointTypeList where name is null
        defaultListPipejointTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListPipejointTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listPipejointTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListPipejointTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListPipejointTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listPipejointTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListPipejointTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where fullName is not null
        defaultListPipejointTypeShouldBeFound("fullName.specified=true");

        // Get all the listPipejointTypeList where fullName is null
        defaultListPipejointTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipejointTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listPipejointTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where isCurrentFlag is not null
        defaultListPipejointTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listPipejointTypeList where isCurrentFlag is null
        defaultListPipejointTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipejointTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipejointTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListPipejointTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListPipejointTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listPipejointTypeList where description equals to UPDATED_DESCRIPTION
        defaultListPipejointTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListPipejointTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listPipejointTypeList where description equals to UPDATED_DESCRIPTION
        defaultListPipejointTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where description is not null
        defaultListPipejointTypeShouldBeFound("description.specified=true");

        // Get all the listPipejointTypeList where description is null
        defaultListPipejointTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListPipejointTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listPipejointTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipejointTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListPipejointTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listPipejointTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipejointTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where dateCreate is not null
        defaultListPipejointTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listPipejointTypeList where dateCreate is null
        defaultListPipejointTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListPipejointTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listPipejointTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipejointTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListPipejointTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listPipejointTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipejointTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where dateEdit is not null
        defaultListPipejointTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listPipejointTypeList where dateEdit is null
        defaultListPipejointTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where creator equals to DEFAULT_CREATOR
        defaultListPipejointTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listPipejointTypeList where creator equals to UPDATED_CREATOR
        defaultListPipejointTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListPipejointTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listPipejointTypeList where creator equals to UPDATED_CREATOR
        defaultListPipejointTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where creator is not null
        defaultListPipejointTypeShouldBeFound("creator.specified=true");

        // Get all the listPipejointTypeList where creator is null
        defaultListPipejointTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where editor equals to DEFAULT_EDITOR
        defaultListPipejointTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listPipejointTypeList where editor equals to UPDATED_EDITOR
        defaultListPipejointTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListPipejointTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listPipejointTypeList where editor equals to UPDATED_EDITOR
        defaultListPipejointTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        // Get all the listPipejointTypeList where editor is not null
        defaultListPipejointTypeShouldBeFound("editor.specified=true");

        // Get all the listPipejointTypeList where editor is null
        defaultListPipejointTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointTypesByPipejointHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipejointHist pipejointHist = PipejointHistResourceIT.createEntity(em);
        em.persist(pipejointHist);
        em.flush();
        listPipejointType.addPipejointHist(pipejointHist);
        listPipejointTypeRepository.saveAndFlush(listPipejointType);
        Long pipejointHistId = pipejointHist.getId();

        // Get all the listPipejointTypeList where pipejointHist equals to pipejointHistId
        defaultListPipejointTypeShouldBeFound("pipejointHistId.equals=" + pipejointHistId);

        // Get all the listPipejointTypeList where pipejointHist equals to pipejointHistId + 1
        defaultListPipejointTypeShouldNotBeFound("pipejointHistId.equals=" + (pipejointHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListPipejointTypeShouldBeFound(String filter) throws Exception {
        restListPipejointTypeMockMvc.perform(get("/api/list-pipejoint-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipejointType.getId().intValue())))
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
        restListPipejointTypeMockMvc.perform(get("/api/list-pipejoint-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListPipejointTypeShouldNotBeFound(String filter) throws Exception {
        restListPipejointTypeMockMvc.perform(get("/api/list-pipejoint-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListPipejointTypeMockMvc.perform(get("/api/list-pipejoint-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListPipejointType() throws Exception {
        // Get the listPipejointType
        restListPipejointTypeMockMvc.perform(get("/api/list-pipejoint-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListPipejointType() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        int databaseSizeBeforeUpdate = listPipejointTypeRepository.findAll().size();

        // Update the listPipejointType
        ListPipejointType updatedListPipejointType = listPipejointTypeRepository.findById(listPipejointType.getId()).get();
        // Disconnect from session so that the updates on updatedListPipejointType are not directly saved in db
        em.detach(updatedListPipejointType);
        updatedListPipejointType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(updatedListPipejointType);

        restListPipejointTypeMockMvc.perform(put("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListPipejointType in the database
        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeUpdate);
        ListPipejointType testListPipejointType = listPipejointTypeList.get(listPipejointTypeList.size() - 1);
        assertThat(testListPipejointType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListPipejointType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListPipejointType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListPipejointType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListPipejointType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListPipejointType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListPipejointType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListPipejointType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListPipejointType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListPipejointType() throws Exception {
        int databaseSizeBeforeUpdate = listPipejointTypeRepository.findAll().size();

        // Create the ListPipejointType
        ListPipejointTypeDTO listPipejointTypeDTO = listPipejointTypeMapper.toDto(listPipejointType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListPipejointTypeMockMvc.perform(put("/api/list-pipejoint-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipejointType in the database
        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListPipejointType() throws Exception {
        // Initialize the database
        listPipejointTypeRepository.saveAndFlush(listPipejointType);

        int databaseSizeBeforeDelete = listPipejointTypeRepository.findAll().size();

        // Delete the listPipejointType
        restListPipejointTypeMockMvc.perform(delete("/api/list-pipejoint-types/{id}", listPipejointType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListPipejointType> listPipejointTypeList = listPipejointTypeRepository.findAll();
        assertThat(listPipejointTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipejointType.class);
        ListPipejointType listPipejointType1 = new ListPipejointType();
        listPipejointType1.setId(1L);
        ListPipejointType listPipejointType2 = new ListPipejointType();
        listPipejointType2.setId(listPipejointType1.getId());
        assertThat(listPipejointType1).isEqualTo(listPipejointType2);
        listPipejointType2.setId(2L);
        assertThat(listPipejointType1).isNotEqualTo(listPipejointType2);
        listPipejointType1.setId(null);
        assertThat(listPipejointType1).isNotEqualTo(listPipejointType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipejointTypeDTO.class);
        ListPipejointTypeDTO listPipejointTypeDTO1 = new ListPipejointTypeDTO();
        listPipejointTypeDTO1.setId(1L);
        ListPipejointTypeDTO listPipejointTypeDTO2 = new ListPipejointTypeDTO();
        assertThat(listPipejointTypeDTO1).isNotEqualTo(listPipejointTypeDTO2);
        listPipejointTypeDTO2.setId(listPipejointTypeDTO1.getId());
        assertThat(listPipejointTypeDTO1).isEqualTo(listPipejointTypeDTO2);
        listPipejointTypeDTO2.setId(2L);
        assertThat(listPipejointTypeDTO1).isNotEqualTo(listPipejointTypeDTO2);
        listPipejointTypeDTO1.setId(null);
        assertThat(listPipejointTypeDTO1).isNotEqualTo(listPipejointTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listPipejointTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listPipejointTypeMapper.fromId(null)).isNull();
    }
}
