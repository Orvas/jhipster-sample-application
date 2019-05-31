package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListDfctPosType;
import io.github.jhipster.application.repository.ListDfctPosTypeRepository;
import io.github.jhipster.application.service.ListDfctPosTypeService;
import io.github.jhipster.application.service.dto.ListDfctPosTypeDTO;
import io.github.jhipster.application.service.mapper.ListDfctPosTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListDfctPosTypeCriteria;
import io.github.jhipster.application.service.ListDfctPosTypeQueryService;

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
 * Integration tests for the {@Link ListDfctPosTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListDfctPosTypeResourceIT {

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
    private ListDfctPosTypeRepository listDfctPosTypeRepository;

    @Autowired
    private ListDfctPosTypeMapper listDfctPosTypeMapper;

    @Autowired
    private ListDfctPosTypeService listDfctPosTypeService;

    @Autowired
    private ListDfctPosTypeQueryService listDfctPosTypeQueryService;

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

    private MockMvc restListDfctPosTypeMockMvc;

    private ListDfctPosType listDfctPosType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListDfctPosTypeResource listDfctPosTypeResource = new ListDfctPosTypeResource(listDfctPosTypeService, listDfctPosTypeQueryService);
        this.restListDfctPosTypeMockMvc = MockMvcBuilders.standaloneSetup(listDfctPosTypeResource)
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
    public static ListDfctPosType createEntity(EntityManager em) {
        ListDfctPosType listDfctPosType = new ListDfctPosType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listDfctPosType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListDfctPosType createUpdatedEntity(EntityManager em) {
        ListDfctPosType listDfctPosType = new ListDfctPosType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listDfctPosType;
    }

    @BeforeEach
    public void initTest() {
        listDfctPosType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListDfctPosType() throws Exception {
        int databaseSizeBeforeCreate = listDfctPosTypeRepository.findAll().size();

        // Create the ListDfctPosType
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(listDfctPosType);
        restListDfctPosTypeMockMvc.perform(post("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListDfctPosType in the database
        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListDfctPosType testListDfctPosType = listDfctPosTypeList.get(listDfctPosTypeList.size() - 1);
        assertThat(testListDfctPosType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListDfctPosType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListDfctPosType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListDfctPosType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListDfctPosType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListDfctPosType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListDfctPosType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListDfctPosType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListDfctPosType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListDfctPosTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listDfctPosTypeRepository.findAll().size();

        // Create the ListDfctPosType with an existing ID
        listDfctPosType.setId(1L);
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(listDfctPosType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListDfctPosTypeMockMvc.perform(post("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListDfctPosType in the database
        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctPosTypeRepository.findAll().size();
        // set the field null
        listDfctPosType.setCode(null);

        // Create the ListDfctPosType, which fails.
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(listDfctPosType);

        restListDfctPosTypeMockMvc.perform(post("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctPosTypeRepository.findAll().size();
        // set the field null
        listDfctPosType.setName(null);

        // Create the ListDfctPosType, which fails.
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(listDfctPosType);

        restListDfctPosTypeMockMvc.perform(post("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctPosTypeRepository.findAll().size();
        // set the field null
        listDfctPosType.setFullName(null);

        // Create the ListDfctPosType, which fails.
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(listDfctPosType);

        restListDfctPosTypeMockMvc.perform(post("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctPosTypeRepository.findAll().size();
        // set the field null
        listDfctPosType.setIsCurrentFlag(null);

        // Create the ListDfctPosType, which fails.
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(listDfctPosType);

        restListDfctPosTypeMockMvc.perform(post("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypes() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList
        restListDfctPosTypeMockMvc.perform(get("/api/list-dfct-pos-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listDfctPosType.getId().intValue())))
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
    public void getListDfctPosType() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get the listDfctPosType
        restListDfctPosTypeMockMvc.perform(get("/api/list-dfct-pos-types/{id}", listDfctPosType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listDfctPosType.getId().intValue()))
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
    public void getAllListDfctPosTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where code equals to DEFAULT_CODE
        defaultListDfctPosTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listDfctPosTypeList where code equals to UPDATED_CODE
        defaultListDfctPosTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListDfctPosTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listDfctPosTypeList where code equals to UPDATED_CODE
        defaultListDfctPosTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where code is not null
        defaultListDfctPosTypeShouldBeFound("code.specified=true");

        // Get all the listDfctPosTypeList where code is null
        defaultListDfctPosTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where name equals to DEFAULT_NAME
        defaultListDfctPosTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listDfctPosTypeList where name equals to UPDATED_NAME
        defaultListDfctPosTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListDfctPosTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listDfctPosTypeList where name equals to UPDATED_NAME
        defaultListDfctPosTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where name is not null
        defaultListDfctPosTypeShouldBeFound("name.specified=true");

        // Get all the listDfctPosTypeList where name is null
        defaultListDfctPosTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListDfctPosTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listDfctPosTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListDfctPosTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListDfctPosTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listDfctPosTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListDfctPosTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where fullName is not null
        defaultListDfctPosTypeShouldBeFound("fullName.specified=true");

        // Get all the listDfctPosTypeList where fullName is null
        defaultListDfctPosTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctPosTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listDfctPosTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where isCurrentFlag is not null
        defaultListDfctPosTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listDfctPosTypeList where isCurrentFlag is null
        defaultListDfctPosTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctPosTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctPosTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctPosTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListDfctPosTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListDfctPosTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listDfctPosTypeList where description equals to UPDATED_DESCRIPTION
        defaultListDfctPosTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListDfctPosTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listDfctPosTypeList where description equals to UPDATED_DESCRIPTION
        defaultListDfctPosTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where description is not null
        defaultListDfctPosTypeShouldBeFound("description.specified=true");

        // Get all the listDfctPosTypeList where description is null
        defaultListDfctPosTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListDfctPosTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listDfctPosTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListDfctPosTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListDfctPosTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listDfctPosTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListDfctPosTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where dateCreate is not null
        defaultListDfctPosTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listDfctPosTypeList where dateCreate is null
        defaultListDfctPosTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListDfctPosTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listDfctPosTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListDfctPosTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListDfctPosTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listDfctPosTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListDfctPosTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where dateEdit is not null
        defaultListDfctPosTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listDfctPosTypeList where dateEdit is null
        defaultListDfctPosTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where creator equals to DEFAULT_CREATOR
        defaultListDfctPosTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listDfctPosTypeList where creator equals to UPDATED_CREATOR
        defaultListDfctPosTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListDfctPosTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listDfctPosTypeList where creator equals to UPDATED_CREATOR
        defaultListDfctPosTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where creator is not null
        defaultListDfctPosTypeShouldBeFound("creator.specified=true");

        // Get all the listDfctPosTypeList where creator is null
        defaultListDfctPosTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where editor equals to DEFAULT_EDITOR
        defaultListDfctPosTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listDfctPosTypeList where editor equals to UPDATED_EDITOR
        defaultListDfctPosTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListDfctPosTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listDfctPosTypeList where editor equals to UPDATED_EDITOR
        defaultListDfctPosTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListDfctPosTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        // Get all the listDfctPosTypeList where editor is not null
        defaultListDfctPosTypeShouldBeFound("editor.specified=true");

        // Get all the listDfctPosTypeList where editor is null
        defaultListDfctPosTypeShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListDfctPosTypeShouldBeFound(String filter) throws Exception {
        restListDfctPosTypeMockMvc.perform(get("/api/list-dfct-pos-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listDfctPosType.getId().intValue())))
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
        restListDfctPosTypeMockMvc.perform(get("/api/list-dfct-pos-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListDfctPosTypeShouldNotBeFound(String filter) throws Exception {
        restListDfctPosTypeMockMvc.perform(get("/api/list-dfct-pos-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListDfctPosTypeMockMvc.perform(get("/api/list-dfct-pos-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListDfctPosType() throws Exception {
        // Get the listDfctPosType
        restListDfctPosTypeMockMvc.perform(get("/api/list-dfct-pos-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListDfctPosType() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        int databaseSizeBeforeUpdate = listDfctPosTypeRepository.findAll().size();

        // Update the listDfctPosType
        ListDfctPosType updatedListDfctPosType = listDfctPosTypeRepository.findById(listDfctPosType.getId()).get();
        // Disconnect from session so that the updates on updatedListDfctPosType are not directly saved in db
        em.detach(updatedListDfctPosType);
        updatedListDfctPosType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(updatedListDfctPosType);

        restListDfctPosTypeMockMvc.perform(put("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListDfctPosType in the database
        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeUpdate);
        ListDfctPosType testListDfctPosType = listDfctPosTypeList.get(listDfctPosTypeList.size() - 1);
        assertThat(testListDfctPosType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListDfctPosType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListDfctPosType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListDfctPosType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListDfctPosType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListDfctPosType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListDfctPosType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListDfctPosType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListDfctPosType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListDfctPosType() throws Exception {
        int databaseSizeBeforeUpdate = listDfctPosTypeRepository.findAll().size();

        // Create the ListDfctPosType
        ListDfctPosTypeDTO listDfctPosTypeDTO = listDfctPosTypeMapper.toDto(listDfctPosType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListDfctPosTypeMockMvc.perform(put("/api/list-dfct-pos-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctPosTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListDfctPosType in the database
        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListDfctPosType() throws Exception {
        // Initialize the database
        listDfctPosTypeRepository.saveAndFlush(listDfctPosType);

        int databaseSizeBeforeDelete = listDfctPosTypeRepository.findAll().size();

        // Delete the listDfctPosType
        restListDfctPosTypeMockMvc.perform(delete("/api/list-dfct-pos-types/{id}", listDfctPosType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListDfctPosType> listDfctPosTypeList = listDfctPosTypeRepository.findAll();
        assertThat(listDfctPosTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListDfctPosType.class);
        ListDfctPosType listDfctPosType1 = new ListDfctPosType();
        listDfctPosType1.setId(1L);
        ListDfctPosType listDfctPosType2 = new ListDfctPosType();
        listDfctPosType2.setId(listDfctPosType1.getId());
        assertThat(listDfctPosType1).isEqualTo(listDfctPosType2);
        listDfctPosType2.setId(2L);
        assertThat(listDfctPosType1).isNotEqualTo(listDfctPosType2);
        listDfctPosType1.setId(null);
        assertThat(listDfctPosType1).isNotEqualTo(listDfctPosType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListDfctPosTypeDTO.class);
        ListDfctPosTypeDTO listDfctPosTypeDTO1 = new ListDfctPosTypeDTO();
        listDfctPosTypeDTO1.setId(1L);
        ListDfctPosTypeDTO listDfctPosTypeDTO2 = new ListDfctPosTypeDTO();
        assertThat(listDfctPosTypeDTO1).isNotEqualTo(listDfctPosTypeDTO2);
        listDfctPosTypeDTO2.setId(listDfctPosTypeDTO1.getId());
        assertThat(listDfctPosTypeDTO1).isEqualTo(listDfctPosTypeDTO2);
        listDfctPosTypeDTO2.setId(2L);
        assertThat(listDfctPosTypeDTO1).isNotEqualTo(listDfctPosTypeDTO2);
        listDfctPosTypeDTO1.setId(null);
        assertThat(listDfctPosTypeDTO1).isNotEqualTo(listDfctPosTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listDfctPosTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listDfctPosTypeMapper.fromId(null)).isNull();
    }
}
