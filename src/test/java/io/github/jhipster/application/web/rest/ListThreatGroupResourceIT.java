package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListThreatGroup;
import io.github.jhipster.application.domain.ListThreat;
import io.github.jhipster.application.repository.ListThreatGroupRepository;
import io.github.jhipster.application.service.ListThreatGroupService;
import io.github.jhipster.application.service.dto.ListThreatGroupDTO;
import io.github.jhipster.application.service.mapper.ListThreatGroupMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListThreatGroupCriteria;
import io.github.jhipster.application.service.ListThreatGroupQueryService;

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
 * Integration tests for the {@Link ListThreatGroupResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListThreatGroupResourceIT {

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
    private ListThreatGroupRepository listThreatGroupRepository;

    @Autowired
    private ListThreatGroupMapper listThreatGroupMapper;

    @Autowired
    private ListThreatGroupService listThreatGroupService;

    @Autowired
    private ListThreatGroupQueryService listThreatGroupQueryService;

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

    private MockMvc restListThreatGroupMockMvc;

    private ListThreatGroup listThreatGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListThreatGroupResource listThreatGroupResource = new ListThreatGroupResource(listThreatGroupService, listThreatGroupQueryService);
        this.restListThreatGroupMockMvc = MockMvcBuilders.standaloneSetup(listThreatGroupResource)
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
    public static ListThreatGroup createEntity(EntityManager em) {
        ListThreatGroup listThreatGroup = new ListThreatGroup()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listThreatGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListThreatGroup createUpdatedEntity(EntityManager em) {
        ListThreatGroup listThreatGroup = new ListThreatGroup()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listThreatGroup;
    }

    @BeforeEach
    public void initTest() {
        listThreatGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createListThreatGroup() throws Exception {
        int databaseSizeBeforeCreate = listThreatGroupRepository.findAll().size();

        // Create the ListThreatGroup
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(listThreatGroup);
        restListThreatGroupMockMvc.perform(post("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the ListThreatGroup in the database
        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeCreate + 1);
        ListThreatGroup testListThreatGroup = listThreatGroupList.get(listThreatGroupList.size() - 1);
        assertThat(testListThreatGroup.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListThreatGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListThreatGroup.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListThreatGroup.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListThreatGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListThreatGroup.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListThreatGroup.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListThreatGroup.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListThreatGroup.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListThreatGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listThreatGroupRepository.findAll().size();

        // Create the ListThreatGroup with an existing ID
        listThreatGroup.setId(1L);
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(listThreatGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListThreatGroupMockMvc.perform(post("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListThreatGroup in the database
        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatGroupRepository.findAll().size();
        // set the field null
        listThreatGroup.setCode(null);

        // Create the ListThreatGroup, which fails.
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(listThreatGroup);

        restListThreatGroupMockMvc.perform(post("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatGroupRepository.findAll().size();
        // set the field null
        listThreatGroup.setName(null);

        // Create the ListThreatGroup, which fails.
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(listThreatGroup);

        restListThreatGroupMockMvc.perform(post("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatGroupRepository.findAll().size();
        // set the field null
        listThreatGroup.setFullName(null);

        // Create the ListThreatGroup, which fails.
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(listThreatGroup);

        restListThreatGroupMockMvc.perform(post("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatGroupRepository.findAll().size();
        // set the field null
        listThreatGroup.setIsCurrentFlag(null);

        // Create the ListThreatGroup, which fails.
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(listThreatGroup);

        restListThreatGroupMockMvc.perform(post("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListThreatGroups() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList
        restListThreatGroupMockMvc.perform(get("/api/list-threat-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listThreatGroup.getId().intValue())))
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
    public void getListThreatGroup() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get the listThreatGroup
        restListThreatGroupMockMvc.perform(get("/api/list-threat-groups/{id}", listThreatGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listThreatGroup.getId().intValue()))
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
    public void getAllListThreatGroupsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where code equals to DEFAULT_CODE
        defaultListThreatGroupShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listThreatGroupList where code equals to UPDATED_CODE
        defaultListThreatGroupShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListThreatGroupShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listThreatGroupList where code equals to UPDATED_CODE
        defaultListThreatGroupShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where code is not null
        defaultListThreatGroupShouldBeFound("code.specified=true");

        // Get all the listThreatGroupList where code is null
        defaultListThreatGroupShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where name equals to DEFAULT_NAME
        defaultListThreatGroupShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listThreatGroupList where name equals to UPDATED_NAME
        defaultListThreatGroupShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListThreatGroupShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listThreatGroupList where name equals to UPDATED_NAME
        defaultListThreatGroupShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where name is not null
        defaultListThreatGroupShouldBeFound("name.specified=true");

        // Get all the listThreatGroupList where name is null
        defaultListThreatGroupShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where fullName equals to DEFAULT_FULL_NAME
        defaultListThreatGroupShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listThreatGroupList where fullName equals to UPDATED_FULL_NAME
        defaultListThreatGroupShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListThreatGroupShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listThreatGroupList where fullName equals to UPDATED_FULL_NAME
        defaultListThreatGroupShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where fullName is not null
        defaultListThreatGroupShouldBeFound("fullName.specified=true");

        // Get all the listThreatGroupList where fullName is null
        defaultListThreatGroupShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListThreatGroupShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listThreatGroupList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatGroupShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListThreatGroupShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listThreatGroupList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatGroupShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where isCurrentFlag is not null
        defaultListThreatGroupShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listThreatGroupList where isCurrentFlag is null
        defaultListThreatGroupShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListThreatGroupShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listThreatGroupList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatGroupShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListThreatGroupShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listThreatGroupList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatGroupShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListThreatGroupsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where description equals to DEFAULT_DESCRIPTION
        defaultListThreatGroupShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listThreatGroupList where description equals to UPDATED_DESCRIPTION
        defaultListThreatGroupShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListThreatGroupShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listThreatGroupList where description equals to UPDATED_DESCRIPTION
        defaultListThreatGroupShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where description is not null
        defaultListThreatGroupShouldBeFound("description.specified=true");

        // Get all the listThreatGroupList where description is null
        defaultListThreatGroupShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListThreatGroupShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listThreatGroupList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListThreatGroupShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListThreatGroupShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listThreatGroupList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListThreatGroupShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where dateCreate is not null
        defaultListThreatGroupShouldBeFound("dateCreate.specified=true");

        // Get all the listThreatGroupList where dateCreate is null
        defaultListThreatGroupShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListThreatGroupShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listThreatGroupList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListThreatGroupShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListThreatGroupShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listThreatGroupList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListThreatGroupShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where dateEdit is not null
        defaultListThreatGroupShouldBeFound("dateEdit.specified=true");

        // Get all the listThreatGroupList where dateEdit is null
        defaultListThreatGroupShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where creator equals to DEFAULT_CREATOR
        defaultListThreatGroupShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listThreatGroupList where creator equals to UPDATED_CREATOR
        defaultListThreatGroupShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListThreatGroupShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listThreatGroupList where creator equals to UPDATED_CREATOR
        defaultListThreatGroupShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where creator is not null
        defaultListThreatGroupShouldBeFound("creator.specified=true");

        // Get all the listThreatGroupList where creator is null
        defaultListThreatGroupShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where editor equals to DEFAULT_EDITOR
        defaultListThreatGroupShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listThreatGroupList where editor equals to UPDATED_EDITOR
        defaultListThreatGroupShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListThreatGroupShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listThreatGroupList where editor equals to UPDATED_EDITOR
        defaultListThreatGroupShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        // Get all the listThreatGroupList where editor is not null
        defaultListThreatGroupShouldBeFound("editor.specified=true");

        // Get all the listThreatGroupList where editor is null
        defaultListThreatGroupShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatGroupsByListThreatIsEqualToSomething() throws Exception {
        // Initialize the database
        ListThreat listThreat = ListThreatResourceIT.createEntity(em);
        em.persist(listThreat);
        em.flush();
        listThreatGroup.addListThreat(listThreat);
        listThreatGroupRepository.saveAndFlush(listThreatGroup);
        Long listThreatId = listThreat.getId();

        // Get all the listThreatGroupList where listThreat equals to listThreatId
        defaultListThreatGroupShouldBeFound("listThreatId.equals=" + listThreatId);

        // Get all the listThreatGroupList where listThreat equals to listThreatId + 1
        defaultListThreatGroupShouldNotBeFound("listThreatId.equals=" + (listThreatId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListThreatGroupShouldBeFound(String filter) throws Exception {
        restListThreatGroupMockMvc.perform(get("/api/list-threat-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listThreatGroup.getId().intValue())))
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
        restListThreatGroupMockMvc.perform(get("/api/list-threat-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListThreatGroupShouldNotBeFound(String filter) throws Exception {
        restListThreatGroupMockMvc.perform(get("/api/list-threat-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListThreatGroupMockMvc.perform(get("/api/list-threat-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListThreatGroup() throws Exception {
        // Get the listThreatGroup
        restListThreatGroupMockMvc.perform(get("/api/list-threat-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListThreatGroup() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        int databaseSizeBeforeUpdate = listThreatGroupRepository.findAll().size();

        // Update the listThreatGroup
        ListThreatGroup updatedListThreatGroup = listThreatGroupRepository.findById(listThreatGroup.getId()).get();
        // Disconnect from session so that the updates on updatedListThreatGroup are not directly saved in db
        em.detach(updatedListThreatGroup);
        updatedListThreatGroup
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(updatedListThreatGroup);

        restListThreatGroupMockMvc.perform(put("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isOk());

        // Validate the ListThreatGroup in the database
        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeUpdate);
        ListThreatGroup testListThreatGroup = listThreatGroupList.get(listThreatGroupList.size() - 1);
        assertThat(testListThreatGroup.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListThreatGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListThreatGroup.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListThreatGroup.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListThreatGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListThreatGroup.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListThreatGroup.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListThreatGroup.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListThreatGroup.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListThreatGroup() throws Exception {
        int databaseSizeBeforeUpdate = listThreatGroupRepository.findAll().size();

        // Create the ListThreatGroup
        ListThreatGroupDTO listThreatGroupDTO = listThreatGroupMapper.toDto(listThreatGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListThreatGroupMockMvc.perform(put("/api/list-threat-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListThreatGroup in the database
        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListThreatGroup() throws Exception {
        // Initialize the database
        listThreatGroupRepository.saveAndFlush(listThreatGroup);

        int databaseSizeBeforeDelete = listThreatGroupRepository.findAll().size();

        // Delete the listThreatGroup
        restListThreatGroupMockMvc.perform(delete("/api/list-threat-groups/{id}", listThreatGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListThreatGroup> listThreatGroupList = listThreatGroupRepository.findAll();
        assertThat(listThreatGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListThreatGroup.class);
        ListThreatGroup listThreatGroup1 = new ListThreatGroup();
        listThreatGroup1.setId(1L);
        ListThreatGroup listThreatGroup2 = new ListThreatGroup();
        listThreatGroup2.setId(listThreatGroup1.getId());
        assertThat(listThreatGroup1).isEqualTo(listThreatGroup2);
        listThreatGroup2.setId(2L);
        assertThat(listThreatGroup1).isNotEqualTo(listThreatGroup2);
        listThreatGroup1.setId(null);
        assertThat(listThreatGroup1).isNotEqualTo(listThreatGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListThreatGroupDTO.class);
        ListThreatGroupDTO listThreatGroupDTO1 = new ListThreatGroupDTO();
        listThreatGroupDTO1.setId(1L);
        ListThreatGroupDTO listThreatGroupDTO2 = new ListThreatGroupDTO();
        assertThat(listThreatGroupDTO1).isNotEqualTo(listThreatGroupDTO2);
        listThreatGroupDTO2.setId(listThreatGroupDTO1.getId());
        assertThat(listThreatGroupDTO1).isEqualTo(listThreatGroupDTO2);
        listThreatGroupDTO2.setId(2L);
        assertThat(listThreatGroupDTO1).isNotEqualTo(listThreatGroupDTO2);
        listThreatGroupDTO1.setId(null);
        assertThat(listThreatGroupDTO1).isNotEqualTo(listThreatGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listThreatGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listThreatGroupMapper.fromId(null)).isNull();
    }
}
