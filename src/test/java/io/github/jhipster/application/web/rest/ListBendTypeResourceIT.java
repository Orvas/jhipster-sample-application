package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListBendType;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.repository.ListBendTypeRepository;
import io.github.jhipster.application.service.ListBendTypeService;
import io.github.jhipster.application.service.dto.ListBendTypeDTO;
import io.github.jhipster.application.service.mapper.ListBendTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListBendTypeCriteria;
import io.github.jhipster.application.service.ListBendTypeQueryService;

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
 * Integration tests for the {@Link ListBendTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListBendTypeResourceIT {

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
    private ListBendTypeRepository listBendTypeRepository;

    @Autowired
    private ListBendTypeMapper listBendTypeMapper;

    @Autowired
    private ListBendTypeService listBendTypeService;

    @Autowired
    private ListBendTypeQueryService listBendTypeQueryService;

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

    private MockMvc restListBendTypeMockMvc;

    private ListBendType listBendType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListBendTypeResource listBendTypeResource = new ListBendTypeResource(listBendTypeService, listBendTypeQueryService);
        this.restListBendTypeMockMvc = MockMvcBuilders.standaloneSetup(listBendTypeResource)
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
    public static ListBendType createEntity(EntityManager em) {
        ListBendType listBendType = new ListBendType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listBendType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListBendType createUpdatedEntity(EntityManager em) {
        ListBendType listBendType = new ListBendType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listBendType;
    }

    @BeforeEach
    public void initTest() {
        listBendType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListBendType() throws Exception {
        int databaseSizeBeforeCreate = listBendTypeRepository.findAll().size();

        // Create the ListBendType
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(listBendType);
        restListBendTypeMockMvc.perform(post("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListBendType in the database
        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListBendType testListBendType = listBendTypeList.get(listBendTypeList.size() - 1);
        assertThat(testListBendType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListBendType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListBendType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListBendType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListBendType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListBendType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListBendType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListBendType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListBendType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListBendTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listBendTypeRepository.findAll().size();

        // Create the ListBendType with an existing ID
        listBendType.setId(1L);
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(listBendType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListBendTypeMockMvc.perform(post("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBendType in the database
        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendTypeRepository.findAll().size();
        // set the field null
        listBendType.setCode(null);

        // Create the ListBendType, which fails.
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(listBendType);

        restListBendTypeMockMvc.perform(post("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendTypeRepository.findAll().size();
        // set the field null
        listBendType.setName(null);

        // Create the ListBendType, which fails.
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(listBendType);

        restListBendTypeMockMvc.perform(post("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendTypeRepository.findAll().size();
        // set the field null
        listBendType.setFullName(null);

        // Create the ListBendType, which fails.
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(listBendType);

        restListBendTypeMockMvc.perform(post("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendTypeRepository.findAll().size();
        // set the field null
        listBendType.setIsCurrentFlag(null);

        // Create the ListBendType, which fails.
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(listBendType);

        restListBendTypeMockMvc.perform(post("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListBendTypes() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList
        restListBendTypeMockMvc.perform(get("/api/list-bend-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBendType.getId().intValue())))
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
    public void getListBendType() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get the listBendType
        restListBendTypeMockMvc.perform(get("/api/list-bend-types/{id}", listBendType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listBendType.getId().intValue()))
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
    public void getAllListBendTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where code equals to DEFAULT_CODE
        defaultListBendTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listBendTypeList where code equals to UPDATED_CODE
        defaultListBendTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListBendTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listBendTypeList where code equals to UPDATED_CODE
        defaultListBendTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where code is not null
        defaultListBendTypeShouldBeFound("code.specified=true");

        // Get all the listBendTypeList where code is null
        defaultListBendTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where name equals to DEFAULT_NAME
        defaultListBendTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listBendTypeList where name equals to UPDATED_NAME
        defaultListBendTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListBendTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listBendTypeList where name equals to UPDATED_NAME
        defaultListBendTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where name is not null
        defaultListBendTypeShouldBeFound("name.specified=true");

        // Get all the listBendTypeList where name is null
        defaultListBendTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListBendTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listBendTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListBendTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListBendTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listBendTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListBendTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where fullName is not null
        defaultListBendTypeShouldBeFound("fullName.specified=true");

        // Get all the listBendTypeList where fullName is null
        defaultListBendTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListBendTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listBendTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where isCurrentFlag is not null
        defaultListBendTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listBendTypeList where isCurrentFlag is null
        defaultListBendTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListBendTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListBendTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listBendTypeList where description equals to UPDATED_DESCRIPTION
        defaultListBendTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListBendTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listBendTypeList where description equals to UPDATED_DESCRIPTION
        defaultListBendTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where description is not null
        defaultListBendTypeShouldBeFound("description.specified=true");

        // Get all the listBendTypeList where description is null
        defaultListBendTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListBendTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listBendTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBendTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListBendTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listBendTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBendTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where dateCreate is not null
        defaultListBendTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listBendTypeList where dateCreate is null
        defaultListBendTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListBendTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listBendTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBendTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListBendTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listBendTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBendTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where dateEdit is not null
        defaultListBendTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listBendTypeList where dateEdit is null
        defaultListBendTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where creator equals to DEFAULT_CREATOR
        defaultListBendTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listBendTypeList where creator equals to UPDATED_CREATOR
        defaultListBendTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListBendTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listBendTypeList where creator equals to UPDATED_CREATOR
        defaultListBendTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where creator is not null
        defaultListBendTypeShouldBeFound("creator.specified=true");

        // Get all the listBendTypeList where creator is null
        defaultListBendTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where editor equals to DEFAULT_EDITOR
        defaultListBendTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listBendTypeList where editor equals to UPDATED_EDITOR
        defaultListBendTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListBendTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listBendTypeList where editor equals to UPDATED_EDITOR
        defaultListBendTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBendTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        // Get all the listBendTypeList where editor is not null
        defaultListBendTypeShouldBeFound("editor.specified=true");

        // Get all the listBendTypeList where editor is null
        defaultListBendTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendTypesByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listBendType.addBendHist(bendHist);
        listBendTypeRepository.saveAndFlush(listBendType);
        Long bendHistId = bendHist.getId();

        // Get all the listBendTypeList where bendHist equals to bendHistId
        defaultListBendTypeShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listBendTypeList where bendHist equals to bendHistId + 1
        defaultListBendTypeShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListBendTypeShouldBeFound(String filter) throws Exception {
        restListBendTypeMockMvc.perform(get("/api/list-bend-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBendType.getId().intValue())))
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
        restListBendTypeMockMvc.perform(get("/api/list-bend-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListBendTypeShouldNotBeFound(String filter) throws Exception {
        restListBendTypeMockMvc.perform(get("/api/list-bend-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListBendTypeMockMvc.perform(get("/api/list-bend-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListBendType() throws Exception {
        // Get the listBendType
        restListBendTypeMockMvc.perform(get("/api/list-bend-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListBendType() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        int databaseSizeBeforeUpdate = listBendTypeRepository.findAll().size();

        // Update the listBendType
        ListBendType updatedListBendType = listBendTypeRepository.findById(listBendType.getId()).get();
        // Disconnect from session so that the updates on updatedListBendType are not directly saved in db
        em.detach(updatedListBendType);
        updatedListBendType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(updatedListBendType);

        restListBendTypeMockMvc.perform(put("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListBendType in the database
        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeUpdate);
        ListBendType testListBendType = listBendTypeList.get(listBendTypeList.size() - 1);
        assertThat(testListBendType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListBendType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListBendType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListBendType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListBendType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListBendType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListBendType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListBendType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListBendType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListBendType() throws Exception {
        int databaseSizeBeforeUpdate = listBendTypeRepository.findAll().size();

        // Create the ListBendType
        ListBendTypeDTO listBendTypeDTO = listBendTypeMapper.toDto(listBendType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListBendTypeMockMvc.perform(put("/api/list-bend-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBendType in the database
        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListBendType() throws Exception {
        // Initialize the database
        listBendTypeRepository.saveAndFlush(listBendType);

        int databaseSizeBeforeDelete = listBendTypeRepository.findAll().size();

        // Delete the listBendType
        restListBendTypeMockMvc.perform(delete("/api/list-bend-types/{id}", listBendType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListBendType> listBendTypeList = listBendTypeRepository.findAll();
        assertThat(listBendTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBendType.class);
        ListBendType listBendType1 = new ListBendType();
        listBendType1.setId(1L);
        ListBendType listBendType2 = new ListBendType();
        listBendType2.setId(listBendType1.getId());
        assertThat(listBendType1).isEqualTo(listBendType2);
        listBendType2.setId(2L);
        assertThat(listBendType1).isNotEqualTo(listBendType2);
        listBendType1.setId(null);
        assertThat(listBendType1).isNotEqualTo(listBendType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBendTypeDTO.class);
        ListBendTypeDTO listBendTypeDTO1 = new ListBendTypeDTO();
        listBendTypeDTO1.setId(1L);
        ListBendTypeDTO listBendTypeDTO2 = new ListBendTypeDTO();
        assertThat(listBendTypeDTO1).isNotEqualTo(listBendTypeDTO2);
        listBendTypeDTO2.setId(listBendTypeDTO1.getId());
        assertThat(listBendTypeDTO1).isEqualTo(listBendTypeDTO2);
        listBendTypeDTO2.setId(2L);
        assertThat(listBendTypeDTO1).isNotEqualTo(listBendTypeDTO2);
        listBendTypeDTO1.setId(null);
        assertThat(listBendTypeDTO1).isNotEqualTo(listBendTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listBendTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listBendTypeMapper.fromId(null)).isNull();
    }
}
