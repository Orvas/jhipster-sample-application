package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListDfctGroup;
import io.github.jhipster.application.domain.ListDfctType;
import io.github.jhipster.application.repository.ListDfctGroupRepository;
import io.github.jhipster.application.service.ListDfctGroupService;
import io.github.jhipster.application.service.dto.ListDfctGroupDTO;
import io.github.jhipster.application.service.mapper.ListDfctGroupMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListDfctGroupCriteria;
import io.github.jhipster.application.service.ListDfctGroupQueryService;

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
 * Integration tests for the {@Link ListDfctGroupResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListDfctGroupResourceIT {

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
    private ListDfctGroupRepository listDfctGroupRepository;

    @Autowired
    private ListDfctGroupMapper listDfctGroupMapper;

    @Autowired
    private ListDfctGroupService listDfctGroupService;

    @Autowired
    private ListDfctGroupQueryService listDfctGroupQueryService;

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

    private MockMvc restListDfctGroupMockMvc;

    private ListDfctGroup listDfctGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListDfctGroupResource listDfctGroupResource = new ListDfctGroupResource(listDfctGroupService, listDfctGroupQueryService);
        this.restListDfctGroupMockMvc = MockMvcBuilders.standaloneSetup(listDfctGroupResource)
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
    public static ListDfctGroup createEntity(EntityManager em) {
        ListDfctGroup listDfctGroup = new ListDfctGroup()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listDfctGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListDfctGroup createUpdatedEntity(EntityManager em) {
        ListDfctGroup listDfctGroup = new ListDfctGroup()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listDfctGroup;
    }

    @BeforeEach
    public void initTest() {
        listDfctGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createListDfctGroup() throws Exception {
        int databaseSizeBeforeCreate = listDfctGroupRepository.findAll().size();

        // Create the ListDfctGroup
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(listDfctGroup);
        restListDfctGroupMockMvc.perform(post("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the ListDfctGroup in the database
        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeCreate + 1);
        ListDfctGroup testListDfctGroup = listDfctGroupList.get(listDfctGroupList.size() - 1);
        assertThat(testListDfctGroup.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListDfctGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListDfctGroup.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListDfctGroup.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListDfctGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListDfctGroup.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListDfctGroup.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListDfctGroup.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListDfctGroup.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListDfctGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listDfctGroupRepository.findAll().size();

        // Create the ListDfctGroup with an existing ID
        listDfctGroup.setId(1L);
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(listDfctGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListDfctGroupMockMvc.perform(post("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListDfctGroup in the database
        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctGroupRepository.findAll().size();
        // set the field null
        listDfctGroup.setCode(null);

        // Create the ListDfctGroup, which fails.
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(listDfctGroup);

        restListDfctGroupMockMvc.perform(post("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctGroupRepository.findAll().size();
        // set the field null
        listDfctGroup.setName(null);

        // Create the ListDfctGroup, which fails.
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(listDfctGroup);

        restListDfctGroupMockMvc.perform(post("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctGroupRepository.findAll().size();
        // set the field null
        listDfctGroup.setFullName(null);

        // Create the ListDfctGroup, which fails.
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(listDfctGroup);

        restListDfctGroupMockMvc.perform(post("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctGroupRepository.findAll().size();
        // set the field null
        listDfctGroup.setIsCurrentFlag(null);

        // Create the ListDfctGroup, which fails.
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(listDfctGroup);

        restListDfctGroupMockMvc.perform(post("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListDfctGroups() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList
        restListDfctGroupMockMvc.perform(get("/api/list-dfct-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listDfctGroup.getId().intValue())))
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
    public void getListDfctGroup() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get the listDfctGroup
        restListDfctGroupMockMvc.perform(get("/api/list-dfct-groups/{id}", listDfctGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listDfctGroup.getId().intValue()))
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
    public void getAllListDfctGroupsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where code equals to DEFAULT_CODE
        defaultListDfctGroupShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listDfctGroupList where code equals to UPDATED_CODE
        defaultListDfctGroupShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListDfctGroupShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listDfctGroupList where code equals to UPDATED_CODE
        defaultListDfctGroupShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where code is not null
        defaultListDfctGroupShouldBeFound("code.specified=true");

        // Get all the listDfctGroupList where code is null
        defaultListDfctGroupShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where name equals to DEFAULT_NAME
        defaultListDfctGroupShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listDfctGroupList where name equals to UPDATED_NAME
        defaultListDfctGroupShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListDfctGroupShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listDfctGroupList where name equals to UPDATED_NAME
        defaultListDfctGroupShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where name is not null
        defaultListDfctGroupShouldBeFound("name.specified=true");

        // Get all the listDfctGroupList where name is null
        defaultListDfctGroupShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where fullName equals to DEFAULT_FULL_NAME
        defaultListDfctGroupShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listDfctGroupList where fullName equals to UPDATED_FULL_NAME
        defaultListDfctGroupShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListDfctGroupShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listDfctGroupList where fullName equals to UPDATED_FULL_NAME
        defaultListDfctGroupShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where fullName is not null
        defaultListDfctGroupShouldBeFound("fullName.specified=true");

        // Get all the listDfctGroupList where fullName is null
        defaultListDfctGroupShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctGroupShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctGroupList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctGroupShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListDfctGroupShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listDfctGroupList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctGroupShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where isCurrentFlag is not null
        defaultListDfctGroupShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listDfctGroupList where isCurrentFlag is null
        defaultListDfctGroupShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctGroupShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctGroupList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctGroupShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctGroupShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctGroupList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctGroupShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListDfctGroupsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where description equals to DEFAULT_DESCRIPTION
        defaultListDfctGroupShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listDfctGroupList where description equals to UPDATED_DESCRIPTION
        defaultListDfctGroupShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListDfctGroupShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listDfctGroupList where description equals to UPDATED_DESCRIPTION
        defaultListDfctGroupShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where description is not null
        defaultListDfctGroupShouldBeFound("description.specified=true");

        // Get all the listDfctGroupList where description is null
        defaultListDfctGroupShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListDfctGroupShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listDfctGroupList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListDfctGroupShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListDfctGroupShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listDfctGroupList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListDfctGroupShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where dateCreate is not null
        defaultListDfctGroupShouldBeFound("dateCreate.specified=true");

        // Get all the listDfctGroupList where dateCreate is null
        defaultListDfctGroupShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListDfctGroupShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listDfctGroupList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListDfctGroupShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListDfctGroupShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listDfctGroupList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListDfctGroupShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where dateEdit is not null
        defaultListDfctGroupShouldBeFound("dateEdit.specified=true");

        // Get all the listDfctGroupList where dateEdit is null
        defaultListDfctGroupShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where creator equals to DEFAULT_CREATOR
        defaultListDfctGroupShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listDfctGroupList where creator equals to UPDATED_CREATOR
        defaultListDfctGroupShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListDfctGroupShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listDfctGroupList where creator equals to UPDATED_CREATOR
        defaultListDfctGroupShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where creator is not null
        defaultListDfctGroupShouldBeFound("creator.specified=true");

        // Get all the listDfctGroupList where creator is null
        defaultListDfctGroupShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where editor equals to DEFAULT_EDITOR
        defaultListDfctGroupShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listDfctGroupList where editor equals to UPDATED_EDITOR
        defaultListDfctGroupShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListDfctGroupShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listDfctGroupList where editor equals to UPDATED_EDITOR
        defaultListDfctGroupShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        // Get all the listDfctGroupList where editor is not null
        defaultListDfctGroupShouldBeFound("editor.specified=true");

        // Get all the listDfctGroupList where editor is null
        defaultListDfctGroupShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctGroupsByListDfctTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListDfctType listDfctType = ListDfctTypeResourceIT.createEntity(em);
        em.persist(listDfctType);
        em.flush();
        listDfctGroup.addListDfctType(listDfctType);
        listDfctGroupRepository.saveAndFlush(listDfctGroup);
        Long listDfctTypeId = listDfctType.getId();

        // Get all the listDfctGroupList where listDfctType equals to listDfctTypeId
        defaultListDfctGroupShouldBeFound("listDfctTypeId.equals=" + listDfctTypeId);

        // Get all the listDfctGroupList where listDfctType equals to listDfctTypeId + 1
        defaultListDfctGroupShouldNotBeFound("listDfctTypeId.equals=" + (listDfctTypeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListDfctGroupShouldBeFound(String filter) throws Exception {
        restListDfctGroupMockMvc.perform(get("/api/list-dfct-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listDfctGroup.getId().intValue())))
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
        restListDfctGroupMockMvc.perform(get("/api/list-dfct-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListDfctGroupShouldNotBeFound(String filter) throws Exception {
        restListDfctGroupMockMvc.perform(get("/api/list-dfct-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListDfctGroupMockMvc.perform(get("/api/list-dfct-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListDfctGroup() throws Exception {
        // Get the listDfctGroup
        restListDfctGroupMockMvc.perform(get("/api/list-dfct-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListDfctGroup() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        int databaseSizeBeforeUpdate = listDfctGroupRepository.findAll().size();

        // Update the listDfctGroup
        ListDfctGroup updatedListDfctGroup = listDfctGroupRepository.findById(listDfctGroup.getId()).get();
        // Disconnect from session so that the updates on updatedListDfctGroup are not directly saved in db
        em.detach(updatedListDfctGroup);
        updatedListDfctGroup
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(updatedListDfctGroup);

        restListDfctGroupMockMvc.perform(put("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isOk());

        // Validate the ListDfctGroup in the database
        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeUpdate);
        ListDfctGroup testListDfctGroup = listDfctGroupList.get(listDfctGroupList.size() - 1);
        assertThat(testListDfctGroup.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListDfctGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListDfctGroup.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListDfctGroup.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListDfctGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListDfctGroup.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListDfctGroup.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListDfctGroup.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListDfctGroup.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListDfctGroup() throws Exception {
        int databaseSizeBeforeUpdate = listDfctGroupRepository.findAll().size();

        // Create the ListDfctGroup
        ListDfctGroupDTO listDfctGroupDTO = listDfctGroupMapper.toDto(listDfctGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListDfctGroupMockMvc.perform(put("/api/list-dfct-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListDfctGroup in the database
        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListDfctGroup() throws Exception {
        // Initialize the database
        listDfctGroupRepository.saveAndFlush(listDfctGroup);

        int databaseSizeBeforeDelete = listDfctGroupRepository.findAll().size();

        // Delete the listDfctGroup
        restListDfctGroupMockMvc.perform(delete("/api/list-dfct-groups/{id}", listDfctGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListDfctGroup> listDfctGroupList = listDfctGroupRepository.findAll();
        assertThat(listDfctGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListDfctGroup.class);
        ListDfctGroup listDfctGroup1 = new ListDfctGroup();
        listDfctGroup1.setId(1L);
        ListDfctGroup listDfctGroup2 = new ListDfctGroup();
        listDfctGroup2.setId(listDfctGroup1.getId());
        assertThat(listDfctGroup1).isEqualTo(listDfctGroup2);
        listDfctGroup2.setId(2L);
        assertThat(listDfctGroup1).isNotEqualTo(listDfctGroup2);
        listDfctGroup1.setId(null);
        assertThat(listDfctGroup1).isNotEqualTo(listDfctGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListDfctGroupDTO.class);
        ListDfctGroupDTO listDfctGroupDTO1 = new ListDfctGroupDTO();
        listDfctGroupDTO1.setId(1L);
        ListDfctGroupDTO listDfctGroupDTO2 = new ListDfctGroupDTO();
        assertThat(listDfctGroupDTO1).isNotEqualTo(listDfctGroupDTO2);
        listDfctGroupDTO2.setId(listDfctGroupDTO1.getId());
        assertThat(listDfctGroupDTO1).isEqualTo(listDfctGroupDTO2);
        listDfctGroupDTO2.setId(2L);
        assertThat(listDfctGroupDTO1).isNotEqualTo(listDfctGroupDTO2);
        listDfctGroupDTO1.setId(null);
        assertThat(listDfctGroupDTO1).isNotEqualTo(listDfctGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listDfctGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listDfctGroupMapper.fromId(null)).isNull();
    }
}
