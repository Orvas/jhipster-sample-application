package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListLongSeamWeldTypeRepository;
import io.github.jhipster.application.service.ListLongSeamWeldTypeService;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeDTO;
import io.github.jhipster.application.service.mapper.ListLongSeamWeldTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeCriteria;
import io.github.jhipster.application.service.ListLongSeamWeldTypeQueryService;

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
 * Integration tests for the {@Link ListLongSeamWeldTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListLongSeamWeldTypeResourceIT {

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
    private ListLongSeamWeldTypeRepository listLongSeamWeldTypeRepository;

    @Autowired
    private ListLongSeamWeldTypeMapper listLongSeamWeldTypeMapper;

    @Autowired
    private ListLongSeamWeldTypeService listLongSeamWeldTypeService;

    @Autowired
    private ListLongSeamWeldTypeQueryService listLongSeamWeldTypeQueryService;

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

    private MockMvc restListLongSeamWeldTypeMockMvc;

    private ListLongSeamWeldType listLongSeamWeldType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListLongSeamWeldTypeResource listLongSeamWeldTypeResource = new ListLongSeamWeldTypeResource(listLongSeamWeldTypeService, listLongSeamWeldTypeQueryService);
        this.restListLongSeamWeldTypeMockMvc = MockMvcBuilders.standaloneSetup(listLongSeamWeldTypeResource)
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
    public static ListLongSeamWeldType createEntity(EntityManager em) {
        ListLongSeamWeldType listLongSeamWeldType = new ListLongSeamWeldType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listLongSeamWeldType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListLongSeamWeldType createUpdatedEntity(EntityManager em) {
        ListLongSeamWeldType listLongSeamWeldType = new ListLongSeamWeldType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listLongSeamWeldType;
    }

    @BeforeEach
    public void initTest() {
        listLongSeamWeldType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListLongSeamWeldType() throws Exception {
        int databaseSizeBeforeCreate = listLongSeamWeldTypeRepository.findAll().size();

        // Create the ListLongSeamWeldType
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);
        restListLongSeamWeldTypeMockMvc.perform(post("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListLongSeamWeldType in the database
        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListLongSeamWeldType testListLongSeamWeldType = listLongSeamWeldTypeList.get(listLongSeamWeldTypeList.size() - 1);
        assertThat(testListLongSeamWeldType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListLongSeamWeldType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListLongSeamWeldType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListLongSeamWeldType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListLongSeamWeldType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListLongSeamWeldType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListLongSeamWeldType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListLongSeamWeldType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListLongSeamWeldType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListLongSeamWeldTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listLongSeamWeldTypeRepository.findAll().size();

        // Create the ListLongSeamWeldType with an existing ID
        listLongSeamWeldType.setId(1L);
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListLongSeamWeldTypeMockMvc.perform(post("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListLongSeamWeldType in the database
        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listLongSeamWeldTypeRepository.findAll().size();
        // set the field null
        listLongSeamWeldType.setCode(null);

        // Create the ListLongSeamWeldType, which fails.
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);

        restListLongSeamWeldTypeMockMvc.perform(post("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listLongSeamWeldTypeRepository.findAll().size();
        // set the field null
        listLongSeamWeldType.setName(null);

        // Create the ListLongSeamWeldType, which fails.
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);

        restListLongSeamWeldTypeMockMvc.perform(post("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listLongSeamWeldTypeRepository.findAll().size();
        // set the field null
        listLongSeamWeldType.setFullName(null);

        // Create the ListLongSeamWeldType, which fails.
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);

        restListLongSeamWeldTypeMockMvc.perform(post("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listLongSeamWeldTypeRepository.findAll().size();
        // set the field null
        listLongSeamWeldType.setIsCurrentFlag(null);

        // Create the ListLongSeamWeldType, which fails.
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);

        restListLongSeamWeldTypeMockMvc.perform(post("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypes() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList
        restListLongSeamWeldTypeMockMvc.perform(get("/api/list-long-seam-weld-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listLongSeamWeldType.getId().intValue())))
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
    public void getListLongSeamWeldType() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get the listLongSeamWeldType
        restListLongSeamWeldTypeMockMvc.perform(get("/api/list-long-seam-weld-types/{id}", listLongSeamWeldType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listLongSeamWeldType.getId().intValue()))
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
    public void getAllListLongSeamWeldTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where code equals to DEFAULT_CODE
        defaultListLongSeamWeldTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listLongSeamWeldTypeList where code equals to UPDATED_CODE
        defaultListLongSeamWeldTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListLongSeamWeldTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listLongSeamWeldTypeList where code equals to UPDATED_CODE
        defaultListLongSeamWeldTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where code is not null
        defaultListLongSeamWeldTypeShouldBeFound("code.specified=true");

        // Get all the listLongSeamWeldTypeList where code is null
        defaultListLongSeamWeldTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where name equals to DEFAULT_NAME
        defaultListLongSeamWeldTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listLongSeamWeldTypeList where name equals to UPDATED_NAME
        defaultListLongSeamWeldTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListLongSeamWeldTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listLongSeamWeldTypeList where name equals to UPDATED_NAME
        defaultListLongSeamWeldTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where name is not null
        defaultListLongSeamWeldTypeShouldBeFound("name.specified=true");

        // Get all the listLongSeamWeldTypeList where name is null
        defaultListLongSeamWeldTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListLongSeamWeldTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listLongSeamWeldTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListLongSeamWeldTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListLongSeamWeldTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listLongSeamWeldTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListLongSeamWeldTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where fullName is not null
        defaultListLongSeamWeldTypeShouldBeFound("fullName.specified=true");

        // Get all the listLongSeamWeldTypeList where fullName is null
        defaultListLongSeamWeldTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag is not null
        defaultListLongSeamWeldTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listLongSeamWeldTypeList where isCurrentFlag is null
        defaultListLongSeamWeldTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listLongSeamWeldTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListLongSeamWeldTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListLongSeamWeldTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listLongSeamWeldTypeList where description equals to UPDATED_DESCRIPTION
        defaultListLongSeamWeldTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListLongSeamWeldTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listLongSeamWeldTypeList where description equals to UPDATED_DESCRIPTION
        defaultListLongSeamWeldTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where description is not null
        defaultListLongSeamWeldTypeShouldBeFound("description.specified=true");

        // Get all the listLongSeamWeldTypeList where description is null
        defaultListLongSeamWeldTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListLongSeamWeldTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listLongSeamWeldTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListLongSeamWeldTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListLongSeamWeldTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listLongSeamWeldTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListLongSeamWeldTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where dateCreate is not null
        defaultListLongSeamWeldTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listLongSeamWeldTypeList where dateCreate is null
        defaultListLongSeamWeldTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListLongSeamWeldTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listLongSeamWeldTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListLongSeamWeldTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListLongSeamWeldTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listLongSeamWeldTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListLongSeamWeldTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where dateEdit is not null
        defaultListLongSeamWeldTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listLongSeamWeldTypeList where dateEdit is null
        defaultListLongSeamWeldTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where creator equals to DEFAULT_CREATOR
        defaultListLongSeamWeldTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listLongSeamWeldTypeList where creator equals to UPDATED_CREATOR
        defaultListLongSeamWeldTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListLongSeamWeldTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listLongSeamWeldTypeList where creator equals to UPDATED_CREATOR
        defaultListLongSeamWeldTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where creator is not null
        defaultListLongSeamWeldTypeShouldBeFound("creator.specified=true");

        // Get all the listLongSeamWeldTypeList where creator is null
        defaultListLongSeamWeldTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where editor equals to DEFAULT_EDITOR
        defaultListLongSeamWeldTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listLongSeamWeldTypeList where editor equals to UPDATED_EDITOR
        defaultListLongSeamWeldTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListLongSeamWeldTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listLongSeamWeldTypeList where editor equals to UPDATED_EDITOR
        defaultListLongSeamWeldTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        // Get all the listLongSeamWeldTypeList where editor is not null
        defaultListLongSeamWeldTypeShouldBeFound("editor.specified=true");

        // Get all the listLongSeamWeldTypeList where editor is null
        defaultListLongSeamWeldTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listLongSeamWeldType.addBendHist(bendHist);
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);
        Long bendHistId = bendHist.getId();

        // Get all the listLongSeamWeldTypeList where bendHist equals to bendHistId
        defaultListLongSeamWeldTypeShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listLongSeamWeldTypeList where bendHist equals to bendHistId + 1
        defaultListLongSeamWeldTypeShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listLongSeamWeldType.addBuckleArrestorHist(buckleArrestorHist);
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listLongSeamWeldTypeList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListLongSeamWeldTypeShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listLongSeamWeldTypeList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListLongSeamWeldTypeShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listLongSeamWeldType.addPipeHist(pipeHist);
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);
        Long pipeHistId = pipeHist.getId();

        // Get all the listLongSeamWeldTypeList where pipeHist equals to pipeHistId
        defaultListLongSeamWeldTypeShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listLongSeamWeldTypeList where pipeHist equals to pipeHistId + 1
        defaultListLongSeamWeldTypeShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listLongSeamWeldType.addTeeHist(teeHist);
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);
        Long teeHistId = teeHist.getId();

        // Get all the listLongSeamWeldTypeList where teeHist equals to teeHistId
        defaultListLongSeamWeldTypeShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listLongSeamWeldTypeList where teeHist equals to teeHistId + 1
        defaultListLongSeamWeldTypeShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListLongSeamWeldTypesByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listLongSeamWeldType.addValveHist(valveHist);
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);
        Long valveHistId = valveHist.getId();

        // Get all the listLongSeamWeldTypeList where valveHist equals to valveHistId
        defaultListLongSeamWeldTypeShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listLongSeamWeldTypeList where valveHist equals to valveHistId + 1
        defaultListLongSeamWeldTypeShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListLongSeamWeldTypeShouldBeFound(String filter) throws Exception {
        restListLongSeamWeldTypeMockMvc.perform(get("/api/list-long-seam-weld-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listLongSeamWeldType.getId().intValue())))
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
        restListLongSeamWeldTypeMockMvc.perform(get("/api/list-long-seam-weld-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListLongSeamWeldTypeShouldNotBeFound(String filter) throws Exception {
        restListLongSeamWeldTypeMockMvc.perform(get("/api/list-long-seam-weld-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListLongSeamWeldTypeMockMvc.perform(get("/api/list-long-seam-weld-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListLongSeamWeldType() throws Exception {
        // Get the listLongSeamWeldType
        restListLongSeamWeldTypeMockMvc.perform(get("/api/list-long-seam-weld-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListLongSeamWeldType() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        int databaseSizeBeforeUpdate = listLongSeamWeldTypeRepository.findAll().size();

        // Update the listLongSeamWeldType
        ListLongSeamWeldType updatedListLongSeamWeldType = listLongSeamWeldTypeRepository.findById(listLongSeamWeldType.getId()).get();
        // Disconnect from session so that the updates on updatedListLongSeamWeldType are not directly saved in db
        em.detach(updatedListLongSeamWeldType);
        updatedListLongSeamWeldType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(updatedListLongSeamWeldType);

        restListLongSeamWeldTypeMockMvc.perform(put("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListLongSeamWeldType in the database
        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeUpdate);
        ListLongSeamWeldType testListLongSeamWeldType = listLongSeamWeldTypeList.get(listLongSeamWeldTypeList.size() - 1);
        assertThat(testListLongSeamWeldType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListLongSeamWeldType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListLongSeamWeldType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListLongSeamWeldType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListLongSeamWeldType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListLongSeamWeldType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListLongSeamWeldType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListLongSeamWeldType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListLongSeamWeldType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListLongSeamWeldType() throws Exception {
        int databaseSizeBeforeUpdate = listLongSeamWeldTypeRepository.findAll().size();

        // Create the ListLongSeamWeldType
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO = listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListLongSeamWeldTypeMockMvc.perform(put("/api/list-long-seam-weld-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listLongSeamWeldTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListLongSeamWeldType in the database
        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListLongSeamWeldType() throws Exception {
        // Initialize the database
        listLongSeamWeldTypeRepository.saveAndFlush(listLongSeamWeldType);

        int databaseSizeBeforeDelete = listLongSeamWeldTypeRepository.findAll().size();

        // Delete the listLongSeamWeldType
        restListLongSeamWeldTypeMockMvc.perform(delete("/api/list-long-seam-weld-types/{id}", listLongSeamWeldType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListLongSeamWeldType> listLongSeamWeldTypeList = listLongSeamWeldTypeRepository.findAll();
        assertThat(listLongSeamWeldTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListLongSeamWeldType.class);
        ListLongSeamWeldType listLongSeamWeldType1 = new ListLongSeamWeldType();
        listLongSeamWeldType1.setId(1L);
        ListLongSeamWeldType listLongSeamWeldType2 = new ListLongSeamWeldType();
        listLongSeamWeldType2.setId(listLongSeamWeldType1.getId());
        assertThat(listLongSeamWeldType1).isEqualTo(listLongSeamWeldType2);
        listLongSeamWeldType2.setId(2L);
        assertThat(listLongSeamWeldType1).isNotEqualTo(listLongSeamWeldType2);
        listLongSeamWeldType1.setId(null);
        assertThat(listLongSeamWeldType1).isNotEqualTo(listLongSeamWeldType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListLongSeamWeldTypeDTO.class);
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO1 = new ListLongSeamWeldTypeDTO();
        listLongSeamWeldTypeDTO1.setId(1L);
        ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO2 = new ListLongSeamWeldTypeDTO();
        assertThat(listLongSeamWeldTypeDTO1).isNotEqualTo(listLongSeamWeldTypeDTO2);
        listLongSeamWeldTypeDTO2.setId(listLongSeamWeldTypeDTO1.getId());
        assertThat(listLongSeamWeldTypeDTO1).isEqualTo(listLongSeamWeldTypeDTO2);
        listLongSeamWeldTypeDTO2.setId(2L);
        assertThat(listLongSeamWeldTypeDTO1).isNotEqualTo(listLongSeamWeldTypeDTO2);
        listLongSeamWeldTypeDTO1.setId(null);
        assertThat(listLongSeamWeldTypeDTO1).isNotEqualTo(listLongSeamWeldTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listLongSeamWeldTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listLongSeamWeldTypeMapper.fromId(null)).isNull();
    }
}
