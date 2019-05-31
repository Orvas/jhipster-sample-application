package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListSoilType;
import io.github.jhipster.application.repository.ListSoilTypeRepository;
import io.github.jhipster.application.service.ListSoilTypeService;
import io.github.jhipster.application.service.dto.ListSoilTypeDTO;
import io.github.jhipster.application.service.mapper.ListSoilTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListSoilTypeCriteria;
import io.github.jhipster.application.service.ListSoilTypeQueryService;

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
 * Integration tests for the {@Link ListSoilTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListSoilTypeResourceIT {

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
    private ListSoilTypeRepository listSoilTypeRepository;

    @Autowired
    private ListSoilTypeMapper listSoilTypeMapper;

    @Autowired
    private ListSoilTypeService listSoilTypeService;

    @Autowired
    private ListSoilTypeQueryService listSoilTypeQueryService;

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

    private MockMvc restListSoilTypeMockMvc;

    private ListSoilType listSoilType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListSoilTypeResource listSoilTypeResource = new ListSoilTypeResource(listSoilTypeService, listSoilTypeQueryService);
        this.restListSoilTypeMockMvc = MockMvcBuilders.standaloneSetup(listSoilTypeResource)
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
    public static ListSoilType createEntity(EntityManager em) {
        ListSoilType listSoilType = new ListSoilType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listSoilType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListSoilType createUpdatedEntity(EntityManager em) {
        ListSoilType listSoilType = new ListSoilType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listSoilType;
    }

    @BeforeEach
    public void initTest() {
        listSoilType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListSoilType() throws Exception {
        int databaseSizeBeforeCreate = listSoilTypeRepository.findAll().size();

        // Create the ListSoilType
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(listSoilType);
        restListSoilTypeMockMvc.perform(post("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListSoilType in the database
        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListSoilType testListSoilType = listSoilTypeList.get(listSoilTypeList.size() - 1);
        assertThat(testListSoilType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListSoilType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListSoilType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListSoilType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListSoilType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListSoilType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListSoilType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListSoilType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListSoilType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListSoilTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listSoilTypeRepository.findAll().size();

        // Create the ListSoilType with an existing ID
        listSoilType.setId(1L);
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(listSoilType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListSoilTypeMockMvc.perform(post("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSoilType in the database
        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSoilTypeRepository.findAll().size();
        // set the field null
        listSoilType.setCode(null);

        // Create the ListSoilType, which fails.
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(listSoilType);

        restListSoilTypeMockMvc.perform(post("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSoilTypeRepository.findAll().size();
        // set the field null
        listSoilType.setName(null);

        // Create the ListSoilType, which fails.
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(listSoilType);

        restListSoilTypeMockMvc.perform(post("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSoilTypeRepository.findAll().size();
        // set the field null
        listSoilType.setFullName(null);

        // Create the ListSoilType, which fails.
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(listSoilType);

        restListSoilTypeMockMvc.perform(post("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSoilTypeRepository.findAll().size();
        // set the field null
        listSoilType.setIsCurrentFlag(null);

        // Create the ListSoilType, which fails.
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(listSoilType);

        restListSoilTypeMockMvc.perform(post("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListSoilTypes() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList
        restListSoilTypeMockMvc.perform(get("/api/list-soil-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSoilType.getId().intValue())))
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
    public void getListSoilType() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get the listSoilType
        restListSoilTypeMockMvc.perform(get("/api/list-soil-types/{id}", listSoilType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listSoilType.getId().intValue()))
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
    public void getAllListSoilTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where code equals to DEFAULT_CODE
        defaultListSoilTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listSoilTypeList where code equals to UPDATED_CODE
        defaultListSoilTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListSoilTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listSoilTypeList where code equals to UPDATED_CODE
        defaultListSoilTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where code is not null
        defaultListSoilTypeShouldBeFound("code.specified=true");

        // Get all the listSoilTypeList where code is null
        defaultListSoilTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where name equals to DEFAULT_NAME
        defaultListSoilTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listSoilTypeList where name equals to UPDATED_NAME
        defaultListSoilTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListSoilTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listSoilTypeList where name equals to UPDATED_NAME
        defaultListSoilTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where name is not null
        defaultListSoilTypeShouldBeFound("name.specified=true");

        // Get all the listSoilTypeList where name is null
        defaultListSoilTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListSoilTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listSoilTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListSoilTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListSoilTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listSoilTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListSoilTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where fullName is not null
        defaultListSoilTypeShouldBeFound("fullName.specified=true");

        // Get all the listSoilTypeList where fullName is null
        defaultListSoilTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSoilTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSoilTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSoilTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListSoilTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listSoilTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSoilTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where isCurrentFlag is not null
        defaultListSoilTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listSoilTypeList where isCurrentFlag is null
        defaultListSoilTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSoilTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSoilTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSoilTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSoilTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSoilTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSoilTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListSoilTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListSoilTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listSoilTypeList where description equals to UPDATED_DESCRIPTION
        defaultListSoilTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListSoilTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listSoilTypeList where description equals to UPDATED_DESCRIPTION
        defaultListSoilTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where description is not null
        defaultListSoilTypeShouldBeFound("description.specified=true");

        // Get all the listSoilTypeList where description is null
        defaultListSoilTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListSoilTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listSoilTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSoilTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListSoilTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listSoilTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSoilTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where dateCreate is not null
        defaultListSoilTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listSoilTypeList where dateCreate is null
        defaultListSoilTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListSoilTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listSoilTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSoilTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListSoilTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listSoilTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSoilTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where dateEdit is not null
        defaultListSoilTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listSoilTypeList where dateEdit is null
        defaultListSoilTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where creator equals to DEFAULT_CREATOR
        defaultListSoilTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listSoilTypeList where creator equals to UPDATED_CREATOR
        defaultListSoilTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListSoilTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listSoilTypeList where creator equals to UPDATED_CREATOR
        defaultListSoilTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where creator is not null
        defaultListSoilTypeShouldBeFound("creator.specified=true");

        // Get all the listSoilTypeList where creator is null
        defaultListSoilTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where editor equals to DEFAULT_EDITOR
        defaultListSoilTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listSoilTypeList where editor equals to UPDATED_EDITOR
        defaultListSoilTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListSoilTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listSoilTypeList where editor equals to UPDATED_EDITOR
        defaultListSoilTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSoilTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        // Get all the listSoilTypeList where editor is not null
        defaultListSoilTypeShouldBeFound("editor.specified=true");

        // Get all the listSoilTypeList where editor is null
        defaultListSoilTypeShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListSoilTypeShouldBeFound(String filter) throws Exception {
        restListSoilTypeMockMvc.perform(get("/api/list-soil-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSoilType.getId().intValue())))
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
        restListSoilTypeMockMvc.perform(get("/api/list-soil-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListSoilTypeShouldNotBeFound(String filter) throws Exception {
        restListSoilTypeMockMvc.perform(get("/api/list-soil-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListSoilTypeMockMvc.perform(get("/api/list-soil-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListSoilType() throws Exception {
        // Get the listSoilType
        restListSoilTypeMockMvc.perform(get("/api/list-soil-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListSoilType() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        int databaseSizeBeforeUpdate = listSoilTypeRepository.findAll().size();

        // Update the listSoilType
        ListSoilType updatedListSoilType = listSoilTypeRepository.findById(listSoilType.getId()).get();
        // Disconnect from session so that the updates on updatedListSoilType are not directly saved in db
        em.detach(updatedListSoilType);
        updatedListSoilType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(updatedListSoilType);

        restListSoilTypeMockMvc.perform(put("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListSoilType in the database
        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeUpdate);
        ListSoilType testListSoilType = listSoilTypeList.get(listSoilTypeList.size() - 1);
        assertThat(testListSoilType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListSoilType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListSoilType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListSoilType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListSoilType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListSoilType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListSoilType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListSoilType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListSoilType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListSoilType() throws Exception {
        int databaseSizeBeforeUpdate = listSoilTypeRepository.findAll().size();

        // Create the ListSoilType
        ListSoilTypeDTO listSoilTypeDTO = listSoilTypeMapper.toDto(listSoilType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListSoilTypeMockMvc.perform(put("/api/list-soil-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSoilTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSoilType in the database
        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListSoilType() throws Exception {
        // Initialize the database
        listSoilTypeRepository.saveAndFlush(listSoilType);

        int databaseSizeBeforeDelete = listSoilTypeRepository.findAll().size();

        // Delete the listSoilType
        restListSoilTypeMockMvc.perform(delete("/api/list-soil-types/{id}", listSoilType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListSoilType> listSoilTypeList = listSoilTypeRepository.findAll();
        assertThat(listSoilTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSoilType.class);
        ListSoilType listSoilType1 = new ListSoilType();
        listSoilType1.setId(1L);
        ListSoilType listSoilType2 = new ListSoilType();
        listSoilType2.setId(listSoilType1.getId());
        assertThat(listSoilType1).isEqualTo(listSoilType2);
        listSoilType2.setId(2L);
        assertThat(listSoilType1).isNotEqualTo(listSoilType2);
        listSoilType1.setId(null);
        assertThat(listSoilType1).isNotEqualTo(listSoilType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSoilTypeDTO.class);
        ListSoilTypeDTO listSoilTypeDTO1 = new ListSoilTypeDTO();
        listSoilTypeDTO1.setId(1L);
        ListSoilTypeDTO listSoilTypeDTO2 = new ListSoilTypeDTO();
        assertThat(listSoilTypeDTO1).isNotEqualTo(listSoilTypeDTO2);
        listSoilTypeDTO2.setId(listSoilTypeDTO1.getId());
        assertThat(listSoilTypeDTO1).isEqualTo(listSoilTypeDTO2);
        listSoilTypeDTO2.setId(2L);
        assertThat(listSoilTypeDTO1).isNotEqualTo(listSoilTypeDTO2);
        listSoilTypeDTO1.setId(null);
        assertThat(listSoilTypeDTO1).isNotEqualTo(listSoilTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listSoilTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listSoilTypeMapper.fromId(null)).isNull();
    }
}
