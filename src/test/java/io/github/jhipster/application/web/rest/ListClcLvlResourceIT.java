package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListClcLvl;
import io.github.jhipster.application.repository.ListClcLvlRepository;
import io.github.jhipster.application.service.ListClcLvlService;
import io.github.jhipster.application.service.dto.ListClcLvlDTO;
import io.github.jhipster.application.service.mapper.ListClcLvlMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListClcLvlCriteria;
import io.github.jhipster.application.service.ListClcLvlQueryService;

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
 * Integration tests for the {@Link ListClcLvlResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListClcLvlResourceIT {

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
    private ListClcLvlRepository listClcLvlRepository;

    @Autowired
    private ListClcLvlMapper listClcLvlMapper;

    @Autowired
    private ListClcLvlService listClcLvlService;

    @Autowired
    private ListClcLvlQueryService listClcLvlQueryService;

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

    private MockMvc restListClcLvlMockMvc;

    private ListClcLvl listClcLvl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListClcLvlResource listClcLvlResource = new ListClcLvlResource(listClcLvlService, listClcLvlQueryService);
        this.restListClcLvlMockMvc = MockMvcBuilders.standaloneSetup(listClcLvlResource)
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
    public static ListClcLvl createEntity(EntityManager em) {
        ListClcLvl listClcLvl = new ListClcLvl()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listClcLvl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListClcLvl createUpdatedEntity(EntityManager em) {
        ListClcLvl listClcLvl = new ListClcLvl()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listClcLvl;
    }

    @BeforeEach
    public void initTest() {
        listClcLvl = createEntity(em);
    }

    @Test
    @Transactional
    public void createListClcLvl() throws Exception {
        int databaseSizeBeforeCreate = listClcLvlRepository.findAll().size();

        // Create the ListClcLvl
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(listClcLvl);
        restListClcLvlMockMvc.perform(post("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isCreated());

        // Validate the ListClcLvl in the database
        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeCreate + 1);
        ListClcLvl testListClcLvl = listClcLvlList.get(listClcLvlList.size() - 1);
        assertThat(testListClcLvl.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListClcLvl.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListClcLvl.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListClcLvl.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListClcLvl.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListClcLvl.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListClcLvl.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListClcLvl.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListClcLvl.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListClcLvlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listClcLvlRepository.findAll().size();

        // Create the ListClcLvl with an existing ID
        listClcLvl.setId(1L);
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(listClcLvl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListClcLvlMockMvc.perform(post("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcLvl in the database
        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcLvlRepository.findAll().size();
        // set the field null
        listClcLvl.setCode(null);

        // Create the ListClcLvl, which fails.
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(listClcLvl);

        restListClcLvlMockMvc.perform(post("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcLvlRepository.findAll().size();
        // set the field null
        listClcLvl.setName(null);

        // Create the ListClcLvl, which fails.
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(listClcLvl);

        restListClcLvlMockMvc.perform(post("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcLvlRepository.findAll().size();
        // set the field null
        listClcLvl.setFullName(null);

        // Create the ListClcLvl, which fails.
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(listClcLvl);

        restListClcLvlMockMvc.perform(post("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcLvlRepository.findAll().size();
        // set the field null
        listClcLvl.setIsCurrentFlag(null);

        // Create the ListClcLvl, which fails.
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(listClcLvl);

        restListClcLvlMockMvc.perform(post("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListClcLvls() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList
        restListClcLvlMockMvc.perform(get("/api/list-clc-lvls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcLvl.getId().intValue())))
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
    public void getListClcLvl() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get the listClcLvl
        restListClcLvlMockMvc.perform(get("/api/list-clc-lvls/{id}", listClcLvl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listClcLvl.getId().intValue()))
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
    public void getAllListClcLvlsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where code equals to DEFAULT_CODE
        defaultListClcLvlShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listClcLvlList where code equals to UPDATED_CODE
        defaultListClcLvlShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListClcLvlShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listClcLvlList where code equals to UPDATED_CODE
        defaultListClcLvlShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where code is not null
        defaultListClcLvlShouldBeFound("code.specified=true");

        // Get all the listClcLvlList where code is null
        defaultListClcLvlShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where name equals to DEFAULT_NAME
        defaultListClcLvlShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listClcLvlList where name equals to UPDATED_NAME
        defaultListClcLvlShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListClcLvlShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listClcLvlList where name equals to UPDATED_NAME
        defaultListClcLvlShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where name is not null
        defaultListClcLvlShouldBeFound("name.specified=true");

        // Get all the listClcLvlList where name is null
        defaultListClcLvlShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where fullName equals to DEFAULT_FULL_NAME
        defaultListClcLvlShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listClcLvlList where fullName equals to UPDATED_FULL_NAME
        defaultListClcLvlShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListClcLvlShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listClcLvlList where fullName equals to UPDATED_FULL_NAME
        defaultListClcLvlShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where fullName is not null
        defaultListClcLvlShouldBeFound("fullName.specified=true");

        // Get all the listClcLvlList where fullName is null
        defaultListClcLvlShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcLvlShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcLvlList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcLvlShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListClcLvlShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listClcLvlList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcLvlShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where isCurrentFlag is not null
        defaultListClcLvlShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listClcLvlList where isCurrentFlag is null
        defaultListClcLvlShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcLvlShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcLvlList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcLvlShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcLvlShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcLvlList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcLvlShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListClcLvlsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where description equals to DEFAULT_DESCRIPTION
        defaultListClcLvlShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listClcLvlList where description equals to UPDATED_DESCRIPTION
        defaultListClcLvlShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListClcLvlShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listClcLvlList where description equals to UPDATED_DESCRIPTION
        defaultListClcLvlShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where description is not null
        defaultListClcLvlShouldBeFound("description.specified=true");

        // Get all the listClcLvlList where description is null
        defaultListClcLvlShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListClcLvlShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listClcLvlList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcLvlShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListClcLvlShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listClcLvlList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcLvlShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where dateCreate is not null
        defaultListClcLvlShouldBeFound("dateCreate.specified=true");

        // Get all the listClcLvlList where dateCreate is null
        defaultListClcLvlShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListClcLvlShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listClcLvlList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcLvlShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListClcLvlShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listClcLvlList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcLvlShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where dateEdit is not null
        defaultListClcLvlShouldBeFound("dateEdit.specified=true");

        // Get all the listClcLvlList where dateEdit is null
        defaultListClcLvlShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where creator equals to DEFAULT_CREATOR
        defaultListClcLvlShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listClcLvlList where creator equals to UPDATED_CREATOR
        defaultListClcLvlShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListClcLvlShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listClcLvlList where creator equals to UPDATED_CREATOR
        defaultListClcLvlShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where creator is not null
        defaultListClcLvlShouldBeFound("creator.specified=true");

        // Get all the listClcLvlList where creator is null
        defaultListClcLvlShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where editor equals to DEFAULT_EDITOR
        defaultListClcLvlShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listClcLvlList where editor equals to UPDATED_EDITOR
        defaultListClcLvlShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListClcLvlShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listClcLvlList where editor equals to UPDATED_EDITOR
        defaultListClcLvlShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcLvlsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        // Get all the listClcLvlList where editor is not null
        defaultListClcLvlShouldBeFound("editor.specified=true");

        // Get all the listClcLvlList where editor is null
        defaultListClcLvlShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListClcLvlShouldBeFound(String filter) throws Exception {
        restListClcLvlMockMvc.perform(get("/api/list-clc-lvls?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcLvl.getId().intValue())))
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
        restListClcLvlMockMvc.perform(get("/api/list-clc-lvls/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListClcLvlShouldNotBeFound(String filter) throws Exception {
        restListClcLvlMockMvc.perform(get("/api/list-clc-lvls?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListClcLvlMockMvc.perform(get("/api/list-clc-lvls/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListClcLvl() throws Exception {
        // Get the listClcLvl
        restListClcLvlMockMvc.perform(get("/api/list-clc-lvls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListClcLvl() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        int databaseSizeBeforeUpdate = listClcLvlRepository.findAll().size();

        // Update the listClcLvl
        ListClcLvl updatedListClcLvl = listClcLvlRepository.findById(listClcLvl.getId()).get();
        // Disconnect from session so that the updates on updatedListClcLvl are not directly saved in db
        em.detach(updatedListClcLvl);
        updatedListClcLvl
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(updatedListClcLvl);

        restListClcLvlMockMvc.perform(put("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isOk());

        // Validate the ListClcLvl in the database
        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeUpdate);
        ListClcLvl testListClcLvl = listClcLvlList.get(listClcLvlList.size() - 1);
        assertThat(testListClcLvl.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListClcLvl.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListClcLvl.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListClcLvl.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListClcLvl.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListClcLvl.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListClcLvl.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListClcLvl.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListClcLvl.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListClcLvl() throws Exception {
        int databaseSizeBeforeUpdate = listClcLvlRepository.findAll().size();

        // Create the ListClcLvl
        ListClcLvlDTO listClcLvlDTO = listClcLvlMapper.toDto(listClcLvl);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListClcLvlMockMvc.perform(put("/api/list-clc-lvls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcLvlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcLvl in the database
        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListClcLvl() throws Exception {
        // Initialize the database
        listClcLvlRepository.saveAndFlush(listClcLvl);

        int databaseSizeBeforeDelete = listClcLvlRepository.findAll().size();

        // Delete the listClcLvl
        restListClcLvlMockMvc.perform(delete("/api/list-clc-lvls/{id}", listClcLvl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListClcLvl> listClcLvlList = listClcLvlRepository.findAll();
        assertThat(listClcLvlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcLvl.class);
        ListClcLvl listClcLvl1 = new ListClcLvl();
        listClcLvl1.setId(1L);
        ListClcLvl listClcLvl2 = new ListClcLvl();
        listClcLvl2.setId(listClcLvl1.getId());
        assertThat(listClcLvl1).isEqualTo(listClcLvl2);
        listClcLvl2.setId(2L);
        assertThat(listClcLvl1).isNotEqualTo(listClcLvl2);
        listClcLvl1.setId(null);
        assertThat(listClcLvl1).isNotEqualTo(listClcLvl2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcLvlDTO.class);
        ListClcLvlDTO listClcLvlDTO1 = new ListClcLvlDTO();
        listClcLvlDTO1.setId(1L);
        ListClcLvlDTO listClcLvlDTO2 = new ListClcLvlDTO();
        assertThat(listClcLvlDTO1).isNotEqualTo(listClcLvlDTO2);
        listClcLvlDTO2.setId(listClcLvlDTO1.getId());
        assertThat(listClcLvlDTO1).isEqualTo(listClcLvlDTO2);
        listClcLvlDTO2.setId(2L);
        assertThat(listClcLvlDTO1).isNotEqualTo(listClcLvlDTO2);
        listClcLvlDTO1.setId(null);
        assertThat(listClcLvlDTO1).isNotEqualTo(listClcLvlDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listClcLvlMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listClcLvlMapper.fromId(null)).isNull();
    }
}
