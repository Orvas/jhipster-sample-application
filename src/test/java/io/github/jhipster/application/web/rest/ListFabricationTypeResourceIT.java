package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListFabricationTypeRepository;
import io.github.jhipster.application.service.ListFabricationTypeService;
import io.github.jhipster.application.service.dto.ListFabricationTypeDTO;
import io.github.jhipster.application.service.mapper.ListFabricationTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListFabricationTypeCriteria;
import io.github.jhipster.application.service.ListFabricationTypeQueryService;

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
 * Integration tests for the {@Link ListFabricationTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListFabricationTypeResourceIT {

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
    private ListFabricationTypeRepository listFabricationTypeRepository;

    @Autowired
    private ListFabricationTypeMapper listFabricationTypeMapper;

    @Autowired
    private ListFabricationTypeService listFabricationTypeService;

    @Autowired
    private ListFabricationTypeQueryService listFabricationTypeQueryService;

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

    private MockMvc restListFabricationTypeMockMvc;

    private ListFabricationType listFabricationType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListFabricationTypeResource listFabricationTypeResource = new ListFabricationTypeResource(listFabricationTypeService, listFabricationTypeQueryService);
        this.restListFabricationTypeMockMvc = MockMvcBuilders.standaloneSetup(listFabricationTypeResource)
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
    public static ListFabricationType createEntity(EntityManager em) {
        ListFabricationType listFabricationType = new ListFabricationType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listFabricationType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListFabricationType createUpdatedEntity(EntityManager em) {
        ListFabricationType listFabricationType = new ListFabricationType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listFabricationType;
    }

    @BeforeEach
    public void initTest() {
        listFabricationType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListFabricationType() throws Exception {
        int databaseSizeBeforeCreate = listFabricationTypeRepository.findAll().size();

        // Create the ListFabricationType
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(listFabricationType);
        restListFabricationTypeMockMvc.perform(post("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListFabricationType in the database
        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListFabricationType testListFabricationType = listFabricationTypeList.get(listFabricationTypeList.size() - 1);
        assertThat(testListFabricationType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListFabricationType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListFabricationType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListFabricationType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListFabricationType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListFabricationType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListFabricationType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListFabricationType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListFabricationType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListFabricationTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listFabricationTypeRepository.findAll().size();

        // Create the ListFabricationType with an existing ID
        listFabricationType.setId(1L);
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(listFabricationType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListFabricationTypeMockMvc.perform(post("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListFabricationType in the database
        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listFabricationTypeRepository.findAll().size();
        // set the field null
        listFabricationType.setCode(null);

        // Create the ListFabricationType, which fails.
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(listFabricationType);

        restListFabricationTypeMockMvc.perform(post("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listFabricationTypeRepository.findAll().size();
        // set the field null
        listFabricationType.setName(null);

        // Create the ListFabricationType, which fails.
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(listFabricationType);

        restListFabricationTypeMockMvc.perform(post("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listFabricationTypeRepository.findAll().size();
        // set the field null
        listFabricationType.setFullName(null);

        // Create the ListFabricationType, which fails.
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(listFabricationType);

        restListFabricationTypeMockMvc.perform(post("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listFabricationTypeRepository.findAll().size();
        // set the field null
        listFabricationType.setIsCurrentFlag(null);

        // Create the ListFabricationType, which fails.
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(listFabricationType);

        restListFabricationTypeMockMvc.perform(post("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypes() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList
        restListFabricationTypeMockMvc.perform(get("/api/list-fabrication-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listFabricationType.getId().intValue())))
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
    public void getListFabricationType() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get the listFabricationType
        restListFabricationTypeMockMvc.perform(get("/api/list-fabrication-types/{id}", listFabricationType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listFabricationType.getId().intValue()))
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
    public void getAllListFabricationTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where code equals to DEFAULT_CODE
        defaultListFabricationTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listFabricationTypeList where code equals to UPDATED_CODE
        defaultListFabricationTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListFabricationTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listFabricationTypeList where code equals to UPDATED_CODE
        defaultListFabricationTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where code is not null
        defaultListFabricationTypeShouldBeFound("code.specified=true");

        // Get all the listFabricationTypeList where code is null
        defaultListFabricationTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where name equals to DEFAULT_NAME
        defaultListFabricationTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listFabricationTypeList where name equals to UPDATED_NAME
        defaultListFabricationTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListFabricationTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listFabricationTypeList where name equals to UPDATED_NAME
        defaultListFabricationTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where name is not null
        defaultListFabricationTypeShouldBeFound("name.specified=true");

        // Get all the listFabricationTypeList where name is null
        defaultListFabricationTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListFabricationTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listFabricationTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListFabricationTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListFabricationTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listFabricationTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListFabricationTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where fullName is not null
        defaultListFabricationTypeShouldBeFound("fullName.specified=true");

        // Get all the listFabricationTypeList where fullName is null
        defaultListFabricationTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listFabricationTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listFabricationTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where isCurrentFlag is not null
        defaultListFabricationTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listFabricationTypeList where isCurrentFlag is null
        defaultListFabricationTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listFabricationTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listFabricationTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListFabricationTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListFabricationTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListFabricationTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listFabricationTypeList where description equals to UPDATED_DESCRIPTION
        defaultListFabricationTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListFabricationTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listFabricationTypeList where description equals to UPDATED_DESCRIPTION
        defaultListFabricationTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where description is not null
        defaultListFabricationTypeShouldBeFound("description.specified=true");

        // Get all the listFabricationTypeList where description is null
        defaultListFabricationTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListFabricationTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listFabricationTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListFabricationTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListFabricationTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listFabricationTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListFabricationTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where dateCreate is not null
        defaultListFabricationTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listFabricationTypeList where dateCreate is null
        defaultListFabricationTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListFabricationTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listFabricationTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListFabricationTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListFabricationTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listFabricationTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListFabricationTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where dateEdit is not null
        defaultListFabricationTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listFabricationTypeList where dateEdit is null
        defaultListFabricationTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where creator equals to DEFAULT_CREATOR
        defaultListFabricationTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listFabricationTypeList where creator equals to UPDATED_CREATOR
        defaultListFabricationTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListFabricationTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listFabricationTypeList where creator equals to UPDATED_CREATOR
        defaultListFabricationTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where creator is not null
        defaultListFabricationTypeShouldBeFound("creator.specified=true");

        // Get all the listFabricationTypeList where creator is null
        defaultListFabricationTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where editor equals to DEFAULT_EDITOR
        defaultListFabricationTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listFabricationTypeList where editor equals to UPDATED_EDITOR
        defaultListFabricationTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListFabricationTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listFabricationTypeList where editor equals to UPDATED_EDITOR
        defaultListFabricationTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        // Get all the listFabricationTypeList where editor is not null
        defaultListFabricationTypeShouldBeFound("editor.specified=true");

        // Get all the listFabricationTypeList where editor is null
        defaultListFabricationTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListFabricationTypesByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listFabricationType.addBendHist(bendHist);
        listFabricationTypeRepository.saveAndFlush(listFabricationType);
        Long bendHistId = bendHist.getId();

        // Get all the listFabricationTypeList where bendHist equals to bendHistId
        defaultListFabricationTypeShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listFabricationTypeList where bendHist equals to bendHistId + 1
        defaultListFabricationTypeShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListFabricationTypesByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listFabricationType.addBuckleArrestorHist(buckleArrestorHist);
        listFabricationTypeRepository.saveAndFlush(listFabricationType);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listFabricationTypeList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListFabricationTypeShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listFabricationTypeList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListFabricationTypeShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListFabricationTypesByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listFabricationType.addPipeHist(pipeHist);
        listFabricationTypeRepository.saveAndFlush(listFabricationType);
        Long pipeHistId = pipeHist.getId();

        // Get all the listFabricationTypeList where pipeHist equals to pipeHistId
        defaultListFabricationTypeShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listFabricationTypeList where pipeHist equals to pipeHistId + 1
        defaultListFabricationTypeShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListFabricationTypesByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listFabricationType.addTeeHist(teeHist);
        listFabricationTypeRepository.saveAndFlush(listFabricationType);
        Long teeHistId = teeHist.getId();

        // Get all the listFabricationTypeList where teeHist equals to teeHistId
        defaultListFabricationTypeShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listFabricationTypeList where teeHist equals to teeHistId + 1
        defaultListFabricationTypeShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListFabricationTypesByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listFabricationType.addValveHist(valveHist);
        listFabricationTypeRepository.saveAndFlush(listFabricationType);
        Long valveHistId = valveHist.getId();

        // Get all the listFabricationTypeList where valveHist equals to valveHistId
        defaultListFabricationTypeShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listFabricationTypeList where valveHist equals to valveHistId + 1
        defaultListFabricationTypeShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListFabricationTypeShouldBeFound(String filter) throws Exception {
        restListFabricationTypeMockMvc.perform(get("/api/list-fabrication-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listFabricationType.getId().intValue())))
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
        restListFabricationTypeMockMvc.perform(get("/api/list-fabrication-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListFabricationTypeShouldNotBeFound(String filter) throws Exception {
        restListFabricationTypeMockMvc.perform(get("/api/list-fabrication-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListFabricationTypeMockMvc.perform(get("/api/list-fabrication-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListFabricationType() throws Exception {
        // Get the listFabricationType
        restListFabricationTypeMockMvc.perform(get("/api/list-fabrication-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListFabricationType() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        int databaseSizeBeforeUpdate = listFabricationTypeRepository.findAll().size();

        // Update the listFabricationType
        ListFabricationType updatedListFabricationType = listFabricationTypeRepository.findById(listFabricationType.getId()).get();
        // Disconnect from session so that the updates on updatedListFabricationType are not directly saved in db
        em.detach(updatedListFabricationType);
        updatedListFabricationType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(updatedListFabricationType);

        restListFabricationTypeMockMvc.perform(put("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListFabricationType in the database
        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeUpdate);
        ListFabricationType testListFabricationType = listFabricationTypeList.get(listFabricationTypeList.size() - 1);
        assertThat(testListFabricationType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListFabricationType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListFabricationType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListFabricationType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListFabricationType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListFabricationType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListFabricationType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListFabricationType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListFabricationType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListFabricationType() throws Exception {
        int databaseSizeBeforeUpdate = listFabricationTypeRepository.findAll().size();

        // Create the ListFabricationType
        ListFabricationTypeDTO listFabricationTypeDTO = listFabricationTypeMapper.toDto(listFabricationType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListFabricationTypeMockMvc.perform(put("/api/list-fabrication-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listFabricationTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListFabricationType in the database
        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListFabricationType() throws Exception {
        // Initialize the database
        listFabricationTypeRepository.saveAndFlush(listFabricationType);

        int databaseSizeBeforeDelete = listFabricationTypeRepository.findAll().size();

        // Delete the listFabricationType
        restListFabricationTypeMockMvc.perform(delete("/api/list-fabrication-types/{id}", listFabricationType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListFabricationType> listFabricationTypeList = listFabricationTypeRepository.findAll();
        assertThat(listFabricationTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListFabricationType.class);
        ListFabricationType listFabricationType1 = new ListFabricationType();
        listFabricationType1.setId(1L);
        ListFabricationType listFabricationType2 = new ListFabricationType();
        listFabricationType2.setId(listFabricationType1.getId());
        assertThat(listFabricationType1).isEqualTo(listFabricationType2);
        listFabricationType2.setId(2L);
        assertThat(listFabricationType1).isNotEqualTo(listFabricationType2);
        listFabricationType1.setId(null);
        assertThat(listFabricationType1).isNotEqualTo(listFabricationType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListFabricationTypeDTO.class);
        ListFabricationTypeDTO listFabricationTypeDTO1 = new ListFabricationTypeDTO();
        listFabricationTypeDTO1.setId(1L);
        ListFabricationTypeDTO listFabricationTypeDTO2 = new ListFabricationTypeDTO();
        assertThat(listFabricationTypeDTO1).isNotEqualTo(listFabricationTypeDTO2);
        listFabricationTypeDTO2.setId(listFabricationTypeDTO1.getId());
        assertThat(listFabricationTypeDTO1).isEqualTo(listFabricationTypeDTO2);
        listFabricationTypeDTO2.setId(2L);
        assertThat(listFabricationTypeDTO1).isNotEqualTo(listFabricationTypeDTO2);
        listFabricationTypeDTO1.setId(null);
        assertThat(listFabricationTypeDTO1).isNotEqualTo(listFabricationTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listFabricationTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listFabricationTypeMapper.fromId(null)).isNull();
    }
}
