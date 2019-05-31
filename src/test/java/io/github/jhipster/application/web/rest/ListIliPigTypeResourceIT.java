package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListIliPigType;
import io.github.jhipster.application.repository.ListIliPigTypeRepository;
import io.github.jhipster.application.service.ListIliPigTypeService;
import io.github.jhipster.application.service.dto.ListIliPigTypeDTO;
import io.github.jhipster.application.service.mapper.ListIliPigTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListIliPigTypeCriteria;
import io.github.jhipster.application.service.ListIliPigTypeQueryService;

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
 * Integration tests for the {@Link ListIliPigTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListIliPigTypeResourceIT {

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
    private ListIliPigTypeRepository listIliPigTypeRepository;

    @Autowired
    private ListIliPigTypeMapper listIliPigTypeMapper;

    @Autowired
    private ListIliPigTypeService listIliPigTypeService;

    @Autowired
    private ListIliPigTypeQueryService listIliPigTypeQueryService;

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

    private MockMvc restListIliPigTypeMockMvc;

    private ListIliPigType listIliPigType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListIliPigTypeResource listIliPigTypeResource = new ListIliPigTypeResource(listIliPigTypeService, listIliPigTypeQueryService);
        this.restListIliPigTypeMockMvc = MockMvcBuilders.standaloneSetup(listIliPigTypeResource)
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
    public static ListIliPigType createEntity(EntityManager em) {
        ListIliPigType listIliPigType = new ListIliPigType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listIliPigType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListIliPigType createUpdatedEntity(EntityManager em) {
        ListIliPigType listIliPigType = new ListIliPigType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listIliPigType;
    }

    @BeforeEach
    public void initTest() {
        listIliPigType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListIliPigType() throws Exception {
        int databaseSizeBeforeCreate = listIliPigTypeRepository.findAll().size();

        // Create the ListIliPigType
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(listIliPigType);
        restListIliPigTypeMockMvc.perform(post("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListIliPigType in the database
        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListIliPigType testListIliPigType = listIliPigTypeList.get(listIliPigTypeList.size() - 1);
        assertThat(testListIliPigType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListIliPigType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListIliPigType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListIliPigType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListIliPigType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListIliPigType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListIliPigType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListIliPigType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListIliPigType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListIliPigTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listIliPigTypeRepository.findAll().size();

        // Create the ListIliPigType with an existing ID
        listIliPigType.setId(1L);
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(listIliPigType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListIliPigTypeMockMvc.perform(post("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListIliPigType in the database
        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listIliPigTypeRepository.findAll().size();
        // set the field null
        listIliPigType.setCode(null);

        // Create the ListIliPigType, which fails.
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(listIliPigType);

        restListIliPigTypeMockMvc.perform(post("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listIliPigTypeRepository.findAll().size();
        // set the field null
        listIliPigType.setName(null);

        // Create the ListIliPigType, which fails.
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(listIliPigType);

        restListIliPigTypeMockMvc.perform(post("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listIliPigTypeRepository.findAll().size();
        // set the field null
        listIliPigType.setFullName(null);

        // Create the ListIliPigType, which fails.
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(listIliPigType);

        restListIliPigTypeMockMvc.perform(post("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listIliPigTypeRepository.findAll().size();
        // set the field null
        listIliPigType.setIsCurrentFlag(null);

        // Create the ListIliPigType, which fails.
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(listIliPigType);

        restListIliPigTypeMockMvc.perform(post("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypes() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList
        restListIliPigTypeMockMvc.perform(get("/api/list-ili-pig-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listIliPigType.getId().intValue())))
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
    public void getListIliPigType() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get the listIliPigType
        restListIliPigTypeMockMvc.perform(get("/api/list-ili-pig-types/{id}", listIliPigType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listIliPigType.getId().intValue()))
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
    public void getAllListIliPigTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where code equals to DEFAULT_CODE
        defaultListIliPigTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listIliPigTypeList where code equals to UPDATED_CODE
        defaultListIliPigTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListIliPigTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listIliPigTypeList where code equals to UPDATED_CODE
        defaultListIliPigTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where code is not null
        defaultListIliPigTypeShouldBeFound("code.specified=true");

        // Get all the listIliPigTypeList where code is null
        defaultListIliPigTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where name equals to DEFAULT_NAME
        defaultListIliPigTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listIliPigTypeList where name equals to UPDATED_NAME
        defaultListIliPigTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListIliPigTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listIliPigTypeList where name equals to UPDATED_NAME
        defaultListIliPigTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where name is not null
        defaultListIliPigTypeShouldBeFound("name.specified=true");

        // Get all the listIliPigTypeList where name is null
        defaultListIliPigTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListIliPigTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listIliPigTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListIliPigTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListIliPigTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listIliPigTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListIliPigTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where fullName is not null
        defaultListIliPigTypeShouldBeFound("fullName.specified=true");

        // Get all the listIliPigTypeList where fullName is null
        defaultListIliPigTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listIliPigTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listIliPigTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where isCurrentFlag is not null
        defaultListIliPigTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listIliPigTypeList where isCurrentFlag is null
        defaultListIliPigTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listIliPigTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listIliPigTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListIliPigTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListIliPigTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListIliPigTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listIliPigTypeList where description equals to UPDATED_DESCRIPTION
        defaultListIliPigTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListIliPigTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listIliPigTypeList where description equals to UPDATED_DESCRIPTION
        defaultListIliPigTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where description is not null
        defaultListIliPigTypeShouldBeFound("description.specified=true");

        // Get all the listIliPigTypeList where description is null
        defaultListIliPigTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListIliPigTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listIliPigTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListIliPigTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListIliPigTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listIliPigTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListIliPigTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where dateCreate is not null
        defaultListIliPigTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listIliPigTypeList where dateCreate is null
        defaultListIliPigTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListIliPigTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listIliPigTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListIliPigTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListIliPigTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listIliPigTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListIliPigTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where dateEdit is not null
        defaultListIliPigTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listIliPigTypeList where dateEdit is null
        defaultListIliPigTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where creator equals to DEFAULT_CREATOR
        defaultListIliPigTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listIliPigTypeList where creator equals to UPDATED_CREATOR
        defaultListIliPigTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListIliPigTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listIliPigTypeList where creator equals to UPDATED_CREATOR
        defaultListIliPigTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where creator is not null
        defaultListIliPigTypeShouldBeFound("creator.specified=true");

        // Get all the listIliPigTypeList where creator is null
        defaultListIliPigTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where editor equals to DEFAULT_EDITOR
        defaultListIliPigTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listIliPigTypeList where editor equals to UPDATED_EDITOR
        defaultListIliPigTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListIliPigTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listIliPigTypeList where editor equals to UPDATED_EDITOR
        defaultListIliPigTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListIliPigTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        // Get all the listIliPigTypeList where editor is not null
        defaultListIliPigTypeShouldBeFound("editor.specified=true");

        // Get all the listIliPigTypeList where editor is null
        defaultListIliPigTypeShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListIliPigTypeShouldBeFound(String filter) throws Exception {
        restListIliPigTypeMockMvc.perform(get("/api/list-ili-pig-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listIliPigType.getId().intValue())))
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
        restListIliPigTypeMockMvc.perform(get("/api/list-ili-pig-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListIliPigTypeShouldNotBeFound(String filter) throws Exception {
        restListIliPigTypeMockMvc.perform(get("/api/list-ili-pig-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListIliPigTypeMockMvc.perform(get("/api/list-ili-pig-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListIliPigType() throws Exception {
        // Get the listIliPigType
        restListIliPigTypeMockMvc.perform(get("/api/list-ili-pig-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListIliPigType() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        int databaseSizeBeforeUpdate = listIliPigTypeRepository.findAll().size();

        // Update the listIliPigType
        ListIliPigType updatedListIliPigType = listIliPigTypeRepository.findById(listIliPigType.getId()).get();
        // Disconnect from session so that the updates on updatedListIliPigType are not directly saved in db
        em.detach(updatedListIliPigType);
        updatedListIliPigType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(updatedListIliPigType);

        restListIliPigTypeMockMvc.perform(put("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListIliPigType in the database
        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeUpdate);
        ListIliPigType testListIliPigType = listIliPigTypeList.get(listIliPigTypeList.size() - 1);
        assertThat(testListIliPigType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListIliPigType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListIliPigType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListIliPigType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListIliPigType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListIliPigType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListIliPigType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListIliPigType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListIliPigType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListIliPigType() throws Exception {
        int databaseSizeBeforeUpdate = listIliPigTypeRepository.findAll().size();

        // Create the ListIliPigType
        ListIliPigTypeDTO listIliPigTypeDTO = listIliPigTypeMapper.toDto(listIliPigType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListIliPigTypeMockMvc.perform(put("/api/list-ili-pig-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listIliPigTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListIliPigType in the database
        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListIliPigType() throws Exception {
        // Initialize the database
        listIliPigTypeRepository.saveAndFlush(listIliPigType);

        int databaseSizeBeforeDelete = listIliPigTypeRepository.findAll().size();

        // Delete the listIliPigType
        restListIliPigTypeMockMvc.perform(delete("/api/list-ili-pig-types/{id}", listIliPigType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListIliPigType> listIliPigTypeList = listIliPigTypeRepository.findAll();
        assertThat(listIliPigTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListIliPigType.class);
        ListIliPigType listIliPigType1 = new ListIliPigType();
        listIliPigType1.setId(1L);
        ListIliPigType listIliPigType2 = new ListIliPigType();
        listIliPigType2.setId(listIliPigType1.getId());
        assertThat(listIliPigType1).isEqualTo(listIliPigType2);
        listIliPigType2.setId(2L);
        assertThat(listIliPigType1).isNotEqualTo(listIliPigType2);
        listIliPigType1.setId(null);
        assertThat(listIliPigType1).isNotEqualTo(listIliPigType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListIliPigTypeDTO.class);
        ListIliPigTypeDTO listIliPigTypeDTO1 = new ListIliPigTypeDTO();
        listIliPigTypeDTO1.setId(1L);
        ListIliPigTypeDTO listIliPigTypeDTO2 = new ListIliPigTypeDTO();
        assertThat(listIliPigTypeDTO1).isNotEqualTo(listIliPigTypeDTO2);
        listIliPigTypeDTO2.setId(listIliPigTypeDTO1.getId());
        assertThat(listIliPigTypeDTO1).isEqualTo(listIliPigTypeDTO2);
        listIliPigTypeDTO2.setId(2L);
        assertThat(listIliPigTypeDTO1).isNotEqualTo(listIliPigTypeDTO2);
        listIliPigTypeDTO1.setId(null);
        assertThat(listIliPigTypeDTO1).isNotEqualTo(listIliPigTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listIliPigTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listIliPigTypeMapper.fromId(null)).isNull();
    }
}
