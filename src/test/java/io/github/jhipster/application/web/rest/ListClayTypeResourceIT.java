package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListClayType;
import io.github.jhipster.application.repository.ListClayTypeRepository;
import io.github.jhipster.application.service.ListClayTypeService;
import io.github.jhipster.application.service.dto.ListClayTypeDTO;
import io.github.jhipster.application.service.mapper.ListClayTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListClayTypeCriteria;
import io.github.jhipster.application.service.ListClayTypeQueryService;

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
 * Integration tests for the {@Link ListClayTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListClayTypeResourceIT {

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
    private ListClayTypeRepository listClayTypeRepository;

    @Autowired
    private ListClayTypeMapper listClayTypeMapper;

    @Autowired
    private ListClayTypeService listClayTypeService;

    @Autowired
    private ListClayTypeQueryService listClayTypeQueryService;

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

    private MockMvc restListClayTypeMockMvc;

    private ListClayType listClayType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListClayTypeResource listClayTypeResource = new ListClayTypeResource(listClayTypeService, listClayTypeQueryService);
        this.restListClayTypeMockMvc = MockMvcBuilders.standaloneSetup(listClayTypeResource)
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
    public static ListClayType createEntity(EntityManager em) {
        ListClayType listClayType = new ListClayType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listClayType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListClayType createUpdatedEntity(EntityManager em) {
        ListClayType listClayType = new ListClayType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listClayType;
    }

    @BeforeEach
    public void initTest() {
        listClayType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListClayType() throws Exception {
        int databaseSizeBeforeCreate = listClayTypeRepository.findAll().size();

        // Create the ListClayType
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(listClayType);
        restListClayTypeMockMvc.perform(post("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListClayType in the database
        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListClayType testListClayType = listClayTypeList.get(listClayTypeList.size() - 1);
        assertThat(testListClayType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListClayType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListClayType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListClayType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListClayType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListClayType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListClayType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListClayType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListClayType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListClayTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listClayTypeRepository.findAll().size();

        // Create the ListClayType with an existing ID
        listClayType.setId(1L);
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(listClayType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListClayTypeMockMvc.perform(post("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClayType in the database
        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClayTypeRepository.findAll().size();
        // set the field null
        listClayType.setCode(null);

        // Create the ListClayType, which fails.
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(listClayType);

        restListClayTypeMockMvc.perform(post("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClayTypeRepository.findAll().size();
        // set the field null
        listClayType.setName(null);

        // Create the ListClayType, which fails.
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(listClayType);

        restListClayTypeMockMvc.perform(post("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClayTypeRepository.findAll().size();
        // set the field null
        listClayType.setFullName(null);

        // Create the ListClayType, which fails.
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(listClayType);

        restListClayTypeMockMvc.perform(post("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClayTypeRepository.findAll().size();
        // set the field null
        listClayType.setIsCurrentFlag(null);

        // Create the ListClayType, which fails.
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(listClayType);

        restListClayTypeMockMvc.perform(post("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListClayTypes() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList
        restListClayTypeMockMvc.perform(get("/api/list-clay-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClayType.getId().intValue())))
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
    public void getListClayType() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get the listClayType
        restListClayTypeMockMvc.perform(get("/api/list-clay-types/{id}", listClayType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listClayType.getId().intValue()))
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
    public void getAllListClayTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where code equals to DEFAULT_CODE
        defaultListClayTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listClayTypeList where code equals to UPDATED_CODE
        defaultListClayTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListClayTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listClayTypeList where code equals to UPDATED_CODE
        defaultListClayTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where code is not null
        defaultListClayTypeShouldBeFound("code.specified=true");

        // Get all the listClayTypeList where code is null
        defaultListClayTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where name equals to DEFAULT_NAME
        defaultListClayTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listClayTypeList where name equals to UPDATED_NAME
        defaultListClayTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListClayTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listClayTypeList where name equals to UPDATED_NAME
        defaultListClayTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where name is not null
        defaultListClayTypeShouldBeFound("name.specified=true");

        // Get all the listClayTypeList where name is null
        defaultListClayTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListClayTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listClayTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListClayTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListClayTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listClayTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListClayTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where fullName is not null
        defaultListClayTypeShouldBeFound("fullName.specified=true");

        // Get all the listClayTypeList where fullName is null
        defaultListClayTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClayTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClayTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClayTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListClayTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listClayTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClayTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where isCurrentFlag is not null
        defaultListClayTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listClayTypeList where isCurrentFlag is null
        defaultListClayTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClayTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClayTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClayTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClayTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClayTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClayTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListClayTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListClayTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listClayTypeList where description equals to UPDATED_DESCRIPTION
        defaultListClayTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListClayTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listClayTypeList where description equals to UPDATED_DESCRIPTION
        defaultListClayTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where description is not null
        defaultListClayTypeShouldBeFound("description.specified=true");

        // Get all the listClayTypeList where description is null
        defaultListClayTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListClayTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listClayTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClayTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListClayTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listClayTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClayTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where dateCreate is not null
        defaultListClayTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listClayTypeList where dateCreate is null
        defaultListClayTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListClayTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listClayTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClayTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListClayTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listClayTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClayTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where dateEdit is not null
        defaultListClayTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listClayTypeList where dateEdit is null
        defaultListClayTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where creator equals to DEFAULT_CREATOR
        defaultListClayTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listClayTypeList where creator equals to UPDATED_CREATOR
        defaultListClayTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListClayTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listClayTypeList where creator equals to UPDATED_CREATOR
        defaultListClayTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where creator is not null
        defaultListClayTypeShouldBeFound("creator.specified=true");

        // Get all the listClayTypeList where creator is null
        defaultListClayTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClayTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where editor equals to DEFAULT_EDITOR
        defaultListClayTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listClayTypeList where editor equals to UPDATED_EDITOR
        defaultListClayTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListClayTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listClayTypeList where editor equals to UPDATED_EDITOR
        defaultListClayTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClayTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        // Get all the listClayTypeList where editor is not null
        defaultListClayTypeShouldBeFound("editor.specified=true");

        // Get all the listClayTypeList where editor is null
        defaultListClayTypeShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListClayTypeShouldBeFound(String filter) throws Exception {
        restListClayTypeMockMvc.perform(get("/api/list-clay-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClayType.getId().intValue())))
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
        restListClayTypeMockMvc.perform(get("/api/list-clay-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListClayTypeShouldNotBeFound(String filter) throws Exception {
        restListClayTypeMockMvc.perform(get("/api/list-clay-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListClayTypeMockMvc.perform(get("/api/list-clay-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListClayType() throws Exception {
        // Get the listClayType
        restListClayTypeMockMvc.perform(get("/api/list-clay-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListClayType() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        int databaseSizeBeforeUpdate = listClayTypeRepository.findAll().size();

        // Update the listClayType
        ListClayType updatedListClayType = listClayTypeRepository.findById(listClayType.getId()).get();
        // Disconnect from session so that the updates on updatedListClayType are not directly saved in db
        em.detach(updatedListClayType);
        updatedListClayType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(updatedListClayType);

        restListClayTypeMockMvc.perform(put("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListClayType in the database
        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeUpdate);
        ListClayType testListClayType = listClayTypeList.get(listClayTypeList.size() - 1);
        assertThat(testListClayType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListClayType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListClayType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListClayType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListClayType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListClayType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListClayType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListClayType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListClayType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListClayType() throws Exception {
        int databaseSizeBeforeUpdate = listClayTypeRepository.findAll().size();

        // Create the ListClayType
        ListClayTypeDTO listClayTypeDTO = listClayTypeMapper.toDto(listClayType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListClayTypeMockMvc.perform(put("/api/list-clay-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClayTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClayType in the database
        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListClayType() throws Exception {
        // Initialize the database
        listClayTypeRepository.saveAndFlush(listClayType);

        int databaseSizeBeforeDelete = listClayTypeRepository.findAll().size();

        // Delete the listClayType
        restListClayTypeMockMvc.perform(delete("/api/list-clay-types/{id}", listClayType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListClayType> listClayTypeList = listClayTypeRepository.findAll();
        assertThat(listClayTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClayType.class);
        ListClayType listClayType1 = new ListClayType();
        listClayType1.setId(1L);
        ListClayType listClayType2 = new ListClayType();
        listClayType2.setId(listClayType1.getId());
        assertThat(listClayType1).isEqualTo(listClayType2);
        listClayType2.setId(2L);
        assertThat(listClayType1).isNotEqualTo(listClayType2);
        listClayType1.setId(null);
        assertThat(listClayType1).isNotEqualTo(listClayType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClayTypeDTO.class);
        ListClayTypeDTO listClayTypeDTO1 = new ListClayTypeDTO();
        listClayTypeDTO1.setId(1L);
        ListClayTypeDTO listClayTypeDTO2 = new ListClayTypeDTO();
        assertThat(listClayTypeDTO1).isNotEqualTo(listClayTypeDTO2);
        listClayTypeDTO2.setId(listClayTypeDTO1.getId());
        assertThat(listClayTypeDTO1).isEqualTo(listClayTypeDTO2);
        listClayTypeDTO2.setId(2L);
        assertThat(listClayTypeDTO1).isNotEqualTo(listClayTypeDTO2);
        listClayTypeDTO1.setId(null);
        assertThat(listClayTypeDTO1).isNotEqualTo(listClayTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listClayTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listClayTypeMapper.fromId(null)).isNull();
    }
}
